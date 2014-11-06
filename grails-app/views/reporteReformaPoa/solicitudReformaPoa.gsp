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
        margin-left : 5cm;
    }
    .valor{
        text-align: right;
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
    <strong>Unidad responsable:</strong>${sol.usuario.unidad}
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
            <td class="center">
                ${sol.tipo=='R'?'X':''}
            </td>
        </tr>
        <tr>
            <td>Reforma por creación de una nueva actividad</td>
            <td class="center">
                ${sol.tipo=='N'?'X':''}
            </td>
        </tr>
        <tr>
            <td>Reforma por creación de una actividad derivada</td>
            <td class="center">
                ${sol.tipo=='D'?'X':''}
            </td>
        </tr>
        <tr>
            <td>Reforma por Incremento</td>
            <td class="center">
                ${sol.tipo=='A'?'X':''}
            </td>
        </tr>

        </tbody>
    </table>
</li>
<li>
    <strong>Matriz de la reforma:</strong>
    <g:set var="ti" value="${0}"></g:set>
    <g:set var="tvi" value="${0}"></g:set>
    <g:set var="tvf" value="${0}"></g:set>
    <g:set var="tf" value="${0}"></g:set>
    <table style="width:100%;" class="table " border="1">
        <thead>
        <tr>
            <th>Proyecto</th>
            <th>Componente</th>
            <th>No</th>
            <th>Actividad</th>
            <th>
                Grupo de <br/>
                gasto
            </th>
            <th>Valor inicial</th>
            <th>Disminución</th>
            <th>Aumento</th>
            <th>Valor final</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${sol.origen.marcoLogico.proyecto}</td>
            <td>${sol.origen.marcoLogico.marcoLogico}</td>
            <td>${sol.fecha.format("yyyy")+'-'+sol.origen.marcoLogico.marcoLogico.numero}</td>
            <td>${sol.origen.marcoLogico}</td>
            <td>${sol.origen.presupuesto.numero}</td>
            <g:if test="${sol.tipo!='A'}">
                <td class="valor">
                    <g:formatNumber number="${sol.valorOrigenSolicitado}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                    <g:set var="ti" value="${ti+sol.valorOrigenSolicitado}"></g:set>
                </td>
                <g:if test="${sol.tipo!='E'}">
                    <td class="valor"> <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
                    <g:set var="tvi" value="${tvi+sol.valor}"></g:set>
                </g:if>
                <g:else>
                    <td class="valor"> <g:formatNumber number="${sol.valorOrigenSolicitado}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
                </g:else>
                <td  class="valor">
                    <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                </td>
                <td class="valor">
                    <g:if test="${sol.tipo!='E'}">
                        <g:formatNumber number="${sol.valorOrigenSolicitado-sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                        <g:set var="tf" value="${tvf+(sol.valorOrigenSolicitado-sol.valor)}"></g:set>
                    </g:if>
                    <g:else>
                        <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                    %{--<g:set var="tf" value="${tf}"></g:set>--}%
                    </g:else>
                </td>
            </g:if>
            <g:else>
                <td class="valor">
                    <g:formatNumber number="${sol.valorOrigenSolicitado}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                    <g:set var="ti" value="${ti+sol.valorOrigenSolicitado}"></g:set>
                </td>
                <td class="valor">
                    <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                </td>
                <td class="valor">
                    <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                    <g:set var="tvf" value="${tvf+sol.valor}"></g:set>
                </td>

                <td class="valor">
                    <g:formatNumber number="${sol.valorOrigenSolicitado+sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                    <g:set var="tf" value="${tf+sol.valorOrigenSolicitado}"></g:set>
                </td>
            </g:else>
        </tr>
        <g:if test="${sol.tipo!='A'}">
            <tr>
                <g:if test="${sol.destino}">
                    <td>${sol.destino.marcoLogico.proyecto}</td>
                    <td>${sol.destino.marcoLogico.marcoLogico}</td>
                    <td>${sol.fecha.format("yyyy")+'-'+sol.destino.marcoLogico.marcoLogico.numero}</td>
                    <td>${sol.destino.marcoLogico}</td>
                    <td>${sol.destino.presupuesto.numero}</td>
                    <td class="valor">
                        <g:formatNumber number="${sol.valorDestinoSolicitado}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                        <g:set var="ti" value="${ti+sol.valorDestinoSolicitado}"></g:set>
                    </td>
                    <td class="valor">
                        <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                    </td>
                    <td class="valor">
                        <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                        <g:set var="tvf" value="${tvf+sol.valor}"></g:set>
                    </td>
                    <td class="valor">
                        <g:formatNumber number="${sol.valorDestinoSolicitado+sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                        <g:set var="tf" value="${tf+(sol.valorDestinoSolicitado+sol.valor)}"></g:set>
                    </td>
                </g:if>
                <g:else>
                    <g:if test="${sol.tipo=='N'}">
                        <td>${sol.origen.marcoLogico.proyecto}</td>
                        <td>${sol.origen.marcoLogico.marcoLogico}</td>
                        <td>${sol.fecha.format("yyyy")+'-'+sol.origen.marcoLogico.marcoLogico.numero}</td>
                        <td>${sol.actividad}</td>
                        <td>${sol.presupuesto.numero}</td>
                        <td class="valor">
                            <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                        </td>
                        <td class="valor">
                            <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                        </td>
                        <td class="valor">
                            <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                            <g:set var="tvf" value="${tvf+sol.valor}"></g:set>
                        </td>
                        <td class="valor">
                            <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                        </td>
                        <g:set var="tf" value="${tf+sol.valor}"></g:set>
                    </g:if>
                    <g:else>
                        <td>${sol.origen.marcoLogico.proyecto}</td>
                        <td>${sol.origen.marcoLogico.marcoLogico}</td>
                        <td>${sol.fecha.format("yyyy")+'-'+sol.origen.marcoLogico.marcoLogico.numero}</td>
                        <td>${sol.origen.marcoLogico}</td>
                        <td>${sol.origen.presupuesto.numero}</td>
                        <td class="valor">
                            <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                        </td>
                        <td class="valor">
                            <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                        </td>
                        <td class="valor">
                            <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                            <g:set var="tvf" value="${tvf+sol.valor}"></g:set>
                        </td>
                        <td class="valor">
                            <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                            <g:set var="tf" value="${tf+sol.valor}"></g:set>
                        </td>
                    </g:else>
                </g:else>

            </tr>
        </g:if>
        <g:else>
            <tr>
                <td colspan="5">
                    Redistribución de fondos
                </td>
                <td class="valor">
                    <g:set var="ti" value="${ti+sol.valor}"></g:set>
                    <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                </td>
                <td class="valor">
                    <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                    <g:set var="tvi" value="${tvi+sol.valor}"></g:set>
                </td>
                <td class="valor">
                    <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                </td>
                <td class="valor">
                    <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                    <g:set var="tf" value="${tf+sol.valor}"></g:set>
                </td>
            </tr>
        </g:else>
        %{--<g:else>--}%
        %{--<g:set var="ti" value="${0}"></g:set>--}%
        %{--<g:set var="tvi" value="${0}"></g:set>--}%
        %{--<g:set var="tvf" value="${0}"></g:set>--}%
        %{--<g:set var="tf" value="${0}"></g:set>--}%
        %{--</g:else>--}%
        <tr>
            <td colspan="4"></td>
            <td style="font-weight: bold">TOTAL</td>
            <td style="font-weight: bold" class="valor"> <g:formatNumber number="${ti}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
            <td  style="font-weight: bold" class="valor"> <g:formatNumber number="${tvi}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
            <td style="font-weight: bold" class="valor"> <g:formatNumber number="${tvf}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
            <td style="font-weight: bold" class="valor"> <g:formatNumber number="${tf}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
        </tr>
        </tbody>
    </table>
</li>
<li>
    <strong>Justificación de la reforma al POA solicitada:</strong>

    <div class="justificacion">
        ${sol.concepto}
    </div>
</li>
</ol>

<div class="firma ">
    <div>
        <span class="spanFirma">
            <g:if test="${sol.firmaSol.estado=='F'}">
                <img src="${resource(dir: 'firmas',file: sol.firmaSol.path)}"/><br/>
                ${sol.firmaSol.usuario.persona.nombre} ${sol.firmaSol.usuario.persona.apellido}<br/>
                ${sol.firmaSol.usuario.cargoPersonal?.toString()?.toUpperCase()}<br/>
                ${sol.firmaSol.fecha.format("dd-MM-yyyy hh:mm")}
            </g:if>
        </span>
    </div>

    %{--<div style="margin-top: 15px">--}%
    %{--${sol.usuario.persona}--}%
    %{--</div>--}%

</div>
</div>
</div>
</div>
</body>
</html>