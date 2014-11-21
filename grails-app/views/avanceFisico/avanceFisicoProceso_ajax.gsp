<table border="1" width="100%" style="font-size: 10px">
    <thead>
        <tr>
            <th>Actividad</th>
            <th>Aporte</th>
            <th>Completado</th>
            <th style="width:30px;"></th>
            <th style="width:30px;"></th>
        </tr>
    </thead>
    <tbody>
        <g:set var="total" value="${0}"/>
        <g:set var="completado" value="${0}"/>
        <g:each in="${avances}" var="avance" status="i">
            <g:set var="total" value="${total + avance.avance}"/>
            <tr>
                <td>${avance.observaciones}</td>
                <td style="text-align: right;">
                    <g:formatNumber number="${avance.avance}" maxFractionDigits="2" minFractionDigits="2"/>%
                    <g:if test="${avance.completado}">
                        <g:set var="completado" value="${completado + avance.avance}"/>
                    </g:if>
                </td>
                <td>${avance.completado?.format("dd-MM-yyyy")}</td>
                <td>
                    <g:if test="${!avance.completado}">
                        <a href="#" class="btnCompletar" id="${avance.id}">Completar</a>
                    </g:if>
                </td>
                <td>
                    <g:if test="${!avance.completado}">
                        <a href="#" class="btnDelete" id="${avance.id}">Eliminar</a>
                    </g:if>
                </td>
            </tr>
        </g:each>
        <tr>
            <td style="font-weight: bold"></td>
            <td style="font-weight: bold;text-align: right">
                Total planificado: <g:formatNumber number="${total}" minFractionDigits="2" maxFractionDigits="2"/>%
            </td>
            <td style="font-weight: bold;text-align: right">
                Total completado: <g:formatNumber number="${completado}" minFractionDigits="2" maxFractionDigits="2"/>%
            </td>
        </tr>
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
    $(".btnCompletar").button({
        text  : false,
        icons : {
            primary : "ui-icon-check"
        }
    }).click(function () {
        var id = $(this).attr("id");
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'completar')}",
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