package rocks.huanglei;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

public class NativePolyglotDemo {
    public static void main(String[] args) throws java.io.IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String         input  = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        try (Context context = Context.create("js")) {
            /**
             * console.log(
             *          JSON.stringify(
             *                  JSON.parse(input), null, 2)
             *          )
             * )
             */
            Value parse     = context.eval("js", "JSON.parse");
            Value stringify = context.eval("js", "JSON.stringify");
            Value nullValue = context.eval("js", "null");
            Value value2    = context.eval("js", "2");
            Value result    = stringify.execute(parse.execute(input), nullValue, value2);
            System.out.println(result.asString());
        }
    }
}
