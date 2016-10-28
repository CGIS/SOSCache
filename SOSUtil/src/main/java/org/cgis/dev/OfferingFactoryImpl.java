package org.cgis.dev;

import org.cgis.dev.SOSUtility.Decoder.TimezoneHelper;
import org.cgis.dev.SOSUtility.XmlBuilder.ElementReader;
import org.cgis.dev.ds.ObservableProperty;
import org.cgis.dev.ds.Offering;
import org.cgis.dev.ds.SOS;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zil on 2016/10/27.
 */
public class OfferingFactoryImpl implements OfferingFactory {

    private SOS sos;
    private NodeList offeringList;
    private Set<Offering> offeringSet = new HashSet<Offering>();

    public OfferingFactoryImpl(Document capability) {
        this.offeringList = capability.getElementsByTagName("swes:offering");
    }

    public Set<Offering> getOfferings() {
        for( int i = 0 ; i < offeringList.getLength(); i++ ){
            Offering offering = new Offering();
            ElementReader reader = new ElementReader((Element) offeringList.item(i));

            // create offering
            offering.setSos(sos);
            offering.setName(reader.textOf("swes:identifier"));
            offering.setProcedure(reader.textOf("swes:procedure"));
            setObservableProperty(offering, reader.setOf("swes:observableProperty"));

            offering.setLowerCorner(reader.textOf("gml:lowerCorner"));
            offering.setUpperCorner(reader.textOf("gml:upperCorner"));

            Date beginPosition = timeZonize(reader.textOf("gml:beginPosition"));
            offering.setBeginPosition( beginPosition );

            Date endPosition = timeZonize(reader.textOf("gml:endPosition"));
            offering.setEndPosition( endPosition );
            offeringSet.add(offering);
        }
        return offeringSet;
    }

    private Date timeZonize(String timePosition){
        Date timezone = null;
        try {
             timezone = TimezoneHelper.toTimezone(timePosition);
        } catch (ParseException e) {
        }
        return timezone;
    }

    public void setSos(SOS sos) {
        this.sos = sos;
    }

    public void setObservableProperty(Offering offering, Set<ElementReader> elementReaders) {
        for( ElementReader reader: elementReaders ){
            ObservableProperty o = new ObservableProperty();
            o.setName(reader.text());
            offering.getObservableProperties().add(o);
        }
    }
}
