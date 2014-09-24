package yachay.avales

import yachay.poa.Asignacion
import yachay.seguridad.Usro

/**
 * Clase para conectar con la tabla 'crtf' de la base de datos
 */
class Certificacion {

    /**
     * Usuario que cre&oacute; la certificaci&oacute;n
     */
    Usro usuario
    /**
     * Asignaci&oacute;n a la cual pertenece la certificaci&oacute;n
     */
    Asignacion asignacion
    /**
     * Fecha de creaci&oacute;n de la certificaci&oacute;n
     */
    Date fecha = new Date()
    /**
     * Fecha de revisi&oacute;n de la certificaci&oacute;n
     */
    Date fechaRevision
    /**
     * Fecha de anulaci&oacute;n de la certificaci&oacute;n
     */
    Date fechaAnulacion
    /**
     * Fecha de revisi&oacute;n de la anulaci&oacute;n de la certificaci&oacute;n
     */
    Date fechaRevisionAnulacion
    /**
     * Fecha de liberaci&oacute;n de la certificaci&oacute;n
     */
    Date fechaLiberacion
    /**
     * Concepto de la anulaci&oacute;n
     */
    String conceptoAnulacion
    /**
     * Path del archivo de solicitud de anulaci&oacute;n
     */
    String pathSolicitudAnulacion
    /**
     * Path del archivo de liberaci&oacute;n
     */
    String pathLiberacion
    /**
     * Path del archivo de anulaci&oacute;n
     */
    String pathAnulacion
    /**
     * N&uacute;mero del contrato
     */
    String numeroContrato
    /**
     * Monto de la liberaci&oacute;n
     */
    double montoLiberacion = 0
    /**
     * Monto de la certificaci&oacute;n
     */
    double monto
    /**
     * Concepto de la certificaci&oacute;n
     */
    String concepto
    /**
     * Observaciones de la certificaci&oacute;n
     */
    String observaciones
    /**
     * N&uacute;mero de memorando de solicitud
     */
    String memorandoSolicitud
    /**
     * N&uacute;mero de memorando del certificado
     */
    String memorandoCertificado
    /**
     * Path del archivo de la solicitud
     */
    String pathSolicitud
    /**
     * Acuerdo de la certificaci&oacute;n
     */
    String acuerdo
    /**
     * Archivo de la certificaci&oacute;n
     */
    String archivo
    /**
     * Estado de la certificaci&oacute;n:<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;0: solicitado
     * &nbsp;&nbsp;&nbsp;&nbsp;1: aprobado
     * &nbsp;&nbsp;&nbsp;&nbsp;2: negado
     * &nbsp;&nbsp;&nbsp;&nbsp;3: anulado
     * &nbsp;&nbsp;&nbsp;&nbsp;4: liberado
     */
    int estado = 0 /* 0 -> solicitado   1-> aprobadp  2-> negado 3-> anulado  4-> liberado */

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'crtf'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'crtf__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'crtf__id'
            usuario column: 'usro__id'
            asignacion column: 'asgn__id'
            fecha column: 'crtffcha'
            fechaRevision column: 'crtffcrv'
            monto column: 'crtfmnto'
            montoLiberacion column: 'crtfmnlb'
            concepto column: 'crtfcnct'
            observaciones column: 'crtfobsr'
            estado column: 'crtfetdo'
            memorandoSolicitud column: 'crtfmemo'
            memorandoCertificado column: 'crtfmecr'
            acuerdo column: 'crtfacue'
            archivo column: 'crtfarch'
            pathSolicitud column: 'crtfsolc'
            fechaAnulacion column: 'crtffcan'
            fechaLiberacion column: 'crtffclb'
            conceptoAnulacion column: 'crtfcpan'
            pathSolicitudAnulacion column: 'crtfphsa'
            pathLiberacion column: 'ctrfptlb'
            pathLiberacion column: 'ctrfptnl'
            numeroContrato column: 'crtfnmct'
            fechaRevisionAnulacion column: 'crtffcra'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        usuario(blank: false, nullable: false)
        asignacion(blank: false, nullable: false)
        fecha(blank: false, nullable: false)
        fechaRevision(blank: true, nullable: true)
        monto(blank: false, nullable: false)
        concepto(blank: false, nullable: false, size: 1..1024)
        observaciones(blank: true, nullable: true, size: 1..255)
        estado(blank: false, nullable: false)
        memorandoSolicitud(blank: false, nullable: false, size: 1..40)
        memorandoCertificado(blank: true, nullable: true, size: 1..40)
        acuerdo(blank: true, nullable: true, size: 1..40)
        archivo(blank: true, nullable: true, size: 1..500)
        pathSolicitud(blank: true, nullable: true, size: 1..500)
        pathAnulacion(blank: true, nullable: true, size: 1..500)
        fechaAnulacion(blank: true, nullable: true)
        fechaLiberacion(blank: true, nullable: true)
        conceptoAnulacion(blank: true, nullable: true, size: 1..1024)
        pathSolicitudAnulacion(blank: true, nullable: true, size: 1..500)
        pathLiberacion(blank: true, nullable: true, size: 1..500)
        numeroContrato(blank: true, nullable: true, size: 1..20)
        fechaRevisionAnulacion(blank: true, nullable: true)
    }

    /**
     * Genera un string para mostrar
     * @return el monto
     */
    String toString() {
        "${this.monto}"
    }
}
