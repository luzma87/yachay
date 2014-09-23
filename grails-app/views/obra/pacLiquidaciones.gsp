<%@ page import="yachay.poa.Asignacion; yachay.proyectos.MarcoLogico" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>P.A.C. de la unidad: ${unidad}</title>

    <style type="text/css">
    .error {
        border-color : #892727;
        background   : #FFCCCC;
    }
    </style>

</head>

<body style="font-size: 10px;">

<div style="margin-left: 10px;">
    <g:link class="btn_arbol btn" controller="entidad" action="arbol_asg"
            style="margin-bottom: 5px;">Unidades Ejecutoras</g:link>
    <a class="btn_arbol btn"  href="${g.createLink(action: 'pacWeb',controller: 'reportes')}/?id=${unidad.id}&anio=${actual.id}">Reporte</a>
</div>

<div style="margin-left: 10px;"><b>Año:</b><g:select from="${yachay.parametros.poaPac.Anio.list([sort:'anio'])}" id="anio_asg"
                                                     name="anio" optionKey="id" optionValue="anio"
                                                     value="${actual.id}"/></div>

<div id="accordion" style="width:1030px">

<g:set var="totalPresupuestos" value="${0}"/>
<g:set var="totalCostos" value="${0}"/>

<fieldset class="ui-corner-all" style="position: relative;" id="paginate_container">
<div id="mascara" style="position: absolute;left: 0px;right: 0px;width: 100%;height: 100%;display: hidden;background: rgba(0,0,0,0.4)">
    <img src="${resource(dir: 'images',file: 'loading.gif')}" alt="">
</div>
<legend>Asignaciones corrientes de la unidad ejecutora: ${unidad}</legend>
<table style="margin-top: 40px;" id="paginate_table">
    <thead>
    <th style="width: 20px;">#</th>
    <th width="70">Fuente</th>
    <th width="75">Presupuesto</th>
    <th width="155">Descripci&oacute;n</th>
    <th width="100">Unidad</th>
    <th width="85">Codigo CPC</th>
    <th width="107">Tipo</th>
    <th width="48">Cant.</th>
    <th width="78">Costo</th>
    <th width="22">C1</th>
    <th width="22">C2</th>
    <th width="22">C3</th>
    <th width="16">Desc</th>
    <th width="16"></th>
    <th width="16"></th>
    <th width="16"></th>
    </thead>
    <tbody>
    <g:set var="cont"  value="${0}"></g:set>
    <g:each in="${asgs}" var="asg" status="i">
        <g:set var="obras" value="${yachay.proyectos.Obra.findAllByAsignacionAndObraIsNull(asg, [sort:'id'])}"></g:set>
        <g:if test="${obras.size() > 0}">
            <g:set var="asigs" value="${0}"/>
            <g:each in="${obras}" var="obra" status="k">
                <g:set var="cont"  value="${cont.toInteger()+1}"></g:set>
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td style="text-align: center;width: 20px;">${cont}</td>
                    <td class="fuente" style="font-size: 11px;">
                        <g:if test="${asigs == 0}">
                            ${(asg.fuente.toString().size() > 40) ? asg.fuente.toString().substring(0, 40) : asg.fuente}
                        </g:if>
                    </td>

                    <td class="asg" style="font-size: 11px; text-align: right;">
                        <g:if test="${asigs == 0}">
                            <input type="hidden" class="asg" value="${asg.id}">
                            <g:formatNumber number="${asg.planificado}" format="###,##0"
                                            minFractionDigits="2" maxFractionDigits="2"/>
                        </g:if>
                    </td>

                    <td>
                        ${asg.actividad} (${asg.presupuesto.numero})
                    </td>
                    <g:set var="asigs" value="${asigs+1}"/>

                    <td class="unidad">
                    ${obra.unidad}

                    </td>

                    <td class="cp">
                        <input type="hidden" class="cpac" value="${obra.codigoComprasPublicas.id}">
                        ${obra.codigoComprasPublicas.numero}

                    </td>

                    <td class="tipo">

                        ${obra.tipoCompra}
                    </td>

                    <td class="cantidad">

                              ${g.formatNumber(number: obra.cantidad, format: '###,##0', maxFractionDigits: 2, minFractionDigits: 2)}
                    </td>

                    <td class="costo">
                       ${g.formatNumber(number: obra.costo, format: '###,##0', maxFractionDigits: 2, minFractionDigits: 2)}
                    </td>

                    <td class="ctr1">
                        <input type="checkbox"
                               class="chk ctr1" ${(obra.cuatrimestre1 == "1") ? "checked" : ""} disabled>
                    </td>

                    <td class="ctr2">
                        <input type="checkbox"
                               class="chk ctr2" ${(obra.cuatrimestre2 == "1") ? "checked" : ""} disabled>
                    </td>

                    <td class="ctr3">
                        <input type="checkbox"
                               class="chk ctr3" ${(obra.cuatrimestre3 == "1") ? "checked" : ""} disabled>
                    </td>

                    <td class="desc_obs">
                        <input type="hidden" id="dscr_${k}${i}" class="desc"
                               value="${obra.descripcion}">

                        <input type="hidden" id="obs_${k}${i}" class="obs"
                               value="${obra.observaciones}">

                        <a href="#" class="btn_editar" desc="dscr_${k}${i}" obs="obs_${k}${i}">Ver</a>
                    </td>
                    <td>


                        <a href="#" class="btn guardar ajax" ml="${asg.id}" val="${obra.cantidad*obra.costo}" iden="${obra.id}"
                           icono="ico_${k}${i}${j}" max="${asg.planificado}">Liquidacion</a>

                    </td>


                </tr>
            </g:each>
        </g:if>

    </g:each>
    </tbody>
</table>



<div class="ui-corner-all" style="height: 15px;margin-top: 5px;">
    %{--<div style="width: 100px;float: left"><b>Monto:</b> ${act.monto}</div><div style="width: 100px;float: left;margin-left: 10px;"><b>Por asignar:</b></div> <div id="asignado_${act.id}" class="asignado" style="width: 100px;float: left" monto="${act.monto}">${(act.monto-asignado.toDouble()).toFloat().round(2)}</div><br>--}%
</div>

</fieldset>

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

<div id="dlg_desc_obs">
    <input type="hidden" id="hid_desc">

    <input type="hidden" id="hid_obs">
    <b>Descripción (255 caracteres):</b><br>

    <textArea name="desc" rows="6" cols="40" id="dlg_desc" disabled
              style="color: black"></textArea>
    <b>Observaciones (127 caracteres):</b><br>

    <textArea name="desc" rows="4" cols="40" id="dlg_obs" disabled
              style="color: black"></textArea> <br>

    <div id="dlg_error"
         style="width: 350px;height: 60px;margin-top: 5px;margin-left: 2px;display: none;padding: 3px;line-height: 24px;border:1px solid red;"
         class="ui-corner-all"></div>
</div>
<div id="dlg_liq" style="width: 500px;height: 400px;">
    <div id="cont_liq">

    </div>
</div>

%{--TODO validar al rato de guardar, poner en disabled los campos despues de guardar--}%

<script type="text/javascript">

    function sumas() {

        var tp = 0, tc = 0;

        $(".asg").each(function () {
            var v = $(this).text().trim();
            v = str_replace(".", "", v);
            v = str_replace(",", ".", v);
            v = parseFloat(v);
            if (!isNaN(v)) {
                tp += v;
            }
        });

        $("input.costo").each(function () {
            var v = $(this).val().trim();
            v = str_replace(".", "", v);
            v = str_replace(",", ".", v);
            v = parseFloat(v);
            if (!isNaN(v)) {
                var cant = parseFloat($(this).parent().prev().children().first().val());
                tc += (cant * v);
            }
        });

        $("#totalPresupuestos").html(number_format(tp, 2, ",", "."));
        $("#totalCostos").html(number_format(tc, 2, ",", "."));
    }

    function validarMax(asg, max) {
        var total = 0;

        var arr = new Array();

        $(".error").removeClass("error");

        $(".costo [asg=" + asg + "]").each(function () {
            var costo = $(this).val();
            var cant = parseFloat($(this).parent().prev().children().first().val());

            arr.push($(this).parent().prev().children().first());
            arr.push($(this));

            costo = str_replace(".", "", costo);
            costo = str_replace(",", ".", costo);
            costo = parseFloat(costo);
            $(this).val(number_format(costo, 2, ",", ".")).css({textAlign:"right"});

//                    //console.log(costo + " x " + cant + " = " + (costo * cant) + "          " + total);

            total += (cant * costo);
        });

        if (total > max) {
            for (var i = 0; i < arr.length; i++) {
                arr[i].addClass("error");
            }
            alert("La suma de los campos marcados con rojo (" + number_format(total, 2, ",", ".") + ") debe ser inferior a " + number_format(max, 2, ",", "."));
            return false;
        } else {
            return true;
        }
    }

    function paginacion(){
        $("#mascara").show()
        var registros = 10; //registros por pagina
        var total = $("#paginate_table").find("tbody").find("tr").size()
        var paginas = Math.ceil(total/registros)

        var i = 0
        $.each($("#paginate_table").find("tbody").find("tr"),function(i){
            if(i<registros){
                $(this).show().addClass("activo")
            }else{
                $(this).hide().addClass("inactivo")
            }
            i++
        });
        var paginate = $("<div>")
        paginate.attr("id","paginate_controls")
        paginate.css({width:$("#paginate_container").width(),height:25})
        for(i=0;i<paginas;i++){
            var pag= $("<div>")
            pag.css({width:20,height:18,background:"#ddd",margin:2,float:"left",textAlign:"center",paddingTop:3,border:"1px solid black",cursor:"pointer"})
            if(i==0){
                pag.css({background:"#3684C4"})
            }
            pag.html(i+1);
            pag.attr("num",i)
            pag.attr("reg",registros)
            pag.addClass(""+i)
            pag.bind("click",function(){
                recargar($(this).attr("num")*1,$(this).attr("reg")*1);
            })
            paginate.append(pag)
        }
        $("#paginate_container").append(paginate)
        $("#mascara").hide("explode")
    }
    function recargar(num,registros){
        $("#mascara").show()
        var i = 0;
        var inicio = num*registros

        $.each($("#paginate_table").find("tbody").find("tr"),function(i){
            if(i<inicio || i>(inicio+registros-1)){
                $(this).hide().addClass("inactivo")
            }else{
                $(this).show().addClass("activo")
            }
            i++
        });
        $("#paginate_controls").find("div").css({background:"#ddd"})
        $("#paginate_controls").find("."+num).css({background:"#3684C4"})
        $("#mascara").hide("explode")
    }

    $(function () {

        paginacion();


        $(".btn").button();


        $(".btn_editar").button({
            icons:{
                primary:"ui-icon-pencil"
            },
            text:false
        }).click(function () {
                    $("#hid_desc").val($(this).attr("desc"))
                    $("#hid_obs").val($(this).attr("obs"))
                    $("#dlg_desc").val($("#" + $(this).attr("desc")).val())
                    $("#dlg_obs").val($("#" + $(this).attr("obs")).val())
                    $("#dlg_error").hide().html("")
                    $("#dlg_desc_obs").dialog("open")
                });

        $("#anio_asg").change(function () {
            location.href = "${createLink(controller:'obra',action:'pacCorrientes')}?id=${unidad.id}&anio=" + $(this).val()
        });

        $(".guardar").button({
            icons:{
                primary:"ui-icon-bookmark"
            },
            text:false
        }).click(function () {
                    var id=$(this).attr("iden")
                    $.ajax({
                        type:"POST",
                        url:"${createLink(action:'datosLiquidacion',controller:'liquidacion')}",
                        data:{
                            id:id
                        },
                        success:function (msg) {
                            $("#cont_liq").html(msg)
                            $("#dlg_liq").dialog("open")
                        }
                    });
                });

        $(".buscar").click(function () {
            $("#id_txt").val($(this).attr("id"))
            $("#buscar").dialog("open")
        });
        $("#btn_buscar").click(function () {
            $.ajax({
                type:"POST",
                url:"${createLink(action:'buscarCcp',controller:'asignacion')}",
                data:"parametro=" + $("#par").val() + "&tipo=" + $("#tipo").val(),
                success:function (msg) {
                    $("#resultado").html(msg)
                }
            });
        });
        $("#buscar").dialog({
            title:"Cuentas presupuestarias",
            width:620,
            height:500,
            autoOpen:false,
            modal:true
        })
        $("#dlg_liq").dialog({
            title:"Cuentas presupuestarias",
            width:500,
            height:400,
            autoOpen:false,
            modal:true
        })
        $("#dlg_desc_obs").dialog({
            title:"Editar descripción y observaciones",
            width:400,
            height:400,
            autoOpen:false,
            modal:true,
            buttons:{
                "Aceptar":function () {
                    if ($("#dlg_desc").val().length < 255) {
                        if ($("#dlg_obs").val().length < 127) {
                            $("#" + $("#hid_desc").val()).val($("#dlg_desc").val())
                            $("#dlg_desc").val("")
                            $("#" + $("#hid_obs").val()).val($("#dlg_obs").val())
                            $("#dlg_obs").val("")
                            $("#dlg_desc_obs").dialog("close")
                        } else {
                            $("#dlg_error").html("El campo observaciones no puede contener mas de 127 caracteres. Actual(" + $("#dlg_obs").val().length + ")")
                            $("#dlg_error").addClass("error")
                            $("#dlg_error").show("pulsate")
                        }
                    } else {
                        $("#dlg_error").html("El campo descripción no puede contener mas de 255 caracteres. Actual(" + $("#dlg_dscr").val().length + ")")
                        $("#dlg_error").addClass("error")
                        $("#dlg_error").show("pulsate")
                    }
                }

            }
        })
    });
</script>

</body>
</html>