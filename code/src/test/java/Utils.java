import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.util.*;
import java.util.stream.*;

public class Utils {

    public static List<String> readLines(String fileName, Class<?> clazz) {
        try (InputStream inputStream = clazz.getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().toList();
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Error reading the file: " + fileName, e);
        }
    }

    public static String readLine(String fileName, Class<?> clazz) {
        List<String> lines = readLines(fileName, clazz);

        if (lines.size() != 1) {
            throw new RuntimeException("Content is not one line in file: " + fileName);
        }

        return lines.get(0);
    }

    public static List<Integer> readLinesAsInt(String fileName, Class<?> clazz) {
        return readLines(fileName, clazz).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int readLineAsInt(String fileName, Class<?> clazz) {
        return Integer.parseInt(readLine(fileName, clazz));
    }

    public static List<Integer> readLineAsIntList(String fileName, Class<?> clazz) {
        return Arrays.stream(readLine(fileName, clazz).split("\\t"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<String> readLineAsStringList(String fileName, Class<?> clazz) {
        return Arrays.asList(readLine(fileName, clazz).split(","));
    }

    public static String read(String fileName, Class<?> clazz) {
        return readLines(fileName, clazz).stream().collect(Collectors.joining("\n"));
    }

}
