package org.cgis.dev.SOSUtility.Decoder;

import org.cgis.dev.SOSUtility.Factory.ObservationFactory;
import org.cgis.dev.SOSUtility.SOSRequest;
import org.cgis.dev.SOSUtility.XmlBuilder.XmlBuilder;
import org.cgis.dev.ds.Observation;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Zil on 2016/9/20.
 */
public class SosGetObservationDecoder {

    private String url;
    private String xml;
    private HashSet<Observation> set = new HashSet<Observation>();
    private final Logger _log = Logger.getLogger(SosGetObservationDecoder.class.getName());

    public SosGetObservationDecoder(String url, String request_xml) {
        this.url = url;
        this.xml = request_xml;
    }

    public HashSet<Observation> decode(){
        String response = "";
        try {
            response = getObservationDocument();
        } catch (IOException e) {
            _log.log(Level.OFF, "Server Cant' get Observation Response.");
            return this.set;
        }
        parse(response);
        return this.set;
    }

    private String getObservationDocument() throws IOException {
        return SOSRequest.post(this.url, this.xml);
    }

    private void parse(String xml){
        try {
            XmlBuilder xmlBuilder = new XmlBuilder(xml);
            NodeList observationList = xmlBuilder.getList("om:OM_Observation");
            for( int i = 0 ; i < observationList.getLength(); i++ ){
                Element observationElement = (Element) observationList.item(i);
                ObservationFactory observationFactory = new ObservationFactory(url);
                Observation observation = observationFactory.getObservation(observationElement);
                set.add(observation);
            }
        } catch (ParserConfigurationException e) {
            _log.log(Level.OFF, "Problem with Parse Observation");
        } catch (SAXException e) {
            _log.log(Level.OFF, "Problem with Parse Observation");
        } catch (IOException e) {
            _log.log(Level.OFF, "Problem with Parse Observation");
        }
    }
}
