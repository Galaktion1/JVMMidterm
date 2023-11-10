import java.io.*;
import java.util.logging.*;

class FileCopyException extends Exception {
    public FileCopyException(String message) {
        super(message);
    }
}

class FileDeletionException extends Exception {
    public FileDeletionException(String message) {
        super(message);
    }
}

interface FileProcessor {
    void copyFile(String sourceFileName, String destinationFileName) throws FileCopyException;
    void deleteFile(String fileName) throws FileDeletionException;
}

class SimpleFileProcessor implements FileProcessor {
    private static final Logger logger = Logger.getLogger(SimpleFileProcessor.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("file_processing_log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void copyFile(String sourceFileName, String destinationFileName) throws FileCopyException {
        try {
            logger.log(Level.INFO, "Copying file {0} to {1}", new Object[]{sourceFileName, destinationFileName});

            File sourceFile = new File(sourceFileName);
            File destinationFile = new File(destinationFileName);

            FileInputStream inputStream = new FileInputStream(sourceFile);
            FileOutputStream outputStream = new FileOutputStream(destinationFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();

            logger.log(Level.INFO, "File copied successfully.");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error occurred during file copy: " + e.getMessage());
            throw new FileCopyException("Error copying file");
        }
    }

    @Override
    public void deleteFile(String fileName) throws FileDeletionException {
        try {
            logger.log(Level.INFO, "Deleting file: {0}", fileName);

            File file = new File(fileName);

            if (file.exists()) {
                if (file.delete()) {
                    logger.log(Level.INFO, "File deleted successfully.");
                } else {
                    logger.log(Level.WARNING, "Error deleting file.");
                    throw new FileDeletionException("Error deleting file");
                }
            } else {
                logger.log(Level.WARNING, "File does not exist.");
                throw new FileDeletionException("File does not exist");
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error occurred during file deletion: " + e.getMessage());
            throw new FileDeletionException("Error deleting file");
        }
    }
}