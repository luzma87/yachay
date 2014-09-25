package yachay.proyectos

/**
 * Clase para conectar con la tabla '' de la base de datos
 */
class MetaBuenVivir implements Serializable {
    PoliticaBuenVivir politica
    Integer codigo
    String descripcion
    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]
    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'mtbv'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'mtbv__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'mtbv__id'
            politica column: 'plbv__id'
            codigo column: 'mtbvcdgo'
            descripcion column: 'mtbvdscr'
        }
    }
    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        politica(blank: false, nullable: false, attributes: [mensaje: 'Meta del buen vivir'])
        codigo(blank: false, nullable: false, attributes: [mensaje: 'Código de la meta'])
        descripcion(size: 1..127, blank: true, nullable: true, attributes: [mensaje: 'Descripción de la meta'])
    }

    /**
     * Genera un string para mostrar
        * @return
     */
    String toString() {
        return this.politica.objetivo.codigo + "." + this.politica.codigo + "." + this.codigo + " - " + this.descripcion
    }
}
