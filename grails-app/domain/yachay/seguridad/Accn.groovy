package yachay.seguridad

/**
 * Clase para conectar con la tabla 'accn' de la base de datos
 */
class Accn {
    /**
     * Nombre de la acci&oacute;n
     */
    String accnNombre
    /**
     * Descripci&oacute;n de la acci&oacute;n
     */
    String accnDescripcion
    /**
     * Indica si la acci&oacute;n es o no auditable
     */
    int accnAuditable
    /**
     * Controlador al cual pertenece la acci&oacute;n
     */
    Ctrl control
    /**
     * M&oacute;dulo al cual pertenece la acci&oacute;n
     */
    Modulo modulo
    /**
     * Tipo de acci&oacute;n
     */
    Tpac tipo

    /**
     * Define las relaciones uno a varios
     */
    static hasMany = [permisos: Prms]
    static searchable = true

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'accn'
        cache usage: 'read-write', include: 'non-lazy'
        version false
        id generator: 'identity'
        control sort: ['ctrlNombre': 'asc']
        columns {
            id column: 'accn__id'
            accnNombre column: 'accnnmbr'
            accnDescripcion column: 'accndscr'
            accnAuditable column: 'accnaudt'
            control column: 'ctrl__id'
            modulo column: 'mdlo__id'
            tipo column: 'tpac__id'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        accnNombre(blank: false, size: 0..50)
        accnAuditable(blank: true, nullable: true)
    }

    /**
     * Genera un string para mostrar
     * @return el nombre de controlador y el nombre de la acci&oacute;n concatenados
     */
    String toString() {
        "${this.control.ctrlNombre} : ${this.accnNombre} "
    }
}
