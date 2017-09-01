import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FunctionLogging {

    private String path;
    private boolean appendToFile = false;
    private int logLevel;
    private String[] messages = new String[] {"FATAL", "WARNING", "NOTICE", "DEBUG"};

    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy|HH:mm:ss:SSSSSS|");
    private SimpleDateFormat app = new SimpleDateFormat("dd-MM-yyyy");

    /**
     *
     * @param filePath used as a placeholder variable for the output path. Can be hard-coded but is preferredly configurable from properties file
     * @param logLevel set as true when called in each class used in. true appends line to the end of the file, false overwrites file contents
     * @param appendValue TODO
     */
    public FunctionLogging(String filePath, int logLevel, boolean appendValue){
        path = filePath;
        appendToFile = appendValue;
        this.logLevel = logLevel;

    }



    public void writeToFile(int logType, String textLine) {
        if (logType <= logLevel) {
            Date date = new Date();
            String timeStamp = format.format(date);
            String dateApp = app.format(date);

            FileWriter logWriter = null;
            try {
                logWriter = new FileWriter(path + dateApp + ".log", appendToFile);
                try (PrintWriter loggerLines = new PrintWriter(logWriter)) {
                    loggerLines.println(timeStamp + "1|" + messages[logType] + "|" + textLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
