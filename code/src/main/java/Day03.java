import java.util.List;
import java.util.Objects;

public record Day03(long part1, long part2) {

    private static final int PART1_DIGITS = 2;
    private static final int PART2_DIGITS = 12;

    public Day03(List<String> input) {
        Objects.requireNonNull(input, "input must not be null");
        this(calculateTotalJolts(input, PART1_DIGITS), calculateTotalJolts(input, PART2_DIGITS));
    }

    private static long calculateTotalJolts(List<String> input, int digitsToPick) {
        return input.stream()
                .map(String::strip)
                .mapToLong(line -> joltsForLine(line, digitsToPick))
                .sum();
    }

    private static long joltsForLine(String line, int digitsToPick) {
        var bank = line.chars().map(ch -> ch - '0').toArray();
        var digits = new int[digitsToPick];
        var last = -1;

        for (int i = digitsToPick - 1; i >= 0; i--) {
            last = findMaxIndex(bank, last + 1, bank.length - i);
            digits[i] = bank[last];
        }

        long jolts = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            jolts = jolts * 10 + digits[i];
        }

        return jolts;
    }

    private static int findMaxIndex(int[] bank, int startInclusive, int endExclusive) {
        var maxIndex = startInclusive;
        var maxValue = bank[startInclusive];

        for (int i = startInclusive + 1; i < endExclusive; i++) {
            var candidate = bank[i];
            if (candidate > maxValue) {
                maxValue = candidate;
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
