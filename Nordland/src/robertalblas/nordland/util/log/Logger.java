package robertalblas.nordland.util.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	private boolean writeToOutputStream;
	private boolean showDebug;
	private boolean showWarning;
	private boolean showError;
	
	public static final int LOGTYPE_DEBUG = 1;
	public static final int LOGTYPE_WARNING = 2;
	public static final int LOGTYPE_ERROR = 3;
	
	public Logger() {
		showDebug = true;
		showWarning = true;
		showError = true;

		writeToOutputStream = false;
	}

	public Logger(boolean writeToOutputStream) {
		this.writeToOutputStream = writeToOutputStream;
		showDebug = true;
		showWarning = true;
		showError = true;
	}

	public boolean isShowDebug() {
		return showDebug;
	}

	public void setShowDebug(boolean showDebug) {
		this.showDebug = showDebug;
	}

	public boolean isShowWarning() {
		return showWarning;
	}

	public void setShowWarning(boolean showWarning) {
		this.showWarning = showWarning;
	}

	public boolean isShowError() {
		return showError;
	}

	public void setShowError(boolean showError) {
		this.showError = showError;
	}

	public void log(String message, int logType) {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];

		if (writeToOutputStream) {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
			Date date = new Date();
			String time = dateFormat.format(date);
			
			if (logType == LOGTYPE_DEBUG && showDebug) {
				System.out.println(time + " DEBUG: " + " " + e.getClassName() + "."
						+ e.getMethodName() + "(): \n" + message);
			}
			if (logType == LOGTYPE_ERROR && showError) {
				System.err.println(time + "ERROR: " + " " + e.getClassName() + "."
						+ e.getMethodName() + "(): \n" + message);
			}
			if (logType == LOGTYPE_WARNING && showWarning) {
				System.err.println(time + " WARNING: " + " " + e.getClassName() + "."
						+ e.getMethodName() + "(): \n" + message);
			}
		}
	}
}
