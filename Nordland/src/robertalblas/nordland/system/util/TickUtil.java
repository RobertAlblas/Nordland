package robertalblas.nordland.system.util;

import robertalblas.nordland.system.defaults.SystemDefaults;

public class TickUtil {

	public static int convertMillisecondsToTicks(int milliseconds) {		
		return SystemDefaults.TICKS_PER_SECOND_NORMAL / (1000 / milliseconds);
	}

	public static int convertTicksToMilliseconds(int deltaTick) {
		return (deltaTick * 1000) / SystemDefaults.TICKS_PER_SECOND_NORMAL;
	}
	
}
