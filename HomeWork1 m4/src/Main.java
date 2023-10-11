public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        MyThread myThread1 = new MyThread();
        myThread.start();
        myThread1.start();

        try {
            myThread.join();
            myThread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Counter.getValue());

    }
}

    class MyThread extends Thread {

        public void run() {
            for (int i = 0; i < 10000; i++) {
                Counter.increment();
            }
        }

    }

    class Counter {
        private static int c;

        public static void increment() {
            c++;
        }

        public static int getValue() {
            return c;
        }

    }
    //Объяснение:т.к. в современных компьютерах для уменьшения "голодающих" процессов есть определённые автоматические
//методы наш поток может обрубаться до завершения записи числа и следующий поток записывает на его место другой то первый
//поток, когда ему дадут вычислительный ресурс, запишет то число, которое он не успел. Например: поток 1 начал прибавлять
//к 60 1, он его прочёл, вычислил, что следующее число 61, и комп обрубил ему вычислительный ресурс и дал его потоку 2,
//а он в свою очередь прочёл что число 60 и пошёл прибпвлять до 80 и тут уже ему не дали ресурс и начал работать поток 1
//и записал на место 80 число 61.
