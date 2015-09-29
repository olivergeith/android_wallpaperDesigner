package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public abstract class AbstractRaster {
	protected static final int WIDE_CANVAS_LIMIT = 3;

	public abstract Point drawNextPoint();

	private RasterPositioning positioning;
	private int abstand;
	protected final List<Point> points = new ArrayList<Point>();
	private final int radius;

	public AbstractRaster(final int radius, final float overlap) {
		setAbstand(Math.round(radius * 2 * overlap));
		this.radius = radius;
	}

	public final int getAnzahlPatterns() {
		return points.size();
	}

	protected boolean isInsideCanvas(final int width, final int height, final Point p) {
		switch (Settings.getCanvasLimitType()) {
			default:
			case small:
				return isInsideCanvasSmallTolerance(width, height, p);
			case wide:
				return isInsideCanvasWideTolerance(width, height, p);
			case strict:
				return isInsideCanvasStrict(width, height, p);
			case no_limit:
				return true;
		}
	}

	protected boolean isInsideCanvasStrict(final int width, final int height, final Point p) {
		return p.x >= 0 && p.x < width && p.y >= 0 && p.y < height;
	}

	protected boolean isInsideCanvasSmallTolerance(final int width, final int height, final Point p) {
		return p.x >= 0 - getSmallTolerance() //
				&& p.x < width + getSmallTolerance() //
				&& p.y >= 0 - getSmallTolerance() //
				&& p.y < height + 2 * getSmallTolerance(); // unten nehmen wir eine reihe mehr mit...so wars früher
															// auch! Und die Designs sollen ja gleich aussehen wie
															// früher
	}

	protected boolean isInsideCanvasWideTolerance(final int width, final int height, final Point p) {
		return p.x >= 0 - getWideTolerance() //
				&& p.x < width + getWideTolerance() //
				&& p.y >= 0 - getWideTolerance() //
				&& p.y < height + getWideTolerance();
	}

	protected void addPoint2List(final int width, final int height, final Point p) {
		if (isInsideCanvas(width, height, p)) {
			points.add(p);
		}
	}

	protected Point drawRandomPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = Randomizer.getRandomInt(-1, size - 1);
		final Point p = points.remove(location);
		return p;
	}

	protected Point drawNextBookPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = 0;
		final Point p = points.remove(location);
		return p;
	}

	protected Point drawNextBookPointReverse() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = size - 1;
		final Point p = points.remove(location);
		return p;
	}

	protected Point drawNextCenterPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = Math.round(size / 2); // aus der mitte nehmen
		final Point p = points.remove(location);
		return p;
	}

	private boolean top = true;

	protected Point drawNextTowerPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int location = 0;
		if (top) {
			location = size - 1;
		}
		final Point p = points.remove(location);
		top = !top;
		return p;
	}

	public RasterPositioning getPositioning() {
		return positioning;
	}

	public void setPositioning(final RasterPositioning positioning) {
		this.positioning = positioning;
	}

	public int getSmallTolerance() {
		return abstand + 1;
	}

	public int getWideTolerance() {
		return abstand * WIDE_CANVAS_LIMIT + 1;
	}

	public int getAbstand() {
		return abstand;
	}

	public void setAbstand(final int abstand) {
		this.abstand = abstand;
	}

	public int getRadius() {
		return radius;
	}

}
