<%@ page import="app.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Crear proceso</title>
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

</head>

<body>
<g:if test="${flash.message}">
    <div class="message ui-state-highlight ui-corner-all">
        ${flash.message}
    </div>
</g:if>
<div class="fila">
    <g:link controller="avales" action="listaProcesos" class="btn">Lista de procesos</g:link>
    <g:link controller="avales" action="crearProceso" class="btn">Crear nuevo</g:link>
</div>
<fieldset style="width: 95%;height: 170px;" class="ui-corner-all">
    <legend>Proceso</legend>
    <g:form action="saveProceso" class="frmProceso">
        <input type="hidden" name="id" value="${proceso?.id}">
        <div class="fila">
            <div class="labelSvt">Proyecto:</div>
            <div class="fieldSvt-xxxl">
                <g:select from="${proyectos}" optionKey="id" optionValue="nombre" name="proyecto.id" id="proyecto" style="width:100%" class="ui-corner-all ui-widget-content" value="${proceso?.proyecto?.id}"></g:select>
            </div>
        </div>
        <div class="fila">

            <div class="labelSvt">Fecha inicio:</div>
            <div class="fieldSvt-small">
                <g:textField class="datepicker field ui-widget-content ui-corner-all fechaFin"
                             name="fechaInicio"
                             style="width: 100%"
                             title="Fecha de inicio"
                             value="${proceso?.fechaInicio?.format('dd-MM-yyyy')}"
                             id="fechaInicio" autocomplete="off"
                />
            </div>
            <div class="labelSvt">Fecha fin:</div>
            <div class="fieldSvt-small">
                <g:textField class="datepicker field ui-widget-content ui-corner-all fechaFin"
                             name="fechaFin"
                             style="width: 100%"
                             title="Fecha de finalización"
                             value="${proceso?.fechaFin?.format('dd-MM-yyyy')}"
                             id="fechaFin" autocomplete="off"
                />
            </div>
        </div>
        <div class="fila">
            <div class="labelSvt">Nombre:</div>
            <div class="fieldSvt-xxxl">
                <input type="text" class="ui-corner-all" name="nombre" id="nombre" style="width: 100%" value="${proceso?.nombre}">
            </div>
            <div class="fieldSvt-small" style="margin-left: 30px;">
                <g:if test="${band}">
                    <a href="#" id="guardar">Guardar</a>
                </g:if>
            </div>
        </div>
    </g:form>
</fieldset>
<g:if test="${proceso && band}">
    <fieldset style="width: 95%;height: 260px;" class="ui-corner-all">
        <legend>Agregar asignaciones</legend>
        <input type="hidden" id="idAgregar">
        <div class="fila">
            <div class="labelSvt">Año:</div>
            <div class="fieldSvt-small" id="">
                <g:select from="${app.Anio.list( [sort:'anio'])}" value="${actual?.id}" optionKey="id" optionValue="anio" id="anio" name="anio"></g:select>
            </div>
        </div>
        <div class="fila">
            <div class="labelSvt">Componente:</div>
            <div class="fieldSvt-xxxl" id="div_comp">
                <g:if test="${proceso}">
                    <g:select from="${app.MarcoLogico.findAllByProyectoAndTipoElemento(proceso?.proyecto,TipoElemento.get(2))}" optionValue="objeto" optionKey="id" name="comp" id="comp" noSelection="['-1':'Seleccione...']" style="width: 100%"></g:select>
                </g:if>
            </div>
        </div>
        <div class="fila">
            <div class="labelSvt">Actividad:</div>
            <div class="fieldSvt-xxxl" id="divAct">
                <g:select from="${[]}"   id="actividad" name="actividad" style="width:100%" noSelection="['-1':'Seleccione']" ></g:select>
            </div>
        </div>
        <div class="fila">
            <div class="labelSvt">Asignacion:</div>
            <div class="fieldSvt-xxxl" id="divAsg">
                <g:select from="${[]}"   id="asignacion" name="actividad" style="width:100%" noSelection="['-1':'Seleccione']"></g:select>
            </div>
        </div>
        <div class="fila">
            <div class="labelSvt">Monto:</div>
            <div class="fieldSvt-large">
                <input type="text" id="monto" class="ui-corner-all" style="width: 100px;text-align: right;margin-right: 10px">
                Máximo: <span id="max" style="display: inline-block"></span> $
            </div>
            <div class="fieldSvt-small" style="margin-left: 20px">
                <a href="#" class="btn" id="agregar">Agregar</a>
            </div>
        </div>
    </fieldset>
</g:if>
<fieldset style="width: 95%;height: 300px;overflow: auto" class="ui-corner-all">
    <legend>Asignaciones</legend>
    <div id="detalle" style="width: 95%"></div>
</fieldset>
<div id="dlgEditar">
    <g:if test="${band}">
        <input type="hidden" id="dlgId">
        <div class="fila">
            <div class="labelSvt">Monto: </div> <input type="text" style="width: 100px;text-align: right;display: inline-block" id="dlgMonto">
        </div>
        <div class="fila">
            <div class="labelSvt">Máximo: </div> <span id="dlgMax" style="display: inline-block"></span> $
        </div>
    </g:if>
    <g:else>
        <div class="fila">
            No puede editar este proceso porque ya tiene un aval o una solicitud pendiente
        </div>
    </g:else>
</div>
<script>

    function getMaximo(asg){
        $.ajax({
            type: "POST",
            url: "${createLink(action:'getMaximoAsg')}",
            data: {
                id:asg
            },
            success: function(msg) {
                if($("#asignacion").val()!="-1")
                    $("#max").html(msg)
                else {
                    var valor = parseFloat(msg)
                    $("#dlgMax").html(valor + $("#dlgMonto").val()*1)
                }
            }
        });
    }
    function getDetalle(){
        $.ajax({
            type: "POST",
            url: "${createLink(action:'getDetalle')}",
            data: {
                id:"${proceso?.id}"
            },
            success: function(msg) {
                $("#detalle").html(msg)
            }
        });
    }
    function vaciar(){
        $("#monto").val("")
        $("#max").html("")
        $("#asignacion").val("-1")
    }
    <g:if test="${proceso}">
    getDetalle();
    </g:if>
    $('.datepicker').datepicker({
        changeMonth:true,
        changeYear:true,
        dateFormat:'dd-mm-yy',
        minDate:new Date(),
        onClose:function (dateText, inst) {

        }
    });
    $("#guardar").button().click(function(){
        $(".frmProceso").submit()
    });
    $(".btn").button()
    $("#agregar").button().click(function(){
        var id = $("#idAgregar").val()
        var monto = $("#monto").val()
        var max = $("#max").html()
        var asg = $("#asignacion").val()
        var proceso ="${proceso?.id}"

        var msg =""
        if(asg=="-1" || isNaN(asg)){
            msg+="<br>Seleccione una asignación"
        }else{

            if(isNaN(monto) || monto==""){
                msg+="<br>El monto tiene que ser un número positivo."
            }else{
                if(monto*1<0)
                    msg+="<br>El monto tiene que ser un número positivo."
                if(monto*1>max*1)
                    msg+="<br>El monto no puede ser mayor al máximo."
            }
        }

        if(msg==""){
            $.ajax({
                type: "POST",
                url: "${createLink(action:'agregarAsignacion')}",
                data: {
                    id : id,
                    proceso:proceso,
                    monto: monto,
                    asg: asg
                },
                success: function(msg) {
                    getDetalle()
                    vaciar()
                }
            });
        }else{
            $.box({
                title:"Error",
                text:msg,
                dialog: {
                    resizable: false,
                    buttons  : {
                        "Cerrar":function(){

                        }
                    }
                }
            });
        }




    });
    $("#dlgEditar").dialog({
        width:300,
        height:300,
        position:[450,300],
        title:"Editar",
        modal:true,
        autoOpen:false,
        resizable:false,
        buttons:{
            "Aceptar":function(){
                var monto = $("#dlgMonto").val()
                var id = $("#dlgId").val()
                var max = $("#dlgMax").html()
                var msg=""
                if(isNaN(monto) || monto==""){
                    msg+="<br>El monto tiene que ser un número positivo."
                }else{
                    if(monto*1<0)
                        msg+="<br>El monto tiene que ser un número positivo."
                    if(monto*1>max*1)
                        msg+="<br>El monto no puede ser mayor al máximo."
                }
                if(msg==""){
                    $.ajax({
                        type: "POST",
                        url: "${createLink(action:'editarDetalle')}",
                        data: {
                            id : id,
                            monto: monto
                        },
                        success: function(msg) {
                            getDetalle()
                            vaciar()
                            $("#dlgEditar").dialog("close")
                        }
                    });
                }else{
                    $.box({
                        title:"Error",
                        text:msg,
                        dialog: {
                            resizable: false,
                            buttons  : {
                                "Cerrar":function(){

                                }
                            }
                        }
                    });

                }

            } ,
            "Cerrar":function(){
                $("#dlgEditar").dialog("close")
            }
        }
    })
    $("#comp").change(function(){
        $.ajax({
            type: "POST",
            url: "${createLink(action:'cargarActividades')}",
            data: {
                id:$("#comp").val(),
                unidad:"${unidad?.id}"
            },
            success: function(msg) {
                $("#divAct").html(msg)
            }
        });
    }).selectmenu({width : 600});
    $("#proyecto").selectmenu({width : 600});
    $("#actividad").selectmenu({width : 600});
    $("#asignacion").selectmenu({width : 600});
</script>
</body>
</html>