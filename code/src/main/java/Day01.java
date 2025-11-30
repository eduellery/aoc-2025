import java.util.stream.IntStream;

public record Day01(int part1, int part2) {

    public Day01(String input) {
        this(solvePart1(input), solvePart2(input));
    }

    private static int solvePart1(String input) {
        return 1;
    }

    private static int solvePart2(String input) {
        return 2;
    }

}
