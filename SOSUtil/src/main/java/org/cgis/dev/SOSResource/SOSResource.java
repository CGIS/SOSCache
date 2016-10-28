package org.cgis.dev.SOSResource;

import org.cgis.dev.OfferingFactory;
import org.cgis.dev.OfferingFactoryImpl;
import org.cgis.dev.Request.XmlRequest;
import org.cgis.dev.SOSFactory;
import org.cgis.dev.SOSUtility.Decoder.TimezoneHelper;
import org.cgis.dev.ds.ObservableProperty;
import org.cgis.dev.ds.Offering;
import org.cgis.dev.ds.SOS;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zil on 2016/9/19.
 */
public class SOSResource implements Resource {

    private URL url;
    private SOS sos = new SOS();

    public SOSResource(URL url) {
        this.url = url;
    }

    public SOSResource(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public SOS getSOS(String url) {
        return null;
    }

    public SOS getSOS() {
        Document cap = getCapabilityDOM();

        if( cap == null )
            return null;

        sos.setUrl(url.toString());
        sos.getOfferings().addAll( getOfferings(cap) );
        return sos;
    }

    private Set<Offering> getOfferings(Document cap){
        OfferingFactory offeringFactory = new OfferingFactoryImpl(cap);
        return offeringFactory.getOfferings();
    }

    public Offering getOffering(String name) {
        if( sos.getUrl() == null )
            getSOS();

        Set<Offering> offerings = sos.getOfferings();
        for( Offering offering: offerings)
            if (offering.getName() == name) return offering;
        return null;
    }

    public void close() {

    }

    private Document getCapabilityDOM() {
        try {
            XmlRequest xmlRequest = new XmlRequest(url.toString() + "?service=SOS&request=GetCapabilities");
            return xmlRequest.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
