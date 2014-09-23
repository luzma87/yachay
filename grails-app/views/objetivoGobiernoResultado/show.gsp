<%@ page import="yachay.parametros.proyectos.ObjetivoGobiernoResultado" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'objetivoGobiernoResultado.label', default: 'Objetivo de Gobierno por Resultados')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<div class="dialog" title="${title}">

    <div id="" class="toolbar ui-widget-header ui-corner-all">
               <g:link class="button list" action="list">
                   <g:message code="objetivoGobiernoResultado.list" default="Lista de Objetivos" />
               </g:link>
               <g:link class="button create" action="create">Nuevo Objetivo
               </g:link>
           </div> <!-- toolbar -->


    <div class="body">
        <g:if test="${flash.message}">
            <div class="message ui-state-highlight ui-corner-all">${flash.message}</div>
        </g:if>
        <div>

            <fieldset class="ui-corner-all">
                <legend class="ui-widget ui-widget-header ui-corner-all">
                    <g:message code="objetivoGobiernoResultado.show.legend"
                               default="Objetivo de Gobierno por Resultados"/>
                </legend>


                <div class="prop">
                    <label>
                        <g:message code="objetivoGobiernoResultado.id.label"
                                   default="Id"/>
                    </label>

                    <div class="campo">

                        ${fieldValue(bean: objetivoGobiernoResultadoInstance, field: "id")}

                    </div> <!-- campo -->
                </div> <!-- prop -->


                <div class="prop">
                    <label>
                        <g:message code="objetivoGobiernoResultado.descripcion.label"
                                   default="Descripcion"/>
                    </label>

                    <div class="campo">

                        ${fieldValue(bean: objetivoGobiernoResultadoInstance, field: "descripcion")}

                    </div> <!-- campo -->
                </div> <!-- prop -->


                <div class="buttons">
                    <g:link class="button edit" action="edit" id="${objetivoGobiernoResultadoInstance?.id}">
                        <g:message code="default.button.update.label" default="Edit"/>
                    </g:link>
                    <g:link class="button delete" action="delete" id="${objetivoGobiernoResultadoInstance?.id}">
                        <g:message code="default.button.delete.label" default="Delete"/>
                    </g:link>
                </div>

            </fieldset>
        </div>
    </div> <!-- body -->
</div> <!-- dialog -->

<script type="text/javascript">
    $(function() {
        $(".button").button();
        $(".home").button("option", "icons", {primary:'ui-icon-home'});
        $(".list").button("option", "icons", {primary:'ui-icon-clipboard'});
        $(".create").button("option", "icons", {primary:'ui-icon-document'});

        $(".edit").button("option", "icons", {primary:'ui-icon-pencil'});
        $(".delete").button("option", "icons", {primary:'ui-icon-trash'}).click(function() {
            if (confirm("${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}")) {
                return true;
            }
            return false;
        });
    });
</script>

</body>
</html>
