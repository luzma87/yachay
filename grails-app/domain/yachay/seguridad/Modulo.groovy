package yachay.seguridad

/**
 * Clase para conectar con la tabla 'mdlo' de la base de datos
 */
class Modulo implements Serializable {
    /**
     * Nombre del m&oacute;dulo
     */
    String nombre
    /**
     * Descripci&oacute;n del m&oacute;dulo
     */
    String descripcion
    /**
     * Orden del m&oacute;dulo
     */
    int orden

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'mdlo'
        cache usage: 'read-write', include: 'non-lazy'
        version false
        id generator: 'identity'
        columns {
            id column: 'mdlo__id'
            nombre column: 'mdlonmbr'
            descripcion column: 'mdlodscr'
            orden column: 'mdloordn'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {

    }

    /**
     * Genera un string para mostrar
     * @return el nombre
     */
    String toString() {
        "${this.nombre}"
    }
}
