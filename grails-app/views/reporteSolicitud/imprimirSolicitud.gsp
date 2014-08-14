<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 13/08/14
  Time: 04:23 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Imprimir solicitud</title>
        <style type="text/css">
        @page {
            size   : 21cm 29.7cm;  /*width height */
            margin : 2cm;
        }

        .hoja {
            width     : 15cm;
            font-size : 12pt;
        }

        .titulo {
            width : 15.5cm;
        }

        .hoja {
            /*background  : #fedcba;*/
            height      : 24.7cm; /*29.7-(1.5*2)*/
            font-family : arial;
            font-size   : 12pt;
        }

        .titulo {
            height        : 130px;
            font-size     : 12pt;
            /*font-weight   : bold;*/
            text-align    : center;
            margin-bottom : 5px;
            width         : 95%;
        }

        .totales {
            font-weight : bold;
        }

        .num {
            text-align : right;
        }

        .header {
            background : #333333 !important;
            color      : #AAAAAA;
        }

        .total {
            background : #000000 !important;
            color      : #FFFFFF !important;
        }

        th {
            background : #cccccc;
        }

        tbody tr:nth-child(2n+1) {
            background : none repeat scroll 0 0 #E1F1F7;
        }

        tbody tr:nth-child(2n) {
            background : none repeat scroll 0 0 #F5F5F5;
        }

        .label {
            font-weight : bold;
        }

        .ui-widget-header {
            font-weight : bold;
        }
        </style>
    </head>

    <body>
        <div class="hoja">
            <table style="width: 100%;">
                <tr>
                    <td class="label">Unidad requirente</td>
                    <td colspan="5">
                        ${solicitud.unidadEjecutora?.nombre}
                    </td>
                </tr>

                <tr>
                    <td class="label">Proyecto</td>
                    <td colspan="5">
                        ${solicitud.actividad?.proyecto?.nombre}
                    </td>
                </tr>

                <tr>
                    <td class="label">Componente</td>
                    <td colspan="5">
                        ${solicitud.actividad?.marcoLogico?.objeto}
                    </td>
                </tr>

                <tr>
                    <td class="label">Actividad</td>
                    <td colspan="5">
                        ${solicitud.actividad?.objeto}
                    </td>
                </tr>

                <tr>
                    <td class="label">Nombre del proceso</td>
                    <td colspan="5">
                        ${solicitud.nombreProceso}
                    </td>
                </tr>

                <tr>
                    <td class="label">Forma de pago</td>
                    <td>
                        ${solicitud.formaPago?.descripcion}
                    </td>
                </tr>

                <tr>
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
                </tr>

                <tr>
                    <td class="label">Monto solicitado</td>
                    <td>
                        <g:formatNumber number="${solicitud.montoSolicitado}" type="currency"/>
                    </td>
                </tr>

                <tr>
                    <td class="label">Modalidad de contratación</td>
                    <td colspan="3">
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
                        ${solicitud.pathPdfTdr}
                    </td>
                </tr>
            </table>

            <table width="100%" class="ui-widget-content ui-corner-all">
                <thead>
                    <tr>
                        <td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
                            Gerencia Administrativa Financiera (${solicitud.revisadoAdministrativaFinanciera ?
                                    'Revisado el ' + solicitud.revisadoAdministrativaFinanciera.format('dd-MM-yyyy') :
                                    'No revisado'})
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <td style="width: 98px;" class="label">Observaciones:</td>
                    <td style="width: 785px;">
                        ${solicitud.observacionesAdministrativaFinanciera ?: '- Sin observaciones-'}
                    </td>
                </tbody>
            </table>

            <table width="100%" class="ui-widget-content ui-corner-all">
                <thead>
                    <tr>
                        <td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
                            Gerencia Jurídica ( ${solicitud.revisadoJuridica ?
                                    'Revisado el ' + solicitud.revisadoJuridica.format('dd-MM-yyyy') :
                                    'No revisado'})
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <td style="width: 98px;" class="label">Observaciones:</td>
                    <td style="width: 785px;">
                        ${solicitud.observacionesJuridica ?: '- Sin observaciones-'}
                    </td>
                </tbody>
            </table>

            <table width="100%" class="ui-widget-content ui-corner-all">
                <thead>
                    <tr>
                        <td colspan="3" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
                            Gerencia de Dirección de Proyectos ( ${solicitud.revisadoDireccionProyectos ?
                                    'Revisado el ' + solicitud.revisadoDireccionProyectos.format('dd-MM-yyyy') :
                                    'No revisado'})
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <td class="label" style="width: 98px;">Observaciones:</td>
                    <td style="width: 785px;">
                        ${solicitud.observacionesDireccionProyectos ?: '- Sin observaciones-'}
                    </td>
                </tbody>
            </table>
        </div>
    </body>
</html>