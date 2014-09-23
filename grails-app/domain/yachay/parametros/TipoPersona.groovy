package yachay.parametros

class TipoPersona {

    String descripcion

    static auditable=[ignore:[]]

    static mapping = {

        table 'tpps'

        cache usage:'read-write', include:'non-lazy'
        id column:'tpps__id'
        id generator:'identity'
        version false
        columns {
            id column:'tpps__id'
            descripcion column: 'tppsdesc'
        }

    }
    static constraints = {


        descripcion(blank: true, nullable: true, attributes: [mensaje: 'Descripcion'])
    }

    String toString() {
        return this.descripcion
    }
}
