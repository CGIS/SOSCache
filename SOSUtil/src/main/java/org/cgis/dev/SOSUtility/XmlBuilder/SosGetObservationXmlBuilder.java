package org.cgis.dev.SOSUtility.XmlBuilder;

import org.cgis.dev.SOSUtility.SOSOpreationXml;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zil on 2016/9/20.
 */
public class SosGetObservationXmlBuilder extends XmlBuilder {


    public SosGetObservationXmlBuilder() throws IOException, SAXException, ParserConfigurationException {
        super(SOSOpreationXml.GET_OBSERVATION_XML_TEMPLATE);
    }

    public void add(String offering){
        Element element = doc.createElement("sos:offering");
        element.setTextContent(offering);
        root.appendChild(element);
    }

    public void add(Date start, Date end){
        DateFormat df = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss.SSSZ");
        String beginPosition = df.format(start);
        String endPosition = df.format(end);

        Element temporal = doc.createElement("sos:temporalFilter");
        Element during = doc.createElement("fes:During");
        Element valueRef = doc.createElement("fes:ValueReference");
        Element timePeriod = doc.createElement("gml:TimePeriod");
        Element beginTime = doc.createElement("gml:beginPosition");
        beginTime.setTextContent(beginPosition);
        Element endTime = doc.createElement("gml:endPosition");
        endTime.setTextContent(endPosition);
        timePeriod.appendChild(beginTime);
        timePeriod.appendChild(endTime);
        during.appendChild(timePeriod);
        during.appendChild(valueRef);
        temporal.appendChild(during);
    }

    public void remove(String offering){

    }

}
