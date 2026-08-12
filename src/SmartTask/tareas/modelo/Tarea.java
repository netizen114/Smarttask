package SmartTask.tareas.modelo;

import SmartTask.contrato.Accionable;

/**
 * Representa una tarea genérica de SmartTask.
 * 
 * <p>Es una clase abstracta porque las tareas concretas
 * se representan mediante {@link TareaPersonal} y {@link TareaTrabajo}.</p>
 */

public abstract class Tarea implements Accionable{

    /**
     * Identificador único de la tarea
     */
    private final int id;

    /**
     * nombre o descripción breve de la tarea
     */
    private String nombre;

    /**
     * Prioridad desde 1, menos importante, hasta 5, muy importante.
     */
    private int prioridad;

    /**
     * Estado de la tarea: activa o completada.
     */
    private boolean completada;

    /**
     * Construye una nueva tarea.
     * 
     * @param id identificador positivo de la tarea.
     * @param nombre descripción de la tarea.
     * @param prioridad número entre 1 y 5.
     * @throws IllegalArgumentException si algún dato no es válido.
     */
    public Tarea(int id, String nombre, int prioridad) {
        validarId(id);  
        validarNombre(nombre);
        validarPrioridad(prioridad);
        
        this.id = id;
        this.nombre = nombre.trim();
        this.prioridad = prioridad;
        this.completada = false;
    }

    /**
     * Obtiene el identificador.
     * @return identificador de la tarea.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre.
     * @return nombre de la tarea.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la prioridad.
     * 
     * @return prioridad entre 1 y 5.
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * Cambia el nombre después de validarlo.
     * 
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre){
        validarNombre(nombre);
        this.nombre = nombre.trim();
    }

    /**
     * Cambia la prioridad después de validarla.
     * @param prioridad nueva prioridad entre 1 y 5.
     */
    public void setPrioridad(int prioridad){
        validarPrioridad(prioridad);
        this.prioridad = prioridad;
    }

    /**
     * Marca la tarea como completada.
     */
    @Override
    public void completar() {
        this.completada = true;
    }

    /**
     * Indica si la tarea ya terminó.
     * 
     * @return {@code true} si terminó en caso contrario, {@code false}
     */
    @Override
    public boolean estaCompletada(){
        return completada;
    }

    /**
     * obtiene estado en palabras.
     * @return {@code COMPLETADA} o {@code ACTIVA}.
     */
    public String obtenerEstado(){
        if(completada) {
            return "COMPLETADA";
        }
        return "ACTIVA";
    }
    
    /**
     * Indica el tipo concreto de tarea.
     * <p>Cada clase hija debe entregar su propia respuesta.</p>
     * @return nombre del tipo de tarea.
     */
    public abstract String obtenerTipo();

    /**
     * Entrega un mensaje especial definido por cada clase hija.
     * 
     * @return mensaje particular de la tarea.
     */
    public abstract String obtenerMensajeEspecial();

    /**
     * Construye el detalle completo de la tarea.
     * 
     * <p>Aqui aparece el polimorfismo: Java ejecuta automáticamente  los métodos
     * {@code obtenerTipo()} y {@code obtener MensajeEspecial()} de la clase hija.</p>
     * 
     * @return texto listo para imprimir.
     */
    @Override
    public String obtenerDetalle() {
        return "[" + obtenerEstado() + "] " 
        + "ID: " + id
        + "  | Tipo:  " + obtenerTipo()
        + "  | Tarea: " + nombre
        + "  | Prioridad: " + prioridad
        + "  | " + obtenerMensajeEspecial();
    }

    /**
     * Devuelve el mismo texto que se utiliza para mostrar la tarea.
     * 
     * @return detalle completo.
     */
    @Override
    public String toString() {
        return obtenerDetalle();
    }

    /**
     * Valida que el identificador sea positivo. (De todas maneras el ID es controlado por la clase GestorTarea al llamar al constructor)
    */    
    private void validarId(int id) {
        if(id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero");
        }
    }

    /**
     * Valida que el nombre contenga texto.
    */
    private void validarNombre(String nombre) {
        if(nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la tarea no puede estar vacío.");
        }
    }

    /**
     * Valida que la prioridad esté entre 1 y 5.
     */
    private void validarPrioridad(int prioridad) {
        if(prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 5.");
        }
    }
    
}