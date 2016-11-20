package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.GeometrieHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public abstract class AbstractRaster {
	protected static final int WIDE_CANVAS_LIMIT = 3;

	public abstract Point drawNextPoint();

	private RasterPositioning positioning;
	private int abstand;
	protected final List<Point> points = new ArrayList<>();
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
				&& p.y < height + 2 * getSmallTolerance(); // unten nehmen wir eine
															// reihe mehr mit...so wars
															// fr�her
															// auch! Und die Designs
															// sollen ja gleich aussehen
															// wie
															// fr�her
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

	protected Point drawLeftmostPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int topmostIndex = 0; // der erste
		int topX = points.get(0).x;
		for (int i = 0; i < points.size(); i++) {
			final Point p = points.get(i);
			if (p.x < topX) {
				topX = p.x;
				topmostIndex = i;
			}
		}
		final Point p = points.remove(topmostIndex);
		return p;
	}

	protected Point drawPointNearestToGeometricCenter(final int width, final int height) {
		final Point center = new Point(width / 2, height / 2);

		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int nearestCenterIndex = 0; // der erste
		float distance = GeometrieHelper.calcDistance(center, points.get(0));
		for (int i = 0; i < points.size(); i++) {
			final Point p = points.get(i);
			final float d = GeometrieHelper.calcDistance(center, p);
			if (d < distance) {
				distance = d;
				nearestCenterIndex = i;
			}
		}
		return points.remove(nearestCenterIndex);
	}

	protected Point drawPointFarmostToGeometricCenter(final int width, final int height) {
		final Point center = new Point(width / 2, height / 2);

		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int index = 0; // der erste
		float distance = GeometrieHelper.calcDistance(center, points.get(0));
		for (int i = 0; i < points.size(); i++) {
			final Point p = points.get(i);
			final float d = GeometrieHelper.calcDistance(center, p);
			if (d > distance) {
				distance = d;
				index = i;
			}
		}
		return points.remove(index);
	}

	protected Point drawRightmostPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int topmostIndex = 0; // der erste
		int topX = points.get(0).x;
		for (int i = 0; i < points.size(); i++) {
			final Point p = points.get(i);
			if (p.x > topX) {
				topX = p.x;
				topmostIndex = i;
			}
		}
		final Point p = points.remove(topmostIndex);
		return p;
	}

	protected Point drawTopmostPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int topmostIndex = 0; // der erste
		int topY = points.get(0).y;
		for (int i = 0; i < points.size(); i++) {
			final Point p = points.get(i);
			if (p.y < topY) {
				topY = p.y;
				topmostIndex = i;
			}
		}
		final Point p = points.remove(topmostIndex);
		return p;
	}

	protected Point drawBottommostPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int topmostIndex = 0; // der erste
		int topY = points.get(0).y;
		for (int i = 0; i < points.size(); i++) {
			final Point p = points.get(i);
			if (p.y > topY) {
				topY = p.y;
				topmostIndex = i;
			}
		}
		final Point p = points.remove(topmostIndex);
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

	private int state = 0;

	protected Point drawTriStepPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int location = 0;
		switch (state) {
		default:
		case 0:
			location = 0;
			state = 1;
			break;
		case 1:
			location = Math.round(size / 2); // aus der mitte nehmen
			state = 2;
			break;
		case 2:
			location = size - 1; // den hintersten Punkt
			state = 0;
			break;
		}
		final Point p = points.remove(location);
		return p;
	}

	protected Point drawQuadStepPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int location = 0;
		switch (state) {
		default:
		case 0:
			location = 0;
			state = 1;
			break;
		case 1:
			location = Math.round(size / 3); // aus der mitte nehmen
			state = 2;
			break;
		case 2:
			location = Math.round(size * 2 / 3); // aus der mitte nehmen
			state = 3;
			break;
		case 3:
			location = size - 1; // den hintersten Punkt
			state = 0;
			break;
		}
		final Point p = points.remove(location);
		return p;
	}

	protected Point drawDuoCenterPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int location = 0;
		switch (state) {
		default:
		case 0:
			location = Math.round(size * 1 / 3); // aus der mitte nehmen
			state = 1;
			break;
		case 1:
			location = Math.round(size * 2 / 3); // aus der mitte nehmen
			state = 0;
			break;
		}
		final Point p = points.remove(location);
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
