package yachay.proyectos

/**
 * Clase para conectar con la tabla 'bfsp' de la base de datos
 */
class BeneficioSenplades implements Serializable {
    /**
     * Proyecto del beneficio Semplades
     */
    Proyecto proyecto
    /**
     * N&uacute;mero de beneficiarios directos hombres del beneficio
     */
    int beneficiariosDirectosHombres
    /**
     * N&uacute;mero de beneficiarios directos mujeres del beneficio
     */
    int beneficiariosDirectosMujeres
    /**
     * N&uacute;mero de beneficiarios indirectos hombres del beneficio
     */
    int beneficiariosIndirectosHombres
    /**
     * N&uacute;mero de beneficiarios indirectos mujeres del beneficio
     */
    int beneficiariosIndirectosMujeres

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'bfsp'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'bfsp__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'bfsp__id'
            proyecto column: 'proy__id'
            beneficiariosDirectosHombres column: 'bfspdrhh'
            beneficiariosDirectosMujeres column: 'bfspdrmj'
            beneficiariosIndirectosHombres column: 'bfspidhh'
            beneficiariosIndirectosMujeres column: 'bfspidmj'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        proyecto(blank: true, nullable: true, attributes: [mensaje: 'Proyecto'])
        beneficiariosDirectosHombres(blank: true, nullable: true, attributes: [mensaje: 'Beneficiarios Directos Hombres'])
        beneficiariosDirectosMujeres(blank: true, nullable: true, attributes: [mensaje: 'Beneficiarios Directos Mujeres'])
        beneficiariosIndirectosHombres(blank: true, nullable: true, attributes: [mensaje: 'Beneficiarios Indirectos Hombres'])
        beneficiariosIndirectosMujeres(blank: true, nullable: true, attributes: [mensaje: 'Beneficiarios Indirectos Mujeres'])
    }
}