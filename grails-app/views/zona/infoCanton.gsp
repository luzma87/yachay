<%@ page import="yachay.parametros.geografia.Canton" %>
<table width="100%">
    <tbody>

        <tr>
            <td class="label">
                <g:message code="canton.provincia.label"
                           default="Provincia"/>
            </td>
            <td class="campo">
                <g:link class="linkArbol" tipo="canton_${cantonInstance.provincia.id}" controller="canton" action="show"
                        id="${cantonInstance?.provincia?.id}">
                    ${cantonInstance?.provincia?.encodeAsHTML()}
                </g:link>
            </td> <!-- campo -->
        </tr>

        <tr>
            <td class="label">
                <g:message code="canton.numero.label"
                           default="Número"/>
            </td>
            <td class="campo">
                ${fieldValue(bean: cantonInstance, field: "numero")}
            </td> <!-- campo -->

            <td class="label">
                <g:message code="canton.nombre.label"
                           default="Nombre"/>
            </td>
            <td class="campo">
                ${fieldValue(bean: cantonInstance, field: "nombre")}
            </td> <!-- campo -->
        </tr>

        <tr>
            <td class="label">
                <g:message code="canton.latitud.label"
                           default="Latitud"/>
            </td>
            <td class="campo" id="tdLat">
                ${cantonInstance.latitud}
            </td> <!-- campo -->

            <td class="label">
                <g:message code="canton.longitud.label"
                           default="Longitud"/>
            </td>
            <td class="campo" id="tdLng">
                ${cantonInstance.longitud}
            </td> <!-- campo -->
        </tr>

        <tr>

            <td class="label">
                <g:message code="canton.Zoom.label"
                           default="Zoom"/>
            </td>
            <td class="campo" id="tdZoom">
                ${cantonInstance.zoom}
            </td> <!-- campo -->
            <td colspan="2" id="tdModif" class="ui-helper-hidden">
                <g:checkBox name="modif"/>
                Modificar coordenadas
                <a href="#" id="btnSave" style="margin-left: 25px;" class="ui-helper-hidden" data-lat="${cantonInstance.latitud}" data-lng="${cantonInstance.longitud} data-zoom="${cantonInstance.zoom}">Guardar</a>
            </td>
        </tr>

    </tbody>
</table>


<div id="mapa" style="margin-top: 15px; width: 595px; height: 420px;">

</div>

<script type="text/javascript">

    var lat = ${cantonInstance.latitud};
    var lng = ${cantonInstance.longitud};
    var zoom = ${cantonInstance.zoom};

    if (lat == 0 && lng == 0) {
        lat = ${cantonInstance.provincia.latitud};
        lng = ${cantonInstance.provincia.longitud};
        zoom = ${cantonInstance.provincia.zoom};
    }

    var map, marker;
    var countryCenter = new google.maps.LatLng(lat, lng);
    var origLat = lat;
    var origLng = lng;
    var origZoom = zoom;
    var modifZoom = false;

    var allowedBounds = new google.maps.LatLngBounds(
            new google.maps.LatLng(-4.990993680834003, -92.83448730468751),
            new google.maps.LatLng(7.3087208827117167, -74.93775878906251)
    );

    var lastValidCenter = countryCenter;
    var lastValidPin = countryCenter;

    function updateLatLng(lat, long) {
        $("#tdLat").html(lat);
        $("#tdLng").html(long);
        $("#btnSave").data("lat", lat);
        $("#btnSave").data("lng", long);
    }

    function updateZoom(pZoom) {
        zoom = pZoom;
        if (modifZoom) {
            $("#tdZoom").html(zoom);
            $("#btnSave").data("zoom", zoom);
        }
    }

    function initialize() {
        var myOptions = {
            center             : countryCenter,
            zoom               : zoom,
            minZoom            : 7,
            panControl         : false,
            zoomControl        : true,
            mapTypeControl     : false,
            scaleControl       : false,
            streetViewControl  : false,
            overviewMapControl : false,
            mapTypeId          : google.maps.MapTypeId.TERRAIN  //SATELLITE, ROADMAP, HYBRID, TERRAIN
        };
        map = new google.maps.Map(document.getElementById("mapa"),
                myOptions);

        var styleNoPoi = [
            {
                featureType : "poi",
                stylers     : [
                    { visibility : "off" }
                ]
            }
        ];
        map.setOptions({styles : styleNoPoi});

        google.maps.event.addListener(map, 'center_changed', function () {
            if (allowedBounds.contains(map.getCenter())) {
                lastValidCenter = map.getCenter();
                return;
            }
            map.panTo(lastValidCenter);
        }); //center changed

        google.maps.event.addListener(map, 'zoom_changed', function () {
            updateZoom(map.getZoom());
        }); //zoom changed

        google.maps.event.addListenerOnce(map, "tilesloaded", function () {

            $("#tdModif").show();

            var latLng = new google.maps.LatLng(lat, lng);

            var image = new google.maps.MarkerImage('${resource(dir:"images/pins/flags", file:"flag_light_blue.png")}',
                    // This marker is 32 pixels wide by 32 pixels tall.
                    new google.maps.Size(32, 32),
                    // The origin for this image is 0,0.
                    new google.maps.Point(0, 0),
                    // The anchor for this image is the base of the flagpole at 5,31.
                    new google.maps.Point(4, 31));

            marker = new google.maps.Marker({
                position  : latLng,
                map       : map,
                draggable : false,
                animation : google.maps.Animation.DROP,
                icon      : image,
                id        : ${cantonInstance.id},
                title     : '${cantonInstance.nombre}'
            });

            google.maps.event.addListener(marker, "drag", function () {
                var latlng = marker.getPosition();
                if (allowedBounds.contains(latlng)) {
                    lastValidPin = latlng;
                    var lat = latlng.lat();
                    var long = latlng.lng();
                    updateLatLng(lat, long);
                    return;
                }
                marker.setOptions({
                    position : lastValidPin
                });
            });
        }); //tiles loaded

    } //init map
    $(function () {

        initialize();

        $("#modif").click(function () {
            if ($(this).is(":checked")) {
//                console.log("activo");
                modifZoom = true;
                $("#btnSave").show();
                marker.setOptions({
                    draggable : true,
                    animation : google.maps.Animation.DROP
                });
            } else {
//                console.log("desactivo");
                modifZoom = false;
                $("#btnSave").hide();
                var latLng = new google.maps.LatLng(origLat, origLng);
                marker.setOptions({
                    position  : latLng,
                    draggable : false,
                    animation : google.maps.Animation.DROP
                });
                map.setOptions({
                    center : countryCenter,
                    zoom   : origZoom
                });
            }
        });

        $("#btnSave").button({
            icons : {
                primary : 'ui-icon-disk'
            },
            text  : false
        }).hide().click(function () {
                    var lat = $(this).data("lat");
                    var lng = $(this).data("lng");
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'updateCantonCoords')}",
                        data    : {
                            id   : ${cantonInstance.id},
                            lat  : lat,
                            lng  : lng,
                            zoom : zoom
                        },
                        success : function (msg) {
                            if (msg == "OK") {
                                $("#tdLat, #tdLng, #tdZoom").css({
                                    color : "#59A53D"
                                }).show("pulsate", function () {
                                            $("#tdLat, #tdLng, #tdZoom").css({
                                                color : "#312E25"
                                            })
                                        });
                                origLat = lat;
                                origLng = lng;
                            } else {
                                var latLng = new google.maps.LatLng(origLat, origLng);
                                marker.setOptions({
                                    position : latLng
                                });
                                map.setOptions({
                                    center : countryCenter,
                                    zoom   : origZoom
                                });
                                $("#tdLat, #tdLng, #tdZoom").css({
                                    color : "#C62929"
                                }).show("pulsate", function () {
                                            $("#tdLat, #tdLng").css({
                                                color : "#312E25"
                                            })
                                        });
                            }
                        }
                    });
                });

    });
</script>