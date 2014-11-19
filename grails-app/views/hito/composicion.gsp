<script type="text/javascript" src="${resource(dir: 'js/jquery/plugins', file: 'jquery.meio.mask.js')}"></script>
<table style="width: 100%;font-size: 10px">
    <thead>
    <th>Fecha</th>
    <th>Tipo</th>
    <th>Proyecto</th>
    <th>Componente</th>
    <th>Actividad</th>
    <th>Proceso</th>
    <th>Valor</th>
    <th></th>
    </thead>
    <tbody>
    <g:set var="total" value="${0}"></g:set>
    <g:each in="${comp}" var="c">
        <tr>
            <td>${c.fecha.format("dd-MM-yyyy")}</td>
            <g:if test="${c.proyecto}">
                <td class="tipo">Proyecto</td>
                <td>${c.proyecto}</td>
                <td></td>
                <td></td>
                <td></td>
                <td style="text-align: right"><g:formatNumber number="${c.proyecto.valorPriorizado}" type="currency"/></td>
                <g:set var="total" value="${total+c.proyecto.valorPriorizado}"></g:set>
            </g:if>
            <g:if test="${c.marcoLogico}">

                <g:if test="${c.marcoLogico.tipoElemento.id==2}">
                    <td class="tipo">Componente</td>
                    <td>${c.marcoLogico.proyecto}</td>
                    <td>${c.marcoLogico}</td>
                    <td></td>
                    <td></td>
                    <td style="text-align: right"><g:formatNumber number="${c.marcoLogico.totalPriorizado}" type="currency"/></td>

                </g:if>
                <g:else>
                    <td class="tipo">Actividad</td>
                    <td>${c.marcoLogico.proyecto}</td>
                    <td>${c.marcoLogico.marcoLogico}</td>
                    <td>${c.marcoLogico}</td>
                    <td></td>
                    <td style="text-align: right"><g:formatNumber number="${c.marcoLogico.totalPriorizado}" type="currency"/></td>
                </g:else>
                <g:set var="total" value="${total+c.marcoLogico.totalPriorizado}"></g:set>
            </g:if>
            <g:if test="${c.proceso}">
                <td class="tipo">Proceso</td>
                <td>${c.proceso.proyecto}</td>
                <td></td>
                <td></td>
                <td>${c.proceso?.nombre}</td>
                <td style="text-align: right"><g:formatNumber number="${c.proceso.monto}" type="currency"/></td>
                <g:set var="total" value="${total+c.proceso.monto}"></g:set>
            </g:if>
            <td>
                <a href="#" class="borrar" iden="${c.id}">Borrar</a>
            </td>
        </tr>
    </g:each>
    <tr>
        <td style="font-weight: bold" colspan="6">TOTAL</td>
        <td style="text-align: right;font-weight: bold"><g:formatNumber number="${total}" type="currency"/></td>
    </tr>
    </tbody>
</table>
<script>
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
                        detalle()
                    } else {
                        $.box({
                            title  : "Error",
                            text   : "No se puede borrar",
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