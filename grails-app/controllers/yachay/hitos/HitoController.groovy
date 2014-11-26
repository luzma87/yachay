package yachay.hitos

import jxl.Sheet
import jxl.Workbook
import jxl.WorkbookSettings
import yachay.avales.ProcesoAval
import yachay.parametros.TipoElemento
import yachay.proyectos.MarcoLogico
import yachay.proyectos.Proyecto

class HitoController {

    def crearHito = {

        def hito = null
        if(params.id)
            hito = Hito.get(params.id)

        def proys = Proyecto.list([sort: "nombre"])
        [hito:hito,proyectos:proys]

    }

    def verHito = {
        def hito = Hito.get(params.id)
        [hito:hito]
    }

    def saveHito = {
        println "params "+params
        def hito
        if(params.id)
            hito = Hito.get(params.id)
        else
            hito = new Hito()
        def fechaP = new Date().parse("dd/MM/yyyy",params.fechaPlanificada)
        def inicio = new Date().parse("dd/MM/yyyy",params.inicio)
        hito.descripcion = params.descripcion
        hito.fechaPlanificada = fechaP
        hito.inicio = inicio
        if(!hito.fecha)
            hito.fecha=new Date()
        hito.tipo="A"
        if(!hito.save(flush: true)){
            println "error save hito "+hito
        }
        flash.message="Datos guardados"
        redirect(action: "crearHito",id: hito.id)
    }

    def agregarComp = {
        println "agregar comp "+params
        def hito = Hito.get(params.id)
        switch (params.tipo){
            case "proy":
                def comp = new ComposicionHito()
                comp.hito=hito
                comp.proyecto = Proyecto.get(params.componente)
                comp.save(flush: true)
                break;
            case "ml":
                def comp = new ComposicionHito()
                comp.hito=hito
                comp.marcoLogico = MarcoLogico.get(params.componente)
                comp.save(flush: true)
                break;
            case "proc":
                def comp = new ComposicionHito()
                comp.hito=hito
                comp.proceso = ProcesoAval.get(params.componente)
                if(!comp.save(flush: true))
                    println "errores save "+comp.errors
                break;
            default:
                println "wtf"
                break
        }
        redirect(action: "composicion",id: hito.id)

    }

    def composicion = {
        def hito = Hito.get(params.id)
        return [comp:ComposicionHito.findAllByHito(hito,[sort: "id"]),ver:params.ver,hito:hito]
    }

    def componentesProyecto = {
        def proyecto = Proyecto.get(params.id)
        def comps = yachay.proyectos.MarcoLogico.findAllByProyectoAndTipoElemento(proyecto, TipoElemento.get(2))
        [comps:comps]
    }
    def cargarActividades = {
        def comp = MarcoLogico.get(params.id)
        def acts = MarcoLogico.findAllByMarcoLogico(comp)
        [acts:acts]
    }

    def borrarDetalle = {
        def ch = ComposicionHito.get(params.id)
        ch.delete()
        render "ok"
    }

    def lista = {
        def hitos = Hito.list([sort: "fecha"])
        [hitos:hitos]
    }

    def cargarExcelHitos ={

    }
    /*Función para cargar un archivo excel de hitos financiero*/
    /**
     * Acción
     */

    def subirExcelHitos ={

        println("entro excel hitos")

        def path = servletContext.getRealPath("/") + "excel/"
        new File(path).mkdirs()
        def f = request.getFile('file')


        WorkbookSettings ws = new WorkbookSettings();
        ws.setEncoding("ISO-8859-1");


        Workbook workbook = Workbook.getWorkbook(f.inputStream, ws)
        Sheet sheet = workbook.getSheet(0)

        def numeroContrato =[]
        def anticipo = []
        def devengado = []
        def monto = []
        def n = []
        def m = []
        byte b
        def ext

        if(f && !f.empty){
            def nombre = f.getOriginalFilename()
            def parts = nombre.split("\\.")
            nombre = ""
            parts.eachWithIndex { obj, i ->
                if (i < parts.size() - 1) {
                    nombre += obj
                } else {
                    ext = obj
                }
            }

            def reps = [
                    "a": "[àáâãäåæ]",
                    "e": "[èéêë]",
                    "i": "[ìíîï]",
                    "o": "[òóôõöø]",
                    "u": "[ùúûü]",

                    "A": "[ÀÁÂÃÄÅÆ]",
                    "E": "[ÈÉÊË]",
                    "I": "[ÌÍÎÏ]",
                    "O": "[ÒÓÔÕÖØ]",
                    "U": "[ÙÚÛÜ]",

                    "n": "[ñ]",
                    "c": "[ç]",

                    "N": "[Ñ]",
                    "C": "[Ç]",

                    "" : "[\\!@#\\\$%\\^&*()-='\"\\/<>:;\\.,\\?]",

                    "_": "[\\s]"
            ]

            reps.each { k, v ->
                nombre = (nombre.trim()).replaceAll(v, k)
            }

            nombre = nombre + "." + ext
            def pathFile = path + File.separatorChar + nombre
            def src = new File(pathFile)

            if(ext == 'xls'){
                if(src.exists()){
                    flash.message = 'Ya existe un archivo con ese nombre. Por favor cambielo o elimine el otro archivo primero.'
                    flash.estado = "error"
                    flash.icon = "alert"
                    redirect(action: 'cargarExcelHitos')
                    return
                }else{

                    println("entro!")
                    for(int r =1; r < sheet.rows; r++){

                        def nc = sheet.getCell(1,r).contents //columna de número de contrato
                        def an = sheet.getCell(2,r).contents //columna de anticipo
                        def dv = sheet.getCell(3,r).contents //columna de devengado
                        def mc = sheet.getCell(4,r).contents //columna de monto contrato

                        println("numero contrato " + nc)
                        println("anticipo " + an)
                        println("devengado " + dv)
                        println("monto contrato " + mc)

                    }

                    flash.message = 'Archivo cargado existosamente.'
                    flash.estado = "error"
                    flash.icon = "alert"
                    redirect(action: 'cargarExcelHitos')
                    return
                }
            }else{
                flash.message = 'El archivo a cargar debe ser del tipo EXCEL con extensión XLS.'
                flash.estado = "error"
                flash.icon = "alert"
                redirect(action: 'cargarExcelHitos')
                return
            }


        }else{
            flash.message = 'No se ha seleccionado ningun archivo para cargar'
            flash.estado = "error"
            flash.icon = "alert"
            redirect(action: 'cargarExcelHitos')
            return
        }



    }


}
