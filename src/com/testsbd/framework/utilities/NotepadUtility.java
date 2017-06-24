package com.testsbd.framework.utilities;

import com.testsbd.framework.config.ConfigReader;
import com.testsbd.framework.config.FrameworkSettings;
import com.testsbd.framework.input.FormatInput;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ranjani.Ilango on 21/06/2017.
 */
public class NotepadUtility
{
    public void ReadInput() throws IOException {
        ConfigReader configReader = new ConfigReader();
        configReader.PopulateSetiings();

         //RelocInputFile = "C:/Automation/Input/RelocInput.txt";
        String RelocInputFile = FrameworkSettings.RelocInputFilePath;

        ArrayList <String> RelocList = new ArrayList <>();
        String line = null;

        try
        {
            BufferedReader RelocInput = new BufferedReader(new FileReader(RelocInputFile));
            line = RelocInput.readLine();

            while (line != null)
            {
                RelocList.add(line);
                line = RelocInput.readLine();
            }
        }catch (Exception e)
        {

        }

        String[] arrayReloc = RelocList.toArray(new String[RelocList.size()]);

        //String KioskLogFile = "C:\\Automation\\KioskLogs\\KioskLogs.txt";
        String KioskLogFile = FrameworkSettings.KioskLogsFilePath;
        ArrayList<String> ConsoleOutput = new ArrayList <>();
        ArrayList<String> AllPassengerName = new ArrayList <>();
        ArrayList<String> AllPassengerBaggage = new ArrayList <>();
        ArrayList<String> CheckedInPaxName = new ArrayList <>();
        ArrayList<String> BagTagNo = new ArrayList <>();
        ArrayList<String> BPBarcode = new ArrayList <>();

        ConsoleOutput.add("Starting new kiosk transaction");
        ConsoleOutput.add("FindBooking by Reloc completed in:");
        ConsoleOutput.add("PASSENGERS :");
        ConsoleOutput.add("Reloc:");
        ConsoleOutput.add("FindBooking successful");
        ConsoleOutput.add("Flight:");
        ConsoleOutput.add("Booking Type:");
        ConsoleOutput.add("Requested Bag Count =");
        ConsoleOutput.add("Successfully Checked In");
        ConsoleOutput.add("Bag Tag :");
        ConsoleOutput.add("Generated Print Stream :");
        ConsoleOutput.add("Barcode Data");
        ConsoleOutput.add("Ending kiosk transaction");
        ConsoleOutput.add("Switching to error screen");
        ConsoleOutput.add("User Exit");
        String[] LogContext = ConsoleOutput.toArray(new String[ConsoleOutput.size()]);
        String Reloc = null;
        String FlightNo = null;
        String BagCount = null;

        try
        {
            BufferedReader KioskLogs = new BufferedReader(new FileReader(KioskLogFile));
            line = KioskLogs.readLine();
            int ContinueProcessing = 0;

            while (line !=null)
            {
                if (line.contains(LogContext[0]))
                {
                    line = KioskLogs.readLine();
                    if (line.contains(LogContext[1]))
                    {
                        line = KioskLogs.readLine();
                        if (line.contains(LogContext[2]))
                        {
                            ContinueProcessing = 1;
                            line = KioskLogs.readLine();
                        }
                    }
                }

                while (ContinueProcessing == 1) {
                    if (line.contains("Passenger Name :")) {
                        String[] Splitter = line.split(": ");
                        AllPassengerName.add(Splitter[1]);
                    }
                    if (line.contains("Baggage Allowance :")) {
                        String[] Splitter = line.split(": ");
                        AllPassengerBaggage.add(Splitter[1]);
                    }
                    if (line.isEmpty()) {
                        ContinueProcessing = 2;
                    }
                    line = KioskLogs.readLine();
                }

                String[] arrayPaxName = AllPassengerName.toArray(new String[AllPassengerName.size()]);
                String[] arrayPaxBaggage = AllPassengerBaggage.toArray(new String [AllPassengerBaggage.size()]);

                while (ContinueProcessing == 2) {
                    if (line.contains(LogContext[3])) {
                        String[] Splitter = line.split(": ");
                        Reloc = Splitter[1];
                        line = KioskLogs.readLine();
                        if (line.contains(LogContext[4])) {
                            ContinueProcessing = 3;
                        }
                        line = KioskLogs.readLine();
                    }
                }

                while (ContinueProcessing == 3) {
                    if (line.contains(LogContext[5])) {
                        String[] Splitter = line.split(": ");
                        FlightNo = Splitter[1];
                        ContinueProcessing = 4;
                    }
                    line = KioskLogs.readLine();
                }

                while (ContinueProcessing == 4) {
                    if (line.contains(LogContext[7])) {
                        String[] Splitter = line.split("= ");
                        BagCount = Splitter[1];
                        ContinueProcessing = 5;
                    }
                    if ((line.contains(LogContext[13])) || (line.contains(LogContext[14]))) {
                        ContinueProcessing = 7;
                    }
                    line = KioskLogs.readLine();
                }

                while (ContinueProcessing == 5) {
                    if (line.contains(LogContext[8])) {
                        for (int i = 0; i < arrayPaxName.length; i++) {
                            if (line.contains(LogContext[8])) {
                                String[] Splitter = line.split("- ");
                                String[] splitter = Splitter[1].split(" Successfully");
                                CheckedInPaxName.add(splitter[0]);
                                line = KioskLogs.readLine();
                                ContinueProcessing = 6;
                            }
                            else {
                                break;
                            }
                        }
                    }
                    line = KioskLogs.readLine();
                }

                String[] arrayCheckedPax = CheckedInPaxName.toArray(new String[CheckedInPaxName.size()]);
                while (ContinueProcessing == 6) {
                    int i = 1;
                    if (line.contains(LogContext[9]) && (line.contains((LogContext[10])))) {
                        if (i == Integer.parseInt(BagCount)) {
                            break;
                        }
                        String[] Splitter = line.split("#7A");
                        BagTagNo.add(Splitter[1]);
                    }
                    i = 0;

                    if (line.contains(LogContext[11])) {
                        i = i + 1;
                        String[] Splitter = line.split("= ");
                        BPBarcode.add(Splitter[1]);
                        if (i == arrayCheckedPax.length) {
                            ContinueProcessing = 7;
                        }
                    }
                    line = KioskLogs.readLine();
                }
                String[] arrayBagTagNo = BagTagNo.toArray(new String[BagTagNo.size()]);
                String[] arrayBPBarcode = BPBarcode.toArray(new String[BPBarcode.size()]);

                while (ContinueProcessing == 7) {
                    if (line.contains(LogContext[12])) {
                        ContinueProcessing = 0;
                        VerifyRelocAndWriteInDatabase(arrayReloc, Reloc, FlightNo, arrayPaxName, arrayCheckedPax, arrayPaxBaggage, arrayBagTagNo, arrayBPBarcode);
                    }
                    line = KioskLogs.readLine();
                }
                line = KioskLogs.readLine();
            }
        }catch (Exception E)
        {
            E.getMessage();
        }
    }

    public void VerifyRelocAndWriteInDatabase(String[] RelocList, String ReadReloc, String FlightNo, String[] AllPaxName, String[] AllCheckedInPax, String[] AllPaxBaggage, String[] BagTagNo, String[] Barcode)
    {
        FormatInput formatInput = new FormatInput();
        if (ArrayUtils.contains(RelocList, ReadReloc))
        {
            formatInput.VerifyRelocAndWriteInDatabase(ReadReloc, FlightNo, AllPaxName, AllCheckedInPax, AllPaxBaggage, BagTagNo, Barcode);
        }
    }
}
