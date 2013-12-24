package robertalblas.nordland.system.timer;


public class TickTimer {

	private final int deltaTick;
	private int currentTick;
	private TickTimerRunnable tickTimerRunnable;
	
	public TickTimer(int deltaTick, TickTimerRunnable tickTimerRunnable){
		this.deltaTick = deltaTick;
		this.tickTimerRunnable = tickTimerRunnable;
		this.currentTick = 0;
	}
	
	public TickTimer(TickTimerRunnable tickTimerRunnable){
		this(60, tickTimerRunnable);
	}
	
	public void tick(){
		currentTick++;
		if(currentTick == deltaTick){
			tickTimerRunnable.run();
			currentTick = 0;
		}
	}
}
