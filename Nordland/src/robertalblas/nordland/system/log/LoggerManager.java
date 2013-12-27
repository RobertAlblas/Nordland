package robertalblas.nordland.system.log;

import java.util.ArrayList;

public class LoggerManager {
	private static LoggerManager instance;
	private ArrayList<Logger> loggers;

	public LoggerManager() {
		loggers = new ArrayList<Logger>();
		createLogger(true);
	}

	public int createLogger(boolean writeToOutputStream) {
		Logger logger = new Logger(writeToOutputStream, "log.txt");
		loggers.add(logger);
		return loggers.size() - 1;
	}

	public static LoggerManager getInstance() {
		if (instance == null) {
			instance = new LoggerManager();
		}
		return instance;
	}

	public Logger getDefaultLogger() {
		return loggers.get(0);
	}
}
