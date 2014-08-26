<table style="width: 98%;font-size: 10px;margin-top: 10px">
    <thead>
    <tr>
        <th sort="numero" class="sort ${(sort=='numero')?order:''}">Numero</th>
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
            <td>${aval.fechaAprobacion.format("yyyy")}-CP No.${aval.numero}</td>
            <td style="text-align: center">${aval.proceso.proyecto}</td>
            <td>${aval.proceso.nombre}</td>
            <td style="text-align: right">${aval.monto}</td>
            <td>${app.yachai.SolicitudAval.findAll("from SolicitudAval where estado="+ estado.id+" and tipo is null and aval="+aval.id)?.pop()?.usuario}</td>
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
                <g:if test="${aval.estado.codigo=='E002'}">
                    <a href="#">Liberar</a>
                </g:if>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
<script>
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