package robertalblas.nordland.system.timer;

import java.util.ArrayList;
import java.util.List;

public class TickTimerManager {
	
	private List<TickTimer> tickTimers = new ArrayList<TickTimer>();
	
	public TickTimerManager(){
		this.tickTimers = new ArrayList<TickTimer>();
	}
	
	public void addTickTimer(TickTimer tickTimer){
		tickTimers.add(tickTimer);
	}
	
	public void removeTickTimer(TickTimer tickTimer){
		tickTimers.remove(tickTimer);
	}
	
	public void tick(){	
		for(TickTimer tt : tickTimers){
			tt.tick();
		}
	}
}
