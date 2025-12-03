import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.ToLongFunction;
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

    private record Case(
            String fileName,
            long expected,
            ToLongFunction<Day02> partFunction,
            String displayName) {}

    private static Stream<Arguments> allCases() {
        return Stream.of(
                        new Case(
                                EXAMPLE_FILE,
                                1227775554L,
                                Day02::part1,
                                "Day 02 - Example – Part 1"),
                        new Case(
                                EXAMPLE_FILE,
                                4174379265L,
                                Day02::part2,
                                "Day 02 - Example – Part 2"),
                        new Case(INPUT_FILE, 22062284697L, Day02::part1, "Day 02 - Input – Part 1"),
                        new Case(INPUT_FILE, 46666175279L, Day02::part2, "Day 02 - Input – Part 2"))
                .map(c -> Arguments.of(c.fileName, c.expected, c.partFunction, c.displayName));
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("allCases")
    void testDay02(
            String fileName,
            long expected,
            ToLongFunction<Day02> partFunction,
            String displayName) {

        var input = Utils.readLineAsStringList(fileName, Day02Test.class);
        var day = new Day02(input);

        assertEquals(expected, partFunction.applyAsLong(day), displayName);
    }
}
