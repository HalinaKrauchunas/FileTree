package com.efimchick.ifmo.io.filetree;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class FileTreeImpl implements FileTree {

    @Override
    public Optional<String> tree(Path path) throws IOException {

        MyFileVisitor myFileVisitor = new MyFileVisitor();
        File something = new File(String.valueOf(path));

        if (something.isFile()) {
            String size = String.valueOf(something.length());
            return Optional.of(something.getName() + " " + size + " bytes");
        } else if (something.isDirectory()) {
            Files.walkFileTree(path, myFileVisitor);
            return Optional.of(myFileVisitor.getString().toString());
        }
        return Optional.empty();
    }

//    private static void listDirectory(File directory, int level) {
//
//        for (File file : Objects.requireNonNull(directory.listFiles())) {
//            for (int i = 0; i < level; i++) {
//                System.out.print("├─ ");
//            }
//            System.out.println(file.getName());
//
//            if (file.isDirectory()) {
//                listDirectory(file, level + 1);
//            }
//        }
//    }
}
