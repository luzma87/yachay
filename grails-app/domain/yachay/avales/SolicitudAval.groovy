package yachay.avales

import yachay.parametros.UnidadEjecutora
import yachay.seguridad.Usro

/**
 * Clase para conectar con la tabla 'slav' de la base de datos
 */
class SolicitudAval {
    /**
     * Proceso de la solicitud de aval
     */
    ProcesoAval proceso
    /**
     * Unidad ejecutora que solicita el aval
     */
    UnidadEjecutora unidad
    /**
     * Usuario que gener&oacute; la solicitud
     */
    Usro usuario
    /**
     * Aval de la solicitud
     */
    Aval aval
    /**
     * Estado de la solicitud
     */
    EstadoAval estado
    /**
     * Path del documento de solicitud
     */
    String path
    /**
     * Concepto de la solicitud
     */
    String concepto
    /**
     * Contrato de solicitud de aval
     */
    String contrato
    /**
     * N&uacute;mero de memo de solicitud de aval
     */
    String memo
    /**
     * Fecha de solicitud
     */
    Date fecha
    /**
     * Fecha de revisi&oacute;n de la solicitud
     */
    Date fechaRevision
    /**
     * Monto de la solicitud
     */
    double monto = 0;
    /**
     * Observaciones de la solicitud
     */
    String observaciones
    /**
     * N&uacute;mero de la solicitud
     */
    String numero
    /**
     * Tipo de solicitud: <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp; : aprobaci&oacute;n
     * &nbsp;&nbsp;&nbsp;&nbsp;A: anulaci&oacute;n
     */
    String tipo /*A--> anulacion*/

    /**
     * Usuario que firma la solicitud
     */
    Usro firma1
    /**
     * Usuario que firma la solicitud
     */
    Usro firma2
    /**
     * Usuario que firma la solicitud
     */
    Usro firma3

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
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

    /**
     * Define las restricciones de cada uno de los campos
     */
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
        unidad(nullable: true, blank: true)
    }
}
