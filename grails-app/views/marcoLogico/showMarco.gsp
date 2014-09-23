<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Marco logico: Fin y Propósito</title>

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
        width      : 235px;
        margin     : 5px;
        min-height : 215px;
        /*border: 1px solid black;*/
        float      : left;
        padding    : 5px;
        position   : relative;

    }

    .cmpDoble {
        width      : 470px;
        margin     : 5px;
        min-height : 215px;
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
        min-height : 115px;
        margin     : 5%;
        cursor     : pointer;
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
<g:form action="guardarMarco" class="marcoLogico">
<div id="divSubmit">
    <input type="hidden" name="proyecto" value="${proyecto.id}" id="proyecto">
    <input type="hidden" name="edicion" value="1" id="edicion">
</div>

<div class="dialog" title="MATRIZ DE MARCO LOGICO DEL PROYECTO">

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
                Marco L&oacute;gico
            </li>
        </ul>
    </div>
</div>

<div id="" class="toolbar ui-widget-header ui-corner-all" style="margin-bottom: 5px;height: 30px;">
    %{--<div style="float: left;margin: 5px;height: 30px">--}%
    %{--<a href="#" id="btn_editar">Activar edici&oacute;n</a>--}%
    <a href="#" id="btn_componentes" style="float:right;">Agregar/Editar componente</a>
</div>

<div style="width: 1030px;float: left;border: 1px solid  rgba(145, 192, 95,0.6)" class="ui-corner-all">
    <div class="matriz ui-corner-all campo cmp datos " ml="Fin" div="div_fin"
         identificador="${fin?.id}">
        <div class="titulo">Fin</div>

        <div class="texto agregado ui-corner-all fin" style="min-height: 115px;" id="div_fin"
             identificador="${fin?.id}">
            ${fin?.objeto}
        </div>

    </div>


    <div class="matriz ui-corner-all campo cmpDoble " id="div_indi_medios_fin">
        <div class="filaMedio" style="min-height: 25px;margin-top: 0px">
            <div class="titulo">Indicadores</div>
        </div>

        <div class="filaMedio" style="min-height: 25px;margin-top: 0px">
            <div class="titulo">Medios de Verificación</div>
        </div>
        <g:set var="band" value="0"></g:set>
        <g:each in="${indicadores}" var="indicador" status="i">
            <g:set var="band" value="1"></g:set>
            <div class="matriz ui-corner-all  fila " id="ind"
                 style="${(i == 0) ? 'margin-top:-10px;' : ''}">
                <div class="filaMedio izq ">
                    <div class="texto agregado ui-corner-all fin varios  " pref="if_"
                         id="if_${indicador.id}" ml="Fin" tipo="1" div="if_${indicador.id}"
                         identificador="${indicador.id}" indicador="${fin.id}">
                        ${indicador?.descripcion}
                    </div>
                </div>

                <div class="filaMedio der">
                    <g:each in="${yachay.proyectos.MedioVerificacion.findAllByIndicador(indicador)}" var="med">
                        <div class="texto agregado ui-corner-all md fin varios" pref="mf_"
                             id="mf_${med.id}"
                             ml="Fin" tipo="2" div="mf_${med.id}" indicador="${indicador.id}"
                             identificador="${med.id}">${med.descripcion}</div>
                    </g:each>
                    <div class=" texto agregado ui-corner-all md fin varios edicion" pref="mf_"
                         id="mf_0${i + 1}" ml="Fin" div="mf_0${i + 1}" tipo="2"
                         indicador="${indicador.id}"
                         identificador="0">Agregar</div>
                </div>
            </div>
        </g:each>

        <div class="matriz ui-corner-all  fila " id="ind"
             style="${(band.toInteger() == 0) ? 'margin-top:-10px;' : ''}">
            <div class="filaMedio izq ">
                <div class="texto agregado ui-corner-all fin varios edicion  " pref="if_" id="if_0"
                     ml="Fin"
                     div="if_0" tipo="1" identificador="0" indicador="${fin?.id}">
                    Agregar
                </div>
            </div>

            <div class="filaMedio der">
                <div class=" texto agregado ui-corner-all md fin varios edicion" pref="mf_"
                     id="mf_0"
                     ml="Fin" div="mf_0" indicador="0" tipo="2" identificador="0">Agregar</div>
            </div>
        </div>

    </div>


    <div class="matriz ui-corner-all campo cmp">
        <div class="titulo">Supuestos</div>

        <div class="texto" style=" min-height: 115px;" id="supuestos">
            <g:each in="${sup}" var="su">
                <div class="agregado ui-corner-all fin varios" id="sf_${su.id}" ml="Fin"
                     div="sf_${su.id}"
                     identificador="${su.id}" tipo="3"
                     indicador="${fin?.id}">${su.descripcion}</div>
            </g:each>
            <div class="agregado ui-corner-all md fin varios editar edicion" id="sf_0" ml="Fin"
                 div="sf_0"
                 identificador="0" tipo="3" indicador="${fin?.id}">Agregar</div>
        </div>
    </div>

</div>%{--end del fin--}%

<div style="width: 1030px;float: left;border: 1px solid  rgba(110, 182, 213,0.6);margin-top: 5px;"
     class="ui-corner-all">
    <div class="matriz ui-corner-all campoProp cmp datos" ml="Proposito" div="div_prop"
         identificador="${proposito?.id}">
        <div class="titulo">Propósito</div>

        <div class="texto agregado ui-corner-all proposito" style="min-height: 115px;" id="div_prop"
             identificador="${proposito?.id}">
            ${proposito?.objeto}
        </div>

    </div>


    <div class="matriz ui-corner-all  cmpDoble  campoProp" id="div_indi_medios_prop">
        <div class="filaMedio" style="min-height: 25px;margin-top: 0px">
            <div class="titulo">Indicadores</div>
        </div>

        <div class="filaMedio" style="min-height: 25px;margin-top: 0px">
            <div class="titulo">Medios de Verificación</div>
        </div>
        <g:set var="band" value="0"></g:set>
        <g:each in="${indiProps}" var="indiProp" status="i">
            <g:set var="band" value="1"></g:set>
            <div class="matriz ui-corner-all  fila " id="ind"
                 style="${(i == 0) ? 'margin-top:-10px;' : ''}">
                <div class="filaMedio izq ">
                    <div class="texto agregado ui-corner-all proposito varios"
                         pref="div_indicador_prop_"
                         id="div_indicador_prop_${indiProp.id}" ml="Proposito" tipo="1"
                         div="div_indicador_prop_${indiProp.id}" identificador="${indiProp.id}"
                         indicador="${proposito?.id}">
                        ${indiProp?.descripcion}
                    </div>
                </div>

                <div class="filaMedio der">
                    <g:each in="${yachay.proyectos.MedioVerificacion.findAllByIndicador(indiProp)}" var="med">
                        <div class="texto agregado ui-corner-all md proposito varios" pref="mp_"
                             id="mp_${med.id}" ml="Proposito" div="mp_${med.id}"
                             indicador="${indiProp.id}"
                             tipo="2" identificador="${med.id}">${med.descripcion}</div>
                    </g:each>
                    <div class=" texto agregado ui-corner-all md proposito varios edicion"
                         pref="mp_"
                         id="mp_0${i + 1}" ml="Proposito" div="mp_0${i + 1}" tipo="2"
                         indicador="${indiProp.id}" identificador="0">Agregar</div>
                </div>
            </div>
        </g:each>

        <div class="matriz ui-corner-all  fila " id="ind"
             style="${(band.toInteger() == 0) ? 'margin-top:-10px;' : ''}">
            <div class="filaMedio izq ">
                <div class="texto agregado ui-corner-all proposito varios edicion  "
                     pref="div_indicador_prop_" id="div_indicador_prop_0" ml="Proposito"
                     div="div_indicador_prop_0" identificador="0" tipo="1"
                     indicador="${proposito?.id}">
                    Agregar
                </div>
            </div>

            <div class="filaMedio der">
                <div class=" texto agregado ui-corner-all md proposito varios edicion" pref="mp_"
                     id="mp_0"
                     ml="Proposito" div="mp_0" indicador="0" tipo="2"
                     identificador="0">Agregar</div>
            </div>
        </div>

    </div>


    <div class="matriz ui-corner-all campoProp cmp">
        <div class="titulo">Supuestos</div>

        <div class="texto" style=" min-height: 115px;" id="div_sup_prop">
            <g:each in="${supProp}" var="su">
                <div class="agregado ui-corner-all proposito varios" id="sp_${su.id}" ml="Proposito"
                     div="sp_${su.id}" identificador="${su.id}" tipo="3"
                     indicador="${proposito?.id}">${su.descripcion}</div>
            </g:each>
            <div class="agregado ui-corner-all proposito varios editar edicion" id="sp_0"
                 ml="Proposito"
                 div="sp_0" identificador="0" tipo="3" indicador="${proposito?.id}">Agregar</div>

        </div>
    </div>

</div>%{--end del proposito--}%

</div>

<div id="dlg_datos">
    <input type="hidden" id="ml">
    <input type="hidden" id="div">
    <input type="hidden" id="iden">

    <div class="texto" style="min-height: 270px">
        <textarea name="mdo" id="txt_datos" style="min-height: 270px"></textarea>
    </div>
</div>

<div id="dlg_combo">
    <input type="hidden" id="c_ml">
    <input type="hidden" id="c_div">
    <input type="hidden" id="c_iden">
    <input type="hidden" id="c_indi">
    <input type="hidden" id="c_tipo">
    %{--<div id="filaCombo" style="display: none">--}%
    %{--<div id="combo" style="float:left">--}%
    %{--<g:select from="${yachay.parametros.TipoSupuesto.list()}" name="tipo" optionKey="id" optionValue="descripcion" style="margin-left: 15px;max-width: 250px;" id="tipoSupuesto" noSelection="${['-1':'Seleccione']}"  />--}%
    %{--</div>--}%
    %{--<a href="#"  id="btnAgregar">Agregar</a>--}%
    %{--</div>--}%
    <div class="texto" style="min-height: 270px">
        <textarea name="txt_varios" id="txt_varios" style="min-height: 270px"></textarea>
    </div>

</div>

</g:form>

</div>

<script type="text/javascript">

    $("#breadCrumb").jBreadCrumb({
        beginingElementsToLeaveOpen: 10
    });

    function reajustar() {
        var tam = 215
        $.each($(".campo"), function() {
            if ($(this).height() * 1 > tam)
                tam = $(this).height() * 1
        });
        $(".campo").css("min-height", tam)
        $("#div_fin").css("min-height", tam - 60)
        tam = 215
        $.each($(".campoProp"), function() {
            if ($(this).height() * 1 > tam)
                tam = $(this).height() * 1
        });
        $(".campoProp").css("min-height", tam)
        $("#div_prop").css("min-height", tam - 60)
        $.each($(".fila"), function() {
            tam = 0
//            $.each($(this).find(".der").children(),function(){
//                //console.log("div de la izq "+$(this).css("display"))
//                if($(this).css("display")!="none")
//                tam += $(this).height()+7
//            });

            if ($(this).find(".izq").height() < $(this).find(".der").height())
                $(this).find(".izq").children().height($(this).find(".der").height() - 10)


        });


    }
    $(function() {
        reajustar()
//                $("#edicion").val("-1")
//                $(".edicion").toggle()
        $("#btn_editar").button({icons: {primary: "ui-icon-pencil"}}).click(function() {
            $(".edicion").toggle()
            if ($("#edicion").val() == "-1") {
                $("#edicion").val("1")
                ////console.log($(this));
                $(this).children(".ui-button-text").html("Desactivar edici&oacute;n")
                $(this).children(".ui-button-icon-primary").removeClass("ui-icon-pencil").addClass("ui-icon-close");
            }
            else {
                $("#edicion").val("-1")
                $(this).children(".ui-button-text").html("Activar edici&oacute;n")
                $(this).children(".ui-button-icon-primary").removeClass("ui-icon-close").addClass("ui-icon-pencil");
            }
        });

        $(".back").button({
            icons: {
                primary:'ui-icon-arrowreturnthick-1-w'
            }
        });

        $("#btn_componentes").button({icons: {primary: "ui-icon-search"}}).click(function() {
            location.href = "${createLink(controller:'marcoLogico',action:'componentes')}/" + $("#proyecto").val()
        });

        $("#btnAgregar").button().click(function() {
            if ($("").val() != "-1") {
                $.ajax({
                    type: "POST",
                    url: "${createLink(action:'agregarSupuesto')}",
                    data: {
                        marco:$("#c_indi").val(),
                        id:$("#tipoSupuesto").val()
                    },
                    success: function(msg) {
                        if (msg != "no") {
                            var partes = msg.split("&&")
                            if ($("#c_iden").val() == "0") {
                                var div = $("<div>")
                                if ($("#c_ml").val() == "Proposito") {
                                    div.attr("ml", "Proposito").attr("id", "sp_0").attr("div", "sp_0").attr("identificador", "0").attr("tipo", "3").attr("indicador", $("#c_indi").val())
                                    div.addClass("agregado ui-corner-all proposito varios editar nuevo edicion")
                                } else {
                                    div.attr("ml", "Fin").attr("id", "sf_0").attr("div", "sf_0").attr("identificador", "0").attr("tipo", "3").attr("indicador", $("#c_indi").val())
                                    div.addClass("agregado ui-corner-all fin varios editar nuevo edicion")
                                }
                                div.html("Agregar")
                                $("#" + $("#c_div").val()).parent().append(div)
                                div.bind("click", function() {
                                    $("#txt_varios").val("")
                                    $("#c_ml").val($(this).attr("ml"))
                                    $("#c_div").val($(this).attr("div"))
                                    $("#c_iden").val($(this).attr("identificador"))
                                    $("#c_indi").val($(this).attr("indicador"))
                                    $("#c_tipo").val($(this).attr("tipo"))
                                    if ($(this).attr("tipo") != "3")
                                        $("#filaCombo").hide()
                                    else
                                    if ($("#c_iden").val() == "0")
                                        $("#filaCombo").show()
                                    if ($("#" + $(this).attr("div")).html())
                                        $("#txt_varios").val($("#" + $(this).attr("div")).html().trim())
                                    if ($("#txt_varios").val() == "Agregar")
                                        $("#txt_varios").val("")

                                    $("#dlg_combo").dialog("open")
                                });
                            }


                            $("#" + $("#c_div").val()).html(partes[1])
                            $("#" + $("#c_div").val()).attr("identificador", partes[0])
                            $("#" + $("#c_div").val()).removeClass("nuevo")
                            $("#" + $("#c_div").val()).removeClass("edicion")
                            $("#" + $("#c_div").val()).attr("div", $("#" + $("#c_div").val()).attr("pref") + partes[0])
                            $("#" + $("#c_div").val()).attr("id", $("#" + $("#c_div").val()).attr("pref") + partes[0])

                            reajustar()
                            $("#dlg_combo").dialog("close")
                        } else {
                            alert("algo salio mal")
                        }
                    }
                });
            }
        });

        $(".datos").click(function() {
            if ($("#edicion").val() == "1") {
                $("#txt_datos").val("")
                $("#ml").val($(this).attr("ml"))
                $("#div").val($(this).attr("div"))
                $("#iden").val($(this).attr("identificador"))
                /* TODO hay un trim aqui que no valdria en ie... cambiar por la funcion */
                if ($("#" + $(this).attr("div")).html())
                    $("#txt_datos").val($("#" + $(this).attr("div")).html().trim())

                $("#dlg_datos").dialog("open")
            }
        });
        $(".varios").click(function() {
            if ($("#edicion").val() == "1") {
                $("#txt_varios").val("")
                $("#c_ml").val($(this).attr("ml"))
                $("#c_div").val($(this).attr("div"))
                $("#c_iden").val($(this).attr("identificador"))
                $("#c_indi").val($(this).attr("indicador"))
                $("#c_tipo").val($(this).attr("tipo"))
                if ($(this).attr("tipo") != "3")
                    $("#filaCombo").hide()
                else
                if ($("#c_iden").val() == "0")
                    $("#filaCombo").show()
                /* TODO hay un trim aqui que no valdria en ie... cambiar por la funcion */
                if ($("#" + $(this).attr("div")).html())
                    $("#txt_varios").val($("#" + $(this).attr("div")).html().trim())
                if ($("#txt_varios").val() == "Agregar")
                    $("#txt_varios").val("")

                $("#dlg_combo").dialog("open")
            }
        });

        $("#dlg_datos").dialog({
            width:300,
            height:440,
            position:"center",
            modal:true,
            autoOpen:false,
            title:"Ingreso de datos",
            buttons:{
                "Eliminar":function() {
                    if (confirm("Esta seguro que desea eliminar?. Esta acción es irreversible")) {
                        $.ajax({
                            type: "POST",
                            url: "${createLink(action:'eliminarMarco')}",
                            data: {
                                tipo:$("#ml").val(),
                                datos:$("#txt_datos").val(),
                                proyecto:$("#proyecto").val(),
                                id:$("#iden").val()
                            },
                            success: function(msg) {
                                if (msg == "ok") {
                                    window.location.reload(true)
                                } else {
                                    alert("algo salio mal")
                                }
                            }
                        });
                    }
                },
                "Aceptar":function() {
                    if ($("#txt_datos").val() != "" && $("#txt_datos").val() != " ") {
                        $.ajax({
                            type: "POST",
                            url: "${createLink(action:'guardarDatosMarco')}",
                            data: {
                                tipo:$("#ml").val(),
                                datos:$("#txt_datos").val(),
                                proyecto:$("#proyecto").val()
                            },
                            success: function(msg) {
                                if (msg == "ok") {
                                    $("#" + $("#div").val()).html($("#txt_datos").val())
                                    $("#txt_datos").val("")

                                    if ($("#iden").val() == "" || $("#iden").val() == " ")
                                        location.reload(true)
                                    reajustar()
                                    $("#dlg_datos").dialog("close")
                                } else {
                                    alert("algo salio mal")
                                }
                            }
                        });

                    }

                }

            }
        });

        $("#dlg_combo").dialog({
            width:300,
            height:440,
            position:"center",
            modal:true,
            autoOpen:false,
            title:"Ingreso de datos",
            buttons:{
                "Eliminar":function() {
                    if (confirm("Esta seguro que desea eliminar?. Esta acción es irreversible") && $("#c_iden").val() != "0") {
                        $.ajax({
                            type: "POST",
                            url: "${createLink(action:'eliminarIndiMedSup')}",
                            data: {
                                ml:$("#c_ml").val(),
                                datos:$("#txt_varios").val(),
                                proyecto:$("#proyecto").val(),
                                id:$("#c_iden").val(),
                                indicador:$("#c_indi").val(),
                                tipo:$("#c_tipo").val()
                            },
                            success: function(msg) {
                                if (msg == "ok") {
                                    if ($("#c_tipo").val() == "1") {
                                        $.each($("#" + $("#c_div").val()).parent().parent().find(".der").children(), function() {
                                            if (!$(this).hasClass("edicion"))
                                                $(this).remove()
                                            else
                                                $(this).attr("indicador", "0")
                                        });
                                        $("#" + $("#c_div").val()).attr("iden", "0")
                                        $("#" + $("#c_div").val()).html("Agregar")
                                    } else {
                                        $("#" + $("#c_div").val()).remove()
                                    }
                                    $("#dlg_combo").dialog("close")

                                } else {
                                    alert("algo salio mal")
                                }
                            }
                        });
                    } else {
                        $("#dlg_combo").dialog("close")
                    }
                },
                "Aceptar":function() {
                    if ($("#txt_varios").val() != "" && $("#txt_varios").val() != " ") {
                        $.ajax({
                            type: "POST",
                            url: "${createLink(action:'guardarDatosIndMedSup')}",
                            data: {
                                ml:$("#c_ml").val(),
                                datos:$("#txt_varios").val(),
                                proyecto:$("#proyecto").val(),
                                id:$("#c_iden").val(),
                                indicador:$("#c_indi").val(),
                                tipo:$("#c_tipo").val()
                            },
                            success: function(msg) {
                                if (msg != "no") {
                                    if ($("#c_iden").val() == "0" && $("#c_tipo").val() * 1 == 1) {
                                        var d1 = $("<div>")
                                        d1.addClass("matriz ui-corner-all  fila ")
                                        var d2 = $("<div>")
                                        d2.addClass("filaMedio")
                                        var d3 = $("<div>")
                                        d3.attr("indicador", $("#c_indi").val())
                                        d3.html("Agregar")
                                        var d4 = $("<div>")
                                        d4.addClass("filaMedio der")
                                        var d5 = $("<div>")
                                        d5.attr("indicador", "0")
                                        d5.html("Agregar")
                                        d2.append(d3)
                                        d4.append(d5)
                                        d5.bind("click", function() {
                                            if ($("#edicion").val() == "1") {
                                                $("#txt_varios").val("")
                                                $("#c_ml").val($(this).attr("ml"))
                                                $("#c_div").val($(this).attr("div"))
                                                $("#c_iden").val($(this).attr("identificador"))
                                                $("#c_indi").val($(this).attr("indicador"))
                                                $("#c_tipo").val($(this).attr("tipo"))
                                                if ($(this).attr("ml") != "Supuestos")
                                                    $("#filaCombo").hide()
                                                else
                                                    $("#filaCombo").show()
                                                /* TODO hay un trim aqui que no valdria en ie... cambiar por la funcion */
                                                if ($("#" + $(this).attr("div")).html())
                                                    $("#txt_varios").val($("#" + $(this).attr("div")).html().trim())
                                                if ($("#txt_varios").val() == "Agregar")
                                                    $("#txt_varios").val("")

                                                $("#dlg_combo").dialog("open")
                                            }
                                        });
                                        d3.bind("click", function() {
                                            if ($("#edicion").val() == "1") {
                                                $("#txt_varios").val("")
                                                $("#c_ml").val($(this).attr("ml"))
                                                $("#c_div").val($(this).attr("div"))
                                                $("#c_iden").val($(this).attr("identificador"))
                                                $("#c_indi").val($(this).attr("indicador"))
                                                $("#c_tipo").val($(this).attr("tipo"))
                                                if ($(this).attr("ml") != "Supuestos")
                                                    $("#filaCombo").hide()
                                                else
                                                    $("#filaCombo").show()
                                                /* TODO hay un trim aqui que no valdria en ie... cambiar por la funcion */
                                                if ($("#" + $(this).attr("div")).html())
                                                    $("#txt_varios").val($("#" + $(this).attr("div")).html().trim())
                                                if ($("#txt_varios").val() == "Agregar")
                                                    $("#txt_varios").val("")

                                                $("#dlg_combo").dialog("open")
                                            }
                                        });
                                        d1.append(d2)
                                        d1.append(d4)
                                        var num =Math.floor(Math.random()*101)
                                        if ($("#c_ml").val() == "Fin") {
                                            d3.addClass("texto agregado ui-corner-all fin varios edicion").attr("pref", "div_indicador_").attr("id", "div_indicador_0"+num).attr("ml", "Fin").attr("div", "div_indicador_0"+num).attr("identificador", "0").attr("tipo", "1")
                                            d5.addClass(" texto agregado ui-corner-all md fin varios edicion").attr("pref", "mp_").attr("id", "mp_0"+num).attr("ml", "Fin").attr("div", "mp_0"+num).attr("identificador", "0").attr("tipo", "2")
                                            $("#div_indi_medios_fin").append(d1)
                                        } else {
                                            d3.addClass("texto agregado ui-corner-all proposito varios edicion").attr("pref", "div_indicador_").attr("id", "div_indicador_0"+num).attr("ml", "Proposito").attr("div", "div_indicador_0"+num).attr("identificador", "0").attr("tipo", "1")
                                            d5.addClass("texto agregado ui-corner-all md proposito varios edicion").attr("pref", "mp_").attr("id", "mp_0"+num).attr("ml", "Proposito").attr("div", "mp_0"+num).attr("identificador", "0").attr("tipo", "2")
                                            $("#div_indi_medios_prop").append(d1)
                                        }
                                        $("#" + $("#c_div").val()).parent().parent().find(".der").children().attr("indicador", msg)
                                    }
                                    if ($("#c_iden").val() == "0" && $("#c_tipo").val() * 1 == 2) {
                                        var d1 = $("<div>")
                                        d1.html("Agregar")
                                        if ($("#c_ml").val() == "Fin") {
                                            d1.addClass("texto agregado ui-corner-all md fin varios nuevo edicion")
                                            d1.attr("pref", "mp_").attr("div", $("#c_div").val()).attr("ml", "Fin").attr("tipo", "2").attr("indicador", $("#c_indi").val()).attr("identificador", "0")
                                        } else {
                                            d1.addClass(" texto agregado ui-corner-all md proposito varios nuevo edicion")
                                            d1.attr("pref", "mp_").attr("div", $("#c_div").val()).attr("ml", "Proposito").attr("tipo", "2").attr("indicador", $("#c_indi").val()).attr("identificador", "0")
                                        }

                                        $("#" + $("#c_div").val()).parent().append(d1)
                                        d1.bind("click", function() {
                                            if ($("#edicion").val() == "1") {
                                                $("#txt_varios").val("")
                                                $("#c_ml").val($(this).attr("ml"))
                                                $("#c_div").val($(this).attr("div"))
                                                $("#c_iden").val($(this).attr("identificador"))
                                                $("#c_indi").val($(this).attr("indicador"))
                                                $("#c_tipo").val($(this).attr("tipo"))
                                                if ($(this).attr("ml") != "Supuestos")
                                                    $("#filaCombo").hide()
                                                else
                                                    $("#filaCombo").show()
                                                /* TODO hay un trim aqui que no valdria en ie... cambiar por la funcion */
                                                if ($("#" + $(this).attr("div")).html())
                                                    $("#txt_varios").val($("#" + $(this).attr("div")).html().trim())
                                                if ($("#txt_varios").val() == "Agregar")
                                                    $("#txt_varios").val("")

                                                $("#dlg_combo").dialog("open")
                                            }
                                        });
                                    }

                                    if ($("#c_iden").val() == "0" && $("#c_tipo").val() * 1 == 3) {
                                        var div = $("<div>")
                                        if ($("#c_ml").val() == "Proposito") {
                                            div.attr("ml", "Proposito").attr("id", "sp_0").attr("div", "sp_0").attr("identificador", "0").attr("tipo", "3").attr("indicador", $("#c_indi").val())
                                            div.addClass("texto agregado ui-corner-all proposito varios editar nuevo edicion")
                                        } else {
                                            div.attr("ml", "Fin").attr("id", "sf_0").attr("div", "sf_0").attr("identificador", "0").attr("tipo", "3").attr("indicador", $("#c_indi").val())
                                            div.addClass("texto agregado ui-corner-all fin varios editar nuevo edicion")
                                        }
                                        div.html("Agregar")
                                        $("#" + $("#c_div").val()).parent().append(div)
                                        div.bind("click", function() {
                                            if ($("#edicion").val() == "1") {
                                                $("#txt_varios").val("")
                                                $("#c_ml").val($(this).attr("ml"))
                                                $("#c_div").val($(this).attr("div"))
                                                $("#c_iden").val($(this).attr("identificador"))
                                                $("#c_indi").val($(this).attr("indicador"))
                                                $("#c_tipo").val($(this).attr("tipo"))
                                                if ($(this).attr("tipo") != "3")
                                                    $("#filaCombo").hide()
                                                else
                                                if ($("#c_iden").val() == "0")
                                                    $("#filaCombo").show()
                                                /* TODO hay un trim aqui que no valdria en ie... cambiar por la funcion */
                                                if ($("#" + $(this).attr("div")).html())
                                                    $("#txt_varios").val($("#" + $(this).attr("div")).html().trim())
                                                if ($("#txt_varios").val() == "Agregar")
                                                    $("#txt_varios").val("")

                                                $("#dlg_combo").dialog("open")
                                            }
                                        });
                                    }

                                    if ($("#c_tipo").val() != "3") {
                                        var id = $("#c_div").val()
                                        var div = $("#" + $("#c_div").val())
                                        $("#" + $("#c_div").val()).removeClass("nuevo")
                                        $("#" + $("#c_div").val()).html($("#txt_varios").val())
                                        $("#" + $("#c_div").val()).attr("div", $("#" + $("#c_div").val()).attr("pref") + msg)
                                        $("#" + $("#c_div").val()).attr("identificador", msg)
                                        $("#" + $("#c_div").val()).removeClass("edicion")
                                        $("#" + $("#c_div").val()).attr("id", $("#" + $("#c_div").val()).attr("pref") + msg)
                                        if ($("#c_iden").val() == "0" && $("#c_tipo").val() * 1 == 2) {
                                            div.parent().find(".nuevo").attr("id", id)
                                        }
                                    } else {
                                        var partes = msg.split("&&")
                                        $("#" + $("#c_div").val()).html(partes[1])
                                        $("#" + $("#c_div").val()).attr("identificador", partes[0])
                                        $("#" + $("#c_div").val()).removeClass("edicion")
                                        $("#" + $("#c_div").val()).removeClass("nuevo")
                                        $("#" + $("#c_div").val()).attr("div", $("#" + $("#c_div").val()).attr("pref") + partes[0])
                                        $("#" + $("#c_div").val()).attr("id", $("#" + $("#c_div").val()).attr("pref") + partes[0])
                                    }
                                    reajustar()
                                    $("#dlg_combo").dialog("close")
                                } else {
                                    $("#dlg_combo").dialog("close")
                                    alert("Error al guardar los datos")
                                }
                            }
                        });
                    }
                }
            }
        });

    });



</script>
</body>
</html>