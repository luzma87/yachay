
<%@ page import="yachay.proyectos.Liquidacion" %>
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
                    <g:hasErrors bean="${liquidacionInstance}">
                        <div class="errors ui-state-error ui-corner-all">
                            <g:renderErrors bean="${liquidacionInstance}" as="list" />
                        </div>
                    </g:hasErrors>
                    <g:form action="save" class="frmLiquidacion"
                            method="post"  >
                        <g:hiddenField name="id" value="${liquidacionInstance?.id}" />
                        <g:hiddenField name="version" value="${liquidacionInstance?.version}" />
                    <div>
                        <fieldset class="ui-corner-all">
                            <legend class="ui-widget ui-widget-header ui-corner-all">
                                <g:if test="${source == 'edit'}">
                                    <g:message code="liquidacion.edit.legend" default="Edit Liquidacion details"/>
                                </g:if>
                                <g:else>
                                    <g:message code="liquidacion.create.legend" default="Enter Liquidacion details"/>
                                </g:else>
                </legend>
                    
                    <div class="prop mandatory ${hasErrors(bean: liquidacionInstance, field: 'obra', 'error')}">
                        <label for="obra">
                            <g:message code="liquidacion.obra.label" default="Obra" />
                            <span class="indicator">*</span>
                        </label>
                        <div class="campo">
                            <g:select class="field required requiredCmb ui-widget-content ui-corner-all" name="obra.id" title="Obra" from="${yachay.proyectos.Obra.list()}" optionKey="id" value="${liquidacionInstance?.obra?.id}"  />
                        </div>
                    </div>
                    
                    <div class="prop mandatory ${hasErrors(bean: liquidacionInstance, field: 'valor', 'error')}">
                        <label for="valor">
                            <g:message code="liquidacion.valor.label" default="Valor" />
                            <span class="indicator">*</span>
                        </label>
                        <div class="campo">
                            <g:textField class="field number required ui-widget-content ui-corner-all" name="valor" title="Valor" id="valor" value="${fieldValue(bean: liquidacionInstance, field: 'valor')}" />
                        </div>
                    </div>
                    
                    <div class="prop ${hasErrors(bean: liquidacionInstance, field: 'fechaRegistro', 'error')}">
                        <label for="fechaRegistro">
                            <g:message code="liquidacion.fechaRegistro.label" default="Fecha Registro" />
                            
                        </label>
                        <div class="campo">
                            <input type="hidden" value="date.struct" name="fechaRegistro">
<input type="hidden" name="fechaRegistro_day" id="fechaRegistro_day"  value="${liquidacionInstance?.fechaRegistro?.format('dd')}" >
<input type="hidden" name="fechaRegistro_month" id="fechaRegistro_month" value="${liquidacionInstance?.fechaRegistro?.format('MM')}">
<input type="hidden" name="fechaRegistro_year" id="fechaRegistro_year" value="${liquidacionInstance?.fechaRegistro?.format('yyyy')}">
<g:textField class="datepicker field ui-widget-content ui-corner-all" name="fechaRegistro" title="FechaRegistro" id="fechaRegistro" value="${liquidacionInstance?.fechaRegistro?.format('dd-MM-yyyy')}" />
<script type='text/javascript'>
    $('#fechaRegistro').datepicker({
        changeMonth: true,
        changeYear:true,
        dateFormat: 'dd-mm-yy',
        onClose: function(dateText, inst) {
            var date = $(this).datepicker('getDate');
            var day, month, year;
            if(date != null) {
                day = date.getDate();
                month = parseInt(date.getMonth()) + 1;
                year = date.getFullYear();
            } else {
                day = '';
                month = '';
                year = '';
            }
            var id = $(this).attr('id');
            $('#' + id + '_day').val(day);
            $('#' + id + '_month').val(month);
            $('#' + id + '_year').val(year);
        }
    });
</script>
                        </div>
                    </div>
                    
                    <div class="prop mandatory ${hasErrors(bean: liquidacionInstance, field: 'fechaAdjudicacion', 'error')}">
                        <label for="fechaAdjudicacion">
                            <g:message code="liquidacion.fechaAdjudicacion.label" default="Fecha Adjudicacion" />
                            <span class="indicator">*</span>
                        </label>
                        <div class="campo">
                            <input type="hidden" value="date.struct" name="fechaAdjudicacion">
<input type="hidden" name="fechaAdjudicacion_day" id="fechaAdjudicacion_day"  value="${liquidacionInstance?.fechaAdjudicacion?.format('dd')}" >
<input type="hidden" name="fechaAdjudicacion_month" id="fechaAdjudicacion_month" value="${liquidacionInstance?.fechaAdjudicacion?.format('MM')}">
<input type="hidden" name="fechaAdjudicacion_year" id="fechaAdjudicacion_year" value="${liquidacionInstance?.fechaAdjudicacion?.format('yyyy')}">
<g:textField class="datepicker field required ui-widget-content ui-corner-all" name="fechaAdjudicacion" title="FechaAdjudicacion" id="fechaAdjudicacion" value="${liquidacionInstance?.fechaAdjudicacion?.format('dd-MM-yyyy')}" />
<script type='text/javascript'>
    $('#fechaAdjudicacion').datepicker({
        changeMonth: true,
        changeYear:true,
        dateFormat: 'dd-mm-yy',
        onClose: function(dateText, inst) {
            var date = $(this).datepicker('getDate');
            var day, month, year;
            if(date != null) {
                day = date.getDate();
                month = parseInt(date.getMonth()) + 1;
                year = date.getFullYear();
            } else {
                day = '';
                month = '';
                year = '';
            }
            var id = $(this).attr('id');
            $('#' + id + '_day').val(day);
            $('#' + id + '_month').val(month);
            $('#' + id + '_year').val(year);
        }
    });
</script>
                        </div>
                    </div>
                    
                    <div class="prop ${hasErrors(bean: liquidacionInstance, field: 'fechaInicio', 'error')}">
                        <label for="fechaInicio">
                            <g:message code="liquidacion.fechaInicio.label" default="Fecha Inicio" />
                            
                        </label>
                        <div class="campo">
                            <input type="hidden" value="date.struct" name="fechaInicio">
<input type="hidden" name="fechaInicio_day" id="fechaInicio_day"  value="${liquidacionInstance?.fechaInicio?.format('dd')}" >
<input type="hidden" name="fechaInicio_month" id="fechaInicio_month" value="${liquidacionInstance?.fechaInicio?.format('MM')}">
<input type="hidden" name="fechaInicio_year" id="fechaInicio_year" value="${liquidacionInstance?.fechaInicio?.format('yyyy')}">
<g:textField class="datepicker field ui-widget-content ui-corner-all" name="fechaInicio" title="FechaInicio" id="fechaInicio" value="${liquidacionInstance?.fechaInicio?.format('dd-MM-yyyy')}" />
<script type='text/javascript'>
    $('#fechaInicio').datepicker({
        changeMonth: true,
        changeYear:true,
        dateFormat: 'dd-mm-yy',
        onClose: function(dateText, inst) {
            var date = $(this).datepicker('getDate');
            var day, month, year;
            if(date != null) {
                day = date.getDate();
                month = parseInt(date.getMonth()) + 1;
                year = date.getFullYear();
            } else {
                day = '';
                month = '';
                year = '';
            }
            var id = $(this).attr('id');
            $('#' + id + '_day').val(day);
            $('#' + id + '_month').val(month);
            $('#' + id + '_year').val(year);
        }
    });
</script>
                        </div>
                    </div>
                    
                    <div class="prop ${hasErrors(bean: liquidacionInstance, field: 'fechaFin', 'error')}">
                        <label for="fechaFin">
                            <g:message code="liquidacion.fechaFin.label" default="Fecha Fin" />
                            
                        </label>
                        <div class="campo">
                            <input type="hidden" value="date.struct" name="fechaFin">
<input type="hidden" name="fechaFin_day" id="fechaFin_day"  value="${liquidacionInstance?.fechaFin?.format('dd')}" >
<input type="hidden" name="fechaFin_month" id="fechaFin_month" value="${liquidacionInstance?.fechaFin?.format('MM')}">
<input type="hidden" name="fechaFin_year" id="fechaFin_year" value="${liquidacionInstance?.fechaFin?.format('yyyy')}">
<g:textField class="datepicker field ui-widget-content ui-corner-all" name="fechaFin" title="FechaFin" id="fechaFin" value="${liquidacionInstance?.fechaFin?.format('dd-MM-yyyy')}" />
<script type='text/javascript'>
    $('#fechaFin').datepicker({
        changeMonth: true,
        changeYear:true,
        dateFormat: 'dd-mm-yy',
        onClose: function(dateText, inst) {
            var date = $(this).datepicker('getDate');
            var day, month, year;
            if(date != null) {
                day = date.getDate();
                month = parseInt(date.getMonth()) + 1;
                year = date.getFullYear();
            } else {
                day = '';
                month = '';
                year = '';
            }
            var id = $(this).attr('id');
            $('#' + id + '_day').val(day);
            $('#' + id + '_month').val(month);
            $('#' + id + '_year').val(year);
        }
    });
</script>
                        </div>
                    </div>
                    

                    <div class="buttons">
                        <g:if test="${source == 'edit'}">
                            <a href="#" class="button save">
                                <g:message code="update" default="Update" />
                            </a>
                            <g:link class="button delete" action="delete" id="${liquidacionInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete" />
                            </g:link>
                            <g:link class="button show" action="show" id="${liquidacionInstance?.id}">
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
                var myForm = $(".frmLiquidacion");

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