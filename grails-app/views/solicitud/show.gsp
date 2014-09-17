<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 12/08/14
  Time: 01:25 PM
--%>

<%@ page import="app.seguridad.Prfl; app.Proyecto" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Ver solicitud</title>
    </head>

    <body>

        %{--<h2>${Prfl.get(session.perfil.id).nombre}</h2>--}%

        <div class="dialog" title="${title}">
            <div id="" class="toolbar ui-widget-header ui-corner-all">
                <g:link class="button list" action="list">
                    Lista de solicitudes
                </g:link>
                <g:link class="button create" action="ingreso">
                    Nueva solicitud
                </g:link>

                <a href="#" id="btnPrint" class="button" style="float: right;">Imprimir</a>
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
                        <g:if test="${solicitud}">
                            <tr>
                                <td colspan="4" style="padding-bottom: 15px; font-size: larger; font-weight: bold;">
                                    <g:if test="${solicitud.incluirReunion == 'S'}">
                                        Se incluirá en la próxima reunión de aprobación
                                        (Solicitado el ${solicitud.fechaPeticionReunion.format("dd-MM-yyyy HH:mm")})
                                    </g:if>
                                    <g:else>
                                        No se incluirá en la próxima reunión de aprobación
                                    </g:else>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <slc:showSolicitud solicitud="${solicitud}"/>
                                </td>
                            </tr>
                        </g:if>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="4" class="buttons" style="text-align: right;">
                                <g:if test="${solicitud.estado == 'P'}">
                                    <g:if test="${session.perfil.codigo == 'RQ'}">
                                        <g:link class="button edit" action="ingreso" id="${solicitud?.id}">
                                            <g:message code="default.button.update.label" default="Edit"/>
                                        </g:link>
                                    </g:if>
                                %{--<g:if test="${session.perfil.codigo == 'GAF' || session.perfil.codigo == 'GJ'/* || session.perfil.codigo == 'GDP'*/}">--}%
                                    <g:if test="${session.perfil.codigo == 'ASAF' || session.perfil.codigo == 'ASGJ'/* || session.perfil.codigo == 'GDP'*/}">
                                        <g:link class="button revision" action="revision" id="${solicitud?.id}">
                                            Revisar
                                        </g:link>
                                    </g:if>
                                    <g:if test="${solicitud.revisadoAdministrativaFinanciera && solicitud.revisadoJuridica}">
                                        <g:if test="${session.perfil.codigo == 'GAF' || session.perfil.codigo == 'GJ'}">
                                            <g:link class="button revision" action="revision" id="${solicitud?.id}">
                                                Validar
                                            </g:link>
                                        </g:if>
                                        <g:if test="${session.perfil.codigo == 'GP'}">
                                            <g:if test="${solicitud.incluirReunion == 'S'}">
                                                <g:link class="button aprobacion" action="aprobacion" id="${solicitud?.id}">
                                                    Reunión de aprobación
                                                </g:link>
                                            </g:if>
                                        </g:if>
                                    </g:if>
                                </g:if>
                                <g:elseif test="${solicitud.estado == 'A'}">
                                    <g:if test="${session.perfil.codigo == 'GP'}">
                                        <g:link class="button aprobacion" action="aprobacion" id="${solicitud?.id}">
                                            Ver/Modificar aprobación
                                        </g:link>
                                    </g:if>
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
                $("#btnPrint").button("option", "icons", {primary : 'ui-icon-print'}).click(function () {
                    var url = "${createLink(controller: 'reporteSolicitud', action: 'imprimirSolicitud')}/?id=${solicitud.id}";
//                    console.log(url);
                    location.href = "${createLink(controller:'pdf',action:'pdfLink')}?url=" + url + "&filename=solicitud.pdf";
                    return false;
                });

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