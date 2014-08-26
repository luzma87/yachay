<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 13/08/14
  Time: 04:23 PM
--%>

<%@ page import="app.Aprobacion" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Imprimir solicitudes</title>
        <style type="text/css">
        @page {
            size   : 29.7cm 21cm ;  /*width height */
            margin : 2cm;
        }

        .hoja {
            /*background  : #fedcba;*/
            height      : 16.5cm;
            width       : 24.7cm; /*29.7-(1.5*2)*/
            font-family : arial;
            font-size   : 12pt;
            font-size   : 12pt;
        }

        .titulo {
            width : 15.5cm;
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

        .ttl {
            text-align  : center;
            font-weight : bold;
        }

        .tabla {
            border-collapse : collapse;
            font-size       : 10pt;
        }

        td, th {
            padding : 5px;
        }

        </style>
    </head>

    <body>
        <div class="hoja">
            <slc:headerReporte title="Lista de Solicitudes de contratación"/>

            <div class="divTabla">
                <table style="width: 100%;" border="1" class="tabla">
                    <thead>
                        <tr>
                            <th>Unidad Ejecutora</th>
                            <th>Actividad</th>
                            <th>Fecha</th>
                            <th>Monto Solicitado</th>
                            <th>Modalidad contratación</th>
                            <th>Nombre del proceso</th>
                            <th>Plazo de ejecución</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${solicitudInstanceList}" status="i" var="solicitudInstance">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                <td>
                                    ${solicitudInstance.unidadEjecutora?.nombre}
                                </td>
                                <td>${solicitudInstance.actividad?.objeto}</td>
                                <td>${solicitudInstance.fecha?.format('dd-MM-yyyy')}</td>
                                <td><g:formatNumber number="${solicitudInstance.montoSolicitado}" type="currency"/></td>
                                <td>${solicitudInstance.tipoContrato?.descripcion}</td>
                                <td>${solicitudInstance.nombreProceso}</td>
                                <td><g:formatNumber number="${solicitudInstance.plazoEjecucion}" maxFractionDigits="0"/> días</td>
                                <td>
                                    ${solicitudInstance.aprobacion?.descripcion}
                                </td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>