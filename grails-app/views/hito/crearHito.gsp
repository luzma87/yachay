<%@ page import="yachay.parametros.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Crear Hito</title>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}"
          type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}"
          type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'svt.css')}"
          type="text/css"/>
    <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript"
            language="JavaScript"></script>
    <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}"
            type="text/javascript" language="JavaScript"></script>

    <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.js')}"></script>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.css')}"/>

    <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins', file: 'jquery.meio.mask.js')}"></script>
    <style>
        .tipo{
            font-weight: bold;
        }
    </style>
</head>
<body>
<g:if test="${flash.message}">
    <div class="message ui-state-highlight ui-corner-all">
        ${flash.message}
    </div>
</g:if>
<div class="fila">
    <g:link controller="hito" action="lista" class="btn">Lista de hitos</g:link>
    %{--<g:link controller="avales" action="crearProceso" class="btn">Crear nuevo</g:link>--}%

</div>
<fieldset style="width: 95%;height: 190px;" class="ui-corner-all">
    <legend>Hito</legend>
    <g:form action="saveHito" class="frmHito">
        <input type="hidden" name="id" value="${hito?.id}">
        <div class="fila" style="height: 110px">
            <div class="labelSvt">Descripción:</div>
            <div class="fieldSvt-xxxl" style="width: 800px">
                <textarea name="descripcion" style="width:100%;height: 100px" id="descripcion">${hito?.descripcion}</textarea>
            </div>
        </div>
        <div class="fila" >
            <div class="fieldSvt-small" style="margin-left: 30px;">
                    <a href="#" id="guardar" class="btn">Guardar</a>
            </div>
        </div>
    </g:form>
</fieldset>
<fieldset style="width: 95%;height: 230px;" class="ui-corner-all">
    <legend>Elementos</legend>
    <div class="fila">
        <div class="labelSvt">Proyecto:</div>

        <div class="fieldSvt-xxxl">
            <g:select from="${proyectos}" optionKey="id" optionValue="nombre" name="proyecto.id" id="proy" style="width:100%" class="ui-corner-all ui-widget-content cmb" value="${proceso?.proyecto?.id}" noSelection="['-1': 'Seleccione...']"></g:select>
        </div>
        <div class="labelSvt">
            <a href="#" class="btn" id="ag_proy">Agregar</a>
        </div>
    </div>
    <div class="fila">
        <div class="labelSvt">Componente:</div>

        <div class="fieldSvt-xxxl" id="div_comp">
                <g:select  class="cmb" from="${[]}" optionValue="objeto" optionKey="id" name="comp" id="comp" noSelection="['-1': 'Seleccione...']" style="width: 100%"></g:select>
                %{--<g:select  class="cmb" from="${yachay.proyectos.MarcoLogico.findAllByProyectoAndTipoElemento(proceso?.proyecto, yachay.parametros.TipoElemento.get(2))}" optionValue="objeto" optionKey="id" name="comp" id="comp" noSelection="['-1': 'Seleccione...']" style="width: 100%"></g:select>--}%

        </div>
        <div class="labelSvt">
            <a href="#" class="btn" id="ag_comp">Agregar</a>
        </div>
    </div>

    <div class="fila">
        <div class="labelSvt">Actividad:</div>

        <div class="fieldSvt-xxxl" id="divAct">
            <g:select class="cmb" from="${[]}" id="actividad" name="actividad" style="width:100%" noSelection="['-1': 'Seleccione']"></g:select>
        </div>
        <div class="labelSvt">
            <a href="#" class="btn" id="ag_act">Agregar</a>
        </div>
    </div>
    <div class="fila" style="height: 2px;margin-top: 20px;margin-bottom: 20px">
        <div style="width: 100%;border-bottom: 1px solid black"></div>
    </div>
    <div class="fila">
        <div class="labelSvt">Proceso:</div>

        <div class="fieldSvt-xxxl" >
            <g:select class="cmb" from="${yachay.avales.ProcesoAval.list([sort: 'nombre'])}" id="proceso" name="proceso" optionKey="id" optionValue="nombre" style="width:100%" noSelection="['-1': 'Seleccione']"></g:select>
        </div>
        <div class="labelSvt">
            <a href="#" class="btn" id="ag_proc">Agregar</a>
        </div>
    </div>
</fieldset>
<fieldset style="width: 95%;height: 300px;overflow: auto" class="ui-corner-all">
    <legend>Composición</legend>
    <div id="detalle" style="width: 95%"></div>
</fieldset>
<script>
    function detalle(){
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'composicion')}",
            data    : {
                id : "${hito?.id}"
            },
            success : function (msg) {
                $("#detalle").html(msg)
            }
        });
    }
    <g:if test="${hito}">
    detalle()
    </g:if>
    $(".btn").button()
    $(".cmb").selectmenu({width : 600});
    $("#guardar").click(function(){
        var msg =""
        if($("#descripcion").val().length==0){
            msg+="<br>Por favor, ingrese una descripción"
        }
        if(msg==""){
            $(".frmHito").submit()
        }else{
            $.box({
                title  : "Error",
                text   : msg,
                dialog : {
                    resizable : false,
                    buttons   : {
                        "Cerrar" : function () {

                        }
                    }
                }
            });
        }
    })
    $("#ag_proy").click(function(){
        var  msg =""
        if($("#proy").val()=="-1"){
         msg="Seleccione un proyecto"
        }
        if(msg==""){
            $.ajax({
                type    : "POST",
                url     : "${createLink(action:'agregarComp')}",
                data    : {
                    id : "${hito?.id}",
                    tipo: "proy",
                    componente:$("#proy").val()
                },
                success : function (msg) {
                    $("#detalle").html(msg)
                }
            });
        }else{
            $.box({
                title  : "Error",
                text   : msg,
                dialog : {
                    resizable : false,
                    buttons   : {
                        "Cerrar" : function () {

                        }
                    }
                }
            });
        }
    });
    $("#ag_comp").click(function(){
        var  msg =""
        if($("#comp").val()=="-1"){
            msg="Seleccione un componente"
        }
        if(msg==""){
            $.ajax({
                type    : "POST",
                url     : "${createLink(action:'agregarComp')}",
                data    : {
                    id : "${hito?.id}",
                    tipo: "ml",
                    componente:$("#comp").val()
                },
                success : function (msg) {
                    $("#detalle").html(msg)
                }
            });
        }else{
            $.box({
                title  : "Error",
                text   : msg,
                dialog : {
                    resizable : false,
                    buttons   : {
                        "Cerrar" : function () {

                        }
                    }
                }
            });
        }
    });
    $("#ag_act").click(function(){
        var  msg =""
        if($("#actividad").val()=="-1"){
            msg="Seleccione una actividad"
        }
        if(msg==""){
            $.ajax({
                type    : "POST",
                url     : "${createLink(action:'agregarComp')}",
                data    : {
                    id : "${hito?.id}",
                    tipo: "ml",
                    componente:$("#actividad").val()
                },
                success : function (msg) {
                    $("#detalle").html(msg)
                }
            });
        }else{
            $.box({
                title  : "Error",
                text   : msg,
                dialog : {
                    resizable : false,
                    buttons   : {
                        "Cerrar" : function () {

                        }
                    }
                }
            });
        }
    });
    $("#ag_proc").click(function(){
        var  msg =""
        if($("#proceso").val()=="-1"){
            msg="Seleccione un proceso"
        }
        if(msg==""){
            $.ajax({
                type    : "POST",
                url     : "${createLink(action:'agregarComp')}",
                data    : {
                    id : "${hito?.id}",
                    tipo: "proc",
                    componente:$("#proceso").val()
                },
                success : function (msg) {
                    $("#detalle").html(msg)
                }
            });
        }else{
            $.box({
                title  : "Error",
                text   : msg,
                dialog : {
                    resizable : false,
                    buttons   : {
                        "Cerrar" : function () {

                        }
                    }
                }
            });
        }
    });

    $("#proy").change(function(){
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'componentesProyecto')}",
            data    : {
                id     : $("#proy").val()
            },
            success : function (msg) {
                $("#div_comp").html(msg)
            }
        });
    });

</script>
</body>
</html>