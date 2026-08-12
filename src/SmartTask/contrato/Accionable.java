package SmartTask.contrato;

/**
 * Define las acciones mínimas que debe poder realizar una tarea.
 * 
*/

public interface Accionable{

    /**
     * Cambia la tarea a estado completo.
     */
    void completar();

    /**
     * Indica si la tarea ya fue completada.
     * 
     * @return {@code true} si está completada o {@code false} si sigue activa.
     */
    boolean estaCompletada();

    /**
     * Construye un texto con la información principal de la tarea.
     * 
     * @return detalle listo para mostrar en consola.
     */
    String obtenerDetalle();
}   