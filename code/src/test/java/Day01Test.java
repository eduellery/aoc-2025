import java.util.List;
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

    private String exampleFile = "day01.example";
    private String inputFile = "day01.in";

    @Test
    void testExamplePart1() {
        List<String> input = Utils.readLines(exampleFile, Day01Test.class);
        Day01 day = new Day01(input);
        assertEquals(3, day.part1());
    }

    @Test
    void testExamplePart2() {
        List<String> input = Utils.readLines(exampleFile, Day01Test.class);
        Day01 day = new Day01(input);
        assertEquals(6, day.part2());
    }

    @Test
    void testSolutionPart1() {
        List<String> input = Utils.readLines(inputFile, Day01Test.class);
        Day01 day = new Day01(input);
        assertEquals(1132, day.part1(), "Part 1");
    }

    @Test
    void testSolutionPart2() {
        List<String> input = Utils.readLines(inputFile, Day01Test.class);
        Day01 day = new Day01(input);
        assertEquals(6623, day.part2(), "Part 2");
    }

}
