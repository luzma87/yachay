package app

class Certificacion {

    app.seguridad.Usro usuario
    Asignacion asignacion
    Date fecha =  new Date()
    Date fechaRevision
    Date fechaAnulacion
    Date fechaRevisionAnulacion
    Date fechaLiberacion
    String conceptoAnulacion
    String pathSolicitudAnulacion
    String pathLiberacion
    String numeroContrato
    double  monto
    String concepto
    String observaciones
    String memorandoSolicitud
    String memorandoCertificado
    String pathSolicitud
    String acuerdo
    String archivo
    int estado =0 /* 0 -> solicitado   1-> aprobadp  2-> negado 3-> anulado  4-> liberado */

    static auditable=[ignore:[]]
    static mapping = {
        table 'crtf'
        cache usage:'read-write', include:'non-lazy'
        id column:'crtf__id'
        id generator:'identity'
        version false
        columns {
            id column:'crtf__id'
            usuario column: 'usro__id'
            asignacion column: 'asgn__id'
            fecha column: 'crtffcha'
            fechaRevision column: 'crtffcrv'
            monto column: 'crtfmnto'
            concepto column: 'crtfcnct'
            observaciones column: 'crtfobsr'
            estado column: 'crtfetdo'
            memorandoSolicitud column: 'crtfmemo'
            memorandoCertificado column: 'crtfmecr'
            acuerdo column: 'crtfacue'
            archivo column: 'crtfarch'
            pathSolicitud column: 'crtfsolc'
            fechaAnulacion column: 'crtffcan'
            fechaLiberacion column: 'crtffclb'
            conceptoAnulacion column: 'crtfcpan'
            pathSolicitudAnulacion column: 'crtfphsa'
            pathLiberacion column: 'ctrfptlb'
            numeroContrato column: 'crtfnmct'
            fechaRevisionAnulacion column: 'crtffcra'
        }
    }
    static constraints = {
        usuario(blank:false,nullable: false)
        asignacion(blank:false,nullable: false)
        fecha(blank:false,nullable: false)
        fechaRevision(blank:true,nullable: true)
        monto(blank:false,nullable: false)
        concepto(blank:false,nullable: false,size: 1..1024)
        observaciones(blank:true,nullable: true,size: 1..255)
        estado(blank: false, nullable: false)
        memorandoSolicitud(blank: false,nullable: false,size: 1..40)
        memorandoCertificado(blank: true,nullable: true,size: 1..40)
        acuerdo(blank: true,nullable: true,size: 1..40)
        archivo(blank: true,nullable: true,size: 1..500)
        pathSolicitud(blank: true,nullable: true,size: 1..500)
        pathSolicitud (blank:true,nullable: true,size: 1..500)
        fechaAnulacion (blank:true,nullable: true)
        fechaLiberacion (blank:true,nullable: true)
        conceptoAnulacion (blank:true,nullable: true,size: 1..1024)
        pathSolicitudAnulacion (blank:true,nullable: true,size: 1..500)
        pathLiberacion (blank:true,nullable: true,size: 1..500)
        numeroContrato (blank:true,nullable: true,size: 1..20)
        fechaRevisionAnulacion (blank:true,nullable: true)
    }

    String toString(){
        "${this.monto}"
    }
}
