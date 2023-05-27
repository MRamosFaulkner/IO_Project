import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.SimpleTimeZone;
import java.nio.file.*;

public class FileManagerProject {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the directory path?");
        String pathInput = scanner.nextLine();

        String directoryPath = ""; //Directory you want to display..path/to/directory
        String sourceFilePath = "path/to/source/file.txt";//Specify the source
        String destinationDirPath = "path/to/destination";


        displayDirectoryContents(directoryPath);

        //Copy the file
        copyFile(sourceFilePath, destinationDirPath);

        //Move the file
        moveFile(sourceFilePath, destinationDirPath);

        //Delete the file
        deleteFile(sourceFilePath);

    }

    public static void copyFile(String sourceFilePath, String destinationDirPath) {

    }
        File sourceFile = new File(sourceFilePath);
        File destinationDir = new File(destinationDirPath);

        if (!sourceFile.exists() || !sourceFile.isFile()) {
            System.out.println("Not a valid source file path");
            return;
        }

        if (!destinationDir.exists() || !destinationDir.isDi)
}



    public static void displayDirectoryContents(String directoryPath) {

        File directory = new File(directoryPath) {

            if (!(directory.exists()) || !directory.isDirectory()) { //Checks to see if dir. exists and if it is one
                System.out.println("Invalid path");
                return;
            }

        File[] files = directory.listFiles();

        if (files == null) {

                System.out.println("Cannot read contents of directory");

                return;

        for (File file : files) {
            String name = file.getName();
            long size = files.length();
            Date lastModified = new Date(file.lastModified());

                System.out.println("File Name: " + name);
                System.out.println("File Size: " + size +  " bytes");
                System.out.println("Last Modified: " + lastModified);
                System.out.println("------------------------------");
            }
        }
    }

}