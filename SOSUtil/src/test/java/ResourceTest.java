import org.cgis.dev.SOSResource.HibernateResource;
import junit.framework.TestCase;
import org.cgis.dev.SOSResource.SOSResource;
import org.cgis.dev.ds.Offering;
import org.cgis.dev.ds.SOS;

import java.net.MalformedURLException;
import java.util.Set;

/**
 * Created by Zil on 2016/9/18.
 */
public class ResourceTest extends TestCase {

    public void testHibernateResourceBasic() {
        HibernateResource hibernateResource = new HibernateResource("http://test3.url/sos/service");
        SOS sos = hibernateResource.getSOS();
        Set<Offering> offerings = sos.getOfferings();

    }

    public void testHibernateListSOS(){
        HibernateResource hibernateResource = new HibernateResource("http://test3.url/sos/service");
        SOS[] soses = hibernateResource.get();
        for( SOS sos : soses ){
            System.out.print(sos.toJSON());
        }

    }

    public void testSOSResource() throws MalformedURLException {
        SOSResource sosResource = new SOSResource("http://cgis-dev.csrsr.ncu.edu.tw:8080/swcb-ccd/service");
        SOS sos = sosResource.getSOS();
        System.out.print(sos.toJSON());

    }
}
