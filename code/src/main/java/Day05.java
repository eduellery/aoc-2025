import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Day05(long part1, long part2) {

    private static final Pattern RANGES_PATTERN = Pattern.compile("(\\d+)-(\\d+)");

    public Day05(String input) {
        Objects.requireNonNull(input, "input must not be null");

        final String[] blocks = input.split("\n\n", 2);
        final String rawRanges = blocks[0];
        final String[] ingredients = blocks[1].split("\n");

        Matcher matcher = RANGES_PATTERN.matcher(rawRanges);

        int count = 0;
        while (matcher.find()) count++;

        long[][] ranges = new long[count][2];
        matcher.reset();

        int idx = 0;
        while (matcher.find()) {
            ranges[idx][0] = Long.parseLong(matcher.group(1));
            ranges[idx][1] = Long.parseLong(matcher.group(2));
            idx++;
        }

        Arrays.sort(ranges, Comparator.comparingLong(a -> a[0]));

        long freshProducts = 0L;
        for (String ingredient : ingredients) {
            long value = Long.parseLong(ingredient);

            // Binary search could be used, but since range count is small,
            // linear scan is likely faster because it avoids branching costs.
            for (long[] range : ranges) {
                if (value < range[0]) break;
                if (value <= range[1]) {
                    freshProducts++;
                    break;
                }
            }
        }

        long freshIngredients = 0L;
        long currentStart = ranges[0][0];
        long currentEnd = ranges[0][1];

        for (long[] range : ranges) {
            long start = range[0];
            long end = range[1];

            if (start <= currentEnd + 1) {
                if (end > currentEnd) {
                    currentEnd = end;
                }
            } else {
                freshIngredients += (currentEnd - currentStart + 1);
                currentStart = start;
                currentEnd = end;
            }
        }

        freshIngredients += (currentEnd - currentStart + 1);

        this(freshProducts, freshIngredients);
    }
}
