package com.efimchick.ifmo.io.filetree;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import java.util.stream.*;

public class MyFileVisitor implements FileVisitor<Path> {

    //    private static final String REGEX = "src\\\\test\\\\resources\\\\";
    private final StringBuilder stringBuilder = new StringBuilder();

    public StringBuilder getString() {

        return stringBuilder;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        stringBuilder.append(dir.toFile().getName() + " " + getFolderSize(dir) + " bytes\n");
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {

        return FileVisitResult.TERMINATE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

        stringBuilder.append(Stream.of(dir.toFile())
            .map(x -> Arrays.stream(Objects.requireNonNull(x.listFiles(File::isFile)))
                .map(y -> y.getName() + " " + y.length() + " bytes\n").sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.joining())
            ).collect(Collectors.joining()));

        return FileVisitResult.CONTINUE;
    }

    public long getFolderSize(Path path) throws IOException {

        return Files.walk(path)
            .map(Path::toFile)
            .filter(File::isFile)
            .mapToLong(File::length)
            .sum();
    }
}
