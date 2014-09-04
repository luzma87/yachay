<style type="text/css">
.tablaDetalleMonto, .tablaDetalleMonto th, .tablaDetalleMonto td {
    border          : solid 1px #000000 !important;
    border-collapse : collapse;
}

.num {
    text-align : right;
}
</style>

<div style="padding: 5px;" class="ui-state-error ui-corner-all ui-helper-hidden" id="divError">
    <p>
        <span style="float: left; margin-right: .3em;" class="ui-icon ui-icon-alert"></span>
        <span id="spanError"></span>
    </p>
</div>

<table>
    <tr>
        <th>Año:</th>
        <td>
            <g:select from="${anios}" name="selAnio" optionKey="id" optionValue="anio"/>
        </td>
    </tr>
    <tr>
        <th>Monto:</th>
        <td>
            <g:textField name="txtMonto" style="width: 80px;"/>
            <a href="#" id="btnAddDetalleMonto">Agregar</a>
            (máx. <span id="spanMax" data-max="${solicitud.montoSolicitado}"><g:formatNumber number="${solicitud.montoSolicitado}" type="currency"/></span>)
        </td>
    </tr>
</table>

<table border="1" class="tablaDetalleMonto" width="100%">
    <thead>
        <tr>
            <th>Año</th>
            <th>Monto</th>
        </tr>
    </thead>
    <tbody>
        <g:set var="total" value="${0}"/>
        <g:each in="${solicitud.detallesMonto}" var="dt">
            <tr>
                <td>${dt.anio.anio}</td>
                <td class="num">
                    <g:formatNumber number="${dt.monto}" type="currency"/>
                    <g:set var="total" value="${total + dt.monto}"/>
                </td>
            </tr>
        </g:each>
    </tbody>
    <tfoot>
        <tr>
            <th>
                TOTAL
            </th>
            <th class="num">
                <g:formatNumber number="${total}" type="currency"/>
            </th>
        </tr>
    </tfoot>
</table>

<script type="text/javascript">
    $("#txtMonto").setMask('decimal');
    $("#btnAddDetalleMonto").button({
        icons : {
            primary : "ui-icon-plusthick"
        },
        text  : false
    }).click(function () {
        var max = $("#spanMax").data("max");
        var val = $("#txtMonto").val();
        val = val.replace(new RegExp("\\.", 'g'), replace);
        console.log(max, val);
        return false;
    });
</script>