/**
 * Representa una tarea de caracter personal.
 * 
 * <p>Esta clase HEREDA atributos y métodos desde {@code Tarea}.</p>
 */
public class TareaPersonal extends Tarea {

 
    /**
     * Construye una tarea de caracter personal.
     * @param id identifica la tarea.
     * @param nombre descripción de la tarea.
     * @param prioridad prioridad entre 1 y 5.
     */
    public TareaPersonal(int id, String nombre, int prioridad){
        super(id, nombre, prioridad);
    }

    /**
     * Entrega el tipo concreto de esta tarea.
     * 
     * @return texto {@code PERSONAL}
     */
    @Override
    public String obtenerTipo() {
        return "PERSONAL";
    }

    /**
     * Entrega el mensaje propio de una tarea de caracter personal.
     * 
     * @return mensaje del tipo de tarea personal.
     */
    @Override
    public String obtenerMensajeEspecial() {
        return "Esta tarea es de caracter personal";
                
    }
}
