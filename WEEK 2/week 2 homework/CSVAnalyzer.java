import java.util.*;

public class CSVAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter CSV data (empty line to end):");
        StringBuilder rawInput = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            rawInput.append(line).append("\n");
        }

        String[][] data = parseCSV(rawInput.toString());
        cleanData(data);

        DataAnalysis analysis = analyzeData(data);
        String table = formatTable(data, analysis);
        String summary = generateSummaryReport(data, analysis);

        System.out.println("\nFormatted Table:\n" + table);
        System.out.println("Summary Report:\n" + summary);
    }

    static String[][] parseCSV(String text) {
        List<String[]> rows = new ArrayList<>();
        int pos = 0, len = text.length();

        while (pos < len) {
            List<String> fields = new ArrayList<>();
            boolean inQuotes = false;
            int fieldStart = pos;
            StringBuilder field = new StringBuilder();

            while (pos < len) {
                char c = text.charAt(pos);

                if (inQuotes) {
                    if (c == '"') {
                        if (pos + 1 < len && text.charAt(pos + 1) == '"') {
                            // Escaped quote
                            field.append('"');
                            pos += 2;
                            continue;
                        } else {
                            inQuotes = false;
                            pos++;
                            continue;
                        }
                    } else {
                        field.append(c);
                        pos++;
                    }
                } else {
                    if (c == '"') {
                        inQuotes = true;
                        pos++;
                    } else if (c == ',') {
                        fields.add(field.toString());
                        field.setLength(0);
                        pos++;
                    } else if (c == '\n' || c == '\r') {
                        if (c == '\r' && pos + 1 < len && text.charAt(pos + 1) == '\n') pos++; // handle \r\n
                        fields.add(field.toString());
                        pos++;
                        break;
                    } else {
                        field.append(c);
                        pos++;
                    }
                }
            }

            // End of line or input
            if (field.length() > 0 || fields.size() > 0) {
                fields.add(field.toString());
            }
            if (!fields.isEmpty()) rows.add(fields.toArray(new String[0]));
        }

        // Convert to 2D array
        int maxCols = 0;
        for (String[] r : rows) if (r.length > maxCols) maxCols = r.length;

        String[][] result = new String[rows.size()][maxCols];
        for (int i = 0; i < rows.size(); i++) {
            String[] r = rows.get(i);
            for (int j = 0; j < maxCols; j++) {
                result[i][j] = (j < r.length) ? r[j] : "";
            }
        }
        return result;
    }

    static void cleanData(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = data[i][j].trim();
            }
        }
    }

    static class DataAnalysis {
        boolean[] numericCols;
        double[] minVals, maxVals, sumVals;
        int[] validCounts;
        Map<Integer, Set<String>> uniqueCats = new HashMap<>();
        int missingCount = 0;
        int invalidCount = 0;
    }

    static DataAnalysis analyzeData(String[][] data) {
        DataAnalysis a = new DataAnalysis();
        if (data.length == 0) return a;

        int rows = data.length - 1;
        int cols = data[0].length;

        a.numericCols = new boolean[cols];
        Arrays.fill(a.numericCols, true);
        a.minVals = new double[cols];
        a.maxVals = new double[cols];
        a.sumVals = new double[cols];
        a.validCounts = new int[cols];

        for (int c = 0; c < cols; c++) {
            a.minVals[c] = Double.MAX_VALUE;
            a.maxVals[c] = -Double.MAX_VALUE;
            a.uniqueCats.put(c, new HashSet<>());
        }

        for (int r = 1; r <= rows; r++) {
            for (int c = 0; c < cols; c++) {
                String val = data[r][c];
                if (val.isEmpty()) {
                    a.missingCount++;
                    a.numericCols[c] = false;
                    continue;
                }
                if (a.numericCols[c]) {
                    if (isNumeric(val)) {
                        double d = Double.parseDouble(val);
                        if (d < a.minVals[c]) a.minVals[c] = d;
                        if (d > a.maxVals[c]) a.maxVals[c] = d;
                        a.sumVals[c] += d;
                        a.validCounts[c]++;
                    } else {
                        a.numericCols[c] = false;
                        a.invalidCount++;
                    }
                }
                if (!a.numericCols[c]) {
                    a.uniqueCats.get(c).add(val);
                }
            }
        }
        return a;
    }

    static boolean isNumeric(String s) {
        if (s.isEmpty()) return false;
        int dotCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '.') {
                dotCount++;
                if (dotCount > 1) return false;
            } else if (c < '0' || c > '9') return false;
        }
        return true;
    }

    static String formatTable(String[][] data, DataAnalysis a) {
        int rows = data.length;
        if (rows == 0) return "";
        int cols = data[0].length;
        int[] colWidths = new int[cols];
        for (int c = 0; c < cols; c++) {
            colWidths[c] = 0;
            for (int r = 0; r < rows; r++) {
                if (data[r][c].length() > colWidths[c]) colWidths[c] = data[r][c].length();
            }
            colWidths[c] = Math.max(colWidths[c], 8);
        }

        StringBuilder sb = new StringBuilder();
        // header border
        sb.append(borderLine(colWidths));
        sb.append("\n|");

        // header row
        for (int c = 0; c < cols; c++) {
            sb.append(padCenter(data[0][c], colWidths[c])).append("|");
        }
        sb.append("\n").append(borderLine(colWidths)).append("\n");

        // data rows
        for (int r = 1; r < rows; r++) {
            sb.append("|");
            for (int c = 0; c < cols; c++) {
                String val = data[r][c];
                boolean highlight = val.isEmpty() || (!a.numericCols[c] && a.uniqueCats.get(c).contains(val) == false);
                if (highlight) val = "*" + val + "*";
                if (a.numericCols[c]) {
                    sb.append(padLeft(formatNumber(val), colWidths[c])).append("|");
                } else {
                    sb.append(padRight(val, colWidths[c])).append("|");
                }
            }
            sb.append("\n");
        }
        sb.append(borderLine(colWidths));
        return sb.toString();
    }

    static String formatNumber(String val) {
        if (val.isEmpty()) return val;
        try {
            double d = Double.parseDouble(val);
            return String.format("%.2f", d);
        } catch (Exception e) {
            return val;
        }
    }

    static String borderLine(int[] widths) {
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int w : widths) {
            for (int i = 0; i < w; i++) sb.append("-");
            sb.append("+");
        }
        return sb.toString();
    }

    static String padRight(String s, int n) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < n) sb.append(' ');
        return sb.toString();
    }

    static String padLeft(String s, int n) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() + s.length() < n) sb.append(' ');
        sb.append(s);
        return sb.toString();
    }

    static String padCenter(String s, int n) {
        int totalPad = n - s.length();
        int padStart = totalPad / 2;
        int padEnd = totalPad - padStart;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padStart; i++) sb.append(' ');
        sb.append(s);
        for (int i = 0; i < padEnd; i++) sb.append(' ');
        return sb.toString();
    }

    static String generateSummaryReport(String[][] data, DataAnalysis a) {
        int rows = data.length - 1;
        int cols = data[0].length;

        StringBuilder sb = new StringBuilder();
        sb.append("Total records processed: ").append(rows).append("\n");
        sb.append("Data completeness: ");
        int totalCells = rows * cols;
        int missing = a.missingCount;
        double completeness = 100.0 * (totalCells - missing) / totalCells;
        sb.append(String.format("%.2f%%\n\n", completeness));

        sb.append("Column-wise statistics:\n");
        for (int c = 0; c < cols; c++) {
            sb.append("Column: ").append(data[0][c]).append("\n");
            if (a.numericCols[c]) {
                sb.append("  Numeric: min=").append(String.format("%.2f", a.minVals[c]))
                  .append(", max=").append(String.format("%.2f", a.maxVals[c]))
                  .append(", avg=").append(String.format("%.2f", a.sumVals[c] / a.validCounts[c]))
                  .append("\n");
            } else {
                sb.append("  Categorical: unique values=").append(a.uniqueCats.get(c).size()).append("\n");
            }
        }
        sb.append("\nData quality issues:\n");
        sb.append("  Missing entries: ").append(a.missingCount).append("\n");
        sb.append("  Invalid numeric entries: ").append(a.invalidCount).append("\n");
        return sb.toString();
    }
}
