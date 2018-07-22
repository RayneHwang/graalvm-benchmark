package rocks.huanglei;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TopTenSingleFile {

    public static void main(String... args) {
        run(args);
    }

    public static void run(String[] files) {
        System.out.println(Arrays.stream(files)
                                 .flatMap(TopTenSingleFile::lines)
                                 .flatMap(line -> Arrays.stream(line.split("\\b")))
                                 .map(String::toLowerCase)
                                 .filter(s -> !s.isEmpty() && !s.equals(" "))
                                 .map(TopTenSingleFile::hasher)
                                 .filter(Optional::isPresent)
                                 .map(Optional::get)
                                 .collect(Collectors.groupingBy(h -> h, Collectors.counting()))
                                 .entrySet().stream()
                                 .sorted(Map.Entry.comparingByValue((l1, l2) -> (int)(l2 - l1)))
                                 .limit(10)
                                 .map(entry -> String.format("%s -> %d", entry.getKey(), entry.getValue()))
                                 .collect(Collectors.joining(System.lineSeparator())));
    }


    public static Stream<String> lines(String path) {
        try {
            return Files.lines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    public static Optional<String> hasher(String input) {
        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(input.getBytes());
            final byte[] digest = md5.digest();
            final byte[] encode = Base64.getEncoder().encode(digest);
            return Optional.of(new String(encode));
        } catch (NoSuchAlgorithmException ignore) {}
        return Optional.empty();
    }
}
