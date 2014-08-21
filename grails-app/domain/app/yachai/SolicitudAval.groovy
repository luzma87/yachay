package app.yachai

import app.seguridad.Usro

class SolicitudAval {

    ProcesoAval proceso
    Usro usuario
    Aval aval
    EstadoAval estado
    String path
    String concepto
    String contrato
    String memo
    Date fecha
    Date fechaRevision
    double monto=0;

    static mapping = {
        table 'slav'
        cache usage:'read-write', include:'non-lazy'
        id column:'slav__id'
        id generator:'identity'
        version false
        columns {
            id column:'slav__id'
            proceso column: 'prco__id'
            aval column: 'aval__id'
            fechaRevision column: 'slavfcrv'
            fecha column: 'slavfcha'
            monto column: 'slavmnto'
            path column: 'slavpath'
            contrato column: 'slavcntr'
            memo column: 'slavmemo'
            concepto column: 'slavcpto'
            estado column: 'edav__id'
            usuario column: 'usro__id'
        }
    }

    static constraints = {
        concepto (black:true,nullable: true,size: 1..500)
        proceso(black:false,nullable: false)
        aval(black:true,nullable: true)
        fechaRevision (black:true,nullable: true)
        estado (black:false,nullable: false)
        path (black:true,nullable: true,size: 1..350)
        contrato (black:true,nullable: true,size: 1..30)
        memo (black:true,nullable: true,size: 1..30)


    }
}
