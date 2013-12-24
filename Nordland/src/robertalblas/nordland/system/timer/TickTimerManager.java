package robertalblas.nordland.system.timer;

import java.util.ArrayList;
import java.util.List;

public class TickTimerManager {
	
	private static List<TickTimer> tickTimers = new ArrayList<TickTimer>();;
	
	public static void addTickTimer(TickTimer tickTimer){
		tickTimers.add(tickTimer);
	}
	
	public static void removeTickTimer(TickTimer tickTimer){
		tickTimers.remove(tickTimer);
	}
	
	public static void tick(){
		for(TickTimer tt : tickTimers){
			tt.tick();
		}
	}
}
