import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.LongSupplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day06Test {

    private static final String EXAMPLE_FILE = "day06.example";
    private static final String INPUT_FILE = "day06.in";

    private static final Day06 EXAMPLE_DAY06 = new Day06(Utils.readLines(EXAMPLE_FILE));
    private static final Day06 INPUT_DAY06 = new Day06(Utils.readLines(INPUT_FILE));

    private record Case(long expected, LongSupplier partSupplier, String displayName) {}

    private static Stream<Arguments> allCases() {
        return Stream.of(
                        new Case(4277556L, EXAMPLE_DAY06::part1, "Day 06 - Example – Part 1"),
                        new Case(3263827L, EXAMPLE_DAY06::part2, "Day 06 - Example – Part 2"),
                        new Case(6343365546996L, INPUT_DAY06::part1, "Day 06 - Input – Part 1"),
                        new Case(11136895955912L, INPUT_DAY06::part2, "Day 06 - Input – Part 2"))
                .map(c -> Arguments.of(c.expected, c.partSupplier, c.displayName));
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("allCases")
    void testDay06(long expected, LongSupplier partSupplier, String displayName) {
        assertEquals(expected, partSupplier.getAsLong(), displayName);
    }
}
