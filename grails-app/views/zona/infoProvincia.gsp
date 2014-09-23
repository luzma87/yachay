<%@ page import="yachay.parametros.geografia.Provincia" %>
<table width="100%">
    <tbody>

        <tr>
            <td class="label">
                <g:message code="provincia.zona.label"
                           default="Zona"/>
            </td>
            <td class="campo">
                <g:link class="linkArbol" tipo="zona_${provinciaInstance.zona.id}" controller="zona" action="show"
                        id="${provinciaInstance?.zona?.id}">
                    ${provinciaInstance?.zona?.encodeAsHTML()}
                </g:link>
            </td> <!-- campo -->

            <td class="label">
                <g:message code="provincia.region.label"
                           default="Región"/>
            </td>
            <td class="campo">
                ${provinciaInstance?.region?.encodeAsHTML()}
            </td> <!-- campo -->
        </tr>

        <tr>
            <td class="label">
                <g:message code="provincia.numero.label"
                           default="Número"/>
            </td>
            <td class="campo">
                ${fieldValue(bean: provinciaInstance, field: "numero")}
            </td> <!-- campo -->

            <td class="label">
                <g:message code="provincia.nombre.label"
                           default="Nombre"/>
            </td>
            <td class="campo">
                ${fieldValue(bean: provinciaInstance, field: "nombre")}
            </td> <!-- campo -->
        </tr>

        <tr>
            <td class="label">
                <g:message code="provincia.latitud.label"
                           default="Latitud"/>
            </td>
            <td class="campo" id="tdLat">
                ${provinciaInstance.latitud}
            </td> <!-- campo -->

            <td class="label">
                <g:message code="provincia.longitud.label"
                           default="Longitud"/>
            </td>
            <td class="campo" id="tdLng">
                ${provinciaInstance.longitud}
            </td> <!-- campo -->
        </tr>

        <tr>
            <td class="label">
                <g:message code="provincia.zoom.label"
                           default="Zoom"/>
            </td>
            <td class="campo" id="tdZoom">
                ${provinciaInstance.zoom}
            </td> <!-- campo -->

            <td colspan="2" id="tdModif" class="ui-helper-hidden">
                <g:checkBox name="modif"/>
                Modificar coordenadas
                <a href="#" id="btnSave" style="margin-left: 25px;" class="ui-helper-hidden" data-lat="${provinciaInstance.latitud}" data-lng="${provinciaInstance.longitud} data-zoom="${provinciaInstance.zoom}">Guardar</a>
            </td>
        </tr>

    </tbody>
</table>

<div id="mapa" style="margin-top: 15px; width: 595px; height: 420px;">

</div>

<script type="text/javascript">

    var map, marker;
    var countryCenter = new google.maps.LatLng(${provinciaInstance.latitud}, ${provinciaInstance.longitud});
    var origLat = ${provinciaInstance.latitud};
    var origLng = ${provinciaInstance.longitud};
    var origZoom = ${provinciaInstance.zoom};

    var modifZoom = false;
    var zoom = origZoom;

    var allowedBounds = new google.maps.LatLngBounds(
//                        new google.maps.LatLng(-4.990993680834003, -92.83448730468751),
//                        new google.maps.LatLng(1.3087208827117167, -74.93775878906251)
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
            center             : countryCenter, // bueno para el país
            zoom               : ${provinciaInstance.zoom},
//                maxZoom            : 9, //10 para cantones, 13 para parroquias, 7 para pais
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
            }/*,
             {
             featureType : "road",
             stylers     : [
             { visibility : "off" }
             ]
             }*/
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

            var latLng = new google.maps.LatLng(${provinciaInstance.latitud}, ${provinciaInstance.longitud});

            var image = new google.maps.MarkerImage('${resource(dir:"images/pins/flags", file:"flag_orange.png")}',
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
                id        : ${provinciaInstance.id},
                title     : '${provinciaInstance.nombreMostrar}'
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

//            google.maps.event.addListener(marker, "dragstop", function () {
//                var latlng = marker.getPosition();
//                var lat = latlng.lat();
//                var long = latlng.lng();
//                updateLatLng(lat, long);
//            });

        }); //tiles loaded

        /*
         TODO: borrar esta parte
         */

//                var infoWindow = new google.maps.InfoWindow();
//                google.maps.event.addListener(map, 'click', function (event) {
//                    var html = 'The LatLng value is: ' + event.latLng + ' at zoom level ' + map.getZoom();
//                    infoWindow.setContent(html);
//                    infoWindow.setPosition(event.latLng);
//                    infoWindow.open(map);
//                });
    } //init map
    $(function () {

        initialize();

        $("#modif").click(function () {
            if ($(this).is(":checked")) {
//                console.log("activo");
                modifZoom = true;
                $("#tdZoom").html(zoom);
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
                        url     : "${createLink(action:'updateProvinciaCoords')}",
                        data    : {
                            id   : ${provinciaInstance.id},
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
                                origZoom = zoom;
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