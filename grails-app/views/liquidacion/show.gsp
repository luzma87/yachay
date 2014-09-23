
<%@ page import="yachay.proyectos.Liquidacion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName"
               value="${message(code: 'liquidacion.label', default: 'Liquidacion')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
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
                            <g:message code="liquidacion.show.legend"
                                       default="Liquidacion details" />
                        </legend>

                        

                            <div class="prop">
                                <label>
                                    <g:message code="liquidacion.id.label"
                                               default="Id" />
                                </label>

                                <div class="campo">
                                    
                                    ${fieldValue(bean: liquidacionInstance, field: "id")}
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="liquidacion.obra.label"
                                               default="Obra" />
                                </label>

                                <div class="campo">
                                    
                                    <g:link controller="obra" action="show"
                                                         id="${liquidacionInstance?.obra?.id}">
                                        ${liquidacionInstance?.obra?.encodeAsHTML()}
                                    </g:link>
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="liquidacion.valor.label"
                                               default="Valor" />
                                </label>

                                <div class="campo">
                                    
                                    ${fieldValue(bean: liquidacionInstance, field: "valor")}
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="liquidacion.fechaRegistro.label"
                                               default="Fecha Registro" />
                                </label>

                                <div class="campo">
                                    
                                    <g:formatDate date="${liquidacionInstance?.fechaRegistro}" format="dd-MM-yyyy HH:mm" />
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="liquidacion.fechaAdjudicacion.label"
                                               default="Fecha Adjudicacion" />
                                </label>

                                <div class="campo">
                                    
                                    <g:formatDate date="${liquidacionInstance?.fechaAdjudicacion}" format="dd-MM-yyyy HH:mm" />
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="liquidacion.fechaInicio.label"
                                               default="Fecha Inicio" />
                                </label>

                                <div class="campo">
                                    
                                    <g:formatDate date="${liquidacionInstance?.fechaInicio}" format="dd-MM-yyyy HH:mm" />
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="liquidacion.fechaFin.label"
                                               default="Fecha Fin" />
                                </label>

                                <div class="campo">
                                    
                                    <g:formatDate date="${liquidacionInstance?.fechaFin}" format="dd-MM-yyyy HH:mm" />
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                        <div class="buttons">
                            <g:link class="button edit" action="edit" id="${liquidacionInstance?.id}">
                                <g:message code="default.button.update.label" default="Edit" />
                            </g:link>
                            <g:link class="button delete" action="delete" id="${liquidacionInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete" />
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
                    if(confirm("${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}")) {
                        return true;
                    }
                    return false;
                });
            });
        </script>

    </body>
</html>
