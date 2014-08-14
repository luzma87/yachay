package app

import app.yachai.Categoria
import sun.security.x509.AVA
import jxl.WorkbookSettings
import jxl.write.WritableWorkbook
import jxl.Workbook
import jxl.write.WritableFont
import jxl.write.WritableCellFormat
import jxl.write.WritableSheet
import jxl.write.Label

class MarcoLogicoController extends app.seguridad.Shield {

    def kerberosService

    def index = { }

    def nuevoMarco = {
        println "nuevo marco " + params
        def proyecto = Proyecto.get(params.id)
        redirect(action: "showMarco", params: [id: proyecto.id])

    }


    def guardarMarco = {
//        println "params guardar "+params

        def proyecto = Proyecto.get(params.proyecto)
        println "fin "
        def fin = new MarcoLogico([proyecto: proyecto, tipoElemento: TipoElemento.findByDescripcion("Fin"), objeto: params.fin])
        fin.save(flush: true)
        println "errores " + fin.errors
        println "indicador "
        def indicador = new Indicador([marcoLogico: fin, descripcion: params.indicador, cantidad: 0])
        indicador.save(flush: true)
        println "errores " + indicador.errors
        println "medios " + params.medios.class
        if (params.medios.class != java.lang.String) {
            params.medios.each {
                def medio = new MedioVerificacion([indicador: indicador, descripcion: it])
                medio.save(flush: true)
                println "errores  " + medio.errors
            }
        } else {
            println "string " + params.medios
            def medio = new MedioVerificacion([indicador: indicador, descripcion: params.medios])
            medio.save(flush: true)
            println "errores string  " + medio.errors
        }
        println "supuestos " + params.supuestos.class
        if (params.supuestos.class != java.lang.String) {
            params.supuestos.each {
//                def tipo = TipoSupuesto.findByDescripcion(it)
                //                if (!tipo) {
                //                    tipo = new TipoSupuesto([descripcion: it])
                //                    tipo.save(flush: true)
                //                }
                def supuesto = new Supuesto([marcoLogico: fin, descripcion: it])
                supuesto.save(flush: true)
                println "errors " + supuesto.errors
            }
        } else {
            println "string " + params.supuestos
//            def tipo = TipoSupuesto.findByDescripcion(params.supuestos)
            //            if (!tipo) {
            //                tipo = new TipoSupuesto([descripcion: params.supuestos])
            //                tipo.save(flush: true)
            //            }
            def supuesto = new Supuesto([marcoLogico: fin, descripcion: params.supuestos])
            supuesto.save(flush: true)
            println "errors string" + supuesto.errors
        }
        redirect(action: "showMarco")
    }

    def showMarco = {
        def proyecto = Proyecto.get(params.id)
        if (proyecto.aprobado == "a") {
            response.sendError(403)
        } else {
            redirect(action: "componentes",id: proyecto.id)
        }

    }

    def eliminarMarco = {
        if (request.method == 'POST') {
            println "eliminar " + params
            def proyecto = Proyecto.get(params.proyecto)
            def ml = MarcoLogico.get(params.id)
            if (ml) {
                def control = MarcoLogico.findAllByMarcoLogico(ml).size()
                control += Meta.findAllByMarcoLogico(ml).size()
                control += Asignacion.findAllByMarcoLogico(ml).size()
                println "control " + control
                if (control < 1) {
                    def indicadores = Indicador.findAllByMarcoLogico(ml)
                    indicadores.each {
                        MedioVerificacion.findAllByIndicador(it).each {m ->
                            params.id = m.id
                            kerberosService.delete(params, MedioVerificacion, session.perfil, session.usuario)
                        }
                        params.id = it.id
                        kerberosService.delete(params, Indicador, session.perfil, session.usuario)
                    }
                    Supuesto.findAllByMarcoLogico(ml).each {
                        params.id = it.id
                        kerberosService.delete(params, Supuesto, session.perfil, session.usuario)
                    }
                    params.id = ml.id
                    kerberosService.delete(params, MarcoLogico, session.perfil, session.usuario)
                    render("ok")
                } else {
                    render "error"
                }

            } else {
                render "error"
            }
        } else {
            redirect(controller: "shield", action: "ataques")
        }

    }

    def eliminarIndiMedSup = {
        if (request.method == 'POST') {
            println "params eliminarIndiMedSup " + params
            switch (params.tipo) {
            /*Inidicadores*/ case "1":
                def indicador = Indicador.get(params.id)
                MedioVerificacion.findAllByIndicador(indicador).each {m ->
                    params.id = m.id
                    kerberosService.delete(params, MedioVerificacion, session.perfil, session.usuario)
                }
                params.id = indicador.id
                kerberosService.delete(params, Indicador, session.perfil, session.usuario)

                break;

            /*Medios*/ case "2":
                kerberosService.delete(params, MedioVerificacion, session.perfil, session.usuario)


                break;
            /*Supuestos*/ case "3":

                kerberosService.delete(params, Supuesto, session.perfil, session.usuario)
                break;
            }
            render "ok"
        } else {
            redirect(controller: "shield", action: "ataques")
        }
    }
    def eliminarIndiMedSupComponentes = {
        if (request.method == 'POST') {
            println "params eliminarIndiMedSup " + params
            switch (params.tipo) {
            /*Inidicadores*/ case "2":
                def indicador = Indicador.get(params.id)
                MedioVerificacion.findAllByIndicador(indicador).each {m ->
                    params.id = m.id
                    kerberosService.delete(params, MedioVerificacion, session.perfil, session.usuario)
                }
                params.id = indicador.id
                kerberosService.delete(params, Indicador, session.perfil, session.usuario)

                break;

            /*Medios*/ case "3":
                kerberosService.delete(params, MedioVerificacion, session.perfil, session.usuario)


                break;
            /*Supuestos*/ case "4":

                kerberosService.delete(params, Supuesto, session.perfil, session.usuario)
                break;
            }
            render "ok"
        } else {
            redirect(controller: "shield", action: "ataques")
        }
    }

    def guardarDatosMarco = {
        //println "gdm " + params
        def proyecto = Proyecto.get(params.proyecto)
        def ml = MarcoLogico.findByProyectoAndTipoElemento(proyecto, TipoElemento.findByDescripcion(params.tipo))
        if (ml) {
            ml.objeto = params.datos
            if (ml.save(flush: true)) {
                render "ok"
            } else {
                println "errores " + ml.errors
                render "no"
            }
        } else {
            ml = new MarcoLogico([proyecto: proyecto, objeto: params.datos, tipoElemento: TipoElemento.findByDescripcion(params.tipo)])
            if (ml.save(flush: true)) {
                render "ok"
            } else {
                println "errores " + ml.errors
                render "no"
            }
        }

    }

    def guardarDatosIndMedSup = {
        //println "gdims " + params
        switch (params.tipo) {
        /*Inidicadores*/ case "1":
            def indicador
            if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                indicador = Indicador.get(params.id)

                indicador.descripcion = params.datos
            } else {
                indicador = new Indicador([marcoLogico: MarcoLogico.get(params.indicador), descripcion: params.datos])
            }

            indicador = kerberosService.saveObject(indicador, Indicador, session.perfil, session.usuario, "guardarDatosIndMedSup", "marcoLogico", session)
            println " indicador " + indicador.errors.getErrorCount()
            if (indicador.errors.getErrorCount() != 0) {
                render "no"
            } else {
                render indicador.id
            }
            break;
        /*Medios*/ case "2":

            def medio
            if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                medio = MedioVerificacion.get(params.id)
                medio.descripcion = params.datos
            } else {

                medio = new MedioVerificacion([indicador: Indicador.get(params.indicador), descripcion: params.datos])

            }

            medio = kerberosService.saveObject(medio, MedioVerificacion, session.perfil, session.usuario, "guardarDatosIndMedSup", "marcoLogico", session)
            println " medio " + medio.errors.getErrorCount()
            if (medio.errors.getErrorCount() != 0) {
                render "no"
            } else {
                render medio.id
            }

            break;
        /*Supuestos*/ case "3":
            def supuesto
            if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                supuesto = Supuesto.get(params.id)
                supuesto.descripcion = params.datos
            } else {
                def marco = MarcoLogico.get(params.indicador)
                supuesto = new Supuesto([descripcion: params.datos, MarcoLogico: marco])

            }

            supuesto = kerberosService.saveObject(supuesto, Supuesto, session.perfil, session.usuario, "guardarDatosIndMedSup", "marcoLogico", session)
            println " supuesto " + supuesto.errors.getErrorCount()
            if (supuesto.errors.getErrorCount() != 0) {
                render "no"
            } else {
                render "" + supuesto.id + "&&" + supuesto.descripcion
            }
            break;
        }

    }

    def agregarSupuesto = {
        println "as " + params
        def tp = TipoSupuesto.get(params.id)
        def marco = MarcoLogico.get(params.marco)
        def supuesto = new Supuesto([MarcoLogico: marco, tipo: tp])
        supuesto = kerberosService.saveObject(supuesto, Supuesto, session.perfil, session.usuario, "datosSupuesto", "marcoLogico", session)
        if (supuesto.errors.getErrorCount() != 0) {
            render "no"
        } else {
            render "" + supuesto.id + "&&" + supuesto.descripcion
        }
    }

    def cargaCombo = {
        render g.select(from: app.TipoSupuesto.list(), name: "tipo", optionKey: "id", optionValue: "descripcion", style: "margin-left: 15px", id: "tipoSupuesto", noSelection: ['-1': 'Seleccione'])
    }

    def componentes = {
        def proyecto = Proyecto.get(params.id)
        if (proyecto.aprobado == "a") {
            response.sendError(403)
        } else {
            def componentes = MarcoLogico.findAllByProyectoAndTipoElemento(proyecto, TipoElemento.findByDescripcion("Componente"), [sort: "id"])
            [componentes: componentes, proyecto: proyecto]
        }
    }

    def guadarDatosComponentes = {
        println "guadarDatosComponentes " + params
        switch (params.tipo) {
            case "1":
                def componente
                def proyecto = Proyecto.get(params.proyecto)
                if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                    componente = MarcoLogico.get(params.id)
                    componente.objeto = params.datos
                } else {
                    componente = new MarcoLogico([proyecto: proyecto, tipoElemento: TipoElemento.findByDescripcion("Componente"), objeto: params.datos])
                }
                componente = kerberosService.saveObject(componente, MarcoLogico, session.perfil, session.usuario, "guadarDatosComponentes", "marcoLogico", session)
                println " componente " + componente.errors.getErrorCount()
                if (componente.errors.getErrorCount() != 0) {
                    render "no"
                } else {
                    render componente.id
                }
                break;

        /*Inidicadores*/ case "2":
            def indicador
            if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                indicador = Indicador.get(params.id)

                indicador.descripcion = params.datos
            } else {
                indicador = new Indicador([marcoLogico: MarcoLogico.get(params.ml), descripcion: params.datos])
            }

            indicador = kerberosService.saveObject(indicador, Indicador, session.perfil, session.usuario, "guadarDatosComponentes", "marcoLogico", session)
            println " indicador " + indicador.errors.getErrorCount()
            if (indicador.errors.getErrorCount() != 0) {
                render "no"
            } else {
                render indicador.id
            }
            break;
        /*Medios*/ case "3":

            def medio
            if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                medio = MedioVerificacion.get(params.id)
                medio.descripcion = params.datos
            } else {

                medio = new MedioVerificacion([indicador: Indicador.get(params.indicador), descripcion: params.datos])

            }

            medio = kerberosService.saveObject(medio, MedioVerificacion, session.perfil, session.usuario, "guadarDatosComponentes", "marcoLogico", session)
            println " medio " + medio.errors.getErrorCount()
            if (medio.errors.getErrorCount() != 0) {
                render "no"
            } else {
                render medio.id
            }

            break;
        /*Supuestos*/ case "4":
            def supuesto
            if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                supuesto = Supuesto.get(params.id)
                supuesto.descripcion = params.datos
            } else {
                def marco = MarcoLogico.get(params.ml)
                supuesto = new Supuesto([descripcion: params.datos, MarcoLogico: marco])

            }

            supuesto = kerberosService.saveObject(supuesto, Supuesto, session.perfil, session.usuario, "guadarDatosComponentes", "marcoLogico", session)
            println " supuesto " + supuesto.errors.getErrorCount()
            if (supuesto.errors.getErrorCount() != 0) {
                render "no"
            } else {
                render "" + supuesto.id + "&&" + supuesto.descripcion
            }
            break;
        }
    }


    def actividadesComponente = {
       // println "actividades " + params

        def componente = MarcoLogico.get(params.id)
        def proyecto = componente.proyecto
        if (proyecto.aprobado == "a") {
            response.sendError(403)
        } else {
            def actividades = MarcoLogico.findAllByMarcoLogicoAndEstado(componente, 0, [sort: "id"])
            def totComp = 0
            def totFin = 0
            def totOtros = 0
            actividades.each {
                totComp += it.monto
            }
            Financiamiento.findAllByProyecto(proyecto).each {
                totFin += it.monto
            }
            MarcoLogico.findAll("from MarcoLogico where tipoElemento = 2 and proyecto= ${proyecto.id} and estado=0 order by id").each {
                if (it.id.toLong() != componente.id.toLong()) {
                    MarcoLogico.findAllByMarcoLogicoAndEstado(it, 0, [sort: "id"]).each {ac ->
                        totOtros += ac.monto
                    }
                }
            }
            [componente: componente, actividades: actividades, totComp: totComp, totFin: totFin, totOtros: totOtros]
        }

    }

    def guardarDatos = {
        //println "guardar datos!! "+params
        def act = MarcoLogico.get(params.act)
        act.responsable = UnidadEjecutora.get(params.resp)
        if(params.act!="-1")
        act.categoria = Categoria.get(params.cat)
        act.fechaInicio = new Date().parse("dd-MM-yyyy",params.inicio)
        act.fechaFin = new Date().parse("dd-MM-yyyy",params.fin)
        act.aporte = params.aporte.toDouble()
        if(act.save(flush: true))
            render "ok"
        else{
            render "error"
            println "error save act  "+act.errors
        }
    }

    def guadarDatosActividades = {
        println "guadarDatosActividades " + params
        switch (params.tipo) {
            case "1":
                def actividad
                def componente = MarcoLogico.get(params.componente)
                if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                    actividad = MarcoLogico.get(params.id)
                    actividad.objeto = params.datos
                } else {
                    def maxNum = MarcoLogico.list([sort:"numero",order: "desc",max: 1])?.pop()?.numero
                    if(maxNum)
                        maxNum=maxNum+1
                    else
                        maxNum=1
                    actividad = new MarcoLogico([proyecto: componente.proyecto, tipoElemento: TipoElemento.findByDescripcion("Actividad"), objeto: params.datos, marcoLogico: componente,numero:maxNum])
                }
                actividad = kerberosService.saveObject(actividad, MarcoLogico, session.perfil, session.usuario, "guadarDatosActividades", "marcoLogico", session)
                println " actividad " + actividad.errors.getErrorCount()
                if (actividad.errors.getErrorCount() != 0) {
                    render "no"
                } else {
                    render actividad.id
                }
                break;

        /*Inidicadores*/ case "2":
            def indicador
            if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                indicador = Indicador.get(params.id)

                indicador.descripcion = params.datos
            } else {
                indicador = new Indicador([marcoLogico: MarcoLogico.get(params.ml), descripcion: params.datos])
            }

            indicador = kerberosService.saveObject(indicador, Indicador, session.perfil, session.usuario, "guadarDatosActividades", "marcoLogico", session)
            println " indicador " + indicador.errors.getErrorCount()
            if (indicador.errors.getErrorCount() != 0) {
                render "no"
            } else {
                render indicador.id
            }
            break;
        /*Medios*/ case "3":

            def medio
            if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                medio = MedioVerificacion.get(params.id)
                medio.descripcion = params.datos
            } else {

                medio = new MedioVerificacion([indicador: Indicador.get(params.indicador), descripcion: params.datos])

            }

            medio = kerberosService.saveObject(medio, MedioVerificacion, session.perfil, session.usuario, "guadarDatosActividades", "marcoLogico", session)
            println " medio " + medio.errors.getErrorCount()
            if (medio.errors.getErrorCount() != 0) {
                render "no"
            } else {
                render medio.id
            }

            break;
        /*Supuestos*/ case "4":
            def supuesto
            if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                supuesto = Supuesto.get(params.id)
                supuesto.descripcion = params.datos
            } else {

                def marco = MarcoLogico.get(params.ml)
                supuesto = new Supuesto([descripcion: params.datos, MarcoLogico: marco])

            }

            supuesto = kerberosService.saveObject(supuesto, Supuesto, session.perfil, session.usuario, "guadarDatosActividades", "marcoLogico", session)
            println " supuesto " + supuesto.errors.getErrorCount()
            if (supuesto.errors.getErrorCount() != 0) {
                render "no"
            } else {
                render "" + supuesto.id + "&&" + supuesto.descripcion
            }
            break;

        /*Monto*/ case "5":
            def actividad
            if (params.id && params.id != "" && params.id != " " && params.id != "0") {
                actividad = MarcoLogico.get(params.id)
                params.monto = params.datos
                actividad.properties = params
//                try {
//                    actividad.monto = params.datos.toDouble()
//                } catch (e) {
//                    actividad.monto = 0
//                }
                actividad = kerberosService.saveObject(actividad, MarcoLogico, session.perfil, session.usuario, "guadarDatosActividades", "marcoLogico", session)
                println " actividad " + actividad.errors.getErrorCount()
                if (actividad.errors.getErrorCount() != 0) {
                    render "no"
                } else {
                    render actividad.id
                }
            } else {
                render "no"
            }

            break;
        }
    }

    def verMarcoCompleto = {
        def proyecto = Proyecto.get(params.id)
        def tpel = TipoElemento.findByDescripcion("Fin")
        def fin = MarcoLogico.find("from MarcoLogico where proyecto=${proyecto.id} and tipoElemento=${tpel.id} and estado=0")
        tpel = TipoElemento.findByDescripcion("Proposito")
        def proposito = MarcoLogico.find("from MarcoLogico where proyecto=${proyecto.id} and tipoElemento=${tpel.id} and estado=0")
        tpel = TipoElemento.findByDescripcion("Componente")
        def componentes = MarcoLogico.findAll("from MarcoLogico where proyecto=${proyecto.id} and tipoElemento=${tpel.id} and estado=0 order by id")

        println "fin " + fin.id + " prop " + proposito + " comp " + componentes

        [fin: fin, proposito: proposito, proyecto: proyecto, componentes: componentes]


    }

    def ingresoMetas = {
        println "ingreso metas " + params
        def actividad = MarcoLogico.get(params.id)
        def proyecto = actividad.proyecto
        if (proyecto.aprobado == "a") {
            response.sendError(403)
        } else {

            def metas = Meta.findAllByMarcoLogico(actividad, [sort: "id"])
            [actividad: actividad, metas: metas, metaActual: params.meta]
        }
    }



    def matrizMetas = {
        def anios = Anio.list([sort: 'anio'])
        def componente = MarcoLogico.get(params.id)
        def unidades = Unidad.list()
        def tipos = TipoMeta.list()
        [componente: componente, anios: anios, unidades: unidades, tipos: tipos]
    }

    def guardarMetasMatriz = {
        println "params "+params
        def componente = MarcoLogico.get(params.id)
        def data = params.data.split("%%")
        println "data "+data
        data.each {
            def parts = it.split("&&")
            def meta = new Meta()
            meta.marcoLogico=componente
            meta.anio=Anio.get(parts[0])
            meta.tipoMeta=TipoMeta.get(parts[1])
            meta.indicador = parts[3].toDouble()
            meta.unidad=Unidad.get(parts[2])
            meta.parroquia=Parroquia.get(parts[4])
            kerberosService.saveObject(meta,Meta,session.perfil,session.usuario,"guardarMetasMatriz","marcoLogico",session)
//            anio+"&&"+tipo+"&&"+unidad+"&&"+meta+"&&"+parroquia+"%%"
        }
        redirect(action: ingresoMetas,id: componente.id)
    }

    def mapaMeta = {
//        println "--params " + params
        def meta = Meta.get(params.id)
        def provincia = meta.parroquia.canton.provincia

        def avance = Avance.findAllByMeta(meta, [sort: 'valor', order: 'desc', max: 1])
        def ttip = "", ttitle = ""

        ttitle = meta.descripcion
        if (avance) {
            avance = avance[0]
            ttip = "<strong>Avance:</strong> " + avance.descripcion + "<br/>"
            ttip += "<strong>Valor:</strong> " + avance.valor + "<br/><br/>"
        }
        ttip += "<strong>Meta:</strong> " + meta.indicador + " " + meta.unidad + "<br/>"
        ttip += "<strong>Indicador:</strong> " + meta.tipoMeta + "<br/>"
        if (avance && meta.indicador > 0) {
            ttip += "<strong>Porcentaje:</strong> " + g.formatNumber(number: ((avance.valor * 100) / meta.indicador), format: "###,##0", minFractionDigits: 2, maxFractionDigits: 2) + "%"
        }

        def geo = [
                "lat": -1.80,
                "lng": -78.36,
                "zoom": 7,
                "diff": 2,
                "minZoom": 7
        ]
        if (meta.latitud != 0 && meta.longitud != 0) {
            geo.lat = meta.latitud
            geo.lng = meta.longitud
            geo.zoom = meta.zoom
            geo.diff = meta.parroquia.canton.provincia.diff
            geo.minZoom = meta.parroquia.canton.provincia.zoom
        } else {
            def parr = meta.parroquia
            if (parr.latitud != 0 && parr.longitud != 0) {
                geo.lat = parr.latitud
                geo.lng = parr.longitud
                geo.zoom = parr.zoom
                geo.diff = parr.diff
                geo.minZoom = parr.canton.zoom
            } else {
                def cant = parr.canton
                if (cant.latitud != 0 && cant.longitud != 0) {
                    geo.lat = cant.latitud
                    geo.lng = cant.longitud
                    geo.zoom = cant.zoom
                    geo.diff = cant.diff
                    geo.minZoom = cant.provincia.zoom
                } else {
                    def prov = cant.provincia
                    if (prov.latitud != 0 && prov.longitud != 0) {
                        geo.lat = prov.latitud
                        geo.lng = prov.longitud
                        geo.zoom = prov.zoom
                        geo.diff = prov.diff
                        geo.minZoom = 7
                    }
                }
            }
        }
//        println geo

        return [provincia: provincia, meta: meta, avance: avance, ttitle: ttitle, ttip: ttip, componente: meta.marcoLogico, geo: geo]
    }

    def guardarMapaMeta = {
        def meta = Meta.get(params.id)
//        meta.cord_x = params.coordX.toInteger()
//        meta.cord_y = params.coordY.toInteger()

        meta.latitud = params.lat.toDouble()
        meta.longitud = params.long.toDouble()
        meta.zoom = params.zoom.toDouble()

        kerberosService.saveObject(meta, Meta, session.perfil, session.usuario, actionName, controllerName, session)
        redirect(action: "mapaMeta", id: meta.id, params: [c: params.c])
    }

    def asignacionesActividad = {
        def actividad = MarcoLogico.get(params.id)
        def asignaciones = Asignacion.findAllByMarcoLogico(actividad, [sort: "anio"])
        def fuentes = Financiamiento.findAllByProyecto(actividad.proyecto).fuente
        [actividad: actividad, asignaciones: asignaciones, fuentes: fuentes]
    }


}
