<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Avales de la unidad ${unidad} del año: ${actual} </title>

    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}"
          type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}"
          type="text/css"/>

    <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript"
            language="JavaScript"></script>
    <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}"
            type="text/javascript" language="JavaScript"></script>
    <style>
    table{
        border-collapse: collapse;
        border-spacing: 0px 0px;
        border:1px solid black;

    }
    th{
        background: #1395c6;
        color: white;
        border: 1px solid black;
    }
    td{
        border: 1px solid black;
    }

    .label{
        width: 120px;
        font-weight: bold;
        display: inline-block;
        margin-right: 30px;
    }
    input{
        padding: 5px;
    }

    </style>
</head>

<body>

<div style="width: 100%;height: 40px;">
    Anio: <g:select from="${yachay.parametros.poaPac.Anio.list()}" name="anio" id="anio_asg" optionKey="id" optionValue="anio" value="${actual.id}"></g:select>
</div>
<table style="width: 100%" >
    <thead>
    <tr>
        <th>
            Proyecto
        </th>
        <th>
            Componente
        </th>
        <th>
            Actividad
        </th>
        <th>
            Partida
        </th>
        <th>
            # Aval
        </th>
        <th>
            Fecha
        </th>
        <th>
            Monto
        </th>
        <th>
            Estado
        </th>
        <th>
            Acciones
        </th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${certificados}" var="cer" status="i">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <td>${cer.asignacion?.marcoLogico.proyecto}</td>
            <td>${cer.asignacion?.marcoLogico.marcoLogico}</td>
            <td>${cer.asignacion?.marcoLogico}</td>
            <td>${cer.asignacion?.presupuesto.numero}</td>
            <td style="text-align: center">${cer.acuerdo}</td>
            <td style="text-align: center">${cer.fechaRevision?.format("dd-MM-yyyy")}</td>
            <td style="text-align: right">${cer.monto}</td>
            <g:if test="${cer.estado==1}">
                <td  style="text-align: center;background: #d6eba9" >
                    Aprobado
                    <g:if test="${cer.pathSolicitudAnulacion!='' && cer.pathSolicitudAnulacion!=null}">
                        <g:if test="${!cer.fechaRevisionAnulacion}">
                            <br><b>Con solicitud de anulación</b>
                        </g:if>
                    </g:if>
                </td>
                <td>
                    <a href="#" class="btn verCert" iden="${cer.id}">Ver certificado</a>
                    <g:if test="${cer.pathSolicitudAnulacion==null || cer.fechaRevisionAnulacion!=null }">
                        <a href="#" class="btn solAnulacion" iden="${cer.id}">Solicitar anulación</a>
                    </g:if>

                </td>
            </g:if>
            <g:if test="${cer.estado==2}">
                <td  style="text-align: center;background: #eba597" >
                    Negado
                </td>
                <td></td>
            </g:if>
            <g:if test="${cer.estado==0}">
                <td  style="text-align: center;" >
                    Pendiente
                </td>
                <td></td>
            </g:if>
            <g:if test="${cer.estado==3}">
                <td  style="text-align: center;background: #eba597" >
                    Anulado
                </td>
                <td></td>
            </g:if>
            <g:if test="${cer.estado==4}">
                <td  style="text-align: center;;background: #d6eba9" >
                    Liberado
                </td>
                <td></td>
            </g:if>
        </tr>
    </g:each>
    </tbody>
</table>
<div id="solicitud">
    <g:uploadForm action="saveSolicitudAnulacion" controller="certificacion" class="formSol">
        <input type="hidden" name="id" id="certId">
        <div style="width: 100%;margin-top: 10px;">
            <div class="label">Concepto:</div>
            <textarea id="concepto" name="conceptoAnulacion" style="resize: none;width: 450px;height: 80px" class="ui-corner-all ui-widget-content" title="Máximo 1024 caracteres"></textarea>
        </div>
        <div style="width: 100%;margin-top: 10px;height: 40px">
            <div class="label">Archivo:</div>
            <input type="file" name="file" id="file" >
        </div>
    </g:uploadForm>
</div>
<script>
    $("#anio_asg").change(function () {
        location.href = "${createLink(controller:'certificacion',action:'certificados')}?anio=" + $(this).val()
    });
    $(".verCert").button({icons:{ primary:"ui-icon-print"},text:false}).click(function(){
        location.href = "${createLink(controller:'certificacion',action:'descargaDocumento')}/"+$(this).attr("iden")
    })
    $(".solAnulacion").button({icons:{ primary:"ui-icon-pencil"},text:false}).click(function(){
        $("#certId").val($(this).attr("iden"))
        $("#solicitud").dialog("open")
    })
    $("#solicitud").dialog({
        autoOpen:false,
        resizable:false,
        title:'Solicitud de anulación',
        modal:true,
        draggable:true,
        width:500,
        height:450,
        position:'center',
        buttons:{
            "Cerrar":function () {
                $("#solicitud").dialog("close")
            },
            "Guardar":function () {

                $(".formSol").submit()
            }
        }
    });
</script>
</body>
</html>