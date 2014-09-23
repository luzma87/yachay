package app

import yachay.proyectos.Proyecto

class ExtraController {

    def index = {}

    def extra = {

        def proys = Proyecto.list()
//        println proys
        [proys:proys]

    }

}
