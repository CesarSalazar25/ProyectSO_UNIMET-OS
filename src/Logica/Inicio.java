package Logica;

import GUI.InterfazSimulador;
import GUI.InterfazRecursos;
import javax.swing.UIManager;

public class Inicio 
{
    //Main:
    public static void main(String[] args) 
    {
        try 
        { 
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } 
        catch (Exception ex) 
        { 
        ex.printStackTrace(); 
        }   
        
        //Se crean los vectores correspondientes a Procesos y Recursos:
        Recurso vecRecursos [] = new Recurso [10];
        Proceso vecProcesos [] = new Proceso [10];
        
        //Se inicia la interfaz con la que se designaran los Recursos a usar:
        InterfazRecursos ini = new InterfazRecursos(vecRecursos, vecProcesos);
        ini.setVisible(true);
   
    }
    
}