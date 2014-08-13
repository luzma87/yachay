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
                                    <table style="width: 1030px;">
                                        <tr>
                                            <td class="label">Unidad requirente</td>
                                            <td colspan="3">
                                                ${solicitud.unidadEjecutora?.nombre}
                                            </td>

                                            <td class="label">Proyecto</td>
                                            <td colspan="3">
                                                ${solicitud.actividad?.proyecto?.nombre}
                                            </td>
                                        </tr>

                                        <tr>
                                            <td class="label">Componente</td>
                                            <td colspan="3" id="tdComponente">
                                                ${solicitud.actividad?.marcoLogico?.objeto}
                                            </td>

                                            <td class="label">Actividad</td>
                                            <td colspan="3" id="tdActividad">
                                                ${solicitud.actividad?.objeto}
                                            </td>
                                        </tr>

                                        <tr>
                                            <td class="label">Nombre del proceso</td>
                                            <td colspan="3">
                                                ${solicitud.nombreProceso}
                                            </td>

                                            <td class="label">Forma de pago</td>
                                            <td>
                                                ${solicitud.formaPago?.descripcion}
                                            </td>

                                            <td class="label">Plazo de ejecución</td>
                                            <td>
                                                ${solicitud.plazoEjecucion} días
                                            </td>
                                        </tr>

                                        <tr>
                                            <td class="label">Fecha</td>
                                            <td colspan="3">
                                                ${solicitud.fecha?.format("dd-MM-yyyy")}
                                            </td>

                                            <td class="label">Monto solicitado</td>
                                            <td>
                                                <g:formatNumber number="${solicitud.montoSolicitado}" type="currency"/>
                                            </td>

                                            <td class="label">Modalidad de contratación</td>
                                            <td>
                                                ${solicitud.tipoContrato?.descripcion}
                                            </td>
                                        </tr>

                                        <tr>
                                            <td class="label">Objeto del contrato</td>
                                            <td colspan="7">
                                                ${solicitud.objetoContrato}
                                            </td>
                                        </tr>

                                        <tr>
                                            <td class="label">Observaciones</td>
                                            <td colspan="7">
                                                ${solicitud.observaciones}
                                            </td>
                                        </tr>

                                        <tr>
                                            <td class="label">Archivo (pdf)</td>
                                            <td colspan="7">
                                                <g:link action="downloadFile" id="${solicitud.id}">
                                                    ${solicitud.pathPdfTdr}
                                                </g:link>
                                            </td>
                                        </tr>
                                    </table>
                                </g:if>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="4" class="buttons" style="text-align: right;">
                                <g:link class="button edit" action="ingreso" id="${solicitud?.id}">
                                    <g:message code="default.button.update.label" default="Edit"/>
                                </g:link>
                                <g:link class="button revision" action="revision" id="${solicitud?.id}">
                                    Revisar
                                </g:link>
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