package yachay.proyectos

/**
 * Clase para conectar con la tabla 'plbv' de la base de datos
 */
class PoliticaBuenVivir implements Serializable {
    /**
     * Objetivo del buen vivir de la pol&iacute;tica
     */
    ObjetivoBuenVivir objetivo
    /**
     * C&oacute;digo de la pol&iacute;tica del buen vivir
     */
    Integer codigo
    /**
     * Descripci&oacute;n de la pol&iacute;tica del buen vivir
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
        table 'plbv'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'plbv__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'plbv__id'
            objetivo column: 'obbv__id'
            codigo column: 'plbvcdgo'
            descripcion column: 'plbvdscr'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        objetivo(blank: false, nullable: false, attributes: [mensaje: 'Objetivo cel buen vivir'])
        codigo(blank: false, nullable: false, attributes: [mensaje: 'Código de la política'])
        descripcion(size: 1..127, blank: true, nullable: true, attributes: [mensaje: 'Descripción de la política'])
    }

    /**
     * Genera un string para mostrar
     * @return el c&oacute;digo del objetivo del buen vivir, el c&oacute;digo y la descripci&oacute;n concatenados
     */
    String toString() {
        return this.objetivo.codigo + "." + this.codigo + " - " + this.descripcion
    }
}