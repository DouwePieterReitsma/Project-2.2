import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerThread implements Runnable {

    private Socket connection;
    private static File directory = new File("C:\\Users\\kevin\\Desktop\\Testing\\");
    private HashMap<String, WeatherStation> stationList = new HashMap<>();
    private WeatherStation currentStation;
    private ArrayList<String> stationArray;
    private Boolean validStation;

    ServerThread(Socket connection, ArrayList<String> stationArray) {
        this.connection = connection;
        this.stationArray = stationArray;
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
            String strippedSTN = replacedSTN.trim();

             if (stationArray.contains(strippedSTN)) {
                 validStation = true;
                 if (stationList.containsKey(strippedSTN)) {
                     currentStation = stationList.get(strippedSTN);
                 } else {
                     stationList.put(strippedSTN, new WeatherStation(strippedSTN, directory, true));
                     currentStation = stationList.get(strippedSTN);
                 }
             } else {
                 validStation = false;
             }

        } else if (fromClient.contains("DATE") && validStation) {
            String partiallyReplacedDATE = fromClient.replace("<DATE>", "");
            String replacedDATE = partiallyReplacedDATE.replace("</DATE>", "");

            currentStation.setDate(replacedDATE.trim());
        } else if (fromClient.contains("TIME") && validStation) {
            String partiallyReplacedTIME = fromClient.replace("<TIME>", "");
            String replacedTIME = partiallyReplacedTIME.replace("</TIME>", "");

            currentStation.setTime(replacedTIME.trim());
        } else if (fromClient.contains("TEMP") && validStation) {
            String partiallyReplacedTEMP = fromClient.replace("<TEMP>", "");
            String replacedTEMP = partiallyReplacedTEMP.replace("</TEMP>", "");

            currentStation.setTemperature(replacedTEMP.trim());
        } else if (fromClient.contains("DEWP") && validStation) {
            String partiallyReplacedDEWP = fromClient.replace("<DEWP>", "");
            String replacedDEWP = partiallyReplacedDEWP.replace("</DEWP>", "");

            currentStation.setDewPoint(replacedDEWP.trim());
        } else if (fromClient.contains("STP") && validStation) {
            String partiallyReplacedSTP = fromClient.replace("<STP>", "");
            String replacedSTP = partiallyReplacedSTP.replace("</STP>", "");

            currentStation.setStationAirPressure(replacedSTP.trim());
        } else if (fromClient.contains("SLP") && validStation) {
            String partiallyReplacedSLP = fromClient.replace("<SLP>", "");
            String replacedSLP = partiallyReplacedSLP.replace("</SLP>", "");

            currentStation.setSeaLevelAirPressure(replacedSLP.trim());
        } else if (fromClient.contains("VISIB") && validStation) {
            String partiallyReplacedVISIB = fromClient.replace("<VISIB>", "");
            String replacedVISIB = partiallyReplacedVISIB.replace("</VISIB>", "");

            currentStation.setVisibility(replacedVISIB.trim());
        } else if (fromClient.contains("WDSP") && validStation) {
            String partiallyReplacedWDSP = fromClient.replace("<WDSP>", "");
            String replacedWDSP = partiallyReplacedWDSP.replace("</WDSP>", "");

            currentStation.setWindSpeed(replacedWDSP.trim());
        } else if (fromClient.contains("PRCP") && validStation) {
            String partiallyReplacedPRCP = fromClient.replace("<PRCP>", "");
            String replacedPRCP = partiallyReplacedPRCP.replace("</PRCP>", "");

            currentStation.setPrecipitation(replacedPRCP.trim());
        } else if (fromClient.contains("SNDP") && validStation) {
            String partiallyReplacedSNDP = fromClient.replace("<SNDP>", "");
            String replacedSNDP = partiallyReplacedSNDP.replace("</SNDP>", "");

            currentStation.setSnowfall(replacedSNDP.trim());
        } else if (fromClient.contains("FRSHTT") && validStation) {
            String partiallyReplacedFRSHTT = fromClient.replace("<FRSHTT>", "");
            String replacedFRSHTT = partiallyReplacedFRSHTT.replace("</FRSHTT>", "");

            currentStation.setEvents(replacedFRSHTT.trim());
        } else if (fromClient.contains("CLDC") && validStation) {
            String partiallyReplacedCLDC = fromClient.replace("<CLDC>", "");
            String replacedCLDC = partiallyReplacedCLDC.replace("</CLDC>", "");

            currentStation.setCloudCover(replacedCLDC.trim());
        } else if (fromClient.contains("WNDDIR") && validStation) {
            String partiallyReplacedWNDDIR = fromClient.replace("<WNDDIR>", "");
            String replacedWNDDIR = partiallyReplacedWNDDIR.replace("</WNDDIR>", "");

            currentStation.setWindDirection(replacedWNDDIR.trim());
        } else if (fromClient.contains("</MEASUREMENT>") && validStation) {
            currentStation.writeToFile();
        }
    }
}
