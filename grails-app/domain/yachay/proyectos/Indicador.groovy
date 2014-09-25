package yachay.proyectos

import yachay.poa.Asignacion
import yachay.proyectos.MarcoLogico

/*Para cada elemento del marco lógico se define uno o varios indicadores*/
/**
 * Clase para conectar con la tabla '' de la base de datos<br/>
 * Para cada elemento del marco l&oacute;gico se define uno o varios indicadores
 */
class Indicador implements Serializable {
    /**
     * Marco l&oacute;gico del indicador
     */
    MarcoLogico marcoLogico
    /**
     * Asignaci&oacute;n del indicador
     */
    Asignacion asignacion
    /**
     * Descripci&oacute;n del indicador
     */
    String descripcion
    /**
     * Cantidad del indicador
     */
    double cantidad = 0
    /**
     * Modificaci&oacute;n del indicador
     */
    ModificacionProyecto modificacion
    /**
     * Estado del indicador: 0: activo, 1: modificado
     */
    int estado = 0 /* 0 -> activo por facilidad en la base de datos  1-> modificado*/

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'indi'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'indi__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'indi__id'
            marcoLogico column: 'mrlg__id'
            descripcion column: 'indidscr'
            cantidad column: 'indicntd'
            modificacion column: 'mdfc__id'
            estado column: 'indietdo'
            asignacion column: "asgn__id"
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        marcoLogico(blank: true, nullable: true, attributes: [mensaje: 'Elemento del marco lógico al que se aplica el indicador'])
        descripcion(size: 1..1023, blank: true, nullable: true, attributes: [mensaje: 'Descripción del indicador'])
        cantidad(blank: true, nullable: true, attributes: [mensaje: 'Cantidad para indicadores cuantitativos'])
        modificacion(blank: true, nullable: true)
        estado(nullable: false, blank: false)
        asignacion(nullable: true, blank: true)
    }

    /**
     * Genera un string para mostrar
     * @return la descripci&oacute;n
     */
    String toString() {
        "${this.descripcion}"
    }

}