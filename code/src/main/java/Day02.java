import java.util.List;
import java.util.Objects;

public record Day02(long part1, long part2) {

    public Day02(List<String> input) {
        Objects.requireNonNull(input, "input must not be null");
        this(solvePart1(input), solvePart2(input));
    }

    private static long solvePart1(List<String> input) {
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
        return count;
    }

    private static long solvePart2(List<String> input) {
        long count = 0;
        for (final String line : input) {
            int dash = line.indexOf('-');
            long left = Long.parseLong(line, 0, dash, 10);
            long right = Long.parseLong(line, dash + 1, line.length(), 10);
            while (left <= right) {
                String current = String.valueOf(left);
                for (int i = 1; i < (current.length() / 2) + 1; i++) {
                    boolean invalidId = true;
                    String pattern = current.substring(0, i);
                    for (int j = 0; j < current.length(); j += pattern.length()) {
                        int end = j + pattern.length();
                        String next =
                                (end < current.length())
                                        ? current.substring(j, j + pattern.length())
                                        : current.substring(j);
                        if (!next.equals(pattern)) {
                            invalidId = false;
                            break;
                        }
                    }
                    if (invalidId) {
                        count += left;
                        left++;
                        break;
                    }
                }
                left++;
            }
        }
        return count;
    }

    private static long pow10(int n) {
        long result = 1;
        for (int i = 0; i < n; i++) result *= 10;
        return result;
    }
}
