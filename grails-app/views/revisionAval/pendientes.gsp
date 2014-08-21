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
        <li><a href="#solicitudes">Solicitudes</a></li>
        %{--<li><a href="#solicitudes">Historial</a></li>--}%
    </ul>
    <div id="solicitudes" style="width: 1000px;">
        <g:if test="${solicitudes.size()>0}">
            <table style="width: 95%;margin-top: 10px" >
                <thead>
                <tr>
                    <th>Proceso</th>
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
                        <td>${p.proceso.nombre}</td>
                        <td>${p.concepto}</td>
                        <td style="text-align: right">${p.monto}</td>
                        <td style="">${p.estado?.descripcion}</td>
                        <td style="text-align: center">
                            <a href="#" class="btn descRespaldo" iden="${p.id}">Ver</a>
                        </td>
                        <td style="text-align: center">
                            <a href="#" class="btn">Ver</a>
                        </td>
                        <td style="text-align: center">
                            <a href="${g.createLink(action: 'aprobarAval',id: p.id)}" class="aprobar btn" >Aprobar</a>
                            <a href="#" class="negar btn"  iden="${p.id}">Negar</a>
                        </td>
                    </tr>
                </g:each>

                </tbody>
            </table>
        </g:if>
    </div>

</div>
<div id="negar">
    <input type="hidden" id="avalId">
    Esta seguro que desea negar esta solicitud de certificación?
</div>
<script>
    $(".btn").button()
    $(".descRespaldo").click(function(){
        location.href = "${createLink(controller:'avales',action:'descargaSolicitud')}/"+$(this).attr("iden")

    });
    $("#tabs").tabs()
    $(".aprobar").button({icons:{ primary:"ui-icon-check"},text:false});
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