package yachay.seguridad

import yachay.alertas.Alerta

/**
 * Service para manejar el login
 */
class LoginService {

    boolean transactional = true
    static scope = 'session'

    /**
     * Busca el usuario en la base de datos según lo parámetros enviados
     * @param user el nombre de usuario
     * @param pass la contraseña
     * @return si encuentra el usuario lo retorna, caso contrario retorna null
     */
    Usro login(user, pass) {
        def usuario = Usro.findWhere(usroLogin: user, usroPassword: pass.trim().encodeAsMD5(), usroActivo: 1)
        if (usuario) {
            if (this.verificarAccesoUsuario(usuario)) {
                return usuario
            } else {
                return null
            }
        } else {
            return null
        }
    }

    /**
     * Verifica si un usuario puede acceder al sistema el día actual
     * @param user el objeto usuario
     * @return un boolean que indica si el usuario tiene acceso o no al sistema
     */
    boolean verificarAccesoUsuario(user) {
        def usuario = user
        def hoy = new Date()
        if (usuario.accesos.size() > 0) {
            usuario.accesos.findAll { fila ->
                if (hoy.after(fila.accsFechaInicial) && hoy.before(fila.accsFechaFinal)) {
                    return false
                }
            }
        } else
            return true

        return true
    }

    /**
     * Busca los perfiles asignados al usuario
     * @param user el objeto usuario
     * @return la lista de perfiles del usuario
     */
    List perfiles(user) {
        def lista = []
        user.sesiones.each {
            lista.add(it.perfil)
//            println " it  "+it.perfil
        }
//        def ls = lista.sort({it.nombre})
//        println "despues: " + ls
        return lista.sort({ it.nombre }).toArray()     /* ordena por nombre de perfil */
    }

    /**
     * Busca las alertas del usuario que no hayan sido recibidas
     * @param user el objeto usuario
     * @return la lista de alertas no recibidas del usuario
     */
    List alertas(user) {
        def lista = []
        lista = Alerta.findAll("from Alerta where usro=${user.id} and fec_recibido is null order by fec_envio", [max: 4, offset: 0])

        return lista
    }

    /**
     * Verifica si la autorización del usuario es correcta
     * @param usuario el objeto usuario
     * @param pass la clave de autorización
     * @return un boolean que indica si la calve de autorización del usuario es o no correcta
     */
    boolean autorizaciones(usuario, pass) {
        def us = Usro.findWhere(usroLogin: usuario.usroLogin, autorizacion: pass.trim().encodeAsMD5(), usroActivo: 1)
        if (us) {
            return true
        } else {
            return false
        }
    }


}
