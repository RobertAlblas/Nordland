package robertalblas.nordland.system.timer;

import robertalblas.nordland.system.util.TickUtil;

public class TickTimer {

	private int deltaTick;
	private int currentTick;
	private boolean enabled;
	private TickTimerRunnable tickTimerRunnable;

	public static TickTimer createTimer(TickTimerRunnable tickTimerRunnable, int timeoutMilliseconds) {
		int deltaTick = TickUtil.convertMillisecondsToTicks(timeoutMilliseconds);
		return new TickTimer(deltaTick, tickTimerRunnable);
	}

	private TickTimer(int deltaTick, TickTimerRunnable tickTimerRunnable) {
		this.deltaTick = deltaTick;
		this.tickTimerRunnable = tickTimerRunnable;
		this.currentTick = 0;
		this.setEnabled(true);
	}

	public void setTimeout(int milliseconds) {
		this.deltaTick = TickUtil.convertMillisecondsToTicks(milliseconds);
	}

	public int getTimeout() {
		return TickUtil.convertTicksToMilliseconds(deltaTick);
	}

	public void tick() {
		if(enabled){
			currentTick++;
			if (currentTick == deltaTick) {
				tickTimerRunnable.run();
				currentTick = 0;
			}
		}
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public TickTimerRunnable getRunnable() {
		return this.tickTimerRunnable;
	}
}
