package rocks.huanglei;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
public class TopTen {
    @Param("")
    private String files;

    public TopTen() {}

    public static void main(String... args) throws RunnerException {
        System.out.println(Arrays.toString(args));
        Options opt = new OptionsBuilder()
                .include(TopTen.class.getSimpleName())
                .param("files", args)
                .forks(1)
                .warmupIterations(1)
                .build();
        new Runner(opt).run();
    }

    private static Stream<String> lines(String path) {
        try {
            return Files.lines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    @Benchmark
    public void run() {
        System.out.println(Arrays.stream(files.split(" "))
                                 .flatMap(TopTen::lines)
                                 .flatMap(line -> Arrays.stream(line.split("\\b")))
                                 .map(String::toLowerCase)
                                 .filter(s -> !s.isEmpty() && !s.equals(" "))
                                 .collect(Collectors.groupingBy(h -> h, Collectors.counting()))
                                 .entrySet().stream()
                                 .sorted(Map.Entry.comparingByValue((l1, l2) -> (int)(l2 - l1)))
                                 .limit(10)
                                 .map(entry -> String.format("%s -> %d", entry.getKey(), entry.getValue()))
                                 .collect(Collectors.joining(System.lineSeparator())));
    }
}
