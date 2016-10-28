package org.cgis.dev.SOSUtility;

import org.cgis.dev.SOSUtility.Decoder.SosGetObservationDecoder;
import org.cgis.dev.SOSUtility.XmlBuilder.SosGetObservationXmlBuilder;
import org.cgis.dev.ds.Offering;
import org.cgis.dev.ds.SOS;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Used to deserialized SOS source.
 * Created by Zil on 2016/9/17.
 */
public class SOSAdapter {

    private String url;
    private SOS sos;
    long day = 1000 * 60 * 60 * 24;
    private final Logger _log = Logger.getLogger(SOSAdapter.class.getName());

    public SOSAdapter(String url){
        this.sos = new SOS();
        this.url = url;
    }

    public SOS getSos() {
        return sos;
    }

    public SOS getCapabilities() throws IOException {
        return null;
    }

    public void getAllObservation() throws ParserConfigurationException, SAXException, IOException {
        for( Offering offering: sos.getOfferings() ){
            SosGetObservationXmlBuilder sosGetObservationXmlBuilder = new SosGetObservationXmlBuilder();
            sosGetObservationXmlBuilder.add(offering.getName());
            SosGetObservationDecoder sosGetObservationDecoder = new SosGetObservationDecoder(url, sosGetObservationXmlBuilder.toXML());
            offering.setObservations(sosGetObservationDecoder.decode());
        }
    }

//    public void getObservation(int dateRange) throws ParserConfigurationException, SAXException, IOException {
//        for( Offering offering: sos.getOfferings() ){
//            SosGetObservationXmlBuilder sosGetObservationXmlBuilder = new SosGetObservationXmlBuilder();
//            sosGetObservationXmlBuilder.add(offering.getName());
//            Date startTime = new Date(offering.getEndTime().getTime() - dateRange * day);
//            Date endTime = offering.getEndTime();
//            sosGetObservationXmlBuilder.add(startTime, endTime);
//            SosGetObservationDecoder sosGetObservationDecoder = new SosGetObservationDecoder(url, sosGetObservationXmlBuilder.toXML());
//            offering.setObservations(sosGetObservationDecoder.decode());
//        }
//    }




}
