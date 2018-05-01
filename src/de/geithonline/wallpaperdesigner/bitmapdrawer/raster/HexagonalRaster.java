package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;
import android.graphics.PointF;

public class HexagonalRaster extends AbstractRaster {

	public HexagonalRaster(final int width, final int height, final int radius, final float overlap, final ELayoutVariant positioning,
			final ELayoutSubVariant subVariant) {
		super(radius, overlap, width, height);
		setPositioning(positioning);
		setSubVariant(subVariant);

		final int abstandX = Math.round(radius * 2 * overlap);
		final int abstandY = (int) Math.sqrt(abstandX * abstandX - (abstandX / 2) * (abstandX / 2));

		final int anzW = width / abstandX + 2 * WIDE_CANVAS_LIMIT + 1;
		final int anzH = height / abstandY + 2 * WIDE_CANVAS_LIMIT + 1;

		for (int h = -WIDE_CANVAS_LIMIT; h < anzH; h++) {
			for (int w = -WIDE_CANVAS_LIMIT; w < anzW; w++) {
				// random koordinate an der gemalt werden soll
				final int x = w * abstandX + (h % 2) * abstandX / 2;
				final int y = h * abstandY;
				final Point p = new Point(x, y);
				addPoint2List(width, height, p);
			}
		}
	}

	public static PointF[][] getHexRasterPoints(final PointF center, final float radius, final int raster) {
		final float width = 2 * radius;
		final float height = 2 * radius;

		final float abstandX = width / raster;
		final int abstandY = (int) Math.sqrt(abstandX * abstandX - (abstandX / 2) * (abstandX / 2));
		final int anzX = raster + 1;
		final int anzY = Math.round(height / abstandY);
		final PointF[][] array = new PointF[anzX][anzY];

		final PointF obereLinkeEcke = new PointF(center.x - radius, center.y - radius);
		for (int y = 0; y < anzY; y++) {
			for (int x = 0; x < anzX; x++) {
				final PointF p = new PointF();

				p.x = obereLinkeEcke.x + x * abstandX + (y % 2) * abstandX / 2;
				p.y = obereLinkeEcke.y + y * abstandY;
				array[x][y] = p;
			}
		}
		return array;
	}

}
