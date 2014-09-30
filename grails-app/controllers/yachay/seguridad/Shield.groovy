package yachay.seguridad
/**
 * Clase que controla el acceso de los usuarios a las acciones de los controladores
 */
class Shield{
    def beforeInterceptor = [action:this.&auth,except:'login']
    /**
     * Verifica si se ha iniciado una sesión y si el usuario actual tiene los permisos para ejecutar una acción
     */
    def auth() {


        //println "an "+actionName+" cn "+ controllerName+"  "
        if(!session.usuario || !session.perfil){
            if(actionName=~"verificarSession"){
                session.an="inicio"
                session.cn="inicio"

            }else{
                session.an=actionName
                session.cn=controllerName
                session.pr=params
            }
            //println "session .pr "+session.pr
            redirect(controller:'login',action:'login')
            session.finalize()
            return false

        } else {

            //verificacion de permisos
            if(!session.unidad){
                if(controllerName=="proyecto"){
                    if(this.isAllowed() )
                        return true

                    response.sendError(403)
                    return false
                }else{
                    try{
                        def usuario = session.usuario
                        session.unidad = usuario.unidad
                        if(this.isAllowed() )
                            return true

                        response.sendError(403)
                        return false
                    } catch (e){

                        redirect(controller:'login',action:'login')
                        session.finalize()
                        return false
                    }

                }
            }else{
                if(this.isAllowed() )
                    return true

                response.sendError(403)
                return false
            }
        }
    }
    /**
     *Función que verifica si el usuario logeado tiene permisos para ejecutar una acción
     */
    boolean isAllowed(){

        try{
            if(session.permisos[actionName]==controllerName)
                return true
        }catch(e){
            println "execption e"+e
            return true
        }
        return true
    }
}

