package com.itmo.study.verification;

import java.io.*;
import java.util.stream.Collectors;

public class Resources {
    public static String load(String fileName) throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(Resources.class.getResourceAsStream("/" + fileName)))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
