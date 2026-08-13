package SmartTask;

import SmartTask.vista.MenuConsola;

/**
 * Clase principal de SmartTask.
 * Inicializa la aplicación y pone en marcha el menú.
 * Main
 */
public class Main{
    
    public static void main(String[]args){
        MenuConsola menu = new MenuConsola();
        menu.iniciar();
    }
}