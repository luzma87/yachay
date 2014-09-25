package yachay.parametros

/**
 * Clase para conectar con la tabla 'sbsc' de la base de datos
 */
class SubSecretaria implements Serializable {
    /**
     * Entidad de la subsecretar&iacute;a
     */
    Entidad entidad
    /**
     * Nombre de la subsecretar&iacute;a
     */
    String nombre
    /**
     * T&iacute;tulo de la subsecretar&iacute;a
     */
    String titulo

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'sbsc'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'sbsc__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'sbsc__id'
            entidad column: 'entd__id'
            nombre column: 'sbscnmbr'
            titulo column: 'sbsctitl'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        entidad(blank: true, nullable: true, attributes: [mensaje: 'Entidad'])
        nombre(size: 1..63, blank: false, attributes: [mensaje: 'Nombre de la subsecretaría'])
        titulo(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'Título formal'])
    }

    /**
     * Genera un string para mostrar
     * @return el nombre
     */
    String toString() {
        return this.nombre
    }
}