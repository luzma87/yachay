<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 13/08/14
  Time: 04:23 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Imprimir acta</title>
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

        .numeracion {
            float      : right;
            background : #bbb;
            padding    : 7px;
        }

        .tbl {
            border-collapse : collapse;
        }

        .tbl th {
            background  : #bbb;
            font-weight : bold;
            text-align  : left;
            width       : 5cm;
        }

        .tbl td, .tbl th {
            padding-left  : 5px;
            padding-right : 5px;
        }

        .bold {
            font-weight : bold;
        }
        </style>
    </head>

    <body>
        <div class="hoja">
            <slc:headerReporte title="Solicitud de aval de POA"/>

            <div style="width: 100%; height: 1.5cm;">
                <div class="numeracion">
                    Numeración:
                </div>
            </div>

            <div class="texto">
                Con el propósito de ejecutar las actividades programadas en la planificación operativa institucional
                ${solicitud.proceso.fechaInicio?.format("yyyy")}, la Gerencia de "Área de Fomento Académico e Investigación"
                solicita emitir el Aval de POA correspondiente a la actividad que se detalla a continuación:
            </div>

            <div class="tabla">
                <table width="100%" border="1" class="tbl">
                    <tr>
                        <th>
                            Unidad requirente: (Gerencia - Dirección)
                        </th>
                        <td colspan="2">
                            ?Unidad?
                        </td>
                    </tr>

                    <tr>
                        <th>
                            Proyecto:
                        </th>
                        <td colspan="2">
                            ${solicitud.proceso.proyecto.nombre}
                        </td>
                    </tr>

                    <tr>
                        <th>
                            Componente:
                        </th>
                        <td colspan="2">
                            ?Componente?
                        </td>
                    </tr>

                    <tr>
                        <th>
                            Nombre del proceso:
                        </th>
                        <td colspan="2">
                            ${solicitud.proceso.nombre}
                        </td>
                    </tr>

                    <tr>
                        <th>
                            Objeto de la actividad:
                        </th>
                        <td colspan="2">
                            ?objeto actividad?
                        </td>
                    </tr>

                    <tr>
                        <th>
                            Valor total del proceso:
                        </th>
                        <td colspan="2" class="bold">
                            <g:formatNumber number="${solicitud.proceso.monto}" type="currency"/>
                        </td>
                    </tr>

                    <tr>
                        <th>
                            Actividad:
                        </th>
                        <td colspan="2">
                            ?actividad?
                        </td>
                    </tr>

                    <tr>
                        <th>
                            Partida:
                        </th>
                        <td colspan="2">
                            ?partida?
                        </td>
                    </tr>

                    <tr>
                        <th>
                            &nbsp;
                        </th>
                        <td class="bold">
                            Total Plurianual
                        </td>
                    </tr>

                    <tr>
                        <th>
                            Año:
                        </th>
                        <td class="bold">
                            ${solicitud.proceso.fechaInicio?.format("yyyy")}
                        </td>
                        <td>
                            ?Valor?
                        </td>
                    </tr>

                    <tr>
                        <th>
                            Fecha de inicio:
                        </th>
                        <th colspan="2">
                            <tdn:fechaLetras fecha="${solicitud.proceso.fechaInicio}"/>
                        </th>
                    </tr>

                    <tr>
                        <th>
                            Fecha fin actividad:
                        </th>
                        <th colspan="2">
                            <tdn:fechaLetras fecha="${solicitud.proceso.fechaFin}"/>
                        </th>
                    </tr>
                </table>
            </div>

            <div class="texto">
                <p>
                    <strong>Nota Técnica:</strong> El monto solicitado incluye el Impuesto al Valor Agegado IVA 12%.
                </p>

                <p>
                    <strong>FECHA:</strong>
                </p>
            </div>

        </div>
    </body>
</html>