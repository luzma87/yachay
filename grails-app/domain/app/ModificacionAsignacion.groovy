package app
/*La modificación de la asignación presupuestaria se refiere a la redistribución del valor asignado lo cual implica además una reprogramación.*/
class ModificacionAsignacion implements Serializable {
    Asignacion desde
    Asignacion recibe
    ModificacionProyecto modificacionProyecto
    Date fecha
    double valor
    String pdf
    UnidadEjecutora unidad
    static auditable=[ignore:[]]
    static mapping = {
        table 'mdas'
        cache usage:'read-write', include:'non-lazy'
        id column:'mdas__id'
        id generator:'identity'
        version false
        columns {
            id column:'mdas__id'
            desde column: 'asgndsde'
            recibe column: 'asgnrcbe'
            modificacionProyecto column: 'mdfc__id'
            fecha column: 'mdasfcha'
            valor column: 'mdasvlor'
            unidad column: 'unej__id'
        }
    }
    static constraints = {
        desde( blank:true, nullable:true ,attributes:[mensaje:'Asignación desde donde sale dinero'])
        recibe( blank:true, nullable:true ,attributes:[mensaje:'Asignación que recibe el dinero'])
        modificacionProyecto( blank:true, nullable:true ,attributes:[mensaje:'Modificación'])
        fecha( blank:true, nullable:true ,attributes:[mensaje:'Fecha'])
        valor( blank:true, nullable:true ,attributes:[mensaje:'Valor redistribuido, siempre en positivo'])
        pdf(blank: true,nullable: true,size: 1..250)
        unidad(blank: true,nullable: true)
    }
}