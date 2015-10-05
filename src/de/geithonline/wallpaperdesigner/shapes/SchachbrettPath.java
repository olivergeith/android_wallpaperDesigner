package de.geithonline.wallpaperdesigner.shapes;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.ArrowPath.ARROW_TYPE;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.HeartPath.HEART_SHAPE;
import de.geithonline.wallpaperdesigner.shapes.SquarePath.SQUARE_STYLE;

public class SchachbrettPath extends Path {

	public enum BRETT_SHAPE {
		Star, Square, Circle, Pillow, Heart, Arrow, Triangle;
	}

	public SchachbrettPath(final PointF center, final float radius, final int count, final BRETT_SHAPE shape) {
		super();
		float raster;
		final List<PointF> points = new ArrayList<>();
		final float offset = radius * 0.05f;
		float patternRadius;

		switch (count) {
			default:
			case 1:
				raster = radius / 1;
				patternRadius = raster - offset;
				points.add(new PointF(center.x, center.y));
				break;
			case 2:
				raster = radius / 2;
				patternRadius = raster - offset;
				points.add(new PointF(center.x - 1 * raster, center.y - 1 * raster));
				points.add(new PointF(center.x + 1 * raster, center.y - 1 * raster));
				points.add(new PointF(center.x - 1 * raster, center.y + 1 * raster));
				points.add(new PointF(center.x + 1 * raster, center.y + 1 * raster));
				break;
			case 3:
				raster = radius / 3;
				patternRadius = raster - offset;
				for (int x = -2; x <= 2; x = x + 2) {
					for (int y = -2; y <= 2; y = y + 2) {
						points.add(new PointF(center.x + x * raster, center.y + y * raster));
					}
				}
				break;
			case 4:
				raster = radius / 4;
				patternRadius = raster - offset;
				for (int x = -3; x <= 3; x = x + 2) {
					for (int y = -3; y <= 3; y = y + 2) {
						points.add(new PointF(center.x + x * raster, center.y + y * raster));
					}
				}
				break;
		}

		for (final PointF p : points) {
			Path path;
			switch (shape) {
				default:
				case Square:
					path = new SquarePath(p, patternRadius, true, SQUARE_STYLE.NORMAL, Direction.CW);
					break;
				case Star:
					path = new StarPath(5, p, patternRadius, patternRadius / 2, true);
					break;
				case Circle:
					path = new CirclePath(p, patternRadius, patternRadius, true, CIRCLE_STYLE.CIRCLE);
					break;
				case Pillow:
					path = new PillowPath(p, patternRadius);
					break;
				case Heart:
					path = new HeartPath(new PointF(p.x, p.y - patternRadius * 0.3f), patternRadius, false, HEART_SHAPE.Straigth);
					break;
				case Arrow:
					path = new ArrowPath(p, patternRadius, Direction.CW, true, ARROW_TYPE.STRAIGHT_UP);
					break;
				case Triangle:
					path = new ArrowPath(p, patternRadius, Direction.CW, true, ARROW_TYPE.TRIANGLE);
					break;
			}
			addPath(path);
		}
	}
}
