<%@include file="/WEB-INF/views/includes/header.jsp" %><%@include file="/WEB-INF/views/includes/navi.jsp" %><script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyA2hWOkRXF_JR24XO5zmExutpFmYrAdcmk"></script><script>    var myCenter=new google.maps.LatLng(38.891529, -77.037737);    var map;    var marker = new Array;    var center = new Array;    function initialize() {        var mapProp = {            center:myCenter,            zoom:12,            mapTypeId:google.maps.MapTypeId.ROADMAP        };        map =new google.maps.Map(document.getElementById("googleMap"),mapProp);        <c:forEach items="${projList}" var="proj" varStatus="status">            <c:if test="${not empty proj.locationDetail.lat}">                center[${status.index}] = new google.maps.LatLng(${proj.locationDetail.lat}, ${proj.locationDetail.lng});                marker[${status.index}] = new google.maps.Marker({                    position:center[${status.index}],                    map:map                });                marker[${status.index}].addListener('click', function() {                    var contentString = '<div id="content">'+                            '<div id="siteNotice">'+                            '</div>'+                            '<h1 id="firstHeading" class="firstHeading">'+'${proj.name}'+'</h1>'+                            '<div id="bodyContent">'+                            '<p>'+'${proj.desc}'+'</p>'+                            '</div>'+                            '</div>';                    infowindow.setContent(contentString);                    infowindow.open(map, marker[${status.index}]);                });            </c:if>        </c:forEach>    }    google.maps.event.addDomListener(window, 'load', initialize);    var infowindow = new google.maps.InfoWindow({    });    $(document).ready(function(){        $('#project_select').change(function() {            //alert(this.value);            map.panTo(marker[this.value].position);            new google.maps.event.trigger( marker[this.value], 'click' );        });    });</script><div style="position: relative;top:40px;left:130px; z-index: 100;">    <label for="project_select" style="background-color: rgb(220,220,220)">Project:</label>    <select id="project_select">        <option></option>        <c:forEach items="${projList}" var="proj" varStatus="status">            <option value="${status.index}">${proj.name}</option>        </c:forEach>    </select></div><div style="width:100%; height:500px;" id="map-canvas">    <div id="googleMap" style="width: 100%; height: 100%"></div></div><%@include file="/WEB-INF/views/includes/footer.jsp" %>