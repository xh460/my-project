package com.collageprogram.core.demos;

import java.io.IOException;
import java.nio.file.*;

public class FileUtil {

    public static String moveFile(String oldPath, String newPath, String fileName) throws IOException {
        // 构建源文件路径
        Path sourceFilePath = Paths.get(oldPath, fileName);

        // 构建目标文件夹路径
        Path targetDirectoryPath = Paths.get(newPath);

        // 如果目标文件夹不存在，则创建它
        if (!Files.exists(targetDirectoryPath)) {
            Files.createDirectories(targetDirectoryPath);
        }

        // 构建目标文件路径
        Path targetFilePath = Paths.get(newPath, fileName);

        // 移动文件到目标文件夹
        Files.move(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);

        // 构建移动后的全路径
        String movedFilePath = targetFilePath.toString();

        // 返回移动后的全路径
        return movedFilePath;
    }

    public static void main(String[] args) {
        try {

            String sourcePath = "E:/source";
            String sourceFileName = "E:/new";
            String sourceFileExtension = "newFile.txt";


            String movedFilePath = moveFile(sourcePath, sourceFileName, sourceFileExtension
            );

            System.out.println("File moved to: " + movedFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
