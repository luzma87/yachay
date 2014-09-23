package yachay.parametros

class TipoAprobacion {

    String codigo
    String descripcion

    static auditable = [ignore: []]
    static mapping = {
        table 'tpap'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'tpap__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'tpap__id'
            codigo column: 'tpapcdgo'
            descripcion column: 'tpapdscr'
        }
    }

    static constraints = {
        codigo(maxSize: 2)
        descripcion(maxSize: 63)
    }
}
