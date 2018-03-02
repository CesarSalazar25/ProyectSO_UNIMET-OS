
package Logica;

//Librería:
//import javax.swing.JLabel;
//import javax.swing.JTextArea;

public class Prediccion 
{
    //Variables:
    private int Asignacion[][]= new int [1][1];             //Matriz de recursos asignados para cada proceso.
    private int Necesidad[][]= new int [1][1];              //Matriz de requerimientos actual para cada proceso.
    private int Bloqueados[][]= new int [1][1];             //Matriz de procesos bloqueados.
    private int Maximos[][]= new int [1][1];                //Matriz de procesos con sus recursos máximos.
    private int Recursos[]= new int [1];                    //Vector de cantidad total de recursos en el sistema.
    private int Disponibles[]= new int [1];                 //Vector de recursos disponibles para cada proceso.
    private int Auxiliar[]= new int [1];                 
    private int Procesos_finalizados[]= new int [1];      
    private int cant_procesos;              
    private int cant_recursos;
    private long tiempo=0;
    private int Pro_bloqueados = 0;
    private int Pro_bloq_Total = 0;
    private int Pro_finalizados = 0;
    private int Solicitudes = 0;
    private int Procesos_sistema = 0;
    private int Procesos_Totales = 0;
    //public TextArea ConsolePrediccion;
    
    //Constructor:
    
    
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
