<%@ page import="app.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Aprobar aval</title>
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

        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/ckeditor', file: 'ckeditor.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/ckeditor/adapters', file: 'jquery.js')}"></script>

    </head>

    <body>
        <g:if test="${flash.message}">
            <div class="message ui-state-highlight ui-corner-all">
                ${flash.message}
            </div>
        </g:if>
        <div class="fila">
            <g:link controller="revisionAval" action="pendientes" class="btn">Regresar</g:link>
        </div>
        <fieldset style="width: 95%;padding-bottom: 10px;font-size: 10px;margin-top: 0px" class="ui-corner-all">
            <legend>Solicitud a aprobar</legend>
            <table style="width: 95%;margin-top: 0px">
                <thead>
                    <tr>
                        <th>Proceso</th>
                        <th>Concepto</th>
                        <th>Monto</th>
                        <th>Estado</th>
                        <th>Doc. Respaldo</th>
                        <th>Solicitud</th>
                    </tr>
                </thead>
                <tbody>

                    <tr>
                        <td>${solicitud.proceso.nombre}</td>
                        <td>${solicitud.concepto}</td>
                        <td style="text-align: right">${solicitud.monto}</td>
                        <td style="">${solicitud.estado?.descripcion}</td>
                        <td style="text-align: center">
                            <a href="#" class="btn descRespaldo" iden="${solicitud.id}">Ver</a>
                        </td>
                        <td style="text-align: center">
                            <a href="#" class="btn">Ver</a>
                        </td>
                    </tr>

                </tbody>
            </table>
        </fieldset>
        <fieldset style="width: 95%;padding-bottom: 10px" class="ui-corner-all">
            <legend>Datos para la aprobación</legend>
            <g:form action="aprobarAval" class="frmAprobar" enctype="multipart/form-data">
                <div class="fila" style="margin-top: 0px">
                    <div class="labelSvt" style="width: 180px">Número de aval:</div>

                    <div class="fieldSvt-small">
                        <input type="text" style="width: 100%;" id="numero" value="" class="ui-corner-all ui-widget-content">
                    </div>
                </div>

                <div class="fila" style="height: 300px">
                    <div class="labelSvt" style="width: 180px;">Observaciones:</div>

                    <div style="width: 700px;height: 280px;display: inline-block">
                        <textarea id="richText" style="width: 100%;height: 100%;resize: none"></textarea>
                    </div>
                </div>

                <h3>Ingrese el número del aval, las observaciones y descargue el formulario</h3> <a href="#" class="btn" id="descargaForm" style="display: inline-block">Aquí</a>

                <div class="fila">
                    <div class="labelSvt" style="width: 180px">Documento firmado:</div>

                    <div class="fieldSvt-medium">
                        <input type="file" id="archivo" name="archivo" style="display: inline-block">
                    </div>
                </div>

            %{--Ingrese el número del aval y descargue el formulario con un clic  </br>--}%
            %{--Después de llenar y firmar el documento del Aval súbalo al sistema. </br> </br>--}%
                <div class="fila">
                    <div class="labelSvt">
                        <a href="#" class="btn">Aprobar</a>
                    </div>
                </div>
            </g:form>
        </fieldset>
        <script>
            $(function () {
                $('#richText').ckeditor(function () { /* callback code */
                        },
                        {
                            customConfig : '${resource(dir: 'js/jquery/plugins/ckeditor', file: 'config_min.js')}'
                        });
                $(".btn").button();
                $(".descRespaldo").click(function () {
                    location.href = "${createLink(controller:'avales',action:'descargaSolicitud')}/" + $(this).attr("iden");
                });
            });
        </script>
    </body>

</html>