package com.testsbd.framework.input;

import com.testsbd.framework.utilities.DatabaseUtility;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by Ranjani.Ilango on 23/06/2017.
 */
public class FormatInput
{
    public void VerifyRelocAndWriteInDatabase(String Reloc, String FlightNo, String[] AllPaxName, String[] AllCheckedInPax,
                                              String[] AllPaxBaggage, String[] BagTagNo, String[] Barcode)
    {
        DatabaseUtility databaseUtility = new DatabaseUtility();
        boolean FullyCheckedIn = false;
        String PassengerName = null, Baggage = null, BarcodeData = null, BagTags = null;

        if (AllPaxName.length == AllCheckedInPax.length)
        {
            FullyCheckedIn = false;
        }

        for (int i=0; i< AllCheckedInPax.length; i++)
        {
            if (ArrayUtils.contains(AllPaxName, AllCheckedInPax[i]))
            {
                int index = ArrayUtils.indexOf(AllPaxName, AllCheckedInPax[i]);
                PassengerName = AllCheckedInPax[i] + ", ";
                Baggage = AllPaxBaggage[index] + ", ";
            }
        }


        for(int i=0; i< Barcode.length; i++)
        {
            if (BarcodeData == null)
            {
                BarcodeData = Barcode[i] + ",";
            }
            else {
                BarcodeData = BarcodeData + Barcode[i] + ",";
            }
        }

        for(int i=0; i<BagTagNo.length; i++)
        {
            if (BagTags == null) {
                BagTags = BagTagNo[i] + ",";
            }
            else
            {
                BagTags = BagTags + BagTagNo[i] + ",";
            }
        }

        databaseUtility.InsertInputValues(Reloc, FlightNo, FullyCheckedIn, PassengerName, Baggage, BarcodeData, BagTags );
    }
}
