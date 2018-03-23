package Logica;

import javax.swing.JTextArea;

public class Prediccion 
{
    //Variables:
    private int Asignacion[][]= new int [10][10];             //Matriz de recursos asignados para cada proceso.
    private int Necesidad[][]= new int [10][10];              //Matriz de requerimientos actual para cada proceso.
    private int Bloqueados[][]= new int [10][10];             //Matriz de procesos bloqueados.
    private int Maximos[][]= new int [10][10];                //Matriz de procesos con sus recursos máximos.
    private int Recursos[]= new int [10];                    //Vector de cantidad total de recursos en el sistema.
    private int Disponibles[]= new int [10];                 //Vector de recursos disponibles para cada proceso.
    private int Auxiliar[]= new int [10];                 
    private int Procesos_finalizados[]= new int [10];      
    private int cant_procesos;              
    private int cant_recursos;
    private long tiempo=0;
    private int Pro_bloqueados = 0;
    private int Pro_bloq_Total = 0;
    private int Pro_finalizados = 0;
    private int Solicitudes = 0;
    private int Procesos_sistema = 0;
    private int Procesos_Totales = 0;
    public JTextArea Consola_P;
    
    //Constructor:
    public Prediccion(Recurso [] rec, JTextArea Consola_P) 
    {
        this.cant_recursos = rec.length;
        this.cant_procesos= 10;
        this.Consola_P = Consola_P;
        
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
                Maximos[i][j]=0;
            }
        }
        
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                Bloqueados[i][j]=0;
            }
        }
        
        for (int i = 0; i < 10; i++) 
        {   
            Procesos_finalizados[i]=0;    
        }
        
        for (int i = 0; i < 10; i++) 
        {
            Recursos[i]=0;
        }
        
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
    
    //Método que Inserta cada proceso en las matrices Maximos y Asignados: 
    public void Insertar_Procesos (int [] maxRecursosPerProceso, int id_Proceso ) 
    {
    
        for (int j = 0; j < Recursos.length; j++) 
        {
            Asignacion[id_Proceso][j]=0;
        }

        for (int j = 0; j < maxRecursosPerProceso.length; j++) 
        {
            Maximos[id_Proceso][j]=maxRecursosPerProceso[j];
        } 
        
        Procesos_sistema++;
        Procesos_Totales++;
    }
    
    //Método que asigna los recursos a cada proceso:
    public boolean Asignar (int id_Proceso, int [] solicitud )
    {   
        boolean desbloquea = true;  //Por defecto el proceso esta desbloqueado al momento de la solicitud 
        //boolean bloqueado = false;

        //Se comprueba si hay un proceso Bloqueado o no:
        for (int i = 0; i < Bloqueados[id_Proceso].length; i++) 
        {
            if (Bloqueados[id_Proceso][i] < solicitud[i]) 
            {
                desbloquea = false;
            }
        }
        //Se desbloquea el proceso de acuerdo al boleean obtenido de la comprobación previa:
        if (desbloquea == true) 
        {
            Desbloquear_Proceso (id_Proceso);
            //Consola_P.append("\nSe desbloqueó el proceso: "+id_Proceso+" \n");
            return true;
        }
        
        //Condiciones para asignar los recursso:
        boolean conceder = true;
        
        //Se bloquea si se trata de asignar más recursos de los que hay disponibles
        for (int i = 0; i < Recursos.length; i++) 
        {
            if (solicitud[i] > Disponibles[i]) 
            {
                Bloquear_Proceso(id_Proceso, solicitud, 0);                
                conceder = false;
            }
        }
        
        //Se asignan los recursos:
        if (conceder == true) 
        {
                
            for (int i = 0; i <Recursos.length; i++) 
            {
                Asignacion[id_Proceso][i] = Asignacion[id_Proceso][i] + solicitud[i];
                Disponibles[i]= Disponibles[i] - solicitud[i];
            }
            return true; 
        }
        //No se asignan los recursos:
        else 
        {
            return false;
        }
    }
    
    //Método que bloquea a un proceso:
    public void Bloquear_Proceso (int id_Proceso, int [] solicitud, int x) 
    {
        
        if (x==1) 
        {
        
            for (int i = 0; i <solicitud.length; i++) 
            {
                Bloqueados[id_Proceso][i] = solicitud[i];
                Asignacion[id_Proceso][i] = Asignacion[id_Proceso][i] - solicitud[i];
                Disponibles[i] = Disponibles[i] + solicitud[i];
                System.out.println("No se asigno al Bloquear Pre: "+Disponibles[i]);
                 
            }
            Pro_bloqueados++;
            Pro_bloq_Total++;
            Consola_P.append("\nSe bloqueó el proceso "+id_Proceso+" \n");
            Consola_P.append("La asignación del recurso no pudo darse \n");
        }
        else
        {
            for (int i = 0; i <solicitud.length; i++) 
            {
                Bloqueados[id_Proceso][i] = solicitud[i];
            }
            Pro_bloqueados++;
            Pro_bloq_Total++;
            Consola_P.append("\nSe bloqueó el proceso "+id_Proceso+" \n");
            Consola_P.append("La asignación del recurso no pudo darse \n");
        }
    }
    
    //Método que desbloquea a un proceso:
    public void Desbloquear_Proceso (int id_Proceso) 
    {
    
        for (int i = 0; i < Bloqueados[id_Proceso].length; i++) 
        {
            Asignacion[id_Proceso][i] = Asignacion[id_Proceso][i] + Bloqueados[id_Proceso][i];
            Bloqueados [id_Proceso][i] = 0;
        }
        
        Pro_bloqueados--;
    }
    
    //Método que calcula la matriz Necesidad (Máximos-Asignación):
    public void Calculo_Necesidad() 
    {
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < cant_recursos; j++) 
            {
                Necesidad[i][j] = Maximos[i][j] - Asignacion[i][j];
            }
        }
    }
 
    //Método que chequea si todos los recursos para el proceso pueden ser asignados:
    public boolean chequear(int i, int [] auxiliar) 
    {
        for (int j = 0; j < cant_recursos; j++) 
        {
            if (auxiliar[j] < Necesidad[i][j]) 
            {
                return false;
            }
        }
        return true;
    }
 
    //Método que comprueba si el estado del sistema es Seguro o no (Algoritmo del Banquero): 
    public boolean esSeguro(int id_Proceso) 
    {

        Calculo_Necesidad();
        boolean [] done = new boolean[cant_procesos];
        int j = 0;
        
        for (int x = 0; x <Disponibles.length; x++) 
        {
            Auxiliar[x] = Disponibles[x];
        }
        
        //Hasta que todos los procesos se asignen
        while (j < cant_procesos) 
        {     
            boolean asignado = false;
            
            for (int i = 0; i < cant_procesos; i++) 
            {
                
                if (!done[i] && chequear(i, Auxiliar)) 
                {  
                    for (int k = 0; k < cant_recursos; k++) 
                    {
                        Auxiliar[k] = Auxiliar[k] - Necesidad[i][k] + Maximos[i][k];
                    }
                    //System.out.println("Proceso asignado : " + i);
                    asignado = done[i] = true;
                    j++;
                }
            }
            
            if (!asignado) 
            {
                break;  
            }
        }
        
        if (j == cant_procesos) 
        {
            Consola_P.append("\nAsignado de forma segura \n");
            return true;
        } 
        else 
        {
            //Consola_P.append("\nEstado Seguro, todos los procesos se pueden asignar de forma segura \n");
            return false;
        }
    } 
    
    //Método que ejecuta el algoritmo:
    public void Ejecutar(int id_Proceso, int [] solicitud)
    {
        
        //System.out.println(cant_procesos);
        
        long tiempoAux = System.nanoTime();
        boolean end = Comprobar_Finalizacion(id_Proceso);
        if(end == false)
        {
            boolean valido = Asignar(id_Proceso, solicitud);
            if(valido == true)
            {
                if(esSeguro(id_Proceso) == false)
                {
                   //Se bloquea el proceso que realizó la solicitud y se devuelve la solicitud a Disponibles 
                   Bloquear_Proceso(id_Proceso, solicitud, 1);
                }
                else
                {
                   Finalizar_Proceso(id_Proceso);
                }
            }
            else
                 Consola_P.append("\nSolicitud Negada \n");
        }
        else
        {
                Consola_P.append("\nAl proceso "+id_Proceso+" no es posible asignarle recursos, puesto que ha finalizado su ejecución \n");            
        }
        long finishTime = System.nanoTime();
        tiempo = (finishTime-tiempoAux)/1000000;
        
    }
    
    //Método que finaliza el proceso:
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
            for (int i = 0; i < Asignacion[id_Proceso].length; i++) 
            {
                Disponibles[i] = Asignacion[id_Proceso][i];
                Asignacion[id_Proceso][i] = 0;
                Maximos[id_Proceso][i] = 0;
                System.out.println("Pre al finalizar: "+Disponibles[i]);
            }
            
            Procesos_finalizados[id_Proceso]= 1;
            Pro_finalizados++;
            Procesos_sistema--;
            Consola_P.append("\nProceso numero: "+id_Proceso+", finalizó exitosamente \n");
        }
    }
    
    //Método que comprueba si un proceso ha finalizado:
    public boolean Comprobar_Finalizacion (int id_Proceso) 
    {
        if (Procesos_finalizados[id_Proceso] != 0) 
        {
            return true;
        }
        else
            return false;
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

    public int[] getAuxiliar() 
    {
        return Auxiliar;
    }

    public void setAuxiliar(int[] Auxiliar) 
    {
        this.Auxiliar = Auxiliar;
    }

    public int[] getProcesos_finalizados() 
    {
        return Procesos_finalizados;
    }

    public void setProcesos_finalizados(int[] Procesos_finalizados) 
    {
        this.Procesos_finalizados = Procesos_finalizados;
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

    public long getTiempo() 
    {
        return tiempo;
    }

    public void setTiempo(long tiempo) 
    {
        this.tiempo = tiempo;
    }

    public int getPro_bloqueados() 
    {
        return Pro_bloqueados;
    }

    public void setPro_bloqueados(int Pro_bloqueados) 
    {
        this.Pro_bloqueados = Pro_bloqueados;
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

    public int getProcesos_Totales() 
    {
        return Procesos_Totales;
    }

    public void setProcesos_Totales(int Procesos_Totales) 
    {
        this.Procesos_Totales = Procesos_Totales;
    }
  
}
