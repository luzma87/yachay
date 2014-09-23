<%@ page import="yachay.parametros.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Componente: ${actividad.objeto}</title>

    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}"
          type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}"
          type="text/css"/>

    <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript"
            language="JavaScript"></script>
    <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}"
            type="text/javascript" language="JavaScript"></script>
    <style type="text/css">
    .cmp {
        width      : 215px;
        margin     : 5px;
        min-height : 50px;
        /*border: 1px solid black;*/
        float      : left;
        padding    : 5px;
        position   : relative;

    }

    .cmpDoble {
        width      : 450px;
        margin     : 5px;
        min-height : 50px;
        /*border: 1px solid black;*/
        float      : left;
        padding    : 5px;
        position   : relative;
    }

    .fila {
        width      : 98%;
        margin     : 1%;
        min-height : 50px;
        float      : left;
        /*border: 1px solid black;*/
    }

    .filaMedio {
        width      : 47.5%;
        margin     : 1%;
        min-height : 50px;
        float      : left;
        /*border: 1px solid black;*/
    }

    .todo {
        height : 100%;
    }

    .titulo {
        width         : 90%;
        margin        : 5%;
        margin-top    : 2px;
        height        : 30px;
        line-height   : 30px;
        text-align    : center;
        font-family   : fantasy;
        font-style    : italic;
        font-size     : 11px;
        border-bottom : 1px solid black;

    }

    .filaCombo {
        width         : 90%;
        margin        : 5%;
        margin-top    : -10px;
        height        : 40px;
        line-height   : 40px;
        text-align    : center;
        font-family   : fantasy;
        font-style    : italic;
        border-bottom : 1px solid black;
    }

    .texto {
        width      : 90%;
        min-height : 50px;
        margin     : 5%;
        font-size  : 10px;
        word-wrap  : break-word;
    }

    textarea {
        width      : 93%;
        padding    : 5px;
        min-height : 115px;
        resize     : vertical;
    }

    .agregado {
        width      : 90%;
        margin     : 1.5%;
        text-align : justify;
        padding    : 4px;
        min-height : 20px;
        word-wrap  : break-word;
    }

    .fin {
        background : rgba(145, 192, 95, 0.2)
    }

    .proposito {
        background : rgba(110, 182, 213, 0.2)
    }

    .linea {
        width  : 95%;
        margin : 1.5%;
        height : 1px;
        border : 1px solid black;
    }
    </style>
</head>

<body>
<div class="body">
<div class="dialog">

<div class="breadCrumbHolder module">
    <div id="breadCrumb" class="breadCrumb module">
        <ul>
            <li>
                <g:link class="bc" controller="proyecto" action="show"
                        id="${actividad.proyecto.id}">
                    Proyecto
                </g:link>
            </li>

            <li>
                <g:link class="bc" controller="marcoLogico" action="showMarco"
                        id="${actividad.proyecto.id}">
                    Marco L&oacute;gico
                </g:link>
            </li>

            <li>
                <g:link class="bc" controller="marcoLogico" action="componentes"
                        id="${actividad.proyecto.id}">
                    Componentes
                </g:link>
            </li>

            <li>
                Metas
            </li>
        </ul>
    </div>
</div>

%{--<div id="" class="toolbar ui-widget-header ui-corner-all" style="margin-bottom: 5px;">--}%
%{--<g:link class="btn back" controller="proyecto" action="show" id="${actividad.proyecto.id}">--}%
%{--Proyecto--}%
%{--</g:link>--}%
%{--<g:link class="btn back" controller="marcoLogico" action="showMarco" id="${actividad.proyecto.id}">--}%
%{--Marco L&oacute;gico--}%
%{--</g:link>--}%
%{--<g:link class="btn back" controller="marcoLogico" action="actividads"--}%
%{--id="${actividad.proyecto.id}">--}%
%{--actividads--}%
%{--</g:link>--}%
%{--</div> <!-- toolbar -->--}%


<div style="padding: 5px; margin: 5px;" class="ui-state-error ui-corner-all ui-helper-hidden">
    <p>
        <span style="float: left; margin-right: .3em;" class="ui-icon ui-icon-alert"></span>
        <span id="msgError">
        </span>
    </p>
</div>

<div style="width: 1030px;min-height: 100px;float: left;margin-top: -20px">

    <div class="matriz ui-corner-all campo cmp datos " ml="1" div="rn_${k}"
         identificador="${actividad?.id}" style="margin-left: -10px;" tipo="1">
        <div class="titulo">Actividad</div>

        <div class="texto agregado ui-corner-all fin" ml="1" style="min-height: 40px;"
             id="rn_${k}" identificador="${actividad?.id}" tipo="1">
            ${actividad?.objeto}
        </div>

    </div>
    %{--monto--}%
    %{--<div class="matriz ui-corner-all campo cmp  ">--}%
    %{--<div class="titulo">Monto($)</div>--}%

    %{--<div class="texto agregado ui-corner-all fin varios" ml="5"--}%
    %{--style="height: 40px;line-height: 40px;text-align: center" pref="mn_"--}%
    %{--id="mn_${actividad?.id}" div="mn_${actividad?.id}" identificador="${actividad?.id}" tipo="5">--}%
    %{--<g:formatNumber number="${actividad?.monto.toDouble()}"--}%
    %{--format="###,##0"--}%
    %{--minFractionDigits="2" maxFractionDigits="2"/>--}%
    %{--</div>--}%

    %{--</div>--}%
    %{--fin monto--}%
    %{--indicadores--}%
    <div class="matriz ui-corner-all campo cmp  ">
        <div class="titulo">Indicadores</div>

        <div class="texto indicadores" style=" min-height: 40px;" id="indis">
            <g:each in="${yachay.proyectos.Indicador.findAllByMarcoLogico(actividad)}" var="indicador" status="i">
                <div class="texto indi agregado ui-corner-all fin varios  " pref="ic_"
                     id="ic_${indicador.id}" ml="${actividad.id}" tipo="2" div="ic_${indicador.id}"
                     identificador="${indicador.id}" indicador="${actividad.id}">
                    ${indicador?.descripcion}
                </div>
            </g:each>
        </div>

    </div>
    %{--fin indicadores--}%

    <div class="matriz ui-corner-all campo cmp">
        <div class="titulo">Supuestos</div>

        <div class="texto supuestos" style=" min-height: 40px;" id="supuestos">
            <g:each in="${yachay.proyectos.Supuesto.findAllByMarcoLogico(actividad)}" var="su">
                <div class="texto sup ui-corner-all fin varios" id="sc_${su.id}" ml="${actividad.id}"
                     div="sc_${su.id}" identificador="${su.id}" tipo="4"
                     indicador="${actividad?.id}">${su.descripcion}</div>
            </g:each>

        </div>
    </div>
</div>

<div style="float: left;">
    <div id="accordion" style="width:1030px">

        <g:if test="${metas.size()<1}">
            <h3><a href="#">Metas</a></h3>

            <div>El actividad no tiene metas registradas</div>

        </g:if>
        <g:set var="ver" value="0"></g:set>
        <g:set var="invertido" value="0"></g:set>
        <g:each in="${metas}" var="meta" status="k">
            <h3><a href="#">Meta ${k + 1}</a>
            </h3>
            <g:if test="${meta.id.toString()==metaActual}">
                <g:set var="ver" value="${k}"></g:set>
            </g:if>
            <g:set var="invertido" value="${invertido.toDouble()+meta.inversion}"></g:set>
            <div id="tab_${meta.id}">

                <table width="100%" class="ui-widget-content ui-corner-all">

                    <tbody>
                    <tr class="prop ${hasErrors(bean: meta, field: 'id', 'error')}">

                        <td class="label">
                            Indicador
                        </td>

                        <td class="campo">

                            ${meta?.tipoMeta?.encodeAsHTML()}

                        </td> <!-- campo -->
                        <td class="label">
                            <g:message code="meta.parroquia.label" default="Parroquia"/>
                        </td>

                        <td class="campo">

                            ${meta?.parroquia?.encodeAsHTML()} - ${meta?.parroquia?.canton?.encodeAsHTML()} - ${meta?.parroquia?.canton?.provincia?.encodeAsHTML()}

                        </td> <!-- campo -->
                    </tr>

                    <tr class="prop ${hasErrors(bean: meta, field: 'parroquia', 'error')}">

                        <td class="label">
                            <g:message code="meta.marcoLogico.label" default="Marco Logico"/>
                        </td>

                        <td class="campo">
                            ${meta?.marcoLogico?.encodeAsHTML()}
                        </td> <!-- campo -->
                        <td class="label">
                            <g:message code="meta.unidad.label" default="Unidad"/>
                        </td>

                        <td class="campo">

                            ${meta?.unidad?.encodeAsHTML()}

                        </td> <!-- campo -->
                    </tr>

                    <tr class="prop ${hasErrors(bean: meta, field: 'unidad', 'error')}">

                        <td class="label">
                            Meta
                        </td>

                        <td class="campo">
                            ${fieldValue(bean: meta, field: "indicador")}
                        </td> <!-- campo -->
                    %{--<td class="label">--}%
                    %{--Inversión--}%
                    %{--</td>--}%

                    %{--<td class="campo">--}%
                    %{--${fieldValue(bean: meta, field: "inversion")}--}%
                    %{--<input type="hidden" class="inv" id="inv_${meta.id}" value="${meta.inversion}">--}%
                    %{--</td> <!-- campo -->--}%
                        <td class="label">
                            Año
                        </td>

                        <td class="campo">
                            ${fieldValue(bean: meta, field: "anio")}
                        </td> <!-- campo -->
                    </tr>



                    </tbody>

                    <tfoot>
                    <tr>
                        <td colspan="4" style="text-align: right;">
                            <a href="#" class="btnEliminarMeta" id="${meta.id}" style="float: left;">
                                Eliminar
                            </a>

                            <a href="#" class="btn editar" iden="${meta.id}">Editar</a>
                            <g:link class="btn" action="mapaMeta" id="${meta.id}"
                                    params="[c:actividad.id]">
                                Ubicar en el mapa
                            </g:link>

                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </g:each>
    </div>
</div>

<div style="float: left;width: 90%;height: 35px;display: block;margin-top: 10px;margin-bottom: 10px;">
    <a href="#" id="agregar" class="btn">Agregar</a>
    <a href="${createLink(action: 'matrizMetas' ,id:actividad.id)}" id="cargar" class="btn">Matriz</a>
</div>

%{--<div style="width: 600px;margin-top: 30px;">--}%
%{--<b>Monto:</b>--}%
%{--<g:formatNumber number="${actividad.monto.toFloat()}"--}%
%{--format="###,##0"--}%
%{--minFractionDigits="2" maxFractionDigits="2"/>--}%
%{--&nbsp;&nbsp;&nbsp;--}%
%{--<b>Invertido:</b>--}%
%{--<g:formatNumber number="${invertido.toFloat()}"--}%
%{--format="###,##0"--}%
%{--minFractionDigits="2" maxFractionDigits="2"/>--}%
%{--&nbsp;&nbsp;&nbsp;--}%
%{--<b>Por invertir:</b>--}%
%{--<g:formatNumber number="${(actividad.monto.toFloat() - invertido.toFloat())}"--}%
%{--format="###,##0"--}%
%{--minFractionDigits="2" maxFractionDigits="2"/>--}%
%{--</div>--}%

<input type="hidden" id="monto" value="${actividad.monto}">

<input type="hidden" id="invertido" value="${invertido}">

<input type="hidden" id="id_hidden">

<div style="float: left;width: 90%;height: 35px;display: block;margin-top: 10px;">
    Componente: <g:select
            from="${yachay.proyectos.MarcoLogico.findAllWhere(proyecto: actividad.proyecto,estado: 0,tipoElemento: yachay.parametros.TipoElemento.get(2)).sort{it.id}}" optionKey="id" id="cmb_comp" value="${actividad.id}"/>
</div>
</div>
</div>


<div id="dlg_agregar">
    <g:form action="guardarMeta" controller="meta" class="frm_meta">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        Año
                    </td>

                    <td valign="top"
                        class="value ${hasErrors(bean: metaInstance, field: 'anio', 'errors')}">
                        <g:select class="field ui-widget-content ui-corner-all" name="anio.id"
                                  title="año" from="${yachay.parametros.poaPac.Anio.list([sort: 'anio'])}" optionKey="id"
                                  value="${metaInstance?.anio?.id}" style="width: 80px;"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        Componente
                    </td>

                    <td valign="top"
                        class="value ${hasErrors(bean: metaInstance, field: 'marcoLogico', 'errors')}">
                        ${(actividad?.objeto.length() > 40) ? actividad?.objeto.substring(0, 40) + "..." : actividad.objeto}
                        <input type="hidden" name="marcoLogico.id" value="${actividad.id}">
                    </td>
                </tr>

                <tr class="prop">

                    <td valign="top" class="name">
                        Indicador
                    </td>

                    <td valign="top"
                        class="value ${hasErrors(bean: metaInstance, field: 'tipoMeta', 'errors')}">
                        <g:select class="field ui-widget-content ui-corner-all" name="tipoMeta.id"
                                  title="tipoMeta" from="${yachay.parametros.proyectos.TipoMeta.list()}" optionKey="id"
                                  value="${metaInstance?.tipoMeta?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        Unidad
                    </td>

                    <td valign="top"
                        class="value ${hasErrors(bean: metaInstance, field: 'unidad', 'errors')}">
                        <g:select class="field ui-widget-content ui-corner-all" name="unidad.id"
                                  title="unidad"
                                  from="${yachay.parametros.Unidad.list()}" optionKey="id"
                                  value="${metaInstance?.unidad?.id}"
                                  noSelection="['null': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        Meta
                    </td>

                    <td valign="top"
                        class="value ${hasErrors(bean: metaInstance, field: 'indicador', 'errors')}">
                        <g:textField class="field number required ui-widget-content ui-corner-all"
                                     name="indicador"
                                     title="indicador" id="indicador"
                                     value="${fieldValue(bean: metaInstance, field: 'indicador')}" style="width: 100px;"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        Parroquia
                    </td>
                    %{--TODO cambiar por buscador o alguna molleja--}%
                    <td valign="top"
                        class="value ${hasErrors(bean: metaInstance, field: 'parroquia', 'errors')}">
                        <a href="#" id="buscar">Buscar</a>

                        <input type="hidden" id="parr_id" name="parroquia.id">

                        <div id="parr_nombre"></div>
                        %{--<g:select class="field ui-widget-content ui-corner-all" name="parroquia.id" title="parroquia"--}%
                        %{--from="${yachay.parametros.geografia.Parroquia.list()}" optionKey="id" value="${metaInstance?.parroquia?.id}"--}%
                        %{--noSelection="['null': '']"/>--}%
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div id="dlg_buscar">
            <div style="width: 90%;height: 30px;">
                <input type="text" id="txt_buscar">

                <a href="#" id="buscar_dlg" class="btn">Buscar</a>
            </div>

            <div id="parrs"
                 style="width: 95%;height: 340px;overflow-y: auto;margin: auto;margin-top: 10px;border: 1px solid black;"
                 class="ui-corner-all"></div>
        </div>

        <div style="width: 400px;height: 25px;display: none;margin-top: 10px;border: red 1px solid;background: rgba(255,0,0,0.5);padding: 5px" id="error_agr" class="ui-corner-all"></div>
    </g:form>
    <div id="editar"></div>
</div>

<script type="text/javascript">
    function reajustar() {
        var tam = 40
        var indi = $(".indi").size()
        var sup = $(".sup").size()
        var tipo = 0

        $.each($(".texto"), function () {
            if (tam < $(this).height() * 1) {
                tam = $(this).height() * 1
                if ($(this).hasClass("indicadores"))
                    tipo = 1
                if ($(this).hasClass("supuestos"))
                    tipo = 2
            }
        });
        $.each($(".texto"), function () {
            if ($(this).hasClass("indi")) {
                if (tipo != 1)
                    $(this).height((tam / indi) - (indi - 1) * 3)
            } else
            if ($(this).hasClass("sup")) {
                if (tipo != 2)
                    $(this).height((tam / sup) - (sup - 1) * 3)
            }
            else
                $(this).height(tam)
        });
    }
    reajustar();
    $(".editar").click(function () {
        $("#inv_" + $(this).attr("iden")).addClass("actual")
        $("#id_hidden").val("inv_" + $(this).attr("iden"))
        $.ajax({
            type:"POST",
            url:"${createLink(action:'form',controller:'meta')}",
            data:{
                id:$(this).attr("iden"),
                source:"edit"
            },
            success:function (msg) {

                $("#editar").html(msg)
                $("#editar").dialog("open")
            }
        });
    });
    $(".btn").button()
    $(".back").button({icons:{primary:'ui-icon-arrowreturnthick-1-w'}});

    $(".btnEliminarMeta").button({
        icons:{
            primary:"ui-icon-closethick"
        }
    }).click(function () {
                if (confirm("Está seguro de querer eliminar esta meta?\nEsta acción no es reversible.")) {
                    var meta = $(this).attr("id");
                    $.ajax({
                        type:"POST",
                        url:"${createLink(action:'eliminarMeta',controller:'meta')}",
                        data:{
                            id:meta
                        },
                        success:function (msg) {
                            var parts = msg.split("_");
                            if (parts[0] == "OK") {
                                window.location.reload(true);
                            } else {
                                $("#msgError").html(parts[1]).parent().show();
                            }
                        }
                    });
                }
                return false;
            });


    $("#breadCrumb").jBreadCrumb({
        beginingElementsToLeaveOpen:10
    });

    $("#buscar").button().click(function () {
        $("#dlg_buscar").dialog("open")
    });
    $("#buscar_dlg").click(function () {
        $.ajax({
            type:"POST",
            url:"${createLink(action:'buscarParroquias',controller:'meta')}",
            data:"parametro=" + $("#txt_buscar").val(),
            success:function (msg) {
                $("#parrs").html(msg)

            }
        });
    });
    $("#agregar").click(function () {
        $("#dlg_agregar").dialog("open")
    });
    $("#cmb_comp").change(function () {
        location.href = "${createLink(action:'ingresoMetas')}/" + $(this).val()
    });
    $("#accordion").accordion({collapsible:true ${(ver!="0")?",active:"+ver:""}})
    $("#dlg_buscar").dialog({
        width:630,
        height:500,
        position:"center",
        modal:true,
        autoOpen:false
    })
    $("#editar").dialog({
        width:480,
        height:360,
        position:"center",
        modal:true,
        autoOpen:false,
        title:"Editar",
        buttons:{
            "Guardar":function () {
                var monto = 0
                var invertido = 0

//                $.each($(".inv"), function () {
//
//                    if (!$(this).hasClass("actual")) {
//
//                        invertido += $(this).val() * 1
//
//                    } else {
//                        if ($(this).attr("id") != $("#id_hidden").val()) {
//                            invertido += $(this).val() * 1
//
//                            $(this).removeClass("actual")
//                        }
//                    }
//
//                });
//                var valorForm =$("#inversion_form").val()
//                valorForm = str_replace(".","",valorForm)
//                valorForm = str_replace(",",".",valorForm)
//                valorForm = valorForm*1
//                invertido += valorForm
                if (monto >= invertido) {
                    $.ajax({
                        type:"POST",
                        url:"${createLink(action:'guardarMeta',controller:'meta')}",
                        data:$(".edit_meta").serialize(),
                        success:function (msg) {
                            if (msg == "ok") {
                                location.href = "${createLink(controller:'marcoLogico',action:'ingresoMetas')}?id=${actividad.id}&meta=" + $("#id_form").val()
                            } else {
                                $("#errorUpdate").html(msg).show("pulsate")
                            }
                        }
                    });
                } else {
                    $("#error").addClass("error")
                    $("#error").html("El total de inversión supera al monto de la actividad")
                    $("#error").show("pulsate")
                }
            }
        }
    })
    $("#dlg_agregar").dialog({
        width:460,
        height:360,
        position:"center",
        modal:true,
        autoOpen:false,
        buttons:{
            "Aceptar":function () {
                if ($("#parr_id").val() * 1 > 0) {
//                    var monto = $("#monto").val() * 1
                    var invertido = 0
                    var monto=0
//                    var invForm = $("#inversion_agr").val()
//                    invForm = str_replace(".","",invForm)
//                    invForm = str_replace(",",".",invForm)
//                    invertido += invForm*1
//                    if (isNaN(invertido))
//                        invertido = 0
//                    if (invertido == 0) {
//                        $("#error_agr").html("El campo inversión debe ser un numero mayor a cero")
//                        $("#error_agr").show("pulsate")
//                    } else {
//                        $.each($(".inv"), function () {
//                            invertido += $(this).val() * 1
//                        });

                    if (monto >= invertido) {
                        $.ajax({
                            type:"POST",
                            url:"${createLink(action:'guardarMeta',controller:'meta')}",
                            data:$(".frm_meta").serialize(),
                            success:function (msg) {
                                if (msg != "error") {
                                    window.location.reload(true)
                                } else {
                                    alert("Existe un error. Revise los datos ingresados")
                                }
                            }
                        });
                    } else {
                        $("#error_agr").html("El total de inversión supera al monto de la actividad")
                        $("#error_agr").show("pulsate")
                    }

                } else {
                    $("#error_agr").html("Escoja una parroquia")
                    $("#error_agr").show("pulsate")
                }
            }
        }
    });



</script>
</body>
</html>