import java.util.Random;

/**
 * Created by Spencer on 2/3/2017.
 */

public class PercolationStats {

    /* 3 command line parameters
    * N - size
    * T - amount of repetitions
    * type - string that determines if its fast or slow; ie which type of union method to use */

    public static double stdDev(int counts[], double mean) {
        double powerSums = 0;
        for (int count: counts) {
            powerSums += (count - mean) * (count - mean);
        }
        return Math.sqrt(powerSums / counts.length);
    }

    public static double stdDev(double times[], double mean) {
        double powerSums = 0;
        for (double time: times) {
            powerSums += (time - mean) * (time - mean);
        }
        return Math.sqrt(powerSums / times.length);
    }

    public static void main(String[] args) {

        Random r = new Random();
        boolean weighted = false;
        if (args[2].equals("fast")) {
            weighted = true;
        }
        else if (args[2].equals("slow")) {
            weighted = false;
        }
        int N = Integer.valueOf(args[0]);
        int T = Integer.valueOf(args[1]);
        int counts[] = new int[T];
        double times[] = new double[T];

        for (int i = 0; i < T; i++) {
            int count = 0;
            double start = System.nanoTime();
            if (weighted) {
                Percolation p = new Percolation(N);
                while (!p.percolates()) {
                    int x = r.nextInt(N);
                    int y = r.nextInt(N);
                    if (!p.isOpen(x, y)) {
                        p.open(x, y);
                        count++;
                    }
                }
            }
            else {
                PercolationQuick pQ = new PercolationQuick(N);
                while (!pQ.percolates()) {
                    int x = r.nextInt(N);
                    int y = r.nextInt(N);
                    if (!pQ.isOpen(x, y)) {
                        pQ.open(x, y);
                        count++;
                    }
                }
            }
            double end = System.nanoTime();
            counts[i] = count;
            times[i] = (end - start)/1000000000;
        }

        int totalCount = 0;
        for (int num: counts) {
            totalCount += num;
        }

        double totalTime = 0;
        for (double time: times) {
            totalTime += time;
        }

        double meanThresh = (double)totalCount/T;
        double stdDev = stdDev(counts, meanThresh);
        double meanTime = totalTime / T;
        double sdTime = stdDev(times, meanTime);

        System.out.println("**OUTPUT BELOW**");
        System.out.println("mean threshold=" + meanThresh);
        System.out.println("std dev=" + stdDev);
        System.out.println("time=" + totalTime);
        System.out.println("mean time=" + meanTime);
        System.out.println("stddev time=" + sdTime);
    }

}
