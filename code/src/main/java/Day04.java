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

    private enum Direction {
        N(-1, 0),
        NE(-1, 1),
        E(0, 1),
        SE(1, 1),
        S(1, 0),
        SW(1, -1),
        W(0, -1),
        NW(-1, -1);

        private final int di;
        private final int dj;

        Direction(int di, int dj) {
            this.di = di;
            this.dj = dj;
        }

        int di() {
            return di;
        }

        int dj() {
            return dj;
        }
    }

    private static final Direction[] ADJACENT_DIRECTIONS = Direction.values();

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
            List<String> input, int i, int j, int height, int width) {
        int count = 0;

        for (var dir : ADJACENT_DIRECTIONS) {
            int ni = i + dir.di();
            int nj = j + dir.dj();

            if (ni < 0 || nj < 0 || ni >= height || nj >= width) {
                continue;
            }

            if (input.get(ni).charAt(nj) == '@') {
                count++;
            }
        }

        return count;
    }
}
