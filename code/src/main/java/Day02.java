import java.util.List;
import java.util.Objects;

public record Day02(long part1, long part2) {

    public Day02(List<String> input) {
        this(solvePart1(Objects.requireNonNull(input)), solvePart2(input));
    }

    private static long solvePart1(List<String> input) {
        long total = 0;

        for (var line : input) {
            final int dash = line.indexOf('-');
            long left = Long.parseLong(line, 0, dash, 10);
            long right = Long.parseLong(line, dash + 1, line.length(), 10);

            while (left <= right) {
                final int length = (left == 0) ? 1 : (int) Math.log10(left) + 1;

                if ((length & 1) == 0) {
                    final int half = length >>> 1;
                    final long div = pow10(half);

                    final long up = left / div;
                    final long down = left % div;

                    if (up > down) {
                        left += (up - down);
                        continue;
                    }

                    if (up == down) {
                        total += left;
                    }

                    left = (up + 1) * div;
                } else {
                    left = pow10(length);
                }

                if (left < 0) {
                    break;
                }
            }
        }

        return total;
    }

    private static long solvePart2(List<String> input) {
        long total = 0;

        for (var line : input) {
            final int dash = line.indexOf('-');
            long left = Long.parseLong(line, 0, dash, 10);
            long right = Long.parseLong(line, dash + 1, line.length(), 10);

            while (left <= right) {
                final String s = Long.toString(left);
                final int n = s.length();

                outerFor:
                for (int i = 1; i <= n / 2; i++) {
                    final String pattern = s.substring(0, i);

                    for (int pos = 0; pos < n; pos += i) {
                        int end = pos + i;
                        if (!s.regionMatches(pos, pattern, 0, end - pos)) {
                            continue outerFor;
                        }
                    }

                    total += left;
                    break;
                }

                left++;
            }
        }

        return total;
    }

    private static long pow10(int n) {
        long p = 1;
        for (int i = 0; i < n; i++) {
            p *= 10;
        }
        return p;
    }
}
