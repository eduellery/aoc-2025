import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.LongSupplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day03Test {

    private static final String EXAMPLE_FILE = "day03.example";
    private static final String INPUT_FILE = "day03.in";

    private static final Day03 EXAMPLE_DAY03 = new Day03(Utils.readLines(EXAMPLE_FILE));
    private static final Day03 INPUT_DAY03 = new Day03(Utils.readLines(INPUT_FILE));

    private record Case(long expected, LongSupplier partSupplier, String displayName) {}

    private static Stream<Arguments> allCases() {
        return Stream.of(
                        new Case(357L, EXAMPLE_DAY03::part1, "Day 03 - Example – Part 1"),
                        new Case(3121910778619L, EXAMPLE_DAY03::part2, "Day 03 - Example – Part 2"),
                        new Case(17694L, INPUT_DAY03::part1, "Day 03 - Input – Part 1"),
                        new Case(175659236361660L, INPUT_DAY03::part2, "Day 03 - Input – Part 2"))
                .map(c -> Arguments.of(c.expected, c.partSupplier, c.displayName));
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("allCases")
    void testDay03(long expected, LongSupplier partSupplier, String displayName) {
        assertEquals(expected, partSupplier.getAsLong(), displayName);
    }
}
