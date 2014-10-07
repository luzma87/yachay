<%@ page import="yachay.contratacion.Aprobacion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <g:set var="entityName"
               value="${message(code: 'aprobacion.label', default: 'Aprobación')}"/>
        <title><g:message code="default.show.label" args="[entityName]"/></title>
    </head>

    <body>

        <div class="dialog" title="${title}">

            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message ui-state-highlight ui-corner-all">${flash.message}</div>
                </g:if>
                <div>

                    <fieldset class="ui-corner-all">
                        <legend class="ui-widget ui-widget-header ui-corner-all">
                            <g:message code="aprobacion.show.legend"
                                       default="Detalles de la reunión de aprobación"/>
                        </legend>

                        <div class="prop">
                            <label>
                                <g:message code="aprobacion.fecha.label"
                                           default="Fecha planificada"/>
                            </label>

                            <div class="campo">

                                <g:formatDate date="${aprobacionInstance?.fecha}" format="dd-MM-yyyy HH:mm"/>

                            </div> <!-- campo -->
                        </div> <!-- prop -->

                        <div class="prop">
                            <label>
                                <g:message code="aprobacion.fechaRealizacion.label"
                                           default="Fecha de realización"/>
                            </label>

                            <div class="campo">

                                <g:formatDate date="${aprobacionInstance?.fechaRealizacion}" format="dd-MM-yyyy HH:mm"/>

                            </div> <!-- campo -->
                        </div> <!-- prop -->


                        <div class="prop">
                            <label>
                                <g:message code="aprobacion.asistentes.label"
                                           default="Asistentes"/>
                            </label>

                            <div class="campo">

                                ${fieldValue(bean: aprobacionInstance, field: "asistentes")}

                            </div> <!-- campo -->
                        </div> <!-- prop -->


                        <div class="prop">
                            <label>
                                <g:message code="aprobacion.observaciones.label"
                                           default="Observaciones"/>
                            </label>

                            <div class="campo">

                                ${fieldValue(bean: aprobacionInstance, field: "observaciones")}

                            </div> <!-- campo -->
                        </div> <!-- prop -->


                        <div class="prop">
                            <label>
                                <g:message code="aprobacion.pathPdf.label"
                                           default="Path Pdf"/>
                            </label>

                            <div class="campo">

                                ${fieldValue(bean: aprobacionInstance, field: "pathPdf")}

                            </div> <!-- campo -->
                        </div> <!-- prop -->

                        <div class="prop">
                            <label>
                                <g:message code="aprobacion.solicitudes.label"
                                           default="Solicitudes"/>
                            </label>

                            <div class="campo">

                                <ul>
                                    <g:each in="${aprobacionInstance.solicitudes}" var="s">
                                        <li>
                                            <g:link controller="solicitud"
                                                    action="show"
                                                    id="${s.id}">
                                                ${s?.objetoContrato}
                                            </g:link>
                                        </li>
                                    </g:each>
                                </ul>

                            </div> <!-- campo -->
                        </div> <!-- prop -->


                        <div class="buttons">
                            %{--<g:link class="button edit" action="edit" id="${aprobacionInstance?.id}">--}%
                            %{--<g:message code="default.button.update.label" default="Edit"/>--}%
                            %{--</g:link>--}%
                            <g:link class="button delete" action="delete" id="${aprobacionInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete"/>
                            </g:link>
                        </div>

                    </fieldset>
                </div>
            </div> <!-- body -->
        </div> <!-- dialog -->

        <script type="text/javascript">
            $(function () {
                $(".button").button();
                $(".home").button("option", "icons", {primary : 'ui-icon-home'});
                $(".list").button("option", "icons", {primary : 'ui-icon-clipboard'});
                $(".create").button("option", "icons", {primary : 'ui-icon-document'});

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
