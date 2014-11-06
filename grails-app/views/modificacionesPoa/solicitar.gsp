<%--
  Created by IntelliJ IDEA.
  User: svt
  Date: 10/21/2014
  Time: 3:00 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Solicitar Reforma del P.O.A.</title>
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
</head>

<body>
<g:if test="${flash.message}">
    <div class="message ui-state-highlight ui-corner-all">
        ${flash.message}
    </div>
</g:if>
<fieldset style="width: 95%;height: 300px;" class="ui-corner-all">
    <legend>Asignación de origen.</legend>
    <g:form action="save" class="frmProceso">
        <div class="fila">
            <div class="labelSvt">Año:</div>

            <div class="fieldSvt-small" id="">
                <g:select from="${yachay.parametros.poaPac.Anio.list([sort: 'anio'])}" value="${actual?.id}" optionKey="id" optionValue="anio" id="anio" name="anio" ></g:select>
            </div>
        </div>
        <div class="fila">
            <div class="labelSvt">Proyecto:</div>

            <div class="fieldSvt-xxxl">
                <g:select from="${proyectos}" optionKey="id" optionValue="nombre" name="proyecto.id" id="proyecto" style="width:100%" class="ui-corner-all ui-widget-content" value="${proceso?.proyecto?.id}"  noSelection="['-1': 'Seleccione...']" ></g:select>
            </div>
        </div>
        <div class="fila">
            <div class="labelSvt">Componente:</div>

            <div class="fieldSvt-xxxl" id="div_comp">
                <g:select from="${[]}"  name="comp" id="comp" noSelection="['-1': 'Seleccione...']" style="width: 100%"></g:select>
            </div>
        </div>

        <div class="fila">
            <div class="labelSvt">Actividad:</div>

            <div class="fieldSvt-xxxl" id="divAct">
                <g:select from="${[]}" id="actividad" name="actividad" style="width:100%" noSelection="['-1': 'Seleccione']"></g:select>
            </div>
        </div>

        <div class="fila">
            <div class="labelSvt">Asignacion:</div>

            <div class="fieldSvt-xxxl" id="divAsg">
                <g:select from="${[]}" id="asignacion" name="origen.id" style="width:100%" noSelection="['-1': 'Seleccione']"></g:select>
            </div>
        </div>

        <div class="fila">
            <div class="labelSvt">Monto:</div>
            <input type="hidden" id="valor">
            <div class="fieldSvt-large">
                <input type="text" id="monto" class="ui-corner-all decimal" style="width: 100px;text-align: right;margin-right: 10px">
                Máximo: <span id="max" style="display: inline-block"></span> $

            </div>
        </div>

    </g:form>
</fieldset>
<fieldset style="width: 95%;height: 100px;" class="ui-corner-all">
<legend>Firma</legend>
    <div class="fila">
        <div class="labelSvt">Firma:</div>

        <div class="fieldSvt-small">
            <g:select from="${yachay.seguridad.Usro.findAllByUnidad(session.usuario.unidad,[sort:'persona'])}" optionKey="id"  optionValue="persona"  id="firma" name="firma" ></g:select>
        </div>
    </div>
</fieldset>
<div id="tabs" style="width: 1025px;margin-top: 10px;">
    <ul>
        <li><a href="#reasignar">Actividad existente</a></li>
        <li><a href="#nueva">Nueva actividad</a></li>
        <li><a href="#derivada">Nueva partida</a></li>
        <li><a href="#aumentar">Incremento</a></li>
    </ul>

    <div id="reasignar" style="width: 960px;">
        <fieldset style="width: 95%;height: 270px;" class="ui-corner-all">
            <legend>Asignación de destino.</legend>

            <div class="fila">
                <div class="labelSvt">Año:</div>

                <div class="fieldSvt-small">
                    <g:select from="${yachay.parametros.poaPac.Anio.list([sort: 'anio'])}" value="${actual?.id}" optionKey="id" optionValue="anio" id="anio_dest" name="anio" ></g:select>
                </div>
            </div>

            <div class="fila">
                <div class="labelSvt">Proyecto:</div>

                <div class="fieldSvt-xxxl">
                    <g:select from="${yachay.proyectos.Proyecto.list([sort: 'nombre'])}" optionKey="id" optionValue="nombre" name="proyecto.id" id="proyectoDest" style="width:100%" class="ui-corner-all ui-widget-content" value="${proceso?.proyecto?.id}"  noSelection="['-1': 'Seleccione...']" ></g:select>
                </div>
            </div>
            <div class="fila">
                <div class="labelSvt">Componente:</div>

                <div class="fieldSvt-xxxl" id="div_comp_dest">
                    <g:select from="${[]}"  name="comp" id="compDest" noSelection="['-1': 'Seleccione...']" style="width: 100%"></g:select>
                </div>
            </div>


            <div class="fila">
                <div class="labelSvt">Actividad:</div>

                <div class="fieldSvt-xxxl" id="divAct_dest">
                    <g:select from="${[]}" id="actividad_dest" name="actividad" style="width:100%" noSelection="['-1': 'Seleccione']"></g:select>
                </div>
            </div>

            <div class="fila">
                <div class="labelSvt">Asignacion:</div>

                <div class="fieldSvt-xxxl" id="divAsg_dest">
                    <g:select from="${[]}" id="asignacion_dest" name="origen.id" style="width:100%" noSelection="['-1': 'Seleccione']"></g:select>
                </div>
            </div>


        </fieldset>
        <fieldset style="width: 95%;height: 150px;" class="ui-corner-all">
            <legend>Concepto</legend>
            <div class="fila">
                <textarea id="concepto_reasignacion" style="width: 95%;height: 80px" class="ui-corner-all ui-widget-content"></textarea>
            </div>
        </fieldset>
        <div class="fila">
            <a href="#" id="guardar1" class="btn">Guardar</a>
        </div>
    </div>
    <div id="nueva" style="width: 960px;">
        <fieldset style="width: 95%;height: 280px;" class="ui-corner-all">
            <legend>Nueva asignación.</legend>

            <div class="fila">
                <div class="labelSvt">Año:</div>
                <div class="fieldSvt-small">
                    <g:select from="${yachay.parametros.poaPac.Anio.list([sort: 'anio'])}" value="${actual?.id}" optionKey="id" optionValue="anio" id="anio_nueva" name="anio" ></g:select>
                </div>
            </div>
            <div class="fila" style="height: 60px">
                <div class="labelSvt">Actividad:</div>
                <div class="fieldSvt-xxxl" style="width: 700px" id="divAct_nueva_">
                    %{--<g:select from="${[]}" id="actividad_nueva" name="actividad" style="width:100%" noSelection="['-1': 'Seleccione']"></g:select>--}%
                    <textarea id="actividad_nueva" style="width: 100%;height: 50px;resize: none"></textarea>
                </div>
            </div>
            <div class="fila">
                <div class="labelSvt">Incio</div>
                <div class="fieldSvt-medium">
                    <g:textField class="datepicker field ui-widget-content ui-corner-all fechaInicio"
                                 name="fechaInicio"
                                 style="width: 90%"
                                 title="Fecha de inicio de la actividad"
                                 id="inicio" autocomplete="off"                    />
                </div>
                <div class="labelSvt">Fin</div>
                <div class="fieldSvt-medium">
                    <g:textField class="datepicker field ui-widget-content ui-corner-all fechaFin"
                                 name="fechaFin"
                                 style="width: 90%"
                                 title="Fecha de fin de la actividad"
                                 id="fin" autocomplete="off"                    />
                </div>
            </div>

            <div class="fila">
                <div class="labelSvt">Fuente</div>
                <div class="fieldSvt-xxxl" >
                    <g:select from="${yachay.parametros.poaPac.Fuente.list([sort:'descripcion'])}" id="fuente" name="fuente" class="ui-corner-all" optionKey="id" optionValue="descripcion"></g:select>
                </div>
            </div>
            <div class="fila">
                <div class="labelSvt">Partida:</div>
                <div class="fieldSvt-xxxl">
                    <input type="hidden" class="prsp" value="" id="prsp_id">
                    <input type="text" id="prsp_num" class="buscar ui-corner-all" style="width: 60px;color:black">
                </div>
            </div>

        </fieldset>
        <fieldset style="width: 95%;height: 150px;" class="ui-corner-all">
            <legend>Concepto</legend>
            <div class="fila">
                <textarea id="concepto_nueva" style="width: 95%;height: 80px" class="ui-corner-all ui-widget-content"></textarea>
            </div>
        </fieldset>
        <div class="fila">
            <a  href="#" id="guardar2" class="btn">Guardar</a>
        </div>
    </div>
    <div id="derivada" style="width: 960px;">
        <fieldset style="width: 95%;height: 150px;" class="ui-corner-all">
            <legend>Concepto</legend>
            <div class="fila">
                <textarea id="concepto_derivada" style="width: 95%;height: 80px" class="ui-corner-all ui-widget-content"></textarea>
            </div>
        </fieldset>
        <div class="fila">
            <a  href="#" id="guardar3" class="btn">Guardar</a>
        </div>
    </div>
    <div id="aumentar" style="width: 960px;">
        <fieldset style="width: 95%;height: 150px;" class="ui-corner-all">
            <legend>Concepto</legend>
            <div class="fila">
                <textarea id="concepto_aumentar" style="width: 95%;height: 80px" class="ui-corner-all ui-widget-content"></textarea>
            </div>
        </fieldset>
        <div class="fila">
            <a  href="#" id="guardar4" class="btn">Guardar</a>
        </div>
    </div>
    %{--<div id="elimiar" style="width: 960px;">--}%
    %{--<fieldset style="width: 95%;height: 150px;" class="ui-corner-all">--}%
    %{--<legend>Concepto</legend>--}%
    %{--<div class="fila">--}%
    %{--<textarea id="concepto_eliminar" style="width: 95%;height: 80px" class="ui-corner-all ui-widget-content"></textarea>--}%
    %{--</div>--}%
    %{--</fieldset>--}%
    %{--<div class="fila">--}%
    %{--<a  href="#" id="guardar4" class="btn">Guardar</a>--}%
    %{--</div>--}%
    %{--</div>--}%
</div>
<div id="buscar">
    <input type="hidden" id="id_txt">

    <div>
        Buscar por:
        <select id="tipo">
            <option value="1">Número</option>
            <option value="2">Descripción</option>
        </select>

        <input type="text" id="par" style="width: 160px;">

        <a href="#" class="btn" id="btn_buscar">Buscar</a>
    </div>

    <div id="resultado" style="width: 450px;margin-top: 10px;" class="ui-corner-all"></div>
</div>
<div id="load">
    <img src="${g.resource(dir:'images',file: 'loading.gif')}" alt="Procesando">
    Procesando
</div>
<script>

    function getMaximo(asg) {
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'getMaximoAsg',controller: 'avales')}",
            data    : {
                id : asg
            },
            success : function (msg) {
                getValor(asg)
                if ($("#asignacion").val() != "-1") {
                    $("#max").html(number_format(msg, 2, ",", "."));
                    $("#max").attr("valor", msg)
                }else {
                    var valor = parseFloat(msg);
                    var monto = $("#dlgMonto").val();
                    monto = monto.replace(new RegExp("\\.", 'g'), "");
                    monto = monto.replace(new RegExp(",", 'g'), ".");
                    monto = parseFloat(monto);
                    $("#dlgMax").html(number_format(valor + monto, 2, ",", "."));
                }
            }
        });
    }
    function getValor(asg) {
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'getValor',controller: 'modificacionesPoa')}",
            data    : {
                id : asg
            },
            success : function (msg) {
//                combosInternos()
                $("#valor").val(msg);
            }
        });
    }
    function combosInternos(){
//        comboNuevo()
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'cargarActividades',controller: 'modificacionesPoa')}",
            data    : {
                id : $("#comp").val(),
                div : "divAsg_dest"
            },
            success : function (msg) {

                $("#divAct_dest").html(msg)

            }
        });
    }
    function comboNuevo(){
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'cargarActividades',controller: 'modificacionesPoa')}",
            data    : {
                id : $("#comp").val(),
                div : "",
                comboId:"actividad_nueva"
            },
            success : function (msg) {

                $("#divAct_nueva").html(msg)

            }
        });
    }

    $(".decimal").setMask("decimal");
    /*Guardar*/
    $("#guardar1").click(function(){
        var asgDest = $("#asignacion_dest").val()
        var asgOrigen = $("#asignacion").val()
        var monto = $("#monto").val();
        monto = monto.replace(new RegExp("\\.", 'g'), "");
        monto = monto.replace(new RegExp(",", 'g'), ".");
        var max = $("#max").attr("valor")
        var msg =""
        var concepto = $("#concepto_reasignacion").val()
//        console.log("dest ",asgDest,monto,max,"!"+concepto+"!")
        if(asgOrigen=="-1"){
            msg+="<br>Por favor, seleccione una asignación de origen"
        }
        if(asgDest=="-1"){
            msg+="<br>Por favor, seleccione una asignación de destino"
        }
        if(monto==""){
            msg+="<br>Por favor, ingrese un monto válido"
        }
        if(isNaN(monto)){
            msg+="<br>Por favor, ingrese un monto válido"
        }else{
            if(monto*1<=0){
                msg+="<br>El monto debe ser un número positivo mayor a cero"
            }
            if(monto*1>max*1){
                msg+="<br>El monto no puede superar al máximo"
            }
        }
        if(concepto.trim().length==0){
            msg+="<br>Por favor, ingrese concepto"
        }
        if(asgDest==asgOrigen){
            msg+="<br>La asignación de destino debe ser diferente a la de origen"
        }
        if(msg==""){
            $("#load").dialog("open")
            $.ajax({
                type:"POST",
                url:"${createLink(action:'guardarSolicitudReasignacion',controller:'modificacionesPoa')}",
                data:{
                    origen:asgOrigen,
                    destino:asgDest,
                    monto:monto,
                    concepto:concepto,
                    firma:$("#firma").val()
                },
                success:function (msg) {
                    location.href="${g.createLink(controller: 'modificacionesPoa',action: 'historialUnidad')}"
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
    $('.datepicker').datepicker({
        changeMonth:true,
        changeYear:true,
        dateFormat:'dd-mm-yy',
        minDate:new Date(),
        onClose:function (dateText, inst) {
            var date = $(this).datepicker('getDate');
            var day, month, year;
            if (date != null) {
                day = date.getDate();
                month = parseInt(date.getMonth()) + 1;
                year = date.getFullYear();
            } else {
                day = '';
                month = '';
                year = '';
            }
            var id = $(this).attr('id');
            $('#' + id + '_day').val(day);
            $('#' + id + '_month').val(month);
            $('#' + id + '_year').val(year);
        }
    });
    $("#guardar2").click(function(){
        var asgOrigen = $("#asignacion").val()
        var monto = $("#monto").val();
        monto = monto.replace(new RegExp("\\.", 'g'), "");
        monto = monto.replace(new RegExp(",", 'g'), ".");
        var max = $("#max").attr("valor")
        var msg =""
        var concepto = $("#concepto_nueva").val()
//        console.log("nueva ",monto,max,"!"+concepto+"!")
        var fuente = $("#fuente").val()
        var presupuesto = $("#prsp_id").val()
        var anio = $("#anio_nueva").val()
        var actividad = $("#actividad_nueva").val()
        var inicio = $("#inicio").val()
        var fin = $("#fin").val()
        if(asgOrigen=="-1"){
            msg+="<br>Por favor, seleccione una asignación de origen"
        }
        if(monto==""){
            msg+="<br>Por favor, ingrese un monto válido"
        }
        if(isNaN(monto)){
            msg+="<br>Por favor, ingrese un monto válido"
        }else{
            if(monto*1<=0){
                msg+="<br>El monto debe ser un número positivo mayor a cero"
            }
            if(monto*1>max*1){
                msg+="<br>El monto no puede superar al máximo"
            }
        }
        if(concepto.trim().length==0){
            msg+="<br>Por favor, ingrese concepto"
        }
        if(actividad.trim().length==0){
            msg+="<br>Por favor, insgrese una actividad para la nueva asignación"
        }
        if(actividad.trim().length==1023){
            msg+="<br>La actividad debe tener un máximo de 1023 caracteres"
        }
        if(presupuesto==""){
            msg+="<br>Por favor, seleccione partida presupuestaria"
        }
        if(inicio==""){
            msg+="<br>Por favor, ingrese una fecha de inicio"
        }
        if(fin==""){
            msg+="<br>Por favor, ingrese una fecha de fin"
        }
        if(msg==""){
            $("#load").dialog("open")
            $.ajax({
                type:"POST",
                url:"${createLink(action:'guardarSolicitudNueva',controller:'modificacionesPoa')}",
                data:{
                    origen:asgOrigen,
                    monto:monto,
                    concepto:concepto,
                    fuente:fuente,
                    presupuesto:presupuesto,
                    actividad:actividad,
                    inicio:inicio,
                    fin:fin,
                    firma:$("#firma").val()
                },
                success:function (msg) {
                    location.href="${g.createLink(controller: 'modificacionesPoa',action: 'historialUnidad')}"
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

    $("#guardar3").click(function(){

        var asgOrigen = $("#asignacion").val()
        var monto = $("#monto").val();
        monto = monto.replace(new RegExp("\\.", 'g'), "");
        monto = monto.replace(new RegExp(",", 'g'), ".");
        var max = $("#max").attr("valor")
        var msg =""
        var concepto = $("#concepto_derivada").val()
//        console.log("dest ",asgDest,monto,max,"!"+concepto+"!")
        if(asgOrigen=="-1"){
            msg+="<br>Por favor, seleccione una asignación de origen"
        }

        if(monto==""){
            msg+="<br>Por favor, ingrese un monto válido"
        }
        if(isNaN(monto)){
            msg+="<br>Por favor, ingrese un monto válido"
        }else{
            if(monto*1<=0){
                msg+="<br>El monto debe ser un número positivo mayor a cero"
            }
            if(monto*1>max*1){
                msg+="<br>El monto no puede superar al máximo"
            }
        }
        if(concepto.trim().length==0){
            msg+="<br>Por favor, ingrese concepto"
        }
        if(msg==""){
            $("#load").dialog("open")
            $.ajax({
                type:"POST",
                url:"${createLink(action:'guardarSolicitudDerivada',controller:'modificacionesPoa')}",
                data:{
                    origen:asgOrigen,
                    monto:monto,
                    concepto:concepto,
                    firma:$("#firma").val()
                },
                success:function (msg) {
                    location.href="${g.createLink(controller: 'modificacionesPoa',action: 'historialUnidad')}"
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


    $("#guardar4").click(function(){

        var asgOrigen = $("#asignacion").val()

        var max = $("#max").attr("valor")
        var valor = $("#valor").val()
        var msg =""
        var monto = $("#monto").val();
        monto = monto.replace(new RegExp("\\.", 'g'), "");
        monto = monto.replace(new RegExp(",", 'g'), ".");
        var concepto = $("#concepto_aumentar").val()
//        console.log("dest ",asgDest,monto,max,"!"+concepto+"!")
        if(asgOrigen=="-1"){
            msg+="<br>Por favor, seleccione una asignación de origen"
        }
        if(monto==""){
            msg+="<br>Por favor, ingrese un monto válido"
        }
        if(isNaN(monto)){
            msg+="<br>Por favor, ingrese un monto válido"
        }else{
            if(monto*1<=0){
                msg+="<br>El monto debe ser un número positivo mayor a cero"
            }

        }

        if(concepto.trim().length==0){
            msg+="<br>Por favor, ingrese concepto"
        }
//        if(max!=valor){
//            msg+="No puede eliminar la asignación seleccionada porque tiene avales o solicitudes pendientes."
//        }
        if(msg==""){
            $("#load").dialog("open")
            $.ajax({
                type:"POST",
                url:"${createLink(action:'guardarSolicitudAumentar',controller:'modificacionesPoa')}",
                data:{
                    origen:asgOrigen,
                    monto:monto,
                    concepto:concepto,
                    firma:$("#firma").val()
                },
                success:function (msg) {
                    location.href="${g.createLink(controller: 'modificacionesPoa',action: 'historialUnidad')}"
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


    $(".buscar").click(function () {
        $("#id_txt").val($(this).attr("id"))
        $("#buscar").dialog("open")
    });
    $("#buscar").dialog({
        title:"Cuentas presupuestarias",
        width:520,
        height:500,
        autoOpen:false,
        modal:true
    })
    $("#btn_buscar").click(function () {
        $.ajax({
            type:"POST",
            url:"${createLink(action:'buscarPresupuesto',controller:'asignacion')}",
            data:"parametro=" + $("#par").val() + "&tipo=" + $("#tipo").val(),
            success:function (msg) {
                $("#resultado").html(msg)
            }
        });
    });
    $('.datepicker').datepicker({
        changeMonth : true,
        changeYear  : true,
        dateFormat  : 'dd-mm-yy',
        minDate     : new Date(),
        onClose     : function (dateText, inst) {

        }
    });
    $("#guardar").button().click(function () {

    });
    $(".btn").button();
    $("#proyecto").change(function () {
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'componentesProyecto')}",
            data    : {
                id     : $("#proyecto").val()
            },
            success : function (msg) {
                $("#div_comp").html(msg)
            }
        });
    }).selectmenu({width : 600});
    $("#proyecto").selectmenu({width : 600});
    $("#comp").selectmenu({width : 600});
    $("#actividad").selectmenu({width : 600});
    $("#asignacion").selectmenu({width : 600});
    $("#compDest").selectmenu({width : 600});
    $("#proyectoDest").selectmenu({width : 600});
    $("#actividad_dest").selectmenu({width : 600});
    $("#asignacion_dest").selectmenu({width : 600});
    $("#firma").selectmenu({width : 300});
    $("#comp_nueva").selectmenu({width : 600});
    //    $("#actividad_nueva").selectmenu({width : 600});
    $("#tabs").tabs()
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

    $("#proyectoDest").change(function () {
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'componentesProyecto')}",
            data    : {
                id     : $("#proyectoDest").val(),
                idCombo: "compDest",
                div: "divAct_dest"
            },
            success : function (msg) {
                $("#div_comp_dest").html(msg)
            }
        });
    })
</script>
</body>
</html>