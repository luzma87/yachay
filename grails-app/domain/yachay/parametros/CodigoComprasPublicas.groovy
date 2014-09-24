package yachay.parametros
/*Código del PAC para catalogar los bienes o servicios a adquirirse de acuerdo al (PAC).
Esta tabla se toma tal como lo define el INCOP. No se maneja parámetro de nivel sólo el id del padre.*/

/**
 * Clase para conectar con la tabla 'cpac' de la base de datos
 */
class CodigoComprasPublicas implements Serializable {
    /**
     * C&oacute;digo de compras p&uacute;blicas padre
     */
    CodigoComprasPublicas padre
    /**
     * N&uacute;mero del c&oacute;digo de compras p&uacute;blicas
     */
    String numero
    /**
     * Descripci&oacute;n del c&oacute;digo de compras p&uacute;blicas
     */
    String descripcion
    /**
     * Nivel del c&oacute;digo de compras p&uacute;blicas
     */
    String nivel
    /**
     * Movimiento
     */
    int movimiento = 0
    /**
     * Fecha
     */
    Date fecha

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'cpac'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'cpac__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'cpac__id'
            padre column: 'cpacpdre'
            numero column: 'cpacnmro'
            descripcion column: 'cpacdscr'
            nivel column: 'cpacnvel'
            fecha column: 'cpacfcha'
            movimiento column: 'cpacmvnt'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        padre(blank: true, nullable: true, attributes: [mensaje: 'Cuenta padre'])
        numero(size: 1..15, blank: true, nullable: true, attributes: [mensaje: 'Número de la cuenta'])
        descripcion(size: 1..255, blank: true, nullable: true, attributes: [mensaje: 'Descripción de la cuenta'])
        nivel(blank: true, nullable: true, attributes: [mensaje: 'Nivel'])
        fecha(blank: true, nullable: true, attributes: [mensaje: 'Fecha de creación'])
        movimiento(nullable: false, blank: true)
    }

    /**
     * Genera un string para mostrar
     * @return el n&uacute;mero
     */
    String toString() {
        return "${this.numero}"
    }
}