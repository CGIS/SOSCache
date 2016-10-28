package org.cgis.dev.SOSUtility;

/**
 * Created by Zil on 2016/9/19.
 */
public class SOSOpreationXml {

    public static String GET_CAPABILITIES_XML = "<sos:GetCapabilities\n" +
            "        xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n" +
            "        xmlns:ows=\"http://www.opengis.net/ows/1.1\"\n" +
            "        xmlns:swe=\"http://www.opengis.net/swe/2.0\" service=\"SOS\"\n" +
            "        xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "        xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sosGetCapabilities.xsd\">\n" +
            "    <ows:AcceptVersions>\n" +
            "        <ows:Version>2.0.0</ows:Version>\n" +
            "    </ows:AcceptVersions>\n" +
            "    <ows:Sections>\n" +
            "        <ows:Section>OperationsMetadata</ows:Section>\n" +
            "        <ows:Section>ServiceIdentification</ows:Section>\n" +
            "        <ows:Section>ServiceProvider</ows:Section>\n" +
            "        <ows:Section>FilterCapabilities</ows:Section>\n" +
            "        <ows:Section>Contents</ows:Section>\n" +
            "    </ows:Sections>\n" +
            "</sos:GetCapabilities>";

    public static String GET_OBSERVATION_XML_TEMPLATE = "<sos:GetObservation\n" +
            "            xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n" +
            "            xmlns:fes=\"http://www.opengis.net/fes/2.0\"\n" +
            "            xmlns:gml=\"http://www.opengis.net/gml/3.2\"\n" +
            "            xmlns:swe=\"http://www.opengis.net/swe/2.0\"\n" +
            "            xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
            "            xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "            xmlns:swes=\"http://www.opengis.net/swes/2.0\" service=\"SOS\" version=\"2.0.0\" xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sos.xsd\">\n" +
            "\n" +
            "        </sos:GetObservation>";

    public static String GET_FEATURE_OF_INTEREST_TEMPLATE = " <sos:GetFeatureOfInterest\n" +
            "            xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "            xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n" +
            "            xmlns:fes=\"http://www.opengis.net/fes/2.0\"\n" +
            "            xmlns:gml=\"http://www.opengis.net/gml/3.2\"\n" +
            "            xmlns:swe=\"http://www.opengis.net/swe/2.0\"\n" +
            "            xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
            "            xmlns:swes=\"http://www.opengis.net/swes/2.0\" service=\"SOS\" version=\"2.0.0\" xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sos.xsd\">\n" +
            "\n" +
            "        </sos:GetFeatureOfInterest>";
}
