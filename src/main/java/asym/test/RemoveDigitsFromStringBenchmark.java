package asym.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
@State(Scope.Benchmark)
public class RemoveDigitsFromStringBenchmark {

    @Param({"a", "1a", "12a", "1a2", "12345a", "12a12345a", "1", "12345", "12a12345ag12a12345a12a12345a12a1462345a12ay212345a12as14236345a1sgfj2a12345a12ah12345a12amf12345a"})
    private String arg;

    private static final String[] STRING_DIGITS = {"0","1","2","3","4","5","6","7","8","9"};

    private static final char[] CHAR_DIGITS = {'0','1','2','3','4','5','6','7','8','9'};

    @Benchmark
    public String greedyReplaceAll() {
        return arg.replaceAll("[0-9]", "");
    }

    @Benchmark
    public String nonGreedyReplaceAll() {
        return arg.replaceAll("[0-9]+", "");
    }

    @Benchmark
    public String iterReplaceDigits() {
        String iterArg = arg;
        for (String charDigit : STRING_DIGITS) {
            iterArg = arg.replace(charDigit, "");
        }
        return iterArg;
    }

    @Benchmark
    public String madCharReplaceDigits() {
        String iterArg = arg;
        for (char charDigit : CHAR_DIGITS) {
            iterArg = arg.replace(charDigit, '0');
        }
        return iterArg.replace("0", "");
    }
}
