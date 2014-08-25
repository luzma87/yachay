package app.yachai

import app.Proyecto

class ProcesoAval {
    Proyecto proyecto
    String nombre
    Date fechaInicio
    Date fechaFin
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

    static constraints = {
        nombre(size: 1..255, blank: false, nullable: false)
    }

    def getMonto() {
        def detalles = ProcesoAsignacion.findAllByProceso(this)
        def monto = 0
        detalles.each {
            monto += it.monto
        }
        return monto
    }
}
