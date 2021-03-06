<%@ page import="yachay.contratacion.Aprobacion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <g:set var="entityName"
               value="${message(code: 'aprobacion.label', default: 'Aprobacion')}"/>
        <title>Reuniones de aprobación</title>

        <style type="text/css">
        .btnSmall {
            font-size : 10px !important;
        }

        .btnSmall + .btnSmall {
            margin-top : 5px;
        }

        .ulSolicitudes {
            margin       : 0;
            padding-left : 20px;
        }
        </style>
    </head>

    <body>

        <g:set var="editables" value="${['ASPL', 'DP', 'GP']}"/> <!-- Asistente, Director, Gerente de Planificacion son los que pueden editar -->

        <div class="dialog" title="${title}">
            <g:if test="${editables.contains(session.perfil.codigo)}">
                <div id="" class="toolbar ui-widget-header ui-corner-all">
                    <g:link class="button create" action="prepararReunionAprobacion">
                        Nueva reunión
                    </g:link>

                    <a href="#" class="button print">
                        PDF aprobadas
                    </a>

                    <g:link controller="reporteSolicitud" action="aprobadasXLS" class="button xls">
                        XLS aprobadas
                    </g:link>
                </div> <!-- toolbar -->
            </g:if>

            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                </g:if>
                <div class="list">
                    <div class="fg-toolbar ui-toolbar ui-widget-header ui-corner-top ui-helper-clearfix">
                        <div id="example_length" class="dataTables_length">
                            Buscar (nombre del proceso)
                            <g:textField name="txtBuscar" class="ui-widget-content ui-corner-all" value="${params.search}"/>
                            <a href="#" class="button" id="btnBuscar">Buscar</a>
                        </div>
                        %{--<div id="example_length" class="dataTables_length">--}%
                        %{--<g:message code="show" default="Show" />&nbsp;--}%
                        %{--<g:select from="${[10,20,30,40,50,60,70,80,90,100]}" name="max" value="${params.max}" />&nbsp;--}%
                        %{--<g:message code="entries" default="entries" />--}%


                        %{--<g:select--}%
                        %{--from="['asc':message(code:'asc', default:'Ascendente'), 'desc':message(code:'desc', default:'Descendente')]"--}%
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
                                <th class="ui-state-default">N.</th>
                                <th class="ui-state-default">Solicitudes a tratar</th>
                                <tdn:sortableColumn property="fecha" class="ui-state-default" style="width:65px;"
                                                    title="${message(code: 'aprobacion.fecha.label', default: 'Fecha')}"/>
                                <tdn:sortableColumn property="observaciones" class="ui-state-default"
                                                    title="${message(code: 'aprobacion.observaciones.label', default: 'Observaciones')}"/>

                                <tdn:sortableColumn property="asistentes" class="ui-state-default"
                                                    title="${message(code: 'aprobacion.asistentes.label', default: 'Asistentes')}"/>
                                <th class="ui-state-default">Estado</th>
                                <th class="ui-state-default">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${aprobacionInstanceList}" status="i" var="aprobacionInstance">
                                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                    <td>${aprobacionInstance.numero}</td>
                                    <td>
                                        <g:set var="solicitudes" value="${yachay.contratacion.Solicitud.findAllByAprobacion(aprobacionInstance, [sort: 'nombreProceso'])}"/>
                                        <g:if test="${solicitudes.size() > 0}">
                                        %{--<a href="#" class="button btnVerSolicitudes" id="${aprobacionInstance.id}" title="Ver solicitudes a tratar">--}%
                                        %{--${solicitudes} solicitud${solicitudes == 1 ? '' : 'es'}--}%
                                        %{--</a>--}%
                                            <ul class="ulSolicitudes">
                                                <g:each in="${solicitudes}" var="sol">
                                                    <li>${sol.unidadEjecutora.nombre} - ${sol.nombreProceso}</li>
                                                </g:each>
                                            </ul>
                                        </g:if>
                                        <g:else>
                                            - Sin solicitudes -
                                        </g:else>
                                    </td>
                                    <td>
                                        <g:if test="${aprobacionInstance.fecha}">
                                            ${aprobacionInstance.fecha.format("dd-MM-yyyy HH:mm")}
                                        </g:if>
                                        <g:else>
                                            <a href="#" class="button btnSmall btnSetFecha" id="${aprobacionInstance.id}">
                                                Establecer
                                            </a>
                                        </g:else>
                                    </td>
                                    <td>${fieldValue(bean: aprobacionInstance, field: "observaciones")}</td>

                                    <td>${fieldValue(bean: aprobacionInstance, field: "asistentes")}</td>

                                    <td>${aprobacionInstance.aprobada == 'A' ? 'Aprobada' : 'Pendiente'}</td>
                                    <td style="text-align: center;">
                                        %{--<g:if test="${!aprobacionInstance.fechaRealizacion}">--}%
                                        <g:if test="${aprobacionInstance.aprobada != 'A'}">
                                            <g:if test="${aprobacionInstance.solicitudes.size() > 0}">
                                                <g:if test="${editables.contains(session.perfil.codigo)}">
                                                    <g:link class="button btnSmall" action="reunion" id="${aprobacionInstance.id}">
                                                        <g:if test="${!aprobacionInstance.numero}">
                                                            Empezar
                                                        </g:if>
                                                        <g:else>
                                                            Continuar
                                                        </g:else>
                                                    </g:link>
                                                </g:if>
                                                <g:if test="${!aprobacionInstance.numero}">
                                                    <g:if test="${editables.contains(session.perfil.codigo)}">
                                                        <g:link class="button btnSmall" action="prepararReunionAprobacion" id="${aprobacionInstance.id}">
                                                            Preparar
                                                        </g:link>
                                                    </g:if>
                                                </g:if>
                                            </g:if>
                                            <g:else>
                                                <g:if test="${editables.contains(session.perfil.codigo)}">
                                                    <g:if test="${!aprobacionInstance.numero}">
                                                        <g:link class="button btnSmall" action="prepararReunionAprobacion" id="${aprobacionInstance.id}">
                                                            Preparar
                                                        </g:link>
                                                    </g:if>
                                                </g:if>
                                            </g:else>
                                        </g:if>
                                        %{--<g:else>--}%
                                        <g:link class="button btnSmall" action="reunion" id="${aprobacionInstance.id}" params="[show: 1]">
                                            Ver
                                        </g:link>
                                        %{--</g:else>--}%
                                    </td>

                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>

                <div class="fg-toolbar ui-toolbar ui-widget-header ui-corner-bottom ui-helper-clearfix paginateButtons">
                    <tdn:paginate total="${aprobacionInstanceTotal}"/>
                </div>
            </div> <!-- body -->
        </div> <!-- dialog -->

        <script type="text/javascript">
            $(function () {
                $(".button").button();
                $(".home").button("option", "icons", {primary : 'ui-icon-home'});
                $(".create").button("option", "icons", {primary : 'ui-icon-document'});
                $(".xls").button("option", "icons", {primary : 'ui-icon-note'});

                $("#btnBuscar").button({
                    icons : {primary : 'ui-icon-search'},
                    text  : false
                }).click(function () {
                    var search = $.trim($("#txtBuscar").val());
                    location.href = "${createLink(controller: 'aprobacion', action:'list')}?search=" + search;
                    return false;
                });
                $("#txtBuscar").keyup(function (ev) {
                    if (ev.keyCode == 13) {
                        var search = $.trim($("#txtBuscar").val());
                        location.href = "${createLink(controller: 'aprobacion', action:'list')}?search=" + search;
                    }
                });

                $(".print").button("option", "icons", {primary : 'ui-icon-print'}).click(function () {
                    var url = "${createLink(controller: 'reporteSolicitud', action: 'aprobadas')}";
                    location.href = "${createLink(controller:'pdf',action:'pdfLink')}?url=" + url + "&filename=solicitudes.pdf";
                    return false;
                });

                $(".btnSetFecha").click(function () {
                    var id = $(this).attr("id");
                    var $html = $("<div>");
                    var $input = $("<input type='text' readonly='readonly' style='width: 80px;' value='${new Date().format('dd-MM-yyyy')}' " +
                                   "class='datepicker ui-widget-content ui-corner-all' name='fecha' id='fecha' />");
                    $input.datepicker({
                        dateFormat : 'dd-mm-yy',
                        minDate    : "+0"
                    });
                    var $selH = $("<select name='horas' class='ui-widget-content ui-corner-all' style='margin-left: 10px;'>");
                    for (var i = 7; i < 19; i++) {
                        var str = "" + i;
                        var pad = "00";
                        str = pad.substring(0, pad.length - str.length) + str;
                        var $opt = $("<option value='" + i + "'>" + str + "</option>");
                        $selH.append($opt);
                    }
                    var $selM = $("<select name='minutos' class='ui-widget-content ui-corner-all'>");
                    for (i = 0; i < 60; i += 5) {
                        str = "" + i;
                        pad = "00";
                        str = pad.substring(0, pad.length - str.length) + str;
                        $opt = $("<option value='" + i + "'>" + str + "</option>");
                        $selM.append($opt);
                    }
                    $html.append($input).append($selH).append(":").append($selM);
                    $.box({
                        imageClass : "box_alert",
                        input      : $html,
                        type       : "prompt",
                        title      : "Fecha",
                        text       : "Ingrese la fecha deseada para la reunión seleccionada",
                        dialog     : {
                            buttons : {
                                "Aceptar" : function () {
                                    $.ajax({
                                        type    : "POST",
                                        url     : "${createLink(action:'setFechaReunion_ajax')}",
                                        data    : {
                                            id      : id,
                                            fecha   : $input.val(),
                                            horas   : $selH.val(),
                                            minutos : $selM.val()
                                        },
                                        success : function (msg) {
                                            location.reload(true);
                                        }
                                    });
                                }
                            }
                        }
                    });
                    return false;
                });

                $(".edit").button("option", "icons", {primary : 'ui-icon-pencil'});
                $(".delete").button("option", "icons", {primary : 'ui-icon-trash'}).click(function () {
                    if (confirm("${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}")) {
                        return true;
                    }
                    return false;
                });
            });
        </script>

    </body>
</html>
