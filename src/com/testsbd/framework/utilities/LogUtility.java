package com.testsbd.framework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Created by Ranjani.Ilango on 8/06/2017.
 */
public class LogUtility
{
    @SuppressWarnings("Since15")
    ZonedDateTime date = ZonedDateTime.now();
    @SuppressWarnings("Since15")
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHMMSS");
    @SuppressWarnings("Since15")
    String logFileNameFormat = date.format(formatter);

    private BufferedWriter bufferedWriter = null;

    public void CreateLogFile()
    {
        try
        {
            File directory = new File("C:/Automation/Logs/");
            if(!directory.exists())
            {
                directory.mkdir();
            }

            File logFile = new File(directory + "/" + logFileNameFormat + ".log");
            FileWriter fileWriter = new FileWriter(logFile.getAbsoluteFile());
            bufferedWriter = new BufferedWriter((fileWriter));
        }
        catch (Exception E){
            E.getStackTrace();
        }
    }

    public void WriteLog(String Message)
    {
        try {
            bufferedWriter.write(Message);
            bufferedWriter.close();
        }catch(Exception E){
            E.getStackTrace();
        }
    }

    public void logMessages(String ErrorType, String Message, String className)
    {
        final Logger logger = LogManager.getLogger(className);
        switch (ErrorType) {
            case "E":
                logger.error(Message);
                break;
            case "I":
                logger.info(Message);
                break;
            case "F":
                logger.fatal(Message);
                break;
        }
    }


}
