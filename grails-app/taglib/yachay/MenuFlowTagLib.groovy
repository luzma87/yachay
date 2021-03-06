package yachay

/**
 * Tag del menú para SEMPLADES
 */
class MenuFlowTagLib {
    static namespace = 'mf'

    /**
     * Crea un menú para el flow SEMPLADES
     * @param items los items a ubicar en el menú
     * @param links indica si crear o no links (crea a menos que este parámetro sea: "false", false, "0", 0)
     * @deprecated ahora se usa el menuSemplades
     */
    @Deprecated
    def menuSemplades_flow = { attrs ->
        def str = ""

        def x = request.findAll { it.key == 'flowRequestContext' }
        def actual = x["flowRequestContext"].getCurrentState().id

        def items = attrs.items

        def links = (attrs.links == "false" || attrs.links == false || attrs.links == "0" || attrs.links == 0) ? false : true

        str += '<div class="" style="padding: 5px 3px 5px 3px;">' + "\n"

        str += '<ul id="menu">' + "\n"

        items.each { item ->

            def paramsLink = (item.link).clone()

            def clase = ((actual == item.evento) ? "current" : "")

            str += '<li>' + "\n"
            if (links && clase != "current") {
                str += '<a href="' + g.createLink(paramsLink) + '" class="' + clase + '" >' + "\n"
            } else {
                str += '<a href="#" class="noLink ' + clase + '" style="cursor: default;" >' + "\n"
            }
            str += item.text + "\n"
            str += '</a>' + "\n"
            str += '</li>' + "\n"
        }

        str += '</ul>' + "\n"

        str += '</div>' + "\n"

        str += '<script type="text/javascript">'
        str += '$(function() {'
        str += '$(".noLink").click(function () { return false; });'
        str += '});'
        str += '</script>'

        out << str
    }
    /**
     * Crea un menú para el flow SEMPLADES
     * @param items los items a ubicar en el menú
     * @param links indica si crear o no links (crea a menos que este parámetro sea: "false", false, "0", 0)
     */
    def menuSemplades = { attrs ->
        def str = ""

        def actual = attrs.actual

        def items = attrs.items

        def links = (attrs.links == "false" || attrs.links == false || attrs.links == "0" || attrs.links == 0) ? false : true

        str += '<div class="" style="padding: 5px 3px 5px 3px;">' + "\n"

        str += '<ul id="menu">' + "\n"

        items.each { item ->

            def paramsLink = (item.link).clone()

            def clase = ((actual == item.evento) ? "current" : "")

            str += '<li>' + "\n"
            if (links && clase != "current") {
                str += '<a href="' + g.createLink(paramsLink) + '" class="' + clase + '" >' + "\n"
            } else {
                str += '<a href="#" class="noLink ' + clase + '" style="cursor: default;" >' + "\n"
            }
            str += item.text + "\n"
            str += '</a>' + "\n"
            str += '</li>' + "\n"
        }

        str += '</ul>' + "\n"

        str += '</div>' + "\n"

        str += '<script type="text/javascript">'
        str += '$(function() {'
        str += '$(".noLink").click(function () { return false; });'
        str += '});'
        str += '</script>'

        out << str
    }
}
