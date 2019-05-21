package ru.stwtforever.schedule.io;

import android.os.Build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigInteger;

import ru.stwtforever.schedule.util.ArrayUtil;

public class FileStreams {
    public static final char lineSeparatorChar = lineSeparator().charAt(0);

    public static final int ONE_KB = 1024;
    public static final int ONE_MB = ONE_KB * 1024;
    public static final int ONE_GB = ONE_MB * 1024;

    public static final long ONE_TB = ONE_GB * 1024L;
    public static final long ONE_PB = ONE_TB * 1024L;
    public static final long ONE_EB = ONE_PB * 1024L;

    public static final BigInteger ONE_ZB = BigInteger.valueOf(ONE_EB).multiply(BigInteger.valueOf(1024L));
    public static final BigInteger ONE_YB = ONE_ZB.multiply(BigInteger.valueOf(1024L));

    // only static methods
    private FileStreams() {
    }

    /**
     * Reads text from specified file
     *
     * @param from the file to read from
     * @throws IOException if an I/O error occurs reading from the stream
     */
    public static String read(File from) throws IOException {
        return EasyStreams.read(reader(from));
    }


    /**
     * Writes text data into specified file.
     *
     * @param from the text data to write
     * @param to   the file to read into
     * @throws IOException if an I/O error occurs writing to file
     */
    public static void write(String from, File to) throws IOException {
        EasyStreams.write(from, writer(to));
    }

    /**
     * Writes byte array into specified file.
     *
     * @param from the bytes to write
     * @param to   the file to read into
     * @throws IOException if an I/O error occurs writing to file
     */
    public static void write(byte[] from, File to) throws IOException {
        EasyStreams.write(from, new FileOutputStream(to));
    }

    /**
     * Appends byte array into specified file.
     *
     * @param from the bytes to append
     * @param to   the file to read into
     * @throws IOException if an I/O error occurs writing to file
     */
    public static void append(byte[] from, File to) throws IOException {
        EasyStreams.write(from, new FileOutputStream(to, true));
    }

    /**
     * Appends characters array into specified file.
     *
     * @param from the bytes to append
     * @param to   the file to read into
     * @throws IOException if an I/O error occurs writing to file
     */
    public static void append(char[] from, File to) throws IOException {
        EasyStreams.write(from, new FileWriter(to, true));
    }

    /**
     * Appends text data into specified file.
     *
     * @param from the text data to write
     * @param to   the file to read into
     * @throws IOException if an I/O error occurs writing to file
     */
    public static void append(CharSequence from, File to) throws IOException {
        EasyStreams.write(from instanceof String
                ? ((String) from)
                : from.toString(), new FileWriter(to, true));
    }

    /**
     * Clean and delete directory or file
     *
     * @param dir the dir to delete
     */
    public static void delete(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                delete(file);
            }
        } else {
            dir.delete();
        }
    }

    /**
     * Returns the system-dependent line separator.
     * Used to split lines on text file. On Android, this is {@code "\n"}
     */
    public static String lineSeparator() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return System.lineSeparator();
        }
        return "\n";
    }

    /**
     * Searches file by name on specified dir.
     *
     * @param dir  the parent directory
     * @param name the file name to search
     * @throws IllegalArgumentException if specified file directory is file
     */
    public static File search(File dir, String name) {
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("dir can't be file.");
        }

        File[] files = dir.listFiles();
        if (ArrayUtil.isEmpty(files)) {
            return null;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                search(file, name);
            } else if (file.getName().contains(name)) {
                return file;
            }
        }

        return null;
    }

    /**
     * Creates a new {@link Reader} for specified file to read.
     *
     * @param from the file to read
     * @throws FileNotFoundException if file don't exist
     */
    public static Reader reader(File from) throws FileNotFoundException {
        return new InputStreamReader(new FileInputStream(from), Charsets.UTF_8);
    }

    /**
     * Creates a new {@link Writer} for specified file to write.
     *
     * @param to the file to write
     * @throws FileNotFoundException if file can't be opened for writing
     */
    public static Writer writer(File to) throws FileNotFoundException {
        return new OutputStreamWriter(new FileOutputStream(to), Charsets.UTF_8);
    }
}
