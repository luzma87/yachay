package yachay.parametros

/* Tipo de responsabilidad en el proyecto*/

/**
 * Clase para conectar con la tabla 'tprp' de la base de datos<br/>
 * Guarda un cat&aacute;logo de posibles tipos de responsabilidad en el proyecto
 */
class TipoResponsable implements Serializable {
    /**
     * C&oacute;digo del tipo de responsable
     */
    String codigo
    /**
     * Descripci&oacute;n del tipo de responsable
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
        table 'tprp'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'tprp__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'tprp__id'
            codigo: 'tprpcdgo'
            descripcion column: 'tprpdscr'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        codigo(size: 1..1, blank: false, nullable: false, attributes: [mensaje: 'Código del tipo de responsable'])
        descripcion(size: 1..15, blank: false, attributes: [mensaje: 'Descripción del ripo de responsable'])
    }

    /**
     * Genera un string para mostrar
     * @return la descripci&oacute;n
     */
    String toString() {
        "${this.descripcion}"
    }
}