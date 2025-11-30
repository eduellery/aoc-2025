import java.util.stream.Stream;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
class Day01Test {

    private String fileName = "day01.in";

    private static Stream<Arguments> provideExamplesPart1() {
        return Stream.of(
            Arguments.of("1122", 1),
            Arguments.of("1111", 1),
            Arguments.of("1234", 1),
            Arguments.of("91212129", 1)
        );
    }

    private static Stream<Arguments> provideExamplesPart2() {
        return Stream.of(
            Arguments.of("1212", 2),
            Arguments.of("1221", 2),
            Arguments.of("123425", 2),
            Arguments.of("123123", 2),
            Arguments.of("12131415", 2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideExamplesPart1")
    void testExamplePart1(String input, int expected) {
        Day01 day = new Day01(input);
        assertEquals(expected, day.part1());
    }

    @ParameterizedTest
    @MethodSource("provideExamplesPart2")
    void testExamplePart2(String input, int expected) {
        Day01 day = new Day01(input);
        assertEquals(expected, day.part2());
    }

//    @Test
//    void testSolution() {
//        String input = Utils.readLine(fileName, Day01Test.class);
//        Day01 day = new Day01(input);
//        assertEquals(1343, day.part1(), "Part 1");
//        assertEquals(1274, day.part2(), "Part 2");
//    }

}
