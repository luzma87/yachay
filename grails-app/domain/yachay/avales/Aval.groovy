package yachay.avales

class Aval {

    ProcesoAval proceso
    String concepto
    Date fechaAprobacion
    Date fechaLiberacion
    Date fechaAnulacion
    double monto
    double liberacion = 0
    EstadoAval estado
    String memo
    String path
    String pathLiberacion
    String pathAnulacion
    String contrato
//    int numero=0
    String numero
    String certificacion

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
        }
    }

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
    }
}
