package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.settings.Settings;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public abstract class IRaster {
	public abstract Point drawNextPoint();

	private RasterPositioning positioning;

	protected final List<Point> points = new ArrayList<Point>();

	public final int getAnzahlPatterns() {
		return points.size();
	}

	protected boolean isInsideCanvas(final int width, final int height, final Point p) {
		return p.x >= 0 && p.x < width && p.y >= 0 && p.y < height;
	}

	protected void addPoint2List(final int width, final int height, final Point p) {
		if (Settings.isLimit2Canvas()) {
			if (isInsideCanvas(width, height, p)) {
				points.add(p);
			}
		} else {
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

}
