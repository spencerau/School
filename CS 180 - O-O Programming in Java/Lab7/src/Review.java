/**
 * Created by Spencer on 7/13/2017.
 */
public class Review {

    interface Counter {
        void increment();
        void decrement();
        int get() throws NonZeroException; //throws NonZeroException;
    }

    public static class DecThread implements Runnable {

        private Counter count;

        public DecThread(Counter counter) {
            this.count = counter;
        }

        public void run() {
            for (int i = 0; i < 100000; i++) {
                count.decrement();
            }
        }
    }

    public static class IncThread implements Runnable {

        private Counter count;

        public IncThread(Counter counter) {
            count = counter;
        }

        public void run() {
            for (int i = 0; i < 10000; i++) {
                count.increment();
            }
        }

    }

    public static class NonZeroException extends Exception {

        private int count;

        public NonZeroException(int num) {
            this.count = num;
        }

        public void printStackTrace() {
            System.out.println("Your count was: " + count);
            super.printStackTrace();
        }
    }

    public static class MyBadCounter implements Counter {

        private int count = 0;

        public void increment() {
            count++;
        }

        public void decrement() {
            count--;
        }

        public int get() throws NonZeroException {
            if (count != 0) {
                NonZeroException n = new NonZeroException(count);
                throw n;
            }
            return count;
        }
    }

    public class myGoodCounter implements Counter {

        private int count = 0;

        @Override
        public void increment() {
            //IncThread.run();
            count += 10000;
        }

        @Override
        public void decrement() {
            //DecThread.run();
            count -= 10000;
        }

        @Override
        public int get() throws NonZeroException{
            if (count != 0) {
                NonZeroException n = new NonZeroException(count);
                throw n;
            }
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //myBadCounter bad = new myBadCounter();
        Counter counter = new MyBadCounter();

        Thread inc = new Thread(new IncThread(counter));
        Thread dec = new Thread(new DecThread(counter));

        inc.start();
        dec.start();
        dec.join();
        inc.join();

        try {
            counter.get();
        } catch (NonZeroException e) {
            e.printStackTrace();
        }
    }

}
