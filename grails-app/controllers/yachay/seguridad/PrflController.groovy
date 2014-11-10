package yachay.seguridad

/**
 * Controlador que muestra las pantallas para el manejo de perfiles
 */
class PrflController {

    def dbConnectionService
    def loginService
    def kerberosService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que redirecciona a list
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción que muestra los módulos a los que un perfil tiene acceso
     * @param id es el identificador del perfil
     */
    def modulos = {
//       println "recibe de parametros: ${params.id}"
       def prflInstance = Prfl.get(params.id)
       if (!prflInstance) {
         flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prfl.label', default: 'Perfil'), params.id])}"
         redirect(action: "list")
       }
       else {
          def lstacmbo = lstaModulos(params.id)
          //println "modulos:----- " + lstacmbo
          render(view: "modulos", model: [prflInstance: prflInstance, lstacmbo: lstacmbo])
       }
    }

    /**
     * Acción que muestra una previsualización del menú de usuario basado en un perfil
     * @param id es el identificador del perfil
     */
  def verMenu = {
    def prfl = params.prfl.toInteger()
//    println "verMenu: ---------parametros: ${params}"
    render(g.generarMenuPreview( perfil: prfl))
    //render(g.generarMenu( perfil: "${prfl}" ))
  }

    /**
     * Acción que muestra una lista de las acciones que puede ver un determinado perfil
     * @param prfl es el identificador del perfil
     * @param tpac es el identificador del tipo de acción.
     * @param ids es la lista de los módulos
     */
    def ajaxPermisos = {
        def prfl = params.prfl.toInteger()
        def tpac = params.tpac
        //println "---------parametros: ${params}"
        def resultado = []
        def i = 0
        def ids = params.ids
        if (params.menu?.size() > 0) ids = params.menu
        if (params.grabar) {
          //println "a grabar... ${prfl}, ${ids}"
        }
        def cn = dbConnectionService.getConnection()
        def tx = ""
        // selecciona las acciones que no se han consedido permisos
        tx = "select accn.accn__id, accndscr, accnnmbr, ctrlnmbr, prms.accn__id prms " +
                "from (accn left join prms on prms.accn__id = accn.accn__id and prfl__id = ${prfl}), ctrl " +
                "where mdlo__id in (" + ids + ") and " +
                "accn.ctrl__id = ctrl.ctrl__id and tpac__id = " + tpac + " order by ctrlnmbr, accndscr"
        //println "ajaxPermisos SQL: ${tx}"
        cn.eachRow(tx) { d ->
          resultado[i] = [d.accn__id] + [d.accndscr] + [d.accnnmbr] + [d.ctrlnmbr] + [d.prms]
          i++
        }
        cn.close()
        //println "-------------------------" + resultado
        render(view: 'lsta', model: [datos: resultado, mdlo__id: ids, tpac__id: tpac])
      }

    /**
     * Acción que muestra un formulario para la creación de un nuevo módulo
     */
    def creaMdlo = {
      def mdloInstance = new Modulo()
      //prflInstance.properties = params
      render(view: 'creaMdlo', model: ['mdloInstance': mdloInstance])
    }

    /**
     * Acción que muestra los datos de un módulo en un formulario para su edición
     * @param id es el identificador del módulo
     */
    def editMdlo = {
//      println "------editMdlo: " + params
      def mdloInstance = Modulo.get(params.id)
      //println mdloInstance
      render(view: 'creaMdlo', model: ['mdloInstance': mdloInstance])
    }

    /**
     * Acción que guarda los datos de un módulo
     * @param id es el identificador del módulo en caso de edición
     * @param params es un mapa con los nombres de los campos del dominio módulo y los valores ingresados por el usuario
     */
  def grabaMdlo = {
//    println "+++++parametros: ${params}"
    if (!params.id) {
      def mdloInstance = new Modulo()
      params.controllerName = controllerName
      params.actionName = "saveMdlo"
      mdloInstance = kerberosService.save(params, Modulo, session.perfil, session.usuario)
      if (mdloInstance.properties.errors.getErrorCount() > 0) {
        //println "---- save ${bancoInstance}"
        render("El módulo no ha podido crearse")
      } else {
        render("El módulo ${params.nombre} ha sido grabado en el sistema")
      }
    } else {
//      println "<<< Update >>> : ${params.id}"
      def mdloInstance = Modulo.get(params.id)
      params.controllerName = controllerName
      params.actionName = "UpdateMdlo"
      mdloInstance = kerberosService.save(params, Modulo, session.perfil, session.usuario)
      if (mdloInstance.properties.errors.getErrorCount() > 0) {
//        println "---- save ${mdloInstance}"
        render("El módulo no se ha podido actualizar")
      } else {
        render("ok")
      }
    }
  }

    /**
     * Acción que borra un módulo
     * @param id es el identificador del módulo
     */
    def borraMdlo = {
//      println "------borrarMdlo: " + params
      params.controllerName = controllerName
      params.actionName = "delete"
      kerberosService.delete(params, Modulo, session.perfil, session.usuario)
      render('borrado: ${params.id}')
    }

    /**
     * Acción que muestra un formulario para la creación de un nuevo perfil
     */
    def creaPrfl = {
      def prflInstance = new Prfl()
      //prflInstance.properties = params
      render(view: 'crear', model: ['prflInstance': prflInstance])
    }

    /**
     * Acción que guarda los datos de un perfil
     * @param id es el identificador del perfil en caso de edición
     * @param params es un mapa con los nombres de los campos del dominio perfil y los valores ingresados por el usuario
     */
    def grabaPrfl = {
//      println "+++++parametros: ${params}"
      if (!params.id) {
        def prflInstance = new Prfl()
        params.controllerName = controllerName
        params.actionName = "save"
        prflInstance = kerberosService.save(params, Prfl, session.perfil, session.usuario)
        if (prflInstance.properties.errors.getErrorCount() > 0) {
          println "---- save ${prflInstance.errors}"
          render("El perfil no ha podido crearse")
        } else {
          if (prflInstance.padre) {
            def prms = Prms.findAllByPerfil(prflInstance.padre)
            def prmsNuevo = new Prms()
            prms.each {
              prmsNuevo = new Prms(['perfil.id': prflInstance.id, 'accion.id': it.accion.id])
              prmsNuevo.save(failOnError: true)
            }
          }
          render("El perfil ${params.nmbr} ha sido grabado en el sistema")
        }
      } else {
//        println "<<< Update >>> : ${params.id}"
        def prflInstance = Prfl.get(params.id)
        params.controllerName = controllerName
        params.actionName = "Update"
        prflInstance = kerberosService.save(params, Prfl, session.perfil, session.usuario)
        if (prflInstance.properties.errors.getErrorCount() > 0) {
//          println "---- save ${prflInstance}"
          render("El perfil no ha podido actualizar")
        } else {
          render("ok")
        }
      }
    }

    /**
     * Acción que muestra los datos de un perfil en un formulario para su edición
     * @param id es el identificador del perfil
     */
    def editPrfl = {
//      println "------editPrfl: " + params
      def prflInstance = Prfl.get(params.id)
      render(view: 'crear', model: ['prflInstance': prflInstance])
    }

    /**
     * Acción que borra un perfil
     * @param id es el identificador del perfil
     */
    def borraPrfl = {
//      println "------editPrfl: " + params
      params.controllerName = controllerName
      params.actionName = "delete"
      kerberosService.delete(params, Prfl, session.perfil, session.usuario)
      render('borrado: ${params.id}')
    }

    /* TODO: revisar grabar.
    * **/

    /**
     * Acción que graba los permisos asignados a un perfil
     * @param ids es un arreglo con los identificadores de las acciones asignadas al perfil
     * @param menu es el identificador del módulo
     * @param prfl es el identificador del perfil
     */
    def grabar = {
      //println "parametros grabar: ${params}"
      def ids = params.ids
      def modulo = params.menu
      def prfl = params.prfl
      def tx1 = ""
      def exst = []
      def actl = []

      if(ids.size()<1) ids = '1000000' // este valor no existe como accn__id, y sirve para el IN del SQL
      // eliminar los permisos que no estén chequeados
      def cn = dbConnectionService.getConnection()
      def cn1 = dbConnectionService.getConnection()
      def tx = ""
      tx = "select prms__id from prms, accn where accn.accn__id = prms.accn__id and " +
              "mdlo__id = ${modulo} and " +
              "prms.accn__id not in (select accn__id " +
              "from accn where mdlo__id = " + modulo + " and  " +
              "accn__id in (${ids})) and prfl__id = ${prfl}"

//      println "grabar SQL: ${tx}"
      cn.eachRow(tx) { d ->
        params.id = d.prms__id
//        println "parametro para borrar: $params"
        kerberosService.delete(params, Prms, session.perfil, session.usuario)
      }
      //println "-------------borrado de permisos----------"
      // se debe barrer tosos los menús señalados y si está chequeado añadir a prms.
      tx = "select prms.accn__id from prms, accn where accn.accn__id = prms.accn__id and " +
              "mdlo__id = ${modulo} and " +
              "prms.accn__id in (select accn__id " +
              "from accn where mdlo__id = " + modulo + " and accn__id in (${ids})) and prfl__id = ${prfl}"
      //println "grabar IN SQL: ${tx}"
      exst = []
      cn.eachRow(tx) { d ->
        exst.add(d.accn__id)
      }
      tx = "select accn__id " +
              "from accn where mdlo__id = " + modulo + " and accn__id in (${ids})"
      //println "grabar señalados SQL: ${tx}"
      actl = []
      cn.eachRow(tx) { d ->
        actl.add(d.accn__id)
      }
      //println "insercion  Actual: ${actl} \n Exst: ${exst}}"
      (actl - exst).each {
        tx1 = "insert into prms(prfl__id, accn__id) values (${prfl.toInteger()}, ${it})"
        try {
          cn.execute(tx1)
          insertaKerveros(prfl.toInteger(), session.usuario, session.perfil)
//          println "insertando.... ${tx1}"
        }
        catch (Exception ex) {
          println ex.getMessage()
        }
        //resp += "<br>" + tx1
      }
      cn.close()
      cn1.close()
      //println "recibido:" + params
      //render(resp + "<br>Existe:" + exst + "<br>Actual:" + actl + "<br>a insertar: ${actl-exst}")
      redirect(action: 'ajaxPermisos', params: params)
    }


    //---------------------------------

    /**
     * Acción que muestra una lista de los perfiles registrados en  el sistema
     */
    def list = {
        def title = g.message(code: "prfl.list", default: "Prfl List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [prflInstanceList: Prfl.list(params), prflInstanceTotal: Prfl.count(), title: title, params: params]
    }

    /**
     * Acción que muestra un formulario para inserción o edición de perfiles
     */
    def form = {
        def title
        def prflInstance

        if (params.source == "create") {
            prflInstance = new Prfl()
            prflInstance.properties = params
            title = g.message(code: "prfl.create", default: "Create Prfl")
        } else if (params.source == "edit") {
            prflInstance = Prfl.get(params.id)
            if (!prflInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prfl.label', default: 'Prfl'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "prfl.edit", default: "Edit Prfl")
        }

        return [prflInstance: prflInstance, title: title, source: params.source]
    }

    /**
     * Acción que redirecciona a form
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que guarda los datos de un perfil
     * @param id es el identificador del perfil en caso de edición
     * @param params es un mapa con el nombre de los campos del dominio perfil y los datos ingresados por el usuario
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "prfl.edit", default: "Edit Prfl")
            def prflInstance = Prfl.get(params.id)
            if (prflInstance) {
                prflInstance.properties = params
                if (!prflInstance.hasErrors() && prflInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'prfl.label', default: 'Prfl'), prflInstance.id])}"
                    redirect(action: "show", id: prflInstance.id)
                }
                else {
                    render(view: "form", model: [prflInstance: prflInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prfl.label', default: 'Prfl'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "prfl.create", default: "Create Prfl")
            def prflInstance = new Prfl(params)
            if (prflInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'prfl.label', default: 'Prfl'), prflInstance.id])}"
                redirect(action: "show", id: prflInstance.id)
            }
            else {
                render(view: "form", model: [prflInstance: prflInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción que actualiza los datos de un perfil
     * @param id es el identificador del perfil
     * @param params es un mapa con el nombre de los campos del dominio perfil y los datos ingresados por el usuario
     */
    def update = {
        def prflInstance = Prfl.get(params.id)
        if (prflInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (prflInstance.version > version) {

                    prflInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'prfl.label', default: 'Prfl')] as Object[], "Another user has updated this Prfl while you were editing")
                    render(view: "edit", model: [prflInstance: prflInstance])
                    return
                }
            }
            prflInstance.properties = params
            if (!prflInstance.hasErrors() && prflInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'prfl.label', default: 'Prfl'), prflInstance.id])}"
                redirect(action: "show", id: prflInstance.id)
            }
            else {
                render(view: "edit", model: [prflInstance: prflInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prfl.label', default: 'Prfl'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra los datos de un perfil
     * @param id es el identificador del perfil
     */
    def show = {
        def prflInstance = Prfl.get(params.id)
        if (!prflInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prfl.label', default: 'Prfl'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "prfl.show", default: "Show Prfl")

            [prflInstance: prflInstance, title: title]
        }
    }

    /**
     * Acción que redirecciona a form
     */
    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que borra un perfil
     * @param id es el identificador del perfil
     */
    def delete = {
        def prflInstance = Prfl.get(params.id)
        if (prflInstance) {
            try {
                prflInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'prfl.label', default: 'Prfl'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'prfl.label', default: 'Prfl'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prfl.label', default: 'Prfl'), params.id])}"
            redirect(action: "list")
        }
    }


    /*
    * Función que obtiene una lista de módulos de un perfil desde la base de datos
    * @param perfil es el identificador del perfil
    */
    List lstaModulos(prfl) {
      def resultado = []
      def cn = dbConnectionService.getConnection()
      cn.eachRow("select mdlo__id, mdlonmbr from mdlo order by mdloordn") { d ->
        resultado.add([d.mdlo__id] + [d.mdlonmbr])
      }
      cn.close()
      return resultado
    }


}
