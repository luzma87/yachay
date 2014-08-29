<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 9/16/11
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Aval</title>

        <style type="text/css">
        @page {
            size   : 21cm 29.7cm;  /*width height */
            margin : 2cm;
        }

        .hoja {
            width : 15cm;
        }

        .titulo {
            width : 15.5cm;
        }

        .hoja {
            /*background  : #e6e6fa;*/
            height      : 24.7cm; /*29.7-(1.5*2)*/
            font-family : arial;
            font-size   : 12pt;
        }

        .titulo {
            height        : 40px;
            font-size     : 12pt;
            /*font-weight   : bold;*/
            text-align    : center;
            margin-bottom : 5px;
            width         : 100%;
            border-bottom : 1px solid black;
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

        tr {
            margin : 0px;
        }

        td {
            border  : 1px solid #000000;
            margin  : 0px;
            padding : 5px;
        }

        table {
            border-collapse : collapse;
            border-spacing  : 0px 0px;
        }
        </style>

    </head>

    <body>
        <div class="hoja">

            <div class="titulo" style="">
                <p><b>AVAL DE POA</b></p>

            </div>

            <div style="float: right;font-size: 12pt;">
                <p>Numeración: ${anio}-GP No. ${sol.numero}</p>
            </div>

            <div style="text-align: justify;float: left;font-size: 12pt;">
                <p>
                    Con solicitud de aval de POA Nro. ${sol.memo}, con fecha ${sol.fecha.format("dd-MM-yyyy")}, la Gerencia de Planificación solicita un aval para realizar la actividad "${sol.proceso.nombre}",
                    por un monto total de <g:formatNumber number="${sol.monto}" format="###,##0" minFractionDigits="2" maxFractionDigits="2"></g:formatNumber>$, con base en cual informo lo siguiente:
                </p>

                <p>
                    Luego de revisar el Plan Operativo Anual ${anio}, la Gerencia de Planificación emite el aval a la actividad conforme el siguiente detalle:
                </p>
                <table style="width: 100%">
                    <tbody>
                        <tr>
                            <td></td>
                            <td style="font-weight: bold;text-align: center">DETALLE</td>
                        </tr>
                        <tr>
                            <td style="font-weight: bold">Proyecto</td>
                            <td>${sol.proceso.proyecto}</td>
                        </tr>
                        <tr>
                            <td style="font-weight: bold">Proceso</td>
                            <td>${sol.proceso.nombre}</td>
                        </tr>
                        <g:each in="${app.yachai.ProcesoAsignacion.findAllByProceso(sol.proceso)}" var="pa">
                            <tr>
                                <td style="font-weight: bold">Unidad responsable</td>
                                <td>${pa.asignacion.unidad}</td>
                            </tr>

                            <tr>
                                <td style="font-weight: bold">Componente</td>
                                <td>${pa.asignacion.marcoLogico.marcoLogico}</td>
                            </tr>
                            <tr>
                                <td style="font-weight: bold">Número de la actividad</td>
                                <td>${pa.asignacion.marcoLogico.numero} (${anio})</td>
                            </tr>
                            <tr>
                                <td style="font-weight: bold">Código</td>
                                <td>${pa.proceso.proyecto.codigoEsigef} ${pa.asignacion.marcoLogico.marcoLogico.numero} ${pa.asignacion.presupuesto.numero}</td>
                            </tr>
                            <tr>
                                <td style="font-weight: bold">Actividad</td>
                                <td>${pa.asignacion.marcoLogico}</td>
                            </tr>
                            <tr>
                                <td style="font-weight: bold">Monto solicitado</td>
                                <td><g:formatNumber number="${pa.monto}" format="###,##0" minFractionDigits="2" maxFractionDigits="2"></g:formatNumber></td>
                            </tr>
                        </g:each>

                    %{--<g:if test="${anterior}">--}%
                    %{--<tr>--}%
                    %{--<td style="font-weight: bold">Valor ${anio.toInteger()-1}</td>--}%
                    %{--<td>${anterior.monto} (Fuente: 998)</td>--}%
                    %{--</tr>--}%
                    %{--</g:if>--}%
                        <tr>
                            <td style="font-weight: bold">Total</td>
                            <td>
                                <g:formatNumber number="${sol.monto + ((anterior) ? anterior?.monto : 0)}" format="###,##0" minFractionDigits="2" maxFractionDigits="2"></g:formatNumber>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p style="border: 1px solid black;padding: 5px;font-size: 8px;text-align: left">
                    <b style="text-decoration: underline">OBSERVACIONES:</b><br/>
                    ${sol.observaciones}
                </p>

                <p>
                    Es importante señalar que la Gerencia Administrativa Financiera en el marco de sus competencias verificará la disponibilidad presupuestaria.
                </p>
            </div>


            <div style="width:6cm; margin-top: 100px;float: left;font-size: 12pt;text-align: center; border-top: solid, 1px #000000;">
                <p>${sol.firma2.persona.nombre} ${sol.firma2.persona.apellido}</p>

                %{--<p>--}%
                %{--<b>Director de Planificación</b>--}%
                %{--</p>--}%
            </div>

            <div style="width:6cm;margin-top: 100px;float: left;font-size: 12pt;margin-left: 3cm;text-align: center;border-top: solid, 1px #000000;">
                <p>${sol.firma3.persona.nombre} ${sol.firma3.persona.apellido}</p>

                %{--<p>--}%
                %{--<b>Gerente de Planificación</b>--}%
                %{--</p>--}%
            </div>

        </div>

    </body>
</html>