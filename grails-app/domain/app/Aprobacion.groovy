package app

/*
 aprb__id | integer                 | not null default nextval('aprb_aprb__id_seq'::regclass)
 slct__id | integer                 |
 tpap__id | integer                 |
 fnte__id | integer                 |
 aprbfcha | date                    |
 aprbobsr | character varying(1023) |
 aprb_pdf | character varying(255)  |

 */

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
