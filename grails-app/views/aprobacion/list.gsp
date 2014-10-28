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
        </style>
    </head>

    <body>

        <div class="dialog" title="${title}">
            <div id="" class="toolbar ui-widget-header ui-corner-all">
                <g:link class="button create" action="prepararReunionAprobacion">
                    Nueva reunión
                </g:link>
            </div> <!-- toolbar -->
            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                </g:if>
                <div class="list">
                    <div class="fg-toolbar ui-toolbar ui-widget-header ui-corner-top ui-helper-clearfix">
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
                                <tdn:sortableColumn property="fechaRealizacion" class="ui-state-default"
                                                    title="${message(code: 'aprobacion.fechaRealizacion.label', default: 'Fecha Realizacion')}"/>
                                <tdn:sortableColumn property="fecha" class="ui-state-default"
                                                    title="${message(code: 'aprobacion.fecha.label', default: 'Fecha')}"/>
                                <tdn:sortableColumn property="observaciones" class="ui-state-default"
                                                    title="${message(code: 'aprobacion.observaciones.label', default: 'Observaciones')}"/>

                                <tdn:sortableColumn property="asistentes" class="ui-state-default"
                                                    title="${message(code: 'aprobacion.asistentes.label', default: 'Asistentes')}"/>

                                <tdn:sortableColumn property="pathPdf" class="ui-state-default"
                                                    title="${message(code: 'aprobacion.pathPdf.label', default: 'Path Pdf')}"/>
                                <th class="ui-state-default">Estado</th>
                                <th class="ui-state-default">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${aprobacionInstanceList}" status="i" var="aprobacionInstance">
                                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                    <td>${aprobacionInstance.numero}</td>
                                    <td>
                                        <g:set var="solicitudes" value="${aprobacionInstance.solicitudes.size()}"/>
                                        <g:if test="${solicitudes > 0}">
                                            <a href="#" class="button btnVerSolicitudes" id="${aprobacionInstance.id}" title="Ver solicitudes a tratar">
                                                ${solicitudes} solicitud${solicitudes == 1 ? '' : 'es'}
                                            </a>
                                        </g:if>
                                        <g:else>
                                            - Sin solicitudes -
                                        </g:else>
                                    </td>
                                    <td>${aprobacionInstance.fechaRealizacion?.format("dd-MM-yyyy HH:mm")}</td>
                                    <td>
                                        ${aprobacionInstance.fecha.format("dd-MM-yyyy HH:mm")}
                                    </td>
                                    <td>${fieldValue(bean: aprobacionInstance, field: "observaciones")}</td>

                                    <td>${fieldValue(bean: aprobacionInstance, field: "asistentes")}</td>

                                    <td>${fieldValue(bean: aprobacionInstance, field: "pathPdf")}</td>
                                    <td>${aprobacionInstance.aprobada == 'A' ? 'Aprobada' : 'Pendiente'}</td>
                                    <td style="text-align: center;">
                                        %{--<g:if test="${!aprobacionInstance.fechaRealizacion}">--}%
                                        <g:if test="${aprobacionInstance.aprobada != 'A'}">
                                            <g:if test="${aprobacionInstance.solicitudes.size() > 0}">
                                                <g:link class="button btnSmall" action="reunion" id="${aprobacionInstance.id}">
                                                    <g:if test="${!aprobacionInstance.numero}">
                                                        Empezar
                                                    </g:if>
                                                    <g:else>
                                                        Continuar
                                                    </g:else>
                                                </g:link>
                                                <g:if test="${!aprobacionInstance.numero}">
                                                    <g:link class="button btnSmall" action="prepararReunionAprobacion" id="${aprobacionInstance.id}">
                                                        Preparar
                                                    </g:link>
                                                </g:if>
                                            </g:if>
                                            <g:else>
                                                <g:if test="${!aprobacionInstance.numero}">
                                                    <g:link class="button btnSmall" action="prepararReunionAprobacion" id="${aprobacionInstance.id}">
                                                        Preparar
                                                    </g:link>
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

                $(".btnVerSolicitudes").click(function () {
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'solicitudes_ajax')}/" + $(this).attr("id"),
                        success : function (msg) {
                            $.box({
                                imageClass : false,
                                title      : "Solicitudes",
                                text       : msg,
                                dialog     : {
                                    width   : 600,
                                    buttons : {
                                        "Aceptar" : function (r) {
                                        }
                                    }
                                }
                            });
                        }
                    });
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
