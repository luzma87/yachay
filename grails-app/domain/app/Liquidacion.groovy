package app

class Liquidacion {

    Obra obra
    double valor
    Date fechaRegistro  = new Date()
    Date fechaAdjudicacion
    Date fechaInicio
    Date fechaFin

    static auditable=[ignore:[]]
    static mapping = {
        table 'lqdc'
        cache usage:'read-write', include:'non-lazy'
        id column:'lqdc__id'
        id generator:'identity'
        version false
        columns {
            id column:'lqdc__id'
            obra column: 'obra__id'
            valor column: 'lqdcvlor'
            fechaRegistro column:  'lqdcfcrg'
            fechaAdjudicacion column: 'lqdcfcad'
            fechaInicio column: 'lqdcfcin'
            fechaFin column: 'lqdcfcfn'
        }
    }
    static constraints = {
        obra(blank:false,nullable:false)
        valor(blank:false,nullable:false)
        fechaRegistro(blank:true,nullable:true)
        fechaAdjudicacion(blank:false,nullable:false)
        fechaInicio (blank:true,nullable:true)
        fechaFin (blank:true,nullable:true)

    }

    String toString(){
        "${this.obra} valor: ${this.valor}"
    }
}
