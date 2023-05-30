import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneOffset;

public class FileManagerProject {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter directory path: ");
        String inputPath = scanner.nextLine();

        try {
            Path directoryPath = Paths.get(inputPath);
            if (!Files.isDirectory(directoryPath)) {
                System.out.println("Not valid directory path.");
                return;
            }

            boolean exit = false;
            while (!exit) {
                displayMenuChoices(scanner);
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        displayContents(directoryPath);
                        break;
                    case 2:
                        copyFile(scanner, directoryPath);
                        break;
                    case 3:
                        moveFile(scanner, directoryPath);
                        break;
                    case 4:
                        deleteFile(scanner, directoryPath);
                        break;
                    case 5:
                        createDirectory(scanner, directoryPath);
                        break;
                    case 6:
                        deleteDirectory(scanner, directoryPath);
                        break;
                    case 7:
                        searchFiles(scanner, directoryPath);
                        break;
                    case 8:
                        exit = true;
                        break;
                    default:
                        System.out.println("Not a valid option.");
                }
            }
        } catch (InvalidPathException e) {
            System.out.println("Invalid path: " + e.getMessage());
        }
    }

    private static void displayMenuChoices(Scanner scanner) {
        System.out.println("\nChoose an option:");
        System.out.println("1. Directory contents");
        System.out.println("2. Copy a file");
        System.out.println("3. Move a file");
        System.out.println("4. Delete a file");
        System.out.println("5. Create a directory");
        System.out.println("6. Delete a directory");
        System.out.println("7. Search for a file");
        System.out.println("8. Exit");

    }

    private static void displayContents(Path directoryPath) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            System.out.println("\nContents:");
            for (Path entry : stream) {
                BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
                String type = attrs.isDirectory() ? "DIR" : "FILE";
                long size = attrs.size();
                LocalDateTime lastModified = LocalDateTime.ofInstant(attrs.lastModifiedTime().toInstant(), ZoneOffset.UTC);
                System.out.printf("%-10s %-10d %-20s %s%n", type, size, lastModified.format(formatter), entry.getFileName());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void copyFile(Scanner scanner, Path dirPath) {
        System.out.println("Enter source file name:");
        String sourceFile = scanner.next();
        System.out.println("Enter target file name:");
        String targetFile = scanner.next();
        Path source = dirPath.resolve(sourceFile);
        Path target = dirPath.resolve(targetFile);

        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private static void moveFile(Scanner scanner, Path dirPath) {
        System.out.println("Enter the source file name:");
        String srcFile = scanner.nextLine();
        System.out.println("Enter the target file name:");
        String tgtFile = scanner.nextLine();
        Path source = dirPath.resolve(srcFile);
        Path target = dirPath.resolve(tgtFile);

        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private static void deleteFile(Scanner scanner, Path dirPath) {
        System.out.println("Enter the file to delete:");
        String fileToDelete = scanner.nextLine();
        Path file = dirPath.resolve(fileToDelete);

        try {
            Files.delete(file);
            System.out.println("File deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private static void createDirectory(Scanner scanner, Path directoryPath) {
        System.out.println("Enter the name of the new directory:");
        String newDir = scanner.nextLine();
        Path dir = directoryPath.resolve(newDir);

        try {
            Files.createDirectory(dir);
            System.out.println("Directory created successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private static void deleteDirectory(Scanner scanner, Path directoryPath) {
        System.out.println("Enter the name of the directory to delete:");
        String dirToDelete = scanner.nextLine();
        Path dir = directoryPath.resolve(dirToDelete);

        try {
            Files.delete(dir);
            System.out.println("Directory deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private static void searchFiles(Scanner scanner, Path directoryPath) {
        System.out.println("Enter the file name or extension to search for:");
        String searchTerm = scanner.nextLine();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath, searchTerm)) {
            System.out.println("\nSearch results:");
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
