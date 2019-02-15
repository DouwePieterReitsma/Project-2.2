import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread implements Runnable {

    private Socket connection;
    private static File directory = new File("C:\\Users\\kevin\\Desktop\\Testing\\");
    private HashMap<String, WeatherStation> stationList = new HashMap<>();
    private WeatherStation currentStation;

    public ServerThread(Socket connection) {
        this.connection = connection;
    }

    public void run() {

        System.err.println("New thread");

        try {
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while (true) {
                String fromClient = inputFromClient.readLine();

                if (fromClient == null) {
                    connection.close();
                    System.out.println("Connection closed.");
                    break;
                } else {
                    xmlFilter(fromClient);
                }
            }
            connection.close();
            System.err.println("Connection closed, worker thread ending.");

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    private void xmlFilter(String fromClient) {

        if (fromClient.contains("STN")) {
            String partiallyReplacedSTN = fromClient.replace("<STN>", "");
            String replacedSTN = partiallyReplacedSTN.replace("</STN>", "");
            String strippedSTN = replacedSTN.strip();

            if (stationList.containsKey(strippedSTN)) {
                currentStation = stationList.get(strippedSTN);
            } else {
                stationList.put(strippedSTN, new WeatherStation(strippedSTN, directory));
                currentStation = stationList.get(strippedSTN);
            }
        } else if (fromClient.contains("DATE")) {
            String partiallyReplacedDATE = fromClient.replace("<DATE>", "");
            String replacedDATE = partiallyReplacedDATE.replace("</DATE>", "");

            currentStation.setDate(replacedDATE.strip());
        } else if (fromClient.contains("TIME")) {
            String partiallyReplacedTIME = fromClient.replace("<TIME>", "");
            String replacedTIME = partiallyReplacedTIME.replace("</TIME>", "");

            currentStation.setTime(replacedTIME.strip());
        } else if (fromClient.contains("TEMP")) {
            String partiallyReplacedTEMP = fromClient.replace("<TEMP>", "");
            String replacedTEMP = partiallyReplacedTEMP.replace("</TEMP>", "");

            currentStation.setTemperature(replacedTEMP.strip());
        } else if (fromClient.contains("DEWP")) {
            String partiallyReplacedDEWP = fromClient.replace("<DEWP>", "");
            String replacedDEWP = partiallyReplacedDEWP.replace("</DEWP>", "");

            currentStation.setDewPoint(replacedDEWP.strip());
        } else if (fromClient.contains("STP")) {
            String partiallyReplacedSTP = fromClient.replace("<STP>", "");
            String replacedSTP = partiallyReplacedSTP.replace("</STP>", "");

            currentStation.setStationAirPressure(replacedSTP.strip());
        } else if (fromClient.contains("SLP")) {
            String partiallyReplacedSLP = fromClient.replace("<SLP>", "");
            String replacedSLP = partiallyReplacedSLP.replace("</SLP>", "");

            currentStation.setSeaLevelAirPressure(replacedSLP.strip());
        } else if (fromClient.contains("VISIB")) {
            String partiallyReplacedVISIB = fromClient.replace("<VISIB>", "");
            String replacedVISIB = partiallyReplacedVISIB.replace("</VISIB>", "");

            currentStation.setVisibility(replacedVISIB.strip());
        } else if (fromClient.contains("WDSP")) {
            String partiallyReplacedWDSP = fromClient.replace("<WDSP>", "");
            String replacedWDSP = partiallyReplacedWDSP.replace("</WDSP>", "");

            currentStation.setWindSpeed(replacedWDSP.strip());
        } else if (fromClient.contains("PRCP")) {
            String partiallyReplacedPRCP = fromClient.replace("<PRCP>", "");
            String replacedPRCP = partiallyReplacedPRCP.replace("</PRCP>", "");

            currentStation.setPrecipitation(replacedPRCP.strip());
        } else if (fromClient.contains("SNDP")) {
            String partiallyReplacedSNDP = fromClient.replace("<SNDP>", "");
            String replacedSNDP = partiallyReplacedSNDP.replace("</SNDP>", "");

            currentStation.setSnowfall(replacedSNDP.strip());
        } else if (fromClient.contains("FRSHTT")) {
            String partiallyReplacedFRSHTT = fromClient.replace("<FRSHTT>", "");
            String replacedFRSHTT = partiallyReplacedFRSHTT.replace("</FRSHTT>", "");

            currentStation.setEvents(replacedFRSHTT.strip());
        } else if (fromClient.contains("CLDC")) {
            String partiallyReplacedCLDC = fromClient.replace("<CLDC>", "");
            String replacedCLDC = partiallyReplacedCLDC.replace("</CLDC>", "");

            currentStation.setCloudCover(replacedCLDC.strip());
        } else if (fromClient.contains("WNDDIR")) {
            String partiallyReplacedWNDDIR = fromClient.replace("<WNDDIR>", "");
            String replacedWNDDIR = partiallyReplacedWNDDIR.replace("</WNDDIR>", "");

            currentStation.setWindDirection(replacedWNDDIR.strip());
        } else if (fromClient.contains("</MEASUREMENT>")) {

            currentStation.writeToFile();
        }
    }
}
