package app.reportes

import yachay.contratacion.Aprobacion
import yachay.poa.Asignacion
import yachay.contratacion.Solicitud
import yachay.seguridad.Usro
import yachay.contratacion.DetalleMontoSolicitud
import yachay.avales.SolicitudAval
import jxl.Workbook
import jxl.WorkbookSettings
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

class ReporteSolicitudController {

    def index = {}

    def solicitudesXls = {

        def iniRow = 1
        def iniCol = 0

        def list2 = Solicitud.findAll("from Solicitud order by unidadEjecutora.id,fecha")
        def list01 = []
        def list02 = []
        def anios = []

        list2.each { s ->
            def aprobaciones = Aprobacion.findBySolicitud(s)
            if (aprobaciones && aprobaciones.tipoAprobacion.codigo != "NA") {
                list01 += s
            } else {
                list02 += s
            }
        }
        def list = list01 + list02

        list.each { sol ->
            DetalleMontoSolicitud.findAllBySolicitud(sol, [sort: "anio"]).each { d ->
                if (!anios.contains(d.anio)) {
                    anios.add(d.anio)
                }
            }
        }

        WorkbookSettings workbookSettings = new WorkbookSettings()
        workbookSettings.locale = Locale.default
        def file = File.createTempFile('solicitudes', '.xls')
        def label
        def number
        file.deleteOnExit()

        WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
        def row = iniRow
        def col = iniCol
        WritableSheet sheet = workbook.createSheet('MySheet', 0)

        label = new Label(3, row, "YACHAY EP"); sheet.addCell(label);
        sheet.setColumnView(col, 30)
        row++
        label = new Label(3, row, "Lista de solicitudes de contratación"); sheet.addCell(label);
        sheet.setColumnView(col, 30)

        row += 2

        label = new Label(col, row, "Proyecto"); sheet.addCell(label);
        sheet.setColumnView(col, 30)
        col++
        label = new Label(col, row, "Componente"); sheet.addCell(label);
        sheet.setColumnView(col, 30)
        col++
        label = new Label(col, row, "N.Poa"); sheet.addCell(label);
        sheet.setColumnView(col, 20)
        col++
        label = new Label(col, row, "Nombre"); sheet.addCell(label);
        sheet.setColumnView(col, 30)
        col++
        label = new Label(col, row, "Objetivo"); sheet.addCell(label);
        sheet.setColumnView(col, 30)
        col++
        label = new Label(col, row, "TDR's"); sheet.addCell(label);
        col++
        label = new Label(col, row, "Responsable"); sheet.addCell(label);
        sheet.setColumnView(col, 20)
        col++
        anios.each { a ->
            label = new Label(col, row, "Valor ${a.anio}"); sheet.addCell(label);
            sheet.setColumnView(col, 20)
            col++
        }
        label = new Label(col, row, "Monto"); sheet.addCell(label);
        sheet.setColumnView(col, 20)
        col++
        label = new Label(col, row, "Aprobacion"); sheet.addCell(label);
        sheet.setColumnView(col, 20)
        col++
        label = new Label(col, row, "Fecha Solicitud"); sheet.addCell(label);
        sheet.setColumnView(col, 20)

        row++
        list.each { solicitudInstance ->
            col = iniCol
            label = new Label(col, row, solicitudInstance.actividad.proyecto.toString()); sheet.addCell(label);
            col++
            label = new Label(col, row, solicitudInstance.actividad.marcoLogico.toString()); sheet.addCell(label);
            col++
            label = new Label(col, row, Asignacion.findByMarcoLogico(solicitudInstance.actividad)?.presupuesto?.numero); sheet.addCell(label);
            col++
            label = new Label(col, row, solicitudInstance.nombreProceso); sheet.addCell(label);
            col++
            label = new Label(col, row, solicitudInstance.objetoContrato); sheet.addCell(label);
            col++
            label = new Label(col, row, "X"); sheet.addCell(label);
            col++
            label = new Label(col, row, solicitudInstance.unidadEjecutora?.codigo); sheet.addCell(label);
            col++

            anios.each { a ->
                def valor = DetalleMontoSolicitud.findByAnioAndSolicitud(a, solicitudInstance)
                if (valor) {
                    number = new jxl.write.Number(col, row, valor.monto); sheet.addCell(number);
                } else {
                    label = new Label(col, row, ""); sheet.addCell(label);
                }
                col++
            }
            number = new jxl.write.Number(col, row, solicitudInstance.montoSolicitado); sheet.addCell(number);
            col++
            def estado = Aprobacion.findBySolicitud(solicitudInstance)
            if (estado) {
                label = new Label(col, row, estado.tipoAprobacion.descripcion + " (" + estado.fecha.format("dd-MM-yyyy") + ")");
                sheet.addCell(label);
                col++
            } else {
                label = new Label(col, row, "Pendiente"); sheet.addCell(label);
                col++
            }
            label = new Label(col, row, solicitudInstance.fecha?.format('dd-MM-yyyy')); sheet.addCell(label);
            col++

            row++
        }

        workbook.write();
        workbook.close();
        def output = response.getOutputStream()
        def header = "attachment; filename=" + "solicitudes.xls";
        response.setContentType("application/octet-stream")
        response.setHeader("Content-Disposition", header);
        output.write(file.getBytes());
    }

    def solicitudes = {
        println "solicitudes"
        def list2 = Solicitud.findAll("from Solicitud order by unidadEjecutora.id,fecha")
        def list01 = []
        def list02 = []
        def anios = []

        list2.each { s ->
            def aprobaciones = Aprobacion.findBySolicitud(s)
            if (aprobaciones && aprobaciones.tipoAprobacion.codigo != "NA") {
                list01 += s
            } else {
                list02 += s
            }
        }
        def list = list01 + list02

//        list = list.sort { it.aprobacion?.descripcion + it.unidadEjecutora?.nombre + it.fecha.format("dd-MM-yyyy") }
//        list = list.sort { a, b ->
//            ((a.aprobacion?.descripcion <=> b.aprobacion?.descripcion) ?:
//                    (a.unidadEjecutora?.nombre <=> b.unidadEjecutora?.nombre)) ?:
//                    (a.fecha?.format("dd-MM-yyyy") <=> b.fecha?.format("dd-MM-yyyy"))
//    }


        list.each { sol ->
            DetalleMontoSolicitud.findAllBySolicitud(sol, [sort: "anio"]).each { d ->
                if (!anios.contains(d.anio)) {
                    anios.add(d.anio)
                }
            }
        }
        return [solicitudInstanceList: list, anios: anios]
    }

    def aprobadas = {
        println "aprobadas"
        def list = []
        def list2 = Solicitud.findAll("from Solicitud order by unidadEjecutora.id,fecha")

        list2.each { s ->
            def aprobaciones = Aprobacion.findBySolicitud(s)
            if (aprobaciones && aprobaciones.tipoAprobacion.codigo != "NA") {
                list += s
            }
        }

        def anios = []

//        list = list.sort { it.aprobacion?.descripcion + it.unidadEjecutora?.nombre + it.fecha.format("dd-MM-yyyy") }
//        list = list.sort { a, b ->
//            ((a.aprobacion?.descripcion <=> b.aprobacion?.descripcion) ?:
//                    (a.unidadEjecutora?.nombre <=> b.unidadEjecutora?.nombre)) ?:
//                    (a.fecha?.format("dd-MM-yyyy") <=> b.fecha?.format("dd-MM-yyyy"))
//    }


        list.each { sol ->
            DetalleMontoSolicitud.findAllBySolicitud(sol, [sort: "anio"]).each { d ->
                if (!anios.contains(d.anio)) {
                    anios.add(d.anio)
                }


            }
        }
        return [solicitudInstanceList: list, anios: anios]
    }

    def solicitudesReunion = {
        def list = []
        list = Solicitud.findAll("from Solicitud where  incluirReunion='S' order by unidadEjecutora.id,fecha")
        def anios = []
        list.each { sol ->
            DetalleMontoSolicitud.findAllBySolicitud(sol, [sort: "anio"]).each { d ->
                if (!anios.contains(d.anio)) {
                    anios.add(d.anio)
                }


            }
        }
        return [solicitudInstanceList: list, anios: anios]
    }

    def imprimirSolicitud = {
        def solicitud = Solicitud.get(params.id)

        def firmas = []

        if (solicitud.usuario) {
            firmas += [cargo: "Responsable unidad", usuario: solicitud.usuario]
        }
        return [solicitud: solicitud, firmas: firmas]
    }

    def imprimirActaAprobacion = {
//        println "Acta aprobacion:::: " + params
        def solicitud = Solicitud.get(params.id)
        def aprobacion = Aprobacion.findBySolicitud(solicitud)

//        def cargoDirectorPlanificacion = CargoPersonal.findByCodigo("DRPL")
//        def cargoGerentePlanificacion = CargoPersonal.findByCodigo("GRPL")
//
//        def directorPlanificacion = Usro.findByCargoPersonal(cargoDirectorPlanificacion)
//        def gerentePlanificacion = Usro.findByCargoPersonal(cargoGerentePlanificacion)
//
//        def firmas = []
//        if (directorPlanificacion) {
//            firmas += [cargo: "Director de planificación", usuario: directorPlanificacion]
//        }
//        if (gerentePlanificacion) {
//            firmas += [cargo: "Gerente de planificación", usuario: gerentePlanificacion]
//        }
        def firmas = []
        if (params.fgp != "null") {
            def gerentePlanificacion = Usro.get(params.fgp)
            if (gerentePlanificacion) {
                firmas += [cargo: "Gerente de planificación", usuario: gerentePlanificacion]
            }
        }
        if (params.fdp != "null") {
            def directorPlanificacion = Usro.get(params.fdp)
            if (directorPlanificacion) {
                firmas += [cargo: "Director de planificación", usuario: directorPlanificacion]
            }
        }
        if (params.fgt != "null") {
            def gerenteTec = Usro.get(params.fgt)
            if (gerenteTec) {
                firmas += [cargo: "Gerente técnico", usuario: gerenteTec]
            }
        }
        if (params.frq != "null") {
            def req = Usro.get(params.frq)
            if (req) {
                firmas += [cargo: "Requirente", usuario: req]
            }
        }

        return [solicitud: solicitud, aprobacion: aprobacion, firmas: firmas]
    }

    def imprimirSolicitudAval = {
        println "impr sol " + params
        def solicitud = SolicitudAval.get(params.id)
        println "solcitud " + solicitud
        return [solicitud: solicitud]
    }
}
