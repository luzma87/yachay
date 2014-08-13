<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Solicitud de certificación -- Unidad:${unidad}</title>

    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}" type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}" type="text/css"/>

    <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript" language="JavaScript"></script>
    <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}" type="text/javascript" language="JavaScript"></script>


    <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins', file: 'jquery.highlight-3.js')}"></script>

    <style type="text/css">
    .highlight {
        background-color : yellow
    }
    </style>
</head>

<body>
<g:link class="btn_arbol" controller="entidad" action="arbol_asg" style="margin: 10px;">Unidades ejecutoras</g:link>
<g:if test="${flash.message}">
    <div class="message ui-state-highlight ui-corner-all">
        ${flash.message}
    </div>
</g:if>
<div id="tabs" style="width: 950px;">
    <div id="tabs-1">
        <g:if test="${inversion}">
            <table>
                <thead>
                <th>Proyecto</th>
                <th>Actividad</th>
                <th>Partida</th>
                <th>Monto</th>
                <th>Certificaciones</th>
                </thead>
                <tbody>
                <g:each in="${inversion}" var="asg">
                    <tr>
                        <td class="proyecto">${asg.marcoLogico.proyecto}</td>
                        <td class="actividad">${asg.marcoLogico}</td>
                        <td class="partida">${asg.presupuesto}</td>
                        <td><g:formatNumber number="${asg.planificado}" format="###,##0" minFractionDigits="2" maxFractionDigits="2"/></td>
                        <g:if test="${asg.marcoLogico.fechaInicio>now}">
                            <td style="text-align: center">
                                <a href="#" class="btn_sol inv" id="${asg.id}" monto="${asg.planificado}">Solicitar aval</a>
                            </td>
                        </g:if>
                        <g:else>
                            <td style="text-align: center">
                                <a href="#" class="btn_sol_reprog inv" id="${asg.id}" monto="${asg.planificado}">Solicitar Reprogramacion</a>
                            </td>
                        </g:else>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            La unidad ejecutora seleccionada no tiene asignaciones de inversión para el año ${actual}.
        </g:else>
    </div>

</div>

<div id="ventana">
    <fieldset style="width: 400px;height: 200px;overflow: hidden" class="ui-corner-all">
        <legend>Avales</legend>

        <div id="tabla_cer" style="overflow: auto;height: 170px"></div>
    </fieldset>

    <input type="hidden" id="asg">
    <input type="hidden" id="maxVal">
    <a href="#" id="solicitar" class="btn">Solicitar</a>


    <div style="width: 400px;margin-top: 10px;background: rgba(24,111,255,0.48);height: 20px;line-height: 20px;padding-left: 10px;display: none" class="ui-corner-all" id="estado">

    </div>
</div>

<script>
    function reset() {
        $(".search").val("");
        $("td").removeHighlight();
        $(".Corrientes").show();
    }

    function search(tipo) {
        var elm = $("#txt_" + tipo);
        var txt = elm.val();
        var col = $("#sel_" + tipo).val();
        var cols = col.split(" ");

        var f = false;
        $("." + tipo).hide();
        $("td").removeHighlight();
        for (var i = 0; i < cols.length; i++) {
            var c = cols[i] + tipo;
            if (trim(txt) != "") {
                $("." + c + ":icontains('" + txt + "')").parents("tr").show();
                $("." + c).highlight(txt);
                f = true;
            }
        }
        if (!f) {
            reset();
        }
    }

    $(function () {

        reset();

        $(".sel").change(function () {
            var elm = $(this);
            var tipo = elm.attr("id");
            var parts = tipo.split("_");
            search(parts[1]);
        });

        $(".search").keyup(function (evt) {
            var elm = $(this);
            var tipo = elm.attr("id");
            var parts = tipo.split("_");
            search(parts[1]);
        });

        $(".btn_arbol").button({icons : { primary : "ui-icon-bullet"}})
        $("#tabs").tabs();
        $(".btn_sol_reprog").button({icons : { primary : "ui-icon-pencil"}, text : true})
        $(".btn_sol").button({icons : { primary : "ui-icon-disk"}, text : true}).click(function () {
            $("#asg").val($(this).attr("id"))
            $("#solicitar").show("slide")
            $("#datos").hide("slide")
            $("#error").hide("slide")
            $("#estado").hide()
            $("#monto").val("")
            $("#concepto").val("")
            $("#memo").val("")
            var monto = $(this).attr("monto") * 1
            $.ajax({
                type    : "POST", url : "${createLink(action:'cargarCertificados', controller: 'certificacion')}",
                data    : "id=" + $(this).attr("id"),
                success : function (msg) {
                    $("#tabla_cer").html(msg)
                    var max = $("#certificado").val() * 1
                    max = monto - max
                    $("#maxVal").val(max)
                    $("#maxTxt").html(number_format(max, 2, ",", "."))

                }
            });
            $("#ventana").dialog("open")
        });
        $(".btn").button()
        $("#solicitar").click(function () {
            location.href="${g.createLink(action: 'solicitarAval')}?asg="+$("#asg").val();
        });
        $("#ventana").dialog({
            autoOpen  : false,
            resizable : false,
            title     : 'Avales',
            modal     : true,
            draggable : true,
            width     : 500,
            height    : 600,
            position  : 'center',
            open      : function (event, ui) {
                $(".ui-dialog-titlebar-close").hide();
            },
            buttons   : {
                "Cerrar" : function () {
                    $("#ventana").dialog("close")
                }
            }
        });

        $("#enviar").click(function () {
            var monto = $("#monto").val()
            monto = str_replace(".", "", monto)
            monto = str_replace(",", ".", monto)
            monto = monto * 1
            var band = false
            var max = $("#maxVal").val() * 1
            if (isNaN(monto)) {
                monto = 0
            } else {
                monto = monto * 1
            }
            if (monto > max) {
                $("#error").html("El monto no puede ser mayor a " + max)
                band = true
            }
            var concepto = $("#concepto").val()
            if (concepto.length > 254) {
                $("#error").html("El campo concepto debe tener un máximo de 255 caracteres. Actual: " + concepto.length)
                band = true
            }
            var memo = $("#memo").val()
            if (memo.length > 254) {
                $("#error").html("El campo memorando debe tener un máximo de 40 caracteres. Actual: " + memo.length)
                band = true
            }

            if (memo == "" || concepto == "") {
                $("#error").html("Los campos memorando y concepto son obligatorios")
                band = true
            }

            if (monto < 1) {
                $("#error").html("El monto deber ser un número mayor a cero")
                band = true
            }
            if (!band) {
                $.ajax({
                    type    : "POST", url : "${createLink(action:'guardarSolicitud', controller: 'certificacion')}",
                    data    : "asgn=" + $("#asg").val() + "&monto=" + monto + "&concepto=" + concepto + "&memorando=" + memo,
                    success : function (msg) {
                        $("#estado").html(msg)
                        $("#estado").show("slide")
                        $("#datos").hide("explode")
                        $.ajax({
                            type    : "POST", url : "${createLink(action:'cargarCertificados', controller: 'certificacion')}",
                            data    : "id=" + $("#asg").val(),
                            success : function (msg) {
                                $("#tabla_cer").html(msg)
                                var max = $("#certificado").val() * 1
                                max = monto - max
                                $("#maxVal").val(max)
                                $("#maxTxt").html(number_format(max, 2, ",", "."))

                            }
                        });
                    }
                });
            } else {
                $("#error").show("pulsate")
            }
        });
    });
</script>

</body>
</html>