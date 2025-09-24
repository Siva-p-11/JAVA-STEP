import java.util.*;

public class TextCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expressions to evaluate (empty line to exit):");

        while (true) {
            String expr = scanner.nextLine().trim();
            if (expr.isEmpty()) break;

            if (!validateExpression(expr)) {
                System.out.println("Invalid expression format.\n");
                continue;
            }

            System.out.println("Original expression: " + expr);
            List<String> steps = new ArrayList<>();
            try {
                double result = evaluateExpressionWithParentheses(expr, steps);
                displaySteps(steps, result);
            } catch (Exception e) {
                System.out.println("Error evaluating expression: " + e.getMessage());
            }

            System.out.println();
        }
        scanner.close();
    }

    static boolean validateExpression(String expr) {
        int parenCount = 0;
        boolean lastWasOp = true;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == ' ') continue;
            if (isDigit(c) || c == '(' || c == ')') {
                if (c == '(') parenCount++;
                else if (c == ')') {
                    parenCount--;
                    if (parenCount < 0) return false;
                }
                lastWasOp = false;
            } else if (isOperator(c)) {
                if (lastWasOp) return false; 
                lastWasOp = true;
            } else {
                return false; 
            }
        }
        return parenCount == 0 && !lastWasOp;
    }

    static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static double evaluateExpressionWithParentheses(String expr, List<String> steps) throws Exception {
        while (expr.contains("(")) {
            int close = expr.indexOf(')');
            if (close == -1) throw new Exception("Mismatched parentheses");
            int open = expr.lastIndexOf('(', close);
            if (open == -1) throw new Exception("Mismatched parentheses");

            String inner = expr.substring(open + 1, close);
            double innerVal = evaluateSimpleExpression(inner, steps);
            steps.add("Evaluated (" + inner + ") = " + innerVal);
            expr = expr.substring(0, open) + innerVal + expr.substring(close + 1);
            steps.add("Expression now: " + expr);
        }
        return evaluateSimpleExpression(expr, steps);
    }

    static double evaluateSimpleExpression(String expr, List<String> steps) throws Exception {
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        parseExpression(expr, numbers, operators);

        for (int i = 0; i < operators.size(); ) {
            char op = operators.get(i);
            if (op == '*' || op == '/') {
                double a = numbers.get(i);
                double b = numbers.get(i + 1);
                double res = (op == '*') ? a * b : a / b;
                numbers.set(i, res);
                numbers.remove(i + 1);
                operators.remove(i);
                steps.add("Step: " + listToString(numbers, operators));
            } else {
                i++;
            }
        }

        for (int i = 0; i < operators.size(); ) {
            char op = operators.get(i);
            double a = numbers.get(i);
            double b = numbers.get(i + 1);
            double res = (op == '+') ? a + b : a - b;
            numbers.set(i, res);
            numbers.remove(i + 1);
            operators.remove(i);
            steps.add("Step: " + listToString(numbers, operators));
        }

        return numbers.get(0);
    }

    static void parseExpression(String expr, List<Double> numbers, List<Character> operators) throws Exception {
        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }
            if (isDigit(c) || c == '.') {
                int start = i;
                while (i < expr.length() && (isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) i++;
                double num = Double.parseDouble(expr.substring(start, i));
                numbers.add(num);
            } else if (isOperator(c)) {
                operators.add(c);
                i++;
            } else {
                throw new Exception("Invalid character in expression");
            }
        }
    }

    static String listToString(List<Double> numbers, List<Character> operators) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < operators.size(); i++) {
            sb.append(numbers.get(i)).append(' ').append(operators.get(i)).append(' ');
        }
        sb.append(numbers.get(numbers.size() - 1));
        return sb.toString();
    }

    static void displaySteps(List<String> steps, double finalResult) {
        for (String s : steps) {
            System.out.println(s);
        }
        System.out.println("Final result: " + finalResult);
    }
}
