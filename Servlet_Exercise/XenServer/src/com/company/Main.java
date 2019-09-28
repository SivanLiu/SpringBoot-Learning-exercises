package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static ILog textLogger, xmlLogger;

    private static class FileLogger implements ILog
    {
        private FileWriter w;

        public FileLogger(String path)
        {
            try
            {
                w = new FileWriter(path);
            } catch (IOException e)
            {
                System.err.print("Couldn't open " + path + " for log output.");
                e.printStackTrace();
            }
        }

        public void log(String s)
        {
            if (w != null)
            {
                try
                {
                    System.out.print(s);
                    w.write(s);
                    w.flush();
                } catch (IOException e)
                {
                    System.err.print("Couldn't write to log file!");
                    e.printStackTrace();
                }
            }
        }

        public void logln(String s)
        {
            log(s + "\n");
        }
    }

    public static void main(String[] args) {
        // write your code here
        textLogger = new FileLogger("JavaTestOutput.txt");

        TargetServer server = new TargetServer("*****", "***", "****");
        try {
            StartAllVMs.RunTest(textLogger, server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
