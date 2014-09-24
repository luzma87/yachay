package yachay.proyectos

import groovy.io.FileType
import yachay.proyectos.Proyecto

class ExtraController {

    def index = {}

    def extra = {

        def proys = Proyecto.list()
//        println proys
        [proys: proys]

    }

}
