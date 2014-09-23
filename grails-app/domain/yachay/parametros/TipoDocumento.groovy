package yachay.parametros

class TipoDocumento {

    String descripcion

    static auditable=[ignore:[]]
    static mapping = {
        table 'tpdc'
        cache usage:'read-write', include:'non-lazy'
        id column:'tpdc__id'
        id generator:'identity'
        version false
        columns {
            id column:'tpdc__id'
            descripcion column: 'tpdcdscr'
        }
    }
    static constraints = {
        descripcion(size:1..50,blank:false,attributes:[mensaje:'Descripción'])

    }

    String toString(){
        "${this.descripcion}"
    }
}
