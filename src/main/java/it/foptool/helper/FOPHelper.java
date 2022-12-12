package it.foptool.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import org.json.JSONArray;
import org.json.JSONObject;

import it.foptool.enums.ToolsEnum;
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
     * @param xslt    Template fop to generate the pdf from.
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

    public static byte[] transformJson2PDFPhase5(final String rawJson, final byte[] xslt) {
        try {
            return transformSRC2PDF(getXMLSourceFromJsonPhase5(rawJson), xslt);
        } catch (FOPException | IOException | TransformerException e) {
            log.info("Error while generating pdf");
        }
        return new byte[0];
    }

    private static Source getXMLSourceFromJsonPhase5(final String rawJson) {
        final JSONObject json = new JSONObject(rawJson);

        final StringBuilder result = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r");

        final JSONObject root = json.getJSONObject("parameters");
        result.append("<parameters>");

        addPhase5Info(root, result);
        addItemsPhase5(root, result);

        addFooter(root, result);

        result.append("</parameters>");
        return new StreamSource(new StringReader(result.toString()));
    }

    private static Source getXMLSourceFromJson(final String rawJson) {
        final JSONObject json = new JSONObject(rawJson);

        final StringBuilder result = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r");

        final JSONObject root = json.getJSONObject("parameters");
        result.append("<parameters>");

        addProductInfo(root, result);
        addLotsDefinition(root, result);
        addItems(root, result);

        addFooter(root, result);

        result.append("</parameters>");
        return new StreamSource(new StringReader(result.toString()));
    }

    private static void addLotsDefinition(JSONObject root, StringBuilder result) {

        try {
            final JSONArray lotsDefinition = root.getJSONArray("lotDefinitions");
            
            result.append("<lotDefinitions>");

            for (int i = 0; i < lotsDefinition.length(); i++) {
                final JSONObject lot = lotsDefinition.getJSONObject(i);
                result.append("<lotDefinition>");
                result.append("<name>");
                result.append(lot.getString("name"));
                result.append("</name>");
                result.append("<value>");
                result.append(lot.getString("value"));
                result.append("</value>");
                result.append("</lotDefinition>");
            }

            result.append("</lotDefinitions>");
        } catch (Exception e) {
            log.warn("No lots definition found");
        }
    }

    private static void addPhase5Info(JSONObject root, StringBuilder result) {
        result.append("<year>");
        result.append(root.getString("year"));
        result.append("</year>");

        result.append("<month>");
        result.append(root.getString("month"));
        result.append("</month>");
    }

    private static void addItemsPhase5(final JSONObject root, final StringBuilder result) {

        final JSONArray items = root.getJSONArray("items");

        for(ToolsEnum tool : ToolsEnum.values()) {
            result.append("<item>");

            result.append("<description>");
            result.append(tool.getDescription());
            result.append("</description>");
            result.append("<infos>");
            for(int j = 1; j <= 31; j++) {
                result.append("<info>");
                result.append("<day>");
                result.append(j);
                result.append("</day>");
                result.append("<isPresent>");

                result.append(checkIfToolIsPresent(items, tool, j));
                
                result.append("</isPresent>");
                result.append("</info>");
            }
            result.append("</infos>");
            result.append("</item>");
        }
    }

    private static boolean checkIfToolIsPresent(JSONArray items, ToolsEnum tool, int dayNumber) {

        boolean isPresent = false;
        for (int i = 0; i < items.length(); i++) {
            final JSONObject item = (JSONObject) items.getJSONObject(i).get("item");

            if(item.get("day").equals(dayNumber)) {

                JSONArray tools = null;
                try {
                    tools = item.getJSONArray("description");
                } catch (Exception e) {
                    isPresent = false;
                    break;
                }
                
                for (int j = 0; j < tools.length(); j++) {
                    if(tools.get(j).equals(tool.getDescription())) {
                        isPresent = true;
                        break;
                    }
                }

                if(isPresent) {
                    break;
                }
            }
        }

        return isPresent;
    }

    private static void addItems(final JSONObject root, final StringBuilder result) {
        try {

            final JSONArray items = root.getJSONArray("items");

            result.append("<items>");

            for (int i = 0; i < items.length(); i++) {
                final JSONObject item = (JSONObject) items.getJSONObject(i).get("item");

                result.append("<item>");

                // Getting keys of the item
                final JSONArray keys = item.names();
                for (int j = 0; j < keys.length(); j++) {
                    final String key = keys.getString(j);
                    String value = "";
                    final Object objValue = item.get(key);
                    if (objValue instanceof String) {
                        value = (String) objValue;
                    } else if (objValue instanceof Integer) {
                        value = String.valueOf(objValue);
                    } else if (objValue instanceof Double) {
                        value = String.valueOf(objValue);
                    } else if (objValue instanceof Boolean) {
                        value = ((Boolean) objValue) ? "Si" : "No";
                    } else if (objValue instanceof JSONArray) {
                        final JSONArray array = (JSONArray) objValue;
                        StringBuilder innerContent = new StringBuilder();

                        List<String> usedTools = IntStream
                                    .range(0, array.length())
                                    .mapToObj(array::getString)
                                    .collect(Collectors.toList());
                        
                        Arrays.asList(ToolsEnum.values()).forEach(
                            tool -> {
                                innerContent.append("<" + tool.name() + ">");
                                if (usedTools.contains(tool.getDescription())) {
                                    innerContent.append("Si");
                                } else {
                                    innerContent.append("No");
                                }
                                innerContent.append("</" + tool.name() + ">");
                            }
                        );
                        value = innerContent.toString();
                    }

                    result.append("<" + key + ">");
                    result.append(value);
                    result.append("</" + key + ">");
                }
                result.append("</item>");
            }

            result.append("</items>");
        } catch (Exception e) {
            log.warn("No items found, will be skipped");
        }
    }

    private static void addFooter(final JSONObject root, final StringBuilder result) {
        try {
            result.append("<footer>");
            result.append("<title>");

            final JSONObject footer = root.getJSONObject("footer");
            result.append(footer.getString("title"));
            result.append("</title>");

            result.append("<subtitle>");
            result.append(footer.getString("subtitle"));
            result.append("</subtitle>");
            result.append("</footer>");
        } catch (final Exception e) {
            log.warn("Footer not found, will be skipped");
        }
    }

    private static void addProductInfo(final JSONObject root, final StringBuilder result) {

        try {
            final JSONObject productInfo = root.getJSONObject("productInfo");

            if (productInfo != null) {
                result.append("<productInfo>");
                final JSONArray infos = productInfo.names();
                for (int j = 0; j < infos.length(); j++) {
                    final String key = infos.getString(j);
                    String value = "";
                    final Object objValue = productInfo.get(key);
                    if (objValue instanceof String) {
                        value = (String) objValue;
                    } else if (objValue instanceof Integer) {
                        value = String.valueOf(objValue);
                    } else if (objValue instanceof Double) {
                        value = String.valueOf(objValue);
                    } else if (objValue instanceof Boolean) {
                        value = ((Boolean) objValue) ? "Si" : "No";
                    }
                    result.append("<" + key + ">");
                    result.append(value);
                    result.append("</" + key + ">");
                }
                result.append("</productInfo>");
            }
        } catch (final Exception e) {
            log.warn("Product Info not correctly formed, if required it will be ignored");
        }
    }

    private static byte[] transformSRC2PDF(final Source srcXML, final byte[] xslt)
            throws FOPException, IOException, TransformerException {
        final InputStream streamXSLT = new ByteArrayInputStream(xslt);
        final Source srcXSLT = new StreamSource(streamXSLT);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
        final Result res = new SAXResult(fop.getDefaultHandler());
        final Transformer transformer = tFactory.newTransformer(srcXSLT);
        try {
            transformer.transform(srcXML, res);
        } catch (Exception e) {
            log.error("Error while transforming xml to pdf", e);
            throw e;
        }
        out.flush();
        out.close();
        return out.toByteArray();
    }

}
