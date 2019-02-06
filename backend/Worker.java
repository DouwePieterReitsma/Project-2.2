import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Worker implements Runnable {

    private Socket connection;
    private static Set<String> stationSet = new HashSet<>();
    private static File directory = new File("C:\\Users\\kevin\\Desktop\\Testing\\");

    public Worker(Socket connection) {
        this.connection = connection;
    }

    public void run() {

        try {
            String fromClient;
            StringBuilder newLine = new StringBuilder();
            String stationNumber = "";

            System.err.println("New thread");

            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while (true) {
                fromClient = inputFromClient.readLine();

                if (fromClient == null) {
                    connection.close();
                    System.out.println("Connection closed.");
                    break;
                } else {
                    if (fromClient.contains("STN")) {
                        String partiallyReplacedSTN = fromClient.replace("<STN>", "");
                        String replacedSTN = partiallyReplacedSTN.replace("</STN>", "");
                        newLine.append(replacedSTN.strip());
                        newLine.append(",");
                        stationNumber = replacedSTN.strip();
                    } else if (fromClient.contains("DATE")) {
                        String partiallyReplacedDATE = fromClient.replace("<DATE>", "");
                        String replacedDATE = partiallyReplacedDATE.replace("</DATE>", "");
                        newLine.append(replacedDATE.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("TIME")) {
                        String partiallyReplacedTIME = fromClient.replace("<TIME>", "");
                        String replacedTIME = partiallyReplacedTIME.replace("</TIME>", "");
                        newLine.append(replacedTIME.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("TEMP")) {
                        String partiallyReplacedTEMP = fromClient.replace("<TEMP>", "");
                        String replacedTEMP = partiallyReplacedTEMP.replace("</TEMP>", "");
                        newLine.append(replacedTEMP.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("DEWP")) {
                        String partiallyReplacedDEWP = fromClient.replace("<DEWP>", "");
                        String replacedDEWP = partiallyReplacedDEWP.replace("</DEWP>", "");
                        newLine.append(replacedDEWP.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("STP")) {
                        String partiallyReplacedSTP = fromClient.replace("<STP>", "");
                        String replacedSTP = partiallyReplacedSTP.replace("</STP>", "");
                        newLine.append(replacedSTP.strip());
                        testingFunction(replacedSTP.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("SLP")) {
                        String partiallyReplacedSLP = fromClient.replace("<SLP>", "");
                        String replacedSLP = partiallyReplacedSLP.replace("</SLP>", "");
                        newLine.append(replacedSLP.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("VISIB")) {
                        String partiallyReplacedVISIB = fromClient.replace("<VISIB>", "");
                        String replacedVISIB = partiallyReplacedVISIB.replace("</VISIB>", "");
                        newLine.append(replacedVISIB.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("WDSP")) {
                        String partiallyReplacedWDSP = fromClient.replace("<WDSP>", "");
                        String replacedWDSP = partiallyReplacedWDSP.replace("</WDSP>", "");
                        newLine.append(replacedWDSP.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("PRCP")) {
                        String partiallyReplacedPRCP = fromClient.replace("<PRCP>", "");
                        String replacedPRCP = partiallyReplacedPRCP.replace("</PRCP>", "");
                        newLine.append(replacedPRCP.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("SNDP")) {
                        String partiallyReplacedSNDP = fromClient.replace("<SNDP>", "");
                        String replacedSNDP = partiallyReplacedSNDP.replace("</SNDP>", "");
                        newLine.append(replacedSNDP.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("FRSHTT")) {
                        String partiallyReplacedFRSHTT = fromClient.replace("<FRSHTT>", "");
                        String replacedFRSHTT = partiallyReplacedFRSHTT.replace("</FRSHTT>", "");
                        newLine.append(replacedFRSHTT.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("CLDC")) {
                        String partiallyReplacedCLDC = fromClient.replace("<CLDC>", "");
                        String replacedCLDC = partiallyReplacedCLDC.replace("</CLDC>", "");
                        newLine.append(replacedCLDC.strip());
                        newLine.append(",");
                    } else if (fromClient.contains("WNDDIR")) {
                        String partiallyReplacedWNDDIR = fromClient.replace("<WNDDIR>", "");
                        String replacedWNDDIR = partiallyReplacedWNDDIR.replace("</WNDDIR>", "");
                        newLine.append(replacedWNDDIR.strip());
                    } else if (fromClient.contains("</MEASUREMENT>")) {

                        if (stationSet.contains(stationNumber)) {
                            addNewLineToExistingFile(stationNumber, newLine);
                        } else {
                            addNewLineToNewFile(stationNumber, newLine);
                            stationSet.add(stationNumber);
                        }
                        newLine = new StringBuilder();
                    }
                }
            }
            connection.close();
            System.err.println("Connection closed, worker thread ending.");

        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    private synchronized void addNewLineToNewFile(String stationNumber, StringBuilder newLine) throws IOException{

        File newFile = new File(directory + "\\" + stationNumber + ".csv");
        BufferedWriter newOutput = new BufferedWriter(new FileWriter(newFile));

        newOutput.write("STN,DATE,TIME,TEMP,DEWP,STP,SLP,VISIB,WDSP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR");
        newOutput.newLine();
        newOutput.write(newLine.toString());
        newOutput.newLine();
        newOutput.flush();
    }

    private synchronized void addNewLineToExistingFile(String stationNumber, StringBuilder newLine) throws IOException {

        File newFile = new File(directory + "\\" +  stationNumber + ".csv");
        BufferedWriter appendOutput = new BufferedWriter(new FileWriter(newFile, true));

        appendOutput.append(newLine.toString());
        appendOutput.newLine();
        appendOutput.flush();
    }

    private static void testingFunction(String testable) {

        if (testable.length() == 0) {
            System.out.println("No measurement found.");
        }
    }
}
