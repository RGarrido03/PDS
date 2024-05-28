package ex5;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectorySizeCalculator extends SimpleFileVisitor<Path> {
    private final Path startPath;
    private final boolean recursive;
    private long size = 0;

    public DirectorySizeCalculator(Path startPath, boolean recursive) {
        this.startPath = startPath;
        this.recursive = recursive;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return recursive || dir == startPath ? FileVisitResult.CONTINUE : FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (attrs.isRegularFile()) {
            System.out.println(file.toString() + ": " + attrs.size() / 1024 + " kB");
            size += attrs.size() / 1024;
        }
        return FileVisitResult.CONTINUE;
    }

    public long getSize() {
        return size;
    }
}

