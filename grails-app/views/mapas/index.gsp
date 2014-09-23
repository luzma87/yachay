<%@ page import="yachay.proyectos.Meta" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.min.js')}"></script>
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.css')}"/>
        <title>Mapa</title>

        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBpasnhIQUsHfgCvC3qeJpEgcB9_ppWQI0&sensor=true"></script>

        <style type="text/css">
        #filtros {
            float  : left;
            width  : 340px;
            height : 615px;
            margin : 5px 10px 5px 0;
        }

        #mapa {
            float  : left;
            width  : 675px;
            height : 615px;
            margin : 5px 0;
        }

        .ui-accordion-content {
            max-height : 433px;
            padding    : 10px !important;
        }

        .selects {
            padding    : 0;
            margin     : 0;
            list-style : none;
        }

        .disabled {
            color : #666;
        }

        .enabled {
            font-weight : bold;
        }
        </style>

    </head>

    <body>
        <div class="body">
            <h1>Mapa de proyectos y metas</h1>

            <div class="dialog">

                <div id="filtros" class="ui-helper-hidden">

                    <h3><a href="#" tipo="resumen">Informaci&oacute;n nacional</a></h3>

                    <div>
                        <ul class="selects">
                            <li>
                                <g:checkBox class="chk filtro" name="resumen_" tipo="resumen"/>
                                <span class="texto">
                                    Proyectos por provincia (${yachay.parametros.geografia.Provincia.count()})
                                </span>
                                <span class="spanPin"></span>
                            </li>
                        </ul>
                    </div>

                    <h3><a href="#" tipo="tipoMeta">Tipo de metas</a></h3>

                    <div>
                        <ul class="selects">
                            <g:each in="${yachay.parametros.proyectos.TipoMeta.list([sort: 'descripcion'])}" var="tipoMeta">
                                <li>
                                    <g:checkBox class="chk filtro" name="tipoMeta_${tipoMeta.id}" tipo="tipoMeta" disabled="${tipoMeta.metas.metasCoords.size() == 0}"/>
                                    <span class="texto ${tipoMeta.metas.metasCoords.size() == 0 ? 'disabled' : 'enabled'}">
                                        ${tipoMeta.descripcion} (${tipoMeta.metas.metasCoords.size()}/${tipoMeta.metas.metasTotal.size()})
                                    </span>
                                    <span class="spanPin"></span>
                                </li>
                            </g:each>
                        </ul>
                    </div>

                    <h3><a href="#" tipo="proyecto">Proyectos</a></h3>

                    <div>
                        <ul class="selects">
                            <g:each in="${yachay.proyectos.Proyecto.list([sort: 'nombre'])}" var="proyecto">
                                <li>
                                    <g:checkBox class="chk filtro" name="proyecto_${proyecto.id}" tipo="proyecto" disabled="${proyecto.metas.metasCoords.size() == 0}"/>
                                    <span class="texto ${proyecto.metas.metasCoords.size() == 0 ? 'disabled' : 'enabled'}">
                                        ${proyecto.nombre} (${proyecto.metas.metasCoords.size()}/${proyecto.metas.metasTotal.size()})
                                    </span>
                                    <span class="spanPin"></span>
                                </li>
                            </g:each>
                        </ul>
                    </div>

                    <h3><a href="#" tipo="provincia">Provincia</a></h3>

                    <div>
                        <ul class="selects">
                            <g:each in="${yachay.parametros.geografia.Provincia.list([sort: 'nombre'])}" var="prov">
                                <li>
                                    <g:checkBox class="chk filtro" name="provincia_${prov.id}" tipo="provincia" disabled="${prov.metas.metasCoords.size() == 0}"/>
                                    <span class="texto ${prov.metas.metasCoords.size() == 0 ? 'disabled' : 'enabled'}">
                                        ${prov.nombreMostrar ?: prov.nombre} (${prov.metas.metasCoords.size()}/${prov.metas.metasTotal.size()})
                                    </span>
                                    <span class="spanPin"></span>
                                </li>
                            </g:each>
                        </ul>
                    </div>

                </div>

                <div id="mapa">

                </div>

            </div>
        </div>


        <div id="dlgLoad" style="text-align:center; display: none;">
            Cargando...Por favor espere...<br/>
            <img src="${resource(dir: 'images', file: 'spinner_64.gif')}" alt="Cargando..."/><br/>
            En caso que el mapa tarde demasiado tiempo en cargarse, haga click aqu&iacute;<br/>
            <a href="#" id="btnReload">Recargar la p&aacute;gina</a>
        </div>

        <script type="text/javascript">

            var marks = 0;
            var markers = {tipo : 'resumen'};

            var map;
            var countryCenter = new google.maps.LatLng(-1.8, -78.36);

            //            var allowedBounds = new google.maps.LatLngBounds(
            //                    new google.maps.LatLng(-1.8 - 0.5, -78.36 - 0.5),
            //                    new google.maps.LatLng(-1.8 + 0.5, -78.36 + 0.5)
            //            );

            var infowindow = new google.maps.InfoWindow({
                content : ""
            });

            google.maps.event.addListener(infowindow, 'closeclick', function () {
//                    map.setOptions({center : countryCenter });
                map.panTo(countryCenter);
            });

            var allowedBounds = new google.maps.LatLngBounds(
//                        new google.maps.LatLng(-4.990993680834003, -92.83448730468751),
//                        new google.maps.LatLng(1.3087208827117167, -74.93775878906251)
                    new google.maps.LatLng(-4.990993680834003, -92.83448730468751),
                    new google.maps.LatLng(7.3087208827117167, -74.93775878906251)
            );

            var lastValidCenter = countryCenter;

            var pins = [
                '${resource(dir: "images/pins/flags", file: "flag_black.png")}',
                '${resource(dir: "images/pins/flags", file: "flag_blue.png")}',
                '${resource(dir: "images/pins/flags", file: "flag_green.png")}',
                '${resource(dir: "images/pins/flags", file: "flag_red.png")}',
                '${resource(dir: "images/pins/flags", file: "flag_yellow.png")}'
            ];

            var pins2 = pins;

            function createMarks(json) {
                var array = [];
                for (var i in json) {
                    var item = json[i];
                    var latLng = new google.maps.LatLng(item.latitud, item.longitud);

                    var image = new google.maps.MarkerImage(item.image,
                            // This marker is 32 pixels wide by 32 pixels tall.
                            new google.maps.Size(32, 32),
                            // The origin for this image is 0,0.
                            new google.maps.Point(0,0),
                            // The anchor for this image is the base of the flagpole at 5,31.
                            new google.maps.Point(4, 31));


                    var newMarker = new google.maps.Marker({
                        position : latLng,
                        map      : map,
                        icon     : image,
                        id       : item.id,
                        title    : item.title
                    });
                    google.maps.event.addListener(newMarker, 'click', function () {
                        var id = this.id;
                        var tipo = this.tipo;
                        infowindow.close();
                        infowindow.setContent(loadInfo(item.url, id, tipo));
                        infowindow.open(map, this);
                    });
                    array.push(newMarker);
                }
                return array;
            }

            function hideMarks(marks) {
                infowindow.close();
                map.panTo(countryCenter);
                if (marks) {
                    for (var i in marks) {
                        marks[i].setMap(null);
                    }
                }
            }

            function loadInfo(url, id, tipo) {
                var checked = $(".filtro:checked");
                var filtro = "id=" + id + "&";
                var b = false;

                checked.each(function () {
                    filtro += "filtro=" + $(this).attr("id") + "&";
                    b = true;
                });
                var ms = "";
                $.ajax({
                    type    : "POST",
                    async   : false,
                    url     : url,
                    data    : filtro,
                    success : function (msg) {
                        ms = msg;
                    }
                });

                return ms;
            }

            function initialize() {
                var myOptions = {
                    center             : countryCenter, // bueno para el país
                    zoom               : 7,
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
                    },
                    {
                        featureType : "road",
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
                });

                google.maps.event.addListenerOnce(map, "tilesloaded", function () {
                    $("#dlgLoad").dialog("close");
                    $("#filtros").show();
                    $(".chk").attr("checked", false);
                });

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
//                $(".chk").attr("checked", false);
//                $("#filtros").show();

                initialize();

                $("#filtros").accordion({
                    collapsible : true,
                    autoHeight  : false
                });

                $("#btnReload").button({
                    icons : {
                        primary : "ui-icon-arrowreturnthick-1-s"
                    }
                }).click(function () {
                            location.reload(true);
                        });

                $(".ui-accordion-header").click(function () {
//                    $(".chk").removeAttr("checked");

                    $(".chk:checked").each(function () {
                        var checkbox = $(this);
                        pins2.push(checkbox.data("pin"));
                        checkbox.removeAttr("checked");
                        checkbox.removeData("pin");
                        //TODO Aqui borrar el pin
                        checkbox.siblings(".spanPin").html("");
                    });

                    $.each(markers, function (i, val) {
                        if ($.isArray(val)) {
                            hideMarks(val);
                        }
                    });

                    markers = {};
                    markers.tipo = $(this).attr("tipo");
                    marks = 0;
                });

                $(".chk").click(function () {
                    %{--var markersProvincias = createMarks(${jsonProv}, images.provincia, urls.provincia, "prov");--}%
                    infowindow.close();
                    map.panTo(countryCenter);
                    var checkbox = $(this);

                    var band = "";
                    var tipo = checkbox.attr("id");

                    if (marks < 5) {
                        if (checkbox.is(":checked")) {
                            checkbox.attr("checked", true);
                            band = "show";
                        } else {
                            band = "hide";
                        }
                    } else {
                        if (!checkbox.is(":checked")) {
                            band = "hide";
                        } else {
                            checkbox.removeAttr("checked");
                        }
                    }

                    if (band == "show") {
                        var col = pins2.pop();
                        $.ajax({
                            type     : "POST",
                            url      : "${createLink(action:'getPins')}",
                            data     : {
                                tipo  : tipo,
                                image : col
                            },
                            dataType : "json",
                            success  : function (msg) {
                                markers[tipo] = createMarks(msg);
                                marks++;
                                checkbox.data("pin", col);
                                //TODO: aqui mostrar el pin
                                checkbox.siblings(".spanPin").html("<img src='" + col + "' width='20' alt=''/>");
                            },
                            error    : function () {
                                pins2.push(col);
                            }
                        });
                    } else if (band == "hide") {
                        marks--;
                        hideMarks(markers[tipo]);
                        delete markers[tipo];
                        pins2.push(checkbox.data("pin"));
                        checkbox.removeData("pin");
                        //TODO: aqui borrar el pin
                        checkbox.siblings(".spanPin").html("");
                    }
                });

                $("#dlgLoad").dialog({
                    autoOpen      : true,
                    modal         : true,
                    closeOnEscape : false,
                    draggable     : false,
                    resizable     : false,
                    open          : function (event, ui) {
                        $(event.target).parent().find(".ui-dialog-titlebar-close").remove();
                    }
                });
            });
        </script>

    </body>
</html>