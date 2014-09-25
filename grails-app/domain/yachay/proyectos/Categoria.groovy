package yachay.proyectos

/**
 * Clase para conectar con la tabla 'ctgr' de la base de datos
 */
class Categoria {
    /**
     * Descripci&oacute;n de la categor&iacute;a
     */
    String descripcion
    /**
     * C&oacute;digo de la categor&iacute;a
     */
    String codigo

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'ctgr'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'ctgr__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'ctgr__id'
            descripcion column: 'ctgrdscr'
            codigo column: 'ctgrcdgo'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        descripcion(size: 1..50, blank: false, nullable: false)
        codigo(size: 1..10, blank: false, nullable: false)
    }
}
