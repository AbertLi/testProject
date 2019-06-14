package one.example.com.runtime.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtils {
    public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        } else {
            try {
                if (file.isDirectory()) {
                    cleanDirectory(file);
                }
            } catch (Exception var3) {
            }

            try {
                return file.delete();
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static void cleanDirectory(File directory) throws IOException {
        File[] files = verifiedListFiles(directory);
        IOException exception = null;
        File[] var3 = files;
        int var4 = files.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            File file = var3[var5];

            try {
                forceDelete(file);
            } catch (IOException var8) {
                exception = var8;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }

    private static File[] verifiedListFiles(File directory) throws IOException {
        String message;
        if (!directory.exists()) {
            message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        } else if (!directory.isDirectory()) {
            message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        } else {
            File[] files = directory.listFiles();
            if (files == null) {
                throw new IOException("Failed to list contents of " + directory);
            } else {
                return files;
            }
        }
    }

    public static void forceDelete(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                deleteDirectory(file);
            } else {
                boolean filePresent = file.exists();
                if (!file.delete()) {
                    if (!filePresent) {
                        throw new FileNotFoundException("File does not exist: " + file);
                    }

                    String message = "Unable to delete file: " + file;
                    throw new IOException(message);
                }
            }

        }
    }


    public static void deleteDirectory(File directory) throws IOException {
        if (directory.exists()) {
            cleanDirectory(directory);
            if (!directory.delete()) {
                String message = "Unable to delete directory " + directory + ".";
                throw new IOException(message);
            }
        }
    }
}
