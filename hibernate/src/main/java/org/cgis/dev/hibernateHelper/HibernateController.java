package org.cgis.dev.hibernateHelper;

import org.cgis.dev.ds.ObservableProperty;
import org.cgis.dev.ds.Observation;
import org.cgis.dev.ds.Offering;
import org.cgis.dev.ds.SOS;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    public Boolean isExist(SOS sos){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(SOS.class);
        criteria.add(Restrictions.eq("url", sos.getUrl()));
        return criteria.list().size() > 0;
    }

    public Boolean isExist(Offering offering){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(SOS.class);
        criteria.add(Restrictions.eq("name", offering.getName()));
        return criteria.list().size() > 0;
    }
}
