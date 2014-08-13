<table style="width: 100%">
    <tbody>
    <tr>
        <td style="font-weight: bold">Proyecto</td>
        <td>${act.proyecto}</td>
    </tr>
    <tr>
        <td style="font-weight: bold">Componente</td>
        <td>${act.marcoLogico.objeto}</td>
    </tr>
    <tr>
        <td style="font-weight: bold">Actividad</td>
        <td>${act.objeto}</td>
    </tr>
    <tr>
        <td style="font-weight: bold">Responsable</td>
        <td>${act.responsable}</td>
    </tr>
    <tr>
        <td style="font-weight: bold">Monto</td>
        <td>${act.monto}$</td>
    </tr>
    <tr>
        <td style="font-weight: bold">Aporte</td>
        <td>${act.aporte}%</td>
    </tr>
    <tr>
        <td style="font-weight: bold">Inicio</td>
        <td>${act.fechaInicio?.format("dd-MM-yyyy")}</td>
    </tr>
    <tr>
        <td style="font-weight: bold">Fin</td>
        <td>${act.fechaFin?.format("dd-MM-yyyy")}</td>
    </tr>
    <tr>
        <td style="font-weight: bold">Categoría</td>
        <td>${(act.categoria)?act.categoria?.descripcion:"Ninguna"}</td>
    </tr>
    <tr>
        <td style="font-weight: bold">Partida</td>
        <td>${asg.presupuesto.numero} - ${asg.presupuesto.descripcion}</td>
    </tr>

    </tbody>
</table>