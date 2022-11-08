package it.foptool.utility;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility class to perform some operations on files.
 * 
 * @author Simone Lungarella
 */
@Slf4j
public class FileUtility {

    /**
     * Max size chunk.
     */
    private static final int CHUNK_SIZE = 16384;

    /**
     * Searches for the filename in the map of parameters. If not found, it
     * generates a default one.
     * Appends to the filename the current local date (format: dd-MM-yyyy).
     * 
     * @param params Map of parameters to use to populate the xslt file.
     * @return The filename to use.
     */
    public static String getFilename(final Map<String, String> params) {
        // TODO: implement this method
        return "";
    }

    /**
     * Searches for the filename in the json. If not found, it generates a default
     * one.
     * Appends to the filename the current local date (format: dd-MM-yyyy).
     * 
     * @param json Json to use to populate the xslt file.
     * @return The filename to use.
     */
    public static String getFilenameFromJson(final JSONObject json) {
        // TODO: implement this method
        return "";
    }
    
    public static byte[] getFileFromInternalResources(final String filename) {
        byte[] b = null;
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
                ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            int nRead;
            byte[] data = new byte[CHUNK_SIZE];

            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            b = buffer.toByteArray();
        } catch (Exception e) {
            log.error("Error while retrieving file from internal resources", e);
            // TODO: Throw BusinessException
        }
        return b;
    }
}
