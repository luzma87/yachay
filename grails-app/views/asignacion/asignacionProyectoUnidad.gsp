<%--
  Created by IntelliJ IDEA.
  User: gato
  Date: 01/12/14
  Time: 12:52 PM
--%>

<%@ page import="yachay.parametros.UnidadEjecutora" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Asignaciones del proyecto: ${proyecto}</title>

    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'svt.css')}" type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}"
          type="text/css"/>

    <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript"
            language="JavaScript"></script>
    <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}"
            type="text/javascript" language="JavaScript"></script>
    <style type="text/css">

    th {
        background-color : #363636 !important;
        color: white;
    }
    .btn{
        font-size: 11px !important;
    }

    </style>
</head>

<body>
<div style="margin-left: 10px;">
    %{--<g:link class="btn" controller="asignacion" action="programacionAsignacionesInversion" id="${proyecto?.id}">Programación</g:link>--}%
    %{--<g:link class="btn" controller="asignacion" action="agregarAsignacionInv" id="${proyecto?.id}">Agregar asignaciones</g:link>--}%
    %{--<a class="btn" id="reporte">Reporte Asignaciones</a>--}%
    <a class="btn" id="reporteUnidad">Reporte Unidad</a>
    <g:if test="${actual?.estado==1}">
        <g:if test="${proyecto.aprobadoPoa=='S'}">
            <g:link class="btn" controller="modificacion" action="poaInversionesMod" id="${proyecto?.id}">Modificaciones</g:link>
        </g:if>
    </g:if>
    <g:if test="${actual?.estado==1}">
        <g:if test="${proyecto.aprobadoPoa!='S'}">
            <a href="#" id="aprobPrio">Aprobar priorización</a>
        </g:if>
    </g:if>
%{--&nbsp;&nbsp;&nbsp;<b style="font-size: 11px">Año:</b><g:select from="${yachay.parametros.poaPac.Anio.list([sort:'anio'])}" id="anio_asg" name="anio" optionKey="id" optionValue="anio" value="${actual?.id}" style="font-size: 11px"/>--}%
%{--&nbsp;&nbsp;&nbsp; <b style="font-size: 11px">Filtro: </b><g:select from="${['Todos','Componente', 'Responsable']}" name="filtro" style="font-size: 11px"/>--}%
    <div id="filtrados" style="margin-left: 375px"></div>

</div>
<fieldset class="ui-corner-all" style="width: 98%;margin-top: 40px;">
    <legend>Asignaciones para el año ${actual}</legend>
    <table style="width: 1040px;font-size: 10px">
        <thead>
        %{--<th>#</th>--}%

        %{--<th style="width: 200px">Programa</th>--}%
        <th>Proyecto</th>
        <th style="">Componente</th>
        <th>#</th>
        <th style="">Actividad</th>
        <th>Fechas</th>
        <th style="">Reponsable</th>
        <th style="width: 60px;">Partida</th>
        <th>Presupuesto</th>
        <g:if test="${actual?.estado==1}">
            <th>Priorizado</th>
        </g:if>
        <th></th>
        </thead>
        <tbody>
        <g:set var="total" value="${0}"></g:set>
        <g:each in="${asignaciones}" var="asg" status="i">
            <g:if test="${asg.planificado>0}">
                <g:if test="${actual?.estado==0}">
                    <g:set var="total" value="${total.toDouble()+asg.getValorReal()}"></g:set>
                </g:if>
                <g:else>
                    <g:set var="total" value="${total.toDouble()+asg.priorizado}"></g:set>
                </g:else>
            </g:if>
            <tr>
                %{--<td class="prog" style="width: 200px;"--}%
                %{--title="">--}%
                %{--${asg.marcoLogico?.proyecto?.programaPresupuestario.descripcion}--}%
                %{--</td>--}%
                <td class="" >
                    ${asg.marcoLogico.proyecto}
                </td>
                <td class=""
                    title="${asg.marcoLogico.marcoLogico.toStringCompleto()}">${asg.marcoLogico.marcoLogico}
                </td>
                <td>
                    ${asg.marcoLogico.numero}
                </td>
                <td class=""  title="${asg.marcoLogico.toStringCompleto()}">
                    ${asg.marcoLogico}
                </td>
                <td>
                    <b>Inicio: </b>${asg.marcoLogico.fechaInicio?.format("dd-MM-yyyy")}<br/>
                    <b>Fin: </b>${asg.marcoLogico.fechaFin?.format("dd-MM-yyyy")}
                </td>
                <td>
                    ${asg.unidad}
                </td>
                <td>
                    ${asg.presupuesto.numero}
                </td>
                <td class="valor" style="text-align: right">
                    <g:formatNumber number="${asg.getValorReal()}" format="###,##0" minFractionDigits="2"
                                    maxFractionDigits="2"/>
                </td>
                <g:if test="${actual.estado==1}">
                    <g:if test="${proyecto.aprobadoPoa!='S'}">
                        <td class="valor" style="text-align: right">
                            <div style="width: 150px">
                                <input type="text" style="width: 100px;text-align: right;display: inline-block" id="prio_${asg.id}" value="${asg.priorizado}">
                                <a href="#" style="width: 30px;display: inline-block" class="savePrio" iden="${asg.id}">Guardar</a>
                            </div>
                        </td>
                    </g:if><g:else>
                    <td class="valor" style="text-align: right">
                        <div style="width: 150px">
                            <g:formatNumber number="${asg.priorizado}" format="###,##0" minFractionDigits="2"
                                            maxFractionDigits="2"/>

                        </div>
                    </td>
                </g:else>
                </g:if>
                <td class="agr">
                    <g:if test="${actual.estado==0}">
                        <a href="#" class="btn_agregar" asgn="${asg.id}" proy="${proyecto.id}"
                           anio="${actual.id}">Dividir en dos partidas</a>
                        <g:if test="${asg.padre != null}">
                            <a href="#" class="btn_borrar" asgn="${asg.id}">Eliminar la Asignación</a>
                        </g:if>
                    </g:if>
                    <g:else>
                        <a href="#" class="btn_agregar_prio" asgn="${asg.id}" proy="${proyecto.id}" anio="${actual.id}">Dividir en dos partidas</a>
                        <g:if test="${asg.padre != null}">
                            <a href="#" class="btn_borrar_prio" asgn="${asg.id}">Eliminar la Asignación</a>
                        </g:if>
                    </g:else>
                </td>
                %{--<td>--}%
                %{--<a href="#" id="env_${i}" class="btn_env" asgn="${asg.id}" proy="${proyecto.id}" anio="${actual.id}" valor="${asg.getValorReal()}">Enviar a unidad ejectura</a>--}%
                %{--</td>--}%
            </tr>
        </g:each>
        <tr>

            <td><b>TOTAL</b></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td class="valor" style="text-align: right; font-weight: bold;">
                <g:formatNumber number="${total}" format="###,##0" minFractionDigits="2" maxFractionDigits="2"/>
            </td>


        </tr>
        </tbody>
    </table>

    <div id="ajx_asgn" style="width:520px;"></div>
    <div id="ajx_asgn_prio" style="width:520px;"></div>

    <div id="reporteDialogo" style="width:250px;">
        <div>Seleccione el año para generar el reporte.</div>
        <div style="margin-left: 100px; margin-top: 30px">
            <b>Año:</b><g:select from="${yachay.parametros.poaPac.Anio.list([sort:'anio'])}" id="anio-asg" name="anio" optionKey="id" optionValue="anio" value="${actual?.id}"/>
        </div>
    </div>

    <div id="reporteUnidadDialogo" style="width: 250px">
        <div>Seleccione el proyecto para generar el reporte.</div>
        <div style="margin-left: 100px; margin-top: 30px">
            <b>Proyecto:</b> <g:select from="${proyecto}" id="pro-asg" name="proye" optionKey="id"/>
        </div>
    </div>

    <div style="position: absolute;top:5px;right:10px;font-size: 10px;">
        <b>Total invertido proyecto actual:</b>
        <g:formatNumber number="${total?.toFloat()}"                        format="###,##0"
                        minFractionDigits="2" maxFractionDigits="2"/>
    </div>
    <div style="position: absolute;top:25px;right:10px;font-size: 10px;">
        <b>M&aacute;ximo Inversiones:</b>
        <g:formatNumber number="${maxInv}"
                        format="###,##0"
                        minFractionDigits="2" maxFractionDigits="2"/>
    </div>

    <div style="position: absolute;top:45px;right:10px;font-size: 10px;">
        <b>Restante:</b>
        <g:formatNumber number="${maxInv - total}"
                        format="###,##0"
                        minFractionDigits="2" maxFractionDigits="2"/>
    </div>
    <div id="dlg_env">
        <input type="hidden" id="env_id" value="">
        <input type="hidden" id="env_btn" value="">
        <input type="hidden" id="max" value="">
        <fieldset style="width: 450px;height: 160px;" class="ui-corner-all">
            <legend>Ingreso de datos</legend>
            Monto: <input type="text" id="monto_env" style="width: 100px;height: 20px;margin-left: 7px" class="ui-corner-all"> Máximo: <span id="lbl_max"></span><br> <br>
            Unidad: <g:select from="${UnidadEjecutora.list([sort:'nombre'])}" id="cmb_env" name="unrb" optionKey="id" optionValue="nombre" noSelection="['-1':'Seleccione...']" style="width: 400px" class="ui-corner-all"></g:select>  <br><br>
            <a href="#" class="btn" id="env">Enviar</a>
        </fieldset>
        <fieldset style="width: 450px;height: 400px;" class="ui-corner-all">
            <legend>Detalle</legend>
            <div id="detalle" style="width: 430px;height:360px;overflow-y: auto;margin: auto "></div>
        </fieldset>


    </div>



</fieldset>
<div id="load">
    <img src="${g.resource(dir:'images',file: 'loading.gif')}" alt="Procesando">
    Procesando
</div>
<script type="text/javascript">

    $("#filtro").change(function (){
        var aniof = $("#anio_asg").val();
        var camp = $("#filtro").val()

        if($("#filtro").val() != 'Todos'){
            $.ajax({
                type: "POST",
                url:"${createLink(action: 'filtro')}",
                data:"id=${proyecto.id}"+"&anio=" + aniof + "&camp=" + camp,
                success: function (msg){
                    $("#filtrados").html(msg)
                }
            });
        }else{
            location.href = "${createLink(controller:'asignacion',action:'asignacionProyectov2')}?id=${proyecto.id}&anio=" + aniof
        }
    })

    $("#aprobPrio").button().click(function(){
        if(confirm("Esta seguro?")){
            $.ajax({
                type:"POST",
                url:"${createLink(action:'aprobarPrio', controller: 'asignacion')}",
                data:"id=${proyecto.id}",
                success:function (msg) {
                    if(msg=="ok"){

                        location.reload(true)
                    }

                }
            });
        }

    });
    $(".btn_arbol").button({icons:{ primary:"ui-icon-bullet"}})
    $(".btn").button()
    $(".back").button("option", "icons", {primary:'ui-icon-arrowreturnthick-1-w'});

    $("#breadCrumb").jBreadCrumb({
        beginingElementsToLeaveOpen:10
    });

    $(".savePrio").button({icons:{ primary:"ui-icon-disk"},text:false}).click(function(){
        var id = $(this).attr("iden")
        var monto =$("#prio_"+id).val()
        $.ajax({
            type:"POST", url:"${createLink(action:'guardarPrio', controller: 'asignacion')}",
            data:"id="+id+"&prio="+monto,
            success:function (msg) {
                if(msg=="ok"){
                    $.box({
                        title:"Guardar",
                        text:"Datos guardados",
                        dialog: {
                            resizable: false,
                            buttons  : {
                                "Cerrar":function(){

                                }
                            }
                        }
                    });
                    location.reload(true)
                }

            }
        });

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

    $("#env").button().click(function(){
        var band = true
        if($("#cmb_env").val()=="-1"){
            alert("Seleccione una unidad ejecutora")
            band=false
        }else{

            var monto = $("#monto_env").val()
            monto = str_replace(".","",monto)
            monto = str_replace(",",".",monto)
            monto=monto*1
            if(isNaN(monto))
                monto=0
            if(monto<=0){
                alert("El monto deber ser un número mayor a 0")
                band=false
            }else{
                var max = $("#max").val()*1
                if(monto>max){
                    alert("El monto deber ser menor a "+number_format(max, 2, ",", "."))
                    band = false
                }
            }
        }


        if(band){
            $.ajax({
                type:"POST", url:"${createLink(action:'enviarUnidad', controller: 'asignacion')}",
                data:"id=" + $("#env_id").val() + "&unidad=" +$("#cmb_env").val()+"&monto="+monto,
                success:function (msg) {
                    $("#detalle").html(msg).show("slide")
                    var dist = $("#max").val()*1-monto
                    $("#lbl_max").html(number_format(dist, 2, ",", "."))
                }
            });
        }

    }) ;

    $(".btn_env").button({
        icons:{
            primary:"ui-icon-refresh"
        },
        text:false
    }).click(function(){
        $("#monto_env").val("0,00")
        $("#env_id").val($(this).attr("asgn"))
        $("#cmb_env").val($(this).attr("env"))
        $("#env_btn").val($(this).attr("id"))
        $("#max").val($(this).attr("valor"))
        //console.log("max 1" +$("#max").val())
        $.ajax({
            type:"POST", url:"${createLink(action:'enviarUnidad', controller: 'asignacion')}",
            data:"id=" + $("#env_id").val(),
            success:function (msg) {
                $("#dlg_env").dialog("open")
                $("#detalle").html(msg).show("slide")
                $("#lbl_max").html(number_format($("#max").val()*1, 2, ",", "."))
                //console.log("dis 1 " +$("#dist").val()*1)

            }
        });

    });

    $("#anio_asg").change(function () {
        location.href = "${createLink(controller:'asignacion',action:'asignacionProyectov2')}?id=${proyecto.id}&anio=" + $(this).val()
    });
    $(".btn_agregar").button({
        icons:{
            primary:"ui-icon-carat-2-n-s"
        },
        text:false
    }).click(function () {
        //alert ("id:" +$(this).attr("asgn"))1

        $.ajax({
            type:"POST", url:"${createLink(action:'agregaAsignacion', controller: 'asignacion')}",
            data:"id=" + $(this).attr("asgn") + "&proy=" + $(this).attr("proy") + "&anio=" + $(this).attr("anio"),
            success:function (msg) {
                $("#ajx_asgn").dialog("option", "title", "Dividir la asignación para ..")
                $("#ajx_asgn").html(msg).show("puff", 100)
            }
        });
        $("#ajx_asgn").dialog("open");

    });
    $(".btn_agregar_prio").button({
        icons:{
            primary:"ui-icon-carat-2-n-s"
        },
        text:false
    }).click(function () {
        $.ajax({
            type:"POST", url:"${createLink(action:'agregaAsignacionPrio', controller: 'asignacion')}",
            data:"id=" + $(this).attr("asgn") + "&proy=" + $(this).attr("proy") + "&anio=" + $(this).attr("anio"),
            success:function (msg) {
                $("#ajx_asgn_prio").dialog("option", "title", "Dividir la asignación para ..")
                $("#ajx_asgn_prio").html(msg).show("puff", 100)
            }
        });
        $("#ajx_asgn_prio").dialog("open");

    });

    $(".btn_borrar").button({
        icons:{
            primary:"ui-icon-trash"
        },
        text:false
    }).click(function () {
        $("#load").dialog("open")
        if (confirm("Eliminar esta asignación: \n Su valor se sumará a su asignación original y\n la programación deberá revisarse. La asignación no se eliminara si tiene distribuciones derivadas")) {

            $.ajax({
                type:"POST", url:"${createLink(action:'borrarAsignacion', controller: 'asignacion')}",
                data:"id=" + $(this).attr("asgn"),
                success:function (msg) {
                    if(msg=="ok")
                        location.reload(true);
                    else{
                        $("#load").dialog("close")
                        alert("Error al eliminar la asignación. Asegurese que no tenga distribuciones ni asignaciones hijas")
                    }

                }
            });
        }else{
            $("#load").dialog("close")
        }
    });
    $(".btn_borrar_prio").button({
        icons:{
            primary:"ui-icon-trash"
        },
        text:false
    }).click(function () {
        $("#load").dialog("open")
        if (confirm("Eliminar esta asignación: \n Su valor se sumará a su asignación original y\n la programación deberá revisarse. La asignación no se eliminara si tiene distribuciones derivadas")) {

            $.ajax({
                type:"POST", url:"${createLink(action:'borrarAsignacionPrio', controller: 'asignacion')}",
                data:"id=" + $(this).attr("asgn"),
                success:function (msg) {
                    if(msg=="ok")
                        location.reload(true);
                    else{
                        $("#load").dialog("close")
                        alert("Error al eliminar la asignación. Asegurese que no tenga distribuciones ni asignaciones hijas")
                    }

                }
            });
        }else{
            $("#load").dialog("close")
        }
    });


    $("#dlg_env").dialog({
        autoOpen:false,
        resizable:false,
        title:'Enviar esta asignación al P.O.A. de una unidad ejecutora',
        modal:true,
        draggable:true,
        width:520,
        height:750,
        position:'center',
        open:function (event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        buttons:{
            "Cerrar":function () {
                window.location.reload(true)
                $(this).dialog("close");
            }
        }
    });

    $("#ajx_asgn").dialog({
        autoOpen:false,
        resizable:false,
        title:'Crear un Perfil',
        modal:true,
        draggable:true,
        width:480,
        height:300,
        position:'center',
        open:function (event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        buttons:{
            "Grabar":function () {
                var asgn = $('#padre').val()
                var mxmo = parseFloat($('#maximo').val());
                var valor = str_replace(".", "", $('#vlor').val());
                valor = str_replace(",", ".", valor);
                valor = parseFloat(valor);
                //alert("Valores: maximo " + mxmo + " valor: " + valor);
                if (valor >= mxmo) {
                    alert("La nueva asignación debe ser menor a " + mxmo);
                } else {
                    var partida = $('#prsp2').val()
                    var fuente = $('#fuente').val();
                    $(this).dialog("close");
                    $.ajax({
                        type:"POST", url:"${createLink(action:'creaHijo', controller: 'asignacion')}",
                        data:"id=" + asgn + "&fuente=" + fuente + "&partida=" + partida + "&valor=" + valor,
                        success:function (msg) {
                            //alert("se ha creado la asignación: " + msg)
                            location.reload(true);

                        }
                    });
                }
            },
            "Cancelar":function () {
                $(this).dialog("close");
            }
        }
    });
    $("#ajx_asgn_prio").dialog({
        autoOpen:false,
        resizable:false,
        title:'Crear un Perfil',
        modal:true,
        draggable:true,
        width:480,
        height:300,
        position:'center',
        open:function (event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        buttons:{
            "Grabar":function () {
                var asgn = $('#padre').val()
                var mxmo = parseFloat($('#maximo').val());
                var valor = str_replace(".", "", $('#vlor').val());
                valor = str_replace(",", ".", valor);
                valor = parseFloat(valor);
                //alert("Valores: maximo " + mxmo + " valor: " + valor);
                if (valor >= mxmo) {
                    alert("La nueva asignación debe ser menor a " + mxmo);
                } else {
                    var partida = $('#prsp2').val()
                    var fuente = $('#fuente').val();
                    $(this).dialog("close");
                    $.ajax({
                        type:"POST", url:"${createLink(action:'creaHijoPrio', controller: 'asignacion')}",
                        data:"id=" + asgn + "&fuente=" + fuente + "&partida=" + partida + "&valor=" + valor,
                        success:function (msg) {
                            //alert("se ha creado la asignación: " + msg)
                            location.reload(true);

                        }
                    });
                }
            },
            "Cancelar":function () {
                $(this).dialog("close");
            }
        }
    });

    $("#reporteDialogo").dialog({
        autoOpen:false,
        resizable:false,
        title:'Reporte de Asignaciones del Proyecto',
        modal:true,
        draggable:true,
        width:350,
        height:200,
        position:'center',
        open:function (event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        buttons:{
            "Cancelar": function () {
                $(this).dialog("close");
            },
            "Aceptar":function () {
                var anio = $("#anio-asg").val();
                var url = "${createLink(controller: 'reportes2', action: 'reporteAsignacionProyecto')}?id=" + ${proyecto?.id} + "Wanio=" + anio;
                location.href = "${createLink(controller:'pdf',action:'pdfLink')}?url=" + url;
                $(this).dialog("close");
            }
        }
    });

    $("#reporte").click(function(){
        $("#reporteDialogo").dialog("open")
    })

    $("#reporte").button({

        icons: {
            primary: "ui-icon-print"
        }
    });

    $("#reporteUnidadDialogo").dialog({
        autoOpen:false,
        resizable:false,
        title:'Reporte de Asignaciones por Unidad',
        modal:true,
        draggable:true,
        width:350,
        height:200,
        position:'center',
        open:function (event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        buttons:{
            "Cancelar": function () {
                $(this).dialog("close");
            },
            "Aceptar":function () {
                var proyec = $("#pro-asg").val();
                %{--console.log("session " + ${session.unidad.id})--}%
                var url = "${createLink(controller: 'reportes2', action: 'reporteAsignacionUnidad')}?id=" + ${proyecto?.id} + "Wproy=" + proyec + "Wses=" + ${session.unidad.id};
                location.href = "${createLink(controller:'pdf',action:'pdfLink')}?url=" + url;
                $(this).dialog("close");
            }
        }
    });




    $("#reporteUnidad").click(function () {
        $("#reporteUnidadDialogo").dialog("open")
    });

    $("#reporteUnidad").button({
        icons: {
            primary: "ui-icon-print"
        }
    });


</script>
</body>
</html>