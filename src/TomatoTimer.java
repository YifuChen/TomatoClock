import javax.swing.*;
import java.time.Clock;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TomatoTimer {

    private int totalSeconds;
    private int remainingSeconds;
    private int passedSeconds;
    private ScheduledExecutorService ses;
    private ClockFace cf;
    protected boolean isPaused;
    private int tickInSec;

    private int sessionTime;
    private int breakTime;
    private int status;  // 1: session; 2: break

    public TomatoTimer(int sessionTimeMin, int breakTimeMin, ClockFace cf) {
        this.cf = cf;

        this.sessionTime = sessionTimeMin;
        this.breakTime = breakTimeMin;
        this.status = 1;

        this.totalSeconds = sessionTimeMin * 60;
        this.remainingSeconds = sessionTimeMin * 60;
        this.passedSeconds = 0;
        this.tickInSec = remainingSeconds / 25;
        this.isPaused = true;

        ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

                cf.setTime(getMinSec());

                if (!isPaused) {
                    remainingSeconds--;
                    passedSeconds++;
                    cf.updateTick((int) (passedSeconds / (totalSeconds/50.0)));
                }

                if (remainingSeconds == 0) {
                    if (status == 1) {
                        setStatus(2);
                        cf.resetTick();
                        cf.setStatus(getStatus());
                        remainingSeconds = breakTime * 60;
                        totalSeconds = remainingSeconds;
                        passedSeconds = 0;
                    } else if (status == 2) {
                        setStatus(1);
                        cf.resetTick();
                        cf.setStatus(getStatus());
                        remainingSeconds = sessionTime * 60;
                        totalSeconds = remainingSeconds;
                        passedSeconds = 0;
                    }
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }


    public void setSessionTime(int sessionTimeMin) {
        this.sessionTime = sessionTimeMin;
    }


    public void setBreakTime(int breakTimeMin) {
        this.breakTime = breakTimeMin;
    }


    public String getStatus() {
        if (this.status == 1) {
            return "- in session -";
        } else {
            return "- in break -";
        }
    }

    public void setStatus(int s) {
        this.status = s;
    }

    public String getMinSec() {
        int min = remainingSeconds / 60;
        int sec = remainingSeconds % 60;
        return sec < 10 ? String.format("%d:0%d", min, sec) : String.format("%d:%d", min, sec);
    }

    public int getRemainingSeconds() {
        return this.remainingSeconds;
    }

    public boolean togglePause() {
        isPaused = !isPaused;
        return isPaused;
    }

    public void destroy() {
        ses.shutdown();
    }
}
