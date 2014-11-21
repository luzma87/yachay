<%@ page import="yachay.parametros.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Avances físicos</title>
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
        <div class="fila">
            <g:link controller="avales" action="listaProcesos" class="btn">Lista de procesos</g:link>
        </div>
        <fieldset style="width: 95%;height: 160px;" class="ui-corner-all">
            <legend>Proceso</legend>

            <div class="fila">
                <div class="labelSvt">Proyecto:</div>

                <div class="fieldSvt-xxxl">
                    ${proceso.proyecto}
                </div>
            </div>

            <div class="fila">

                <div class="labelSvt">Fecha inicio:</div>

                <div class="fieldSvt-small">
                    ${proceso.fechaInicio.format("dd-MM-yyyy")}
                </div>

                <div class="labelSvt">Fecha fin:</div>

                <div class="fieldSvt-small">
                    ${proceso.fechaFin.format("dd-MM-yyyy")}
                </div>
            </div>

            <div class="fila">
                <div class="labelSvt">Nombre:</div>

                <div class="fieldSvt-xxxl">
                    ${proceso.nombre}
                </div>
            </div>
        </fieldset>
        <fieldset style="width: 95%;height: 350px;overflow: auto" class="ui-corner-all">
            <legend>Sub actividades</legend>

            <div class="fila" style="margin-bottom: 10px;">
                <a href="#" class="btn" id="btnOpenDlg">Agregar</a>
            </div>

            <div id="detalle" style="width: 95%; height: 260px; overflow: auto;"></div>
        </fieldset>

        <g:if test="${proceso}">
            <div id="dlgNuevo">
                <div class="fila">
                    <div class="labelSvt">Aporte:</div>

                    <div class="fieldSvt-small">
                        <g:textField name="avance" class="ui-widget-content ui-corner-all" style="width: 50px;"/> %
                    </div>

                    <div class="labelSvt" style="width: 75px;">Fecha:</div>

                    <div class="fieldSvt-small">
                        <g:textField name="fecha" class="datepicker ui-widget-content ui-corner-all" style="width: 100px;"/>
                    </div>
                </div>

                <div class="fila" style="height: 95px;">
                    <div class="labelSvt">Descripción:</div>

                    <div class="fieldSvt-xxl">
                        <g:if test="${proceso}">
                            <g:textArea name="observaciones" rows="5" cols="68" class="ui-widget-content ui-corner-all"/>
                        </g:if>
                    </div>
                </div>
            </div>
        </g:if>

        <script type="text/javascript">
            var max = parseFloat("${maxAvance}");
            var min = parseFloat("${minAvance}");

            function loadTabla() {
                $.ajax({
                    type    : "POST",
                    url     : "${createLink(action:'avanceFisicoProceso_ajax')}",
                    data    : {
                        id : "${proceso.id}"
                    },
                    success : function (msg) {
                        $("#detalle").html(msg);
                    }
                });
            }

            function setMinDate(minDate) {
//                var date = Date.parse(minDate, "dd-MM-yyyy");
//                if (minDate != "") {
//                    $(".datepicker").datepicker("option", "minDate", date);
//                } else {
//                    $(".datepicker").datepicker("option", "minDate", new Date(1900, 0, 1));
//                }
            }

            function updateAll(msg) {
                var parts = msg.split("_");
                if (parts[0] == "OK") {
                    min = parseFloat(parts[1]);
                    max = parseFloat(parts[2]);
                    setMinDate(parts[3]);
                    loadTabla();
                }
            }

            $(function () {
                $("#dlgNuevo").dialog({
                    autoOpen : false,
                    modal    : true,
                    width    : 750,
                    title    : "Nueva sub actividad",
                    close    : function (event, ui) {
                        $("#avance").val("");
                        $("#fecha").val("");
                        $("#observaciones").val("");
                    },
                    buttons  : {
                        "Guardar"  : function () {
                            var avance = $.trim($("#avance").val());
                            var fecha = $.trim($("#fecha").val());
                            var obs = $.trim($("#observaciones").val());
                            var id = "${proceso.id}";
                            if (avance == "" || fecha == "" || obs.length < 1) {
                                $.box({
                                    imageClass : "box_info",
                                    text       : "Por favor ingrese el porcentaje de aportación, la fecha y la descripción de la sub actividad",
                                    title      : "Alerta",
                                    iconClose  : false,
                                    dialog     : {
                                        resizable     : false,
                                        draggable     : false,
                                        closeOnEscape : false,
                                        buttons       : {
                                            "Aceptar" : function () {
                                            }
                                        }
                                    }
                                });
                            } else {
                                if (isNaN(avance)) {
                                    $.box({
                                        imageClass : "box_info",
                                        text       : "Por favor ingrese un número válido en el porcentaje de avance",
                                        title      : "Alerta",
                                        iconClose  : false,
                                        dialog     : {
                                            resizable     : false,
                                            draggable     : false,
                                            closeOnEscape : false,
                                            buttons       : {
                                                "Aceptar" : function () {
                                                }
                                            }
                                        }
                                    });
                                } else {
                                    avance = parseFloat(avance);
//                                    if (avance > max || avance < min) {
                                    if (avance > max) {
                                        $.box({
                                            imageClass : "box_info",
                                            text       : "Por favor ingrese un número menor a " + max,
                                            title      : "Alerta",
                                            iconClose  : false,
                                            dialog     : {
                                                resizable     : false,
                                                draggable     : false,
                                                closeOnEscape : false,
                                                buttons       : {
                                                    "Aceptar" : function () {
                                                    }
                                                }
                                            }
                                        });
                                    } else {
                                        $.ajax({
                                            type    : "POST",
                                            url     : "${createLink(action:'addAvanceFisicoProceso_ajax')}",
                                            data    : {
                                                id            : id,
                                                avance        : avance,
                                                fecha         : fecha,
                                                observaciones : obs
                                            },
                                            success : function (msg) {
                                                updateAll(msg);
                                                var $d = $('#detalle');
                                                $d.animate({scrollTop : $d[0].scrollHeight}, 1000);
                                            }
                                        });
                                    }
                                }
                            }
                            $("#dlgNuevo").dialog("close");
                        },
                        "Cancelar" : function () {
                            $("#dlgNuevo").dialog("close");
                        }
                    }
                });
                $("#btnOpenDlg").button({
                    icons : {
                        primary : "ui-icon-plusthick"
                    }
                }).click(function () {
                    $("#dlgNuevo").dialog("open");
                    return false;
                });
                $(".btn").button();
                $(".datepicker").datepicker({
                    maxDate : "+0"
                });
                loadTabla();
                setMinDate("${minDate}")
            });
        </script>

    </body>
</html>