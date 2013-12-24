package robertalblas.nordland.system.timer;

import java.util.ArrayList;
import java.util.List;

public class TickTimerManager {
	
	private List<TickTimer> tickTimers;
	
	public TickTimerManager(){
		this.tickTimers = new ArrayList<TickTimer>();
	}
	
	public void addTickTimer(TickTimer tickTimer){
		this.tickTimers.add(tickTimer);
	}
	
	public void removeTickTimer(TickTimer tickTimer){
		this.tickTimers.remove(tickTimer);
	}
	
	public void tick(){
		for(TickTimer tt : tickTimers){
			tt.tick();
		}
	}
}
