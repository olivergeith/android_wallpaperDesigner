
package de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.settings.Settings.GLOSSY_GLOW_STYLE;
import de.geithonline.wallpaperdesigner.settings.Settings.GLOSSY_REFLECTIONS_STYLE;
import de.geithonline.wallpaperdesigner.shapes.TrailHeartPath;
import de.geithonline.wallpaperdesigner.shapes.TrailHeartPath.HEART_TRAIL_TYPE;
import de.geithonline.wallpaperdesigner.shapes.TrailStarPath;
import de.geithonline.wallpaperdesigner.shapes.TrailStarPath.TRAIL_TYPE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class SceneDrawer {

	private final Canvas bitmapCanvas;
	private final GlossyDrawer glossyDrawer;
	private final OutlineDrawer outlineDrawer;
	private final Rotator rotator;

	public SceneDrawer(final Canvas bitmapCanvas, final GlossyDrawer glossyDrawer, final OutlineDrawer outlineDrawer, final Rotator rotator) {
		this.bitmapCanvas = bitmapCanvas;
		this.glossyDrawer = glossyDrawer;
		this.outlineDrawer = outlineDrawer;
		this.rotator = rotator;

	}

	public void drawStarWithTrail(final int x, final int y, final Paint paint, final int radius, final boolean filled, final TRAIL_TYPE type) {
		final TrailStarPath path = new TrailStarPath(new PointF(x, y), radius, filled, type);
		PathHelper.rotatePath(x, y, path, rotator.getRotationDegrees(0, 360, new Point(x, y)));
		// PathHelper.rotatePath(x, y, path.trailPath, rotator.getRotationDegrees(0, 360, new Point(x, y)));

		// star
		bitmapCanvas.drawPath(path, paint);
		glossyDrawer.draw(x, y, paint, radius, path);
		outlineDrawer.draw(paint, radius, path);
		// trail
		glossyDrawer.draw(x, y, paint, radius, path.trailPath, //
				GLOSSY_REFLECTIONS_STYLE.NONE, GLOSSY_GLOW_STYLE.VERTICAL, true);
	}

	public void drawHeartWithTrail(final int x, final int y, final Paint paint, final int radius, final boolean filled, final HEART_TRAIL_TYPE type) {
		final TrailHeartPath path = new TrailHeartPath(new PointF(x, y), radius, filled, type);
		PathHelper.rotatePath(x, y, path, rotator.getRotationDegrees(0, 360, new Point(x, y)));
		// PathHelper.rotatePath(x, y, path.trailPath, rotator.getRotationDegrees(0, 360, new Point(x, y)));

		// star
		bitmapCanvas.drawPath(path, paint);
		glossyDrawer.draw(x, y, paint, radius, path);
		outlineDrawer.draw(paint, radius, path);
		// trail
		glossyDrawer.draw(x, y, paint, radius, path.trailPath, //
				GLOSSY_REFLECTIONS_STYLE.NONE, GLOSSY_GLOW_STYLE.VERTICAL, true);
	}

}
