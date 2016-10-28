import junit.framework.TestCase;
import org.cgis.dev.SOSFactoryImpl;
import org.cgis.dev.ds.SOS;

import java.net.MalformedURLException;

/**
 * Created by zil on 2016/10/27.
 */
public class SOSFactoryTest extends TestCase {

    public void testGetNullSOS() throws MalformedURLException {
        SOSFactoryImpl sosFactory = new SOSFactoryImpl("http://123.com:8080/");
        SOS sos = sosFactory.getSOS();
        assertNull(sos);
    }

    public void testGetHibernateSOS() throws MalformedURLException {
        SOSFactoryImpl sosFactory = new SOSFactoryImpl("http://test.url/sos/service");
        SOS sos = sosFactory.getSOS();
        System.out.println(sos.toJSON());
        assertNotNull(sos);
    }


    public void testGetRealSOS() throws MalformedURLException {
        SOSFactoryImpl sosFactory = new SOSFactoryImpl("http://cgis-dev.csrsr.ncu.edu.tw:8080/swcb-ccd/service");
//        SOS sos = sosFactory.getSOS();
//        System.out.println(sos.toJSON());
        assertNotNull(null);
    }
}
