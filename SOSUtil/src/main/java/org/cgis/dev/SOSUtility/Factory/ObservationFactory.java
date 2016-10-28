package org.cgis.dev.SOSUtility.Factory;

import org.cgis.dev.ds.Observation;
import org.w3c.dom.Element;

import java.util.logging.Logger;

/**
 * Created by zil on 2016/9/21.
 */
public class ObservationFactory {

    private final Logger _log = Logger.getLogger(ObservationFactory.class.getName());
    private String url;

    public ObservationFactory(String url){
        this.url = url;
    }

    public Observation getObservation(Element observationElement){
        Observation observation = new Observation();
        // set phenomenonTime
        String time = getFirstNodeText(observationElement, "gml:timePosition");
//        try {
//            observation.setPhenomenonTime( TimezoneHelper.toTimezone(time) );
//        } catch (ParseException e) {
//            _log.log(Level.OFF, "Problem with Parse Observation in Timezone");
//        }
//        // set feature with no location;
//        FeatureOfInterest featureOfInterest = new FeatureOfInterest();
//        featureOfInterest.setName(getFirstNodeAttribute(observationElement, "om:featureOfInterest", "xlink:href"));
//        observation.setFeatureOfInterest(featureOfInterest);
//        // send request to get featureOfInterest location
//        SosGetFeatureOfInterestDecoder sosGetFeatureOfInterestDecoder = new SosGetFeatureOfInterestDecoder(url);
//        String[] locationByObservation = sosGetFeatureOfInterestDecoder.getLocationByObservation(observation);
//        observation.getFeatureOfInterest().setLongitude(locationByObservation[0]);
//        observation.getFeatureOfInterest().setLatitude(locationByObservation[1]);
//        // set observableProperty
//        ObservableProperty observableProperty = new ObservableProperty();
//        observableProperty.setName(getFirstNodeAttribute(observationElement, "om:observedProperty", "xlink:href"));
//        observation.setObservableProperty(observableProperty);
        // set result
        observation.setResult(getFirstNodeText(observationElement, "om:result"));
        return observation;
    }

    private String getFirstNodeText(Element element, String tagName){
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }

    private String getFirstNodeAttribute(Element element, String tagName, String attributeName){
        return element.getElementsByTagName(tagName).item(0).getAttributes().getNamedItem(attributeName).getNodeName();

    }
}
