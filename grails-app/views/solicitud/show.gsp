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
        <title>Solicitud</title>
    </head>

    <body>

        <g:if test="${flash.message}">
            <div class="ui-state-error ui-corner-all">
                <p><span style="float: left; margin-right: .3em;" class="ui-icon ui-icon-alert"></span>
                    <strong>Alerta:</strong>
                    ${flash.message}
                </p>
            </div>
        </g:if>

        <g:if test="${solicitud}">
            <table width="100%">
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
    </body>
</html>