<%@ page import="app.Solicitud" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <g:set var="entityName"
               value="${message(code: 'solicitud.label', default: 'Año')}"/>
        <title>Lista de Solicitudes</title>

        <g:set var="width" value="1000"/>

    </head>

    <body>

        <div class="dialog">

            <div class="toolbar ui-widget-header ui-corner-all">
                <g:link class="button create" action="create">
                    Nueva solicitud
                </g:link>
            </div> <!-- toolbar -->

            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                </g:if>
                <div class="list" style="width: ${width}px;">
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
                    <table style="width: ${width}px;">
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

        <script type="text/javascript">
            $(function () {
                $(".button").button();
                $(".home").button("option", "icons", {primary : 'ui-icon-home'});
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
