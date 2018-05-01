package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.GeometrieHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class AbstractRaster {
	protected static final int WIDE_CANVAS_LIMIT = 3;
	private RasterPositioning positioning;
	private int abstand;
	protected final List<Point> points = new ArrayList<>();
	private final int radius;
	protected final int bWidth;
	protected final int bHeight;

	protected final Point pointTopLeft;
	protected final Point pointTopRight;
	protected final Point pointBottomLeft;
	protected final Point pointBottomRight;
	protected final Point pointCenter;
	private final Point pointCenterTop;
	private final Point pointCenterLeft;
	private final Point pointCenterBottom;
	private final Point pointCenterRight;

	public AbstractRaster(final int radius, final float overlap, final int width, final int height) {
		bWidth = width;
		bHeight = height;
		setAbstand(Math.round(radius * 2 * overlap));
		this.radius = radius;
		pointTopRight = new Point(bWidth, 0);
		pointTopLeft = new Point(0, 0);
		pointBottomRight = new Point(bWidth, bHeight);
		pointBottomLeft = new Point(0, bHeight);
		pointCenter = new Point(bWidth / 2, bHeight / 2);

		pointCenterTop = new Point(bWidth / 2, 0);
		pointCenterLeft = new Point(0, bHeight / 2);
		pointCenterBottom = new Point(bWidth / 2, bHeight);
		pointCenterRight = new Point(bWidth, bHeight / 2);

	}

	public Point drawNextPoint() {
		switch (getPositioning()) {
			default:
			case RANDOM:
				return drawRandomPoint();

			case LEFTMOST:
				return drawLeftmostPoint();
			case TOPMOST:
				return drawTopmostPoint();
			case RIGHTMOST:
				return drawRightmostPoint();
			case BOTTOMMOST:
				return drawBottommostPoint();

			case INNER:
				return drawNearestPoint(pointCenter);
			case OUTER:
				return drawFarestPoint(pointCenter);

			case CENTER:
				return drawCenterPoint();
			case DUO_CENTER:
				return drawDuoCenterPoint();
			case TOWER:
				return drawTowerPoint();
			case TRISTEP:
				return drawTriStepPoint();
			case QUADSTEP:
				return drawQuadStepPoint();

			case DUO_STEP_INNER_2_OUTER:
				return drawDuoStepInner2OuterPoint();
			case DUO_STEP_OUTER_2_INNER:
				return drawDuoStepOuter2InnerPoint();

			case TOP_LEFT_2_BOTTOM_RIGHT:
				return drawNearestPoint(pointTopLeft);
			case TOP_RIGHT_2_BOTTOM_LEFT:
				return drawNearestPoint(pointTopRight);

			case ALTERNATING_TOP_LEFT_BOTTOM_RIGHT:
				return drawAlternatingPoint(pointTopLeft, pointBottomRight);
			case ALTERNATING_TOP_RIGHT_BOTTOM_LEFT:
				return drawAlternatingPoint(pointTopRight, pointBottomLeft);
			case ALTERNATING_TOP_RIGHT_TOP_LEFT:
				return drawAlternatingPoint(pointTopRight, pointTopLeft);
			case ALTERNATING_BOTTOM_RIGHT_BOTTOM_LEFT:
				return drawAlternatingPoint(pointBottomRight, pointBottomLeft);
			case ALTERNATING_LEFT_RIGHT:
				return drawAlternatingPoint(pointCenterLeft, pointCenterRight);
			case ALTERNATING_TOP_BOTTOM:
				return drawAlternatingPoint(pointCenterTop, pointCenterBottom);

			case ALTERNATING:
				return drawAlternatingPoint(pointTopRight, pointTopLeft, pointBottomLeft, pointBottomRight);
			case ALTERNATING_V2:
				return drawAlternatingPoint(pointCenterTop, pointCenterRight, pointCenterBottom, pointCenterLeft);
		}
	}

	public final int getAnzahlPatterns() {
		return points.size();
	}

	private boolean isInsideCanvas(final int width, final int height, final Point p) {
		switch (Settings.getCanvasLimitType()) {
			default:
			case small:
				// unten nehmen wir eine reihe mehr mit...so wars fr�her auch! Und die Designs sollen ja gleich aussehen wie fr�her
				return isInsideCanvasTolerance(width, height, p, getSmallTolerance(), getSmallTolerance(), getSmallTolerance(), getSmallTolerance() * 2);
			case wide:
				return isInsideCanvasTolerance(width, height, p, getWideTolerance());
			case double_wide:
				return isInsideCanvasTolerance(width, height, p, 2 * getWideTolerance());
			case strict:
				return isInsideCanvasTolerance(width, height, p, 0);
			case small_inset:
				return isInsideCanvasTolerance(width, height, p, -getSmallTolerance());
			case wide_inset:
				return isInsideCanvasTolerance(width, height, p, -getWideTolerance());
			case no_limit:
				return true;
		}
	}

	private boolean isInsideCanvasTolerance(final int width, final int height, final Point p, final int tolerance) {
		return isInsideCanvasTolerance(width, height, p, tolerance, tolerance, tolerance, tolerance);
	}

	private boolean isInsideCanvasTolerance(final int width, final int height, final Point p, final int toleranceTop, final int toleranceLeft,
			final int toleranceRight, final int toleranceBottom) {
		return p.x >= 0 - toleranceLeft //
				&& p.x < width + toleranceRight //
				&& p.y >= 0 - toleranceTop //
				&& p.y < height + toleranceBottom;
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
		final int location = Randomizer.getRandomInt(0, size - 1);
		final Point p = points.remove(location);
		return p;
	}

	protected Point drawFirstPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = 0;
		final Point p = points.remove(location);
		return p;
	}

	protected Point drawLastPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = size - 1;
		final Point p = points.remove(location);
		return p;
	}

	protected Point drawCenterPoint() {
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

	protected Point drawPointNearestToGeometricCenter() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int nearestCenterIndex = 0; // der erste
		float distance = GeometrieHelper.calcDistance(pointCenter, points.get(0));
		for (int i = 0; i < points.size(); i++) {
			final Point p = points.get(i);
			final float d = GeometrieHelper.calcDistance(pointCenter, p);
			if (d < distance) {
				distance = d;
				nearestCenterIndex = i;
			}
		}
		return points.remove(nearestCenterIndex);
	}

	protected Point drawPointFarmostToGeometricCenter() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int index = 0; // der erste
		float distance = GeometrieHelper.calcDistance(pointCenter, points.get(0));
		for (int i = 0; i < points.size(); i++) {
			final Point p = points.get(i);
			final float d = GeometrieHelper.calcDistance(pointCenter, p);
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

	protected Point drawTowerPoint() {
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

	protected Point drawDuoStepInner2OuterPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int location = 0;
		switch (state) {
			default:
			case 0:
				location = 0; // vom Anfang
				state = 1;
				break;
			case 1:
				location = Math.round(size * 1 / 2); // aus der mitte der liste nehmen
				state = 0;
				break;
		}
		final Point p = points.remove(location);
		return p;
	}

	protected Point drawDuoStepOuter2InnerPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int location = 0;
		switch (state) {
			default:
			case 0:
				location = size - 1; // vom Ende
				state = 1;
				break;
			case 1:
				location = Math.round(size / 2); // aus der Mitte nehmen
				state = 0;
				break;
		}
		final Point p = points.remove(location);
		return p;
	}

	protected Point drawAlternatingPoint(final Point p1, final Point p2) {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		switch (state) {
			default:
			case 0:
				state = 1;
				return drawNearestPoint(p1);
			case 1:
				state = 0;
				return drawNearestPoint(p2);
		}
	}

	protected Point drawAlternatingPoint(final Point p1, final Point p2, final Point p3, final Point p4) {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		switch (state) {
			default:
			case 0:
				state = 1;
				return drawNearestPoint(p1);
			case 1:
				state = 2;
				return drawNearestPoint(p2);
			case 2:
				state = 3;
				return drawNearestPoint(p3);
			case 3:
				state = 0;
				return drawNearestPoint(p4);
		}
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
				location = 0; // den ersten punkt
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

	protected Point drawNearestPoint(final Point center) {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int index = 0;
		float minDist = 999999;
		for (int i = 0; i < points.size(); i++) {
			final Point p = points.get(i);
			final float dist = GeometrieHelper.calcDistance(p, center);
			if (dist < minDist) {
				minDist = dist;
				index = i;
			}
		}
		final Point p = points.remove(index);
		return p;
	}

	protected Point drawFarestPoint(final Point center) {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int index = 0;
		float maxDist = 0;
		for (int i = 0; i < points.size(); i++) {
			final Point p = points.get(i);
			final float dist = GeometrieHelper.calcDistance(p, center);
			if (dist > maxDist) {
				maxDist = dist;
				index = i;
			}
		}
		final Point p = points.remove(index);
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
