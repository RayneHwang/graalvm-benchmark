package rocks.huanglei.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Stream;

public class Utils {

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

    public static boolean isBlank(String s) {
        return s == null || s.isEmpty() || " ".equals(s);
    }
}
