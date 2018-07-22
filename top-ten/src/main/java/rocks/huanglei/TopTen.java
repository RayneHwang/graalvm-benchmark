package rocks.huanglei;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import rocks.huanglei.util.Utils;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
public class TopTen {
    @Param("")
    private String files;

    private List<String> words;

    public TopTen() {}

    @Setup
    public void setUp() {
        words = Arrays.stream(files.split(" "))
                      .flatMap(Utils::lines)
                      .flatMap(line -> Arrays.stream(line.split("\\b")))
                      .collect(Collectors.toList());
    }

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

    @Benchmark
    public void run() {
        System.out.println(words.parallelStream()
                                .map(String::toLowerCase)
                                .filter(s -> !s.isEmpty() && !s.equals(" "))
                                .map(Utils::hasher)
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .collect(Collectors.groupingBy(h -> h, Collectors.counting()))
                                .entrySet().stream()
                                .sorted(Map.Entry.comparingByValue((l1, l2) -> (int)(l2 - l1)))
                                .limit(10)
                                .map(entry -> String.format("%s -> %d", entry.getKey(), entry.getValue()))
                                .collect(Collectors.joining(System.lineSeparator())));
    }
}
