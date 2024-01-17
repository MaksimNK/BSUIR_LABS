package maksim.nk.airport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AirportServlet", urlPatterns = "/airportServlet")
public class AirportServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Airports</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Airports</h1>");

            try {
                DocumentBuilderFactory factory = SingletonFactory.getInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                ClassLoader classLoader = getClass().getClassLoader();
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
                            case "AirportName":
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
                                String fullAddress = "";
                                for (Node n = bufferNode.getFirstChild(); n != null; n = n.getNextSibling()) {
                                    fullAddress = fullAddress.concat(n.getTextContent() + "");
                                }
                                builderAirport.withFullAddress(fullAddress);
                                break;
                        }
                    }
                    airports.add(builderAirport.build());
                }

                for (Airport airport : airports) {
                    out.println("<div>");
                    out.println("<h2>" + airport.getAirportName() + " (" + airport.getAirportID() + ")</h2>");
                    out.println("<p>Contact Name: " + airport.getContactName() + "</p>");
                    out.println("<p>Contact Title: " + airport.getContactTitle() + "</p>");
                    out.println("<p>Phone: " + airport.getPhone() + "</p>");
                    out.println("<p>Full Address: " + airport.getFullAddress() + "</p>");
                    out.println("</div>");
                }

                out.println("</body>");
                out.println("</html>");

            } catch (ParserConfigurationException | SAXException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
