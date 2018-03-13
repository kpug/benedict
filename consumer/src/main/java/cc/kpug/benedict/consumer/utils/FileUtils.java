package cc.kpug.benedict.consumer.utils;

import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.BaseStream;

/**
 * @author Lawrence
 * @note
 * @since 2018. 3. 11.
 * @version 0.0.1
 */
public class FileUtils {
    public static Flux<String> fromPath(Path path) {
        return Flux.using(() -> Files.lines(path),
                Flux::fromStream,
                BaseStream::close
        );
    }
}
