package yachay.proyectos

class Categoria {

    String descripcion
    String codigo
    static mapping = {
        table 'ctgr'
        cache usage:'read-write', include:'non-lazy'
        id column:'ctgr__id'
        id generator:'identity'
        version false
        columns {
            id column:'ctgr__id'
            descripcion column: 'ctgrdscr'
            codigo column: 'ctgrcdgo'
        }
    }

    static constraints = {
        descripcion(size: 1..50,blank: false,nullable: false)
        codigo(size: 1..10,blank: false,nullable: false)
    }
}
