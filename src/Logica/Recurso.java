package Logica;

public class Recurso 
{
    //Variables:
    private String nombre;  //Nombre del recurso a crear.
    private int cantidad;   //Cantidad del recurso creado.
    
    //Constructor:
    public Recurso(String nombre, int cantidad) 
    {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    
    //Getters y Setters:
    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public int getCantidad() 
    {
        return cantidad;
    }

    public void setCantidad(int cantidad) 
    {
        this.cantidad = cantidad;
    }
    
    
}
