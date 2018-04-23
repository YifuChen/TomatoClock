import javax.swing.*;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TomatoTimer {
    private int remainingSeconds;
    ScheduledExecutorService ses;

    public TomatoTimer(int lengthMin) {
        this.remainingSeconds = lengthMin * 60;
        final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public String getMinSec() {
        int min = remainingSeconds / 60;
        int sec = remainingSeconds % 60;
        return String.format("%d:%d", min, sec);
    }
}
