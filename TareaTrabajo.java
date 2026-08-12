/**
 * Representa una tarea de caracter laboral.
 * 
 * <p>Esta clase HEREDA atributos y métodos desde {@code Tarea}.</p>
 */
public class TareaTrabajo extends Tarea{

    /**
     * Indica si la tarea es diurna.
     */
    private boolean esDiurna;

    /**
     * Construye una tarea de caracter laboral.
     * 
     * @param id identificador de la tarea.
     * @param nombre descripción de la tarea.
     * @param prioridad prioridad entre 1 y 5.
     * @param esDiurna indica si la tarea se realiza en la jornada de la mañana o no.
     */
    public TareaTrabajo(int id, String nombre, int prioridad, boolean esDiurna){
        super(id, nombre, prioridad);
        this.esDiurna = esDiurna;
    }

    /**
     * Indica si la tarea se debe realizar durante la jornada de la mañana o durante la tarde.
     * 
     * @return {@code true} si la tarea es diurna o {@code false} si es vespertina.
     */
    public boolean isDiurna() {
        return esDiurna;
    }

    /**
     * Cambia si la tarea se realiza durante la mañana.
     * @param esDiurna nuevo valor.
     */
    public void setDiurna(boolean esDiurna) {
        this.esDiurna = esDiurna;
    }

    /**
     * Entrega el tipo concreto de esta tarea.
     * 
     * @return texto {@code Laboral}
     */
    @Override
    public String obtenerTipo() {
        return "LABORAL ";
    }

    /**
     * Entrega el mensaje propio de una tarea de caracter laboral.
     * 
     * @return  tipo de tarea.
     */
    @Override
    public String obtenerMensajeEspecial() {
        String turnoTarea;

        if (esDiurna) {
            turnoTarea = "diurno";
        }else {
            turnoTarea = "vespertino";
        }

        return "Tarea laboral y corresponde al turno "+ turnoTarea;
    }
}