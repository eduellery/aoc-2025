import java.util.List;

public record Day01(int part1, int part2) {

    public Day01(List<String> input) {
        this(solvePart1(input), solvePart2(input));
    }

    private static int solvePart1(List<String> input) {
        int current = 50;
        int zeroStop = 0;
        for (String line : input) {
            int diff = Integer.valueOf(line.substring(1, line.length()));
            current = switch (line.charAt(0)) {
                case 'L' -> current >= diff ? current - diff : (1000 + current - diff) % 100;
                case 'R' -> (current + diff) % 100;
                default -> 100;
            };
            if (current == 0) {
                zeroStop++;
            }
        }
        return zeroStop;
    }

    private static int solvePart2(List<String> input) {
        int current = 50;
        int zeroPass = 0;
        for (String line : input) {
            int diff = Integer.valueOf(line.substring(1, line.length()));
            current = switch (line.charAt(0)) {
                case 'L' -> {
                    zeroPass += (((100 - current) % 100) + diff) / 100;
                    yield current >= diff ? current - diff : (1000 + current - diff) % 100;
                }
                case 'R' -> {
                    zeroPass += (current + diff) / 100;
                    yield (current + diff) % 100;
                }
                default -> 100;
            };
            //System.out.println(line + ", current: " + current + ", zeroPass: " + zeroPass);
        }
        return zeroPass;
    }

}
