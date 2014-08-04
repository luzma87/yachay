<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 9/28/11
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>
            Mapa de la provincia
        </title>

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.min.js')}"></script>
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.css')}"/>

        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}"
              type="text/css"/>
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}"
              type="text/css"/>

        <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript"
                language="JavaScript"></script>
        <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}"
                type="text/javascript" language="JavaScript"></script>


        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBpasnhIQUsHfgCvC3qeJpEgcB9_ppWQI0&sensor=true"></script>

        <style type="text/css">
        .pin {
            cursor   : pointer;
            z-index  : 50;
            position : absolute;
        }

        .remove {
            background : #deb887;
            border     : solid 2px #8b4513;
            padding    : 6px;
            width      : 24px;
            height     : 40px;
        }

        .area {
            width    : 755px;
            position : relative;
        }

        .provincia {
            /*z-index : 1;*/
        }
        </style>

    </head>

    <body>

        <div class="breadCrumbHolder module">
            <div id="breadCrumb" class="breadCrumb module">
                <ul>
                    <li>
                        <g:link class="bc" controller="proyecto" action="show"
                                id="${componente.proyecto.id}">
                            Proyecto
                        </g:link>
                    </li>
                    <li>
                        <g:link class="bc" controller="marcoLogico" action="showMarco"
                                id="${componente.proyecto.id}">
                            Marco L&oacute;gico
                        </g:link>
                    </li>
                    <li>
                        <g:link class="bc" controller="marcoLogico" action="componentes"
                                id="${componente.proyecto.id}">
                            Componentes
                        </g:link>
                    </li>
                    <li>
                        <g:link class="bc" action="ingresoMetas" id="${meta.marcoLogico.id}">
                            Metas
                        </g:link>
                    </li>
                    <li>
                        Mapa
                    </li>
                </ul>
            </div>
        </div>

        <div id="" class="toolbar ui-widget-header ui-corner-all" style="margin-bottom: 5px;">
            <g:link class="button save">
                Guardar
            </g:link>

            <a href="#" id="btnReset">Reiniciar mapa</a>
        </div> <!-- toolbar -->


        <div style="margin-bottom: 15px; margin-top: 15px;">
            Posicione el ícono en el lugar que desee ubicar la meta <strong>${meta.descripcion}</strong> (${meta.parroquia} - ${meta.parroquia?.canton})
            <g:form action="guardarMapaMeta" name="frmMapaMeta">
                <input type="hidden" id="lat" name="lat" value="${geo.lat}"/>
                <input type="hidden" id="long" name="long" value="${geo.lng}"/>
                <input type="hidden" id="zoom" name="zoom" value="${geo.zoom}"/>
            </g:form>
            Datos actuales:
            <b>Latitud:</b> <span id="spLat">${geo.lat}</span>
            <b>Longitud:</b> <span id="spLng">${geo.lng}</span>
            <b>Zoom:</b> <span id="spZoom">${geo.zoom}</span>
        </div>

        <div class="area" style="min-height: 550px;overflow: auto;float: left">
            <div class="remove left ui-corner-all" style="margin-right: 15px;">
                %{--<img src="${resource(dir: 'images', file: 'red_pin.png')}" class="pin" alt="Pin" data-text="${ttip}"--}%
                %{--data-title="${ttitle}"--}%
                %{--style="${(meta.cord_y > 0 && meta.cord_x > 0) ? 'top: ' + meta.cord_y + 'px; left: ' + meta.cord_x + 'px;' : ''}">--}%

                %{--<img class="pin" src="http://maps.gstatic.com/mapfiles/ms/icons/ltblue-dot.png" width="32" height="32" alt=""--}%
                %{--style="${(meta.latitud != 0 && meta.longitud != 0) ? '' : 'left: 3px; top: 12px;'} display:none;">--}%

                <img class="pin" src="${resource(dir: 'images/pins', file: 'blue-pin_32.png')}" alt=""
                     style="${(meta.latitud != 0 && meta.longitud != 0) ? '' : 'left: 10px; top: 12px;'} display:none;">

            </div>

            <div id="map_canvas" class="provincia left" style="width: 700px; height: 500px;">
                %{--<img src="${resource(dir: 'images/mapas/provincias', file: provincia.imagen)}"--}%
                %{--alt="${provincia.nombreMostrar}" width="680"/>--}%

            </div>
        </div>

        <div id="dlgLoad" style="text-align:center; display: none;">
            Cargando...Por favor espere...<br/>
            <img src="${resource(dir: 'images', file: 'spinner_64.gif')}" alt="Cargando..."/><br/>
        </div>

        <div id="dlgLoadMap" style="text-align:center; display: none;">
            Cargando...Por favor espere...<br/>
            <img src="${resource(dir: 'images', file: 'spinner_64.gif')}" alt="Cargando..."/><br/>
            En caso que el mapa tarde demasiado tiempo en cargarse, haga click aqu&iacute;<br/>
            <a href="#" id="btnReload">Recargar la p&aacute;gina</a>
        </div>

        <script type="text/javascript">
            var map;
            var pin = $(".pin");
            var center = new google.maps.LatLng(parseFloat("${geo.lat}"), parseFloat("${geo.lng}"));

            var allowedBounds = new google.maps.LatLngBounds(
                    new google.maps.LatLng(parseFloat("${geo.lat}") - parseFloat("${geo.diff}"), parseFloat("${geo.lng}") - parseFloat("${geo.diff}")),
                    new google.maps.LatLng(parseFloat("${geo.lat}") + parseFloat("${geo.diff}"), parseFloat("${geo.lng}") + parseFloat("${geo.diff}"))
            );

            var lastValidCenter = center;
            var lastValidPin;
            var marker;
            var dummy;

            function updateLatLng(lat, long) {
                $("#lat").val(lat);
                $("#long").val(long);
                $("#spLat").html(lat);
                $("#spLng").html(long);
            }

            function updateZoom(zoom) {
                $("#zoom").val(zoom);
                $("#spZoom").html(zoom);
            }

            function DummyOView() {
                // Bind this to the map to access MapCanvasProjection
                this.setMap(map);
                // MapCanvasProjection is only available after draw has been called.
                this.draw = function () {
                };
            }
            //
            DummyOView.prototype = new google.maps.OverlayView();

            function initialize() {
                var myOptions = {
                    center             : center, // bueno para el país
                    zoom               : parseInt("${geo.zoom}"),
//                maxZoom            : 9, //10 para cantones, 13 para parroquias, 7 para pais
                    minZoom            : parseInt("${geo.minZoom}"),
                    panControl         : false,
                    zoomControl        : true,
                    mapTypeControl     : false,
                    scaleControl       : false,
                    streetViewControl  : false,
                    overviewMapControl : false,
                    mapTypeId          : google.maps.MapTypeId.TERRAIN  //SATELLITE, ROADMAP, HYBRID, TERRAIN
                };
                map = new google.maps.Map(document.getElementById("map_canvas"),
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

                // Add a dummy overlay for later use.
                // Needed for API v3 to convert pixels to latlng.
                dummy = new DummyOView();

                google.maps.event.addListener(map, 'center_changed', function () {
                    if (allowedBounds.contains(map.getCenter())) {
                        lastValidCenter = map.getCenter();
                        return;
                    }
                    map.panTo(lastValidCenter);
                });
                google.maps.event.addListener(map, 'zoom_changed', function () {
                    updateZoom(map.getZoom());
                });

                google.maps.event.addListenerOnce(map, "tilesloaded", function () {
                    <g:if test="${meta.latitud == 0 && meta.longitud == 0}">
                    $(".pin").show();
                    </g:if>
                    <g:else>
                    var latlng = new google.maps.LatLng(${geo.lat}, ${geo.lng});
                    var src = pin.attr("src");
                    createDraggedMarker(latlng, src);
                    </g:else>
                    $("#dlgLoadMap").dialog("close");
                });

                /*
                 TODO: borrar esta parte
                 */

                /*var infoWindow = new google.maps.InfoWindow();
                 google.maps.event.addListener(map, 'click', function (event) {
                 var html = 'The LatLng value is: ' + event.latLng + ' at zoom level ' + map.getZoom();
                 infoWindow.setContent(html);
                 infoWindow.setPosition(event.latLng);
                 infoWindow.open(map);
                 });*/
            } //init map

            function createDraggedMarker(point, src) {

                var g = google.maps;

                marker = new g.Marker({
                    position  : point,
                    map       : map,
                    clickable : true,
                    draggable : true,
//                    animation : google.maps.Animation.DROP,
//                    raiseOnDrag : false,
                    icon      : src,
//                    shadow    : shadow,
                    zIndex    : 1000
                });

                g.event.addListener(marker, "click", function () {
//                    actual = marker;
//                    var lat = actual.getPosition().lat();
//                    var lng = actual.getPosition().lng();
//
//                    iw.setContent(lat.toFixed(6) + ", " + lng.toFixed(6));
//                    iw.open(map, this);
                });
                g.event.addListener(marker, "drag", function () {
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

//                g.event.addListener(marker, "dragstop", function () {
//                    var latlng = marker.getPosition();
//                    var lat = latlng.lat();
//                    var long = latlng.lng();
//                    updateLatLng(lat, long);
//                });
            } //create mark

            function getLatlng() {
                var gd = $(map.getDiv());
                var mLeft = gd.offset().left;
                var mTop = gd.offset().top;
                var mWidth = gd.width();
                var mHeight = gd.height();

                var oWidth = pin.width();
                var oHeight = pin.height();

                // The object's pixel position relative to the document
                var x = pin.offset().left + oWidth / 2;
                var y = pin.offset().top + oHeight / 2;

                // Check if the cursor is inside the map div
                if (x > mLeft && x < (mLeft + mWidth) && y > mTop && y < (mTop + mHeight)) {

                    // Difference between the x property of iconAnchor
                    // and the middle of the icon width
                    var anchorDiff = 1;

                    // Find the object's pixel position in the map container
                    var g = google.maps;
                    var pixelpoint = new g.Point(x - mLeft - anchorDiff, y - mTop + (oHeight / 2));

                    // Corresponding geo point on the map
                    var proj = dummy.getProjection();
                    var latlng = proj.fromContainerPixelToLatLng(pixelpoint);

                    var lat = latlng.lat();
                    var long = latlng.lng();
                    updateLatLng(lat, long);
                    return latlng;
                }
                return false;
            }

            $(function () {

                initialize();

                $("#dlgLoad").dialog({
                    autoOpen      : false,
                    modal         : true,
                    closeOnEscape : false,
                    draggable     : false,
                    resizable     : false,
                    open          : function (event, ui) {
                        $(event.target).parent().find(".ui-dialog-titlebar-close").remove();
                    }
                });
                $("#dlgLoadMap").dialog({
                    autoOpen      : true,
                    modal         : true,
                    closeOnEscape : false,
                    draggable     : false,
                    resizable     : false,
                    open          : function (event, ui) {
                        $(event.target).parent().find(".ui-dialog-titlebar-close").remove();
                    }
                });

                $("#breadCrumb").jBreadCrumb({
                    beginingElementsToLeaveOpen : 10
                });
                $(".button").button();
                $(".back").button("option", "icons", {primary : 'ui-icon-arrowreturnthick-1-w'});
                $(".save").button("option", "icons", {primary : 'ui-icon-disk'}).click(function () {
                    $("#dlgLoad").dialog("open");
                    var datos = $("#frmMapaMeta").serialize();
                    datos += "&id=${meta.id}&c=${componente.id}";
                    var url = $("#frmMapaMeta").attr("action");
                    url += "?" + datos
//                    console.log(url);
                    location.href = url;
                    return false
                });

                $("#btnReset").button({
                    icons : {
                        primary : "ui-icon-arrowrefresh-1-s"
                    }
                }).click(function () {
                            map.setOptions({
                                center : center,
                                zoom   : parseInt("${geo.zoom}")
                            });
                            var latlng = new google.maps.LatLng(${geo.lat}, ${geo.lng});
                            marker.setOptions({
                                position : latlng
                            });
                        });

                $("#btnReload").button({
                    icons : {
                        primary : "ui-icon-arrowreturnthick-1-s"
                    }
                }).click(function () {
                            location.reload(true);
                        });

                $(".pin").draggable({
//                    revert      : "invalid",
                    scroll      : false,
                    containment : ".area",
                    drag        : function (event, ui) {
                        getLatlng();
                    },
                    stop        : function (event, ui) {
                        var latlng = getLatlng();
                        // Create a corresponding marker on the map
                        var src = pin.attr("src");
                        createDraggedMarker(latlng, src);
                        lastValidPin = latlng;
                        pin.remove();
                    } //drag stop
                });
            });
        </script>

    </body>
</html>