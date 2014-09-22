<table style="width: 98%;font-size: 10px">
    <thead>
    <tr>
        <th>Fecha</th>
        <th>Proyecto</th>
        <th>Proceso</th>
        <th>Tipo</th>
        <th>Requiriente</th>
        <th>Concepto</th>
        <th>Monto</th>
        <th>Estado</th>
        <th>Solicitud</th>
        <th># Aval</th>
        <th>F. Emisión</th>
        <th>Aval</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${datos}" var="sol">
        <tr>
            <td style="text-align: center">${sol.fecha.format("dd-MM-yyyy")}</td>
            <td style="text-align: center">${sol.proceso.proyecto}</td>
            <td>${sol.proceso.nombre}</td>
            <td style="text-align: center" class="${(sol.tipo=='A')?'E03':'E02'}">${(sol.tipo=="A")?'Anulación':'Aprobación'}</td>
            <td>${sol.usuario}</td>
            <td>${sol.concepto}</td>
            <td style="text-align: right">
                <g:formatNumber number="${sol.monto}" format="###,##0" minFractionDigits="2" maxFractionDigits="2" ></g:formatNumber>
              </td>
            <td style="text-align: center" class="${sol.estado?.codigo}">${sol.estado?.descripcion}</td>
            <td style="text-align: center">
                <g:if test="${sol.tipo!='A'}">
                    <a href="#" class="btn imprimiSolicitud" iden="${sol.id}">Ver</a>
                </g:if>
            </td>
            <td>
                <g:if test="${sol.aval}">
                    ${sol.aval.fechaAprobacion.format("yyyy")}-GP No.<tdn:imprimeNumero aval="${sol.aval.id}"/>
                </g:if>
            </td>
            <td>
                <g:if test="${sol.aval}">
                    ${sol.aval.fechaAprobacion.format("dd-MM-yyyy")}
                </g:if>
            </td>
            <td style="text-align: center">
                <g:if test="${sol.aval}">
                    <a href="#" class="imprimiAval" iden="${sol.aval.id}">Imprimir</a>
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
</script>