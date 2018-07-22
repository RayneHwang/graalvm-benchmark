package rocks.huanglei;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import rocks.huanglei.util.Utils;

@Warmup(iterations = 3)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class TopTen {

    private List<String> words;

    public TopTen() {}

    @Setup
    public void setUp() {
      String s="A text message as it appears on the display screen of an iPhone before iOS 7 Text messaging or texting is the act of composing and sending electronic messages typically consisting of alphabetic and numeric characters between two or more users of mobile devices desktops/laptops or other type of compatible computer Text messages may be sent over a cellular network or may also be sent via an Internet connection The term originally referred to messages sent using the Short Message Service (SMS) It has grown beyond alphanumeric text to include multimedia messages (known as MMS) containing digital images videos and sound content as well as ideograms known as emoji (happy faces sad faces and other icons) As of 2017 text messages are used by youth and adults for personal family and social purposes and in business Governmental and non-governmental organizations use text messaging for communication between colleagues As with emailing in the 2010s the sending of short informal messages has become an accepted part of many cultures[1] This makes texting a quick and easy way to communicate with friends and colleagues including in contexts where a call would be impolite or inappropriate (eg calling very late at night or when one knows the other person is busy with family or work activities) Like e-mail and voice mail and unlike calls (in which the caller hopes to speak directly with the recipient) texting does not require the caller and recipient to both be free at the same moment this permits communication even between busy individuals Text messages can also be used to interact with automated systems for example to order products or services from e-commerce websites or to participate in online contests Advertisers and service providers use direct text marketing to send messages to mobile users about promotions payment due dates and other notifications instead of using postal mail email or voicemail";
        words=Arrays.stream(s.split(" ")).collect(Collectors.toList());
    }

    public static void main(String... args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TopTen.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public void run() {
        words.parallelStream()
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
                                .collect(Collectors.joining(System.lineSeparator()));
    }
}
