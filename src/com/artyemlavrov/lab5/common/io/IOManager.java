package com.artyemlavrov.lab5.common.io;

import java.io.*;

enum ReadMode { console, file }

/**
 * Класс, отвечающий за работу с вводом-выводом.
 */
public class IOManager {
    private InputStreamReader inputStreamReader;
    private BufferedWriter bufferedWriter;
    private ReadMode readMode = ReadMode.console;

    public IOManager() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    /**
     * Переключает IOManager на чтение из файла вместо консоли.
     */
    public void setInputFile(String path) throws IOException {
        File file = new File(path);
        inputStreamReader = new InputStreamReader(new FileInputStream(file));
        readMode = ReadMode.file;
    }

    /**
     * Переключает IOManager на вывод в файл вместо консоли.
     */
    public void setOutputFile(String path) throws IOException  {
        File file = new File(path);
        bufferedWriter = new BufferedWriter(new FileWriter(file));
    }

    public boolean hasNext() {
        try {
            return inputStreamReader.ready();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
        return false;
    }

    /**
     * Метод, читающий следующее слово.
     */
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
        return builder.toString();
    }

    /**
     * Читает файл целиком.
     */
    public String readUntilEnd() throws IOException {
        StringBuilder builder = new StringBuilder();
        while (hasNext()) {
            char c = (char)inputStreamReader.read();
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * Вывод строки.
     * @param s Выводимая строка.
     */
    public void write(String s) {
        try {
            bufferedWriter.write(s);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    /**
     * Вывод строки c добавлением переноса строки в конце.
     * @param s Выводимая строка.
     */
    public void writeLine(String s) {
        write(s + "\n");
    }
}
