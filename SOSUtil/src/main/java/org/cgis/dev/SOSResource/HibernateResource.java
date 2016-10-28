package org.cgis.dev.SOSResource;

import org.cgis.dev.ds.Offering;
import org.cgis.dev.ds.SOS;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.util.List;

/**
 * Created by Zil on 2016/9/17.
 */
public class HibernateResource implements Resource {

    private final String url;
    private SessionFactory sessionFactory;
    private Session session;

    public HibernateResource(String url){
        sessionFactory = buildSessionFactory(null);
        this.url = url.toString();
    }

    public HibernateResource(URL url){
        this(url.toString());
    }

    public SOS[] get(){
        session = sessionFactory.openSession();
        Criteria c = session.createCriteria(SOS.class);
        List sosList = c.list();
        return (SOS[]) sosList.toArray(new SOS[sosList.size()]);
    }

    public SOS getSOS() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from SOS s where s.url = :url" );
        query.setParameter("url", url);
        List result = query.list();

        if( result.size() > 0 )
            return (SOS) result.get(0);

        return null;
    }

    public void close(){
        session.close();
    }

    public Offering getOffering(String offering) {
        return null;
    }

    private SessionFactory buildSessionFactory(String configure){
        if( configure == null )
            return new Configuration().configure().buildSessionFactory();
        return new Configuration().configure( configure ).buildSessionFactory();
    }
}
