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
    .valor{
        text-align: right;
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
            %{--<th class="negro">Form. No. ${sol.id}</th>--}%
            <th>Numeración:</th>
            <th>Ref. ${sol.fecha.format("yyyy")}-GP</th>
            <th>No. 00${sol.id}</th>
        </tr>
    </table>
</div>

<div>
<ol>
    <li>
        <strong>Unidad responsable (Gerencia-Dirección):</strong> ${sol.usuario.unidad}
    </li>
    <li>
        <strong>Matriz de la reforma POA:</strong>
        <g:set var="ti" value="${0}"></g:set>
        <g:set var="tvi" value="${0}"></g:set>
        <g:set var="tvf" value="${0}"></g:set>
        <g:set var="tf" value="${0}"></g:set>
        <table style="width:100%;margin-top: 15px" class="table " border="1">
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
                <th>Aumneto</th>
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
                <td class="valor">
                    <g:formatNumber number="${sol.valorOrigen}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                    <g:set var="ti" value="${ti+sol.valorOrigen}"></g:set>
                </td>
                <g:if test="${sol.tipo!='E'}">
                    <td class="valor"> <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
                    <g:set var="tvi" value="${tvi+sol.valor}"></g:set>
                </g:if>
                <g:else>
                    <td class="valor"> <g:formatNumber number="${sol.valorOrigen}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
                </g:else>
                <td  class="valor">
                    <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                </td>
                <td class="valor">
                    <g:if test="${sol.tipo!='E'}">
                        <g:formatNumber number="${sol.valorOrigen-sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                        <g:set var="tf" value="${tvf+(sol.valorOrigen-sol.valor)}"></g:set>
                    </g:if>
                    <g:else>
                        <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                    %{--<g:set var="tf" value="${tf}"></g:set>--}%
                    </g:else>
                </td>
            </tr>
            <g:if test="${sol.tipo!='E'}">
                <tr>
                    <g:if test="${sol.destino}">
                        <td>${sol.destino.marcoLogico.proyecto}</td>
                        <td>${sol.destino.marcoLogico.marcoLogico}</td>
                        <td>${sol.fecha.format("yyyy")+'-'+sol.destino.marcoLogico.marcoLogico.numero}</td>
                        <td>${sol.destino.marcoLogico}</td>
                        <td>${sol.destino.presupuesto.numero}</td>
                        <td class="valor">
                            <g:formatNumber number="${sol.valorDestino}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                            <g:set var="ti" value="${ti+sol.valorDestino}"></g:set>
                        </td>
                        <td class="valor">
                            <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                        </td>
                        <td class="valor">
                            <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                            <g:set var="tvf" value="${tvf+sol.valor}"></g:set>
                        </td>
                        <td class="valor">
                            <g:formatNumber number="${sol.valorDestino+sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                            <g:set var="tf" value="${tf+(sol.valorDestino+sol.valor)}"></g:set>
                        </td>
                    </g:if>
                    <g:else>
                        <g:if test="${sol.tipo=='N'}">
                            <td>${sol.origen.marcoLogico.proyecto}</td>
                            <td>${sol.origen.marcoLogico.marcoLogico}</td>
                            <td>${sol.fecha.format("yyyy")+'-'+sol.origen.marcoLogico.marcoLogico.numero}</td>
                            <td>${sol.marcoLogico}</td>
                            <td>${sol.presupuesto.numero}</td>
                            <td class="valor">
                                <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                            </td>
                            <td class="valor">
                                <g:formatNumber number="${0}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                            </td>
                            <td class="valor">
                                <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                            </td>
                            <td class="valor">
                                <g:formatNumber number="${sol.valor}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                                <g:set var="tvf" value="${tvf+sol.valor}"></g:set>
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
            %{--<g:else>--}%
            %{--<g:set var="ti" value="${0}"></g:set>--}%
            %{--<g:set var="tvi" value="${0}"></g:set>--}%
            %{--<g:set var="tvf" value="${0}"></g:set>--}%
            %{--<g:set var="tf" value="${0}"></g:set>--}%
            %{--</g:else>--}%
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td style="font-weight: bold">TOTAL</td>
                <td style="font-weight: bold" class="valor"> <g:formatNumber number="${ti}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
                <td  style="font-weight: bold" class="valor"> <g:formatNumber number="${tvi}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
                <td style="font-weight: bold" class="valor"> <g:formatNumber number="${tvf}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
                <td style="font-weight: bold" class="valor"> <g:formatNumber number="${tf}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
            </tr>
            </tbody>
        </table>
    </li>
</ol>
<g:if test="${sol.observaciones}">
    <strong>Observación:</strong>
    <ul>
        ${sol.observaciones}
    </ul>
</g:if>
<div>
    <strong>Elaborado por:</strong> ${sol.revisor?.sigla}
</div>

<div class="fright">
    <strong>FECHA:</strong> ${sol.fechaRevision?.format("dd-MM-yyyy")}
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
            ${director?.persona}
        </div>

        <div>
            <strong>DIRECTOR DE PLANIFICACIÓN</strong>
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
            ${gerente?.persona}
        </div>

        <div>
            <strong>GERENTE DE PLANIFICACIÓN</strong>
        </div>
    </div>
</div>
</div>
</div>
</div>
</body>
</html>