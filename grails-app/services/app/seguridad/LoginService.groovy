package app.seguridad

class LoginService {

    boolean transactional = true
    static scope = 'session'

    Usro login(user, pass) {
    def usuario = Usro.findWhere(usroLogin: user, usroPassword: pass.trim().encodeAsMD5() ,usroActivo:1 )
        if (usuario) {
            if (this.verificarAccesoUsuario(usuario)) {
                return usuario
            } else {
            	return null
            }
        }
        else{
            return null
        }
    }

    boolean verificarAccesoUsuario(user) {
        def usuario = user
        def hoy = new Date()
        if(usuario.accesos.size() > 0) {
            usuario.accesos.findAll { fila ->
                if(hoy.after(fila.accsFechaInicial) && hoy.before(fila.accsFechaFinal)) {
                    return false
                }
            }
        }
        else
           return true

        return true
    }

    List perfiles(user){
        def lista=[]
        user.sesiones.each {
            lista.add(it.perfil)
//            println " it  "+it.perfil
        }
//        def ls = lista.sort({it.nombre})
//        println "despues: " + ls
        return lista.sort({it.nombre}).toArray()     /* ordena por nombre de perfil */
    }

    List alertas(user){
        def lista=[]
        lista=app.alertas.Alerta.findAll("from Alerta where usro=${user.id} and fec_recibido is null order by fec_envio",[max:4, offset:0])

        return lista
    }

    boolean autorizaciones(usuario,pass) {
        def us = Usro.findWhere(usroLogin: usuario.usroLogin, autorizacion: pass.trim().encodeAsMD5(),usroActivo:1 )
        if (us) {
            return true
        }
        else{
            return false
        }
    }




}
