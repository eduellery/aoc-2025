import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.IntSupplier;
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

    private static final Day01 EXAMPLE_DAY01 =
            Day01.fromInput(Utils.readLines(EXAMPLE_FILE, Day01Test.class));
    private static final Day01 INPUT_DAY01 =
            Day01.fromInput(Utils.readLines(INPUT_FILE, Day01Test.class));

    private record Case(int expected, IntSupplier partSupplier, String displayName) {}

    private static Stream<Arguments> allCases() {
        return Stream.of(
                        new Case(3, EXAMPLE_DAY01::part1, "Day 01 - Example – Part 1"),
                        new Case(6, EXAMPLE_DAY01::part2, "Day 01 - Example – Part 2"),
                        new Case(1132, INPUT_DAY01::part1, "Day 01 - Input – Part 1"),
                        new Case(6623, INPUT_DAY01::part2, "Day 01 - Input – Part 2"))
                .map(c -> Arguments.of(c.expected, c.partSupplier, c.displayName));
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("allCases")
    void testDay01(int expected, IntSupplier partSupplier, String displayName) {
        assertEquals(expected, partSupplier.getAsInt(), displayName);
    }
}
