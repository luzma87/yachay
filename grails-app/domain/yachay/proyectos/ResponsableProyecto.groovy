package yachay.proyectos

import yachay.parametros.UnidadEjecutora
import yachay.parametros.TipoResponsable

/* Responsable nombrado en la Unidad ejecutora para la ejecución de los proyectos.*/
/**
 * Clase para conectar con la tabla '' de la base de datos
 */
class ResponsableProyecto implements Serializable {
    yachay.seguridad.Usro responsable
    UnidadEjecutora unidad
    Proyecto proyecto
    Date desde
    Date hasta
    TipoResponsable tipo
    String observaciones
    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]
    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'rspy'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'rspy__id'
        id generator: 'identity'
        version false
        columns {
            responsable column: 'usro__id'
            proyecto column: 'proy__id'
            desde column: 'rspydsde'
            hasta column: 'rspyhsta'
            tipo column: 'tprp__id'
            observaciones column: 'rspyobsr'
            unidad column: 'unej__id'
        }
    }
    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        responsable(blank: true, nullable: true, attributes: [mensaje: 'Responsable'])
        proyecto(blank: true, nullable: true, attributes: [mensaje: 'Proyecto'])
        desde(blank: true, nullable: true, attributes: [mensaje: 'Responsable desde (fecha)'])
        hasta(blank: true, nullable: true, attributes: [mensaje: 'Responsable hasta (fecha)'])
        tipo(blank: false, nullable: false, attributes: [mensaje: 'Tipo de responsable'])
        observaciones(size: 1..127, blank: true, nullable: true, attributes: [mensaje: 'Observaciones'])
        unidad(blank:true,nullable: true)
    }

    /**
     * Genera un string para mostrar
        * @return
     */
    String toString() {
        return this.responsable.persona.nombre + " " + this.responsable.persona.apellido
    }
}