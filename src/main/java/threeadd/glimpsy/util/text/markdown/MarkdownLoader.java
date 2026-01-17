package threeadd.glimpsy.util.text.markdown;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threeadd.glimpsy.Glimpsy;
import threeadd.glimpsy.util.ScheduleUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MarkdownLoader {

    private static final Map<String, Component> CACHE = new ConcurrentHashMap<>();
    private static final Logger log = LoggerFactory.getLogger(MarkdownLoader.class);

    public static CompletableFuture<Component> load(String path) {
        final String resourcePath = path.startsWith("/") ? path.substring(1) : path;

        if (CACHE.containsKey(resourcePath)) {
            return CompletableFuture.completedFuture(CACHE.get(resourcePath));
        }

        CompletableFuture<Component> future = new CompletableFuture<>();
        ScheduleUtil.scheduleAsync(() -> {
            try {
                String rawMarkdown = readFileFromResources(resourcePath);
                Component parsed = MarkdownRenderer.render(rawMarkdown);

                CACHE.put(resourcePath, parsed);

                future.complete(parsed);
            } catch (Throwable t) {
                log.error("Couldn't parse markdown", t);
                future.complete(Component.text("Error loading markdown file: " + resourcePath)
                        .color(NamedTextColor.RED));
            }
        });

        return future;
    }

    public static void clearCache() {
        CACHE.clear();
    }

    private static String readFileFromResources(String path) throws IOException {
        ClassLoader classLoader = Glimpsy.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IOException("File not found in resources: " + path);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        }
    }
}