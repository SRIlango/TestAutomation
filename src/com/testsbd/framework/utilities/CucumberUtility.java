package com.testsbd.framework.utilities;

import cucumber.api.DataTable;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Ranjani.Ilango on 6/06/2017.
 */
public class CucumberUtility
{
    private static Dictionary<String, DataCollection> _data = new Hashtable<>();

    public static Dictionary<String, DataCollection> ConvertDataTableToDictionary(DataTable table)
    {
        List<List<String>> data = table.raw();

        int Row =0;
        for(List<String> column:data)
        {
            for (int i=0; i< column.size(); i++)
            {
                _data.put(data.get(0).get(i), new DataCollection(data.get(0).get(i), column.get(i), Row));
            }
            Row++;
        }
        return  _data;
    }

    public static String GetCellValue(String ColumnName)
    {
        return _data.get(ColumnName).ColumnValue;
    }

    private static class DataCollection
    {
        private String ColumnName;
        private String ColumnValue;
        private int RowNumber;

        public DataCollection(String columnName, String columnValue, int rowNumber)
        {
            ColumnName = columnName;
            ColumnValue = columnValue;
            RowNumber = rowNumber;
        }
    }
}
