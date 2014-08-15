<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 12/08/14
  Time: 01:25 PM
--%>

<%@ page import="app.Proyecto" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Ver solicitud</title>
    </head>

    <body>

        <div class="dialog" title="${title}">
            <div id="" class="toolbar ui-widget-header ui-corner-all">
                <g:link class="button list" action="list">
                    Lista de solicitudes
                </g:link>
                <g:link class="button create" action="ingreso">
                    Nueva solicitud
                </g:link>
            </div> <!-- toolbar -->

            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message ui-state-highlight ui-corner-all">${flash.message}</div>
                </g:if>
                <table width="100%" class="ui-widget-content ui-corner-all">
                    <thead>
                        <tr>
                            <td class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
                                <g:message code="default.show.legend" args="${['Solicitud']}"
                                           default="Show Politica details"/>
                            </td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <g:if test="${solicitud}">
                                    <slc:showSolicitud solicitud="${solicitud}"/>
                                </g:if>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="4" class="buttons" style="text-align: right;">
                                <g:if test="${solicitud.estado == 'P'}">
                                    <g:link class="button edit" action="ingreso" id="${solicitud?.id}">
                                        <g:message code="default.button.update.label" default="Edit"/>
                                    </g:link>

                                    <g:link class="button revision" action="revision" id="${solicitud?.id}">
                                        Revisar
                                    </g:link>

                                    <g:if test="${solicitud.revisadoAdministrativaFinanciera &&
                                            solicitud.revisadoDireccionProyectos &&
                                            solicitud.revisadoJuridica}">
                                        <g:link class="button aprobacion" action="aprobacion" id="${solicitud?.id}">
                                            Reunión de aprobación
                                        </g:link>
                                    </g:if>
                                </g:if>
                                <g:elseif test="${solicitud.estado == 'A'}">
                                    <g:link class="button aprobacion" action="aprobacion" id="${solicitud?.id}">
                                        Ver/Modificar aprobación
                                    </g:link>
                                    <g:link class="button aprobacion" action="aprobacion" id="${solicitud?.id}">
                                        Archivar acta
                                    </g:link>
                                </g:elseif>
                                %{--<g:link class="button delete" action="delete" id="${politicaInstance?.id}">--}%
                                %{--<g:message code="default.button.delete.label" default="Delete"/>--}%
                                %{--</g:link>--}%
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div> <!-- body -->
        </div> <!-- dialog -->

        <script type="text/javascript">
            $(function () {
                $(".button").button();
                $(".home").button("option", "icons", {primary : 'ui-icon-home'});
                $(".list").button("option", "icons", {primary : 'ui-icon-clipboard'});
                $(".create").button("option", "icons", {primary : 'ui-icon-document'});

                $(".edit").button("option", "icons", {primary : 'ui-icon-pencil'});
                $(".revision").button("option", "icons", {primary : 'ui-icon-check'});
                $(".delete").button("option", "icons", {primary : 'ui-icon-trash'}).click(function () {
                    if (confirm("${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}")) {
                        return true;
                    }
                    return false;
                });
            });
        </script>
    </body>
</html>