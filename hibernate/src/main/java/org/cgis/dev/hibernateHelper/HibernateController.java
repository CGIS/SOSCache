package org.cgis.dev.hibernateHelper;

import org.cgis.dev.ds.ObservableProperty;
import org.cgis.dev.ds.Observation;
import org.cgis.dev.ds.Offering;
import org.cgis.dev.ds.SOS;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by Zil on 2016/9/20.
 */
public class HibernateController {

    private SessionFactory sessionFactory;

    public HibernateController(){
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
    }

    public void save(SOS sos){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(sos);
        for(Offering offering : sos.getOfferings()) {
            session.saveOrUpdate(offering);
            for(ObservableProperty observableProperty :offering.getObservableProperties() )
                session.saveOrUpdate(observableProperty);
//            for(Observation observation : offering.getObservations())
//                session.saveOrUpdate(observation);
        }
        transaction.commit();
        session.close();
    }
}
