
<%@ page import="app.DocumentoProceso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName"
               value="${message(code: 'documentoProceso.label', default: 'DocumentoProceso')}" />
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
                            <g:message code="documentoProceso.show.legend"
                                       default="DocumentoProceso details" />
                        </legend>

                        

                            <div class="prop">
                                <label>
                                    <g:message code="documentoProceso.id.label"
                                               default="Id" />
                                </label>

                                <div class="campo">
                                    
                                    ${fieldValue(bean: documentoProcesoInstance, field: "id")}
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="documentoProceso.liquidacion.label"
                                               default="Liquidacion" />
                                </label>

                                <div class="campo">
                                    
                                    <g:link controller="liquidacion" action="show"
                                                         id="${documentoProcesoInstance?.liquidacion?.id}">
                                        ${documentoProcesoInstance?.liquidacion?.encodeAsHTML()}
                                    </g:link>
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="documentoProceso.descripcion.label"
                                               default="Descripcion" />
                                </label>

                                <div class="campo">
                                    
                                    ${fieldValue(bean: documentoProcesoInstance, field: "descripcion")}
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="documentoProceso.tipo.label"
                                               default="Tipo" />
                                </label>

                                <div class="campo">
                                    
                                    <g:link controller="tipoDocumento" action="show"
                                                         id="${documentoProcesoInstance?.tipo?.id}">
                                        ${documentoProcesoInstance?.tipo?.encodeAsHTML()}
                                    </g:link>
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="documentoProceso.clave.label"
                                               default="Clave" />
                                </label>

                                <div class="campo">
                                    
                                    ${fieldValue(bean: documentoProcesoInstance, field: "clave")}
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="documentoProceso.resumen.label"
                                               default="Resumen" />
                                </label>

                                <div class="campo">
                                    
                                    ${fieldValue(bean: documentoProcesoInstance, field: "resumen")}
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="documentoProceso.documento.label"
                                               default="Documento" />
                                </label>

                                <div class="campo">
                                    
                                    ${fieldValue(bean: documentoProcesoInstance, field: "documento")}
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                        <div class="buttons">
                            <g:link class="button edit" action="edit" id="${documentoProcesoInstance?.id}">
                                <g:message code="default.button.update.label" default="Edit" />
                            </g:link>
                            <g:link class="button delete" action="delete" id="${documentoProcesoInstance?.id}">
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
