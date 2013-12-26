package robertalblas.nordland.system.timer;

import robertalblas.nordland.system.util.TickUtil;

public class TickTimer {

	private int deltaTick;
	private int currentTick;
	private TickTimerRunnable tickTimerRunnable;

	public static TickTimer createTimer(TickTimerRunnable tickTimerRunnable, int timeoutMilliseconds) {
		int deltaTick = TickUtil.convertMillisecondsToTicks(timeoutMilliseconds);
		return new TickTimer(deltaTick, tickTimerRunnable);
	}

	private TickTimer(int deltaTick, TickTimerRunnable tickTimerRunnable) {
		this.deltaTick = deltaTick;
		this.tickTimerRunnable = tickTimerRunnable;
		this.currentTick = 0;
	}

	public void setTimeout(int milliseconds) {
		this.deltaTick = TickUtil.convertMillisecondsToTicks(milliseconds);
	}

	public int getTimeout() {
		return TickUtil.convertTicksToMilliseconds(deltaTick);
	}

	public void tick() {
		currentTick++;
		if (currentTick == deltaTick) {
			tickTimerRunnable.run();
			currentTick = 0;
		}
	}
}
