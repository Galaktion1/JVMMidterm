public class FileProcessorApp {
    public static void main(String[] args) {
        SimpleFileProcessor fileProcessor = new SimpleFileProcessor();

        try {
            fileProcessor.copyFile("source.txt", "destination.txt");
        } catch (FileCopyException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }

        try {
            fileProcessor.deleteFile("nonexistent.txt");
        } catch (FileDeletionException e) {
            System.out.println("Error deleting file: " + e.getMessage());
        }
    }
}