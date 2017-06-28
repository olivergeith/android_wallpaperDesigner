
package de.geithonline.wallpaperdesigner.shapes.composed;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Path;

public class ComposedPath extends Path {

    private final List<Path> pathElements;

    public ComposedPath() {
        pathElements = new ArrayList<>();
    }

    @Override
    public void addPath(final Path p) {
        super.addPath(p);
        pathElements.add(p);
    }

    public List<Path> getPathElements() {
        return pathElements;
    }

}
