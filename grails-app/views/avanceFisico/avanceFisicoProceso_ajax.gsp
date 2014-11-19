<table border="1" width="100%">
    <thead>
        <tr>
            <th>Fecha</th>
            <th>Avance</th>
            <th>Observaciones</th>
            <th style="width:30px;"></th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${avances}" var="avance" status="i">
            <tr>
                <td>${avance.fecha.format("dd-MM-yyyy")}</td>
                <td style="text-align: right;">
                    <g:formatNumber number="${avance.avance}" maxFractionDigits="2" minFractionDigits="2"/> %
                </td>
                <td>${avance.observaciones}</td>
                <td>
                    <g:if test="${i == avances.size() - 1}">
                        <a href="#" class="btnDelete" id="${avance.id}">Eliminar</a>
                    </g:if>
                </td>
            </tr>
        </g:each>
    </tbody>
</table>

<script type="text/javascript">
    $(".btnDelete").button({
        text  : false,
        icons : {
            primary : "ui-icon-trash"
        }
    }).click(function () {
        var id = $(this).attr("id");
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'deleteAvanceFisicoProceso_ajax')}",
            data    : {
                id : id
            },
            success : function (msg) {
                updateAll(msg);
            }
        });
        return false;
    });
</script>