<%@ page import="yachay.parametros.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Aprobar anulación aval</title>
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
            <th>Tipo</th>
            <th>Concepto</th>
            <th>Monto</th>
            <th>Estado</th>
            <th>Doc. Respaldo</th>
            %{--<th>Solicitud</th>--}%
        </tr>
        </thead>
        <tbody>

        <tr>
            <td>${solicitud.proceso.nombre}</td>
            <td class="E03">Anulación</td>
            <td>${solicitud.concepto}</td>
            <td style="text-align: right">${solicitud.monto}</td>
            <td style="">${solicitud.estado?.descripcion}</td>
            <td style="text-align: center">
                <a href="#" class="btn descRespaldo" iden="${solicitud.id}">Ver</a>
            </td>

        </tr>

        </tbody>
    </table>
</fieldset>
%{--<fieldset style="width: 95%;padding-bottom: 10px" class="ui-corner-all">--}%
    %{--<legend>Datos para la generación del documento</legend>--}%

    %{--<div class="fila" style="margin-top: 0px">--}%
        %{--<div class="labelSvt" style="width: 180px">Número de aval:</div>--}%

        %{--<div class="fieldSvt-small">--}%
            %{--<input type="text" style="width: 100%;" id="numero" value="${solicitud.numero}" class="ui-corner-all ui-widget-content">--}%
        %{--</div>--}%
    %{--</div>--}%

    %{--<div class="fila" style="height: 300px;position: relative">--}%
        %{--<div class="labelSvt" style="width: 180px;position: absolute;top: 10px">Observaciones:</div>--}%

        %{--<div style="width: 700px;height: 280px;display: inline-block;position: absolute;top: 10px;left: 190px">--}%
            %{--<textarea id="richText" style="width: 100%;height: 100%;resize: none;display: inline-block">${solicitud.observaciones}</textarea>--}%
        %{--</div>--}%
    %{--</div>--}%
    %{--<div class="fila" style="margin-top: 20px">--}%
        %{--<div class="labelSvt">--}%
            %{--<a href="#" class="btn" id="guardarDatosDoc">Guardar</a>--}%

        %{--</div>--}%
        %{--<div class="labelSvt">--}%
            %{--<a href="#" class="btn" id="descargaForm" style="display: inline-block">Descargar</a>--}%
        %{--</div>--}%
    %{--</div>--}%


%{--</fieldset>--}%
<fieldset style="width: 95%;padding-bottom: 10px" class="ui-corner-all">
    <legend>Anular</legend>
    %{--<h3>Descargue el formulario, firmelo y subalo</h3>--}%
    <g:form action="guardarAnulacion" class="frmAprobar" enctype="multipart/form-data">

        <input type="hidden" name="id" value="${solicitud.id}">
        <div class="fila">
            <div class="labelSvt" style="width: 180px">Documento de respaldo:</div>

            <div class="fieldSvt-medium">
                <input type="file" id="archivo" name="archivo" style="display: inline-block">
            </div>
        </div>

    %{--Ingrese el número del aval y descargue el formulario con un clic  </br>--}%
    %{--Después de llenar y firmar el documento del Aval súbalo al sistema. </br> </br>--}%
        <div class="fila">
            <div class="labelSvt">
                <a href="#" class="btn" id="aprobar">Anular aval</a>
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
        $("#descargaForm").button().click(function () {

            var url = "${createLink(controller: 'reportes', action: 'certificacion')}/?id=${solicitud.id}Wusu=${session.usuario.id}";
            location.href = "${createLink(controller:'pdf',action:'pdfLink')}?url=" + url+"&filename=aval_"+ $("#numero").val()+".pdf"
        });
        $("#aprobar").click(function(){
            var file = $("#archivo").val()
            var msg =""
            if(file.length<1){
                msg+="<br>Por favor seleccione un archivo."
            }else{
                var ext = file.split('.').pop();
                if(ext!="pdf"){
                    msg+="<br>Por favor seleccione un archivo de formato pdf. El formato "+ext+" no es aceptado por el sistema"
                }
            }
            if(msg==""){
                $(".frmAprobar").submit()
            }else{
                $.box({
                    title:"Error",
                    text:msg,
                    dialog: {
                        resizable: false,
                        buttons  : {
                            "Cerrar":function(){

                            }
                        }
                    }
                });
            }
        });
        $("#guardarDatosDoc").click(function(){
            var aval = $("#numero").val()
            var obs = $("#richText").val()
            $.ajax({
                type:"POST", url:"${createLink(action:'guarDatosDoc', controller: 'revisionAval')}",
                data:{
                    id:"${solicitud.id}",
                    aval:aval,
                    obs:obs
                },
                success:function (msg) {
                    $.box({
                        title:"Resultado",
                        text:"Datos guardados",
                        dialog: {
                            resizable: false,
                            buttons  : {
                                "Cerrar":function(){

                                }
                            }
                        }
                    });

                }
            });
        });
    });
</script>
</body>

</html>