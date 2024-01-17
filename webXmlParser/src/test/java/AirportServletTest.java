import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import maksim.nk.airport.AirportServlet;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AirportServletTest {

    @Test
    public void testAirportServlet() throws Exception {
        AirportServlet airportServlet = new AirportServlet();


        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        airportServlet.doGet(request, response);


        String htmlContent = stringWriter.toString();


        assertTrue(htmlContent.contains("Sheremetyevo International Airport"));
        assertTrue(htmlContent.contains("Minsk National Airport"));
    }

    // ... остальной код теста
}
