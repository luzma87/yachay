<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 6/22/11
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="yachay.proyectos.PoliticasProyecto; yachay.parametros.proyectos.Politica" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'jquery.validate.min.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'additional-methods.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'messages_es.js')}"></script>

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.min.js')}"></script>

        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.css')}"/>
        <link rel="stylesheet" href="${resource(dir: 'css/menuSemplades', file: 'flow_menu_green.css')}"/>

        <title>
            Objetivos Estratégicos Inst.. - ${proyecto.nombre.size() > 30 ? proyecto.nombre[0..28]+"..." : proyecto.nombre}

        </title>
    </head>

    <body>

        <div class="dialog" title="Pol&iacute;ticas del proyecto ${proyecto.nombre}">

            <g:form class="frmPoliticas" method="post" action="nuevoProyecto" event="savePoliticas">
                <input type="hidden" name="goto" id="goto" value="politicasAgenda" />
                <div class="ui-widget-content ui-corner-all" style="padding: 3px;">

                    <mf:menuSemplades_flow items='${items}' links="${links}"/>

                    <table>
                        <thead>
                            <tr style="padding: 5px;">
                                <th style="padding: 5px;" class="ui-widget-header ui-corner-tl">
                                    <input type="checkbox" name="selAll" id="selAll"/>
                                </th>
                                <th style="padding: 5px;" class="ui-widget-header ui-corner-tr">
                                    Objetivos Estratégicos Institucionales
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${Politica.list()}" status="i" var="pol">
                                <g:set var="contiene"
                                       value="${(politicasProyecto.contains(pol.id.toLong())) ||(politicasProyecto.contains(pol.id.toInteger()))}"/>
                                <tr class="${i % 2 == 0 ? 'even' : 'odd'} ${contiene ? 'ui-state-highlight' : ''}">
                                    <td style="text-align: center;">
                                        <input type="checkbox" name="politica" class="sel" value="${pol.id}"
                                               id="sel_${pol.id}" ${(contiene) ? "checked" : ""}/>
                                    </td>
                                    <td>
                                        ${pol.descripcion}
                                    </td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                    <br/>

                    <div class="botones">
                        <div class="botones right">
                            <a href="#" class="button saveOnly" title="Guardar los cambios">
                                Guardar
                            </a>
                            <a href="#" class="button back"
                                    title="A polítas de agenda social">
                                Atr&aacute;s
                            </a>
                            <a href="#" class="button continue" title="Terminar">
                                Terminar
                            </a>
                        </div>

                        <div class="botones left">
                            <g:link action="nuevoProyecto" event="salir" class="button salir">Salir</g:link>
                        </div>
                    </div>

                </div>
            </g:form>

        </div>


        <script type="text/javascript">
            $(function () {


                var myForm = $(".frmPoliticas");

                $(".button").button();
                $(".saveOnly").button("option", "icons", {primary:'ui-icon-disk'}).click(function () {
                    $("#goto").val("politicasProyecto");
                    myForm.submit();
                    return false;
                });
                $(".continue").button("option", "icons", {secondary:'ui-icon-check'}).click(function () {
                    $("#goto").val("salir");
                    myForm.submit();
                    return false;
                });
                $(".back").button("option", "icons", {primary:'ui-icon-arrowthick-1-w'}).click(function () {
                    $("#goto").val("politicasAgenda");
                    myForm.submit();
                    return false;
                });
                $(".salir").button("option", "icons", {primary:'ui-icon-arrowreturnthick-1-w'}).click(function () {
                    if (confirm("Si sale perderá los cambios no guardados. Continuar?")) {
                        return true;
                    } else {
                        return false;
                    }
                });


                var clase = "ui-state-highlight";

                $("#selAll").click(function () {
                    $(".sel").attr("checked", $("#selAll").is(":checked"));
                    if ($("#selAll").is(":checked")) {
                        $(".sel").parent().parent().addClass(clase);
                    } else {
                        $(".sel").parent().parent().removeClass(clase);
                    }
                });

                $(".sel").click(function () {
                    var sel = true;
                    $(".sel").each(function () {
                        if ($(this).is(":checked")) {
                            $(this).parent().parent().addClass(clase);
                            sel = sel & true;
                        } else {
                            $(this).parent().parent().removeClass(clase);
                            sel = sel & false;
                        }
                    });
                    if (sel) {
                        $("#selAll").attr("checked", true);
                    } else {
                        $("#selAll").attr("checked", false);
                    }
                });
            });
        </script>

    </body>
</html>