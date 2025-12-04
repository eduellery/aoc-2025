import java.util.List;
import java.util.Objects;

public record Day03(long part1, long part2) {

    public Day03(List<String> input) {
        this(solvePart1(Objects.requireNonNull(input)), solvePart2(input));
    }

    private static long solvePart1(List<String> input) {
        long total = 0L;

        for (var line : input) {
            var digits = line.chars().map(ch -> ch - '0').toArray();

            var lastIndex = digits.length - 1;
            var leftIndex = lastIndex - 1;
            var rightIndex = lastIndex;
            var high = digits[leftIndex];
            var low = digits[rightIndex];

            for (int i = leftIndex - 1; i >= 0; i--) {
                var candidateHigh = digits[i];
                if (candidateHigh >= high) {
                    high = candidateHigh;
                    for (int j = rightIndex - 1; j > i; j--) {
                        var candidateLow = digits[j];
                        if (candidateLow >= low) {
                            low = candidateLow;
                            rightIndex = j;
                        }
                    }
                }
            }

            total += high * 10L + low;
        }

        return total;
    }

    private static long solvePart2(List<String> input) {
        long total = 0;

        return total;
    }
}
