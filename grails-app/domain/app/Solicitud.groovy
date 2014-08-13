package app

class Solicitud {

    UnidadEjecutora unidadEjecutora
    MarcoLogico actividad
    Date fecha
    String observaciones
    Date revisadoAdministrativaFinanciera
    Date revisadoJuridica
    Date revisadoDireccionProyectos
    String observacionesAdministrativaFinanciera
    String observacionesJuridica
    String observacionesDireccionProyectos
    String pathPdfTdr

    Double montoSolicitado
    TipoContrato tipoContrato
    String nombreProceso
    String objetoContrato
    FormaPago formaPago
    Integer plazoEjecucion

    static auditable = [ignore: []]
    static mapping = {
        table 'slct'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'slct__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'slct__id'
            unidadEjecutora column: 'unej__id'
            actividad column: 'mrlg__id'
            fecha column: 'slctfcha'
            observaciones column: 'slctobsr'
            revisadoAdministrativaFinanciera column: 'slctrvaf'
            revisadoJuridica column: 'slctrvgj'
            revisadoDireccionProyectos column: 'slctrvdp'
            observacionesAdministrativaFinanciera column: 'slctobaf'
            observacionesJuridica column: 'slctobgj'
            observacionesDireccionProyectos column: 'slctobdp'
            pathPdfTdr column: 'slct_tdr'

            montoSolicitado column: 'slctmnsl'
            tipoContrato column: 'tpcn__id'
            nombreProceso column: 'slctnmpr'
            objetoContrato column: 'slctobcn'
            objetoContrato type: 'text'
            formaPago column: 'frpg__id'
            plazoEjecucion column: 'slctplej'
        }
    }

    static constraints = {
        observaciones(blank: true, nullable: true, maxSize: 1023)
        revisadoAdministrativaFinanciera(blank: true, nullable: true)
        revisadoJuridica(blank: true, nullable: true)
        revisadoDireccionProyectos(blank: true, nullable: true)
        observacionesAdministrativaFinanciera(blank: true, nullable: true, maxSize: 1023)
        observacionesJuridica(blank: true, nullable: true, maxSize: 1023)
        observacionesDireccionProyectos(blank: true, nullable: true, maxSize: 1023)
        pathPdfTdr(blank: true, nullable: true, maxSize: 255)
    }
}
