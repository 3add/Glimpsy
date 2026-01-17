package threeadd.glimpsy.feature.help.article;

import net.kyori.adventure.text.Component;
import threeadd.glimpsy.util.text.markdown.MarkdownRenderer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record Article(Component title, Component content, LocalDateTime creationDate, List<String> authors) {

    public static Article deserialize(Map<String, Object> args) {

        Component title = Component.text((String) args.get("title"));
        Component content = MarkdownRenderer.render((String) args.get("content"));

        Instant creationInstant = Instant.ofEpochSecond((int) args.get("timeOfCreation"));
        LocalDateTime creationDate = LocalDateTime.ofInstant(creationInstant, ZoneOffset.UTC);

        List<?> authorsListRaw = (List<?>) args.get("authors");

        List<String> authors = new ArrayList<>();
        for (Object obj : authorsListRaw) {
            if (obj instanceof String s) {
                authors.add(s);
            } else {
                throw new IllegalArgumentException("Author must be a string");
            }
        }

        return new Article(
                title,
                content,
                creationDate,
                authors
        );
    }
}
