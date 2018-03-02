package Logica;


public class Proceso 
{
    //Variables:
    private String nombre_proceso;                  //Nombre del proceso a crear.
    private int id_proceso;                         //Id del proceso creado.
    private int cant_max [] = new int [100];        //Vector que almacena la cantidad máxima de recursos que el proceso puede llegar a tener.
    
    //Constructor:
    public Proceso(String nombre_proceso, int id_proceso, int[] cant_max) 
    {
        this.nombre_proceso = nombre_proceso;
        this.id_proceso = id_proceso;
        this.cant_max = cant_max;
    }
    
    //Getters y Setters:
    public String getNombre_proceso() 
    {
        return nombre_proceso;
    }

    public void setNombre_proceso(String nombre_proceso) 
    {
        this.nombre_proceso = nombre_proceso;
    }

    public int getId_proceso() 
    {
        return id_proceso;
    }

    public void setId_proceso(int id_proceso) 
    {
        this.id_proceso = id_proceso;
    }

    public int[] getCant_max() 
    {
        return cant_max;
    }

    public void setCant_max(int[] cant_max) 
    {
        this.cant_max = cant_max;
    }
    
    //Devuelve la cantidad máxima en la posición:
    public int getCant_maxPOSICION(int i) 
    {
        return cant_max[i];
    }
    
    
    
    
    
    
    
}
