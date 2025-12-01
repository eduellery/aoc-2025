import java.util.List;
import java.util.Objects;

public record Day01(int part1, int part2) {

    private static final int RING_SIZE = 100;
    private static final int START_POSITION = 50;

    public static Day01 fromInput(List<String> input) {
        Objects.requireNonNull(input, "input must not be null");

        var current = START_POSITION;
        var zeroStops  = 0;
        var zeroPasses = 0;

        for (String line : input) {
            var direction = line.charAt(0);
            var diff = Integer.parseInt(line, 1, line.length(), 10);

            current = switch (direction) {
                case 'L' -> {
                    zeroPasses += ((RING_SIZE - current) % RING_SIZE + diff) / RING_SIZE;
                    yield Math.floorMod(current - diff, RING_SIZE);
                }
                case 'R' -> {
                    zeroPasses += (current + diff) / RING_SIZE;
                    yield (current + diff) % RING_SIZE;
                }
                default -> throw new IllegalArgumentException("Unknown direction: '" + direction + '\'');
            };

            if (current == 0) {
                zeroStops++;
            }
        }

        return new Day01(zeroStops, zeroPasses);
    }
}

