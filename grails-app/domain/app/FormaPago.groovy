package app

class FormaPago {

    String descripcion

    static auditable = [ignore: []]
    static mapping = {
        table 'frpg'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'frpg__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'frpg__id'
            descripcion column: 'frpgdscr'
        }
    }

    static constraints = {
        descripcion(maxSize: 63)
    }
}
