package yachay.parametros

/**
 * Clase para conectar con la tabla 'tppc' de la base de datos
 */
class TipoProcedencia implements Serializable {
    /**
     * Descripci&oacute;n del tipo de procedencia
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
        table 'tppc'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'tppc__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'tppc__id'
            descripcion column: 'tppcdscr'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        descripcion(size: 1..31, blank: false, attributes: [mensaje: 'Descripción del tipo de procedencia'])
    }

    /**
     * Genera un string para mostrar
     * @return la descripci&oacute;n
     */
    String toString() {
        "${this.descripcion}"
    }
}