package yachay.reportes

import yachay.proyectos.Meta
import yachay.proyectos.Proyecto
import yachay.parametros.geografia.Provincia
import yachay.parametros.geografia.Canton
import yachay.parametros.geografia.Parroquia
import yachay.proyectos.Avance
import yachay.parametros.proyectos.TipoMeta

class MapasController {

    def getPins = {
//        println "getPins: " + params
        def parts = params.tipo.split("_")
        def tipo = parts[0]
        def ret

        switch (tipo) {
            case "resumen":
                def json = "["

                Provincia.list().each { p ->
                    if ((p.latitud && p.longitud) && (p.latitud != 0 || p.longitud != 0)) {

                        json += "{"
                        json += '"id":"' + p.id + '",'
                        json += '"title":"' + p.nombreMostrar + '",'
                        json += '"latitud":' + p.latitud + ','
                        json += '"longitud":' + p.longitud + ','
                        json += '"image":' + '"' + params.image + '",'
                        json += '"url":' + '"' + createLink(controller: "meta", action: 'datosProv') + '"'
                        json += "},"
                    }
                }
                if (json != "[") {
                    json = json[0..json.size() - 2]
                }
                json += "]"
                ret = json
                break;
            case "tipoMeta":
                def json = "["
                def id = parts[1]

                def tipoMeta = TipoMeta.get(id)

                tipoMeta.metas.metasCoords.each { m ->
                    json += "{"
                    json += '"id":"' + m.id + '",'
                    json += '"title":"' + m.indicador + " " + m.unidad.descripcion + '",'
                    json += '"latitud":' + m.latitud + ','
                    json += '"longitud":' + m.longitud + ','
                    json += '"image":' + '"' + params.image + '",'
                    json += '"url":' + '"' + createLink(controller: 'meta', action: 'datosMeta') + '"'
                    json += "},"
                }
                if (json != "[") {
                    json = json[0..json.size() - 2]
                }
                json += "]"
                ret = json
                break;
            case "proyecto":
                def json = "["
                def id = parts[1]

                def proyecto = Proyecto.get(id)

                proyecto.metas.metasCoords.each { m ->
                    json += "{"
                    json += '"id":"' + m.id + '",'
                    json += '"title":"' + m.indicador + " " + m.unidad.descripcion + '",'
                    json += '"latitud":' + m.latitud + ','
                    json += '"longitud":' + m.longitud + ','
                    json += '"image":' + '"' + params.image + '",'
                    json += '"url":' + '"' + createLink(controller: 'meta', action: 'datosMeta') + '"'
                    json += "},"
                }

                if (json != "[") {
                    json = json[0..json.size() - 2]
                }
                json += "]"
                ret = json
                break;
            case "provincia":
                def json = "["
                def id = parts[1]

                def provincia = Provincia.get(id)

                def metas = Meta.withCriteria {
                    parroquia {
                        canton {
                            eq("provincia", provincia)
                        }
                    }
                }

                metas.each { m ->
                    if ((m.latitud && m.longitud) && (m.latitud != 0 || m.longitud != 0)) {
                        json += "{"
                        json += '"id":"' + m.id + '",'
                        json += '"title":"' + m.indicador + " " + m.unidad.descripcion + '",'
                        json += '"latitud":' + m.latitud + ','
                        json += '"longitud":' + m.longitud + ','
                        json += '"image":' + '"' + params.image + '",'
                        json += '"url":' + '"' + createLink(controller: 'meta', action: 'datosMeta') + '"'
                        json += "},"
                    }
                }


                if (json != "[") {
                    json = json[0..json.size() - 2]
                }
                json += "]"
                ret = json
                break;
        }

        render ret
    }

    def index = {

    }

    def provincia = {
        def proyectos = []
        def prov = Provincia.findByNombreIlike(params.nombre)
        def image = g.resource(dir: 'images/mapas/provincias', file: prov.imagen)
        def metas = []
        if (prov) {
            (Canton.findAllByProvincia(prov)).each { canton ->
                Parroquia.findAllByCanton(canton).each { p ->
                    def meta = Meta.findAllByParroquia(p)
                    if (meta.size() > 0) {
                        def pin = "pin0.png"
                        meta.each { e ->
                            def proy = e.marcoLogico.proyecto
                            if (!proyectos.proy.contains(proy)) {
                                pin = "pin" + (proyectos.size()) % 16 + ".png"
                                def pr = [:]
                                pr.proy = proy
                                pr.pin = pin
                                proyectos.add(pr)
                            }
                            def m = [:]
                            def avance = Avance.findAllByMeta(e, [sort: 'valor', order: 'desc', max: 1])
                            def ttip = "", ttitle
                            ttitle = e.indicador + " " + e.tipoMeta.descripcion
                            ttip += "<strong>Proyecto: </strong>" + proy.nombre + "<br/><br/>"
                            if (avance) {
                                avance = avance[0]
                                ttip += "<strong>Avance:</strong> " + avance.descripcion + "<br/>"
                                ttip += "<strong>Valor:</strong> " + avance.valor + "<br/><br/>"
                            }
                            ttip += "<strong>Meta:</strong> " + e.indicador + " " + e.tipoMeta.descripcion + "<br/>"

                            def marco = e.marcoLogico
                            while (marco) {
                                ttip += "<strong>" + marco.tipoElemento.descripcion + ":</strong> " + marco + "</br>"
                                marco = marco.marcoLogico
                            }

                            ttip += "<strong>Unidad:</strong> " + e.unidad + "<br/>"
                            if (avance && e.indicador > 0) {
                                ttip += "<strong>Porcentaje:</strong> " + g.formatNumber(number: ((avance.valor * 100) / meta.indicador), format: "###,##0", minFractionDigits: 2, maxFractionDigits: 2) + "%"
                            }
                            m.meta = e
                            m.ttitle = ttitle
                            m.ttip = ttip
                            m.pin = pin
                            metas.add(m)
                        }
                    } //if meta.size > 0
                } // parroquias
            } //cantones
        } //if prov
        [image: image, prov: params.nombre, metas: metas, proyectos: proyectos]
    }

    def datosMeta = {
        println "datos meta " + params
        def meta = Meta.get(params.id)
        [meta: meta]
    }
}
