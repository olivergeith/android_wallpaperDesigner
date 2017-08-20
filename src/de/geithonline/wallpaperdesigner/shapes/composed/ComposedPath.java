
package de.geithonline.wallpaperdesigner.shapes.composed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.graphics.Path;

public class ComposedPath extends Path {

	private final List<Path> pathElements = new ArrayList<>();

	public ComposedPath() {
	}

	@Override
	public void addPath(final Path p) {
		super.addPath(p);
		pathElements.add(p);
	}

	public Collection<Path> getPathElements() {
		return pathElements;
	}

}
