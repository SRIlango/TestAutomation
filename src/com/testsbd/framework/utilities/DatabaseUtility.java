package com.testsbd.framework.utilities;

import com.testsbd.framework.config.ConfigReader;
import com.testsbd.framework.config.FrameworkSettings;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ranjani.Ilango on 15/06/2017.
 */
public class DatabaseUtility
{
    public static Connection connection;
    public static void InitiateConnection() throws IOException
    {
        ConfigReader configReader = new ConfigReader();
        configReader.PopulateSetiings();

        try
        {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(FrameworkSettings.ConnectionString);
        }catch(Exception e)
        {

        }
    }

    public static void InsertInputValues(String Reloc, String FlightNo, boolean AllCheckedIn, String PassengerName, String PassengerBaggage, String BoardingPassStream, String BagTagNo)
    {
        try {
            String InsertQuery = "INSERT INTO [DBO].[vBDAutomationInput] VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(InsertQuery);
            preparedStatement.setString(1, "JQ_OOL_20170623");
            preparedStatement.setString(2, Reloc);
            preparedStatement.setString(3, FlightNo);
            preparedStatement.setBoolean(4, AllCheckedIn);
            preparedStatement.setString(5, PassengerName);
            preparedStatement.setString(6, PassengerBaggage);
            preparedStatement.setString(7, BoardingPassStream);
            preparedStatement.setString(8, BagTagNo);

            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }
}
