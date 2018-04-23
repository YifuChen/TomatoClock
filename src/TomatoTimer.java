import javax.swing.*;
import java.time.Clock;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TomatoTimer {
    private int remainingSeconds;
    private ClockFace cf;
    ScheduledExecutorService ses;

    public TomatoTimer(int lengthMin, ClockFace cf) {
        this.cf = cf;
        this.remainingSeconds = lengthMin * 60;
        final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                remainingSeconds--;
                cf.setTime(getMinSec());
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public String getMinSec() {
        int min = remainingSeconds / 60;
        int sec = remainingSeconds % 60;
        return String.format("%d:%d", min, sec);
    }


}
