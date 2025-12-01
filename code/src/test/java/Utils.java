import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public final class Utils {

    private static final Pattern TAB   = Pattern.compile("\\t");
    private static final Pattern COMMA = Pattern.compile(",");

    private Utils() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static List<String> readLines(String fileName, Class<?> context) {
        Objects.requireNonNull(fileName, "fileName must not be null");
        Objects.requireNonNull(context, "context class must not be null");

        try (InputStream inputStream = Objects.requireNonNull(
                     context.getResourceAsStream(fileName),
                     () -> "Resource not found: " + fileName + " (relative to " + context.getName() + ')');
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            return reader.lines().toList();
        } catch (IOException e) {
            throw new UncheckedIOException("Error reading file: " + fileName, e);
        }
    }

    public static String readLine(String fileName, Class<?> context) {
        List<String> lines = readLines(fileName, context);

        if (lines.size() != 1) {
            throw new IllegalStateException(
                    "Expected exactly one line in file: " + fileName + ", but found " + lines.size()
            );
        }

        return lines.getFirst();
    }

    public static List<Integer> readLinesAsInt(String fileName, Class<?> context) {
        return readLines(fileName, context).stream()
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .toList();
    }

    public static int readLineAsInt(String fileName, Class<?> context) {
        return Integer.parseInt(readLine(fileName, context).trim());
    }

    public static List<Integer> readLineAsIntList(String fileName, Class<?> context) {
        String line = readLine(fileName, context);
        return TAB.splitAsStream(line)
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .toList();
    }

    public static List<String> readLineAsStringList(String fileName, Class<?> context) {
        String line = readLine(fileName, context);
        return COMMA.splitAsStream(line)
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();
    }

    public static String read(String fileName, Class<?> context) {
        List<String> lines = readLines(fileName, context);
        return String.join("\n", lines);
    }

}

