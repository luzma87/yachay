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
            <g:textField name="txtMonto" style="width: 95px;"/>
            <a href="#" id="btnAddDetalleMonto">Agregar</a>
            (máx. <span id="spanMax" max="${solicitud.montoSolicitado}"><g:formatNumber number="${solicitud.montoSolicitado}" type="currency"/></span>)
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
    <tbody id="tb">
        <g:set var="total" value="${0}"/>
        <g:each in="${solicitud.detallesMonto}" var="dt">
            <tr class="${dt.anio.anio}" val="${dt.monto}">
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
            <th class="num" id="thTotal">
                <g:formatNumber number="${total}" type="currency"/>
            </th>
        </tr>
    </tfoot>
</table>

<script type="text/javascript">
    function guardar() {
        var max = $("#spanMax").attr("max");
        max = parseFloat(max);
        var val = $("#txtMonto").val();
        val = val.replace(new RegExp("\\.", 'g'), "");
        val = val.replace(new RegExp(",", 'g'), ".");
        val = parseFloat(val);

        if (val > max || val <= 0) {
            $("#spanError").text("Por favor ingrese un valor mayor que 0,00 y menor o igual que " + number_format(max, 2, ",", "."));
            $("#txtMonto").val(number_format(max, 2, ".", "")).setMask("decimal");
            $("#divError").show();
        } else {
            $("#divError").hide();
            $.ajax({
                type    : "POST",
                url     : "${createLink(action:'addDetalleMonto')}",
                data    : {
                    id    : "${solicitud.id}",
                    monto : val,
                    anio  : $("#selAnio").val()
                },
                success : function (msg) {
                    if (msg == "OK") {
                        var anio = $("#selAnio option:selected").text();
                        var $row = $("<tr>");
                        $row.addClass(anio);
                        $row.attr("val", val);
                        var $tdAnio = $("<td>" + anio + "</td>");
                        var $tdMonto = $("<td class='num'>" + number_format(val, 2, ",", ".") + "</td>");
                        $row.append($tdAnio);
                        $row.append($tdMonto);
                        var $oldRow = $("#tb").find("." + anio);
                        if ($oldRow.length == 0) {
                            $("#tb").append($row);
                        } else {
                            $oldRow.replaceWith($row);
                        }
                        var total = 0;
                        $("#tb").children().each(function () {
                            total += parseFloat($(this).attr("val"));
                        });
                        $("#thTotal").text("$" + number_format(total, 2, ",", "."));
                        $("#txtMonto").val(0).setMask("decimal");
                        max = parseFloat("${solicitud.montoSolicitado}") - total;
                        $("#spanMax").text("$" + number_format(max, 2, ",", ".")).attr("max", max);
                    } else {
                        var parts = msg.split("_");
                        $("#spanError").text(parts[1]);
                        $("#divError").show();
                    }
                }
            });
        }
    }

    $("#txtMonto").setMask('decimal').keyup(function (ev) {
        if (ev.keyCode == 13) {
            guardar();
            return false;
        }
    });
    $("#btnAddDetalleMonto").button({
        icons : {
            primary : "ui-icon-plusthick"
        },
        text  : false
    }).click(function () {
        guardar();
        return false;
    });
</script>