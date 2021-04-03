package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.FileFormatsForReading.*;

public class ReadFiles {

    String xlsxFile = "src/test/resources/files/xlsx.xlsx",
            pdfFile = "src/test/resources/files/pdf.pdf",
            zipFile = "src/test/resources/files/zip.zip",
            expectedValue = "Документ для тестирования к домашке номер 6",
            unzipFolderPath = "src/test/resources/files/unzip",
            zipPassword = "1qaz2wsx",
            unzipTxtFilePath = "src/test/resources/files/unzip/pdf.pdf";
    File docxFile = new File("src/test/resources/files/docx.docx");

    @Test
    void xlsxTest() {
        XLS xlsx = getXls(xlsxFile);
        assertThat(xlsx, XLS.containsText(expectedValue));
    }

    @Test
    void pdfTest() throws IOException {
        PDF pdf = getPdf(pdfFile);
        assertThat(pdf, PDF.containsText(expectedValue));
    }

    @Test
    void zipWithPasswordTest() throws IOException, ZipException {
        unzip(zipFile, unzipFolderPath, zipPassword);
        PDF actualData = getPdf(unzipTxtFilePath);
        assertThat(actualData, PDF.containsText(expectedValue));
    }

    @Test
    void docTest() throws IOException {
        String doc = getDoc(docxFile);
        assertTrue(doc.contains(expectedValue));
    }

}
