import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Day05(long part1, long part2) {

    private static final Pattern RANGES_PATTERN = Pattern.compile("(\\d+)-(\\d+)");

    public Day05(String input) {
        Objects.requireNonNull(input, "input must not be null");
        long freshProducts = 0L;
        long freshIngredients = 0L;

        String[] blocks = input.split("\n\n");
        String[] groupedRanges = blocks[0].split("\n");
        String[] ingredients = blocks[1].split("\n");

        Matcher rangesMatcher = RANGES_PATTERN.matcher(blocks[0]);
        long[][] ranges = new long[groupedRanges.length][2];

        int index = 0;
        while (rangesMatcher.find()) {
            ranges[index][0] = Long.parseLong(rangesMatcher.group(1));
            ranges[index][1] = Long.parseLong(rangesMatcher.group(2));
            index++;
        }

        Arrays.sort(ranges, (a, b) -> Long.compare(a[0], b[0]));

        for (var ingredient : ingredients) {
            long i = Long.parseLong(ingredient);
            for (var range : ranges) {
                if (i >= range[0] && i <= range[1]) {
                    freshProducts++;
                    break;
                }
            }
        }

        long freshStart = ranges[0][0];
        long freshEnd = ranges[0][1];

        for (var range : ranges) {
            long start = range[0];
            long end = range[1];

            if (start <= freshEnd + 1 && end > freshEnd) {
                freshEnd = end;
            } else if (start > freshEnd + 1) {
                freshIngredients += (freshEnd - freshStart + 1);
                freshStart = start;
                freshEnd = end;
            }
        }

        freshIngredients += (freshEnd - freshStart + 1);
        this(freshProducts, freshIngredients);
    }
}
