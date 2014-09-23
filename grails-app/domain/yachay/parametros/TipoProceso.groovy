package yachay.parametros

class TipoProceso {

    String descripcion

    static auditable=[ignore:[]]
    static mapping = {
        table 'tppr'
        cache usage:'read-write', include:'non-lazy'
        id column:'tppr__id'
        id generator:'identity'
        version false
        columns {
            id column:'tppr__id'
            descripcion column: 'tpprdscr'
        }
    }
    static constraints = {
        descripcion(size:1..50,blank:false,attributes:[mensaje:'Descripción'])

    }

    String toString(){
        "${this.descripcion}"
    }
}
