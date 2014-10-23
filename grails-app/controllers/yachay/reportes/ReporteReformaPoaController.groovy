package yachay.reportes

import sun.misc.Perf
import yachay.modificaciones.SolicitudModPoa
import yachay.seguridad.Prfl
import yachay.seguridad.Sesn

/**
 * Controlador para generar reportes de reformas del POA
 */
class ReporteReformaPoaController {

    /**
     * Genera el reporte PDF de solicitud de reforma al POA
     */
    def solicitudReformaPoa = {
//        http://localhost:8090/yachay/pdf/pdfLink?url=/yachay/reporteReformaPoa/solicitudReformaPoa/?id=1&filename=Solicitud.pdf
        def sol = SolicitudModPoa.get(params.id)
        [sol:sol]
    }

    /**
     * Genera el reporte PDF de la reforma al POA
     */
    def reformaPoa = {
        def sol = SolicitudModPoa.get(params.id)
        if(sol.estado!=1)
            response.sendError(403)
        def director = Sesn.findByPerfil(Prfl.findByCodigo("DP"))
        if(director){
            director=director.usuario
        }
        def gerente = Sesn.findByPerfil(Prfl.findByCodigo("GP"))
        if(gerente){
            gerente=gerente.usuario
        }
        [sol:sol,gerente:gerente,director:director]

    }
}
