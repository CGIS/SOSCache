import junit.framework.TestCase;
import org.cgis.dev.SOSUtility.SOSAdapter;
import org.cgis.dev.ds.SOS;
import org.cgis.dev.hibernateHelper.HibernateController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Zil on 2016/9/19.
 */
public class SOSAdapterTest extends TestCase {

    public void testGetCapabilities() throws IOException, ParserConfigurationException, SAXException {
        SOSAdapter SOSAdapter = new SOSAdapter("http://cgis.csrsr.ncu.edu.tw:8080/swcb-sos-new/service");
        SOSAdapter.getCapabilities();
        SOS sos = SOSAdapter.getSos();
        HibernateController hibernateController = new HibernateController();
        hibernateController.save(sos);
    }
}
