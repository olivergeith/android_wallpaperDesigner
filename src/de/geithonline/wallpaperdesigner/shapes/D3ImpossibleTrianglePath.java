package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.bitmapdrawer.raster.HexagonalRaster;

public class D3ImpossibleTrianglePath extends D3Path {

	public D3ImpossibleTrianglePath(final PointF center, final float radius) {
		draw(center, radius);
	}

	private void draw(final PointF center, final float radius) {
		// calculating points
		final PointF[][] ps = HexagonalRaster.getHexRasterPoints(center, radius, 9);

		moveTo(seite0, ps[0][8]);
		lineTo(seite0, ps[4][0]);
		lineTo(seite0, ps[5][0]);
		lineTo(seite0, ps[1][7]);
		lineTo(seite0, ps[6][7]);
		lineTo(seite0, ps[7][8]);
		seite0.close();
		addPath(seite0);

		moveTo(seite1, ps[5][0]);
		lineTo(seite1, ps[9][8]);
		lineTo(seite1, ps[8][9]);
		lineTo(seite1, ps[5][2]);
		lineTo(seite1, ps[2][7]);
		lineTo(seite1, ps[1][7]);
		seite1.close();
		addPath(seite1);

		moveTo(seite2, ps[8][9]);
		lineTo(seite2, ps[0][9]);
		lineTo(seite2, ps[0][8]);
		lineTo(seite2, ps[7][8]);
		lineTo(seite2, ps[4][3]);
		lineTo(seite2, ps[5][2]);
		seite2.close();
		addPath(seite2);

	}

	public void lineTo(final Path path, final PointF p) {
		path.lineTo(p.x, p.y);
	}

	public void moveTo(final Path path, final PointF p) {
		path.moveTo(p.x, p.y);
	}

}
