var map;

function initMap(){
    map = new google.maps.Map(document.getElementById('map'), {
        center : {lat : 42.45, lng : 59.617},
        zoom : 3
    });

    map.data.setStyle(function(feature){
        let weatherData = {
            station : feature.getProperty('station'),
            name : feature.getProperty('name'),
            country : feature.getProperty('country'),
            elevation : feature.getProperty('elevation'),
            datetime : new Date(feature.getProperty('datetime')),
            temperature : feature.getProperty('temperature'),
            dewPoint : feature.getProperty('dewPoint'),
            atmosphericPressureStationLevel : feature.getProperty('atmosphericPressureStationLevel'),
            atmosphericPressureSeaLevel : feature.getProperty('atmosphericPressureSeaLevel'),
            visibility : feature.getProperty('visibility'),
            windSpeed : feature.getProperty('windSpeed'),
            precipitate : feature.getProperty('precipitate'),
            snowfall : feature.getProperty('snowfall'),
            frshtt : (function(n){
                let obj = {
                    freeze : (n & (1 << 5)) != 0,
                    rain : (n & (1 << 4)) != 0,
                    snow : (n & (1 << 3)) != 0,
                    hail : (n & (1 << 2)) != 0,
                    storm : (n & (1 << 1)) != 0,
                    tornado : (n & (1 << 0)) != 0
                };

                return obj;
            })(feature.getProperty('frshtt')),
            cloudCoverage : feature.getProperty('cloudCoverage'),
            windDirection : feature.getProperty('windDirection')
        };
        
        return {
            icon : {
                url : getWeatherIcon(weatherData.frshtt),
                scaledSize : new google.maps.Size(50, 50)
            }
        };
    });

    let f = setInterval(seedMap, 1000);
}

function getWeatherIcon(frshtt){
    const icons = {
        freeze : "/resources/images/icons/wi-snowflake-cold.svg",
        rain : "/resources/images/icons/wi-rain-wind.svg",
        snow : "/resources/images/icons/wi-snow.svg",
        hail : "/resources/images/icon/wi-hail.svg",
        storm : "/resources/images/icon/wi-thunderstorm.svg",
        tornado : "/resources/images/icon/wi-tornado.svg"
    };

    let icon = "";

    for(let key in frshtt){
        if(!icons.hasOwnProperty(key)) continue;

        if(frshtt[key]){
            icon = icons[key]; 
        }
    }

    return icon;
}

function seedMap(){
    map.data.loadGeoJson("weatherdata.php");
}