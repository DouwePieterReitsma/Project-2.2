import java.io.*;

public class WeatherStation {

    // The string that will be added to the .csv file.
    private StringBuilder newLine = new StringBuilder();
    // The writer for this station.
    private BufferedWriter writer;
    // The directory for this weather stations' .csv
    private File directory;
    private Boolean newFile;

    // The number of the weather station.
    private String stationNumber;
    // The date the measurement was taken. date format: YYYY-mm-dd
    private String date;
    // The time the measurement was taken. Times format: hh:mm:ss. Timezone: UTC
    private String time;
    // The temperature in degrees Celsius.
    private String temperature;
    // The dew point in degrees Celsius.
    private String dewPoint;
    // Air pressure, in millibar, at station level.
    private String stationAirPressure;
    // Air pressure, in millibar, at sea level.
    private String seaLevelAirPressure;
    // Visibility in kilometers.
    private String visibility;
    // Wind speed in kilometers per hour.
    private String windSpeed;
    // Precipitation in centimeters.
    private String precipitation;
    // Snowfall in centimeters.
    private String snowfall;
    /*  Events on this day. In binary representation.
        In order of significance, starting at most significant:
        Freezing, Rainfall, Snowfall, Hail, Thunder, Tornado
     */
    private String events;
    // Cloud coverage in percentages.
    private String cloudCover;
    // Direction of the wind in degrees.
    private String windDirection;

    // Variables to hold previous measurement data for fault correction
    private String previousTemperature;
    private String previousDewPoint;
    private String previousStationAirPressure;
    private String previousSeaLevelAirPressure;
    private String previousVisibility;
    private String previousWindSpeed;
    private String previousPrecipitation;
    private String previousSnowfall;
    private String previousEvents;
    private String previousCloudCover;
    private String previousWindDirection;

    public WeatherStation(String station, File directory, Boolean newFile) {
        this.stationNumber = station;
        this.newFile = newFile;

        try {
            this.directory = new File(directory + "\\" + this.stationNumber + ".csv");
            this.writer = new BufferedWriter(new FileWriter(this.directory));
        } catch (IOException IOE) {
            System.err.println(IOE.getMessage());
        }
    }

    public void setDate(String date) {this.date = date;}

    public void setTime(String time) {this.time = time;}

    public void setTemperature(String temperature) {this.temperature = temperature;}

    public void setDewPoint(String dewPoint) {this.dewPoint = dewPoint;}

    public void setStationAirPressure(String stationAirPressure) {this.stationAirPressure = stationAirPressure;}

    public void setSeaLevelAirPressure(String seaLevelAirPressure) {this.seaLevelAirPressure = seaLevelAirPressure;}

    public void setVisibility(String visibility) {this.visibility = visibility;}

    public void setWindSpeed(String windSpeed) {this.windSpeed = windSpeed;}

    public void setPrecipitation(String precipitation) {this.precipitation = precipitation;}

    public void setSnowfall(String snowfall) {this.snowfall = snowfall;}

    public void setEvents(String events) {this.events = events;}

    public void setCloudCover(String cloudCover) {this.cloudCover = cloudCover;}

    public void setWindDirection(String windDirection) {this.windDirection = windDirection;}

    public String getNewLine() {

        newLine.append(this.stationNumber);
        newLine.append(", ");
        newLine.append(this.date);
        newLine.append(", ");
        newLine.append(this.time);
        newLine.append(", ");

        if (temperature.length() > 1) {
            newLine.append(this.temperature);
        } else {
            newLine.append(previousTemperature);
        }
        newLine.append(", ");

        if (this.dewPoint.length() > 1) {
            newLine.append(this.dewPoint);
        } else {
            newLine.append(this.previousDewPoint);
        }
        newLine.append(", ");

        if (this.stationAirPressure.length() > 1) {
            newLine.append(this.stationAirPressure);
        } else {
            newLine.append(this.previousStationAirPressure);
        }
        newLine.append(", ");

        if (this.seaLevelAirPressure.length() > 1) {
            newLine.append(this.seaLevelAirPressure);
        } else {
            newLine.append(this.previousSeaLevelAirPressure);
        }
        newLine.append(", ");

        if (this.visibility.length() > 1) {
            newLine.append(this.visibility);
        } else {
            newLine.append(this.previousVisibility);
        }
        newLine.append(", ");

        if (this.windSpeed.length() > 1) {
            newLine.append(this.windSpeed);
        } else {
            newLine.append(this.previousWindSpeed);
        }
        newLine.append(", ");

        if (this.precipitation.length() > 1) {
            newLine.append(this.precipitation);
        } else {
            newLine.append(this.previousPrecipitation);
        }
        newLine.append(", ");

        if (this.snowfall.length() > 1) {
            newLine.append(this.snowfall);
        } else {
            newLine.append(this.previousSnowfall);
        }
        newLine.append(", ");

        if (this.events.length() > 1) {
            newLine.append(this.events);
        } else {
            newLine.append(this.previousEvents);
        }
        newLine.append(", ");

        if (this.cloudCover.length() > 1) {
            newLine.append(this.cloudCover);
        } else {
            newLine.append(this.previousCloudCover);
        }
        newLine.append(", ");

        if (this.windDirection.length() > 1) {
            newLine.append(this.windDirection);
        } else {
            newLine.append(this.previousWindDirection);
        }
        return newLine.toString();
    }

    public void setNewLine() {
        this.previousTemperature = this.temperature;
        this.previousDewPoint = this.dewPoint;
        this.previousStationAirPressure = this.stationAirPressure;
        this.previousSeaLevelAirPressure = this.seaLevelAirPressure;
        this.previousVisibility = this.visibility;
        this.previousWindSpeed = this.windSpeed;
        this.previousPrecipitation = this.precipitation;
        this.previousSnowfall = this.snowfall;
        this.previousEvents = this.events;
        this.previousCloudCover = this.cloudCover;
        this.previousWindDirection = this.windDirection;

        this.newLine = new StringBuilder();
    }

    public void writeToFile() {

        try {
            if (!newFile) {
                this.writer.append(this.getNewLine());
                this.writer.newLine();
                this.writer.flush();
                setNewLine();
            } else {
                this.writer.write("STN,DATE,TIME,TEMP,DEWP,STP,SLP,VISIB,WDSP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR");
                this.writer.newLine();
                this.writer.append(this.newLine);
                this.writer.flush();

                this.newFile = false;
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
