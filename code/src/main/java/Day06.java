import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public record Day06(long part1, long part2) {

    private static final Pattern WHITESPACE = Pattern.compile("\\s+");

    public Day06(List<String> input) {
        Objects.requireNonNull(input, "input must not be null");
        this(solve(input), 0);
    }

    private static long solve(List<String> input) {
        final var operationsIndex = input.size() - 1;
        final var width = input.getFirst().length();

        List<List<Long>> numberList = new ArrayList<>();

        for (int i = 0; i < operationsIndex; i++) {
            var line = input.get(i);
            var numbers =
                    WHITESPACE
                            .splitAsStream(line)
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .map(Long::parseLong)
                            .toList();
            numberList.add(numbers);
        }

        var operations =
                WHITESPACE.splitAsStream(input.get(operationsIndex)).map(s -> s.charAt(0)).toList();

        long result = 0L;

        for (int i = 0; i < operations.size(); i++) {
            List<Long> numbers = new ArrayList<>();
            for (var list : numberList) {
                numbers.add(list.get(i));
            }
            result +=
                    switch (operations.get(i)) {
                        case '*' -> numbers.stream().reduce(1L, (a, b) -> a * b);
                        case '+' -> numbers.stream().mapToLong(Long::longValue).sum();
                        default -> 0L;
                    };
        }
        return result;
    }
}
