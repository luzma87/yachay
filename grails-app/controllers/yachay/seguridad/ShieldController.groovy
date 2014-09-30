package yachay.seguridad

/**
 * Controlador que muestra los diferentes mensajes de error o de accesos restringidos del sistema
 */
class ShieldController {
    def loginService
    /**
     * Acción que muestra una pantalla cuando se produce algún intento de ingreso a acciones restringidas en el sistema
     */
    def ataques = {
        def msn="Se ha detectado que esta ejecutando una acción que atenta contra la seguridad del sistema.<br>Dicha accion sera registrada en su historial.<br>"
        render(view:"advertencia",model:[msn:msn])
    }
    /**
    * Acción que muestra una pantalla cuando se produce un error 404 en el sistema
    */
    def error404 = {
        def msn="Esta tratando de ingresar a una accion no registrada en el sistema. Por favor use las opciones del menu para navegar por el sistema."
        render(view:"advertencia",model:[msn:msn])
    }
    /**
     * Acción que muestra una pantalla cuando se produce un fallo en el sistema. Errores de tipo 500
     */
    def error = {
        def msn="Ha ocurrido un error interno."
        try{
            def er = new ErrorLog()
            er.fecha=new Date()
            er.error=request["exception"].message?.encodeAsHTML()
            er.causa=request["exception"].cause?.message?.encodeAsHTML()
            er.url=request["javax.servlet.forward.request_uri"];
            er.usuario=session.usuario
            er.save()
            // println " \n<===Error Aqui===> "+request["javax.servlet.forward.request_uri"]
            //println " \n<===Que eres pal burro?????? ===> "+request["exception"].message?.encodeAsHTML()
            //println " \n<===Causa===> "+request["exception"].cause?.message?.encodeAsHTML()

        }catch (e){
            println "error en error "+e
        }
        render(view:"advertencia",model:[msn:msn,error:true])
    }
    /**
     * Acción que comprueba la clave de autorización
     * @param atrz es la contraseña de autorización
     */
    def comprobarPassword = {
        if(request.method=='POST'){
            println "comprobar password "+params
            def resp=loginService.autorizaciones(session.usuario,params.atrz)
            render(resp)
        }else{
            response.sendError(403)
        }
    }


    /**
     * Acción de prueba
     */
    def prueba = {
        def er = new ErrorLog()
        er.fecha=new Date()
        er.error="Boton de borrado"
        er.causa="Boton de borrado"
        er.url="Boton de borrado"
        er.usuario=session.usuario
        er.save()
        redirect(action: "logout",controller: "login")
    }

}
