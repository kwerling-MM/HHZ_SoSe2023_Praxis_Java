package com.github.vh.skvs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StoreFactory {

    public static Store createStore(String name) {
        return new Store(Paths.get(""), name);
    }

    public static Store createStore(String path, String name) throws IOException {
        Path pth = Paths.get(path);
        if (!Files.exists(pth)) {
            Files.createDirectory(pth);
        }

        return new Store(pth, name);
    }

    public static Store createStore(Path path, String name) {
        return new Store(path, name);
    }
}
