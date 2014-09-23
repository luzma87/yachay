
<%@ page import="app.parametros.Contratista" %>
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
        
        <title>Crear Contratista</title>
    </head>

    <body>
            <div class="dialog" title="Crear Contratista">




                <div class="body">
                    <g:if test="${flash.message}">
                        <div class="message ui-state-highlight ui-corner-all">
                            <g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" />
                        </div>
                    </g:if>
                    <g:hasErrors bean="${contratistaInstance}">
                        <div class="errors ui-state-error ui-corner-all">
                            <g:renderErrors bean="${contratistaInstance}" as="list" />
                        </div>
                    </g:hasErrors>
                    <g:form action="save" class="frmContratista"
                            method="post"  >
                        <g:hiddenField name="id" value="${contratistaInstance?.id}" />
                        <g:hiddenField name="version" value="${contratistaInstance?.version}" />
                    <div>
                        <fieldset class="ui-corner-all" style="margin-left: 250px; width: 450px">
                            <legend class="ui-widget ui-widget-header ui-corner-all">
                                <g:if test="${source == 'edit'}">
                                    <g:message code="contratista.edit.legend" default="Editar Contratista"/>
                                </g:if>
                                <g:else>
                                    <g:message code="contratista.create.legend" default="Crear Contratista"/>
                                </g:else>
                </legend>
                    
                    <div class="prop mandatory ${hasErrors(bean: contratistaInstance, field: 'ruc', 'error')}">
                        <label for="ruc">
                            <g:message code="contratista.ruc.label" default="Ruc" />
                            <span class="indicator">*</span>
                        </label>
                        <div class="campo">
                            <g:textField  name="ruc" id="ruc" title="Ruc del Contratista" class="field required ui-widget-content ui-corner-all" minlength="1" maxlength="13" style="width: 110px"
                                          value="${contratistaInstance?.ruc}"  />
                        </div>
                    </div>

                            <div class="prop ${hasErrors(bean: contratistaInstance, field: 'nombreCont', 'error')}">
                                <label for="nombreCont">
                                    <g:message code="contratista.nombreCont.label" default="Contratista" />

                                </label>
                                <div class="campo">
                                    <g:textField  name="nombreCont" id="nombreCont" title="Nombre Contratista" class="field ui-widget-content ui-corner-all" minlength="1" maxlength="63" style="width: 300px"
                                                  value="${contratistaInstance?.nombreCont}" />
                                </div>
                            </div>

                            <div class="prop mandatory ${hasErrors(bean: contratistaInstance, field: 'nombre', 'error')}">
                                <label for="nombre">
                                    <g:message code="contratista.nombre.label" default="Nombre" />
                                    <span class="indicator">*</span>
                                </label>
                                <div class="campo">
                                    <g:textField  name="nombre" id="nombre" title="Nombre" class="field required ui-widget-content ui-corner-all" minlength="1" maxlength="63" style="width: 300px"
                                                  value="${contratistaInstance?.nombre}" />
                                </div>
                            </div>

                            <div class="prop ${hasErrors(bean: contratistaInstance, field: 'apellido', 'error')}">
                                <label for="apellido">
                                    <g:message code="contratista.apellido.label" default="Apellido" />

                                </label>
                                <div class="campo">
                                    <g:textField  name="apellido" id="apellido" title="Apellido" class="field ui-widget-content ui-corner-all" minlength="1" maxlength="63" style="width: 300px"
                                                  value="${contratistaInstance?.apellido}" />
                                </div>
                            </div>

                    
                    <div class="prop ${hasErrors(bean: contratistaInstance, field: 'direccion', 'error')}">
                        <label for="direccion">
                            <g:message code="contratista.direccion.label" default="Dirección" />
                            
                        </label>
                        <div class="campo">
                            <g:textField  name="direccion" id="direccion" title="Direccion" class="field ui-widget-content ui-corner-all" minlength="1" maxlength="255" style="width: 300px"
                                          value="${contratistaInstance?.direccion}" />
                        </div>
                    </div>

                            <div class="prop ${hasErrors(bean: contratistaInstance, field: 'mail', 'error')}">
                                <label for="mail">
                                    <g:message code="contratista.mail.label" default="Mail" />

                                </label>
                                <div class="campo">
                                    <g:textField  name="mail" id="mail" title="Mail" class="field ui-widget-content ui-corner-all" minlength="1" maxlength="40" style="width: 200px"
                                                  value="${contratistaInstance?.mail}" />
                                </div>
                            </div>

                            <div class="prop ${hasErrors(bean: contratistaInstance, field: 'telefono', 'error')}">
                                <label for="telefono">
                                    <g:message code="contratista.telefono.label" default="Telófono" />

                                </label>
                                <div class="campo">
                                    <g:textField  name="telefono" id="telefono" title="Telefono" class="field ui-widget-content ui-corner-all" minlength="1" maxlength="20" style="width: 150px"
                                                  value="${contratistaInstance?.telefono}" />
                                </div>
                            </div>


                            <div class="prop ${hasErrors(bean: contratistaInstance, field: 'fecha', 'error')}">
                        <label for="fecha">
                            <g:message code="contratista.fecha.label" default="Fecha"  />
                            
                        </label>
                        <div class="campo">
                            <input type="hidden" value="date.struct" name="fecha">
<input type="hidden" name="fecha_day" id="fecha_day"  value="${contratistaInstance?.fecha?.format('dd')}" >
<input type="hidden" name="fecha_month" id="fecha_month" value="${contratistaInstance?.fecha?.format('MM')}">
<input type="hidden" name="fecha_year" id="fecha_year" value="${contratistaInstance?.fecha?.format('yyyy')}">
<g:textField class="datepicker field ui-widget-content ui-corner-all" name="fecha" title="Fecha" id="fecha" value="${contratistaInstance?.fecha?.format('dd-MM-yyyy')}" style="width: 100px"/>
<script type='text/javascript'>
    $('#fecha').datepicker({
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


                    <div class="prop ${hasErrors(bean: contratistaInstance, field: 'estado', 'error')}">
                        <label for="estado">
                            <g:message code="contratista.estado.label" default="Estado" />
                            
                        </label>
                        <div class="campo">
                            %{--<g:textField  name="estado" id="estado" title="Estado" class="field ui-widget-content ui-corner-all" value="${contratistaInstance?.estado}" />--}%
                            <g:select  name="estado" id="estado" title="Estado" class="field ui-widget-content ui-corner-all" from="${['Activo', 'Inactivo']}"
                                       value="${contratistaInstance?.estado}" style="width: 100px"/>

                        </div>
                    </div>

                            <div class="prop ${hasErrors(bean: contratistaInstance, field: 'observaciones', 'error')}">
                                <label for="observaciones">
                                    <g:message code="contratista.observaciones.label" default="Observaciones" />

                                </label>
                                <div class="campo">
                                    <g:textField  name="observaciones" id="observaciones" title="Observaciones" class="field ui-widget-content ui-corner-all" minlength="1" maxlength="255" style="width: 300px"
                                                  value="${contratistaInstance?.observaciones}" />
                                </div>
                            </div>



                            <div class="buttons" style="margin-left: 180px">
                        <g:if test="${source == 'edit'}">
                            <a href="#" class="button save">
                                <g:message code="update" default="Actualizar" />
                            </a>
                            <g:link class="button delete" action="delete" id="${contratistaInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete" />
                            </g:link>
                            <g:link class="button show" action="show" id="${contratistaInstance?.id}">
                                <g:message code="default.button.show.label" default="Show" />
                            </g:link>
                        </g:if>
                        <g:else>
                            <a href="#" class="button save">
                                <g:message code="create" default="Crear" />
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
                var myForm = $(".frmContratista");

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