package app.yachai

class EstadoAval {
    String descripcion
    String codigo
    static mapping = {
        table 'edav'
        cache usage:'read-write', include:'non-lazy'
        id column:'edav__id'
        id generator:'identity'
        version false
        columns {
            id column:'edav__id'
            descripcion column: 'edavdscr'
            codigo column: 'edavcdgo'
        }
    }

    static constraints = {
        descripcion(size: 1..50,blank: false,nullable: false)
        codigo(size: 1..3,blank: false,nullable: false)
    }
}
