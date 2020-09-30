package com.itmo.study.verification;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BinomialHeapTestingSuit {
    private ByteArrayOutputStream baos;
    private PrintStream stdOut;

    public void init() {
        baos = new ByteArrayOutputStream();

        stdOut = System.out;
        System.setProperty("line.separator", "\n");

        PrintStream out = new PrintStream(baos, true);
        System.setOut(out);
    }

    public void exit() {
        System.setOut(stdOut);
        System.setProperty("line.separator", System.lineSeparator());
    }

    public void assertOutputMatches(String outputFileName) {
        String output = baos.toString();
        String expected;
        try {
            expected = Resources.load(outputFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expected.trim(), output.trim());
    }
}
