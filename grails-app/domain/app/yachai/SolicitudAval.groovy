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
    String observaciones
    String numero

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
            observaciones column: 'slavobs'
            observaciones type: 'text'
            numero column: 'slavnmro'
        }
    }

    static constraints = {
        concepto (blank:true,nullable: true,size: 1..500)
        proceso(blank:false,nullable: false)
        aval(blank:true,nullable: true)
        fechaRevision (blank:true,nullable: true)
        estado (blank:false,nullable: false)
        path (blank:true,nullable: true,size: 1..350)
        contrato (blank:true,nullable: true,size: 1..30)
        memo (blank:true,nullable: true,size: 1..30)
        observaciones(blank:true,nullable: true)
        numero (blank:true,nullable: true,size: 1..30)
    }
}
