
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.LensePath;
import de.geithonline.wallpaperdesigner.shapes.OvalPath;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class PenguinPath extends ComposedPath {

	private final float raster;

	public Path body;
	public Path bauch;
	public Path augen = new Path();
	public Path schnabel = new Path();

	public PenguinPath(final PointF center, final float radius) {
		raster = radius / 5;
		drawBody(center, radius);
		drawBauch(center, radius);
		drawAugen(center, radius);
		drawSchnabel(center, radius);
	}

	private void drawBody(final PointF center, final float radius) {
		final PointF c = new PointF();
		c.x = center.x;
		c.y = center.y - 3 * raster;
		body = new CirclePath(c, raster * 2);

		c.x = center.x;
		c.y = center.y + raster;
		final Path bauch = new OvalPath(c, 3 * raster, 4 * raster);
		body.op(bauch, Op.UNION);

		// flügel links
		c.x = center.x - 3f * raster;
		c.y = center.y;
		final Path wing1 = new LensePath(c, 2.0f * raster);
		PathHelper.rotatePath(c, wing1, -40);
		body.op(wing1, Op.UNION);

		// flügel links
		c.x = center.x + 3f * raster;
		c.y = center.y;
		final Path wing2 = new LensePath(c, 2.0f * raster);
		PathHelper.rotatePath(c, wing2, 40);
		body.op(wing2, Op.UNION);
		addPath(body);
	}

	private void drawBauch(final PointF center, final float radius) {
		final PointF c = new PointF();
		c.x = center.x;
		c.y = center.y + 2 * raster;
		bauch = new OvalPath(c, 2 * raster, 3 * raster);
	}

	private void drawSchnabel(final PointF center, final float radius) {
		schnabel.moveTo(center.x, center.y);
		schnabel.lineTo(center.x - 0.5f * raster, center.y - 1.5f * raster);
		schnabel.lineTo(center.x + 0.5f * raster, center.y - 1.5f * raster);
		schnabel.close();
	}

	private void drawAugen(final PointF center, final float radius) {
		final PointF c = new PointF();
		float h = raster * Randomizer.getRandomFloat(0.7f, 1.2f);
		final float w = raster * 0.6f;
		c.x = center.x - 0.6f * raster;
		c.y = center.y - 1.5f * raster - h;
		final Path augeL = new OvalPath(c, w, h);
		c.x = center.x - 0.3f * raster;
		final Path augeLInnen = new CirclePath(c, raster * 0.25f, Direction.CCW);

		h = raster * Randomizer.getRandomFloat(0.7f, 1.2f);
		c.x = center.x + 0.6f * raster;
		c.y = center.y - 1.5f * raster - h;
		final Path augeR = new OvalPath(c, w, h);

		c.x = center.x + 0.3f * raster;
		final Path augeRInnen = new CirclePath(c, raster * 0.25f, Direction.CCW);

		augen.addPath(augeL);
		augen.addPath(augeLInnen);
		augen.addPath(augeR);
		augen.addPath(augeRInnen);
	}

}
