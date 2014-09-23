package yachay.parametros.proyectos

class ObjetivoUnidad {

    String descripcion
    Integer orden

    static auditable = [ignore: []]
    static mapping = {
        table 'obun'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'obun__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'obun__id'
            descripcion column: 'obundscr'
            orden column: 'obunordn'
        }
    }

    static constraints = {
        descripcion(size: 3..511)
    }

}
