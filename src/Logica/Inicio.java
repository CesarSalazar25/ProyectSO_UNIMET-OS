package Logica;

import GUI.InterfazSimulador;
import GUI.InterfazRecursos;
import javax.swing.UIManager;

public class Inicio 
{

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
        
        Recurso vecRecursos [] = new Recurso [10];
        Proceso vecProcesos [] = new Proceso [10];
        
        InterfazRecursos ini = new InterfazRecursos(vecRecursos, vecProcesos);
        ini.setVisible(true);
   
    }
    
}