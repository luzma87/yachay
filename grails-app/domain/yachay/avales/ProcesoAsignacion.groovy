package yachay.avales

import yachay.poa.Asignacion
import yachay.avales.ProcesoAval

/**
 * Clase para conectar con la tabla 'poas' de la base de datos
 */
class ProcesoAsignacion {
    /**
     * Proceso
     */
    ProcesoAval proceso
    /**
     * Asignaci&oacute;n
     */
    Asignacion asignacion
    /**
     * Monto
     */
    double monto

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'poas'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'poas__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'poas__id'
            proceso column: 'prco__id'
            asignacion column: 'asgn__id'
            monto column: 'poasmnto'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {

    }
}
