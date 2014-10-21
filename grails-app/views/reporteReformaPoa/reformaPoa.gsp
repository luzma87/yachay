<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 21/10/14
  Time: 03:10 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Reforma de POA</title>
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
            height      : 24.7cm; /*29.7-(1.5*2)*/
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
            border-collapse : collapse;
        }

        .center {
            text-align : center;
        }

        .right {
            text-align : right;
        }

        .justificacion {
            border     : solid 1px #000000;
            margin-top : 5px;
            padding    : 10px;
        }

        .firma {
            margin-left : 4cm;
            float       : left;
        }

        .negro {
            background : #000000;
            color      : #f5f5f5;
        }

        .numeracion {
            height : 35px;
        }

        .fright {
            float : right;
        }

        .firmas {
            margin-top  : 2cm;
            width      : 100%;
            height     : 3cm;
        }
        </style>
    </head>

    <body>
        <div style="margin-left: 10px;">
            <div class="hoja">
                <div class="titulo">REFORMA DE POA</div>

                <div class="numeracion">
                    <table class="table fright" border="0" cellpadding="5" cellspacing="5">
                        <tr>
                            <th class="negro">Form. No. 4</th>
                            <th>Numeración:</th>
                            <th>Ref. 2014-GP</th>
                            <th>No. 006</th>
                        </tr>
                    </table>
                </div>

                <div>
                    <ol>
                        <li>
                            <strong>Unidad responsable (Gerencia-Dirección):</strong> Gerencia de Urbanismo y Arquitectura
                        </li>
                        <li>
                            <strong>Matriz de la reforma POA:</strong>
                            <table class="table " border="1">
                                <thead>
                                    <tr>
                                        <th class="negro">Proyecto</th>
                                        <th class="negro">Componente</th>
                                        <th class="negro">Número de la actividad del POA</th>
                                        <th class="negro">Nueva actividad (Marcar con X)</th>
                                        <th class="negro">Nombre de la actividad</th>
                                        <th class="negro">Grupo de gasto</th>
                                        <th class="negro">Valor inicial USD</th>
                                        <th class="negro">Disminución USD</th>
                                        <th class="negro">Aumento USD</th>
                                        <th class="negro">Valor final USD</th>
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
                                    <th colspan="5"></th>
                                    <th>TOTAL</th>
                                    <th class="right">1.050,79</th>
                                    <th class="right">340,00</th>
                                    <th class="right">340,00</th>
                                    <th class="right">1.050,79</th>
                                </tfoot>
                            </table>
                        </li>
                    </ol>

                    <strong>Observación:</strong>
                    <ul>
                        <li>
                            Solicitud de reforma adjunta al Memorando No. YACHAY-GAUA-20114-0037-M, de 31 de enero de 2014
                        </li>
                        <li>
                            La reforma corresponde al tipo "Creación de nueva actividad" y "Reasignación de recursos y saldos"
                        </li>
                    </ul>

                    <div>
                        <strong>Elaborado por:</strong> RC
                    </div>

                    <div class="fright">
                        <strong>FECHA:</strong> 31/01/2014
                    </div>

                    <div class="firmas">
                        <div class="firma center">
                            <div style="text-align: left; margin-bottom: 1.5cm">
                                <strong>Revisado por:</strong>
                            </div>

                            <div>
                                <strong>f)</strong><span class="spanFirma">_____________________________________</span>
                            </div>

                            <div>
                                Edison Torres
                            </div>

                            <div>
                                <strong>Director de Planificación</strong>
                            </div>
                        </div>

                        <div class="firma center">
                            <div style="text-align: left; margin-bottom: 1.5cm">
                                <strong>Aprobado por:</strong>
                            </div>

                            <div>
                                <strong>f)</strong><span class="spanFirma">_____________________________________</span>
                            </div>

                            <div>
                                Rocío Gavilanes
                            </div>

                            <div>
                                <strong>Gerente de Planificación</strong>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>