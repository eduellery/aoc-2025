import java.util.List;
import java.util.Objects;

public record Day02(long part1, long part2) {

    public static Day02 fromInput(List<String> input) {
        Objects.requireNonNull(input, "input must not be null");
        long count = 0;

        for (String line : input) {
            int dash = line.indexOf('-');
            long left = Long.parseLong(line, 0, dash, 10);
            long right = Long.parseLong(line, dash + 1, line.length(), 10);
            while (left <= right) {
                long abs = Math.abs(left);
                int length = (abs == 0) ? 1 : (int) Math.log10(abs) + 1;
                if (length % 2 == 0) {
                    long div = pow10(length / 2);
                    long up = left / div;
                    long down = left % div;
                    if (up > down) {
                        left += (up - down);
                        continue;
                    }
                    if (up == down) {
                        count += left;
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
        return new Day02(count, 2);
    }

    private static long pow10(int n) {
        long result = 1;
        for (int i = 0; i < n; i++) result *= 10;
        return result;
    }
}
