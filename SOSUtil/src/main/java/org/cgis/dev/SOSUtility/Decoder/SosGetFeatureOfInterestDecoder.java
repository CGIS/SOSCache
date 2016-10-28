package org.cgis.dev.SOSUtility.Decoder;

import org.cgis.dev.SOSUtility.XmlBuilder.SosGetFeatureOfInterestXmlBuilder;
import org.cgis.dev.ds.Observation;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Zil on 2016/9/20.
 */
public class SosGetFeatureOfInterestDecoder {

    private final Logger _log = Logger.getLogger(SosGetFeatureOfInterestDecoder.class.getName());
    private String url;
    private HashMap<String, String> feature = new HashMap<String, String>();

    public SosGetFeatureOfInterestDecoder(String url){
        this.url = url;
    }

    public void parse(String xml){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            Document doc = builder.parse(is);
            NodeList gmlId = doc.getElementsByTagName("gml:identifier");
            String featureName = gmlId.item(0).getTextContent();
            NodeList shape = doc.getElementsByTagName("gml:pos");
            String location = shape.item(0).getTextContent();
            feature.put(featureName, location);
        } catch (ParserConfigurationException e) {
            _log.log(Level.OFF, "Problem with Parse SOS");
        } catch (SAXException e) {
            _log.log(Level.OFF, "Problem with Parse SOS");
        } catch (IOException e) {
            _log.log(Level.OFF, "Problem with Parse SOS");
        }
    }

    public String[] getLocationByObservation(Observation observation){
        String featureName = "";
//        String featureName = observation.getFeatureOfInterest().getName();
        String featureLocation = feature.get(featureName);
        if( featureLocation != null )
            return featureLocation.split(" ");
        try {
            String featureOfInterestDocument = getFeatureOfInterestDocument(featureName);
            parse(featureOfInterestDocument);
        } catch (IOException ignored) {
        } catch (ParserConfigurationException ignored) {
        } catch (SAXException ignored) {
        }
        return featureLocation.split(" ");
    }

    private String getFeatureOfInterestDocument(String feature) throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL(this.url);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        SosGetFeatureOfInterestXmlBuilder sosGetFeatureOfInterestXmlBuilder = new SosGetFeatureOfInterestXmlBuilder();
        sosGetFeatureOfInterestXmlBuilder.add(feature);
        String body = sosGetFeatureOfInterestXmlBuilder.toXML();
        OutputStream output = new BufferedOutputStream(urlConnection.getOutputStream());
        output.write(body.getBytes());
        output.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuilder builder = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            builder.append(inputLine);
        in.close();
        return builder.toString();
    }
}
