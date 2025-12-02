import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day01Test {

    private static final String EXAMPLE_FILE = "day01.example";
    private static final String INPUT_FILE = "day01.in";

    private record Case(
            String fileName, int expected, ToIntFunction<Day01> partFunction, String displayName) {}

    private static Stream<Arguments> allCases() {
        return Stream.of(
                        new Case(EXAMPLE_FILE, 3, Day01::part1, "Day 01 - Example – Part 1"),
                        new Case(EXAMPLE_FILE, 6, Day01::part2, "Day 01 - Example – Part 2"),
                        new Case(INPUT_FILE, 1132, Day01::part1, "Day 01 - Input – Part 1"),
                        new Case(INPUT_FILE, 6623, Day01::part2, "Day 01 - Input – Part 2"))
                .map(c -> Arguments.of(c.fileName, c.expected, c.partFunction, c.displayName));
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("allCases")
    void testDay01(
            String fileName, int expected, ToIntFunction<Day01> partFunction, String displayName) {

        var input = Utils.readLines(fileName, Day01Test.class);
        var day = Day01.fromInput(input);

        assertEquals(expected, partFunction.applyAsInt(day), displayName);
    }
}
