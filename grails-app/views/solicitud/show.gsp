<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 12/08/14
  Time: 01:25 PM
--%>

<%@ page import="yachay.contratacion.DetalleMontoSolicitud; yachay.seguridad.Prfl; yachay.proyectos.Proyecto" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Ver solicitud</title>
    </head>

    <body>

        %{--<h2>${Prfl.get(session.perfil.id).nombre} ${Prfl.get(session.perfil.id).codigo} ${solicitud.estado}</h2>--}%

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
                                <td colspan="4" style="font-size: larger; font-weight: bold;">
                                    <g:if test="${yachay.contratacion.DetalleMontoSolicitud.countBySolicitud(solicitud) == 0}">
                                        <div class="ui-widget-content ui-corner-all ui-state-error" style="padding: 10px 10px 10px 10px; ">
                                            No ha detallado los montos anuales de la solicitud
                                        </div>
                                    </g:if>
                                    <g:else>
                                        <g:if test="${solicitud.fechaParaRevision}">
                                            <div style="padding: 10px 10px 5px 10px; ">
                                                Solicitud marcada para revisión
                                            </div>
                                        </g:if>
                                    </g:else>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4" style="padding: 10px 10px 15px 10px; font-size: larger; font-weight: bold;">
                                    <g:if test="${solicitud.incluirReunion == 'S'}">
                                        Se incluirá en la próxima reunión de aprobación
                                        (Solicitado el ${solicitud.fechaPeticionReunion?.format("dd-MM-yyyy HH:mm")})
                                    </g:if>
                                    <g:else>
                                        No se incluirá en la próxima reunión de aprobación
                                    </g:else>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <slc:showSolicitud solicitud="${solicitud}" perfil="${session.perfil}"/>
                                </td>
                            </tr>
                        </g:if>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="4" class="buttons" style="text-align: right;">
                                <g:if test="${solicitud.estado == 'P'}">
                                    <g:if test="${session.perfil.codigo == 'RQ' || session.perfil.codigo == 'DRRQ'}">
                                        <g:link class="button edit" action="ingreso" id="${solicitud?.id}">
                                            Actualizar
                                        </g:link>
                                        <g:if test="${session.perfil.codigo == 'DRRQ' && DetalleMontoSolicitud.countBySolicitud(solicitud) > 0 && !solicitud.fechaParaRevision}">
                                            <g:link class="button paraRevision" action="paraRevision" id="${solicitud?.id}">
                                                Para revisión
                                            </g:link>
                                        </g:if>
                                    </g:if>
                                %{--<g:if test="${session.perfil.codigo == 'GAF' || session.perfil.codigo == 'GJ'/* || session.perfil.codigo == 'GDP'*/}">--}%
                                    <g:if test="${session.perfil.codigo == 'ASAF' || session.perfil.codigo == 'ASGJ'/* || session.perfil.codigo == 'GDP'*/}">
                                        <g:link class="button revision" action="revision" id="${solicitud?.id}">
                                            Revisar
                                        </g:link>
                                    </g:if>
                                    <g:if test="${solicitud.revisadoAdministrativaFinanciera}">
                                        <g:if test="${session.perfil.codigo == 'GAF'}">
                                            <g:link class="button revision" action="revision" id="${solicitud?.id}">
                                                Validar
                                            </g:link>
                                        </g:if>
                                    </g:if>
                                    <g:if test="${solicitud.revisadoJuridica}">
                                        <g:if test="${session.perfil.codigo == 'GJ'}">
                                            <g:link class="button revision" action="revision" id="${solicitud?.id}">
                                                Validar
                                            </g:link>
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
                $(".paraRevision").button("option", "icons", {primary : 'ui-icon-check'}).click(function () {
                    if (confirm("Está seguro de querer marcar esta solicitud de contratación para revisión?")) {
                        return true;
                    }
                    return false;
                });
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