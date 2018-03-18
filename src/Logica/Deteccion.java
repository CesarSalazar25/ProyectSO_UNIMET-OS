package Logica;

import javax.swing.JTextArea;   //Para mostrar todos los detalles relativos a la Detección del Interbloqueo.

public class Deteccion 
{
    //Variables:
    private int Asignacion[][]= new int [10][10];              //Matriz de recursos asignados para cada proceso.
    private int Necesidad[][]= new int [10][10];               //Matriz de requerimientos actual para cada proceso.
    private int Bloqueados[][]= new int [10][10];              //Matriz de procesos bloqueados.
    private int Maximos[][]= new int [10][10];                 //Matriz de procesos con sus recursos máximos.
    private int Recursos[]= new int [10];                       //Vector de cantidad total de recursos en el sistema.
    private int Disponibles[]= new int [10];                    //Vector de recursos disponibles para cada proceso.
    private int Temporal[]= new int [10];                       //Vector temporal que recibe los recursos disponibles para comparar.
    private int Procesos_finalizados[]= new int [10];           //Vector que almacena los procesos finalizados.
    private int Procesos_eliminados[]= new int [10];            //Vector que almacena los procesos eliminados.
    private boolean Procesos_marcados[]= new boolean [10];      //Vector de procesos marcados (proceso sin marcar, esta en un interbloqueo).
    private int cant_procesos;              
    private int cant_recursos;
    private int p_finalizados=0;
    private int p_eliminados=0;
    private int bloqueo_actual=0;
    private long tiempo=0;
    private int Pro_bloq_Total = 0;
    private int Pro_finalizados = 0;
    private int Solicitudes = 0;
    private int Procesos_sistema = 0;
    private int Pro_eliminados = 0;
    private int Procesos_Totales = 0;
    public JTextArea Consola_D;
    
    
    //Constructor:
    public Deteccion(Recurso[] rec, JTextArea Consola_D)
    {
        this.cant_recursos=rec.length;
        this.Consola_D=Consola_D;
        
        //Se inicializan las matrices y vectores en cero: 
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++) 
            {
                Asignacion[i][j]=0;
            }
        }
                
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                Necesidad[i][j]=0;
            }
        }
        
        for (int i = 0; i < 10; i++) 
        {
            Temporal[i]=0;    
        }
                
        for (int i = 0; i < 10; i++) 
        {
            Procesos_marcados[i]=false;    
        }
        
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                Maximos[i][j]=0;
            }
        }
    
        for (int i = 0; i < 10; i++) 
        {
            Procesos_eliminados[i]=0;           
        }
                
        for (int i = 0; i < 10; i++)
        {
            Procesos_finalizados[i]=0;           
        }
               
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                Bloqueados[i][j]=0;
            }
        }
        
        //Se rellenan los vectores de 'Recursos' y 'Disponibles':
        for (int i = 0; i < 10; i++) 
        {
            if (rec[i]!=null) 
            {
                Recursos[i] = rec[i].getCantidad();
            }
            
        }
        
         for (int i = 0; i < 10; i++) 
         {
            if (rec[i]!=null) 
            {
                Disponibles[i] = rec[i].getCantidad();
            }
        }        
        
    }
    
    //Método que inserta cada proceso en las matrices Maximos y Asignación:
    public void Insertar_Procesos (int [] max_recursos, int id_Proceso) 
    {
    
        for (int j = 0; j < Maximos.length; j++) 
        {
            Maximos[id_Proceso][j]=max_recursos[j];
        }
        
        //Inicialmente todos los procesos están sin marcar:
        Procesos_marcados[id_Proceso]=false;
        
        //Se aumenta el contador de procesos totales y en el sistema: 
        Procesos_sistema++;
        Procesos_Totales++;
    }
    
    //Método que asigna los recursos a cada proceso:
    public boolean Asignar_Recursos (int id_Proceso, int [] solicitud )
    {
        
        boolean desbloquea = true;
        boolean bloqueado = false;
        
        for (int i = 0; i < Bloqueados[id_Proceso].length; i++) 
        {
            if (Bloqueados[id_Proceso][i] != solicitud[i]) 
            {
                desbloquea = false;
            }
            
            if (Bloqueados[id_Proceso][i] != 0) 
            {
                bloqueado = true;
            }
        }
        
        if (desbloquea == true) 
        {
            Desbloquear_Proceso(id_Proceso);
            return true;
        }
        else if (bloqueado == false) 
        {
            boolean conceder = true;
            
            for (int i = 0; i < Disponibles.length; i++)
            {
                
                if (solicitud[i] > Disponibles[i]) 
                {
                    conceder = false;
                }
            }
            
            if (conceder == true) 
            {
                
                for (int i = 0; i <Disponibles.length; i++) 
                {
                    Asignacion[id_Proceso][i] = Asignacion[id_Proceso][i] + solicitud[i];
                    Disponibles[i]=Disponibles[i] - solicitud[i];
                }
            Consola_D.append("Asignado de forma segura \n");                
            }
            else 
            {
                Bloquear_Proceso(id_Proceso, solicitud);
            }
            return true;    
        }
        else
        {
            return false;
        }
    }
    
    // Metodo que bloquea a un proceso:
    public void Bloquear_Proceso (int id_Proceso, int [] solicitud) 
    {
    
        for (int i = 0; i <solicitud.length; i++) 
        {
            Bloqueados[id_Proceso][i] = solicitud[i];    
        }
        bloqueo_actual++;
        Pro_bloq_Total++;
        Consola_D.append("Se bloqueó el proceso: "+id_Proceso+" \n");
    }
    
    // Método que desbloquea a un proceso:
    public void Desbloquear_Proceso (int id_Proceso) 
    {
    
        for (int i = 0; i < Bloqueados[id_Proceso].length; i++) 
        {
            Asignacion[id_Proceso][i] = Asignacion[id_Proceso][i] + Bloqueados[id_Proceso][i];
            Bloqueados [id_Proceso][i] = 0;
        }
        bloqueo_actual--;
        Consola_D.append("Se desbloqueó el proceso: "+id_Proceso+" \n");
    }
    
    // Método que calcula la matriz Necesidad (Máximos-Asignación):
    public void Calculo_Necesidad() 
    {
        for (int i = 0; i < Asignacion.length; i++) 
        {
            for (int j = 0; j < Disponibles.length; j++) 
            {
                Necesidad[i][j] = Maximos[i][j] - Asignacion[i][j];
            }
        } 
    }
    
    // Metodo de detección del interbloqueo:
    private boolean Detectar()
    {
        //entrada();
        Calculo_Necesidad();
        
        for (int i = 0; i < Asignacion.length; i++) 
        {
            boolean marcar = true;
            
            for (int j = 0; j < Asignacion[i].length; j++)
            {
                
                if(Asignacion[i][j] != 0)
                {
                   marcar = false;
                }
            }
            
            if(marcar == true)
            {
                Procesos_marcados[i] = true;
            }
                
        }
        
        for (int i = 0; i <Disponibles.length; i++) 
        {
            
            Temporal[i] = Disponibles[i];     
        }
        
        boolean proceder = true;
        
        while(proceder == true)
        {
            proceder = false;
            
            for(int i = 0; i <Asignacion.length; i++) 
            {
                
                boolean comprob = true;
               
                if(Procesos_marcados[i] == false)
                {         
                    for (int j = 0; j < Asignacion[i].length; j++) 
                    {
                        
                        if(Necesidad[i][j] > Temporal[j])
                        {
                           comprob = false;
                        }
                    }
                    
                    if(comprob == true)
                    {  
                        proceder = true;
                        
                        for (int j = 0; j < Asignacion[i].length; j++) 
                        {
                            Temporal[j] = Temporal[j] + Asignacion[i][j];
                            Procesos_marcados[i] = true;
                        }
                    }
                }    
            }
        }
        
        boolean exito = true;
        
        for (int i = 0; i < Procesos_marcados.length; i++) 
        {
            
            if(Procesos_marcados[i] == false)
            {
                
                Eliminar_Proceso(i);
                Procesos_Totales--;
                exito = false;
            }
        }
        return exito;
    }
    
    // Metodo que ejecuta el algoritmo: 
    public void Ejecutar(int id_Procesos, int[] solicitud)
    {
       
        long tiempoAux = System.nanoTime();
        boolean finalizo = Comprobar_Finalizacion(id_Procesos);
        boolean eliminado = Comprobar_Eliminacion(id_Procesos);
        
        if( finalizo == false && eliminado == false)
        {
            boolean x;
            Asignar_Recursos(id_Procesos, solicitud);
            x = Detectar();
            
            if(x == true)
            {
               Finalizar_Proceso(id_Procesos);    
            }
        }
        
        for (int i = 0; i < Procesos_marcados.length; i++) 
        {
           Procesos_marcados[i] = false;
        }
        
        long finishTime = System.nanoTime();
        tiempo = (finishTime - tiempoAux)/1000000;
        
        //System.out.println(ProcesosSistema);
        
    }
    
    // Metodo que finaliza el proceso
    public void Finalizar_Proceso (int id_Proceso)
    {
        boolean procesoFinalizo = true;
        
        for (int i = 0; i < Asignacion[id_Proceso].length; i++) 
        {
            if (Asignacion[id_Proceso][i] != Maximos[id_Proceso][i]) 
            {
                procesoFinalizo = false;
            }
        }
        
        if (procesoFinalizo == true) 
        {
            for (int i = 0; i < Asignacion[id_Proceso][i]; i++) 
            {
                Disponibles[i] = Asignacion[id_Proceso][i];
                Asignacion[id_Proceso][i] = 0;
                Maximos[id_Proceso][i] = 0;
            }
            
            Procesos_finalizados[id_Proceso]= 1;
            p_finalizados++;
            Procesos_sistema--;
            Consola_D.append("Proceso: "+id_Proceso+", finalizó exitosamente \n");
        }
    }
    
    //Método que comprueba si un proceso ha finalizado:
    public boolean Comprobar_Finalizacion (int id_Proceso) 
    {
        if (Procesos_finalizados[id_Proceso]!=0) 
        {
            return true;
        }
        else
            return false;
    }
    
    // Método que elimina un proceso:
    private void Eliminar_Proceso(int id_Proceso)
    {
        for (int i = 0; i <= Asignacion[id_Proceso][i]; i++) 
        {
                Disponibles[i]= Disponibles[i]+Asignacion[id_Proceso][i];
                Asignacion[id_Proceso][i] = 0;
                Maximos[id_Proceso][i] = 0;
        }
        Procesos_eliminados[id_Proceso] = 1;
        Pro_eliminados++;
        Procesos_sistema--;
        Consola_D.append("Se eliminó el proceso: "+id_Proceso+" \n");  
        
    }
    
    //Método que comprueba si hay procesos eliminados:
    private boolean Comprobar_Eliminacion(int id_Procesos)
    { 
        if(Procesos_eliminados[id_Procesos] != 0)
        {
           return true;
        }
        else
        {
           return false;
        }
    }
    
    
    //Getters y Setters:
    public int[][] getAsignacion() 
    {
        return Asignacion;
    }

    public void setAsignacion(int[][] Asignacion) 
    {
        this.Asignacion = Asignacion;
    }

    public int[][] getNecesidad() 
    {
        return Necesidad;
    }

    public void setNecesidad(int[][] Necesidad) 
    {
        this.Necesidad = Necesidad;
    }

    public int[][] getBloqueados() 
    {
        return Bloqueados;
    }

    public void setBloqueados(int[][] Bloqueados) 
    {
        this.Bloqueados = Bloqueados;
    }

    public int[][] getMaximos() 
    {
        return Maximos;
    }

    public void setMaximos(int[][] Maximos) 
    {
        this.Maximos = Maximos;
    }

    public int[] getRecursos() 
    {
        return Recursos;
    }

    public void setRecursos(int[] Recursos) 
    {
        this.Recursos = Recursos;
    }

    public int[] getDisponibles() 
    {
        return Disponibles;
    }

    public void setDisponibles(int[] Disponibles) 
    {
        this.Disponibles = Disponibles;
    }

    public int[] getTemporal() 
    {
        return Temporal;
    }

    public void setTemporal(int[] Temporal) 
    {
        this.Temporal = Temporal;
    }

    public int[] getProcesos_finalizados() 
    {
        return Procesos_finalizados;
    }

    public void setProcesos_finalizados(int[] Procesos_finalizados) 
    {
        this.Procesos_finalizados = Procesos_finalizados;
    }

    public int[] getProcesos_eliminados() 
    {
        return Procesos_eliminados;
    }

    public void setProcesos_eliminados(int[] Procesos_eliminados) 
    {
        this.Procesos_eliminados = Procesos_eliminados;
    }

    public boolean[] getProcesos_marcados() 
    {
        return Procesos_marcados;
    }

    public void setProcesos_marcados(boolean[] Procesos_marcados) 
    {
        this.Procesos_marcados = Procesos_marcados;
    }

    public int getCant_procesos() 
    {
        return cant_procesos;
    }

    public void setCant_procesos(int cant_procesos) 
    {
        this.cant_procesos = cant_procesos;
    }

    public int getCant_recursos() 
    {
        return cant_recursos;
    }

    public void setCant_recursos(int cant_recursos) 
    {
        this.cant_recursos = cant_recursos;
    }

    public int getP_finalizados() 
    {
        return p_finalizados;
    }

    public void setP_finalizados(int p_finalizados) 
    {
        this.p_finalizados = p_finalizados;
    }

    public int getP_eliminados() 
    {
        return p_eliminados;
    }

    public void setP_eliminados(int p_eliminados) 
    {
        this.p_eliminados = p_eliminados;
    }

    public int getBloqueo_actual() 
    {
        return bloqueo_actual;
    }

    public void setBloqueo_actual(int bloqueo_actual) 
    {
        this.bloqueo_actual = bloqueo_actual;
    }

    public long getTiempo() 
    {
        return tiempo;
    }

    public void setTiempo(long tiempo) 
    {
        this.tiempo = tiempo;
    }

    public int getPro_bloq_Total() 
    {
        return Pro_bloq_Total;
    }

    public void setPro_bloq_Total(int Pro_bloq_Total) 
    {
        this.Pro_bloq_Total = Pro_bloq_Total;
    }

    public int getPro_finalizados() 
    {
        return Pro_finalizados;
    }

    public void setPro_finalizados(int Pro_finalizados) 
    {
        this.Pro_finalizados = Pro_finalizados;
    }

    public int getSolicitudes() 
    {
        return Solicitudes;
    }

    public void setSolicitudes(int Solicitudes) 
    {
        this.Solicitudes = Solicitudes;
    }

    public int getProcesos_sistema() 
    {
        return Procesos_sistema;
    }

    public void setProcesos_sistema(int Procesos_sistema) 
    {
        this.Procesos_sistema = Procesos_sistema;
    }

    public int getPro_eliminados() 
    {
        return Pro_eliminados;
    }

    public void setPro_eliminados(int Pro_eliminados) 
    {
        this.Pro_eliminados = Pro_eliminados;
    }

    public int getProcesos_Totales() 
    {
        return Procesos_Totales;
    }

    public void setProcesos_Totales(int Procesos_Totales) 
    {
        this.Procesos_Totales = Procesos_Totales;
    }

    
}
