package cc.kpug.benedict.provider.service;

import static java.util.stream.Collectors.toList;

import cc.kpug.benedict.provider.GitRepositoryInfo;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaFileExtractor {

    private static final String GIT_CONTENT_PATH = "https://raw.githubusercontent.com";

    private JavaFileExtractor() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static List<String> extract(final GitRepositoryInfo info) {
        log.info("Requested GitRepositoryInfo is: {}", info);

        final Stream<ZipEntry> stream;
        try (
            final InputStream is = new URL(info.getDownloadUrl()).openStream();
            final ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is))
        ) {
            ZipEntry zEntry;
            final Stream.Builder<ZipEntry> streamBuilder = Stream.builder();
            while ((zEntry = zis.getNextEntry()) != null) {
                streamBuilder.add(zEntry);
            }
            stream = streamBuilder.build();
        } catch (IOException e) {
            throw new RuntimeException("Something wrong due to open the given url. [" + info.getDownloadUrl() + "]", e);
        }

        return stream.map(ZipEntry::getName)
            .map(v -> v.substring(v.indexOf(File.separator)))
            .filter(v -> !v.startsWith("/src/test/java"))
            .filter(v -> v.endsWith(".java"))
            .filter(v -> !v.endsWith("/package-info.java"))
            .map(v -> GIT_CONTENT_PATH + "/" + info.getFullName() + "/" + info.getDefaultBranch() + v)
            .collect(toList());
    }

}
