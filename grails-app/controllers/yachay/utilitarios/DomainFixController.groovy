package yachay.utilitarios

class DomainFixController {

    def migracionService

    def index =  {

    }

    def valoresDominios = {
        println "valores dominio "+params
        flash.message = migracionService.arreglaDominios(params.dominio)
        println "msg "+flash.message
        redirect(action: "index")
        return
    }


    def secuencias = {

        render    migracionService.arreglarSecuencias()
    }
}
