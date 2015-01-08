<table style="width: 100%">
    <thead>
        <tr>
            <th>
                Fecha
            </th>
            <th>
                Descripción
            </th>
            <th>
                Avance
            </th>
        </tr>
    </thead>
    <tbody>

        <g:each in="${avances}" var="a">
            <tr>
                <td style="text-align: center">
                    ${a.fecha.format("dd/MM/yyyy")}
                </td>
                <td>
                    ${a.descripcion}
                </td>
                <td style="text-align: right;">
                    <g:formatNumber number="${a.avance}" minFractionDigits="2" maxFractionDigits="2"/>%
                </td>
            </tr>
        </g:each>
        <tr>
            <td colspan="2" style="text-align: right;font-weight: bold">Avance total:</td>
            <g:if test="${avances.size() > 0}">
                <td style="text-align: right;font-weight: bold"><g:formatNumber number="${avances.pop().avance}" minFractionDigits="2" maxFractionDigits="2"/>%</td>
            </g:if>
            <g:else>
                <td style="text-align: right;font-weight: bold"><g:formatNumber number="0" minFractionDigits="2" maxFractionDigits="2"/>%</td>
            </g:else>

        </tr>
    </tbody>
</table>