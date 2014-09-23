package yachay.parametros

class TipoContrato {

    String descripcion

    static auditable = [ignore: []]
    static mapping = {
        table 'tpcn'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'tpcn__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'tpcn__id'
            descripcion column: 'tpcndscr'
        }
    }

    static constraints = {
        descripcion(maxSize: 63)
    }
}
