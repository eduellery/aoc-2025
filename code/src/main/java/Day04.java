import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Day04(long part1, long part2) {

    public Day04(List<String> input) {
        this(solvePart1(Objects.requireNonNull(input)), solvePart2(input));
    }

    private static long solvePart1(List<String> input) {
        long total = 0;

        final var height = input.size();
        final var width = input.get(0).length();

        for (int i = 0; i < height; i++) {
            String line = input.get(i);
            for (int j = 0; j < width; j++) {
                if (line.charAt(j) == '@' && countPapersAround(input, i, j, height, width) < 4) {
                    total++;
                }
            }
        }

        return total;
    }

    private static long solvePart2(List<String> input) {
        long total = 0;

        final var height = input.size();
        final var width = input.get(0).length();

        long diff = -1;

        while (diff != 0) {
            diff = 0;
            var changedList = new ArrayList<String>();
            for (int i = 0; i < height; i++) {
                char[] array = input.get(i).toCharArray();
                String line = input.get(i);
                for (int j = 0; j < width; j++) {
                    if (line.charAt(j) == '@'
                            && countPapersAround(input, i, j, height, width) < 4) {
                        array[j] = '.';
                        diff++;
                    }
                }
                changedList.add(String.valueOf(array));
            }
            total += diff;
            input = changedList;
        }

        return total;
    }

    private static int countPapersAround(List<String> input, int i, int j, int heigth, int width) {
        int count = 0;
        for (int a = i - 1; a <= i + 1; a++) {
            for (int b = j - 1; b <= j + 1; b++) {
                if (a < 0 || b < 0 || a >= heigth || b >= width) {
                    continue;
                }
                if (input.get(a).charAt(b) == '@') {
                    count++;
                }
            }
        }
        return count - 1;
    }
}
