package net.explorer;

public class Simulators {
    public static void main(String[] args) {
        OverflowSimulator.start();
    }

    public static double doError(double value, double limit, double intensity) {
        return OverflowSimulator.customOverflowS(OverflowSimulator.precisionError(value, intensity), limit);
    }

    public static double doError(double value, double max) {
        long v = (long) value;
        long m = (long) max;
        return (double) ((((v / m) * Long.MAX_VALUE) / Long.MAX_VALUE) * m);
    }

    public static class OverflowSimulator {
        public static void start() {
//            double value = 999.1;  // Starting value
//
//            System.out.println("Original Value: " + value);
//            value = customOverflowS(value, 512);
//            System.out.println("After Overflow: " + value);

            double value = 1.0;
            double error = precisionError(value, 1.5);
            System.out.println("Precision error for " + value + ": " + error);
        }

        public static double customOverflowS(double value, double limit) {
            while (value >= limit || value < -limit) {
                if (value >= limit)
                    value = value - limit - limit;
                else if (value < -limit)
                    value = value + limit + limit;
            }

            return value;
        }

        public static double precisionError(double value) {
            double result = value;
            double epsilon = 1.0;

            while (value + epsilon != value) {
                epsilon /= 2.0;
                result = epsilon;
            }

            return result + value;
        }

        public static double precisionError(double value, double intensity) {
            double result = value;
            double epsilon = 1.0;

            while (value + (epsilon * intensity) != value) {
                epsilon /= 2.0;
                result = epsilon * (Math.pow(10, intensity) * intensity);
            }

            return result + value;
        }
    }
}
