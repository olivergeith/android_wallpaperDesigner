
package de.geithonline.wallpaperdesigner.shapes.composed;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import android.graphics.Path;

public class ComposedPath extends Path {

    private final Map<String, Path> pathElements;

    public ComposedPath() {
        pathElements = new HashMap<>();
    }

    @Override
    public void addPath(final Path p) {
        super.addPath(p);
        pathElements.put(UUID.randomUUID().toString(), p);
    }

    public Collection<Path> getPathElements() {
        return pathElements.values();
    }

    public Path getPathElement(final String key) {
        return pathElements.get(key);
    }

}
