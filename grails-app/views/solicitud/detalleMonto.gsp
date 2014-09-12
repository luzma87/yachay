<style type="text/css">
.tablaDetalleMonto {
    border-collapse : collapse;
}

.tablaDetalleMonto, .tablaDetalleMonto tr, .tablaDetalleMonto tr th, .tablaDetalleMonto th, .tablaDetalleMonto td {
    border : solid 1px #000000 !important;
}

.num {
    text-align : right;
}
</style>

<div style="padding: 5px;" class="ui-widget-content ui-corner-all">
    <p>
        <span style="float: left; margin-right: .3em;" class="ui-icon ui-icon-info"></span>
        Los cambios efectuados no se guardarán hasta no hacer clic en el botón "Guardar".<br/>
    </p>
</div>

<div style="padding: 5px;" class="ui-state-error ui-corner-all ui-helper-hidden" id="divError">
    <p>
        <span style="float: left; margin-right: .3em;" class="ui-icon ui-icon-alert"></span>
        <span id="spanError"></span>
    </p>
</div>

<table>
    <tr>
        <th>Monto solicitado:</th>
        <td>
            <g:textField name="txtMax" style="width: 95px;" max="${solicitud.actividad.monto}"
                         value="${g.formatNumber(number: solicitud.montoSolicitado, maxFractionDigits: 2, minFractionDigits: 2)}"/>
            <a href="#" id="btnCambiaMax">Modificar (si las asignaciones superan el nuevo máximo serán eliminadas)</a>
        </td>
    </tr>
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
        </td>
    </tr>
</table>

<div>
    Monto máximo a asignar:
    <span id="spanMax" max="${solicitud.montoSolicitado}">
        ${formatNumber(number: solicitud.montoSolicitado, type: "currency")}
    </span>
</div>

<table border="1" class="tablaDetalleMonto" width="100%">
    <thead>
        <tr>
            <th style="width: 127px;">Año</th>
            <th style="width: 128px;">Monto</th>
            <th style="width: 70px;">Eliminar</th>
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
                <td style="text-align: center;">
                    <a href="#" class="btnDelete">Eliminar</a>
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

    var maximo = parseFloat($("#spanMax").attr("max"));

    function updateTotal() {
        var total = 0;
        $("#tb").children().each(function () {
            total += parseFloat($(this).attr("val"));
        });
        $("#thTotal").text("$" + number_format(total, 2, ",", "."));
        if (total > maximo) {
            $("#thTotal").addClass("ui-state-error");
        } else {
            $("#thTotal").removeClass("ui-state-error");
        }
        return total;
    }

    function cambiaMax() {
        var val = $("#txtMax").val();
        val = val.replace(new RegExp("\\.", 'g'), "");
        val = val.replace(new RegExp(",", 'g'), ".");
        val = parseFloat(val);

        if (val > maximo || val <= 0) {
            $("#spanError").text("Por favor ingrese un valor mayor que 0,00 y menor o igual que " + number_format(maximo, 2, ",", "."));
            $("#txtMax").val(number_format(maximo, 2, ".", "")).setMask("decimal");
            $("#divError").removeClass("ui-state-highlight").addClass("ui-state-error").show();
        } else {
            $("#divError").hide();

            $.ajax({
                type    : "POST",
                url     : "${createLink(action:'cambiarMax')}",
                data    : {
                    id    : "${solicitud.id}",
                    monto : val
                },
                success : function (msg) {
                    var parts = msg.split("_");
                    $("#spanError").text(parts[1]);
                    if (parts[0] == "NO") {
                        $("#divError").removeClass("ui-state-highlight").addClass("ui-state-error").show();
                    } else {
                        $("#divError").addClass("ui-state-highlight").removeClass("ui-state-error").show();
                        maximo = val;
                        $("#spanMax").text(number_format(maximo, 2, ",", "."));
                    }
                }
            });
        }
    }

    function addRow() {
        var val = $("#txtMonto").val();
        val = val.replace(new RegExp("\\.", 'g'), "");
        val = val.replace(new RegExp(",", 'g'), ".");
        val = parseFloat(val);

        if (val > maximo || val <= 0) {
            $("#spanError").text("Por favor ingrese un valor mayor que 0,00 y menor o igual que " + number_format(maximo, 2, ",", "."));
            $("#txtMonto").val(number_format(maximo, 2, ".", "")).setMask("decimal");
            $("#divError").show();
        } else {
            $("#divError").hide();

            var anioId = $("#selAnio").val();
            var anio = $("#selAnio option:selected").text();

            var $row = $("<tr>");
            $row.addClass(anio);
            $row.attr("val", val);
            var $tdAnio = $("<td>" + anio + "</td>");
            var $tdMonto = $("<td class='num'>$" + number_format(val, 2, ",", ".") + "</td>");
            var $tdDelete = $("<td style='text-align:center;'></td>");
            var $btnDelete = $("<a href='#' class='btnDelete'>Eliminar</a>");
            $btnDelete.button({
                icons : {
                    primary : "ui-icon-trash"
                },
                text  : false
            }).click(function () {
                removeRow($(this));
            });
            $tdDelete.append($btnDelete);
            $row.append($tdAnio);
            $row.append($tdMonto);
            $row.append($tdDelete);
            var $oldRow = $("#tb").find("." + anio);
            if ($oldRow.length == 0) {
                $("#tb").append($row);
            } else {
                $oldRow.replaceWith($row);
            }

            updateTotal();
        }
    }

    function removeRow($btn) {
        $btn.parents("tr").remove();
        updateTotal();
    }

    $("#txtMonto").setMask('decimal').keyup(function (ev) {
        if (ev.keyCode == 13) {
            addRow();
        }
    });

    $("#txtMax").setMask('decimal').keyup(function (ev) {
        if (ev.keyCode == 13) {
            cambiaMax();
        }
    });

    $("#btnAddDetalleMonto").button({
        icons : {
            primary : "ui-icon-plusthick"
        },
        text  : false
    }).click(function () {
        addRow();
    });

    $("#btnCambiaMax").button({
        icons : {
            primary : "ui-icon-refresh"
        },
        text  : false
    }).click(function () {
        cambiaMax();
    });

    $(".btnDelete").button({
        icons : {
            primary : "ui-icon-trash"
        },
        text  : false
    }).click(function () {
        removeRow($(this));
    });

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
</script>