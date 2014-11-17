<style type="text/css">
.revisiones {
    max-height : 300px;
    overflow   : auto;
}

.revision {
    padding    : 5px;
    margin-top : 10px;
}

div.warning {
    background : #e8e4d0;
    border     : solid 1px #efce2b;
    padding    : 5px;
}

span.warning, a.warning {
    color : #8e7711;
}

</style>

<div class="loading">
    <img src="${resource(dir: 'images', file: 'spinner_64.gif')}" alt="Por favor espere..."/>
</div>

<div class="loaded ui-helper-hidden">
    <p>
        Agendar reunión de aprobación con <span id="spSolicitudes">${solicitudes.size()} solicitud${solicitudes.size() == 1 ? '' : 'es'}</span>
        para la fecha y hora:
    </p>
    <g:textField name="fechaReunion" class="datepicker wide short ui-widget-content ui-corner-all"
                 value="${reunion?.fecha ? reunion.fecha.format('dd-MM-yyyy') : new Date().format('dd-MM-yyyy')}"/>
    <g:select from="${7..18}" name="horaReunion" class=" wide tiny ui-widget-content ui-corner-all"
              optionValue="${{ it.toString().padLeft(2, '0') }}"
              value="${reunion?.fecha ? reunion.fecha.format('HH') : new Date().format('HH')}"/>
    <g:select from="${0..11}" name="minutoReunion" class=" wide tiny ui-widget-content ui-corner-all"
              optionValue="${{ (it * 5).toString().padLeft(2, '0') }}"
              value="${reunion?.fecha ? reunion.fecha.format('mm').toInteger() / 5 : new Date().format('mm').toInteger() / 5}"/>


    <div class="revisiones">
        <g:each in="${solicitudes}" var="solicitud">
            <div class="ui-widget-content ui-corner-all revision">
                <g:if test="${solicitud.aprobacion && solicitud.aprobacionId != reunion.id}">
                    <div class="warning ui-corner-all">
                        <span class="fa fa-warning warning"></span>
                        Se moverá esta solicitud de la reunión de ${solicitud.aprobacion.fecha.format("dd-MM-yyyy HH:mm")} a la reunión actual
                        <a class="warning" href="#" id="${solicitud.id}" style="margin-left: 40px; color: #07A; font-weight: bold">No mover</a>
                    </div>
                </g:if>
                <p>
                    Proceso: <strong>${solicitud.nombreProceso}</strong>
                </p>

                <p style="font-size: 14px; font-weight: bold">Revisión de la Dirección de Planificación e Inversión</p>
                <g:textArea name="revision_${solicitud.id}" rows="5" cols="78"
                            class="ui-widget-content ui-corner-all txtRevision"
                            value="${solicitud.revisionDireccionPlanificacionInversion ?: ''}"/>
            </div>
        </g:each>
    </div>
</div>

<script type="text/javascript">

    setTimeout(function () {
        // Do something after 5 seconds
        $(".loading").hide();
        $(".loaded").show();
        $(".datepicker").datepicker({
            changeMonth : true,
            changeYear  : true,
            dateFormat  : 'dd-mm-yy',
            minDate     : "+0"
        });
        $("a.warning").click(function () {
            var id = $(this).attr("id");
            var $chk = $("#" + id + ".check");
            $chk.removeClass("checked");
            $chk.children("span").removeClass("fa-check-square").addClass("fa-square-o");
            var $parent = $(this).parents(".revision");
            $parent.hide("blind", 1000, function () {
                $parent.remove();
                var revisiones = $(".revision").length;
                var txt = revisiones + " solicitud" + (revisiones == 1 ? '' : 'es');
                $("#spSolicitudes").text(txt);
                if (revisiones == 0) {
                    validarSols();
                }
            });
            return false;
        });
    }, 1000);


</script>