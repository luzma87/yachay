
<%@ page import="app.Contratista" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName"
               value="${message(code: 'contratista.label', default: 'Contratista')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>

    <body>

        %{--<div class="dialog" title="Detalle del Contratista">--}%


            <div class="body" style="margin-left: 400px">
                <g:if test="${flash.message}">
                    <div class="message ui-state-highlight ui-corner-all">${flash.message}</div>
                </g:if>
                <div>

                    <fieldset class="ui-corner-all">
                        <legend class="ui-widget ui-widget-header ui-corner-all">
                            <g:message code="contratista.show.legend"
                                       default="Detalle del Contratista" />
                        </legend>

                        

                            <div class="prop">
                                <label>
                                    <g:message code="contratista.id.label"
                                               default="Id" />
                                </label>

                                <div class="campo">
                                    
                                    ${fieldValue(bean: contratistaInstance, field: "id")}
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        

                            <div class="prop">
                                <label>
                                    <g:message code="contratista.ruc.label"
                                               default="Ruc" />
                                </label>

                                <div class="campo">
                                    
                                    ${fieldValue(bean: contratistaInstance, field: "ruc")}
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->

                        <div class="prop">
                            <label>
                                <g:message code="contratista.nombreCont.label"
                                           default="Contratista" />
                            </label>

                            <div class="campo">

                                ${fieldValue(bean: contratistaInstance, field: "nombreCont")}

                            </div> <!-- campo -->
                        </div> <!-- prop -->




                        <div class="prop">
                            <label>
                                <g:message code="contratista.nombre.label"
                                           default="Nombre" />
                            </label>

                            <div class="campo">

                                ${fieldValue(bean: contratistaInstance, field: "nombre")}

                            </div> <!-- campo -->
                        </div> <!-- prop -->


                        <div class="prop">
                            <label>
                                <g:message code="contratista.apellido.label"
                                           default="Apellido" />
                            </label>

                            <div class="campo">

                                ${fieldValue(bean: contratistaInstance, field: "apellido")}

                            </div> <!-- campo -->
                        </div> <!-- prop -->


                        <div class="prop">
                                <label>
                                    <g:message code="contratista.direccion.label"
                                               default="Dirección" />
                                </label>

                                <div class="campo">
                                    
                                    ${fieldValue(bean: contratistaInstance, field: "direccion")}
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->

                        <div class="prop">
                            <label>
                                <g:message code="contratista.telefono.label"
                                           default="Teléfono" />
                            </label>

                            <div class="campo">

                                ${fieldValue(bean: contratistaInstance, field: "telefono")}

                            </div> <!-- campo -->
                        </div> <!-- prop -->




                        <div class="prop">
                            <label>
                                <g:message code="contratista.mail.label"
                                           default="Mail" />
                            </label>

                            <div class="campo">

                                ${fieldValue(bean: contratistaInstance, field: "mail")}

                            </div> <!-- campo -->
                        </div> <!-- prop -->


                        <div class="prop">
                                <label>
                                    <g:message code="contratista.fecha.label"
                                               default="Fecha" />
                                </label>

                                <div class="campo">
                                    
                                    <g:formatDate date="${contratistaInstance?.fecha}" format="dd-MM-yyyy" />
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        



                        


                            <div class="prop">
                                <label>
                                    <g:message code="contratista.estado.label"
                                               default="Estado" />
                                </label>

                                <div class="campo">
                                    
                                    ${fieldValue(bean: contratistaInstance, field: "estado")}
                                    
                                </div> <!-- campo -->
                            </div> <!-- prop -->


                        <div class="prop">
                            <label>
                                <g:message code="contratista.observaciones.label"
                                           default="Observaciones" />
                            </label>

                            <div class="campo">

                                ${fieldValue(bean: contratistaInstance, field: "observaciones")}

                            </div> <!-- campo -->
                        </div> <!-- prop -->





                        <div class="buttons">
                            <g:link class="button edit" action="edit" id="${contratistaInstance?.id}">
                                <g:message code="default.button.update.label" default="Edit" />
                            </g:link>
                            <g:link class="button delete" action="delete" id="${contratistaInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete" />
                            </g:link>
                        </div>

                    </fieldset>
                </div>
            </div> <!-- body -->
            %{--</div> <!-- dialog -->--}%

         <script type="text/javascript">
            $(function() {
                $(".button").button();
                $(".home").button("option", "icons", {primary:'ui-icon-home'});
                $(".list").button("option", "icons", {primary:'ui-icon-clipboard'});
                $(".create").button("option", "icons", {primary:'ui-icon-document'});

                $(".edit").button("option", "icons", {primary:'ui-icon-pencil'});
                $(".delete").button("option", "icons", {primary:'ui-icon-trash'}).click(function() {
                    if(confirm("${message(code: 'default.button.delete.confirm.message', default: 'Esta seguro?')}")) {
                        return true;
                    }
                    return false;
                });
            });
        </script>

    </body>
</html>
