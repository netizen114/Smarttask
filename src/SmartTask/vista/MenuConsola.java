package SmartTask.vista;

import java.util.Scanner;
import java.util.List;

import SmartTask.servicio.GestorTareas;
import SmartTask.tareas.modelo.Tarea;

/**
 * Gestiona la interacción entre el usuario y la apliación, mediante un menú en consola.
 * MenuConsola
 */
public class MenuConsola {
    private final Scanner teclado = new Scanner(System.in);
    private final GestorTareas gestor;


    public MenuConsola() {
        gestor = new GestorTareas();
    }
    
    /**
     * Imprime el menú en pantalla
     */
    private void mostrarMenu(){
        
        System.out.println("=========================================");
        System.out.println("=============== SmartTasK=================");
        System.out.println("=========================================");
        System.out.println("1.Agregar tarea");
        System.out.println("2.Listar tareas activas");
        System.out.println("3.Listar tareas completas");
        System.out.println("4.Marcar tarea como completada");
        System.out.println("5.Eliminar tarea");
        System.out.println("0.Salir");
    }

    /**
     * Lee la opción marcada por el usuario y la retorna
     * @return
     */
    private int leerOpcion(){
        return validarInt();
    }

    /**
     * Recibe como parámetro el input del usuario y realiza distintas acciones dependiendo del caso.
     * @param opcion
     */
    private void ejecutarOpcion(int opcion){
        switch (opcion) {
            case 1:
                try {
                    agregarTarea();
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;                
            case 2:
                imprimirTareas(gestor.listarActivas());
                break;
            case 3:
                imprimirTareas(gestor.listarCompletadas());
                break;
            case 4:
                marcarTareaComoCompletada();
                break;
            case 5:
                eliminarTarea();
                break;
            case 0:
                System.out.println("Hasta pronto!");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
   
    /**
     * Inicializa el programa y lo mantiene corriendo en un bucle mientras el usuario no indique lo contrario mediante el input "0".
     */
    public void iniciar(){
        int opcion = 6;
        while (opcion != 0){
            mostrarMenu();
            opcion = leerOpcion();
            ejecutarOpcion(opcion);
        }
    }

    
    /**
     * Pide al usuario ingresar los datos para crear la tarea
     */
    private void agregarTarea(){
        
        int tipo, prioridad;
        String nombre;
        boolean esDiurna;

        System.out.println("Ingrese el tipo de tarea");
        System.out.println("1. Tarea personal");
        System.out.println("2. Tarea laboral");

        tipo = validarInt();
        

        switch (tipo) {

            case 1:
                nombre = pedirNombre();
                prioridad = pedirPrioridad();
                gestor.agregarTareaPersonal(nombre, prioridad);
                System.out.println("Tarea agregada con éxito");
                break;
            case 2:
                nombre = pedirNombre();
                prioridad = pedirPrioridad();
                esDiurna = pedirJornadaTarea();
                gestor.agregarTareaTrabajo(nombre, prioridad, esDiurna);
                System.out.println("Tarea agregada con éxito");
                break;
            default:
                System.out.println("Opción no valida");
                break;
        }

    }

    /**
     * Pide al usuario ingresar el nombre y lo retorna.
     */
    private String pedirNombre(){
        System.out.println("Ingrese nombre de la tarea");
        return teclado.nextLine();
    }

    /**
     * Pide al usuario ingresar la prioridad de la tarea, valida que sea entre 1 y 5, sino vuelve a pedir, luego, si es válida la retorna.
     */
    private int pedirPrioridad(){
    int prioridad = 0;

    while (prioridad < 1 || prioridad > 5){
        System.out.println("Ingrese la prioridad de la tarea (1 a 5):");
        prioridad = validarInt();

        if (prioridad < 1 || prioridad > 5){
            System.out.println("La prioridad debe estar entre 1 y 5.");
        }
    }
    return prioridad;
    }

    /**
     * Si el tipo de tarea es laboral, 
     */
    private boolean pedirJornadaTarea(){
        int turno = 0;
        while (turno != 1 && turno != 2){
            System.out.println("Ingrese horario de la tarea");
            System.out.println("1.Turno Diurno");
            System.out.println("2.Turno Vespertino");
            turno = validarInt();
            
            switch (turno) {
                case 1:
                    return true;
                    
                case 2:
                    return false;
            
                default:
                    System.out.println("Opcion no válida");
                      
            }
        }    
    
        return false;
    }

    /**
     * Valida que la id entregada al eliminar una tarea sea un int, y luego llama al método eliminarTarea de la clase GestorTareas
     */
    private void eliminarTarea(){
        System.out.println("Ingrese el id de la tarea que desea eliminar");
        int id = validarInt();
        
        boolean tareaEliminada = gestor.eliminarTarea(id);
        if (tareaEliminada){
            System.out.println("La tarea fue eliminada con éxito");
            return;
        }
        System.out.println("La tarea con la id "+id+" no fue encontrada");
    }

    /**
     * Valida que la id entregada al eliminar una tarea sea un int, y luego llama al método marcarComoCompletada de la clase GestorTareas
     */
    private void marcarTareaComoCompletada() {
        System.out.println("Ingrese el id de la tarea que desea marcar como completada");
        int id = validarInt();
        
        boolean tareaCompletada = gestor.marcarComoCompletada(id);
        if (tareaCompletada){
            System.out.println("La tarea fue marcada como completada");
            return;
        }
        System.out.println("No se encontró la tarea con el id "+ id);
    }

    /**
     * Imprime linea por línea la información de las tareas
     * @param listaTareas
     */
    private void imprimirTareas(List<Tarea> listaTareas){
        for (Tarea tarea : listaTareas){
            System.out.println(tarea);
            System.out.println("");
        }
    }

    /**
     * Valida que cuando se le solicita, lo ingresado por un usuario sea un entero y no una cadena de texto.
     */
    private int validarInt(){
        while (!teclado.hasNextInt()) {
            System.out.println("Debe ingresar un número. Intente nuevamente");
            teclado.next(); // Descarta el valor incorrecto del escáner
        }

        int numero = teclado.nextInt();
        teclado.nextLine();
        return numero;
    }
}
