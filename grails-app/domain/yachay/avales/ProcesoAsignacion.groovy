package yachay.avales

import yachay.poa.Asignacion
import yachay.avales.ProcesoAval

class ProcesoAsignacion {
    ProcesoAval proceso
    Asignacion asignacion
    double monto
    static mapping = {
        table 'poas'
        cache usage:'read-write', include:'non-lazy'
        id column:'poas__id'
        id generator:'identity'
        version false
        columns {
            id column:'poas__id'
            proceso column: 'prco__id'
            asignacion column: 'asgn__id'
            monto column: 'poasmnto'
        }
    }

    static constraints = {

    }
}
