package maksim.nk;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = SingletonFactory.getInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        ClassLoader classLoader = Main.class.getClassLoader();
        Document document = builder.parse(classLoader.getResourceAsStream("airport.xml"));
        NodeList airportList = document.getElementsByTagName("Airport");

        ArrayList<Airport> airports = new ArrayList<>();

        for (int i = 0; i < airportList.getLength(); i++) {

            Node airportNode = airportList.item(i);
            Airport.Builder builderAirport = new Airport.Builder().withAirportID(airportNode.getAttributes().getNamedItem("AirportID").getTextContent());
            NodeList childNodes = airportNode.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++) {
                Node bufferNode = childNodes.item(j);

                switch (bufferNode.getNodeName()) {
                    case "AirportName" :
                        builderAirport.withAirportName(bufferNode.getTextContent());
                        break;

                    case "ContactName":
                        builderAirport.withContactName(bufferNode.getTextContent());
                        break;

                    case "ContactTitle":
                        builderAirport.withContactTitle(bufferNode.getTextContent());
                        break;

                    case "Phone":
                        builderAirport.withPhone(bufferNode.getTextContent());
                        break;

                    case "FullAddress":
                        String fullAdress = "";
                        for (Node n = bufferNode.getFirstChild(); n != null;  n = n.getNextSibling()) {
                            fullAdress = fullAdress.concat(n.getTextContent() + "");
                        }
                        builderAirport.withFullAddress(fullAdress);
                        break;
                }

            }
            airports.add(builderAirport.build());
        }

        for (Airport airport : airports) {
            System.out.println(airport);
        }

    }
}