let map;
let selectedStation = null;

const MAP_UPDATE_INTERVAL = 1000;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 42.45, lng: 59.617 },
        zoom: 3
    });

    map.data.setStyle(function (feature) {
        let weatherData = getWeatherData(feature);

        return {
            icon: {
                url: getWeatherIcon(weatherData.frshtt),
                scaledSize: new google.maps.Size(50, 50)
            }
        };
    });

    let infoWindow = new google.maps.InfoWindow();

    map.data.addListener("click", function (event) {
        let weatherData = getWeatherData(event.feature);

        selectedStation = weatherData.station;

        let content =
            `<div id='infoWindow'>
                <div><h4>${weatherData.name}, ${weatherData.country}</h4></div>
                <div>Temperature: <span id='temperature'>${weatherData.temperature}</span> °C</div>
            </div>`;


        infoWindow.setContent(content);
        infoWindow.setPosition(event.feature.getGeometry().get());
        infoWindow.setOptions({ pixelOffset: new google.maps.Size(0, -30) });
        infoWindow.open(map);
    });

    let f = setInterval(updateMap, MAP_UPDATE_INTERVAL);
}

function getWeatherData(feature) {
    let weatherData = {
        station: feature.getProperty('station'),
        name: feature.getProperty('name'),
        country: feature.getProperty('country'),
        elevation: feature.getProperty('elevation'),
        datetime: new Date(feature.getProperty('datetime')),
        temperature: feature.getProperty('temperature'),
        dewPoint: feature.getProperty('dewPoint'),
        atmosphericPressureStationLevel: feature.getProperty('atmosphericPressureStationLevel'),
        atmosphericPressureSeaLevel: feature.getProperty('atmosphericPressureSeaLevel'),
        visibility: feature.getProperty('visibility'),
        windSpeed: feature.getProperty('windSpeed'),
        precipitate: feature.getProperty('precipitate'),
        snowfall: feature.getProperty('snowfall'),
        frshtt: (function (n) {
            let obj = {
                freeze: (n & (1 << 5)) != 0,
                rain: (n & (1 << 4)) != 0,
                snow: (n & (1 << 3)) != 0,
                hail: (n & (1 << 2)) != 0,
                storm: (n & (1 << 1)) != 0,
                tornado: (n & (1 << 0)) != 0
            };

            return obj;
        })(feature.getProperty('frshtt')),
        cloudCoverage: feature.getProperty('cloudCoverage'),
        windDirection: feature.getProperty('windDirection')
    };

    return weatherData;
}

function getWeatherIcon(frshtt) {
    let icons = {
        freeze: "/resources/images/icons/wi-snowflake-cold.svg",
        rain: "/resources/images/icons/wi-rain-wind.svg",
        snow: "/resources/images/icons/wi-snow.svg",
        hail: "/resources/images/icon/wi-hail.svg",
        storm: "/resources/images/icon/wi-thunderstorm.svg",
        tornado: "/resources/images/icon/wi-tornado.svg"
    };

    let icon = "";

    for (let key in frshtt) {
        if (!icons.hasOwnProperty(key)) continue;

        if (frshtt[key]) {
            icon = icons[key];
        }
    }

    return icon;
}

function updateMap() {
    map.data.loadGeoJson("weatherdata.php");
}