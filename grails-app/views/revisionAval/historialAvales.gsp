
<style type="text/css">
    th{
        background-color: #595292;
    }
</style>

<table style="width: 98%;font-size: 10px;margin-top: 10px">
    <thead>
    <tr>
        <th sort="numero" class="sort ${(sort=='numero')?order:''}">Número</th>
        <th sort="proyecto" class="sort ${(sort=='proyecto')?order:''}">Proyecto</th>
        <th sort="proceso" class="sort ${(sort=='proceso')?order:''}">Proceso</th>
        <th sort="monto" class="sort ${(sort=='monto')?order:''}">Monto</th>
        <th sort="requiriente" class="">Requiriente</th>
        <th sort="fechaAprobacion" class="sort ${(sort=='fechaAprobacion')?order:''}">F. Emisión</th>
        <th sort="estado" class="sort ${(sort=='estado')?order:''}">Estado</th>
        <th >Aval</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${datos}" var="aval">
        <tr>
            <td>${aval.fechaAprobacion.format("yyyy")}-GP No.<tdn:imprimeNumero aval="${aval.id}"/></td>
            <td style="text-align: center">${aval.proceso.proyecto}</td>
            <td>${aval.proceso.nombre}</td>
            <td style="text-align: right">
                <g:formatNumber number="${aval.monto}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
                </td>
            <td>${yachay.avales.SolicitudAval.findAll("from SolicitudAval where estado="+ estado.id+" and tipo is null and aval="+aval.id)?.pop()?.usuario}</td>
            <td>
                ${aval.fechaAprobacion.format("dd-MM-yyyy")}
            </td>
            <td style="text-align: center" class="${aval.estado?.codigo}">
                ${aval.estado?.descripcion}
            </td>
            <td style="text-align: center">
                <a href="#" class="imprimiAval" iden="${aval.id}">Imprimir</a>
            </td>
            <td style="text-align: center">
                <g:if test="${aval.estado.codigo=='E02'}">
                    <a href="#" class="liberar" iden="${aval.id}" max="${aval.monto}">Liberar</a>
                    %{--<g:if test="${(now-aval.fechaAprobacion)>-1}">--}%
                        %{--<a href="#" class="caducar" iden="${aval.id}">Caducar</a>--}%
                    %{--</g:if>--}%
                </g:if>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
<div id="dlgCaducar">
    <g:form action="caducarAval" class="frmCaducar" enctype="multipart/form-data">
        <input type="hidden" name="id" id="idCaducar">

    </g:form>
</div>
<div id="dlgLiberar">
    <g:form action="guardarLiberacion" class="frmLiberar" enctype="multipart/form-data">
        <input type="hidden" name="id" id="idLiberar">
        <div class="fila">
            <div class="labelSvt">Contrato:</div>
            <div class="fieldSvt-small">
                <input type="text" name="contrato" class="ui-corner-all" id="contrato" style="width: 100%">
            </div>
        </div>
        <div class="fila">
            <div class="labelSvt">Monto:</div>
            <div class="fieldSvt-small">
                <input type="text" name="monto"  class="ui-corner-all" id="monto" style="width: 100%;text-align: right">
            </div>
            <div class="fieldSvt-medium" style="margin-left: 20px" >
                Máximo: $<span id="max"></span>
            </div>
        </div>
        <div class="fila" style="margin-top: 35px">
            <div class="labelSvt" style="width: 180px; ">Documento de respaldo:</div>
            <div class="fieldSvt-medium">
                <input type="file" id="archivo" name="archivo" style="display: inline-block">
            </div>
        </div>
    </g:form>
</div>
<script>
    $(".liberar").button({icons:{ primary:"ui-icon-extlink"},text:false}).click(function(){
//        $("#idLiberar").val($(this).attr("iden"))
//        $("#max").html($(this).attr("max"))
//        $("#monto").val("")
//        $("#contrato").val("")
//        $("#archivo").val("")
//        $("#dlgLiberar").dialog("open")
        location.href="${g.createLink(action: 'liberarAval')}/"+$(this).attr("iden")
    })
    $(".caducar").button({icons:{ primary:"ui-icon-alert"},text:false}).click(function(){
        $("#idCaducar").val($(this).attr("iden"))
        $("#dlgCaducar").dialog("open")
    })

    $("#dlgLiberar").dialog({
        autoOpen:false,
        position:"center",
        title:'Liberar Aval',
        width:500,
        modal:true,
        buttons:{
            "Liberar":function(){
                var file = $("#archivo").val()
                var contrato = $("#contrato").val()
                var monto = $("#monto").val()
                var msg =""
                if(monto==""){
                    msg+="<br>Por favor ingrese un monto."
                }
                if(isNaN(monto)){
                    msg+="<br>El monto debe ser un número positivo mayor a cero."
                }else{
                    if(monto*1<0){
                        msg+="<br>El monto debe ser un número positivo mayor a cero."
                    } else{
                        if(monto>$("#max").html()*1){
                            msg+="<br>El monto no puede ser mayor a: "+$("#max").html()
                        }
                    }
                }
                if(contrato==""){
                    msg+="<br>Ingrese un número de contrato."
                }

                if(file.length<1){
                    msg+="<br>Por favor seleccione un archivo."
                }else{
                    var ext = file.split('.').pop();
                    if(ext!="pdf"){
                        msg+="<br>Por favor seleccione un archivo de formato pdf. El formato "+ext+" no es aceptado por el sistema"
                    }
                }
                if(msg==""){
                    $(".frmLiberar").submit()
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

            },"Cerrar":function(){
                $("#dlgLiberar").dialog("close")
            }
        }
    })
    $("#dlgCaducar").dialog({
        autoOpen:false,
        position:"center",
        title:'Caducar Aval',
        width:500,
        modal:true,
        buttons:{
            "Caducar":function(){

                var msg =""

                if(msg==""){
                    $(".frmCaducar").submit()
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

            },"Cerrar":function(){
                $("#dlgCaducar").dialog("close")
            }
        }
    })

    $(".imprimiAval").button({icons:{ primary:"ui-icon-print"},text:false}).click(function(){
        location.href = "${createLink(controller:'avales',action:'descargaAval')}/"+$(this).attr("iden")
    })
    $(".imprimiSolicitud").button({icons:{ primary:"ui-icon-print"},text:false}).click(function(){
        var url = "${g.createLink(controller: 'reporteSolicitud',action: 'imprimirSolicitudAval')}/?id="+$(this).attr("iden")
        location.href = "${createLink(controller:'pdf',action:'pdfLink')}?url=" + url+"&filename=solicitud.pdf"
    })
    $(".sort").click(function(){
        var header = $(this)
        var sort = header.attr("sort")
        var order =""
        if(header.hasClass("asc")){
            order="desc"
        }else{
            order="asc"
        }
        cargarHistorialSort($("#anio").val(),$("#numero").val(),$("#descProceso").val(),sort,order)
    }).css({"cursor":"pointer"})
</script>