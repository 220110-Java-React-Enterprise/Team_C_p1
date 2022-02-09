package Utilities;

import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {

    private static FileLogger fileLogger;
    private static String filePath;
    private static int stackTraceSize;

    private FileLogger(){
        filePath = "Logs/";
        stackTraceSize = 10;
    }

    public static FileLogger getFileLogger(){
        if(fileLogger == null){
            fileLogger = new FileLogger();
        }
        return fileLogger;
    }

    public static FileLogger getFileLogger(String path){
        if(fileLogger == null){
            fileLogger = new FileLogger();
        }
        filePath = path;
        return fileLogger;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        FileLogger.filePath = filePath;
    }

    public static void log(Exception e){

        StringBuilder sb = new StringBuilder();
        sb.append(fileLogger.getTimeStamp())
                .append(" ")
                .append(e.getMessage())
                .append("\n")
                .append(fileLogger.formatStackTRace(e));

        fileLogger.writeToLog(sb.toString());
    }

    public static void log(String s){
        StringBuilder sb = new StringBuilder();
        sb.append(fileLogger.getTimeStamp())
                .append(" ---  ")
                .append(s)
                .append("\n");

        fileLogger.writeToLog(sb.toString());
    }

    private String getTimeStamp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
        return formatter.format(LocalDateTime.now());

    }


    private String formatStackTRace(Exception e){
        StackTraceElement[] stackTrace =  e.getStackTrace();
        StringBuilder sb = new StringBuilder();
        for(int i = 2;i<2+stackTraceSize;i++){
            if(i >= stackTrace.length){
                break;
            }
            sb.append("\t");
            sb.append(stackTrace[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    private String getFileName(){
        return filePath + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+ ".log";

    }

    private void writeToLog(String s) {
        String filename = getFileName();
        try (Writer fileWrite = new FileWriter(filename, true)) {
            fileWrite.write(s);
        } catch (Exception e) {
            // whats to do here ?
            e.printStackTrace();

        }
    }

}
