<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Cronograma</title>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}"
          type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}"
          type="text/css"/>

    <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript"
            language="JavaScript"></script>
    <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}"
            type="text/javascript" language="JavaScript"></script>

    <style type="text/css">
    .col {
        width : 60px
    }

    .colGrande {
        width : 220px
    }

    .componente {
        /*background : rgba(248, 220, 86, 0.2) !important;*/

    }

    .num {
        text-align : center;
        width      : 60px;
        border     : 0px;
    }

    .disabled {
        background : #ddd;
        width      : 60px;
        text-align : center;
    }

    tbody tr:nth-child(even) {
        background : none !important;
    }

    tbody tr:nth-child(odd) {
        background : none !important;
    }
    </style>
</head>

<body>
<div class="body" style="font-size: 11px">

<div class="breadCrumbHolder module">
    <div id="breadCrumb" class="breadCrumb module">
        <ul>
            <li>
                <g:link class="bc" controller="proyecto" action="show"
                        id="${proyecto.id}">
                    Proyecto
                </g:link>
            </li>
            <li>
                <g:link class="bc" controller="cronograma" action="verCronograma" id="${proyecto.id}">
                    Cronograma
                </g:link>
            </li>
            <li>
                Edici&oacute;n
            </li>
        </ul>
    </div>
</div>

<div class="dialog">
<g:if test="${!actSel}">
    <a href="#" id="calc" class="btn" style="margin: 10px">Generar asignaciones del proyecto</a>
</g:if>
<g:else>
    <a href="${g.createLink(controller: 'marcoLogico',action: 'actividadesComponente',id: actSel.marcoLogico.id)}"  class="btn" style="margin: 10px">Regresar</a>
</g:else>
<g:link controller="asignacion" action="asignacionProyectov2" id="${proyecto.id}" class="btn">Asignaciones</g:link>
<table style="width: 1300px;margin-left: -20px;margin-top: 10px" border="1">
%{--<thead style="background: rgba(110, 182, 213,0.2)">--}%
%{--<thead style="background: #595292">--}%
<thead style="background: #d0d0d0">
<tr>
    <th style="width: 220px">&nbsp;</th>
    <g:form action="nuevoCronograma" method="post" class="frm_anio">
        <input type="hidden" name="id" value="${proyecto.id}">
        <input type="hidden" name="act" value="${actSel?.id}">
        <g:each in="${totAnios}" var="ta">
            <input type="hidden" id="fuente_${ta.key}" value="${ta.value}">
        </g:each>
        <th colspan="16">
            <g:select from="${yachay.parametros.poaPac.Anio.list()}" optionKey="id" optionValue="anio"
                      name="anio" id="anio" value="${anio.id}"
                      style="width: 80px"/>
        </th>
    </g:form>
</tr>

<tr>
    <th class="colGrande" style="width: 220px;">Componentes/Rubros</th>
    <g:each in="${yachay.parametros.poaPac.Mes.list()}" var="mes">
        <th class="col" style="width: 60px;">${(mes.descripcion.size()>5)?mes.descripcion .substring(0,5)+".":mes.descripcion}</th>
    </g:each>
    <th style="width: 90px;">Asignado <br>${anio.anio}</th>
    %{--<th style="width: 90px;">Asignado <br> otros años</th>--}%
    <th style="width: 90px;">Sin<br> asignar</th>
    <th style="width: 90px;">Monto</th>
</tr>
</thead>
<tbody>
<g:set var="indice" value="0"></g:set>
<g:set var="totProy" value="0"></g:set>
<g:set var="totProyAsig" value="0"></g:set>
<g:set var="totalMetas" value="0"></g:set>
<g:set var="totalMetasCronograma" value="${0}"></g:set>
<g:each in="${componentes}" var="comp" status="j">
    <g:set var="totComp" value="0"></g:set>
    <g:set var="totCompAsig" value="0"></g:set>
%{--<g:set var="totOtroAnioComp" value="0"></g:set>--}%
    <tr>
        <td class="colGrande componente" style="background: ${colores[indice.toInteger()]}"
            colspan="17"><b>Componente ${j + 1}</b>: ${(comp.objeto.length() > 100) ? comp.objeto.substring(0, 100) + "..." : comp.objeto}
        </td>
    </tr>
    <g:each in="${yachay.proyectos.MarcoLogico.findAllByMarcoLogicoAndEstado(comp,0,[sort:'id'])}" var="act" status="i">
        <g:if test="${!actSel}">
            <g:set var="monto" value="${act.monto}"></g:set>
            <g:set var="totComp" value="${totComp.toDouble()+monto}"></g:set>
        %{--<g:set var="totOtrosAnios" value="0"></g:set>--}%
            <tr>

                <td class="colGrande" style="background: ${colores[indice.toInteger()]};width: 220px;font-weight: bold" title="${act.responsable} - ${act.objeto}">
                    ${(act.objeto.length() > 100) ? act.objeto.substring(0, 100) + "..." : act.objeto}
                </td>
                <g:set var="tot" value="0"></g:set>
                <g:set var="totAct" value="${monto}"></g:set>
                <g:each in="${yachay.parametros.poaPac.Mes.list()}" var="mes" status="k">
                    <g:set var="crga" value='${yachay.proyectos.Cronograma.findAllByMarcoLogicoAndMes(act,mes)}'></g:set>
                    <g:if test="${crga.size()>0}">
                        <g:each in="${crga}" var="c">
                            <g:if test="${c?.anio==anio && c?.cronograma == null}">

                            %{--paso ${" "+c.anio+" "+k+" mes "+mes+" mrlg  "+act.id}   <br>--}%
                                <g:set var="crg" value='${c}'></g:set>
                                <g:set var="totCompAsig" value="${totCompAsig.toDouble()+crg.valor+crg.valor2}"></g:set>
                            </g:if>
                        %{--<g:else>--}%
                        %{--<g:if test="${c?.anio!=anio && c?.cronograma == null}">--}%
                        %{--<g:set var="totOtrosAnios" value="${totOtrosAnios.toDouble()+c.valor+c.valor2}"></g:set>--}%

                        %{--</g:if>--}%
                        %{--</g:else>--}%
                        </g:each>
                        <g:if test="${crg}">

                            <g:set var="tot" value="${tot.toDouble()+crg?.valor+crg?.valor2}"></g:set>

                            <g:if test="${true}">
                                <td style="width: 60px">

                                    <input type="text" id="crg_${crg.id}" value='${formatNumber(number:crg.valor+crg.valor2,format:"###,##0",minFractionDigits:2,maxFractionDigits:2)}'
                                           class="num fa_${crg.fuente.id}" mes="${mes.id} " identificador="${crg.id}"
                                           actividad="${act.id}" tot="${monto}"
                                           div="tot_${j}${i}"
                                           mt="${mes.descripcion}" style="width: 60px"
                                           prsp_desc="${crg.presupuesto.descripcion}" prsp="${crg.presupuesto.id}" prsp_num="${crg.presupuesto.numero}"
                                           fuente="${crg.fuente.id}"
                                           prsp2="${crg.presupuesto2?.id}" prsp_num2="${crg.presupuesto2?.numero}" prsp_desc2="${crg.presupuesto2?.descripcion}"
                                           valor1="${formatNumber(number:crg.valor,format:"###,##0",minFractionDigits:2,maxFractionDigits:2)}"
                                           valor2="${formatNumber(number:crg.valor2,format:"###,##0",minFractionDigits:2,maxFractionDigits:2)}" >

                                </td>
                            </g:if>
                            <g:else>
                                <td class="disabled" style="width: 60px">0,00</td>
                            </g:else>
                            <g:set var="crg" value="${null}"></g:set>
                        </g:if>
                        <g:else>
                            <g:if test="${monto.toDouble()>0}">
                                <td style="width: 60px"><input type="text" id="crg_0${j}${i}${k}" value="0,00" class="num"
                                                               mes="${mes.id} " actividad="${act.id}" identificador="0"
                                                               tot="${monto}" div="tot_${j}${i}"
                                                               mt="${mes.descripcion}" style="width: 60px" valor1="0,00" valor2="0,00"></td>
                            </g:if>
                            <g:else>
                                <td class="disabled">0,00</td>
                            </g:else>
                        </g:else>
                    </g:if>
                    <g:else>
                        <g:if test="${monto.toDouble()>0}">
                            <td style="width: 60px">
                                <input type="text" id="crg_0${j}${i}${k}" value="0,00" class="num"
                                       mes="${mes.id} " actividad="${act.id}" identificador="0"
                                       tot="${monto}" div="tot_${j}${i}"
                                       mt="${mes.descripcion}" style="width: 60px" valor1="0,00" valor2="0,00">
                            </td>
                        </g:if>
                        <g:else>
                            <td class="disabled">0,00</td>
                        </g:else>
                    </g:else>
                </g:each>
            %{--<<----------------<<<<<<<< >>>>>>>>>>>>> <br>--}%
                <td class="disabled" id="tot_${j}${i}" div="totComp_${j}">
                    <g:formatNumber number="${tot}"
                                    format="###,##0"
                                    minFractionDigits="2" maxFractionDigits="2"/>
                </td>
                %{--<td class="disabled otroAnio">--}%
                %{--<g:formatNumber number="${totOtrosAnios}"--}%
                %{--format="###,##0"--}%
                %{--minFractionDigits="2" maxFractionDigits="2"/>--}%

                %{--</td>--}%
                %{--<g:set var="totOtroAnioProyecto" value="${totOtroAnioProyecto.toDouble()+totOtrosAnios.toDouble()}"></g:set>--}%
                %{--<g:set var="totOtroAnioComp" value="${totOtroAnioComp.toDouble()+totOtrosAnios.toDouble()}"></g:set>--}%
                <td class="disabled" id="tot_${j}${i}a" div="totComp_${j}a">
                    <g:formatNumber number="${totAct.toDouble() - (tot.toDouble()+0)}"
                                    format="###,##0"
                                    minFractionDigits="2" maxFractionDigits="2"/>

                </td>
                <td class="disabled">
                    <g:formatNumber number="${monto}"
                                    format="###,##0"
                                    minFractionDigits="2" maxFractionDigits="2"/>
                    <g:set var="totalMetas" value="${totalMetas.toDouble()+monto}"></g:set>
                </td>

            </tr>
        </g:if>
        <g:else>
            <g:if test="${actSel.id==act.id}">
                <g:set var="monto" value="${act.monto}"></g:set>
                <g:set var="totComp" value="${totComp.toDouble()+monto}"></g:set>
            %{--<g:set var="totOtrosAnios" value="0"></g:set>--}%
                <tr>

                    <td class="colGrande" style="background: ${colores[indice.toInteger()]};width: 220px;font-weight: bold" title="${act.responsable} - ${act.objeto}">
                        ${(act.objeto.length() > 100) ? act.objeto.substring(0, 100) + "..." : act.objeto}
                    </td>
                    <g:set var="tot" value="0"></g:set>
                    <g:set var="totAct" value="${monto}"></g:set>
                    <g:each in="${yachay.parametros.poaPac.Mes.list()}" var="mes" status="k">
                        <g:set var="crga" value='${yachay.proyectos.Cronograma.findAllByMarcoLogicoAndMes(act,mes)}'></g:set>
                        <g:if test="${crga.size()>0}">
                            <g:each in="${crga}" var="c">
                                <g:if test="${c?.anio==anio && c?.cronograma == null}">

                                %{--paso ${" "+c.anio+" "+k+" mes "+mes+" mrlg  "+act.id}   <br>--}%
                                    <g:set var="crg" value='${c}'></g:set>
                                    <g:set var="totCompAsig" value="${totCompAsig.toDouble()+crg.valor+crg.valor2}"></g:set>
                                </g:if>
                            %{--<g:else>--}%
                            %{--<g:if test="${c?.anio!=anio && c?.cronograma == null}">--}%
                            %{--<g:set var="totOtrosAnios" value="${totOtrosAnios.toDouble()+c.valor+c.valor2}"></g:set>--}%

                            %{--</g:if>--}%
                            %{--</g:else>--}%
                            </g:each>
                            <g:if test="${crg}">

                                <g:set var="tot" value="${tot.toDouble()+crg?.valor+crg?.valor2}"></g:set>

                                <g:if test="${true}">
                                    <td style="width: 60px">

                                        <input type="text" id="crg_${crg.id}" value='${formatNumber(number:crg.valor+crg.valor2,format:"###,##0",minFractionDigits:2,maxFractionDigits:2)}'
                                               class="num fa_${crg.fuente.id}" mes="${mes.id} " identificador="${crg.id}"
                                               actividad="${act.id}" tot="${monto}"
                                               div="tot_${j}${i}"
                                               mt="${mes.descripcion}" style="width: 60px"
                                               prsp_desc="${crg.presupuesto.descripcion}" prsp="${crg.presupuesto.id}" prsp_num="${crg.presupuesto.numero}"
                                               fuente="${crg.fuente.id}"
                                               prsp2="${crg.presupuesto2?.id}" prsp_num2="${crg.presupuesto2?.numero}" prsp_desc2="${crg.presupuesto2?.descripcion}"
                                               valor1="${formatNumber(number:crg.valor,format:"###,##0",minFractionDigits:2,maxFractionDigits:2)}"
                                               valor2="${formatNumber(number:crg.valor2,format:"###,##0",minFractionDigits:2,maxFractionDigits:2)}" >

                                    </td>
                                </g:if>
                                <g:else>
                                    <td class="disabled" style="width: 60px">0,00</td>
                                </g:else>
                                <g:set var="crg" value="${null}"></g:set>
                            </g:if>
                            <g:else>
                                <g:if test="${monto.toDouble()>0}">
                                    <td style="width: 60px"><input type="text" id="crg_0${j}${i}${k}" value="0,00" class="num"
                                                                   mes="${mes.id} " actividad="${act.id}" identificador="0"
                                                                   tot="${monto}" div="tot_${j}${i}"
                                                                   mt="${mes.descripcion}" style="width: 60px" valor1="0,00" valor2="0,00"></td>
                                </g:if>
                                <g:else>
                                    <td class="disabled">0,00</td>
                                </g:else>
                            </g:else>
                        </g:if>
                        <g:else>
                            <g:if test="${monto.toDouble()>0}">
                                <td style="width: 60px">
                                    <input type="text" id="crg_0${j}${i}${k}" value="0,00" class="num"
                                           mes="${mes.id} " actividad="${act.id}" identificador="0"
                                           tot="${monto}" div="tot_${j}${i}"
                                           mt="${mes.descripcion}" style="width: 60px" valor1="0,00" valor2="0,00">
                                </td>
                            </g:if>
                            <g:else>
                                <td class="disabled">0,00</td>
                            </g:else>
                        </g:else>
                    </g:each>
                %{--<<----------------<<<<<<<< >>>>>>>>>>>>> <br>--}%
                    <td class="disabled" id="tot_${j}${i}" div="totComp_${j}">
                        <g:formatNumber number="${tot}"
                                        format="###,##0"
                                        minFractionDigits="2" maxFractionDigits="2"/>
                    </td>
                    %{--<td class="disabled otroAnio">--}%
                    %{--<g:formatNumber number="${totOtrosAnios}"--}%
                    %{--format="###,##0"--}%
                    %{--minFractionDigits="2" maxFractionDigits="2"/>--}%

                    %{--</td>--}%
                    %{--<g:set var="totOtroAnioProyecto" value="${totOtroAnioProyecto.toDouble()+totOtrosAnios.toDouble()}"></g:set>--}%
                    %{--<g:set var="totOtroAnioComp" value="${totOtroAnioComp.toDouble()+totOtrosAnios.toDouble()}"></g:set>--}%
                    <td class="disabled" id="tot_${j}${i}a" div="totComp_${j}a">
                        <g:formatNumber number="${totAct.toDouble() - (tot.toDouble()+0)}"
                                        format="###,##0"
                                        minFractionDigits="2" maxFractionDigits="2"/>

                    </td>
                    <td class="disabled">
                        <g:formatNumber number="${monto}"
                                        format="###,##0"
                                        minFractionDigits="2" maxFractionDigits="2"/>
                        <g:set var="totalMetas" value="${totalMetas.toDouble()+monto}"></g:set>
                    </td>

                </tr>
            </g:if>
        </g:else>

    </g:each>
%{--<<----------------<<<<<<<< <br>--}%
    <tr>
        <td class="colGrande " style="background: ${colores[indice.toInteger()]}" colspan="13"><b>TOTAL</b></td>
        <td style="text-align: center;background: ${colores[indice.toInteger()]}">
            <b><div id="totComp_${j}a" class="totCompsa">
                <g:formatNumber number="${totCompAsig}"
                                format="###,##0"
                                minFractionDigits="2" maxFractionDigits="2"/>
                %{--${totCompAsig.toFloat().round(2)}--}%
            </div></b>
        </td>
        %{--<td style="text-align: center;background: ${colores[indice.toInteger()]}">--}%
        %{--<b>--}%
        %{--<div>--}%
        %{--<g:formatNumber number="${totOtroAnioComp}"--}%
        %{--format="###,##0"--}%
        %{--minFractionDigits="2" maxFractionDigits="2"/>--}%
        %{--${totOtroAnioComp}--}%
        %{--</div>--}%
        %{--</b>--}%
        %{--</td>--}%
        <td style="text-align: center;background: ${colores[indice.toInteger()]}">
            <b>
                <div id="totComp_${j}" class="totComps">
                    <g:formatNumber number="${(totComp.toDouble() - totCompAsig.toDouble())}"
                                    format="###,##0"
                                    minFractionDigits="2" maxFractionDigits="2"/>
                    %{--${(totComp.toDouble() - totCompAsig.toDouble()).toFloat().round(2)}--}%
                </div>
            </b>
        </td>
        <td style="text-align: center;background: ${colores[indice.toInteger()]}">
            <g:formatNumber number="${totalMetas}"
                            format="###,##0"
                            minFractionDigits="2" maxFractionDigits="2"/>

            <g:set var="totalMetasCronograma" value="${totalMetasCronograma.toDouble()+totalMetas}"></g:set>
            <g:set var="totalMetas" value="${0}"></g:set>
        </td>
        <g:set var="totProyAsig"
               value="${totProyAsig.toDouble()+totCompAsig.toDouble()}"></g:set>
        <g:set var="totProy" value="${totProy.toDouble()+totComp.toDouble()}"></g:set>
        <g:set var="indice" value="${indice.toInteger()+1}"></g:set>
        <g:if test="${indice>4}">
            <g:set var="indice" value="${0}"></g:set>
        </g:if>
    </tr>
</g:each>
<tr>
    <td class="colGrande " style="background: #e8e8e8" colspan="13"><b>TOTAL DEL PROYECTO</b>
    </td>
    <td style="text-align: center;background: #e8e8e8">
        <b>
            <div id="totGeneralAsignado">
                <g:formatNumber number="${totProyAsig}"
                                format="###,##0"
                                minFractionDigits="2" maxFractionDigits="2"/>
                %{--${totProyAsig.toFloat().round(2)}--}%
            </div>
        </b>
    </td>
    %{--<td style="text-align: center;background: #E0EEF4">--}%
    %{--<b>--}%
    %{--<g:formatNumber number="${totOtroAnioProyecto}"--}%
    %{--format="###,##0"--}%
    %{--minFractionDigits="2" maxFractionDigits="2"/>--}%
    %{--${totOtroAnioProyecto}--}%
    %{--</b>--}%
    %{--</td>--}%
    <td style="text-align: center;background: #363636">
        <b>
            <div id="totGeneral">
                <g:formatNumber number="${(totProy.toDouble() - (totProyAsig.toDouble()))}"
                                format="###,##0"
                                minFractionDigits="2" maxFractionDigits="2"/>
                %{--${(totProy.toDouble() - (totProyAsig.toDouble()+totOtroAnioProyecto.toDouble())).toFloat().round(2)}--}%
            </div>
        </b>
    </td>
    <td style="text-align: center;background: #E0EEF4">
        <g:formatNumber number="${(totalMetasCronograma)}"
                        format="###,##0"
                        minFractionDigits="2" maxFractionDigits="2"/>
    </td>
</tr>
</tbody>
</table>
</div>
</div>

<div id="dlg_editar" title="Cronograma">
    <input type="hidden" id="mes">
    <input type="hidden" id="iden">
    <input type="hidden" id="act">
    <input type="hidden" id="div">
    <input type="hidden" id="vold">
    <input type="hidden" id="unidad" value="${proyecto.unidadEjecutora.id}">


    <div style="width: 100%;float: left;margin-top: 10px;">
        <div style="width: 110px;float: left;">
            <b>Presupuesto (1):</b>
        </div>
        <input type="text" class="ui-corner-all" id="valor" style="border: 1px solid black;width: 120px;text-align: right">  Formato: 12.563,69
    </div>

    <div style="width: 100%;float: left;margin-top: 10px;">
        <div style="width: 110px;float: left;">
            <b>Partida (1):</b>
        </div>
        <input type="hidden" class="prsp" value="nan" id="prsp">
        <input type="text" id="prsp_desc" desc="desc" style="width: 120px;border: 1px solid black" class="buscar ui-corner-all">
        <div id="desc" style="width: 340px;margin-left: 110px;font-size: 10px;"></div>
    </div>
    <div style="width: 100%;float: left;margin-top: 10px;">
        <div style="width: 110px;float: left;">
            <b>Presupuesto (2):</b>
        </div>
        <input type="text" class="ui-corner-all" id="valor2" style="border: 1px solid black;width: 120px;text-align: right"> Formato: 12.563,69
    </div>

    <div style="width: 100%;float: left;margin-top: 10px;">
        <div style="width: 110px;float: left;">
            <b>Partida (2):</b>
        </div>
        <input type="hidden" class="prsp" value="nan" id="prsp2">
        <input type="text" id="prsp_desc2" desc="desc2" style="width: 120px;border: 1px solid black" class="buscar ui-corner-all">
        <a href="#" id="reset_prsp2">Borrar</a>
        <div id="desc2" style="width: 340px;margin-left: 110px;font-size: 10px;"></div>

    </div>


    <div style="width: 100%;float: left;margin-top: 5px;">
        <div style="width: 110px;float: left">
            <b>Fuente:</b>
        </div>
        <g:select from="${fuentes}" id="fuente" optionKey="id" optionValue="descripcion" style="width: 200px;"/>
    </div>

    <div style="width: 100%;float: left;margin-top: 10px;">
        <div style="width: 110px;float: left">
            <b>Año:</b>
        </div>
        ${anio.anio}
    </div>

    <div style="width: 100%;float: left;margin-top: 10px;">
        <div style="width: 110px;float: left">
            <b>Mes:</b>
        </div>

        <div id="mes_texto"></div>
    </div>

    <div style="width: 100%;float: left;margin-top: 10px;">
        <div style="width: 110px;float: left">
            <b>Monto total:</b>
        </div>

        <div id="mt"></div>
    </div>

    <div style="width: 100%;float: left;margin-top: 10px;">
        <div style="width: 110px;float: left">
            <b>Asignado:</b>
        </div>

        <div id="as"></div>
    </div>

    <div style="width: 100%;float: left;margin-top: 10px;">
        <div style="width: 110px;float: left">
            <b>Por asignar:</b>
        </div>

        <div id="pa"></div>
    </div>
</div>

<div id="buscar">
    <input type="hidden" id="id_txt">
    <input type="hidden" id="id_desc">
    <div>
        Buscar por:
        <select id="tipo">
            <option value="1">Número</option>
            <option value="2">Descripción</option>
        </select>
        <input type="text" id="par" style="width: 160px;"><a href="#" class="btn" id="btn_buscar">Buscar</a>
    </div>

    <div id="resultado" style="width: 450px;margin-top: 10px;" class="ui-corner-all"></div>
</div>
<div id="load">
    <img src="${g.resource(dir:'images',file: 'loading.gif')}" alt="Procesando">
    Procesando
</div>

<script type="text/javascript">
    $(function() {
        $(".btn").button()


        $("#reset_prsp2").button().click(function(){
            $("#prsp2").val("")
            $("#desc2").html("")
            $("#prsp_desc2").val("")
        });

        $(".buscar").click(function() {
            $("#id_txt").val($(this).attr("id"))
            $("#id_desc").val($(this).attr("desc"))
            $("#buscar").dialog("open")
        });
        $("#btn_buscar").click(function() {
            $.ajax({
                type: "POST",
                url: "${createLink(action:'buscarPresupuesto',controller:'asignacion')}",
                data: "parametro=" + $("#par").val() + "&tipo=" + $("#tipo").val(),
                success: function(msg) {
                    $("#resultado").html(msg)
                }
            });
        });
        $("#buscar").dialog({
            title:"Cuentas presupuestarias",
            width:530,
            height:500,
            autoOpen:false,
            modal:true
        })
        $("#breadCrumb").jBreadCrumb({
            beginingElementsToLeaveOpen: 10
        });

        $("#load").dialog({
            width:100,
            height:100,
            position:"center",
            title:"Procesando",
            modal:true,
            autoOpen:false,
            resizable:false,
            open: function(event, ui) {
                $(event.target).parent().find(".ui-dialog-titlebar-close").remove()
            }
        });


        $("#calc").click(function(){
            if(confirm("Está seguro que desea generar las asignaciones del PAI del año señalado? Las asignaciones generadas " +
                    "anteriormente serán ELIMINADAS! así como su PAC y programación.")){
                if(confirm("Los datos borrados no podrán ser recuperados!. Esta acción será registrada en log del sistema junto con su usuario")){
                    $("#load").dialog("open")
                    $.ajax({
                        type: "POST",
                        url: "${createLink(action:'calcularAsignaciones')}",
                        data: {
                            anio:"${anio.id}",
                            proyecto:"${proyecto.id}"
                        },
                        success: function(msg) {
                            if (msg != "no" && msg!="error") {

                                location.href="${createLink(controller: 'asignacion',action: 'asignacionProyectov2',params:[id:proyecto.id,anio:anio.id])}"

                            } else {
                                if(msg=="error"){
                                    $("#load").dialog("close")
                                    alert("Error: No se pueden crear las asignaciones, revice que todas las actividades del proyecto tengan responsables")
                                }else{
                                    $("#load").dialog("close")
                                    alert("Error: No se pudo eliminar todas las asignaciones, revice que no tengan modificaciones o certificaciones")
                                }

                            }
                        }
                    });
                }
            }
        });

        $("#anio").change(function() {
            $(".frm_anio").submit()
        });

        $(".num").click(function() {
            $("#prsp").val("")
            $("#prsp_desc").val("")
            $("#desc").html("")
            $("#prsp").css("border", "black solid 1px")
            $("#valor").css("border", "black solid 1px")
            $("#prsp2").css("border", "black solid 1px")
            $("#valor2").css("border", "black solid 1px")
            $("#mes").val($(this).attr("mes"))
            $("#iden").val($(this).attr("identificador"))
            $("#act").val($(this).attr("actividad"))
            $("#div").val($(this).attr("id"))
            $("#valor").val($(this).attr("valor1"))
            $("#valor2").val($(this).attr("valor2"))
            $("#vold").val($(this).val())
            $("#prsp").val($(this).attr("prsp"))
            $("#prsp2").val($(this).attr("prsp2"))
            $("#desc").html($(this).attr("prsp_desc"))
            $("#prsp_desc").val($(this).attr("prsp_num"))
            $("#desc2").html($(this).attr("prsp_desc2"))
            $("#prsp_desc2").val($(this).attr("prsp_num2"))
            $("#mes_texto").html($(this).attr("mt"))
            $("#fuente").val($(this).attr("fuente"))
            var mt = $(this).attr("tot")
            var as = $("#" + $(this).attr("div")).html()
            as = str_replace(".","",as)
            as = str_replace(",",".",as)
            as=as*1
//            var as1 = $("#" + $(this).attr("div")).parent().find(".otroAnio").html()
//            as1 = str_replace(".","",as1)
//            as1 = str_replace(",",".",as1)
//            as1=as1*1
            as=as+0
            $("#mt").html(number_format(mt, 2, ",", "."))
            $("#as").html(number_format(as, 2, ",", "."))
            $("#pa").html(number_format(mt * 1 - as * 1, 2, ",", "."))
            $("#dlg_editar").dialog("open")
        });

        $("#dlg_editar").dialog({
            width:480,
            height:500,
            position:"center",
            modal:true,
            autoOpen:false,
            resizable:false,
            buttons:{
                "Aceptar":function() {

                    if ($("#valor").val() != "" && $("#valor").val() != " ") {
                        var valor = $("#valor").val()
                        valor = str_replace(".","",valor)
                        valor = str_replace(",",".",valor)
                        valor=valor*1
                        var valor2 = $("#valor2").val()
                        valor2 = str_replace(".","",valor2)
                        valor2 = str_replace(",",".",valor2)
                        valor2=valor2*1
                        var valorPrevio = $("#vold").val()
                        valorPrevio = str_replace(".","",valorPrevio)
                        valorPrevio = str_replace(",",".",valorPrevio)
                        valorPrevio=valorPrevio*1
                        var totalFuente = 0
                        var maxFuente = 0

                        if (valor < 0) {
                            alert("Ingrese numeros positivos")
                            $("#valor").css("border", "red solid 1px")
                            $("#valor").show("pulsate")
                        }
                        if (isNaN(valor)) {
                            alert("Ingrese solo numeros")
                            $("#valor").css("border", "red solid 1px")
                            $("#valor").show("pulsate")
                            valor = -1
                        }
                        if (valor2 < 0) {
                            alert("Ingrese numeros positivos")
                            $("#valor2").css("border", "red solid 1px")
                            $("#valor2").show("pulsate")
                        }
                        if (isNaN(valor2)) {
                            alert("Ingrese solo numeros")
                            $("#valor2").css("border", "red solid 1px")
                            $("#valor2").show("pulsate")
                            valor2 = -1
                        }
                        maxFuente=$("#fuente_"+$("#fuente").val()).val()*1
                        $.each($(".fa_"+$("#fuente").val()),function(){
                            totalFuente+=$(this).val()*1

                        });

                        if((totalFuente+valor*1+valor2*1)>maxFuente){
                            var suma = totalFuente*1+valor*1+valor2*1
                            alert("El total asignado ("+number_format(suma, 2, ",", ".")+") supera el máximo permitido para la fuente ("+number_format(maxFuente, 2, ",", ".")+")")
                            $("#valor").css("border", "red solid 1px")
                            $("#valor").show("pulsate")
                            valor=-1
                        }

                        if($("#prsp").val()*1<1){
                            alert("Seleccione una partida presupuestaria)")
                            $("#prsp").css("border", "red solid 1px")
                            $("#prsp").show("pulsate")
                            valor=-1
                        }
                        if(valor2>0){
                            if($("#prsp2").val()*1<1){
                                alert("Seleccione una partida para el segundo valor presupuestado")
                                $("#prsp2").css("border", "red solid 1px")
                                $("#prsp2").show("pulsate")
                                valor=-1
                            }
                            if($("#prsp2").val()*1==$("#prsp").val()*1){
                                alert("Las dos partidas presupuestarias no pueden ser iguales")
                                $("#prsp2").css("border", "red solid 1px")
                                $("#prsp2").show("pulsate")
                                valor=-1
                            }
                        }

                        if($("#fuente").val()*1<1){
                            valor=-1
                            alert("Seleccione una fuente")
                        }
                        if (valor > -1) {
                            $("#" + $("#div").val()).val($("#valor").val()*1+$("#valor2").val()*1)
                            var tot = 0
                            $.each($("#" + $("#div").val()).parent().parent().children(), function() {

                                var temp = $(this).find(".num").val()
                                temp = str_replace(".","",temp)
                                temp = str_replace(",",".",temp)
                                temp=temp*1
//                                //console.log($(this).find(".num"))

                                if (isNaN(temp))
                                    temp = 0

                                tot += temp
                            });

                            if (tot > $("#" + $("#div").val()).attr("tot") * 1) {
                                alert("Error: Los valores asignados ($" + number_format(tot, 2, ",", ".") + ") superan al monto total de la actividad ($" +number_format($("#" + $("#div").val()).attr("tot")*1, 2, ",", ".")  + ")")
                                $("#" + $("#div").val()).val($("#vold").val())
                            } else {
                                var fin = new Date()
                                fin.addMinutes(20)
                                $('#countdown').countdown('change', {until: fin});
                                var check = new Date()
                                check.addMinutes(15)
                                $('#countdown').countdown('change', {until: check});
                                $("#load").dialog("open")
                                $.ajax({
                                    type: "POST",
                                    url: "${createLink(action:'guardarDatosCronograma')}",
                                    data: {
                                        mes:$("#mes").val(),
                                        anio:$("#anio").val(),
                                        valor:valor,
                                        valor2:valor2,
                                        act:$("#act").val(),
                                        id: $("#iden").val(),
                                        fuente:$("#fuente").val(),
                                        unidad:$("#unidad").val(),
                                        prsp: $("#prsp").val(),
                                        prsp2:$("#prsp2").val(),
                                        totalAnio:tot
                                    },
                                    success: function(msg) {
                                        if (msg != "no") {
                                            var val =$("#valor").val()
                                            val = str_replace(".","",val)
                                            val = str_replace(",",".",val)
                                            val=val*1
                                            var val2 =$("#valor2").val()
                                            val2 = str_replace(".","",val2)
                                            val2 = str_replace(",",".",val2)
                                            val2=val2*1
                                            $("#" + $("#div").val()).attr("valor1",number_format(val, 2, ",", "."))
                                            $("#" + $("#div").val()).attr("valor2",number_format(val2, 2, ",", "."))
                                            /*Seteo los valores de las cuentas de presupuesto seleccionadas*/
                                            $("#" + $("#div").val()).attr("prsp",$("#prsp").val())
                                            $("#" + $("#div").val()).attr("prsp_num",$("#prsp_desc").val())
                                            $("#" + $("#div").val()).attr("prsp_desc",$("#desc").html())
                                            $("#" + $("#div").val()).attr("prsp2",$("#prsp2").val())
                                            $("#" + $("#div").val()).attr("prsp_num2",$("#prsp_desc2").val())
                                            $("#" + $("#div").val()).attr("prsp_desc2",$("#desc2").html())
                                            /*fin presupuesto*/
                                            val=val+val2
                                            $("#" + $("#div").val()).val(number_format(val, 2, ",", "."))
                                            $("#" + $("#div").val()).attr("identificador", msg)
                                            $("#" + $("#div").val()).attr("class", "num fa_"+$("#fuente").val())
                                            var tot = 0
                                            $.each($("#" + $("#div").val()).parent().parent().children(), function() {
                                                var temp = $(this).find(".num").val()
                                                temp = str_replace(".","",temp)
                                                temp = str_replace(",",".",temp)
                                                temp=temp*1
                                                if (isNaN(temp))
                                                    temp = 0
                                                tot += temp
                                            });
                                            var anterior1 = $("#" + $("#" + $("#div").val()).attr("div")).html()
                                            anterior1 = str_replace(".","",anterior1)
                                            anterior1 = str_replace(",",".",anterior1)
                                            anterior1=anterior1*1
                                            var anterior2 = $("#" + $("#" + $("#div").val()).attr("div") + "a").html()
                                            anterior2 = str_replace(".","",anterior2)
                                            anterior2 = str_replace(",",".",anterior2)
                                            anterior2=anterior2*1
                                            var dif = anterior1 - tot
//                                            var otoAn = $("#" + $("#" + $("#div").val()).attr("div") + "a").parent().find(".otroAnio").html()
//                                            otoAn = str_replace(".","",otoAn)
//                                            otoAn = str_replace(",",".",otoAn)
//                                            otoAn=otoAn*1
                                            var sumOtroAnio = tot+0
                                            var tempTot = $("#" + $("#div").val()).attr("tot")*1
                                            var dif2 = anterior2 - (tempTot - sumOtroAnio)
                                            $("#" + $("#" + $("#div").val()).attr("div")).html(number_format(tot, 2, ",", "."))

                                            $("#" + $("#" + $("#div").val()).attr("div") + "a").html(number_format($("#" + $("#div").val()).attr("tot") * 1 - (sumOtroAnio), 2, ",", "."))
                                            if (dif > 0) {

                                                var html = $("#" + $("#" + $("#" + $("#div").val()).attr("div")).attr("div") + "a").html()
                                                html = str_replace(".","",html)
                                                html = str_replace(",",".",html)
                                                html=html*1
                                                $("#" + $("#" + $("#" + $("#div").val()).attr("div")).attr("div") + "a").html(number_format(html + dif, 2, ",", "."))
                                                html = $("#" + $("#" + $("#" + $("#div").val()).attr("div")).attr("div")).html()
                                                html = str_replace(".","",html)
                                                html = str_replace(",",".",html)
                                                html=html*1
                                                $("#" + $("#" + $("#" + $("#div").val()).attr("div")).attr("div")).html(number_format(html + dif2, 2, ",", "."))
                                            } else {
                                                var html = $("#" + $("#" + $("#" + $("#div").val()).attr("div")).attr("div") + "a").html()
                                                html = str_replace(".","",html)
                                                html = str_replace(",",".",html)
                                                html=html*1
                                                $("#" + $("#" + $("#" + $("#div").val()).attr("div")).attr("div") + "a").html(number_format(html - dif, 2, ",", "."))
                                                html = $("#" + $("#" + $("#" + $("#div").val()).attr("div")).attr("div")).html()
                                                html = str_replace(".","",html)
                                                html = str_replace(",",".",html)
                                                html=html*1
                                                $("#" + $("#" + $("#" + $("#div").val()).attr("div")).attr("div")).html(number_format(html - dif2, 2, ",", "."))
                                            }
                                            var total = 0
                                            var totAsignado = 0
                                            $.each($(".totComps"), function() {
                                                ////console.log($(this))
                                                var tmpC =$(this).html()
                                                tmpC = str_replace(".","",tmpC)
                                                tmpC = str_replace(",",".",tmpC)
                                                tmpC=tmpC*1
                                                total += tmpC
                                            });
                                            $.each($(".totCompsa"), function() {
                                                var tmpC =$(this).html()
                                                tmpC = str_replace(".","",tmpC)
                                                tmpC = str_replace(",",".",tmpC)
                                                tmpC=tmpC*1
                                                totAsignado += tmpC
                                            });
                                            $("#totGeneralAsignado").html(number_format(totAsignado, 2, ",", "."))
                                            $("#totGeneral").html(number_format(total, 2, ",", "."))
                                            $("#valor").css("border", "black solid 1px")
                                            $("#load").dialog("close")
                                            $("#dlg_editar").dialog("close")

                                        } else {
                                            $("#" + $("#div").val()).val($("#vold").val())
                                            alert("Error al guardar los datos. Revise los cronogramas de años anteriores y posteriores")
                                        }
                                    }
                                });
                            }
                        }

                    }

                }
            }
        });
    });
</script>
</body>
</html>