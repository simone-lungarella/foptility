package it.foptool.utility;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

import it.foptool.exception.FilenameParsingException;
import it.foptool.exception.InternalResourceIOException;
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
     * Searches for the filename in the json. If not found, it generates a default
     * one.
     * Appends to the filename the current local date (format: dd-MM-yyyy).
     * 
     * @param json Json to use to populate the xslt file.
     * @return The filename to use.
     */
    public static String getFilenameFromJson(final JSONObject json) {
        try {
            final JSONObject root = json.getJSONObject("parameters");
    
            if (root.has("filename")) {
                return FilenameUtils.getBaseName(root.getString("filename"));
            } else {
                return "default - ";
            }
        } catch (Exception e) {
            log.error("Error while parsing filename from json", e);
            throw new FilenameParsingException("Error while parsing filename from json", e);
        }
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
            throw new InternalResourceIOException("Error while retrieving file from internal resources", e);
        }
        return b;
    }
}
