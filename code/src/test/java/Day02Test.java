import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.LongSupplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day02Test {

    private static final String EXAMPLE_FILE = "day02.example";
    private static final String INPUT_FILE = "day02.in";

    private static final Day02 EXAMPLE_DAY02 =
            new Day02(Utils.readLineAsStringList(EXAMPLE_FILE, Day02Test.class));
    private static final Day02 INPUT_DAY02 =
            new Day02(Utils.readLineAsStringList(INPUT_FILE, Day02Test.class));

    private record Case(long expected, LongSupplier partSupplier, String displayName) {}

    private static Stream<Arguments> allCases() {
        return Stream.of(
                        new Case(1227775554L, EXAMPLE_DAY02::part1, "Day 02 - Example – Part 1"),
                        new Case(4174379265L, EXAMPLE_DAY02::part2, "Day 02 - Example – Part 2"),
                        new Case(22062284697L, INPUT_DAY02::part1, "Day 02 - Input – Part 1"),
                        new Case(46666175279L, INPUT_DAY02::part2, "Day 02 - Input – Part 2"))
                .map(c -> Arguments.of(c.expected, c.partSupplier, c.displayName));
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("allCases")
    void testDay02(long expected, LongSupplier partSupplier, String displayName) {
        assertEquals(expected, partSupplier.getAsLong(), displayName);
    }
}
