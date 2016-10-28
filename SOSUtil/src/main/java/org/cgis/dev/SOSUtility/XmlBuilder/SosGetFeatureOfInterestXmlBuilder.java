package org.cgis.dev.SOSUtility.XmlBuilder;

import org.cgis.dev.SOSUtility.SOSOpreationXml;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Zil on 2016/9/20.
 */
public class SosGetFeatureOfInterestXmlBuilder extends XmlBuilder {

    public SosGetFeatureOfInterestXmlBuilder() throws ParserConfigurationException, IOException, SAXException {
        super(SOSOpreationXml.GET_FEATURE_OF_INTEREST_TEMPLATE);

    }

    public void add(String featureOfInterest){
        Element element = doc.createElement("sos:featureOfInterest");
        element.setTextContent(featureOfInterest);
        root.appendChild(element);
    }

    public void remove(String featureOfInterest){

    }


}
