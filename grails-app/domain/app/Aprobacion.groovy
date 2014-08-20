package app

class Aprobacion {

    Solicitud solicitud
    TipoAprobacion tipoAprobacion
    Fuente fuente
    Date fecha
    String observaciones
    String pathPdf

    static auditable = [ignore: []]
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
            fuente column: 'fnte__id'
            fecha column: 'aprbfcha'
            observaciones column: 'aprbobsr'
            pathPdf column: 'aprb_pdf'
        }
    }

    static constraints = {
        observaciones(blank: true, nullable: true, maxSize: 1023)
        pathPdf(blank: true, nullable: true, maxSize: 255)
    }
}
