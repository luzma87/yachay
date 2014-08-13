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
        <title>Revisar solicitud</title>

        <style type="text/css">
        textarea {
            width : 780px;
        }
        </style>

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
                <a href="#" id="btnSave" class="button" style="float: right;">Guardar</a>
            </div> <!-- toolbar -->

            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message ui-state-highlight ui-corner-all">${flash.message}</div>
                </g:if>
                <table width="100%" class="ui-widget-content ui-corner-all">
                    <thead>
                        <tr>
                            <td class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
                                Detalles de la solicitud
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
                </table>

                <g:form action="saveRevision" name="frmRevision" id="${solicitud.id}">
                    <table width="100%" class="ui-widget-content ui-corner-all">
                        <thead>
                            <tr>
                                <td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
                                    Gerencia Administrativa Financiera
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <td style="width: 98px;" class="label">Observaciones</td>
                            <td style="width: 785px;">
                                <g:if test="${perfil.codigo == 'GAF' || debug.gaf}">
                                    <g:textArea name="observacionesAdministrativaFinanciera" class="ui-widget-content ui-corner-all"
                                                rows="5" cols="5" value="${solicitud.observacionesAdministrativaFinanciera}"/>
                                </g:if>
                                <g:else>
                                    ${solicitud.observacionesAdministrativaFinanciera ?: '- Sin observaciones-'}
                                </g:else>
                            </td>
                            <td style="width: 127px;">
                                <g:if test="${perfil.codigo == 'GAF' || debug.gaf}">
                                    <label class="label" for="gaf">Revisado
                                        <input type="checkbox" name="gaf" id="gaf" ${solicitud.revisadoAdministrativaFinanciera ? 'checked' : ''}/>
                                    </label>
                                </g:if>
                                <g:else>
                                    ${solicitud.revisadoAdministrativaFinanciera ?
                                            'Revisado el ' + solicitud.revisadoAdministrativaFinanciera.format('dd-MM-yyyy') :
                                            'No revisado'}
                                </g:else>
                            </td>
                        </tbody>
                    </table>

                    <table width="100%" class="ui-widget-content ui-corner-all">
                        <thead>
                            <tr>
                                <td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
                                    Gerencia Jurídica
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <td style="width: 98px;" class="label">Observaciones</td>
                            <td style="width: 785px;">
                                <g:if test="${perfil.codigo == 'GJ' || debug.gj}">
                                    <g:textArea name="observacionesJuridica" class="ui-widget-content ui-corner-all"
                                                rows="5" cols="5" value="${solicitud.observacionesJuridica}"/>
                                </g:if>
                                <g:else>
                                    ${solicitud.observacionesJuridica ?: '- Sin observaciones-'}
                                </g:else>
                            </td>
                            <td style="width: 127px;">
                                <g:if test="${perfil.codigo == 'GJ' || debug.gj}">
                                    <label class="label" for="gj">Revisado
                                        <input type="checkbox" name="gj" id="gj" ${solicitud.revisadoJuridica ? 'checked' : ''}/>
                                    </label>
                                </g:if>
                                <g:else>
                                    ${solicitud.revisadoJuridica ?
                                            'Revisado el ' + solicitud.revisadoJuridica.format('dd-MM-yyyy') :
                                            'No revisado'}
                                </g:else>
                            </td>
                        </tbody>
                    </table>

                    <table width="100%" class="ui-widget-content ui-corner-all">
                        <thead>
                            <tr>
                                <td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
                                    Gerencia de Dirección de Proyectos
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <td class="label" style="width: 98px;">Observaciones</td>
                            <td style="width: 785px;">
                                <g:if test="${perfil.codigo == 'GDP' || debug.gdp}">
                                    <g:textArea name="observacionesDireccionProyectos" class="ui-widget-content ui-corner-all"
                                                rows="5" cols="5" value="${solicitud.observacionesDireccionProyectos}"/>
                                </g:if>
                                <g:else>
                                    ${solicitud.observacionesDireccionProyectos ?: '- Sin observaciones-'}
                                </g:else>
                            </td>
                            <td style="width: 127px;">
                                <g:if test="${perfil.codigo == 'GDP' || debug.gdp}">
                                    <label class="label" for="gdp">Revisado
                                        <input type="checkbox" name="gdp" id="gdp" ${solicitud.revisadoDireccionProyectos ? 'checked' : ''}/>
                                    </label>
                                </g:if>
                                <g:else>
                                    ${solicitud.revisadoDireccionProyectos ?
                                            'Revisado el ' + solicitud.revisadoDireccionProyectos.format('dd-MM-yyyy') :
                                            'No revisado'}
                                </g:else>
                            </td>
                        </tbody>
                    </table>
                </g:form>
            </div> <!-- body -->
        </div> <!-- dialog -->

        <script type="text/javascript">
            $(function () {
                $(".button").button();

                $("#btnSave").button("option", "icons", {primary : 'ui-icon-disk'}).click(function () {
                    $("#frmRevision").submit();
                });
                $(".list").button("option", "icons", {primary : 'ui-icon-clipboard'});
                $(".create").button("option", "icons", {primary : 'ui-icon-document'});
                $(".revision").button("option", "icons", {primary : 'ui-icon-check'});

            });
        </script>
    </body>
</html>