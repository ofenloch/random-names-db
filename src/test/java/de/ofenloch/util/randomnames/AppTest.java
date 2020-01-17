package de.ofenloch.util.randomnames;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.ofenloch.util.randomnames.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    protected static final Logger log = LogManager.getLogger(AppTest.class.getName());

    /**
     * the directory where the tests read their input data with tailing slash
     * ('cause it makes life easier)
     */
    static final String ReferenceData = "./data";
    /**
     * the directory where the tests write genrated result files * with tailing
     * slash ('cause it makes life easier)
     */
    static final String TestOutputData = "./testoutput";
    /**
     * are we in our orinal ${workspaceFolder}?
     */
    private boolean InOriginalWorkspaceFolder = true;
    /**
     * the value of ${workspaceFolder} where the sanctioned output was generated
     */
    private final String originalWorkspaceFolder = "/home/ofenloch/workspaces/java/randomnames";
    private final String originalWorkspaceFolderAsRegExp = "\\/home\\/ofenloch\\/workspaces\\/java\\/randomnames";
    /**
     * the value of ${workspaceFolder} where the the tests are currently run
     */
    private String currentWorkspaceFolder = "";
    private String currentWorkspaceFolderAsRegExp = "";

    public AppTest() {
        try {
            // make sure output directory exists:
            Path testdir = Paths.get(TestOutputData);
            if (!Files.exists(testdir)) {
                Files.createDirectory(testdir);
                log.debug("AppTest: created test result directory \"" + TestOutputData + "\"");
            }
            // get the current ${workspaceFolder}
            currentWorkspaceFolder = System.getProperty("user.dir");
            currentWorkspaceFolderAsRegExp = currentWorkspaceFolder.replaceAll("\\/", "\\\\/");
            log.debug("AppTest: ${workspaceFolder} is \"" + currentWorkspaceFolder + "\"");
            if (currentWorkspaceFolder.compareTo(originalWorkspaceFolder) == 0) {
                log.info("AppTest: running in original ${workspaceFolder}");
            } else {
                log.info("AppTest: running in a different ${workspaceFolder}");
                InOriginalWorkspaceFolder = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } // public AppTest

    /**
     * Rigorous Test.
     */
    @Test
    public void testrandomnames() {
        String args[] = { "20", TestOutputData + "/randomnames20.sql" };

        randomnames.resetRandomizer();
        randomnames.main(args);
        assertTrue(TextFilesAreEqual(args[1], ReferenceData + "/randomnames20.sql"));

        args[0] = "20000";
        args[1] = TestOutputData + "/randomnames20000.sql";
        randomnames.resetRandomizer();
        randomnames.main(args);
        assertTrue(TextFilesAreEqual(args[1], ReferenceData + "/randomnames20000.sql"));

        args[0] = "100000";
        args[1] = TestOutputData + "/randomnames100000.sql";
        randomnames.resetRandomizer();
        randomnames.main(args);
        assertTrue(TextFilesAreEqual(args[1], ReferenceData + "/randomnames100000.sql"));

    }

    static public boolean FilesAreBinaryEqual(String firstName, String secondName) {
        log.debug("FilesAreBinaryEqual: firstName \"" + firstName + "\"");
        log.debug("FilesAreBinaryEqual: secondName \"" + secondName + "\"");
        long start = System.nanoTime();
        try {
            FileChannel ch1 = new RandomAccessFile(firstName, "r").getChannel();
            FileChannel ch2 = new RandomAccessFile(secondName, "r").getChannel();
            if (ch1.size() != ch2.size()) {
                log.debug("Files have different length.");
                ch1.close();
                ch2.close();
                return false;
            }
            long size = ch1.size();
            ByteBuffer m1 = ch1.map(FileChannel.MapMode.READ_ONLY, 0L, size);
            ByteBuffer m2 = ch2.map(FileChannel.MapMode.READ_ONLY, 0L, size);
            for (int pos = 0; pos < size; pos++) {
                if (m1.get(pos) != m2.get(pos)) {
                    log.debug("Files differ at position " + pos + ".");
                    ch1.close();
                    ch2.close();
                    return false;
                }
            }
            ch1.close();
            ch2.close();
            log.debug("Files are identical, you can delete one of them.");
            long end = System.nanoTime();
            log.debug("Execution time: " + (end - start) / 1000000 + "ms");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    static public boolean TextFilesAreEqual(String firstName, String secondName) {
        log.debug("TextFilesAreEqual: firstName \"" + firstName + "\"");
        log.debug("TextFilesAreEqual: secondName \"" + secondName + "\"");
        boolean areEqual = true;
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(firstName));
            BufferedReader reader2 = new BufferedReader(new FileReader(secondName));

            String line1 = reader1.readLine();
            String line2 = reader2.readLine();

            int lineNum = 1;
            while (line1 != null || line2 != null) {
                if (line1 == null || line2 == null) {
                    areEqual = false;
                    break;
                } else if (line1.compareTo(line2) != 0) {
                    areEqual = false;
                    break;
                }
                line1 = reader1.readLine();
                line2 = reader2.readLine();
                lineNum++;
            }
            if (areEqual) {
                log.debug("Two files have same content.");
            } else {
                log.debug("Two files have different content. They differ at line " + lineNum);
                log.debug("File1 has " + line1 + " and File2 has " + line2 + " at line " + lineNum);
            }
            reader1.close();
            reader2.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            areEqual = false;
        }
        return areEqual;
    }

}
