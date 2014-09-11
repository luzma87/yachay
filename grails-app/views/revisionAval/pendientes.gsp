<%@ page import="app.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Solicitudes pendientes</title>
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

        th{
            background-color: #595292;

        }

    </style>

</head>
<body>
<g:if test="${flash.message}">
    <div class="message ui-state-highlight ui-corner-all">
        ${flash.message}
    </div>
</g:if>
<div class="fila">
    <g:link controller="avales" action="listaProcesos" class="btn">Procesos</g:link>
</div>
<div id="tabs" style="width: 1050px;margin-top: 10px;">
    <ul>
        <li><a href="#solicitudes">Solicitudes pendientes</a></li>
        <li><a href="#historial">Historial Solicitudes</a></li>
        %{--<li><a href="#solicitudes">Historial</a></li>--}%
    </ul>
    <div id="solicitudes" style="width: 1000px;">
        <g:if test="${solicitudes.size()>0}">
            <table style="width: 100%;margin-top: 10px;font-size: 10px;" >
                <thead>
                <tr>
                    <th># Sol.</th>
                    <th>Unidad</th>
                    <th>Proceso</th>
                    <th>Tipo</th>
                    <th>Concepto</th>
                    <th>Monto</th>
                    <th>Estado</th>
                    <th>Doc. Respaldo</th>
                    <th>Solicitud</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${solicitudes}" var="p">
                    <tr>
                        <td>${p.unidad?.codigo}-${p.numero}</td>
                        <td>${p.unidad}</td>
                        <td>${p.proceso.nombre}</td>
                        <td class="${(p.tipo=='A')?'E03':'E02'}">${(p.tipo=="A")?'Anulación':'Aprobación'}</td>
                        <td>${p.concepto}</td>
                        <td style="text-align: right"> <g:formatNumber number="${p.monto}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber></td>
                        <td style="text-align: center" class="${p.estado?.codigo}">${p.estado?.descripcion}</td>
                        <td style="text-align: center">
                            <a href="#" class="btn descRespaldo" iden="${p.id}">Ver</a>
                        </td>
                        <td style="text-align: center">
                            <g:if test="${p.tipo!='A'}">
                                <a href="#" class="btn imprimiSolicitud" iden="${p.id}">Ver</a>
                            </g:if>
                        </td>
                        <td style="text-align: center">
                            <g:if test="${p.tipo!='A'}">
                                <a href="${g.createLink(action: 'aprobarAval',id: p.id)}" class="aprobar btn" style="margin: 5px" >Aprobar</a>
                            </g:if>
                            <g:else>
                                <a href="${g.createLink(action: 'aprobarAnulacion',id: p.id)}" class="aprobarAnulacion btn"   style="margin: 5px">Aprobar</a>
                            </g:else>
                            <a href="#" class="negar btn"  iden="${p.id}">Negar</a>
                        </td>
                    </tr>
                </g:each>

                </tbody>
            </table>
        </g:if>
    </div>
    <div id="historial" style="width: 1000px;">
        <div class="fila" style="font-size: 10px">
            <div class="labelSvt" style="width:40px;">Año:</div>
            <div class="fieldSvt-small" style="width: 80px">
                <g:select from="${app.Anio.list([sort: 'anio'])}" id="anio" name="anio" optionKey="id" optionValue="anio" value="${actual.id}" class="ui-corner-all"></g:select>
            </div>
            <div class="labelSvt" style="width: 60px">Número:</div>
            <div class="fieldSvt-medium">${actual.anio}-CP No.<input type="text" id="numero" class="ui-corner-all" style="width: 100px"></div>
            <div class="labelSvt" style="width: 65px">Proceso:</div>
            <div class="fieldSvt-xl" ><input type="text" id="descProceso" class="ui-corner-all" style="width: 90%"></div>
            <div class="fieldSvt-small" style="width: 80px">
                <a href="#" id="buscar">Filtrar</a>
            </div>
        </div>
        <div id="detalle" style="width: 100%;height: 500px;overflow: auto"></div>
    </div>

</div>
<div id="negar">
    <input type="hidden" id="avalId">
    Esta seguro que desea negar esta solicitud de certificación?
</div>
<script>
    function cargarHistorial(anio,numero,proceso){


        $.ajax({
            type:"POST", url:"${createLink(action:'historial', controller: 'revisionAval')}",
            data:{
                anio:anio,
                numero:numero,
                proceso:proceso
            },
            success:function (msg) {
               $("#detalle").html(msg)

            }
        });

    }
    $(".btn").button()
    $("#buscar").button().click(function(){
        cargarHistorial($("#anio").val(),$("#numero").val(),$("#descProceso").val())
    })
    $(".descRespaldo").click(function(){
        location.href = "${createLink(controller:'avales',action:'descargaSolicitud')}/"+$(this).attr("iden")

    });
    $(".imprimiSolicitud").button({icons:{ primary:"ui-icon-print"},text:false}).click(function(){
        var url = "${g.createLink(controller: 'reporteSolicitud',action: 'imprimirSolicitudAval')}/?id="+$(this).attr("iden")
        location.href = "${createLink(controller:'pdf',action:'pdfLink')}?url=" + url+"&filename=Solicitud.pdf"
    })
    $("#tabs").tabs()
    $(".aprobar").button({icons:{ primary:"ui-icon-check"},text:false});
    $(".aprobarAnulacion").button({icons:{ primary:"ui-icon-check"},text:false});
    $(".negar").button({icons:{ primary:"ui-icon-close"},text:false}).click(function(){
        $("#avalId").val($(this).attr("iden"))
        $("#negar").dialog("open")
    });
    $("#negar").dialog({
        autoOpen:false,
        resizable:false,
        title:'Negar solicitud',
        modal:true,
        draggable:true,
        width:400,
        height:150,
        position:'center',
        open:function (event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        buttons:{
            "Cancelar":function () {
                $("#negar").dialog("close")
            },"Negar":function(){
                $.ajax({
                    type:"POST", url:"${createLink(action:'negarAval', controller: 'revisionAval')}",
                    data:"id=" + $("#avalId").val(),
                    success:function (msg) {
                        if(msg!="no")
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