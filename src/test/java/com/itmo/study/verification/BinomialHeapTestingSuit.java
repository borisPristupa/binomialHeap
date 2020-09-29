package com.itmo.study.verification;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BinomialHeapTestingSuit {
    private ByteArrayOutputStream baos;
    private PrintStream stdOut;
    private String lineSep;

    public void init() {
        baos = new ByteArrayOutputStream();

        stdOut = System.out;
        PrintStream out = new PrintStream(baos, true);
        System.setOut(out);
//        System.setProperty("line.separator", "\n");
    }

    public void exit() {
        System.setOut(stdOut);
//        System.setProperty("line.separator", System.lineSeparator());
    }

    public void assertOutputMatches(String outputFileName) {
        String output = baos.toString();//.replaceAll("\n", System.getProperty("line.separator"));
        String expected;
        try {
            expected = Resources.load(outputFileName).replaceAll("\n", System.getProperty("line.separator"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        assertEquals(expected.replaceAll("\\s", ""), output.replaceAll("\\s", ""));
        assertEquals(expected.trim(), output.trim());
    }
}
