package yachay.parametros

/**
 * Clase para conectar con la tabla 'tpsp' de la base de datos
 */
class TipoSupuesto implements Serializable {
    /**
     * Descripci&oacute;n del tipo de supuesto
     */
    String descripcion

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'tpsp'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'tpsp__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'tpsp__id'
            descripcion column: 'tpspdscr'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        descripcion(size: 1..255, blank: true, nullable: true, attributes: [mensaje: 'Descripción del tipo de supuesto'])
    }

    /**
     * Genera un string para mostrar
     * @return la descripci&oacute;n
     */
    String toString() {
        "${this.descripcion}"
    }
}