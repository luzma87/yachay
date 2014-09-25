package yachay.parametros

/**
 * Clase para conectar con la tabla 'tppr' de la base de datos
 */
class TipoProceso {
    /**
     * Descripci&oacute;n del tipo de proceso
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
        table 'tppr'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'tppr__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'tppr__id'
            descripcion column: 'tpprdscr'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        descripcion(size: 1..50, blank: false, attributes: [mensaje: 'Descripción'])
    }

    /**
     * Genera un string para mostrar
     * @return la descripci&oacute;n
     */
    String toString() {
        "${this.descripcion}"
    }
}
