import junit.framework.TestCase;
import org.cgis.dev.ds.ObservableProperty;
import org.cgis.dev.ds.Observation;
import org.cgis.dev.ds.Offering;
import org.cgis.dev.ds.SOS;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

/**
 * Created by Zil on 2016/9/17.
 */
public class mappingTest extends TestCase {

    private SessionFactory sessionFactory;

    @Override
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
    }

    @Override
    protected void tearDown() throws Exception {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    public void testBasicUsage() {
        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save( new SOS("twed", "http://test.url/sos/service", "CCD") );
        session.save( new SOS("swcb", "http://test2.url/sos/service", "waterLevel") );
        session.getTransaction().commit();
        session.close();

        // now lets pull events from the database and list them
        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from SOS" ).list();
        for ( SOS s : (List<SOS>) result ) {
            System.out.println( s.toJSON() );
        }
        session.getTransaction().commit();
        session.close();
    }

    public void testPersistOffering(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SOS sos = new SOS("twed", "http://test.url/sos/service", "CCD");
        Offering offering = new Offering("myOffering", "myProcedure");
        session.save( sos );

        offering.setSos(sos);
        session.save( offering );
        session.getTransaction().commit();
        session.close();

        // now lets pull events from the database and list them
        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from SOS" ).list();
        for ( SOS s : (List<SOS>) result ) {
            System.out.println( s.toJSON() );
        }
        session.getTransaction().commit();
        session.close();
    }
}
