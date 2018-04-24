import javax.swing.*;
import java.time.Clock;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TomatoTimer {
    private int remainingSeconds;
    private ScheduledExecutorService ses;
    private ClockFace cf;
    protected boolean isPaused;
    private int tickInSec;

    public TomatoTimer(int lengthMin, ClockFace cf) {
        this.cf = cf;
        this.remainingSeconds = lengthMin * 60;
        this.tickInSec = remainingSeconds / 25;
        this.isPaused = false;
        ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                if (!isPaused) {
                    remainingSeconds--;
                    cf.setTime(getMinSec());
                    cf.updateTick(remainingSeconds / tickInSec + 1);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public String getMinSec() {
        int min = remainingSeconds / 60;
        int sec = remainingSeconds % 60;
        return sec < 10 ? String.format("%d:0%d", min, sec) : String.format("%d:%d", min, sec);
    }

    public boolean togglePause() {
        isPaused = !isPaused;
        return isPaused;
    }

    public void destroy() {
        ses.shutdown();
    }
}
