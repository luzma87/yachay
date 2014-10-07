package yachay.contratacion

/**
 * Clase para conectar con la tabla 'aprb' de la base de datos<br/>
 * Reunión de aprobación: puede tener una o varias solicitudes ligadas
 */
class Aprobacion {

    /**
     * Fecha planificada para la reunion
     */
    Date fecha
    /**
     * Fecha de realizacion de la reunion
     */
    Date fechaRealizacion
    /**
     * Observaciones de la aprobación
     */
    String observaciones
    /**
     * Path del documento de aprobación
     */
    String pathPdf
    /**
     * Asistentes a la reunión de aprobación
     */
    String asistentes

    /**
     * Define las relaciones uno a varios
     */
    static hasMany = [solicitudes: Solicitud]

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
        fechaRealizacion(blank: true, nullable: true)
        observaciones(blank: true, nullable: true, maxSize: 1023)
        asistentes(blank: true, nullable: true, maxSize: 1023)
        pathPdf(blank: true, nullable: true, maxSize: 255)
    }
}
