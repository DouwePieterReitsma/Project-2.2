import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerThread implements Runnable {

    private Socket connection;
    private static File directory = new File("D:\\Project-2.2\\frontend\\data");
    private HashMap<String, WeatherStation> stationList = new HashMap<>();
    private WeatherStation currentStation;
    private ArrayList<String> stationsIranRadius = new ArrayList<>();
    private Boolean validStation;


    public ServerThread(Socket connection) {
        this.connection = connection;

        // Countries in a 2000km radius around Iran
        // Afghanistan
        stationsIranRadius.add("409480");
        //Armenia
        stationsIranRadius.add("353946");
        stationsIranRadius.add("376820");
        stationsIranRadius.add("377090");
        stationsIranRadius.add("377880");
        stationsIranRadius.add("377890");
        // Azerbaijan
        stationsIranRadius.add("375750");
        stationsIranRadius.add("378630");
        stationsIranRadius.add("378635");
        stationsIranRadius.add("378640");
        stationsIranRadius.add("379070");
        stationsIranRadius.add("379850");
        // Bahrain
        stationsIranRadius.add("404270");
        stationsIranRadius.add("411500");
        // Cyprus
        stationsIranRadius.add("175000");
        stationsIranRadius.add("175150");
        stationsIranRadius.add("175210");
        stationsIranRadius.add("175400");
        stationsIranRadius.add("176000");
        stationsIranRadius.add("176010");
        stationsIranRadius.add("176060");
        stationsIranRadius.add("176074");
        stationsIranRadius.add("176090");
        // Iran
        stationsIranRadius.add("407060");
        stationsIranRadius.add("407090");
        stationsIranRadius.add("407120");
        stationsIranRadius.add("407180");
        stationsIranRadius.add("407190");
        stationsIranRadius.add("407290");
        stationsIranRadius.add("407300");
        stationsIranRadius.add("407310");
        stationsIranRadius.add("407320");
        stationsIranRadius.add("407340");
        stationsIranRadius.add("407360");
        stationsIranRadius.add("407390");
        stationsIranRadius.add("407430");
        stationsIranRadius.add("407450");
        stationsIranRadius.add("407470");
        stationsIranRadius.add("407540");
        stationsIranRadius.add("407570");
        stationsIranRadius.add("407620");
        stationsIranRadius.add("407660");
        stationsIranRadius.add("407720");
        stationsIranRadius.add("407780");
        stationsIranRadius.add("407802");
        stationsIranRadius.add("408000");
        stationsIranRadius.add("408090");
        stationsIranRadius.add("408110");
        stationsIranRadius.add("408190");
        stationsIranRadius.add("408210");
        stationsIranRadius.add("408230");
        stationsIranRadius.add("408310");
        stationsIranRadius.add("408410");
        stationsIranRadius.add("408480");
        stationsIranRadius.add("408560");
        stationsIranRadius.add("408580");
        stationsIranRadius.add("408590");
        stationsIranRadius.add("408750");
        stationsIranRadius.add("408790");
        // Iraq
        stationsIranRadius.add("406080");
        stationsIranRadius.add("406210");
        stationsIranRadius.add("406370");
        stationsIranRadius.add("406420");
        stationsIranRadius.add("406480");
        stationsIranRadius.add("406500");
        stationsIranRadius.add("406650");
        stationsIranRadius.add("406720");
        stationsIranRadius.add("406760");
        stationsIranRadius.add("406890");
        stationsIranRadius.add("690110");
        stationsIranRadius.add("691000");
        stationsIranRadius.add("691194");
        stationsIranRadius.add("692554");
        stationsIranRadius.add("697654");
        stationsIranRadius.add("697774");
        stationsIranRadius.add("697884");
        stationsIranRadius.add("697924");
        // Israel
        stationsIranRadius.add("401540");
        stationsIranRadius.add("401550");
        stationsIranRadius.add("401650");
        stationsIranRadius.add("401760");
        stationsIranRadius.add("401761");
        stationsIranRadius.add("401762");
        stationsIranRadius.add("401770");
        stationsIranRadius.add("401800");
        stationsIranRadius.add("401910");
        stationsIranRadius.add("401980");
        stationsIranRadius.add("401990");
        // Jordan
        stationsIranRadius.add("402500");
        stationsIranRadius.add("402550");
        stationsIranRadius.add("402600");
        stationsIranRadius.add("402650");
        stationsIranRadius.add("402700");
        stationsIranRadius.add("402720");
        stationsIranRadius.add("403100");
        stationsIranRadius.add("403400");
        // Kazakhstan
        stationsIranRadius.add("286790");
        stationsIranRadius.add("287660");
        stationsIranRadius.add("287850");
        stationsIranRadius.add("288670");
        stationsIranRadius.add("288770");
        stationsIranRadius.add("288790");
        stationsIranRadius.add("289520");
        stationsIranRadius.add("289570");
        stationsIranRadius.add("289780");
        stationsIranRadius.add("289840");
        stationsIranRadius.add("298070");
        stationsIranRadius.add("343980");
        stationsIranRadius.add("345810");
        stationsIranRadius.add("346910");
        stationsIranRadius.add("347980");
        stationsIranRadius.add("350420");
        stationsIranRadius.add("350530");
        stationsIranRadius.add("350670");
        stationsIranRadius.add("350780");
        stationsIranRadius.add("350850");
        stationsIranRadius.add("351080");
        stationsIranRadius.add("351730");
        stationsIranRadius.add("351880");
        stationsIranRadius.add("351910");
        stationsIranRadius.add("352170");
        stationsIranRadius.add("352290");
        stationsIranRadius.add("353020");
        stationsIranRadius.add("353210");
        stationsIranRadius.add("353450");
        stationsIranRadius.add("353610");
        stationsIranRadius.add("353630");
        stationsIranRadius.add("353760");
        stationsIranRadius.add("353860");
        stationsIranRadius.add("353940");
        stationsIranRadius.add("354060");
        stationsIranRadius.add("354160");
        stationsIranRadius.add("354260");
        stationsIranRadius.add("354590");
        stationsIranRadius.add("354970");
        stationsIranRadius.add("355320");
        stationsIranRadius.add("355420");
        stationsIranRadius.add("355490");
        stationsIranRadius.add("355760");
        stationsIranRadius.add("355820");
        stationsIranRadius.add("356050");
        stationsIranRadius.add("356330");
        stationsIranRadius.add("356710");
        stationsIranRadius.add("356990");
        stationsIranRadius.add("357000");
        stationsIranRadius.add("357390");
        stationsIranRadius.add("357460");
        stationsIranRadius.add("357560");
        stationsIranRadius.add("357910");
        stationsIranRadius.add("357960");
        stationsIranRadius.add("358490");
        stationsIranRadius.add("358840");
        stationsIranRadius.add("359250");
        stationsIranRadius.add("359530");
        stationsIranRadius.add("359690");
        stationsIranRadius.add("359970");
        stationsIranRadius.add("360010");
        stationsIranRadius.add("360030");
        stationsIranRadius.add("361520");
        stationsIranRadius.add("361770");
        stationsIranRadius.add("361860");
        stationsIranRadius.add("362080");
        stationsIranRadius.add("363350");
        stationsIranRadius.add("363970");
        stationsIranRadius.add("364280");
        stationsIranRadius.add("364390");
        stationsIranRadius.add("364420");
        stationsIranRadius.add("365350");
        stationsIranRadius.add("366220");
        stationsIranRadius.add("366390");
        stationsIranRadius.add("367440");
        stationsIranRadius.add("367780");
        stationsIranRadius.add("368215");
        stationsIranRadius.add("368590");
        stationsIranRadius.add("368700");
        stationsIranRadius.add("380010");
        stationsIranRadius.add("380190");
        stationsIranRadius.add("380490");
        stationsIranRadius.add("380620");
        stationsIranRadius.add("380810");
        stationsIranRadius.add("380910");
        stationsIranRadius.add("381110");
        stationsIranRadius.add("381411");
        stationsIranRadius.add("381960");
        stationsIranRadius.add("381980");
        stationsIranRadius.add("382320");
        stationsIranRadius.add("383280");
        stationsIranRadius.add("383340");
        stationsIranRadius.add("383410");
        stationsIranRadius.add("383430");
        stationsIranRadius.add("384230");
        stationsIranRadius.add("384390");
        // Kuwait
        stationsIranRadius.add("405520");
        stationsIranRadius.add("405640");
        stationsIranRadius.add("405680");
        stationsIranRadius.add("405690");
        stationsIranRadius.add("405700");
        stationsIranRadius.add("405720");
        stationsIranRadius.add("405820");
        stationsIranRadius.add("405860");
        stationsIranRadius.add("405880");
        stationsIranRadius.add("405920");
        // Kyrgyzstan
        stationsIranRadius.add("369820");
        stationsIranRadius.add("382200");
        stationsIranRadius.add("383450");
        stationsIranRadius.add("383530");
        stationsIranRadius.add("383531");
        stationsIranRadius.add("386150");
        // Lebanon
        stationsIranRadius.add("401000");
        stationsIranRadius.add("401010");
        stationsIranRadius.add("401030");
        // Oman
        stationsIranRadius.add("405750");
        stationsIranRadius.add("412410");
        stationsIranRadius.add("412460");
        stationsIranRadius.add("412540");
        stationsIranRadius.add("412560");
        stationsIranRadius.add("412880");
        stationsIranRadius.add("413140");
        stationsIranRadius.add("413160");
        // Pakistan
        stationsIranRadius.add("415300");
        stationsIranRadius.add("415710");
        stationsIranRadius.add("416410");
        stationsIranRadius.add("417490");
        stationsIranRadius.add("417560");
        stationsIranRadius.add("417800");
        // Qatar
        stationsIranRadius.add("404280");
        stationsIranRadius.add("411700");
        // Saudi Arabia
        stationsIranRadius.add("403560");
        stationsIranRadius.add("403570");
        stationsIranRadius.add("403584");
        stationsIranRadius.add("403600");
        stationsIranRadius.add("403610");
        stationsIranRadius.add("403620");
        stationsIranRadius.add("403720");
        stationsIranRadius.add("403730");
        stationsIranRadius.add("403750");
        stationsIranRadius.add("403770");
        stationsIranRadius.add("403940");
        stationsIranRadius.add("404000");
        stationsIranRadius.add("404050");
        stationsIranRadius.add("404150");
        stationsIranRadius.add("404160");
        stationsIranRadius.add("404170");
        stationsIranRadius.add("404200");
        stationsIranRadius.add("404300");
        stationsIranRadius.add("404370");
        stationsIranRadius.add("404380");
        stationsIranRadius.add("404390");
        stationsIranRadius.add("404450");
        stationsIranRadius.add("404770");
        stationsIranRadius.add("404800");
        stationsIranRadius.add("404950");
        stationsIranRadius.add("404980");
        stationsIranRadius.add("410240");
        stationsIranRadius.add("410300");
        stationsIranRadius.add("410360");
        stationsIranRadius.add("410550");
        stationsIranRadius.add("410620");
        stationsIranRadius.add("410840");
        stationsIranRadius.add("411120");
        stationsIranRadius.add("411140");
        stationsIranRadius.add("411280");
        stationsIranRadius.add("411400");
        // Syria
        stationsIranRadius.add("400010");
        stationsIranRadius.add("400070");
        stationsIranRadius.add("400220");
        stationsIranRadius.add("400250");
        stationsIranRadius.add("400300");
        stationsIranRadius.add("400450");
        stationsIranRadius.add("400660");
        stationsIranRadius.add("400800");
        stationsIranRadius.add("400830");
        stationsIranRadius.add("400950");
        // Tajikistan
        stationsIranRadius.add("381413");
        stationsIranRadius.add("385990");
        stationsIranRadius.add("386090");
        stationsIranRadius.add("387130");
        stationsIranRadius.add("388360");
        // Turkey
        stationsIranRadius.add("170220");
        stationsIranRadius.add("170240");
        stationsIranRadius.add("170260");
        stationsIranRadius.add("170300");
        stationsIranRadius.add("170310");
        stationsIranRadius.add("170340");
        stationsIranRadius.add("170380");
        stationsIranRadius.add("170420");
        stationsIranRadius.add("170500");
        stationsIranRadius.add("170560");
        stationsIranRadius.add("170575");
        stationsIranRadius.add("170600");
        stationsIranRadius.add("170660");
        stationsIranRadius.add("170670");
        stationsIranRadius.add("170671");
        stationsIranRadius.add("170672");
        stationsIranRadius.add("170673");
        stationsIranRadius.add("170674");
        stationsIranRadius.add("170700");
        stationsIranRadius.add("170820");
        stationsIranRadius.add("170840");
        stationsIranRadius.add("170860");
        stationsIranRadius.add("170880");
        stationsIranRadius.add("170900");
        stationsIranRadius.add("170920");
        stationsIranRadius.add("170960");
        stationsIranRadius.add("170980");
        stationsIranRadius.add("171100");
        stationsIranRadius.add("171120");
        stationsIranRadius.add("171150");
        stationsIranRadius.add("171160");
        stationsIranRadius.add("171200");
        stationsIranRadius.add("171240");
        stationsIranRadius.add("171270");
        stationsIranRadius.add("171280");
        stationsIranRadius.add("171290");
        stationsIranRadius.add("171295");
        stationsIranRadius.add("171350");
        stationsIranRadius.add("171400");
        stationsIranRadius.add("171500");
        stationsIranRadius.add("171550");
        stationsIranRadius.add("171600");
        stationsIranRadius.add("171700");
        stationsIranRadius.add("171750");
        stationsIranRadius.add("171800");
        stationsIranRadius.add("171840");
        stationsIranRadius.add("171880");
        stationsIranRadius.add("171890");
        stationsIranRadius.add("171900");
        stationsIranRadius.add("171905");
        stationsIranRadius.add("171950");
        stationsIranRadius.add("171990");
        stationsIranRadius.add("172000");
        stationsIranRadius.add("172005");
        stationsIranRadius.add("172020");
        stationsIranRadius.add("172050");
        stationsIranRadius.add("172100");
        stationsIranRadius.add("172180");
        stationsIranRadius.add("172190");
        stationsIranRadius.add("172340");
        stationsIranRadius.add("172370");
        stationsIranRadius.add("172400");
        stationsIranRadius.add("172410");
        stationsIranRadius.add("172440");
        stationsIranRadius.add("172480");
        stationsIranRadius.add("172500");
        stationsIranRadius.add("172600");
        stationsIranRadius.add("172700");
        stationsIranRadius.add("172720");
        stationsIranRadius.add("172734");
        stationsIranRadius.add("172800");
        stationsIranRadius.add("172900");
        stationsIranRadius.add("172920");
        stationsIranRadius.add("172950");
        stationsIranRadius.add("173000");
        stationsIranRadius.add("173100");
        stationsIranRadius.add("173200");
        stationsIranRadius.add("173300");
        stationsIranRadius.add("173500");
        stationsIranRadius.add("173520");
        stationsIranRadius.add("173700");
        stationsIranRadius.add("173750");
        stationsIranRadius.add("691464");
        // Turkmenistan
        stationsIranRadius.add("381412");
        stationsIranRadius.add("383880");
        stationsIranRadius.add("383920");
        stationsIranRadius.add("384573");
        stationsIranRadius.add("385070");
        stationsIranRadius.add("386470");
        stationsIranRadius.add("386560");
        stationsIranRadius.add("386870");
        stationsIranRadius.add("387500");
        stationsIranRadius.add("387630");
        stationsIranRadius.add("387740");
        stationsIranRadius.add("387990");
        stationsIranRadius.add("388060");
        stationsIranRadius.add("388800");
        stationsIranRadius.add("388860");
        stationsIranRadius.add("388950");
        stationsIranRadius.add("389110");
        stationsIranRadius.add("389150");
        stationsIranRadius.add("389740");
        stationsIranRadius.add("389870");
        // United Arab Emirates
        stationsIranRadius.add("404480");
        stationsIranRadius.add("404490");
        stationsIranRadius.add("404520");
        stationsIranRadius.add("411840");
        stationsIranRadius.add("411940");
        stationsIranRadius.add("411960");
        stationsIranRadius.add("411980");
        stationsIranRadius.add("412160");
        stationsIranRadius.add("412170");
        stationsIranRadius.add("412180");
        // Uzbekistan
        stationsIranRadius.add("381410");
        stationsIranRadius.add("381415");
        stationsIranRadius.add("381417");
        stationsIranRadius.add("381490");
        stationsIranRadius.add("381780");
        stationsIranRadius.add("382640");
        stationsIranRadius.add("383960");
        stationsIranRadius.add("384030");
        stationsIranRadius.add("384031");
        stationsIranRadius.add("384032");
        stationsIranRadius.add("384130");
        stationsIranRadius.add("384570");
        stationsIranRadius.add("385450");
        stationsIranRadius.add("385830");
        stationsIranRadius.add("386110");
        stationsIranRadius.add("386180");
        stationsIranRadius.add("386830");
        stationsIranRadius.add("386960");
        stationsIranRadius.add("388120");
        stationsIranRadius.add("389270");
        // Yemen
        stationsIranRadius.add("405760");
        stationsIranRadius.add("414040");

        // Countries in north africa
        // Algeria
        stationsIranRadius.add("603510");
        stationsIranRadius.add("603550");
        stationsIranRadius.add("603600");
        stationsIranRadius.add("603900");
        stationsIranRadius.add("604020");
        stationsIranRadius.add("604190");
        stationsIranRadius.add("604450");
        stationsIranRadius.add("604520");
        stationsIranRadius.add("604570");
        stationsIranRadius.add("604680");
        stationsIranRadius.add("604750");
        stationsIranRadius.add("604900");
        stationsIranRadius.add("604920");
        stationsIranRadius.add("605070");
        stationsIranRadius.add("605110");
        stationsIranRadius.add("605250");
        stationsIranRadius.add("605310");
        stationsIranRadius.add("605350");
        stationsIranRadius.add("605360");
        stationsIranRadius.add("605450");
        stationsIranRadius.add("605490");
        stationsIranRadius.add("605500");
        stationsIranRadius.add("605550");
        stationsIranRadius.add("605590");
        stationsIranRadius.add("605660");
        stationsIranRadius.add("605710");
        stationsIranRadius.add("605800");
        stationsIranRadius.add("605900");
        stationsIranRadius.add("606110");
        stationsIranRadius.add("606400");
        stationsIranRadius.add("606700");
        stationsIranRadius.add("606800");
        stationsIranRadius.add("606805");
        stationsIranRadius.add("889340");
        stationsIranRadius.add("889380");
        stationsIranRadius.add("889400");
        // Egypt
        stationsIranRadius.add("623060");
        stationsIranRadius.add("623090");
        stationsIranRadius.add("623180");
        stationsIranRadius.add("623250");
        stationsIranRadius.add("623320");
        stationsIranRadius.add("623330");
        stationsIranRadius.add("623360");
        stationsIranRadius.add("623370");
        stationsIranRadius.add("623601");
        stationsIranRadius.add("623660");
        stationsIranRadius.add("623662");
        stationsIranRadius.add("623680");
        stationsIranRadius.add("624380");
        stationsIranRadius.add("624400");
        stationsIranRadius.add("624520");
        // Libya
        stationsIranRadius.add("620020");
        stationsIranRadius.add("620070");
        stationsIranRadius.add("620100");
        stationsIranRadius.add("620160");
        stationsIranRadius.add("620190");
        stationsIranRadius.add("620530");
        stationsIranRadius.add("620550");
        stationsIranRadius.add("620560");
        stationsIranRadius.add("620590");
        stationsIranRadius.add("620630");
        stationsIranRadius.add("621310");
        stationsIranRadius.add("621610");
        stationsIranRadius.add("621760");
        // Morocco
        stationsIranRadius.add("601010");
        stationsIranRadius.add("601050");
        stationsIranRadius.add("601070");
        stationsIranRadius.add("601150");
        stationsIranRadius.add("601270");
        stationsIranRadius.add("601350");
        stationsIranRadius.add("601360");
        stationsIranRadius.add("601410");
        stationsIranRadius.add("601500");
        stationsIranRadius.add("601550");
        stationsIranRadius.add("601560");
        stationsIranRadius.add("601600");
        stationsIranRadius.add("601850");
        stationsIranRadius.add("601900");
        stationsIranRadius.add("601910");
        stationsIranRadius.add("602001");
        stationsIranRadius.add("602100");
        stationsIranRadius.add("602200");
        stationsIranRadius.add("602300");
        stationsIranRadius.add("602500");
        stationsIranRadius.add("602520");
        stationsIranRadius.add("602650");
        stationsIranRadius.add("603180");
        stationsIranRadius.add("603400");
        // Tunisia
        stationsIranRadius.add("607100");
        stationsIranRadius.add("607140");
        stationsIranRadius.add("607150");
        stationsIranRadius.add("607200");
        stationsIranRadius.add("607250");
        stationsIranRadius.add("607280");
        stationsIranRadius.add("607340");
        stationsIranRadius.add("607350");
        stationsIranRadius.add("607380");
        stationsIranRadius.add("607400");
        stationsIranRadius.add("607401");
        stationsIranRadius.add("607403");
        stationsIranRadius.add("607450");
        stationsIranRadius.add("607480");
        stationsIranRadius.add("607500");
        stationsIranRadius.add("607600");
        stationsIranRadius.add("607650");
        stationsIranRadius.add("607690");
        stationsIranRadius.add("607700");
        stationsIranRadius.add("607750");
        // Western Sahara
        stationsIranRadius.add("600330");
        stationsIranRadius.add("600960");
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

             if (stationsIranRadius.contains(strippedSTN)) {
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

            currentStation.setDate(replacedDATE.strip());
        } else if (fromClient.contains("TIME") && validStation) {
            String partiallyReplacedTIME = fromClient.replace("<TIME>", "");
            String replacedTIME = partiallyReplacedTIME.replace("</TIME>", "");

            currentStation.setTime(replacedTIME.strip());
        } else if (fromClient.contains("TEMP") && validStation) {
            String partiallyReplacedTEMP = fromClient.replace("<TEMP>", "");
            String replacedTEMP = partiallyReplacedTEMP.replace("</TEMP>", "");

            currentStation.setTemperature(replacedTEMP.strip());
        } else if (fromClient.contains("DEWP") && validStation) {
            String partiallyReplacedDEWP = fromClient.replace("<DEWP>", "");
            String replacedDEWP = partiallyReplacedDEWP.replace("</DEWP>", "");

            currentStation.setDewPoint(replacedDEWP.strip());
        } else if (fromClient.contains("STP") && validStation) {
            String partiallyReplacedSTP = fromClient.replace("<STP>", "");
            String replacedSTP = partiallyReplacedSTP.replace("</STP>", "");

            currentStation.setStationAirPressure(replacedSTP.strip());
        } else if (fromClient.contains("SLP") && validStation) {
            String partiallyReplacedSLP = fromClient.replace("<SLP>", "");
            String replacedSLP = partiallyReplacedSLP.replace("</SLP>", "");

            currentStation.setSeaLevelAirPressure(replacedSLP.strip());
        } else if (fromClient.contains("VISIB") && validStation) {
            String partiallyReplacedVISIB = fromClient.replace("<VISIB>", "");
            String replacedVISIB = partiallyReplacedVISIB.replace("</VISIB>", "");

            currentStation.setVisibility(replacedVISIB.strip());
        } else if (fromClient.contains("WDSP") && validStation) {
            String partiallyReplacedWDSP = fromClient.replace("<WDSP>", "");
            String replacedWDSP = partiallyReplacedWDSP.replace("</WDSP>", "");

            currentStation.setWindSpeed(replacedWDSP.strip());
        } else if (fromClient.contains("PRCP") && validStation) {
            String partiallyReplacedPRCP = fromClient.replace("<PRCP>", "");
            String replacedPRCP = partiallyReplacedPRCP.replace("</PRCP>", "");

            currentStation.setPrecipitation(replacedPRCP.strip());
        } else if (fromClient.contains("SNDP") && validStation) {
            String partiallyReplacedSNDP = fromClient.replace("<SNDP>", "");
            String replacedSNDP = partiallyReplacedSNDP.replace("</SNDP>", "");

            currentStation.setSnowfall(replacedSNDP.strip());
        } else if (fromClient.contains("FRSHTT") && validStation) {
            String partiallyReplacedFRSHTT = fromClient.replace("<FRSHTT>", "");
            String replacedFRSHTT = partiallyReplacedFRSHTT.replace("</FRSHTT>", "");

            currentStation.setEvents(replacedFRSHTT.strip());
        } else if (fromClient.contains("CLDC") && validStation) {
            String partiallyReplacedCLDC = fromClient.replace("<CLDC>", "");
            String replacedCLDC = partiallyReplacedCLDC.replace("</CLDC>", "");

            currentStation.setCloudCover(replacedCLDC.strip());
        } else if (fromClient.contains("WNDDIR") && validStation) {
            String partiallyReplacedWNDDIR = fromClient.replace("<WNDDIR>", "");
            String replacedWNDDIR = partiallyReplacedWNDDIR.replace("</WNDDIR>", "");

            currentStation.setWindDirection(replacedWNDDIR.strip());
        } else if (fromClient.contains("</MEASUREMENT>") && validStation) {
            currentStation.writeToFile();
        }
    }
}
