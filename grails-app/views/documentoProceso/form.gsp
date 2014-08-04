
<%@ page import="app.DocumentoProceso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'jquery.validate.min.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'additional-methods.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'messages_es.js')}"></script>

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.min.js')}"></script>

        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.css')}" />
        
        <title>${title}</title>
    </head>

    <body>
            <div class="dialog" title="${title}">




                <div class="body">
                    <g:if test="${flash.message}">
                        <div class="message ui-state-highlight ui-corner-all">
                            <g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" />
                        </div>
                    </g:if>
                    <g:hasErrors bean="${documentoProcesoInstance}">
                        <div class="errors ui-state-error ui-corner-all">
                            <g:renderErrors bean="${documentoProcesoInstance}" as="list" />
                        </div>
                    </g:hasErrors>
                    <g:form action="save" class="frmDocumentoProceso"
                            method="post"  >
                        <g:hiddenField name="id" value="${documentoProcesoInstance?.id}" />
                        <g:hiddenField name="version" value="${documentoProcesoInstance?.version}" />
                    <div>
                        <fieldset class="ui-corner-all">
                            <legend class="ui-widget ui-widget-header ui-corner-all">
                                <g:if test="${source == 'edit'}">
                                    <g:message code="documentoProceso.edit.legend" default="Edit DocumentoProceso details"/>
                                </g:if>
                                <g:else>
                                    <g:message code="documentoProceso.create.legend" default="Enter DocumentoProceso details"/>
                                </g:else>
                </legend>
                    
                    <div class="prop mandatory ${hasErrors(bean: documentoProcesoInstance, field: 'liquidacion', 'error')}">
                        <label for="liquidacion">
                            <g:message code="documentoProceso.liquidacion.label" default="Liquidacion" />
                            <span class="indicator">*</span>
                        </label>
                        <div class="campo">
                            <g:select class="field required requiredCmb ui-widget-content ui-corner-all" name="liquidacion.id" title="Liquidacion" from="${app.Liquidacion.list()}" optionKey="id" value="${documentoProcesoInstance?.liquidacion?.id}"  />
                        </div>
                    </div>
                    
                    <div class="prop ${hasErrors(bean: documentoProcesoInstance, field: 'descripcion', 'error')}">
                        <label for="descripcion">
                            <g:message code="documentoProceso.descripcion.label" default="Descripcion" />
                            
                        </label>
                        <div class="campo">
                            <g:textField  name="descripcion" id="descripcion" title="Descripción del documento" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="63" value="${documentoProcesoInstance?.descripcion}" />
                        </div>
                    </div>
                    
                    <div class="prop mandatory ${hasErrors(bean: documentoProcesoInstance, field: 'tipo', 'error')}">
                        <label for="tipo">
                            <g:message code="documentoProceso.tipo.label" default="Tipo" />
                            <span class="indicator">*</span>
                        </label>
                        <div class="campo">
                            <g:select class="field required requiredCmb ui-widget-content ui-corner-all" name="tipo.id" title="Tipo del documento" from="${app.TipoDocumento.list()}" optionKey="id" value="${documentoProcesoInstance?.tipo?.id}"  />
                        </div>
                    </div>
                    
                    <div class="prop ${hasErrors(bean: documentoProcesoInstance, field: 'clave', 'error')}">
                        <label for="clave">
                            <g:message code="documentoProceso.clave.label" default="Clave" />
                            
                        </label>
                        <div class="campo">
                            <g:textField  name="clave" id="clave" title="Palabras clave" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="63" value="${documentoProcesoInstance?.clave}" />
                        </div>
                    </div>
                    
                    <div class="prop ${hasErrors(bean: documentoProcesoInstance, field: 'resumen', 'error')}">
                        <label for="resumen">
                            <g:message code="documentoProceso.resumen.label" default="Resumen" />
                            
                        </label>
                        <div class="campo">
                            <g:textArea class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="1024" name="resumen" id="resumen" title="Resumen" cols="40" rows="5" value="${documentoProcesoInstance?.resumen}" />
                        </div>
                    </div>
                    
                    <div class="prop ${hasErrors(bean: documentoProcesoInstance, field: 'documento', 'error')}">
                        <label for="documento">
                            <g:message code="documentoProceso.documento.label" default="Documento" />
                            
                        </label>
                        <div class="campo">
                            <g:textField  name="documento" id="documento" title="Ruta del documento" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="255" value="${documentoProcesoInstance?.documento}" />
                        </div>
                    </div>
                    

                    <div class="buttons">
                        <g:if test="${source == 'edit'}">
                            <a href="#" class="button save">
                                <g:message code="update" default="Update" />
                            </a>
                            <g:link class="button delete" action="delete" id="${documentoProcesoInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete" />
                            </g:link>
                            <g:link class="button show" action="show" id="${documentoProcesoInstance?.id}">
                                <g:message code="default.button.show.label" default="Show" />
                            </g:link>
                        </g:if>
                        <g:else>
                            <a href="#" class="button save">
                                <g:message code="create" default="Create" />
                            </a>
                        </g:else>
                    </div>

                </fieldset>
                </div>
                    </g:form>
                </div>
            </div>

        <script type="text/javascript">
            $(function() {
                var myForm = $(".frmDocumentoProceso");

                // Tooltip de informacion para cada field (utiliza el atributo title del textfield)
                var elems = $('.field')
                        .each(function(i) {
                            $.attr(this, 'oldtitle', $.attr(this, 'title'));
                        })
                        .removeAttr('title');
                $('<div />').qtip(
                        {
                            content: ' ', // Can use any content here :)
                            position: {
                                target: 'event' // Use the triggering element as the positioning target
                            },
                            show: {
                                target: elems,
                                event: 'click mouseenter focus'
                            },
                            hide: {
                                target: elems
                            },
                            events: {
                                show: function(event, api) {
                                    // Update the content of the tooltip on each show
                                    var target = $(event.originalEvent.target);
                                    api.set('content.text', target.attr('title'));
                                }
                            },
                            style: {
                                classes: 'ui-tooltip-rounded ui-tooltip-cream'
                            }
                        });
                // fin del codigo para los tooltips

                // Validacion del formulario
                myForm.validate({
                            errorClass: "errormessage",
                            onkeyup: false,
                            errorElement: "em",
                            errorClass: 'error',
                            validClass: 'valid',
                            errorPlacement: function(error, element) {
                                // Set positioning based on the elements position in the form
                                var elem = $(element),
                                        corners = ['right center', 'left center'],
                                        flipIt = elem.parents('span.right').length > 0;

                                // Check we have a valid error message
                                if (!error.is(':empty')) {
                                    // Apply the tooltip only if it isn't valid
                                    elem.filter(':not(.valid)').qtip({
                                                overwrite: false,
                                                content: error,
                                                position: {
                                                    my: corners[ flipIt ? 0 : 1 ],
                                                    at: corners[ flipIt ? 1 : 0 ],
                                                    viewport: $(window)
                                                },
                                show: {
                                    event: false,
                                ready:
                                    true
                                },
                                hide: false,
                                style: {
                                    classes: 'ui-tooltip-rounded ui-tooltip-red' // Make it red... the classic error colour!
                                }
                            })

                    // If we have a tooltip on this element already, just update its content
                        .qtip('option', 'content.text', error);
            }

            // If the error is empty, remove the qTip
            else
            {
                elem.qtip('destroy');
            }
            },
            success: $.noop // Odd workaround for errorPlacement not firing!
            })
            ;
            //fin de la validacion del formulario


            $(".button").button();
            $(".home").button("option", "icons", {primary:'ui-icon-home'});
            $(".list").button("option", "icons", {primary:'ui-icon-clipboard'});
            $(".show").button("option", "icons", {primary:'ui-icon-bullet'});
            $(".save").button("option", "icons", {primary:'ui-icon-disk'}).click(function() {
                myForm.submit();
                return false;
            });
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