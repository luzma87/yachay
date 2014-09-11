package app.yachai

import app.Anio
import app.Solicitud

class DetalleMontoSolicitud {

    Solicitud solicitud
    Anio anio
    double monto

    static auditable = [ignore: []]
    static mapping = {
        table 'dtms'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'dtms__id'
        id generator: 'identity'
        version false
        columns {
            solicitud column: 'slct__id'
            anio column: 'anio__id'
            monto column: 'dtmsmnto'
        }
    }

    static constraints = {
    }
}
