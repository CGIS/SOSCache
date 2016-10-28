import generator.JsonGenerator;
import org.cgis.dev.SOSResource.HibernateResource;
import org.cgis.dev.SOSResource.Resource;
import org.cgis.dev.ds.Observation;
import org.cgis.dev.ds.SOS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Zil on 2016/9/19.
 */

@WebServlet("/cache")
public class SosCache extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("ISO-8859-1");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");


        resp.getWriter().println("");
    }



}
