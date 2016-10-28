package org.cgis.dev.Request;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.URL;

/**
 * Created by zil on 2016/10/27.
 */
public class XmlRequest {

    private final URL url;
    private final Request request;
    private final DocumentBuilder xmlBuilder;

    public XmlRequest(String url) throws Exception {
        this.url =  new URL(url);
        this.request = new Request(url);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        xmlBuilder = dbf.newDocumentBuilder();
    }

    public Document get() throws Exception {
        return xmlBuilder.parse(url.openStream());
    }

    public Document post(String xml) throws Exception {
        String response = request.post(xml);
        return xmlBuilder.parse(new InputSource(new StringReader(response)));
    }
}
