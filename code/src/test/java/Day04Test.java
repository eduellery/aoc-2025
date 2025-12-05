import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.LongSupplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day04Test {

    private static final String EXAMPLE_FILE = "day04.example";
    private static final String INPUT_FILE = "day04.in";

    private static final Day04 EXAMPLE_DAY04 = new Day04(Utils.readLines(EXAMPLE_FILE));
    private static final Day04 INPUT_DAY04 = new Day04(Utils.readLines(INPUT_FILE));

    private record Case(long expected, LongSupplier partSupplier, String displayName) {}

    private static Stream<Arguments> allCases() {
        return Stream.of(
                        new Case(13, EXAMPLE_DAY04::part1, "Day 04 - Example – Part 1"),
                        new Case(2, EXAMPLE_DAY04::part2, "Day 04 - Example – Part 2"),
                        new Case(1320, INPUT_DAY04::part1, "Day 04 - Input – Part 1"),
                        new Case(2, INPUT_DAY04::part2, "Day 04 - Input – Part 2"))
                .map(c -> Arguments.of(c.expected, c.partSupplier, c.displayName));
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("allCases")
    void testDay04(long expected, LongSupplier partSupplier, String displayName) {
        assertEquals(expected, partSupplier.getAsLong(), displayName);
    }
}
