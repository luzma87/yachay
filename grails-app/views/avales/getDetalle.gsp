<script type="text/javascript" src="${resource(dir: 'js/jquery/plugins', file: 'jquery.meio.mask.js')}"></script>

<table style="width: 100%">
    <thead>
        <tr>
            <th>Componente</th>
            <th>Actividad</th>
            <th>Partida</th>
            <th>Priorizado</th>
            <th>Monto</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <g:set var="total" value="${0}"></g:set>
        <g:each in="${detalle}" var="asg">
            <g:set var="total" value="${total.toDouble() + asg.monto}"></g:set>
            <tr>
                <td>${asg.asignacion.marcoLogico.marcoLogico}</td>
                <td>${asg.asignacion.marcoLogico.numero} - ${asg.asignacion.marcoLogico}</td>
                <td>${asg.asignacion.presupuesto.numero}</td>
                <td style="text-align: right">
                    <g:formatNumber number="${asg.asignacion.priorizado}" type="currency"/>
                </td>
                <td style="text-align: right" id="monto_${asg.id}"
                    valor="${asg.monto}">
                    <g:formatNumber number="${asg.monto}" type="currency"/>
                </td>
                <td style="text-align: center">
                    <g:if test="${band}">
                        <a href="#" class="edit" iden="${asg.id}" asg="${asg.asignacion.id}">Editar</a>
                    </g:if>
                </td>
                <td style="text-align: center">
                    <g:if test="${band}">
                        <a href="#" class="borrar" iden="${asg.id}">Borrar</a>
                    </g:if>
                </td>
            </tr>
        </g:each>
        <tr>
            <td colspan="4" style="font-weight: bold">TOTAL PROCESO</td>
            <td style="font-weight: bold;text-align: right">
                <g:formatNumber number="${total}" type="currency"/>
            </td>
            <td></td>
            <td></td>
        </tr>
    </tbody>
</table>
<script>
    $(".edit").button({icons : { primary : "ui-icon-pencil"}, text : false}).click(function () {
        vaciar();
        $("#dlgMonto").val(number_format($.trim($("#monto_" + $(this).attr("iden")).attr("valor")), 2, ",", ".")).setMask("decimal");
        $("#dlgId").val($(this).attr("iden"));
        getMaximo($(this).attr("asg"));
        $("#dlgEditar").dialog("open");
    });
    $(".borrar").button({icons : { primary : "ui-icon-trash"}, text : false}).click(function () {
        if (confirm("Esta seguro?")) {
            $.ajax({
                type    : "POST",
                url     : "${createLink(action:'borrarDetalle')}",
                data    : {
                    id : $(this).attr("iden")
                },
                success : function (msg) {
                    if (msg == "ok") {
                        getDetalle();
                        vaciar();
                    } else {
                        $.box({
                            title  : "Error",
                            text   : "No se puede borrar porque el proceso tiene un aval o una solicitud de aval pendiente",
                            dialog : {
                                resizable : false,
                                buttons   : {
                                    "Cerrar" : function () {

                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    });
</script>