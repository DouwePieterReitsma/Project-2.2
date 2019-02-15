var map;

function initMap(){
    map = new google.maps.Map(document.getElementById('map'), {
        center : {lat : 42.45, lng : 59.617},
        zoom : 3
    });

    let f = setInterval(seedMap, 1000);
}

function seedMap(){
    map.data.loadGeoJson("weatherdata.php");
}