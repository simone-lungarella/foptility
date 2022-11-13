package it.foptool;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import it.foptool.helper.FOPHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * PDF Creator test class.
 * 
 * @author Simone Lungarella
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class PDFCreatorTest {

	/**
	 * Author placeholder on xslt file.
	 */
	private final String AUTHOR_PH = "author";

	/**
	 * Date placeholder on xslt file.
	 */
	private final String DATE_PH = "today-date";

	/**
	 * Notes placeholder on xslt file.
	 */
	private final String NOTES_PH = "notes";

	/**
	 * Header placeholder on xslt file.
	 */
	private final String HEADER_PH = "header";

	@Test
	void xsltToPdf() {
		final String xsltFilename = "xsltExample.xslt";
		final String pdfFilename = "pdfExample.pdf";

		log.info("Recupero file");
		byte[] xsltFile = getFileFromResources(xsltFilename);
		assertNotNull(xsltFile, "Xslt file not retrieved correctly");

		log.info("Params definition");
		final String author = "Simone Lungarella";
		final String header = "XSLT Template header of example";
		final String notes = "Random text injected from code";
		final String today_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		Map<String, String> params = new HashMap<>();
		params.put(AUTHOR_PH, author);
		params.put(DATE_PH, today_date);
		params.put(NOTES_PH, notes);
		params.put(HEADER_PH, header);

		log.info("Generating PDF");

		byte[] pdfFile = FOPHelper.transformMAP2PDF(params, xsltFile);
		assertNotNull(pdfFile, "PDF file not generated correctly");

		File file = new File("src/main/resources/" + pdfFilename);
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
			bos.write(pdfFile);
		} catch (Exception e) {
			log.error("Error while saving file", e);
		}
		
		log.info("Transformation completed, pdf file generated in resource directory");
	}

	@Test
	void phaseOneTest() throws JSONException {
		final String xsltFilename = "xslt/phase1.xslt";
		final String pdfFilename = "pdfPhase1.pdf";

		byte[] xsltFile = getFileFromResources(xsltFilename);
		assertNotNull(xsltFile, "Xslt file not retrieved correctly");

		final String json = "{\"parameters\": {\"footer\": {\"subtitle\": \"Sottotitolo\", \"title\": \"Titolo\"}, \"items\": [{\"item\": {\"isCompliant\": true, \"kg\": \"Test\", \"lot\": \"Test\", \"origin\": \"Autoproduzione\", \"plants\": \"Test\"}}, {\"item\": {\"isCompliant\": true, \"kg\": \"TEst\", \"lot\": \"Test\", \"origin\": \"Autoproduzione\", \"plants\": \"Test\"}}]}}";
		
		byte[] pdfFile = FOPHelper.transformJson2PDF(json, xsltFile);
		assertNotNull(pdfFile, "PDF file not generated correctly");

		File file = new File("src/main/resources/" + pdfFilename);
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
			bos.write(pdfFile);
		} catch (Exception e) {
			log.error("Error while saving file", e);
		}
		
		log.info("Transformation completed, pdf file generated in resource directory");
	}

	private byte[] getFileFromResources(String filename) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		try (InputStream is = classLoader.getResourceAsStream(filename);) {
			return is.readAllBytes();
		} catch (Exception e) {
			log.error("Error while reading file from internal resources", e);
		}
		return null;
	}

}
