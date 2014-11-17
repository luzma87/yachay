<style type="text/css">
.tabla {
    border-collapse : collapse;
}

.scrollable {
    max-height : 385px;
    overflow   : auto;
}
</style>

<p>
    ${solicitudes.size()} solicitud${solicitudes.size() == 1 ? '' : 'es'} agendadas para la reunión del ${aprobacion.fecha.format("dd-MM-yyyy HH:mm")}
</p>

<div class="scrollable">
    <table class="tabla" border="1" cellpadding="5">
        <thead>
            <th>N.</th>
            <th>Unidad Ejecutora</th>
            <th>Monto solicitado</th>
            <th>Nombre del proceso</th>
        </thead>
        <tbody>
            <g:each in="${solicitudes}" var="sol" status="i">
                <tr>
                    <td>${i + 1}</td>
                    <td>
                        ${sol.unidadEjecutora.nombre}
                    </td>
                    <td>
                        <g:formatNumber number="${sol.montoSolicitado}" type="currency"/>
                        <g:each in="${anios}" var="a">
                            <g:set var="valor" value="${yachay.contratacion.DetalleMontoSolicitud.findByAnioAndSolicitud(a, sol)}"/>
                            <br/>${a.anio} :
                            <g:if test="${valor}">
                                <g:formatNumber number="${valor.monto}" type="currency"/>
                            </g:if>
                        </g:each>
                    </td>
                    <td>
                        ${sol.nombreProceso}
                    </td>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>