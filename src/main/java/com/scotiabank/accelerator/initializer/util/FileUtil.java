package com.scotiabank.accelerator.initializer.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

@Slf4j
public class FileUtil {
    public static void deleteDirectoryQuietly(Path dir) {
        try (Stream<Path> stream = Files.walk(dir)){
            log.info("Cleaning folder {}", dir);

            stream.sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .peek(System.out::println)
                .forEach(File::delete);
        } catch (IOException e) {
            log.error("Cleaning folder {} failed", dir);
        }
    }
}
