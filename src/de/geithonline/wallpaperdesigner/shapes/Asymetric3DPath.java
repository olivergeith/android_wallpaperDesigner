package de.geithonline.wallpaperdesigner.shapes;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Path;
import android.graphics.PointF;

public class Asymetric3DPath {

	public enum ASYMETRIC_3D_STYLE {
		PYRAMIDE;
	}

	private static final List<Path> pathes = new ArrayList<Path>();

	public static List<Path> getPathList(final PointF center, final float radius, final int height,
			final ASYMETRIC_3D_STYLE style) {
		pathes.clear();
		switch (style) {
		default:
		case PYRAMIDE:
			return drawPyramide(center, radius, height);
		}

	}

	private static List<Path> drawPyramide(final PointF center, final float radius, final int height) {

		final Path p1 = new Path();
		p1.moveTo(center.x, center.y);
		p1.lineTo(center.x - radius, center.y - height + radius / 2);
		p1.lineTo(center.x, center.y - height + radius);
		p1.close();
		pathes.add(p1);

		final Path p2 = new Path();
		p2.moveTo(center.x, center.y);
		p2.lineTo(center.x, center.y - height + radius);
		p2.lineTo(center.x + radius, center.y - height + radius / 2);
		p2.close();
		pathes.add(p2);

		final Path p3 = new Path();
		p3.moveTo(center.x, center.y - height + radius);
		p3.lineTo(center.x - radius, center.y - height + radius / 2);
		p3.lineTo(center.x, center.y - height);
		p3.lineTo(center.x + radius, center.y - height + radius / 2);
		p3.close();
		pathes.add(p3);

		return pathes;
	}

}
