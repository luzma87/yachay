<%@ page import="yachay.contratacion.Solicitud" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <g:set var="entityName"
               value="${message(code: 'solicitud.label', default: 'Año')}"/>
        <title>Preparar reunión de aprobación</title>

        <g:set var="width" value="1000"/>

        <style type="text/css">
        .wide {
            height       : 29px;
            padding-left : 5px;
        }

        .short {
            width : 85px;
        }

        .tiny {
            width : 50px;
        }

        .check {
            width      : 22px;
            height     : 22px;
            padding    : 3px;
            background : #aaa;
            cursor     : pointer;
        }

        .original {
            color : antiquewhite !important;
        }
        </style>

    </head>

    <body>

        <div class="dialog">

            <div class="toolbar ui-widget-header ui-corner-all">
                <g:link action="list" class="btnList">
                    Lista de reuniones
                </g:link>
                <a href="#" id="btnReunion">
                    Ingresar la revisión de la DPI y Agendar reunión
                </a>
            </div> <!-- toolbar -->
            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                </g:if>
                <div class="list">
                    <div class="fg-toolbar ui-toolbar ui-widget-header ui-corner-top ui-helper-clearfix">
                        %{--<div id="example_length" class="dataTables_length">--}%
                        %{--<g:message code="show" default="Show"/>&nbsp;--}%
                        %{--<g:select from="${[10, 20, 30, 40, 50, 60, 70, 80, 90, 100]}" name="max" value="${params.max}"/>&nbsp;--}%
                        %{--<g:message code="entries" default="entries"/>--}%


                        %{--<g:select--}%
                        %{--from="['asc': message(code: 'asc', default: 'Ascendente'), 'desc': message(code: 'desc', default: 'Descendente')]"--}%
                        %{--name="order"--}%
                        %{--optionKey="key"--}%
                        %{--optionValue="value"--}%
                        %{--value="${params.order}"--}%
                        %{--class="ui-widget-content ui-corner-all"/>--}%
                        %{--</div>--}%
                    </div>
                    <table border="1">
                        <thead>
                            <tr>
                                <tdn:sortableColumn property="unidadEjecutora" class="ui-state-default"
                                                    title="Unidad Ejecutora"/>
                                <tdn:sortableColumn property="actividad" class="ui-state-default"
                                                    title="Actividad"/>
                                <tdn:sortableColumn property="fecha" class="ui-state-default"
                                                    title="Fecha"/>
                                <tdn:sortableColumn property="montoSolicitado" class="ui-state-default"
                                                    title="Monto Solicitado"/>
                                <tdn:sortableColumn property="tipoContrato" class="ui-state-default"
                                                    title="Modadlidad de contratación"/>
                                <tdn:sortableColumn property="nombreProceso" class="ui-state-default"
                                                    title="Nombre del proceso"/>
                                <tdn:sortableColumn property="plazoEjecucion" class="ui-state-default"
                                                    title="Plazo de ejecución"/>
                                <tdn:sortableColumn property="aprobacion" class="ui-state-default"
                                                    title="Reunión Aprobación"/>
                                <th class="ui-state-default">Agendar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${solicitudInstanceList}" status="i" var="solicitudInstance">
                                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                    <td>
                                        <g:link action="show" id="${solicitudInstance.id}">
                                            ${solicitudInstance.unidadEjecutora?.nombre}
                                        </g:link>
                                    </td>
                                    <td>${solicitudInstance.actividad?.objeto}</td>
                                    <td>${solicitudInstance.fecha?.format('dd-MM-yyyy')}</td>
                                    <td><g:formatNumber number="${solicitudInstance.montoSolicitado}" type="currency"/></td>
                                    <td>${solicitudInstance.tipoContrato?.descripcion}</td>
                                    <td>${solicitudInstance.nombreProceso}</td>
                                    <td><g:formatNumber number="${solicitudInstance.plazoEjecucion}" maxFractionDigits="0"/> días</td>
                                    <td>
                                        ${solicitudInstance.aprobacion ?
                                                solicitudInstance.aprobacion.fecha.format("dd-MM-yyyy HH:mm") :
                                                "No agendado"}
                                    </td>
                                    <td style="text-align: center;">
                                        <g:set var="checked" value="${solicitudInstance.aprobacionId == reunion?.id}"/>
                                        <div id="${solicitudInstance.id}" class="check ${checked ? 'checked original' : ''} ui-corner-all">
                                            <span class="fa fa-2x ${checked ? 'fa-check-square' : 'fa-square-o'}"></span>
                                        </div>
                                        %{--${solicitudInstance.aprobacionId == reunion?.id ? 'checked="checked"' : 'nope'}--}%
                                        %{--<input type="checkbox" class="chkReunion" id="${solicitudInstance.id}"--}%
                                        %{--${solicitudInstance.aprobacionId == reunion?.id ? 'checked' : ''}/>--}%
                                    </td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>

                <div class="fg-toolbar ui-toolbar ui-widget-header ui-corner-bottom ui-helper-clearfix paginateButtons">
                    <tdn:paginate total="${solicitudInstanceTotal}"/>
                </div>
            </div> <!-- body -->
        </div> <!-- dialog -->

    %{--<div id="dlgReunion" title="Agendar reunión">--}%
    %{--<p>Agendar reunión de aprobación con <span id="spanSolicitudes">0 solicitudes</span> para la fecha y hora:--}%
    %{--</p>--}%
    %{--<g:textField name="fechaReunion" class="datepicker wide short ui-widget-content ui-corner-all"--}%
    %{--value="${reunion?.fecha ? reunion.fecha.format('dd-MM-yyyy') : new Date().format('dd-MM-yyyy')}"/>--}%
    %{--<g:select from="${7..18}" name="horaReunion" class=" wide tiny ui-widget-content ui-corner-all"--}%
    %{--optionValue="${{ it.toString().padLeft(2, '0') }}"--}%
    %{--value="${reunion?.fecha ? reunion.fecha.format('HH') : new Date().format('HH')}"/>--}%
    %{--<g:select from="${0..11}" name="minutoReunion" class=" wide tiny ui-widget-content ui-corner-all"--}%
    %{--optionValue="${{ (it * 5).toString().padLeft(2, '0') }}"--}%
    %{--value="${reunion?.fecha ? reunion.fecha.format('mm').toInteger() / 5 : new Date().format('mm').toInteger() / 5}"/>--}%
    %{--</div>--}%

        <script type=" text/javascript">
            function validarSols() {
                var ids = "";
                $(".checked").each(function () {
                    ids += $(this).attr("id") + "_";
                });
                if (ids == "") {
                    $("#dlgAgendar").dialog("close");
                    $.box({
                        imageClass : "box_info",
                        title      : "Alerta",
                        text       : "Seleccione al menos una solicitud para agendar en la reunión",
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
                }
                return ids;
            }
            $(function () {

                $(".check").click(function () {
                    var $div = $(this);
                    var $check = $div.find("span");
                    if ($check.hasClass("fa-check-square")) {
                        $check.removeClass("fa-check-square").addClass("fa-square-o");
                        $div.removeClass("checked");
                    } else if ($check.hasClass("fa-square-o")) {
                        $check.removeClass("fa-square-o").addClass("fa-check-square");
                        $div.addClass("checked");
                    }
                });

                $(".datepicker").datepicker({
                    changeMonth : true,
                    changeYear  : true,
                    dateFormat  : 'dd-mm-yy',
                    minDate     : "+0"
                });

                $(".btnList").button({
                    icons : {
                        primary : "ui-icon-clipboard"
                    }
                });
                $("#btnReunion").button({
                    icons : {
                        primary : "ui-icon-note"
                    }
                }).click(function () {
                    var ids = validarSols();
                    if (ids != "") {
                        var id = "${reunion?.id}";

                        $.ajax({
                            type    : "POST",
                            url     : "${createLink(action:'agendarReunion_ajax')}",
                            data    : {
                                id  : id,
                                ids : ids
                            },
                            success : function (msg) {
                                $.box({
                                    id         : 'dlgAgendar',
                                    imageClass : false,
                                    title      : "Agendar reunión - Revisión",
                                    text       : msg,
                                    dialog     : {
                                        position : "top",
                                        width    : 700,
                                        buttons  : {
                                            "Aceptar"  : function (r) {
                                                $("#dlgReunion").dialog("close");
                                                $.box({
                                                    imageClass    : "box_info",
                                                    title         : "Espere",
                                                    text          : "Por favor espere",
                                                    iconClose     : false,
                                                    closeOnEscape : false,
                                                    dialog        : {
                                                        resizable     : false,
                                                        draggable     : false,
                                                        closeOnEscape : false,
                                                        buttons       : null
                                                    }
                                                });
                                                var data = {
                                                    fecha   : $.trim($("#fechaReunion").val()),
                                                    horas   : $("#horaReunion").val(),
                                                    minutos : $("#minutoReunion").val()
                                                };
                                                $(".txtRevision").each(function () {
                                                    data[$(this).attr("name")] = $(this).val();
                                                });
//                                                console.log(data);
                                                $.ajax({
                                                    type    : "POST",
                                                    url     : "${createLink(action: 'agendarReunion')}",
                                                    data    : data,
                                                    success : function (msg) {
                                                        //                                    console.log(msg);
                                                        location.reload(true);
                                                    }
                                                });
                                            },
                                            "Cancelar" : function () {
                                            }
                                        }
                                    }
                                });
                            }
                        });

////                        $("#dlgReunion").dialog("open");
////                        $("#spanSolicitudes").text(c + " solicitud" + (c == 1 ? "" : "es"));
                    }
                    return false;
                });

//                console.log("BBBBBBBBBBBBBBBB", 2, $(".warning"));
//                $(".warning").click(function () {
//                    console.log("ASDFAs");
//                    console.log($(this).attr("id"));
//                    return false;
//                });

            });
        </script>

    </body>
</html>
