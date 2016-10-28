import junit.framework.TestCase;
import org.cgis.dev.SOSUtility.XmlBuilder.SosGetObservationXmlBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Zil on 2016/9/20.
 */
public class SosGetObservationXmlBuilderTest extends TestCase {

    public void testAddOffering() throws IOException, SAXException, ParserConfigurationException {
        SosGetObservationXmlBuilder sosGetObservationXmlBuilder = new SosGetObservationXmlBuilder();
        sosGetObservationXmlBuilder.add("三義");
        System.out.println(sosGetObservationXmlBuilder.toXML());
    }
}
