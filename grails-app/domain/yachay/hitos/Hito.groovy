package yachay.hitos

import app.Cuantificable

class Hito {

    String descripcion
    Date fecha
    Date fechaCumplimiento
/**
 * Define el mapeo entre los campos del dominio y las columnas de la base de datos
 */
    static mapping = {
        table 'hito'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'hito__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'cmht__id'
            descripcion column: 'hitodscr'
            fecha column: 'hitofcha'
            fechaCumplimiento column: 'hitofccm'
        }
    }
/**
 * Define las restricciones de cada uno de los campos
 */
    static constraints = {

        fecha(blank: false, nullable: false)
        fechaCumplimiento(blank: true, nullable: true)
        descripcion(blank: false, nullable: false)
    }
}
