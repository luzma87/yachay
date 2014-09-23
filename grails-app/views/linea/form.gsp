<%@ page import="yachay.parametros.Linea" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>

    <script type="text/javascript"
            src="${resource(dir: 'js/jquery/plugins/validation', file: 'jquery.validate.min.js')}"></script>
    <script type="text/javascript"
            src="${resource(dir: 'js/jquery/plugins/validation', file: 'additional-methods.js')}"></script>
    <script type="text/javascript"
            src="${resource(dir: 'js/jquery/plugins/validation', file: 'messages_es.js')}"></script>

    <script type="text/javascript"
            src="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.min.js')}"></script>

    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.css')}"/>

    <title>${title}</title>
</head>

<body>
    <div class="dialog">

        <div id="" class="toolbar ui-widget-header ui-corner-all">
            <g:link class="button list" action="list">
                Lista de Lineamientos de inversión
            </g:link>
        </div> <!-- toolbar -->

        <g:if test="${flash.message}">
            <div class="message ui-state-highlight ui-corner-all">
                <g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}"/>
            </div>
        </g:if>
        <g:hasErrors bean="${lineaInstance}">
            <div class="errors ui-state-error ui-corner-all">
                <g:renderErrors bean="${lineaInstance}" as="list"/>
            </div>
        </g:hasErrors>
        <g:form action="save" class="frmLinea"
                method="post">
            <g:hiddenField name="id" value="${lineaInstance?.id}"/>
            <g:hiddenField name="version" value="${lineaInstance?.version}"/>

            <table style="width: 900px;" class="show ui-widget-content ui-corner-all">

                <thead>
                    <tr>
                        <td colspan="6" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
                            <g:if test="${source == 'edit'}">
                                Editar detalles del Lineamiento de inversión
                            </g:if>
                            <g:else>
                                Ingresar detalles del Lineamiento de inversión
                            </g:else>
                        </td>
                    </tr>
                </thead>
                <tbody>

                    <tr>
                        <td colspan="3" class="blanco">&nbsp;</td>
                    </tr>


                    <tr class="prop ${hasErrors(bean: lineaInstance, field: 'descripcion', 'error')}">

                        <td class="label  mandatory" valign="middle">
                            <g:message code="linea.descripcion.label" default="Descripción"/>:
                            %{--<span class="indicator">*</span>--}%
                        </td>
                        <td class="indicator mandatory">
                            <span class="indicator">*</span>
                        </td>
                        <td class=" mandatory" valign="middle">
                            <g:textArea name="descripcion" id="descripcion" title="${Linea.constraints.descripcion.attributes.mensaje}"
                                         class="field required ui-widget-content ui-corner-all" minLenght="1"
                                         maxLenght="127" style="width: 700px;" value="${lineaInstance?.descripcion}"/>
                            %{--<span class="indicator">*</span>--}%
                        </td>

                    </tr>


                    <tr>
                        <td colspan="3" class="blanco">&nbsp;</td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="6" class="buttons" style="text-align: right;">
                            <g:if test="${source == 'edit'}">
                                <a href="#" class="button save">
                                    <g:message code="default.button.update.label" default="Update"/>
                                </a>
                                <g:link class="button delete" action="delete" id="${lineaInstance?.id}">
                                    <g:message code="default.button.delete.label" default="Delete"/>
                                </g:link>
                                <g:link class="button show" action="show" id="${lineaInstance?.id}">
                                    <g:message code="default.button.show.label" default="Show"/>
                                </g:link>
                            </g:if>
                            <g:else>
                                <a href="#" class="button save">
                                    <g:message code="default.button.create.label" default="Create"/>
                                </a>
                            </g:else>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </g:form>
    </div>

    <script type="text/javascript">
        $(function() {
            var myForm = $(".frmLinea");

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
                    else {
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
                if (confirm("${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}")) {
                    return true;
                }
                return false;
            });
        });
    </script>

</body>
</html>