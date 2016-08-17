<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>

<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyA2hWOkRXF_JR24XO5zmExutpFmYrAdcmk"></script>
<script>
    var myCenter=new google.maps.LatLng(38.990960, -77.506168);
    var map;
    function initialize() {
        var mapProp = {
            center:myCenter,
            zoom:15,
            mapTypeId:google.maps.MapTypeId.ROADMAP
        };
        map =new google.maps.Map(document.getElementById("googleMap"),mapProp);
        var marker=new google.maps.Marker({
            position:myCenter
        });

        marker.setMap(map);
    }
    google.maps.event.addDomListener(window, 'load', initialize);

function changeLocation(lat, lng){
    var newCenter=new google.maps.LatLng(lat, lng);
    map.panTo(newCenter);
    var marker=new google.maps.Marker({
        position:newCenter
    });

    marker.setMap(map);
}
</script>
<button type="button" onclick="changeLocation(37.990960, -77.506168);">Project 1</button>
<button type="button" onclick="changeLocation(36.990960, -77.506168);">Project 2</button>
<button type="button" onclick="changeLocation(35.990960, -77.506168);">Project 3</button>
<div id="googleMap" style="width:100%;height:480px;"></div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>
