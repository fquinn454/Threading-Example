import java.util.Scanner;

public class ResponsiveUI implements Runnable {
    private static final int LIMIT = 10;
    private static String buffer = "";
    private final int duration;
    private final int taskId;

    public ResponsiveUI(int time, int task) {
        this.duration = time;
        this.taskId = task;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < LIMIT; i++) {
            System.out.print("Enter the duration in milliseconds of task "
                    + i + ": ");
            int newTime = sc.nextInt();
            Thread t = new Thread(new ResponsiveUI(newTime, i));
            t.start();
            if (!buffer.equals("")) {
                System.out.println("Finished tasks: " + buffer);
                buffer = "";
            }
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Finished Task: " + taskId);
        synchronized (buffer) {
            if (!buffer.equals("")) buffer += "," + taskId;
            else buffer = "" + taskId;
        }
    }
}

