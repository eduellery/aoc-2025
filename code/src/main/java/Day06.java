import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record Day06(long part1, long part2) {

    public Day06(List<String> input) {
        Objects.requireNonNull(input, "input must not be null");
        this(solvePart1(input), solvePart2(input));
    }

    private static long solvePart1(List<String> input) {
        var operators = input.getLast().trim().split("\\s+");

        final int rows = input.size() - 1;
        final int cols = operators.length;

        long[][] numbers = new long[rows][cols];

        for (int i = 0; i < rows; i++) {
            var parts = input.get(i).trim().split("\\s+");
            var row = new long[cols];
            for (int j = 0; j < cols; j++) {
                row[j] = Long.parseLong(parts[j]);
            }
            numbers[i] = row;
        }

        var base = numbers[0];

        for (int r = 1; r < rows; r++) {
            var row = numbers[r];
            for (int c = 0; c < cols; c++) {
                var value = row[c];
                base[c] =
                        switch (operators[c]) {
                            case "+" -> base[c] + value;
                            case "*" -> base[c] * value;
                            default -> base[c];
                        };
            }
        }

        return Arrays.stream(base).sum();
    }

    private static long solvePart2(List<String> input) {
        final int rows = input.size();
        final int cols = input.getFirst().length();

        long result = 0L;
        List<Long> numbers = new ArrayList<>();

        for (int i = cols - 1; i >= 0; i--) {
            long number = 0L;

            for (int j = 0; j < rows - 1; j++) {
                char c = input.get(j).charAt(i);
                number = Character.isDigit(c) ? (number * 10) + (c - '0') : number;
            }

            if (number != 0) numbers.add(number);

            if (i < cols) {
                char c = input.getLast().charAt(i);
                result +=
                        switch (c) {
                            case '+' -> {
                                var temp = numbers.stream().mapToLong(Long::longValue).sum();
                                numbers.clear();
                                yield temp;
                            }
                            case '*' -> {
                                var temp =
                                        numbers.stream()
                                                .mapToLong(Long::longValue)
                                                .reduce(1, (a, b) -> a * b);
                                numbers.clear();
                                yield temp;
                            }
                            default -> 0L;
                        };
            }
        }

        return result;
    }
}
