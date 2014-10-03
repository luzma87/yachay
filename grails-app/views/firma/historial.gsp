<table style="width: 98%;font-size: 10px">
    <thead>
    <tr>
        <th>Fecha</th>
        <th>Documento</th>
        <th>Concepto</th>
        <th>Estado</th>
        <th>Ver</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${datos}" var="firma">
        <tr>
            <td style="text-align: center">${firma.fecha.format("dd-MM-yyyy")}</td>
            <td style="text-align: center">${firma.documento}</td>
            <td>${firma.concepto}</td>
            <td>
                <g:if test="${firma.estado=='F'}">
                    Firmado
                </g:if>
                <g:else>
                    Negado
                </g:else>
            </td>
            <td style="text-align: center">
                <g:if test="${firma.estado=='F'}">
                    <a href="#" class="imprimir" iden="${firma.id}">Ver</a>
                </g:if>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
<script>
    $(".imprimir").button({icons:{ primary:"ui-icon-print"},text:false}).click(function(){
        location.href = "${createLink(controller:'firma',action:'ver')}/"+$(this).attr("iden")
    })
</script>