package com.artyemlavrov.lab5.common.io;

import java.io.*;

enum ReadMode { console, file }

public class IOManager {
    private InputStreamReader inputStreamReader;
    private BufferedWriter bufferedWriter;
    private ReadMode readMode = ReadMode.console;
    private boolean forceWrite = false;

    public IOManager() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public void setInputFile(String path) throws IOException {
        File file = new File(path);
        inputStreamReader = new InputStreamReader(new FileInputStream(file));
        readMode = ReadMode.file;
    }

    public void setOutputFile(String path) throws IOException  {
        File file = new File(path);
        bufferedWriter = new BufferedWriter(new FileWriter(file));
    }

    public void setForceWrite(boolean forceWrite) {
        this.forceWrite = forceWrite;
    }

    public boolean hasNext() {
        try {
            return inputStreamReader.ready();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
        return false;
    }

    public String readNext() {
        StringBuilder builder = new StringBuilder();
        try {
            while (true) {
                if (readMode == ReadMode.file && !hasNext()) break;
                char c = (char) inputStreamReader.read();
                if (c == '\r') continue;
                if (c == '\n') break;
                if (builder.length() != 0 && Character.isWhitespace(c)) break;
                builder.append(c);
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
        String value = builder.toString();
        onReadValue(value);
        return value;
    }

    public String readUntilEnd() throws IOException {
        StringBuilder builder = new StringBuilder();
        while (hasNext()) {
            char c = (char)inputStreamReader.read();
            builder.append(c);
        }
        String value = builder.toString();
        onReadValue(value);
        return value;
    }

    private void onReadValue(String value) {
        if (readMode == ReadMode.file && forceWrite) writeLine(value);
    }

    public void write(Object o) {
        try {
            bufferedWriter.write(o.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public void writeLine(Object o) {
        write(o);
        write("\n");
    }

    public void newLine() {
        writeLine("");
    }
}
