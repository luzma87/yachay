<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 11/08/14
  Time: 12:10 PM
--%>

<%@ page import="app.FormaPago; app.TipoContrato; app.Actividad; app.Componente; app.Proyecto" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>${title}</title>

        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins', file: 'jquery.meio.mask.js')}"></script>

        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.js')}"></script>
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.css')}"/>

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'jquery.validate.min.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'additional-methods.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'messages_es.js')}"></script>

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.min.js')}"></script>

        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.css')}"/>

        <style type="text/css">
        label {
            width   : auto;
            padding : 3px;
        }

        label.error {
            background-color : #b68590;
        }

        .wide {
            height       : 29px;
            padding-left : 5px;
        }

        .tiny {
            width : 45px;
        }

        .short {
            width : 85px;
        }

        .medium {
            width : 145px;
        }

        .nuevaActividad {
            width : 180px;
        }

        .nuevaActividad.datepicker {
            width : 163px;
        }

        #nombreProceso {
            width : 380px;
        }

        table {
            border-collapse : collapse;
        }

        .ta {
            width  : 965px;
            height : 85px;
        }
        </style>

    </head>

    <body>
        <div class="">
            <p>
                Seleccione un proyecto para cargar sus componentes, seleccione un componente para cargar sus actividades.
            </p>

            <p>
                Las actividades marcadas con un <strong>(*)</strong> no se encuentran en el POA y serán automáticamente ingresadas.
            </p>

            <p>
                Puede también crear una nueva activada que será ingresada al POA una vez que guarde la solicitud.
            </p>
        </div>

        <g:uploadForm action="save" method="post" name="frmSolicitud" id="${solicitud.id}">
            <table width="100%">
                <tr>
                    <td class="label">Unidad requirente</td>
                    <td colspan="3">
                        ${unidadRequirente.nombre}
                    </td>

                    <td class="label">Proyecto</td>
                    <td colspan="3">
                        <g:select from="${Proyecto.list()}" name="proyecto.id" id="selProyecto" class="requiredCmb ui-widget-content ui-corner-all"
                                  optionKey="id" optionValue="nombre" value="${solicitud.actividad?.proyectoId}"/>
                    </td>
                </tr>

                <tr>
                    <td class="label">Componente</td>
                    <td colspan="3" id="tdComponente">
                        %{--<g:select from="${Componente.list()}" name="componente.id" id="selComponente" class="ui-widget-content ui-corner-all"/>--}%
                    </td>

                    <td class="label">Actividad</td>
                    <td colspan="3" id="tdActividad">
                        %{--<g:select from="${Actividad.list()}" name="proyecto.id" id="selActividad" class="ui-widget-content ui-corner-all"/>--}%
                    </td>
                </tr>

                <tr>
                    <td class="label">Nombre del proceso</td>
                    <td colspan="3">
                        <g:textField class="required field wide ui-widget-content ui-corner-all"
                                     name="nombreProceso" title="Nombre del proceso" value="${solicitud.nombreProceso}"/>
                    </td>

                    <td class="label">Forma de pago</td>
                    <td>
                        <g:select name="formaPago.id" id="selFormaPago" from="${FormaPago.list()}"
                                  optionKey="id" optionValue="descripcion" class="requiredCmb" value="${solicitud.formaPagoId}"/>
                    </td>

                    <td class="label">Plazo de ejecución</td>
                    <td>
                        <g:textField class="required digits field wide tiny ui-widget-content ui-corner-all"
                                     name="plazoEjecucion" title="Plazo de ejecución" value="${solicitud.plazoEjecucion}"/> días
                    </td>
                </tr>

                <tr>
                    <td class="label">Fecha</td>
                    <td colspan="3">
                        <g:textField class="required datepicker field wide short ui-widget-content ui-corner-all"
                                     name="fecha" title="Fecha" autocomplete="off" value="${solicitud.fecha?.format('dd-MM-yyyy')}"/>
                    </td>

                    <td class="label">Monto solicitado</td>
                    <td>
                        <g:textField class="required number2 field wide medium ui-widget-content ui-corner-all"
                                     name="montoSolicitado" title="Monto solicitado" autocomplete="off" value="${solicitud.montoSolicitado}"/>
                    </td>

                    <td class="label">Modalidad de contratación</td>
                    <td>
                        <g:select name="tipoContrato.id" id="selContrato" from="${TipoContrato.list()}"
                                  optionKey="id" optionValue="descripcion" class="requiredCmb" value="${solicitud.tipoContratoId}"/>
                    </td>
                </tr>

                <tr>
                    <td class="label">Objeto del contrato</td>
                    <td colspan="7">
                        <g:textArea name="objetoContrato" rows="4" cols="115" class="ta required ui-widget-content ui-corner-all"
                                    value="${solicitud.objetoContrato}"/>
                    </td>
                </tr>

                <tr>
                    <td class="label">Observaciones</td>
                    <td colspan="7">
                        <g:textArea name="observaciones" rows="4" cols="115" class="ta ui-widget-content ui-corner-all"
                                    value="${solicitud.observaciones}"/>
                    </td>
                </tr>

                <tr>
                    <td class="label">PDF T.D.R.</td>
                    <td colspan="7">
                        <input type="file" name="pdf" class="${solicitud.pathPdfTdr ? '' : 'required'}"/>
                        <g:if test="${solicitud.pathPdfTdr}">
                            <br/>
                            Archivo actual:
                            ${solicitud.pathPdfTdr}
                        </g:if>
                    </td>
                </tr>

                <tr>
                    <td colspan="8" style="text-align: right;">
                        <g:if test="${solicitud.id}">
                            <g:link action="show" id="${solicitud.id}" class="button">
                                Cancelar
                            </g:link>
                        </g:if>
                        <a href="#" id="btnSave">Guardar</a>
                    </td>
                </tr>
            </table>
        </g:uploadForm>

        <div id="dlgActividad" title="Crear actividad">
            <div id="dlgActividadContent">
                <form id="frmNuevaActividad">
                    <table>
                        <tr>
                            <td class="label">Proyecto</td>
                            <td id="proyectoLabel"></td>
                        </tr>
                        <tr>
                            <td class="label">Componente</td>
                            <td id="componenteLabel"></td>
                        </tr>
                        <tr>
                            <td class="label">Categoría</td>
                            <td>
                                <g:select from="${app.yachai.Categoria.list()}" name="nuevaCategoria"
                                          optionKey="id" optionValue="descripcion" class="requiredCmb"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">Actividad</td>
                            <td>
                                <g:textField name="nuevaActividad" class="required nuevaActividad ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">Monto</td>
                            <td>
                                <g:textField name="nuevoMonto" class="required number2 nuevaActividad ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">Fecha inicio</td>
                            <td>
                                <g:textField name="fechaInicio" class="required datepicker nuevaActividad ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">Fecha fin</td>
                            <td>
                                <g:textField name="fechaFin" class="required datepicker nuevaActividad ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">Aporte</td>
                            <td>
                                <g:textField name="nuevoAporte" class="required number2 nuevaActividad ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <script type="text/javascript">

            var widthProyecto = 425;
            var widthComponente = 385;
            var widthActividad = 395;

            function loadComponentes() {
                $.ajax({
                    type    : "POST",
                    url     : "${createLink(action: 'getComponentesByProyecto')}",
                    data    : {
                        id    : $("#selProyecto").val(),
                        width : widthComponente,
                        val   : "${solicitud.actividad?.marcoLogicoId}"
                    },
                    success : function (msg) {
                        $("#tdComponente").html(msg);
                        loadActividades();
                    }
                });
            }
            function loadActividades() {
                $.ajax({
                    type    : "POST",
                    url     : "${createLink(action: 'getActividadesByComponente')}",
                    data    : {
                        id    : $("#selComponente").val(),
                        width : widthActividad,
                        val   : "${solicitud.actividadId}"
                    },
                    success : function (msg) {
                        $("#tdActividad").html(msg);
                        loadDatosActividad();
                    }
                });
            }

            function loadDatosActividad() {
                var actividadId = $("#selActividad").val();
                if ("${solicitud.actividadId}" == actividadId) {
                    $("#nombreProceso").val("${solicitud.nombreProceso}");
                    $("#montoSolicitado").val(number_format("${solicitud.montoSolicitado}", 2, '.', ''))
                            .attr("max2", number_format("${solicitud.montoSolicitado}", 2, '.', ''))
                            .setMask('decimal');
                } else {
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action: 'getDatosActividad')}",
                        data    : {
                            id : actividadId
                        },
                        success : function (msg) {
                            var parts = msg.split("||");
                            $("#nombreProceso").val(parts[0]);
                            $("#montoSolicitado").val(number_format(parts[1], 2, '.', ''))
                                    .attr("max2", number_format(parts[1], 2, '.', ''))
                                    .setMask('decimal');
                        }
                    });
                }
            }

            function crearActividad() {
                var proySt = $("#selProyecto").find("option:selected").text();
                var compSt = $("#selComponente").find("option:selected").text();

                $("#proyectoLabel").text(proySt);
                $("#componenteLabel").text(compSt);

                $("#dlgActividad").dialog('option', 'title', 'Crear actividad').dialog('open');
            }

            function resetActividadForm() {
                $("#nuevaActividad").val("");
                $("#nuevaCategoria").val("");
                $("#fechaInicio").val("");
                $("#fechaFin").val("");
                $("#nuevoMonto").val("");
                $("#nuevoAporte").val("");
            }

            $(function () {
                var myForm = $("#frmSolicitud");
                $(".button").button();
                $("#btnSave").button({
                    icons : {
                        primary : "ui-icon-disk"
                    }
                }).click(function () {
                    myForm.submit();
                    return false;
                });

                $("#dlgActividad").dialog({
                    modal         : true,
                    resizable     : false,
                    autoOpen      : false,
                    closeOnEscape : false,
                    width         : 450,
                    buttons       : {
                        "Guardar"  : function () {
                            if ($("#frmNuevaActividad").valid()) {
                                var proyecto = $("#selProyecto").val();
                                var componente = $("#selComponente").val();
                                var actividad = $("#nuevaActividad").val();
                                var categoria = $("#nuevaCategoria").val();
                                var fechaIni = $("#fechaInicio").val();
                                var fechaFin = $("#fechaFin").val();
                                var monto = $("#nuevoMonto").val();
                                var aporte = $("#nuevoAporte").val();

                                $(this).dialog("close");
                                $.ajax({
                                    type    : "POST",
                                    url     : "${createLink(action:'newActividad_ajax')}",
                                    data    : {
                                        proy      : proyecto,
                                        comp      : componente,
                                        cat       : categoria,
                                        fechaIni  : fechaIni,
                                        fechaFin  : fechaFin,
                                        actividad : actividad,
                                        monto     : monto,
                                        aporte    : aporte,
                                        width     : widthActividad
                                    },
                                    success : function (msg) {
                                        $("#tdActividad").html(msg);
                                        resetActividadForm();
                                    }
                                });
                            }
                        },
                        "Cancelar" : function () {
                            $(this).dialog("close");
                        }
                    }
                });

                $("#selProyecto").change(function () {
                    loadComponentes();
                }).selectmenu({width : widthProyecto});
                loadComponentes();

                $("#nuevaCategoria").selectmenu({width : 150});
                $("#selContrato").selectmenu({width : 150});
                $("#selFormaPago").selectmenu({width : 150});

                $('.datepicker').datepicker({
                    changeMonth : true,
                    changeYear  : true,
                    dateFormat  : 'dd-mm-yy',
                    maxDate     : "+0"
                });

                $("#montoSolicitado").setMask('decimal');
                $("#plazoEjecucion").setMask('integer');

                $("#nuevoMonto").setMask('decimal');
                $("#nuevoAporte").setMask('decimal');

                $("#frmNuevaActividad").validate();

                // Tooltip de informacion para cada field (utiliza el atributo title del textfield)
                var elems = $('.field')
                        .each(function (i) {
                            $.attr(this, 'oldtitle', $.attr(this, 'title'));
                        })
                        .removeAttr('title');
                $('<div />').qtip(
                        {
                            content  : ' ', // Can use any content here :)
                            position : {
                                target : 'event' // Use the triggering element as the positioning target
                            },
                            show     : {
                                target : elems,
                                event  : 'click mouseenter focus'
                            },
                            hide     : {
                                target : elems
                            },
                            events   : {
                                show : function (event, api) {
                                    // Update the content of the tooltip on each show
                                    var target = $(event.originalEvent.target);
                                    api.set('content.text', target.attr('title'));
                                }
                            },
                            style    : {
                                classes : 'ui-tooltip-rounded ui-tooltip-cream'
                            }
                        });
                // fin del codigo para los tooltips

                // Validacion del formulario
                myForm.validate({
                    onkeyup        : false,
                    errorElement   : "em",
                    errorClass     : 'error',
                    validClass     : 'valid',
                    rules          : {
                        pdf : {
                            accept : "pdf"
                        }
                    },
                    messages       : {
                        pdf : {
                            accept : "Por favor seleccione un archivo PDF"
                        }
                    },
                    errorPlacement : function (error, element) {
                        // Set positioning based on the elements position in the form
                        var elem = $(element),
                                corners = ['right center', 'left center'],
                                flipIt = elem.parents('span.right').length > 0;

                        // Check we have a valid error message
                        if (!error.is(':empty')) {
                            // Apply the tooltip only if it isn't valid
                            elem.filter(':not(.valid)').qtip({
                                overwrite : false,
                                content   : error,
                                position  : {
                                    my       : corners[ flipIt ? 0 : 1 ],
                                    at       : corners[ flipIt ? 1 : 0 ],
                                    viewport : $(window)
                                },
                                show      : {
                                    event : false,
                                    ready : true
                                },
                                hide      : false,
                                style     : {
                                    classes : 'ui-tooltip-rounded ui-tooltip-red' // Make it red... the classic error colour!
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
                    success        : $.noop // Odd workaround for errorPlacement not firing!
                });
                //fin de la validacion del formulario

            });
        </script>

    </body>
</html>