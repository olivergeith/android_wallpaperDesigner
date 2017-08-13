package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.composed.ComposedPath;

public class D3Path extends ComposedPath {

	public Path seite0 = new Path();
	public Path seite1 = new Path();
	public Path seite2 = new Path();

	public void lineTo(final Path path, final PointF p) {
		path.lineTo(p.x, p.y);
	}

	public void moveTo(final Path path, final PointF p) {
		path.moveTo(p.x, p.y);
	}

}
