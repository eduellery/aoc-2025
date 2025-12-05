import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Day04(long part1, long part2) {

    private static final char PAPER = '@';
    private static final char EMPTY = '.';
    private static final int MIN_NEIGHBORS = 4;

    public Day04(List<String> input) {
        Objects.requireNonNull(input, "input must not be null");
        this(solve(input, false), solve(input, true));
    }

    private static long solve(List<String> input, boolean recurring) {
        final var height = input.size();
        final var width = input.getFirst().length();

        List<String> grid = new ArrayList<>(input);

        long totalRemoved = 0L;
        long diff;

        do {
            diff = 0L;
            var nextGrid = new ArrayList<String>(height);

            for (var row = 0; row < height; row++) {
                var line = grid.get(row);
                var chars = line.toCharArray();

                for (var col = 0; col < width; col++) {
                    if (line.charAt(col) == PAPER
                            && countAdjacentPapers(grid, row, col, height, width) < MIN_NEIGHBORS) {
                        chars[col] = EMPTY;
                        diff++;
                    }
                }

                nextGrid.add(String.valueOf(chars));
            }

            totalRemoved += diff;
            grid = nextGrid;
        } while (diff != 0L && recurring);

        return totalRemoved;
    }

    private static int countAdjacentPapers(
            List<String> grid, int row, int col, int height, int width) {
        var count = 0;

        for (var r = Math.max(0, row - 1); r <= Math.min(height - 1, row + 1); r++) {
            var line = grid.get(r);
            for (var c = Math.max(0, col - 1); c <= Math.min(width - 1, col + 1); c++) {
                if (r == row && c == col) {
                    continue;
                }
                if (line.charAt(c) == PAPER) {
                    count++;
                }
            }
        }

        return count;
    }
}
