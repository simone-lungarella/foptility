package it.foptool.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import it.foptool.demo.helper.FOPHelper;
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

		saveFileToResources(pdfFile, pdfFilename);
		log.info("Transformation completed, pdf file generated in resource directory");
	}

	private byte[] getFileFromResources(String filename) {
		String filepath = this.getClass().getResource("/").getPath().replace("target/test-classes/",
				"src/test/java/it/foptool/demo/resources/");

		try (InputStream is = new FileInputStream(new File(filepath + filename));) {
			return is.readAllBytes();
		} catch (Exception e) {
			log.error("Error while reading file from internal resources", e);
		}
		return null;
	}

	private void saveFileToResources(byte[] content, String filename) {

		String filepath = this.getClass().getResource("/").getPath().replace("target/test-classes/",
				"src/test/java/it/foptool/demo/resources/");

		try (BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(new File(filepath + filename)))) {
			bs.write(content);
		} catch (Exception e) {
			log.error("Error while saving content on internal resources", e);
		}

	}

}
