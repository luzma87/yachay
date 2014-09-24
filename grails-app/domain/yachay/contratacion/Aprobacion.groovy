package yachay.contratacion

import yachay.parametros.TipoAprobacion
import yachay.contratacion.Solicitud

/**
 * Clase para conectar con la tabla 'aprb' de la base de datos
 */
class Aprobacion {

    /**
     * Solicitud que va a ser aprobada o negada
     */
    Solicitud solicitud
    /**
     * Tipo de aprobacion (aprobada, negada, ...)
     */
    TipoAprobacion tipoAprobacion
//    Fuente fuente
    /**
     * Fecha de aprobaci&oacute;n
     */
    Date fecha
    /**
     * Observaciones de la aprobaci&oacute;n
     */
    String observaciones
    /**
     * Path del documento de aprobaci&oacute;n
     */
    String pathPdf

    /**
     * Asistentes a la reuni&oacute;n de aprobaci&oacute;n
     */
    String asistentes

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'aprb'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'aprb__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'aprb__id'
            solicitud column: 'slct__id'
            tipoAprobacion column: 'tpap__id'
//            fuente column: 'fnte__id'
            fecha column: 'aprbfcha'
            observaciones column: 'aprbobsr'
            pathPdf column: 'aprb_pdf'
            asistentes column: 'aprbasst'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        observaciones(blank: true, nullable: true, maxSize: 1023)
        asistentes(blank: true, nullable: true, maxSize: 1023)
        pathPdf(blank: true, nullable: true, maxSize: 255)
    }
}
