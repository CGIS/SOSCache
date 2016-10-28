import org.cgis.dev.SOSFactoryImpl;
import org.cgis.dev.ds.SOS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zil on 2016/10/25.
 */

@WebServlet("/register")
public class SosRegister  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("ISO-8859-1");

        String url = req.getParameter("sos");
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        String frequency = req.getParameter("frequency");

        SOSFactoryImpl sosFactory = new SOSFactoryImpl("http://cgis-dev.csrsr.ncu.edu.tw:8080/swcb-ccd/service");
        SOS sos = sosFactory.getSOS();
        sos.setName(name);
        sos.setType(type);
        sos.setFrequency(frequency);
        System.out.println(sos.toJSON());

        resp.setContentType("application/json; charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(sos.toJSON());
    }
}
