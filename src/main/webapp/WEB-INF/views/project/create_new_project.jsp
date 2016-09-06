<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAvHPcYjqYci87EUq4Ua42TNoXJ15ENTx0&libraries=places&callback=initAutocomplete"
        async defer>
</script>

<script>
    // This example displays an address form, using the autocomplete feature
    // of the Google Places API to help users fill in the information.

    // This example requires the Places library. Include the libraries=places
    // parameter when you first load the API. For example:
    // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

    var placeSearch, autocomplete;
    var componentForm = {
        street_number: 'short_name',
        route: 'long_name',
        locality: 'long_name',
        administrative_area_level_1: 'short_name',
        country: 'long_name',
        postal_code: 'short_name'
    };

    function initAutocomplete() {
        // Create the autocomplete object, restricting the search to geographical
        // location types.
        autocomplete = new google.maps.places.Autocomplete(
                /** @type {!HTMLInputElement} */(document.getElementById('p_location')),
                {types: ['geocode']});

        // When the user selects an address from the dropdown, populate the address
        // fields in the form.
        autocomplete.addListener('place_changed', fillInAddress);
    }

    function fillInAddress() {
        // Get the place details from the autocomplete object.
        var place = autocomplete.getPlace();

        for (var component in componentForm) {
            document.getElementById(component).value = '';
            document.getElementById(component).disabled = false;
        }
//Longti lati
        document.getElementById("lat").value = place.geometry.location.lat();
        document.getElementById("lng").value = place.geometry.location.lng();
        // Get each component of the address from the place details
        // and fill the corresponding field on the form.
        for (var i = 0; i < place.address_components.length; i++) {
            var addressType = place.address_components[i].types[0];
            if (componentForm[addressType]) {
                var val = place.address_components[i][componentForm[addressType]];
                document.getElementById(addressType).value = val;
            }
        }
    }

    // Bias the autocomplete object to the user's geographical location,
    // as supplied by the browser's 'navigator.geolocation' object.
    function geolocate() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                var geolocation = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                var circle = new google.maps.Circle({
                    center: geolocation,
                    radius: position.coords.accuracy
                });
                autocomplete.setBounds(circle.getBounds());
            });
        }
    }
</script>
<div class="col-md-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4>Create New Project</h4>
        </div>
        <div class="panel-body">
            <form:form action="/project/create" method="POST" modelAttribute="projectVO">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <form:input path="locationDetail.address1" id="street_number" name="streetNumber" type="hidden"/>
                    <form:input path="locationDetail.address2" id="route" name="route" type="hidden"/>
                    <form:input path="locationDetail.city" id="locality" name="locality" type="hidden"/>
                    <form:input path="locationDetail.state" id="administrative_area_level_1" name="state" type="hidden"/>
                    <form:input path="locationDetail.zip" id="postal_code" name="zip" type="hidden"/>
                    <form:input path="locationDetail.country" id="country" name="country" type="hidden"/>
                    <form:input path="locationDetail.lat" id="lat" name="lat" type="hidden"/>
                    <form:input path="locationDetail.lng" id="lng" name="lng" type="hidden"/>
                <div class="row">
                    <div class="col-md-6">
                        <label for="p_name">Project Name:</label>
                        <form:input path="name" type="text" class="form-control" id="p_name" placeholder="Project Name"/>
                    </div>
                    <div class="col-md-6">
                        <label for="p_location">Project Location:</label>
                        <form:input path="locationDetail.customAddress" type="text" class="form-control" id="p_location" name="location" placeholder="Project Location"  onFocus="geolocate()"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label for="desc">Description:</label>
                        <form:textarea path="desc" class="form-control" id="desc" name="desc" placeholder="Description"/>
                    </div>
                    <div class="col-md-6">
                        <label for="type">Type:</label>
                        <select path="type" class="form-control" id="type" name="type">
                            <option value="1">type1</option>
                            <option value="2">type2</option>
                            <option value="3">type3</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <label for="start_date">Start Date:</label>
                        <form:input path="startDate" type="date" id="start_date" name="startDate" class="form-control"/>
                    </div>
                    <div class="col-md-2">
                        <label for="end_date">End Date:</label>
                        <form:input path="endDate" type="date" id="end_date" name="endDate" class="form-control"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12" style="margin-top: 20px">
                        <button type="submit" class="btn btn-primary">Add</button>
                        <a href="/dashboard"><button type="button" class="btn btn-default">Cancel </button></a>
                    </div>
                </div>
            </form:form>
        </div>

    </div>

</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>
