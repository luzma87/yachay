package yachay.parametros.poaPac
/*Año al cual corresponde el PAPP, cada año debe iniciarse una nueva gestión de proyectos. Es similar al período contable o año fiscal.*/
/**
 * Clase para conectar con la tabla 'anio' de la base de datos<br/>
 * A&ntilde;o al cual corresponde el PAPP, cada a&ntildeo debe iniciarse una nueva
 * gesti&oacute;n de proyectos. Es similar al periodo contable o a&ntilde;o fiscal
 */
class Anio implements Serializable {
    /**
     * N&uacute;mero de a&ntilde;o
     */
    String anio
    /**
     * Estado del a&ntilde;o: 0: no aprobado, 1: aprobado
     */
    int estado = 0 /* 0 -> no aprobado    1-> aprobadp */

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'anio'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'anio__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'anio__id'
            anio column: 'anioanio'
            estado column: 'anioetdo'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        anio(size: 1..31, blank: false, attributes: [mensaje: 'Año al cual corresponde el PAPP'])
        estado(blank: false, nullable: false)
    }

    /**
     * Genera un string para mostrar
     * @return el n&uacute;mero del a&ntilde;o
     */
    String toString() {
        "${this.anio}"
    }
}