package robertalblas.nordland.system.timer;

import java.util.ArrayList;
import java.util.List;

public class TickTimerManager {

	private List<TickTimer> tickTimers = new ArrayList<TickTimer>();

	public TickTimerManager() {
		this.tickTimers = new ArrayList<TickTimer>();
	}

	public void addTickTimer(TickTimer tickTimer) {
		tickTimers.add(tickTimer);
	}

	public void removeTickTimer(TickTimer tickTimer) {
		tickTimers.remove(tickTimer);
	}

	public List<TickTimer> findTickTimers(TickTimerRunnable r) {
		List<TickTimer> foundTimers = new ArrayList<TickTimer>();
		
		for (TickTimer t : tickTimers) {
			if(t.getRunnable() == r){
				foundTimers.add(t);
			}
		}
		
		return foundTimers;
	}

	public void tick() {
		for (int i = 0; i < tickTimers.size(); i++) {
			try {
				TickTimer tt = tickTimers.get(i);
				tt.tick();
			} catch (IndexOutOfBoundsException e) {

			}

		}
	}
}
