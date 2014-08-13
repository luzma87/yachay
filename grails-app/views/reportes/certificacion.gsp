<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 9/16/11
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Aval</title>

    <style type="text/css">
    @page {
        size   : 21cm 29.7cm;  /*width height */
        margin : 2cm;
    }

    .hoja {
        width : 15cm;
    }

    .titulo {
        width : 15.5cm;
    }

    .hoja {
        /*background  : #e6e6fa;*/
        height      : 24.7cm; /*29.7-(1.5*2)*/
        font-family : arial;
        font-size   : 12pt;
    }

    .titulo {
        height        : 40px;
        font-size     : 12pt;
        /*font-weight   : bold;*/
        text-align    : center;
        margin-bottom : 5px;
        width: 100%;
        border-bottom: 1px solid black;
    }

    th {
        background : #cccccc;
    }

    tbody tr:nth-child(2n+1) {
        background : none repeat scroll 0 0 #E1F1F7;
    }

    tbody tr:nth-child(2n) {
        background : none repeat scroll 0 0 #F5F5F5;
    }
    tr{
        margin: 0px;
    }
    td{
        border: 1px solid #000000;
        margin: 0px;
        padding: 5px;
    }
    table {
        border-collapse: collapse;
        border-spacing: 0px 0px;
    }
    </style>

</head>

<body>
<div class="hoja">

    <div class="titulo" style="">
        <p><b>AVAL DE POA</b></p>

    </div>
    <div style="float: right;font-size: 12pt;">
        <p>Numeración: ${anio}-GP No. ${cer.acuerdo}</p>
    </div>

    <div style="text-align: justify;float: left;font-size: 12pt;">
        <p>
            Con solicitud de aval de POA Nro. ${cer.memorandoSolicitud}, con fecha ${cer.fecha.format("dd-MM-yyyy")}, la Gerencia de Planificación solicita un aval para realizar la actividad "${cer.asignacion.marcoLogico}",
            por un monto total de ${cer.monto}$, con base en cual informo lo siguiente:
        </p>
        <p>
            Luego de revisar el Plan Operativo Anual ${anio}, la Gerencia de Planificación emite el aval a la actividad conforme el siguiente detalle:
        </p>
        <table style="width: 100%"  >
            <tbody>
            <tr>
                <td></td>
                <td style="font-weight: bold;text-align: center">DETALLE</td>
            </tr>
            <tr>
                <td style="font-weight: bold">Unidad responsable</td>
                <td>${cer.asignacion.unidad}</td>
            </tr>
            <tr>
                <td style="font-weight: bold">Proyecto</td>
                <td>${cer.asignacion.marcoLogico.proyecto}</td>
            </tr>
            <tr>
                <td style="font-weight: bold">Componente</td>
                <td>${cer.asignacion.marcoLogico.marcoLogico}</td>
            </tr>
            <tr>
                <td style="font-weight: bold">Actividad</td>
                <td>${cer.asignacion.marcoLogico}</td>
            </tr>
            <tr>
                <td style="font-weight: bold">Monto solicitado</td>
                <td>${cer.monto}</td>
            </tr>
            <g:if test="${anterior}">
                <tr>
                    <td style="font-weight: bold">Valor ${anio.toInteger()-1}</td>
                    <td>${anterior.monto} (Fuente: 998)</td>
                </tr>
            </g:if>
            <tr>
                <td style="font-weight: bold">Total</td>
                <td>${cer.monto+((anterior)?anterior?.monto:0)}</td>
            </tr>
            </tbody>
        </table>
        <p style="border: 1px solid black;padding: 5px;font-size: 8px;text-align: left">
            <b style="text-decoration: underline">OBSERVACIONES:</b><br/>
            - El aval se otorga en base a los oficios No. SENPLADES-SZ1N-2012-0110-F, de 12 de semptiembre de 2012, con el cual se actualiza el dictamen de prioridad de proyecto
            "Ciudad del Conocimiento Yachay", CUP: 30400000.680.6990, para el periodo 2012-2017; No MINFIN-DM-2013-0016, de 11 de enero de 2013, con el cual el Ministerio de Finanzas certifica asignará
            plurianualmente los recursos por hasta 206 millones de dólares para la ejecución del proyecto  "Ciudad del Conocimiento Yachay"; y No. MINFIN-DM-2013-1018, de 27 de diciembre de 2013, con el cual el
            Ministerio de Finanzas asignará al proyecto "Ciudad del Conocimiento Yachay"en el año 2014 para gasto no permanente de USD 80 millones.<br/>
            - La gerencia de Planificación es responsable del establecimiento del presupuesto referencial y consistencia con los precios de mercado para el proceso de contratación detallado
        </p>
        <p>
            Es importante señalar que la Gerencia Administrativa Financiera en el marco de sus competencias verificará la disponibilidad presupuestaria.
        </p>
    </div>



    <div style="margin-top: 100px;float: left;font-size: 12pt;text-align: center">
        <p>Edison Torres</p>
        <p>
            <b>Director de Planificación</b>
        </p>
    </div>
    <div style="margin-top: 100px;float: left;font-size: 12pt;margin-left: 180px;text-align: center">
        <p>Rocio Gavilanes</p>
        <p>
            <b>Gerente de Planificación</b>
        </p>
    </div>


</div>

</body>
</html>