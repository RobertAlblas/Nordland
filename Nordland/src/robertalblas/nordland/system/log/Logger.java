package robertalblas.nordland.system.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import robertalblas.nordland.exception.InvalidLogTypeException;
import robertalblas.nordland.exception.LogFileWritingException;

public class Logger {

	private boolean writeToOutputStream;
	private boolean showDebug;
	private boolean showWarning;
	private boolean showError;
	private boolean writeToFile;
	private String fileName;

	public static final int LOGTYPE_DEBUG = 1;
	public static final int LOGTYPE_WARNING = 2;
	public static final int LOGTYPE_ERROR = 3;

	public Logger() {
		showDebug = true;
		showWarning = true;
		showError = true;

		writeToOutputStream = false;
		setWriteToFile(true);
	}

	public Logger(boolean writeToOutputStream, String fileName) {
		this.writeToOutputStream = writeToOutputStream;
		showDebug = true;
		showWarning = true;
		showError = true;

		if (fileName != null) {
			setWriteToFile(true);
			this.setFile(fileName);
		}
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
		String outputMessage = getLogMessage(message, logType);

		if (writeToOutputStream) {
			System.out.println(outputMessage);
		}
		if (writeToFile) {
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
				out.println(outputMessage);
				out.close();
			} catch (IOException e) {
				throw new LogFileWritingException(e.getMessage());
			}
		}
	}

	private String getLogMessage(String message, int logType) {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[3];

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
		Date date = new Date();
		String time = dateFormat.format(date);

		if (logType == LOGTYPE_DEBUG && showDebug) {
			return time + " DEBUG: " + " " + e.getClassName() + "." + e.getMethodName() + "(): \n" + message;
		} else if (logType == LOGTYPE_ERROR && showError) {
			return time + " ERROR: " + " " + e.getClassName() + "." + e.getMethodName() + "(): \n" + message;
		} else if (logType == LOGTYPE_WARNING && showWarning) {
			return time + " WARNING: " + " " + e.getClassName() + "." + e.getMethodName() + "(): \n" + message;
		} else {
			throw new InvalidLogTypeException();
		}
	}

	public boolean isWriteToFile() {
		return writeToFile;
	}

	public void setWriteToFile(boolean writeToFile) {
		this.writeToFile = writeToFile;
	}

	public String getFile() {
		return fileName;
	}

	public void setFile(String file) {
		this.fileName = file;
	}

	
}
