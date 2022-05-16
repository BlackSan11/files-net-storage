package ru.gb.file.net.storage.netty;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class FileSaw {

    public static void main(String[] args) {
        FileSaw fileSaw = new FileSaw();
        Path path = Paths.get(URI.create("file:/Users/bchervoniy/IdeaProjects/files-net-storage/client-dir/img.png"));
        fileSaw.saw(path, System.out::println);
    }

    private static final int MB_8 = 4_000_000;

    public void saw(Path path, Consumer<byte[]> filePartConsumer) {
        byte[] filePart = new byte[MB_8];
        try (FileInputStream fileInputStream = new FileInputStream(path.toFile())) {
            while (fileInputStream.read(filePart) != -1) {
                //код обработки части файла
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
