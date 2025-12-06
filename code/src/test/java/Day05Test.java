import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.LongSupplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day05Test {

    private static final String EXAMPLE_FILE = "day05.example";
    private static final String INPUT_FILE = "day05.in";

    private static final Day05 EXAMPLE_DAY05 = new Day05(Utils.read(EXAMPLE_FILE));
    private static final Day05 INPUT_DAY05 = new Day05(Utils.read(INPUT_FILE));

    private record Case(long expected, LongSupplier partSupplier, String displayName) {}

    private static Stream<Arguments> allCases() {
        return Stream.of(
                        new Case(3L, EXAMPLE_DAY05::part1, "Day 05 - Example – Part 1"),
                        new Case(14L, EXAMPLE_DAY05::part2, "Day 05 - Example – Part 2"),
                        new Case(744L, INPUT_DAY05::part1, "Day 05 - Input – Part 1"),
                        new Case(347468726696961L, INPUT_DAY05::part2, "Day 05 - Input – Part 2"))
                .map(c -> Arguments.of(c.expected, c.partSupplier, c.displayName));
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("allCases")
    void testDay05(long expected, LongSupplier partSupplier, String displayName) {
        assertEquals(expected, partSupplier.getAsLong(), displayName);
    }
}
