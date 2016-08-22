<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>

<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyA2hWOkRXF_JR24XO5zmExutpFmYrAdcmk"></script>
<script>
    var myCenter=new google.maps.LatLng(38.990960, -77.506168);
    var map;
    function initialize() {
        var mapProp = {
            center:myCenter,
            zoom:8,
            mapTypeId:google.maps.MapTypeId.ROADMAP
        };
        map =new google.maps.Map(document.getElementById("googleMap"),mapProp);

    }
    google.maps.event.addDomListener(window, 'load', initialize);

    var infowindow = new google.maps.InfoWindow({
//        content: contentString
    });

function changeLocation(lat, lng, proj_name, desc){
    var newCenter=new google.maps.LatLng(lat, lng);
    map.panTo(newCenter);
    var marker=new google.maps.Marker({
        position:newCenter
    });
    marker.addListener('click', function() {
        var contentString = '<div id="content">'+
                '<div id="siteNotice">'+
                '</div>'+
                '<h1 id="firstHeading" class="firstHeading">'+proj_name+'</h1>'+
                '<div id="bodyContent">'+
                '<p>'+desc+'</p>'+
                '</div>'+
                '</div>';
        infowindow.setContent(contentString);
        infowindow.open(map, marker);
    });
    marker.setMap(map);
}



</script>
<button type="button" onclick="changeLocation(37.990960, -77.506168,'A Big Project', 'This project is big so it is long time ago');">Project 1</button>
<button type="button" onclick="changeLocation(36.990960, -77.506168,'B Big Project', 'sdf sdf asdf asdf asdf asdf asdf asdf asdf asdf sd');">Project 2</button>
<button type="button" onclick="changeLocation(35.990960, -77.506168,'C Big Project', 'This project is big so it is long time ago');">Project 3</button>
<div id="googleMap" style="width:100%;height:100%;"></div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>
