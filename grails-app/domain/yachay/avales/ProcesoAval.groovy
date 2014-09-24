package yachay.avales

import yachay.proyectos.Proyecto

/**
 * Clase para conectar con la tabla 'alertas' de la base de datos
 */
class ProcesoAval {
    /**
     * Proyecto del proceso
     */
    Proyecto proyecto
    /**
     * Nombre del proceso
     */
    String nombre
    /**
     * Fecha de inicio del proceso
     */
    Date fechaInicio
    /**
     * Fecha fin del proceso
     */
    Date fechaFin

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'prco'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'prco__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'prco__id'
            proyecto column: 'proy__id'
            nombre column: 'prconmbr'
            fechaInicio column: 'prcofcin'
            fechaFin column: 'prcofcfn'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        nombre(size: 1..255, blank: false, nullable: false)
    }

    /**
     * Calcula el monto total del proceso sumando todas las asignaciones
     * @return el monto calculado
     */
    def getMonto() {
        def detalles = ProcesoAsignacion.findAllByProceso(this)
        def monto = 0
        detalles.each {
            monto += it.monto
        }
        return monto
    }
}
