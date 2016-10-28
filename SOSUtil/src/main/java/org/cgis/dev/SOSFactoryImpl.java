package org.cgis.dev;

import org.cgis.dev.SOSResource.HibernateResource;
import org.cgis.dev.SOSResource.SOSResource;
import org.cgis.dev.ds.Offering;
import org.cgis.dev.ds.SOS;
import org.cgis.dev.hibernateHelper.HibernateController;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zil on 2016/10/27.
 */
public class SOSFactoryImpl implements SOSFactory {

    private URL url;
    private HibernateController hibernateController = new HibernateController();

    public SOSFactoryImpl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public SOS getSOS() {
        SOS sos = new SOSResource(url).getSOS();
        SOS cache = new HibernateResource(url).getSOS();
        if( cache != null )
            updateHibernate(sos, cache);
        else
            saveHibernate(sos);
        return sos;
    }

    private void saveHibernate(SOS sos) {
        if( sos == null )
            return;
        hibernateController.save(sos);
    }

    private void updateHibernate(SOS sos, SOS cache) {
        Set<Offering> offerings = differenceOffering(sos, cache);
        cache.getOfferings().addAll(offerings);
        hibernateController.save(cache);
    }

    private Set<Offering> differenceOffering(SOS s1, SOS s2){
        Set<Offering> offerings1 = new HashSet<Offering>(s1.getOfferings());
        Set<Offering> offerings2 = new HashSet<Offering>(s2.getOfferings());
        if( offerings1.size() > offerings2.size() ){
            offerings1.removeAll(offerings2);
            return offerings1;
        } else {
            offerings2.removeAll(offerings1);
        }

        return offerings2;
    }
}
