package app.yachai

class Aval {

    ProcesoAval proceso
    String concepto
    Date fechaAprobacion
    Date fechaLiberacion
    Date fechaAnulacion
    double monto
    double liberacion=0
    EstadoAval estado
    String memo
    String path
    String pathLiberacion
    String contrato
    String numero

    static mapping = {
        table 'aval'
        cache usage:'read-write', include:'non-lazy'
        id column:'aval__id'
        id generator:'identity'
        version false
        columns {
            id column:'aval__id'
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
            contrato column: 'avalcntr'
            numero column: 'avalnmro'
            concepto column: 'avalcpto'
        }
    }

    static constraints = {
        proceso(black:false,nullable: false)
        fechaAprobacion (black:true,nullable: true)
        fechaLiberacion (black:true,nullable: true)
        fechaAnulacion   (black:true,nullable: true)
        estado (black:false,nullable: false)
        memo(black:true,nullable: true,size: 1..30)
        path (black:true,nullable: true,size: 1..350)
        pathLiberacion (black:true,nullable: true,size: 1..350)
        contrato (black:true,nullable: true,size: 1..30)
        numero (black:true,nullable: true,size: 1..10)
        concepto (black:true,nullable: true,size: 1..500)
    }
}
