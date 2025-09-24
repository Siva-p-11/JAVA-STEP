import java.util.Scanner;

public class StringPerformanceTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of iterations: ");
        int iterations = scanner.nextInt();

        Result stringResult = testStringConcatenation(iterations);
        Result builderResult = testStringBuilder(iterations);
        Result bufferResult = testStringBuffer(iterations);

        System.out.println("\n=== Performance Comparison ===");
        System.out.printf("%-15s | %-10s | %-15s%n", "Method", "Time (ms)", "Final Length");
        System.out.println("------------------------------");
        System.out.printf("%-15s | %-10d | %-15d%n", "String (+)", stringResult.timeTaken, stringResult.length);
        System.out.printf("%-15s | %-10d | %-15d%n", "StringBuilder", builderResult.timeTaken, builderResult.length);
        System.out.printf("%-15s | %-10d | %-15d%n", "StringBuffer", bufferResult.timeTaken, bufferResult.length);

        scanner.close();
    }

    static class Result {
        long timeTaken;
        int length;

        Result(long timeTaken, int length) {
            this.timeTaken = timeTaken;
            this.length = length;
        }
    }

    public static Result testStringConcatenation(int iterations) {
        long start = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result = result + "abc";
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, result.length());
    }

    public static Result testStringBuilder(int iterations) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("abc");
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, sb.length());
    }

    public static Result testStringBuffer(int iterations) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("abc");
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, sb.length());
    }
}
