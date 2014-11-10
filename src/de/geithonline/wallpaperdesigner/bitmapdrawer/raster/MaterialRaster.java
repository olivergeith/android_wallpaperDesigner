package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class MaterialRaster implements IRaster {
	protected enum MATERIAL_POSITIONING {
		RANDOM, TOWER;
	}

	private final List<Point> points = new ArrayList<Point>();
	private final int anzahlPatterns;
	private final MATERIAL_POSITIONING positioning;

	public MaterialRaster(final int width, final int height, final int patternRadius, final float overlap, final MATERIAL_POSITIONING positioning) {

		this.positioning = positioning;
		final int abstand = Math.round(patternRadius * 2 * overlap);

		final int anzW = width / abstand + 2;
		final int anzH = height / abstand + 2;

		for (int h = 0; h < anzH; h++) {
			for (int w = 0; w < anzW; w++) {
				if (h > anzH / 2 - 2 && h < anzH / 2 + 2) {
					// random koordinate an der gemalt werden soll
					final int x = w * abstand;
					final int y = h * abstand;
					final Point p = new Point(x, y);
					points.add(p);
				}
			}
		}
		anzahlPatterns = points.size();
	}

	@Override
	public Point drawNextPoint() {
		switch (positioning) {
		case RANDOM:
			return drawRandomPoint();
		default:
		case TOWER:
			return drawNextTowerPoint();
		}
	}

	@Override
	public int getAnzahlPatterns() {
		return anzahlPatterns;
	}

	private Point drawRandomPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		final int location = Randomizer.getRandomInt(-1, size - 1);
		final Point p = points.remove(location);
		return p;
	}

	// private Point drawNextBookPoint() {
	// final int size = points.size();
	// if (size == 0) {
	// return new Point(0, 0);
	// }
	// final int location = 0;
	// final Point p = points.remove(location);
	// return p;
	// }
	//
	// private Point drawNextBookPointBackward() {
	// final int size = points.size();
	// if (size == 0) {
	// return new Point(0, 0);
	// }
	// final int location = size - 1;
	// final Point p = points.remove(location);
	// return p;
	// }

	private boolean top = true;

	private Point drawNextTowerPoint() {
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

	private Point drawNextCenterTowerPoint() {
		final int size = points.size();
		if (size == 0) {
			return new Point(0, 0);
		}
		int location = 0;
		location = Math.round(size / 2); // aus der mitte nehmen
		final Point p = points.remove(location);
		top = !top;
		return p;
	}

}
