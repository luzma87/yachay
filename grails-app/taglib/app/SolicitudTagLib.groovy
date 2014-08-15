package app

import app.seguridad.Prfl
import app.seguridad.Usro

class SolicitudTagLib {
    static namespace = 'slc'

    def showSolicitud = { attrs ->
        def solicitud = attrs.solicitud
        def w = attrs.w ?: "1030px"
        def editable = attrs.editable ?: false
        def aprobacion = attrs.aprobacion ?: false
        def perfil = attrs.perfil ?: null
        if (!solicitud) {
            solicitud = Solicitud.get(attrs.id.toLong())
        }
        def html = "No se encontró la solicitud a mostrar"
        if (solicitud) {
            html = '<table style="width: ' + w + ';">'
            html += '<tr>'
            html += '<td class="label">Unidad requirente</td>'
            html += '<td colspan="3">'
            html += solicitud.unidadEjecutora?.nombre
            html += '</td>'

            html += '<td class="label">Proyecto</td>'
            html += '<td colspan="3">'
            html += solicitud.actividad?.proyecto?.nombre
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Componente</td>'
            html += '<td colspan="3" id="tdComponente">'
            html += solicitud.actividad?.marcoLogico?.objeto
            html += '</td>'

            html += '<td class="label">Actividad</td>'
            html += '<td colspan="3" id="tdActividad">'
            html += solicitud.actividad?.objeto
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Nombre del proceso</td>'
            html += '<td colspan="3">'
            html += solicitud.nombreProceso
            html += '</td>'

            html += '<td class="label">Forma de pago</td>'
            html += '<td>'
            html += solicitud.formaPago?.descripcion
            html += '</td>'

            html += '<td class="label">Plazo de ejecución</td>'
            html += '<td>'
            html += solicitud.plazoEjecucion + " días"
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Fecha</td>'
            html += '<td colspan="3">'
            html += solicitud.fecha?.format("dd-MM-yyyy")
            html += '</td>'

            html += '<td class="label">Monto solicitado</td>'
            html += '<td>'
            html += g.formatNumber(number: solicitud.montoSolicitado, type: "currency")
            html += '</td>'

            html += '<td class="label">Modalidad de contratación</td>'
            html += '<td>'
            html += solicitud.tipoContrato?.descripcion
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Objeto del contrato</td>'
            html += '<td colspan="7">'
            html += solicitud.objetoContrato
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Observaciones</td>'
            html += '<td colspan="7">'
            html += solicitud.observaciones
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Archivo TDR (pdf)</td>'
            html += '<td colspan="7">'
            html += g.link(controller: 'solicitud', action: "downloadFile", id: solicitud.id) { solicitud.pathPdfTdr }
            html += '</td>'
            html += '</tr>'
            html += '</table>'

            html += revisiones(solicitud: solicitud, editable: editable, perfil: perfil)
            html += aprobaciones(solicitud: solicitud, editable: aprobacion, perfil: perfil)
        }
        out << html
    }

    def revisiones = { attrs ->
        Solicitud solicitud = attrs.solicitud
        Prfl perfil = attrs.perfil ?: new Prfl()
        def editable = false
        if (attrs.editable == "true" || attrs.editable == true || attrs.editable == 1 || attrs.editable == "1") {
            editable = true
        }
        if (solicitud.estado != "P") {
            editable = false
        }
        def html = "No se encontró la solicitud a mostrar"
        if (solicitud) {
            html = '<table width="100%" class="ui-widget-content ui-corner-all">'
            html += '<thead>'
            html += '<tr>'
            html += '<td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">'
            html += 'Gerencia Administrativa Financiera'
            html += '</td>'
            html += '</tr>'
            html += '</thead>'
            html += '<tbody>'
            html += '<td style="width: 98px;" class="label">Observaciones</td>'
            html += '<td style="width: 785px;">'
            if (!editable) {
                html += (solicitud.observacionesAdministrativaFinanciera ?: '- Sin observaciones-')
            } else {
                if (perfil.codigo == "GAF") {
                    html += g.textArea(name: "observacionesAdministrativaFinanciera", "class": "ui-widget-content ui-corner-all",
                            rows: "5", cols: "5", value: solicitud.observacionesAdministrativaFinanciera)
                }
            }
            html += '</td>'
            html += '<td style="width: 127px;">'
            if (!editable) {
                html += (solicitud.revisadoAdministrativaFinanciera ?
                        'Revisado el ' + solicitud.revisadoAdministrativaFinanciera.format('dd-MM-yyyy') :
                        'No revisado')
            } else {
                if (perfil.codigo == "GAF") {
                    html += '<label class="label" for="gaf">Revisado'
                    html += '<input type="checkbox" name="gaf" id="gaf" ' + (solicitud.revisadoAdministrativaFinanciera ? 'checked' : '') + '/>'
                    html += '</label>'
                }
            }
            html += '</td>'
            html += '</tbody>'
            html += '</table>'

            html += '<table width="100%" class="ui-widget-content ui-corner-all">'
            html += '<thead>'
            html += '<tr>'
            html += '<td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">'
            html += 'Gerencia Jurídica'
            html += '</td>'
            html += '</tr>'
            html += '</thead>'
            html += '<tbody>'
            html += '<td style="width: 98px;" class="label">Observaciones</td>'
            html += '<td style="width: 785px;">'
            if (!editable) {
                html += (solicitud.observacionesJuridica ?: '- Sin observaciones-')
            } else {
                if (perfil.codigo == "GJ") {
                    html += g.textArea(name: "observacionesJuridica", "class": "ui-widget-content ui-corner-all",
                            rows: "5", cols: "5", value: solicitud.observacionesJuridica)
                }
            }
            html += '</td>'
            html += '<td style="width: 127px;">'
            if (!editable) {
                html += (solicitud.revisadoJuridica ?
                        'Revisado el ' + solicitud.revisadoJuridica.format('dd-MM-yyyy') :
                        'No revisado')
            } else {
                if (perfil.codigo == "GJ") {
                    html += '<label class="label" for="gj">Revisado'
                    html += '<input type="checkbox" name="gj" id="gj" ' + (solicitud.revisadoJuridica ? 'checked' : '') + '/>'
                    html += '</label>'
                }
            }
            html += '</td>'
            html += '</tbody>'
            html += '</table>'

            html += '<table width="100%" class="ui-widget-content ui-corner-all">'
            html += '<thead>'
            html += '<tr>'
            html += '<td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">'
            html += 'Gerencia de Dirección de Proyectos'
            html += '</td>'
            html += '</tr>'
            html += '</thead>'
            html += '<tbody>'
            html += '<td class="label" style="width: 98px;">Observaciones</td>'
            html += '<td style="width: 785px;">'
            if (!editable) {
                html += (solicitud.observacionesDireccionProyectos ?: '- Sin observaciones-')
            } else {
                if (perfil.codigo == "GDP") {
                    html += g.textArea(name: "observacionesDireccionProyectos", "class": "ui-widget-content ui-corner-all",
                            rows: "5", cols: "5", value: solicitud.observacionesDireccionProyectos)
                }
            }
            html += '</td>'
            html += '<td style="width: 127px;">'
            if (!editable) {
                html += (solicitud.revisadoDireccionProyectos ?
                        'Revisado el ' + solicitud.revisadoDireccionProyectos.format('dd-MM-yyyy') :
                        'No revisado')
            } else {
                if (perfil.codigo == "GDP") {
                    html += '<label class="label" for="gdp">Revisado'
                    html += '<input type="checkbox" name="gdp" id="gdp" ' + (solicitud.revisadoDireccionProyectos ? 'checked' : '') + '/>'
                    html += '</label>'
                }
            }
            html += '</td>'
            html += '</tbody>'
            html += '</table>'
        }
        out << html
    }

    def aprobaciones = { attrs ->
        Solicitud solicitud = attrs.solicitud
        Prfl perfil = attrs.perfil ?: new Prfl()
        def editable = false
        if (attrs.editable == "true" || attrs.editable == true || attrs.editable == 1 || attrs.editable == "1") {
            editable = true
        }
        if (editable && !(perfil.codigo == "GT" || perfil.codigo == "GP")) {
            editable = false
        }

        def aprobacion = Aprobacion.findBySolicitud(solicitud)
        if (!aprobacion) {
            aprobacion = new Aprobacion()
        }

        def anioPasado = Anio.findByAnio((new Date().format('yyyy').toInteger() - 1).toString())
        def tieneArrastre = Asignacion.countByMarcoLogicoAndAnio(solicitud.actividad, anioPasado) > 0

        def html = ""
        if (solicitud) {
            html = '<table>'
            html += '<tr>'
            html += '<td class="label">Tipo de aprobación</td>'
            html += '<td>'
            if (editable) {
                html += g.select(from: TipoAprobacion.list(), name: "tipoAprobacion.id", id: "tipoAprobacion",
                        optionKey: "id", optionValue: "descripcion", value: aprobacion.tipoAprobacionId)
            } else {
                html += (aprobacion.tipoAprobacion ? aprobacion.tipoAprobacion.descripcion : "-Sin tipo de aprobación-")
            }
            html += '</td>'
            html += '<td class="label">Fondos</td>'
            html += '<td>'
            if (editable) {
                html += g.select(from: Fuente.list(), name: "fuente.id", id: "fuente",
                        optionKey: "id", optionValue: "descripcion", value: aprobacion.fuenteId)
            } else {
                html += (aprobacion.fuente ? aprobacion.fuente.descripcion : "-Sin fuente de fondos-")
            }
            html += '</td>'
            html += '<td>'
            if (tieneArrastre) {
                html += 'Usa recursos de arrastre'
            } else {
                html += 'No usa recursos de arrastre'
            }
            html += '</td>'
            html += '</tr>'
            html += '<tr>'
            html += '<td class="label">Observaciones</td>'
            html += '<td colspan="4">'
            if (editable) {
                html += g.textArea(name: "observaciones", rows: "5", cols: "5", value: aprobacion.observaciones)
            } else {
                html += (aprobacion.observaciones ?: '-Sin observaciones-')
            }
            html += '</td>'
            html += '</tr>'
            if (aprobacion.pathPdf) {
                html += '<tr>'
                html += '<td class="label">Archivo (pdf)</td>'
                html += '<td colspan="4">'
                html += g.link(controller: 'solicitud', action: "downloadActa", id: aprobacion.id) {
                    aprobacion.pathPdf
                }
                html += '</td>'
                html += '</tr>'
            }
            html += '</table>'
        }
        out << html
    }


    def headerReporte = { attrs ->
        def title = attrs.title ?: ""

        def logoPath = resource(dir: 'images', file: 'logo.jpg')
        def rowspan = 2
        def w = 65
        if (title != "") {
            rowspan += 1
//            w = 85
        }

        def html = ""
        html += "<table width='100%' style='margin-bottom:10px;'>"
        html += "<tr>"
        html += "<td rowspan='${rowspan}'><img src='${logoPath}' style='width:${w}px;'/></td>"
        html += "</tr>"
        html += "<tr>"
        html += "<td class='ttl'>YACHAY EP</td>"
        html += "</tr>"
//        html += "<tr>"
//        html += "<td class='ttl'>GERENCIA DE PLANIFICACIÓN</td>"
//        html += "</tr>"
//        html += "<tr>"
//        html += "<td class='ttl'>DIRECCIÓN DE PLANIFICACIÓN</td>"
//        html += "</tr>"
        if (title != "") {
            html += "<tr>"
            html += "<td class='ttl'>${title}</td>"
            html += "</tr>"
        }
        html += "</table>"

        out << html
    }

    def infoReporte = { attrs ->
        Solicitud solicitud = attrs.solicitud

        def html = ""
        if (solicitud) {
            html += '<table style="width: 100%;">'
            html += '<tr>'
            html += '<td class="label">Unidad requirente</td>'
            html += '<td colspan="5">'
            html += solicitud.unidadEjecutora?.nombre
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Proyecto</td>'
            html += '<td colspan="5">'
            html += solicitud.actividad?.proyecto?.nombre
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Componente</td>'
            html += '<td colspan="5">'
            html += solicitud.actividad?.marcoLogico?.objeto
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Actividad</td>'
            html += '<td colspan="5">'
            html += solicitud.actividad?.objeto
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Núm. POA</td>'
            html += '<td colspan="5">'
            html += solicitud.actividad?.numero
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Nombre del proceso</td>'
            html += '<td colspan="5">'
            html += solicitud.nombreProceso
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Forma de pago</td>'
            html += '<td>'
            html += solicitud.formaPago?.descripcion
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Plazo de ejecución</td>'
            html += '<td>'
            html += solicitud.plazoEjecucion + " días"
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Fecha</td>'
            html += '<td colspan="3">'
            html += solicitud.fecha?.format("dd-MM-yyyy")
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Monto solicitado</td>'
            html += '<td>'
            html += g.formatNumber(number: solicitud.montoSolicitado, type: "currency")
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Modalidad de contratación</td>'
            html += '<td colspan="3">'
            html += solicitud.tipoContrato?.descripcion
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Objeto del contrato</td>'
            html += '<td colspan="7">'
            html += solicitud.objetoContrato
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Observaciones</td>'
            html += '<td colspan="7">'
            html += solicitud.observaciones
            html += '</td>'
            html += '</tr>'

            html += '<tr>'
            html += '<td class="label">Archivo (pdf)</td>'
            html += '<td colspan="7">'
            html += solicitud.pathPdfTdr
            html += '</td>'
            html += '</tr>'
            html += '</table>'
        }
        html += revisionesReporte(solicitud: solicitud)
        html += aprobacionesReporte(solicitud: solicitud)
        out << html
    }

    def revisionesReporte = { attrs ->
        Solicitud solicitud = attrs.solicitud

        def html = ""
        if (solicitud) {
            html += '<table width="100%" class="ui-widget-content ui-corner-all">'
            html += '<thead>'
            html += '<tr>'
            html += '<td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">'
            html += 'Gerencia Administrativa Financiera (' + (solicitud.revisadoAdministrativaFinanciera ?
                    'Revisado el ' + solicitud.revisadoAdministrativaFinanciera.format('dd-MM-yyyy') :
                    'No revisado') + ')'
            html += '</td>'
            html += '</tr>'
            html += '</thead>'
            html += '<tbody>'
            html += '<td style="width: 98px;" class="label">Observaciones:</td>'
            html += '<td style="width: 785px;">'
            html += (solicitud.observacionesAdministrativaFinanciera ?: '- Sin observaciones-')
            html += '</td>'
            html += '</tbody>'
            html += '</table>'

            html += '<table width="100%" class="ui-widget-content ui-corner-all">'
            html += '<thead>'
            html += '<tr>'
            html += '<td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">'
            html += 'Gerencia Jurídica (' + (solicitud.revisadoJuridica ?
                    'Revisado el ' + solicitud.revisadoJuridica.format('dd-MM-yyyy') :
                    'No revisado') + ')'
            html += '</td>'
            html += '</tr>'
            html += '</thead>'
            html += '<tbody>'
            html += '<td style="width: 98px;" class="label">Observaciones:</td>'
            html += '<td style="width: 785px;">'
            html += (solicitud.observacionesJuridica ?: '- Sin observaciones-')
            html += '</td>'
            html += '</tbody>'
            html += '</table>'

            html += '<table width="100%" class="ui-widget-content ui-corner-all">'
            html += '<thead>'
            html += '<tr>'
            html += '<td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">'
            html += 'Gerencia de Dirección de Proyectos (' + (solicitud.revisadoDireccionProyectos ?
                    'Revisado el ' + solicitud.revisadoDireccionProyectos.format('dd-MM-yyyy') :
                    'No revisado') + ')'
            html += '</td>'
            html += '</tr>'
            html += '</thead>'
            html += '<tbody>'
            html += '<td class="label" style="width: 98px;">Observaciones:</td>'
            html += '<td style="width: 785px;">'
            html += (solicitud.observacionesDireccionProyectos ?: '- Sin observaciones-')
            html += '</td>'
            html += '</tbody>'
            html += '</table>'
        }
        out << html
    }

    def aprobacionesReporte = { attrs ->
        Solicitud solicitud = attrs.solicitud
        Aprobacion aprobacion = Aprobacion.findBySolicitud(solicitud)
        def html = ""
        if (solicitud && aprobacion) {
            html += '<table width="100%">'
            html += '<tr>'
            html += '<td class="label">Aprobación</td>'
            html += '<td>'
            html += aprobacion.tipoAprobacion?.descripcion
            html += '</td>'
            html += '</tr>'
            html += '<tr>'
            html += '<td class="label">Fondos</td>'
            html += '<td>'
            html += aprobacion.fuente?.descripcion
            html += '</td>'
            html += '</tr>'
            html += '<tr>'
            html += '<td class="label">Observaciones</td>'
            html += '<td>'
            html += aprobacion.observaciones
            html += '</td>'
            html += '</tr>'
            html += '</table>'
        }
        out << html
    }

    def firmasReporte = { attrs ->
        def firmas = attrs.firmas
        def html = ''
        if (firmas.size() > 0) {

            def w = 100 / firmas.size()
            html = "<table width='100%' style='margin-top:2cm;' cellspacing='20' >"
            html += "<tr>"
            firmas.each { firma ->
                html += "<td width='${w}%' style='border-top: solid 1px black; text-align:center;'>"
                html += firma.usuario.persona.nombre + " " + firma.usuario.persona.apellido
                html += "<br/>" + firma.cargo
                html += "</td>"
            }
            html += "</tr>"
            html += "</table>"
        }
        out << html
    }
}
