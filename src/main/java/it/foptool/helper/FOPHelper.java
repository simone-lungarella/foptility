package it.foptool.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.json.JSONObject;
import org.json.XML;

import lombok.extern.slf4j.Slf4j;

/**
 * Helper FOP.
 * 
 * @author Simone Lungarella
 */
@Slf4j
public final class FOPHelper {

    /**
     * Transformer Factory.
     */
    private static TransformerFactory tFactory = TransformerFactory.newInstance();

    /**
     * Fop Factory.
     */
    private static FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

    /**
     * Empty constructor.
     */
    private FOPHelper() {
        // Constructor that hides the public and default constructor.
    }

    /**
     * Transforms into pdf the {@code xslt} file populating its parameter using
     * values in the map: {@code hm}.
     * 
     * @param params Map of parameters to use to populate the xslt file.
     * @param xslt   Template fop to generate the pdf from.
     * @return Byte array of pdf generated.
     */
    public static byte[] transformMAP2PDF(final Map<String, String> params, final byte[] xslt) {
        try {
            return transformSRC2PDF(getXMLSourceFromMAP(params), xslt);
        } catch (FOPException | IOException | TransformerException e) {
            log.info("Error while generating pdf");
        }
        return new byte[0];
    }

    private static Source getXMLSourceFromMAP(final Map<String, String> hm) {
        final StringBuilder result = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        result.append("<parameters>");
        for (final Entry<String, String> e : hm.entrySet()) {
            result.append("<" + e.getKey() + ">");
            result.append(StringEscapeUtils.escapeXml10(e.getValue()));
            result.append("</" + e.getKey() + ">");
        }
        result.append("</parameters>");
        final String str = result.toString();
        log.info("Marshalling per FOP: " + str);
        return new StreamSource(new StringReader(str));
    }

    /**
     * Transforms into pdf the {@code xslt} file populating its parameter using
     * values in the json: {@code rawJson}.
     * 
     * @param rawJson Json to use to populate the xslt file.
     * @param xslt   Template fop to generate the pdf from.
     * @return Byte array of pdf generated.
     */
    public static byte[] transformJson2PDF(final String rawJson, final byte[] xslt) {
        try {
            return transformSRC2PDF(getXMLSourceFromJson(rawJson), xslt);
        } catch (FOPException | IOException | TransformerException e) {
            log.info("Error while generating pdf");
        }
        return new byte[0];
    }

    private static Source getXMLSourceFromJson(final String rawJson) {
        final JSONObject json = new JSONObject(rawJson);
        final String xml = XML.toString(json);
        return new StreamSource(new StringReader(xml.toString()));
    }

    private static byte[] transformSRC2PDF(final Source srcXML, final byte[] xslt) throws FOPException, IOException, TransformerException {
        final InputStream streamXSLT = new ByteArrayInputStream(xslt);
        final Source srcXSLT = new StreamSource(streamXSLT);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
        final Result res = new SAXResult(fop.getDefaultHandler());
        final Transformer transformer = tFactory.newTransformer(srcXSLT);
        transformer.transform(srcXML, res);
        out.flush();
        out.close();
        return out.toByteArray();
    }

}
