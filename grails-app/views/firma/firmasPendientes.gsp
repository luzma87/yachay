<%@ page import="yachay.parametros.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Firmas pendientes</title>
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}"
              type="text/css"/>
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}"
              type="text/css"/>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'svt.css')}"
              type="text/css"/>
        <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript"
                language="JavaScript"></script>
        <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}"
                type="text/javascript" language="JavaScript"></script>

        <style type="text/css">

        th {
            background-color : #363636;
        }

        </style>

    </head>

    <body>
        <g:if test="${flash.message}">
            <div class="message ui-state-highlight ui-corner-all">
                ${flash.message}
            </div>
        </g:if>
        <div id="tabs" style="width: 1050px;margin-top: 0px;">
            <ul>
                <li><a href="#solicitudes">Firmas pendientes</a></li>
                <li><a href="#historial">Historial</a></li>
            </ul>

            <div id="solicitudes" style="width: 1000px;">
                <g:if test="${firmas.size() > 0}">
                    <table style="width: 100%;margin-top: 10px;font-size: 10px;">
                        <thead>
                            <tr>
                                <th>Concepto</th>
                                <th>Documento</th>
                                <th>Ver</th>
                                <th>Firmar</th>
                                %{--<th>Negar</th>--}%
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${firmas}" var="f">
                                <tr>
                                    <td>${f.concepto}</td>
                                    <td>${f.documento}</td>
                                    <td style="text-align: center">
                                    %{--${f.controladorVer}/${f.accionVer}/${f.idAccionVer}--}%
                                        <g:if test="${f.accionVer}">
                                            <g:if test="${f.esPdf != 'N'}">
                                                <a href="${g.createLink(controller: 'pdf', action: 'pdfLink')}?url=${g.createLink(action: f.accionVer, controller: f.controladorVer, id: f.idAccionVer)}" target="_blank" class="btn" style="margin: 5px">Ver</a>
                                            </g:if>
                                            <g:else>
                                                <a href="${g.createLink(action: f.accionVer, controller: f.controladorVer, id: f.idAccionVer)}" target="_blank" class="btn" style="margin: 5px">Ver</a>
                                            </g:else>

                                        </g:if>
                                    </td>
                                    <td>
                                        <a href="#" iden="${f.id}" class="aprobar btn" style="margin: 5px">Firmar</a>
                                    </td>
                                    %{--<td>--}%
                                    %{--<a href="#" class="negar btn"  iden="${f.id}">Negar</a>--}%
                                    %{--</td>--}%
                                </tr>
                            </g:each>

                        </tbody>
                    </table>
                </g:if>
            </div>

            <div id="historial" style="width: 1000px;">
                <div class="fila">
                    Año: <g:select from="${yachay.parametros.poaPac.Anio.list([sort: 'anio'])}" optionKey="id" optionValue="anio" id="anio" style="width:100px;margin-left: 15px;" class="ui-corner-all" value="${actual?.id}"></g:select>
                </div>

                <div id="detalle" style="width: 100%;height: 500px;overflow: auto"></div>
            </div>

        </div>

        <div id="firmarDlg">
            <div class="fila">
                <div class="labelSvt" style="width:150px;height: 45px">
                    Clave de
                    autorización
                </div>

                <div class="fieldSvt-medium" style="height: 45px">
                    <input type="password" id="atrz" style="">
                </div>
            </div>
        </div>

        <div id="negar">
            <input type="hidden" id="avalId">
            Esta seguro que desea negar esta solicitud de certificación?
        </div>
        <script>
            function cargarHistorial(anio) {

                $.ajax({
                    type    : "POST", url : "${createLink(action:'historial', controller: 'firma')}",
                    data    : {
                        anio : anio
                    },
                    success : function (msg) {
                        $("#detalle").html(msg)

                    }
                });

            }
            cargarHistorial($("#anio").val())
            $("#anio").change(function () {
                cargarHistorial($("#anio").val())
            })
            $(".btn").button()

            $(".descRespaldo").click(function () {
                location.href = "${createLink(controller:'avales',action:'descargaSolicitud')}/" + $(this).attr("iden")

            });

            $("#tabs").tabs()
            var id
            $(".aprobar").button({icons : { primary : "ui-icon-check"}, text : false}).click(function () {
                id = $(this).attr("iden")
                if (confirm("Está seguro? esta acción no puede revertirse")) {
                    +$("#atrz").val("")
                    $("#firmarDlg").dialog("open")
                }
            });
            $(".aprobarAnulacion").button({icons : { primary : "ui-icon-check"}, text : false});
            $(".negar").button({icons : { primary : "ui-icon-close"}, text : false}).click(function () {
                var id = $(this).attr("iden")
                if (confirm("Está seguro?")) {
                    $.ajax({
                        type    : "POST", url : "${createLink(action:'negar', controller: 'firma' )}",
                        data    : "id=" + id,
                        success : function (msg) {
                            if (msg != "no") {
                                location.reload(true)
                            } else {
                                $.box({
                                    title  : "Error",
                                    text   : "Ha ocurrido un error interno",
                                    dialog : {
                                        resizable : false,
                                        width     : 400,
                                        buttons   : {
                                            "Cerrar" : function () {

                                            }
                                        }
                                    }
                                });
                            }

                        }
                    });
                }
            });
            $("#firmarDlg").dialog({
                autoOpen  : false,
                resizable : false,
                title     : 'Autorización Electrónica',
                modal     : true,
                draggable : true,
                width     : 500,
                height    : 200,
                position  : 'center',
                open      : function (event, ui) {
                    $(".ui-dialog-titlebar-close").hide();
                },
                buttons   : {
                    "Cancelar"  : function () {
                        $("#firmarDlg").dialog("close")
                    }, "Firmar" : function () {
                        $.ajax({
                            type    : "POST", url : "${createLink(action:'firmar', controller: 'firma' )}",
                            data    : "id=" + id + "&pass=" + $("#atrz").val(),
                            success : function (msg) {
                                if (msg != "no") {
                                    $("#firmarDlg").dialog("close")
                                    $.box({
                                        title  : "Firma",
                                        text   : "Documento firmado",
                                        dialog : {
                                            resizable : false,
                                            width     : 400,
                                            buttons   : {
                                                "Cerrar" : function () {
                                                    location.reload(true)
                                                }
                                            }
                                        }
                                    });
                                    location.href = msg
                                } else {

                                    $.box({
                                        title  : "Error",
                                        text   : "Contraseña incorrecta",
                                        dialog : {
                                            resizable : false,
                                            width     : 400,
                                            buttons   : {
                                                "Cerrar" : function () {

                                                }
                                            }
                                        }
                                    });

                                }

                            }
                        });
                    }
                }
            });
            $("#negar").dialog({
                autoOpen  : false,
                resizable : false,
                title     : 'Negar solicitud',
                modal     : true,
                draggable : true,
                width     : 400,
                height    : 150,
                position  : 'center',
                open      : function (event, ui) {
                    $(".ui-dialog-titlebar-close").hide();
                },
                buttons   : {
                    "Cancelar" : function () {
                        $("#negar").dialog("close")
                    }, "Negar" : function () {
                        $.ajax({
                            type    : "POST", url : "${createLink(action:'negarAval', controller: 'revisionAval')}",
                            data    : "id=" + $("#avalId").val(),
                            success : function (msg) {
                                if (msg != "no")
                                    location.reload(true)
                                else
                                    location.href = "${createLink(controller:'certificacion',action:'listaSolicitudes')}/?msn=Usted no tiene los permisos para negar esta solicitud"

                            }
                        });
                    }
                }
            });
        </script>
    </body>
</html>