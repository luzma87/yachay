<%@ page import="app.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Liberar aval ${aval.fechaAprobacion?.format("yyyy")}-CP No ${aval.numero}</title>
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

        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/ckeditor', file: 'ckeditor.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/ckeditor/adapters', file: 'jquery.js')}"></script>

        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins', file: 'jquery.meio.mask.js')}"></script>

    </head>

    <body>
        <g:if test="${flash.message}">
            <div class="message ui-state-highlight ui-corner-all">
                ${flash.message}
            </div>
        </g:if>
        <div class="fila">
            <g:link controller="revisionAval" action="listaAvales" class="btn">Regresar</g:link>
        </div>
        <g:form action="guardarLiberacion" class="frmLiberar" enctype="multipart/form-data">
            <input type="hidden" name="id" id="id" value="${aval.id}">

            <div class="fila">
                <div class="labelSvt" style="width: 250px; ">Contrato:</div>

                <div class="fieldSvt-medium">
                    <input type="text" name="contrato" class="ui-corner-all" id="contrato" style="width: 100%">
                </div>
            </div>

            <div class="fila">
                <div class="labelSvt" style="width: 250px; ">Certificación presupuestaria:</div>

                <div class="fieldSvt-medium">
                    <input type="text" name="certificacion" class="ui-corner-all" id="certificacion" style="width: 100%">
                </div>
            </div>

            <div class="fila" style="margin-top: 35px">
                <div class="labelSvt" style="width: 250px; ">Documento de respaldo:</div>

                <div class="fieldSvt-large">
                    <input type="file" id="archivo" name="archivo" style="display: inline-block">
                </div>
            </div>
            <input type="hidden" id="datos" name="datos">
            <input type="hidden" id="monto" name="monto">
        </g:form>
        <fieldset>
            <legend>Detalle</legend>
            <table style="width: 100%">
                <thead>
                    <tr>
                        <th>Componente</th>
                        <th>Actividad</th>
                        <th>Partida</th>
                        <th>Priorizado</th>
                        <th>Monto</th>
                        <th>Monto Avalado</th>
                    </tr>
                </thead>
                <tbody>
                    <g:set var="total" value="${0}"></g:set>
                    <g:each in="${detalle}" var="asg">
                        <g:set var="total" value="${total.toDouble() + asg.monto}"></g:set>
                        <tr>
                            <td>${asg.asignacion.marcoLogico.marcoLogico}</td>
                            <td>${asg.asignacion.marcoLogico.numero} - ${asg.asignacion.marcoLogico}</td>
                            <td>${asg.asignacion.presupuesto.numero}</td>
                            <td style="text-align: right">
                                <g:formatNumber number="${asg.asignacion.priorizado}" format="###,##0" minFractionDigits="2" maxFractionDigits="2"/>
                            </td>
                            <td style="text-align: right" id="monto_${asg.id}">
                                <g:formatNumber number="${asg.monto}" format="###,##0" minFractionDigits="2" maxFractionDigits="2"/>
                            </td>
                            <td style="text-align: right">
                                <input type="text" class="det_${asg.id} detalle decimal" iden="${asg.id}"
                                       value="${g.formatNumber(number: asg.monto, maxFractionDigits: 2, minFractionDigits: 2)}"
                                       style="width: 100px;text-align: right" max="${asg.monto}">
                            </td>

                        </tr>
                    </g:each>
                    <tr>
                        <td colspan="4" style="font-weight: bold">TOTAL PROCESO</td>
                        <td style="font-weight: bold;text-align: right"><g:formatNumber number="${total}" format="###,##0" minFractionDigits="2" maxFractionDigits="2"></g:formatNumber></td>
                        <td style="font-weight: bold;text-align: right" id="total" valor="${total}"><g:formatNumber number="${total}" format="###,##0" minFractionDigits="2" maxFractionDigits="2"></g:formatNumber></td>
                    </tr>
                </tbody>
            </table>

        </fieldset>

        <div class="fila">
            <a href="#" class="btn" id="guardar">Guardar</a>
        </div>
        <script>
            $(".btn").button();
            $(".detalle").blur(function () {
                calcular();
            });

            $(".decimal").setMask("decimal");

            $("#guardar").click(function () {
                var file = $("#archivo").val();
                var contrato = $("#contrato").val();
                var certificacion = $("#certificacion").val();
                var monto = $("#total").attr("valor");
                monto = monto.replace(new RegExp("\\.", 'g'), "");
                monto = monto.replace(new RegExp(",", 'g'), ".");
                //console.log(monto)
                //monto=monto.replace(/\./g,"")
                //monto=monto.replace(/,/g,".")
//        console.log("desp",monto)
                var msg = "";
                if (monto == "") {
                    msg += "<br>Ingrese los montos avalados en la sección de detalle."
                }
                if (isNaN(monto)) {
                    msg += "<br>Ingrese los montos avalados en la sección de detalle."
                } else {
                    if (monto * 1 < 0) {
                        msg += "<br>El total debe ser un número positivo mayor a cero."
                    }
                }
                if (contrato == "") {
                    msg += "<br>Ingrese un número de contrato."
                }
                if (certificacion == "") {
                    msg += "<br>Ingrese un número de certificación presupuestaria."
                }

                if (file.length < 1) {
                    msg += "<br>Por favor seleccione un archivo."
                } else {
                    var ext = file.split('.').pop();
                    if (ext != "pdf") {
                        msg += "<br>Por favor seleccione un archivo de formato pdf. El formato " + ext + " no es aceptado por el sistema"
                    }
                }
                if (msg == "") {
                    $(".frmLiberar").submit()
                } else {
                    $("#monto").val(monto);
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
            function calcular() {

                var total = 0;
                $("#datos").val("");
                $(".detalle").each(function () {
                    var monto = $(this).val();
                    monto = monto.replace(new RegExp("\\.", 'g'), "");
                    monto = monto.replace(new RegExp(",", 'g'), ".");
                    var max = $(this).attr("max");
                    if (monto == "")
                        monto = 0;
                    if (isNaN(monto))
                        monto = 0;
                    else
                        monto = monto * 1;
                    if (monto == 0)
                        $(this).val(monto);
                    if (monto > max * 1) {
                        $.box({
                            title  : "Error",
                            text   : "El monto avalado no puede ser mayor al monto planificado",
                            dialog : {
                                resizable : false,
                                buttons   : {
                                    "Cerrar" : function () {

                                    }
                                }
                            }
                        });
                        $(this).val(0)
                        monto = 0
                    }
                    $("#datos").val($("#datos").val() + $(this).attr("iden") + ";" + monto + "&");
                    total += monto
                });
                $("#total").html(total);
                $("#total").attr("valor", total)
            }

        </script>
    </body>
</html>