<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 21/10/14
  Time: 03:10 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Solicitud de reforma al POA</title>
        <style type="text/css">
        @page {
            size   : 29.7cm 21cm;  /*width height */
            margin : 2cm;
        }

        .hoja {
            width : 23.7cm;
        }

        .hoja {
            /*background  : #e6e6fa;*/
            /*height      : 24.7cm; *//*29.7-(1.5*2)*/
            font-family : arial;
            font-size   : 9pt;
        }

        table {
            font-size : 9pt;
        }

        .titulo {
            min-height    : 20px;
            font-size     : 16pt;
            /*font-weight   : bold;*/
            text-align    : center;
            margin-bottom : 5px;
            width         : 100%;
            border-bottom : solid 1px #000000;
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

        .odd {
            background : none repeat scroll 0 0 #E1F1F7;
        }

        .even {
            background : none repeat scroll 0 0 #F5F5F5;
        }

        ol {
            counter-reset : item;
            padding       : 0;
        }

        ol li {
            display       : block;
            margin-bottom : 15px;
        }

        ol li:before {
            content           : counter(item) ". ";
            counter-increment : item;
            font-weight       : bold;
        }

        .table {
            /*border-collapse : collapse;*/
        }

        .center {
            text-align : center;
        }

        .right {
            text-align : right;
        }

        .justificacion {
            /*border     : solid 1px #000000;*/
            margin-top : 5px;
            padding    : 10px;
        }

        .firma {
            margin-top  : 2cm;
            margin-left : 10cm;
        }
        </style>
    </head>

    <body>
        <div style="margin-left: 10px;">
            <div class="hoja">
                <div class="titulo">SOLICITUD DE REFORMA AL POA</div>

                <div>
                    <ol>
                        <li>
                            <strong>Unidad responsable:</strong> Gerencia de Urbanismo y Arquitectura
                        </li>
                        <li>
                            <strong>Tipo de reforma:</strong>
                            <table class="table " border="1">
                                <thead>
                                    <tr>
                                        <th>Detalle</th>
                                        <th>(Marcar X)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Reforma entre actividades por reasignación de recursos o saldos</td>
                                        <td class="center"></td>
                                    </tr>
                                    <tr>
                                        <td>Reforma por creación de una nueva actividad</td>
                                        <td class="center">X</td>
                                    </tr>
                                    <tr>
                                        <td>Reforma por eliminación de una nueva actividad</td>
                                        <td class="center"></td>
                                    </tr>
                                    <tr>
                                        <td>Reforma por cambio de nombre</td>
                                        <td class="center"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </li>
                        <li>
                            <strong>Matriz de la reforma:</strong>
                            <table class="table " border="1">
                                <thead>
                                    <tr>
                                        <th>Proyecto</th>
                                        <th>Componente</th>
                                        <th>Número de la actividad del POA</th>
                                        <th>Nueva actividad (Marcar con X)</th>
                                        <th>Nombre de la actividad</th>
                                        <th>Grupo de gasto</th>
                                        <th>Valor inicial USD</th>
                                        <th>Disminución USD</th>
                                        <th>Aumento USD</th>
                                        <th>Valor final USD</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Universidad de Investigación de la Tecnología Experimentakl "Yachay"</td>
                                        <td>Comunicación y Difusión</td>
                                        <td>114</td>
                                        <td></td>
                                        <td>Taller de Sensibilización de Bienes Patrimoniales y Arqueológicos</td>
                                        <td>73</td>
                                        <td class="right">1.050,79</td>
                                        <td class="right">340,00</td>
                                        <td class="right">0,00</td>
                                        <td class="right">710,79</td>
                                    </tr>
                                    <tr>
                                        <td>Universidad de Investigación de la Tecnología Experimentakl "Yachay"</td>
                                        <td>Equipamiento y operación</td>
                                        <td>NUEVA</td>
                                        <td>X</td>
                                        <td>Adquisición de Suministros y Materiales de la Gerencia de Urbanismo y Arquitectura</td>
                                        <td>73</td>
                                        <td class="right">0,00</td>
                                        <td class="right">0,00</td>
                                        <td class="right">340,00</td>
                                        <td class="right">340,00</td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <td colspan="5"></td>
                                    <th>TOTAL</th>
                                    <th class="right">1.050,79</th>
                                    <th class="right">340,00</th>
                                    <th class="right">340,00</th>
                                    <th class="right">1.050,79</th>
                                </tfoot>
                            </table>
                        </li>
                        <li>
                            <strong>Justificación de la reforma al POA solicitada:</strong>

                            <div class="justificacion">
                                Se solicitó la presente reforma al POA para incluir la actividad
                                "<strong>Adquisición de Suministros y Materiales de la Gerencia de Urbanismo y Arquitectura</strong>" y
                            poder cancelar al proveedor el monto solicitado.
                            </div>
                        </li>
                    </ol>

                    <div class="firma center">
                        <div>
                            <strong>f)</strong><span class="spanFirma">_____________________________________</span>
                        </div>

                        <div>
                            Nicolás Carcelén E.
                        </div>

                        <div>
                            <strong>GERENTE DE URBANISMO Y ARQUITECTURA</strong>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>