package yachay.avales

import yachay.seguridad.Firma

/**
 * Clase para conectar con la tabla 'aval' de la base de datos
 */
class Aval {

    /**
     * Proceso del aval
     */
    ProcesoAval proceso
    /**
     * Concepto del aval
     */
    String concepto
    /**
     * Fecha de aprobación del aval
     */
    Date fechaAprobacion
    /**
     * Fecha de liberación del aval
     */
    Date fechaLiberacion
    /**
     * Fecha de anulación del aval
     */
    Date fechaAnulacion
    /**
     * Monto del aval
     */
    double monto
    /**
     * Monto de liberación del aval
     */
    double liberacion = 0
    /**
     * Estado del aval
     */
    EstadoAval estado
    /**
     * Número de memo de la petición del aval
     */
    String memo
    /**
     * Path del documento del aval
     */
    String path
    /**
     * Path del documento de respaldo de liberación del aval
     */
    String pathLiberacion
    /**
     * Path del documento de respaldo de anulación del aval
     */
    String pathAnulacion
    /**
     * Número de contrato para la liberación
     */
    String contrato
//    int numero=0
    /**
     * Número del aval
     */
    String numero
    /**
     * Número de la certificación para el aval
     */
    String certificacion
    /**
     * Firma del director
     */
    Firma firma1
    /**
     * Firma del gerente
     */
    Firma firma2

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'aval'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'aval__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'aval__id'
            proceso column: 'prco__id'
            fechaAprobacion column: 'avalfcap'
            fechaLiberacion column: 'avalfclb'
            fechaAnulacion column: 'avalfcan'
            monto column: 'avalmnto'
            liberacion column: 'avallbrc'
            estado column: 'edav__id'
            memo column: 'avalmemo'
            path column: 'avalpath'
            pathLiberacion column: 'avalphlb'
            pathLiberacion column: 'avalphan'
            contrato column: 'avalcntr'
            numero column: 'avalnmro'
            concepto column: 'avalcpto'
            certificacion column: 'avalcert'
            firma1 column: 'frmadire'
            firma2 column: 'frmagert'
        }
    }
    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        proceso(blank: false, nullable: false)
        fechaAprobacion(blank: true, nullable: true)
        fechaLiberacion(blank: true, nullable: true)
        fechaAnulacion(blank: true, nullable: true)
        estado(blank: false, nullable: false)
        memo(blank: true, nullable: true, size: 1..30)
        path(blank: true, nullable: true, size: 1..350)
        pathLiberacion(blank: true, nullable: true, size: 1..350)
        pathAnulacion(blank: true, nullable: true, size: 1..350)
        contrato(blank: true, nullable: true, size: 1..30)
        numero(blank: true, nullable: true, size: 1..10)
        concepto(blank: true, nullable: true, size: 1..500)
        certificacion(blank: true, nullable: true, size: 1..50)
        firma1(blank: true, nullable: true)
        firma2(blank: true, nullable: true)
    }
}
