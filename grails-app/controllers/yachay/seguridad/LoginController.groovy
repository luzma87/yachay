package yachay.seguridad


/**
 * Controlador
 */
class LoginController {

    def loginService

    /**
     * Acción
     */
    def index = {
        println "index login"
    }


    /**
     * Acción
     */
    def pagina = {

    }


    /**
     * Acción
     */
    def login = {
        println "login "+params
        def user   = params.usuario
        def pass   = params.password
        def perfil = params.perfil

        if(params.usuario && params.password) {
            println "usuario: ${user} password: ${pass.encodeAsMD5().size()}"
            def usuario = loginService.login(user, pass)
            println "---> usuario: ${usuario}"
            if(usuario){
                flash.message = null
                session.usuario = usuario
                session.unidad = usuario.unidad
                def perfiles = loginService.perfiles(usuario)
                render(view:'index', model:[perfiles:perfiles])
            }else{
                flash.message = "Usuario o contraseña inválidos"
                render(view:'index')
            }
        }
        else{
            if(session.usuario && params.perfil){
                session.perfil=Prfl.get(params.perfil)
                def permisos=yachay.seguridad.Prms.findAllByPerfil(session.perfil)
                def hp=[:]
                permisos.each{
                    hp.put(it.accion.accnNombre,it.accion.control.ctrlNombre)
                }
                session.permisos=hp
                session.color='cafe'

                if(session.an && session.cn){
                    redirect(controller:session.cn, action:session.an,params: session.pr)
                }else{
                    redirect(controller:"inicio", action:"index")
                }
            }
            else{
                session.usuario=null
                redirect(action:"index")
            }
        }
    }
    /**
     * Acción
     */
    def logout = {
        if(session.usuario) {
            session.usuario = null
            session.perfil=null
            session.permisos=null
            session.menu=null
            session.invalidate()
            redirect(action:index)
        } else {
            redirect(action:'index')
        }
    }



}
