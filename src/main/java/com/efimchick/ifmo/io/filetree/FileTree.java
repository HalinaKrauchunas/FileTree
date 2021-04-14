package com.efimchick.ifmo.io.filetree;

import java.io.*;
import java.nio.file.Path;
import java.util.Optional;

public interface FileTree {
    Optional<String> tree(Path path) throws IOException;
}
