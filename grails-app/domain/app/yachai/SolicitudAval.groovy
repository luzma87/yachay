package app.yachai

import app.UnidadEjecutora
import app.seguridad.Usro

class SolicitudAval {

    ProcesoAval proceso
    UnidadEjecutora unidad
    Usro usuario
    Aval aval
    EstadoAval estado
    String path
    String concepto
    String contrato
    String memo
    Date fecha
    Date fechaRevision
    double monto = 0;
    String observaciones
    String numero
    String tipo /*A--> anulacion*/

    Usro firma1
    Usro firma2
    Usro firma3

    static mapping = {
        table 'slav'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'slav__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'slav__id'
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
            tipo column: 'slavtipo'
            firma1 column: 'usro_id1'
            firma2 column: 'usro_id2'
            firma3 column: 'usro_id3'
            unidad column: 'unej__id'
        }
    }

    static constraints = {
        concepto(blank: true, nullable: true, size: 1..500)
        proceso(blank: false, nullable: false)
        aval(blank: true, nullable: true)
        fechaRevision(blank: true, nullable: true)
        estado(blank: false, nullable: false)
        path(blank: true, nullable: true, size: 1..350)
        contrato(blank: true, nullable: true, size: 1..30)
        memo(blank: true, nullable: true, size: 1..30)
        observaciones(blank: true, nullable: true)
        numero(blank: true, nullable: true, size: 1..30)
        tipo(blank: true, nullable: true, size: 1..1)
        firma1(blank: true, nullable: true)
        firma2(blank: true, nullable: true)
        firma3(blank: true, nullable: true)
        unidad(nullable: true,blank:true)
    }
}
