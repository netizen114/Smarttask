import java.util.ArrayList;
import java.util.List;

/** 
 * Administrar las tareas.
 * 
 * <p>Su única responsabilidad es manejar la colección: agregar, buscar,
 * listar, completar y eliminar tareas. No imprime menús ni lee el teclado.</p>
 */
public class GestorTareas {

    /**
     * Lista polimórfica; que permite guardar tareas personales y laborales.
     */
    private final List<Tarea> tareas;

    /**
     * Próximo identificador disponible.
     */
    private int siguienteId;
    
    /**
     * Construye un gestor vacío.
     */
    public GestorTareas() {
        this.tareas = new ArrayList<>();
        this.siguienteId = 1;
  
    }

    /**
     * Crea y agrega una tarea personal.
     * 
     * @param nombre descripción de la tarea.
     * @param prioridad prioridad entre 1 y 5.
     * @return objeto creado y agregado.
     */
    public TareaPersonal agregarTareaPersonal(String nombre, int prioridad) {
            
        TareaPersonal nuevaTarea = new TareaPersonal(siguienteId, nombre, prioridad);

        tareas.add(nuevaTarea);
        siguienteId++;
        return nuevaTarea;
    }

    /**
     * Crea y agrega una tarea de caracter laboral.
     * 
     * @param nombre descripción de la tarea.
     * @param prioridad prioridad entre 1 y 5.
     * @param esDiurna indica si la tarea se realiza en la jornada de la mañana o no.
     * @return objeto creado y agregado.
     */
    public TareaTrabajo agregarTareaTrabajo(String nombre, int prioridad, boolean esDiurna) {
        
        TareaTrabajo nuevaTarea = new TareaTrabajo(siguienteId, nombre, prioridad, esDiurna);
        tareas.add(nuevaTarea);
        siguienteId++;
        return nuevaTarea;
    }

    /**
     * Obtiene una copia de todas las tareas.
     * 
     * <p>Se devuelve una copia para proteger la lista interna 
     * y así se refuerza el encapsulamiento.</p>
     * 
     * @return nueva lista con todas las tareas
     */
    public List<Tarea> listarTodas() {
        return new ArrayList<>(tareas);
    }

    /**
     * Obtiene solamente las tareas activas.
     * 
     * @return lista de tareas no completadas.
     */
    public List<Tarea> listarActivas() {
        List<Tarea> activas = new ArrayList<>();

        for(Tarea tarea : tareas) {
            if (!tarea.estaCompletada()) {
                activas.add(tarea);
            }
        }
        return activas;
    }

    /**
     * Obtiene solamente las tareas completadas.
     * 
     * @return lista de tareas completadas.
     */
    public List<Tarea> listarCompletadas() {
        List<Tarea> completadas = new ArrayList<>();

        for(Tarea tarea : tareas) {
            if (tarea.estaCompletada()) {
                completadas.add(tarea);
            }
        }
        return completadas;
    }

    /**
     * Busca una tarea por su identificador
     * 
     * @param id identificador buscado.
     * @return tarea encontrada o {@code null} si no existe.
     */
    public Tarea buscarPorId(int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return tarea;
            } 
        }
        return null;
    }

    /**
     * Marca una tarea como completada.
     * 
     * @param id identificador de la tarea.
     * @return {@code true} si se encontró y completó; {@code false} si no existe.
     */
    public boolean marcarComoCompletada(int id) {
        Tarea tareaEncontrada = buscarPorId(id);

        if (tareaEncontrada == null) {
            return false;
        }

        tareaEncontrada.completar();
        return true;
    }

    /**
     * Elimina una tarea usando su identificador.
     * 
     * @param id identificador de la tarea.
     * 
     * @return {@code true} si fue eliminada; {@code false} si no existe
     */
    public boolean eliminarTarea(int id) {
        for (int posicion = 0; posicion < tareas.size(); posicion++) {
            Tarea tareaActual = tareas.get(posicion);

            if (tareaActual.getId() == id) {
                tareas.remove(posicion);
                return true;
            }
        }
        return false;
    }

    /**
     * Informa cuantas tareas existen.
     * @return cantidad de tareas guardadas.
     */
    public int cantidadTareas() {
        return tareas.size();
    }
}