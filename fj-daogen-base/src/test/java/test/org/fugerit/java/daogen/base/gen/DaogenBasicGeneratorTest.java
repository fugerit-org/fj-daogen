package test.org.fugerit.java.daogen.base.gen;

import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;
import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;

public class DaogenBasicGeneratorTest {

    private File tempFile;
    private StringWriter sw;
    private PrintWriter pw;

    @Before
    public void setUp() {
        sw = new StringWriter();
        pw = new PrintWriter(sw);
    }

    @After
    public void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    private void writeToFile(String content) throws IOException {
        tempFile = File.createTempFile("test", ".txt");
        try (FileWriter fw = new FileWriter(tempFile)) {
            fw.write(content);
        }
    }

    @Test
    public void testFileNotExists() throws IOException {
        File file = new File("nonexistent.txt");
        DaogenBasicGenerator.customPartWorkerDaogen(file, pw, "/* START CUSTOM */", "/* END CUSTOM */", "\t", "// default code");
        pw.flush();
        String result = sw.toString();
        assertTrue(result.contains("/* START CUSTOM */"));
        assertTrue(result.contains("// default code"));
        assertTrue(result.contains("/* END CUSTOM */"));
    }

    @Test
    public void testFileExistsNoTags() throws IOException {
        writeToFile("linea 1\nlinea 2\n");
        DaogenBasicGenerator.customPartWorkerDaogen(tempFile, pw, "/* START CUSTOM */", "/* END CUSTOM */", "\t", "// default code");
        pw.flush();
        String result = sw.toString();
        assertTrue(result.contains("// default code"));
    }

    @Test
    public void testFileWithTagsEmpty() throws IOException {
        writeToFile("/* START CUSTOM */\n/* END CUSTOM */\n");
        DaogenBasicGenerator.customPartWorkerDaogen(tempFile, pw, "/* START CUSTOM */", "/* END CUSTOM */", "\t", "// default code");
        pw.flush();
        String result = sw.toString();
        assertTrue(result.contains("// default code"));
    }

    @Test
    public void testFileWithCustomContent() throws IOException {
        writeToFile("/* START CUSTOM */\nSystem.out.println(\"Hello\");\n/* END CUSTOM */\n");
        DaogenBasicGenerator.customPartWorkerDaogen(tempFile, pw, "/* START CUSTOM */", "/* END CUSTOM */", "\t", "// default code");
        pw.flush();
        String result = sw.toString();
        assertTrue(result.contains("System.out.println(\"Hello\");"));
        assertFalse(result.contains("// default code"));
    }

    @Test
    public void testFileWithCommentedTags() throws IOException {
        writeToFile("* /* START CUSTOM */\n* codice ignorato\n* /* END CUSTOM */\n");
        DaogenBasicGenerator.customPartWorkerDaogen(tempFile, pw, "/* START CUSTOM */", "/* END CUSTOM */", "\t", "// default code");
        pw.flush();
        String result = sw.toString();
        assertTrue(result.contains("// default code"));
        assertFalse(result.contains("codice ignorato"));
    }
}

