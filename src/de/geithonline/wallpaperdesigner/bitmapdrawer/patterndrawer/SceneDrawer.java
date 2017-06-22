package de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.settings.Settings.GLOSSY_GLOW_STYLE;
import de.geithonline.wallpaperdesigner.settings.Settings.GLOSSY_REFLECTIONS_STYLE;
import de.geithonline.wallpaperdesigner.shapes.TrailOfBoxesPath;
import de.geithonline.wallpaperdesigner.shapes.TrailOfStarsPath;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class SceneDrawer {

  private final Canvas bitmapCanvas;
  private final GlossyDrawer glossyDrawer;
  private final OutlineDrawer outlineDrawer;
  private final Rotator rotator;

  public SceneDrawer(final Canvas bitmapCanvas, final GlossyDrawer glossyDrawer, final OutlineDrawer outlineDrawer,
      final Rotator rotator) {
    this.bitmapCanvas = bitmapCanvas;
    this.glossyDrawer = glossyDrawer;
    this.outlineDrawer = outlineDrawer;
    this.rotator = rotator;

  }

  public void drawTrailOfStars(final int x, final int y, final Paint paint, final int radius, final boolean filled) {
    final TrailOfStarsPath path = new TrailOfStarsPath(new PointF(x, y), radius, filled);
    PathHelper.rotatePath(x, y, path, rotator.getRotationDegrees(0, 360, new Point(x, y)));
    // PathHelper.rotatePath(x, y, path.trailPath,
    // rotator.getRotationDegrees(0, 360, new Point(x, y)));
    bitmapCanvas.drawPath(path, paint);
    glossyDrawer.draw(x, y, paint, radius, path);

    glossyDrawer.draw(x, y, paint, radius, path.trailPath, //
        GLOSSY_REFLECTIONS_STYLE.NONE, GLOSSY_GLOW_STYLE.VERTICAL_WHITE, true);
    outlineDrawer.draw(paint, radius, path);
  }

  public void drawTrailOfBoxes(final int x, final int y, final Paint paint, final int radius,
      final boolean filled) {
    final TrailOfBoxesPath path = new TrailOfBoxesPath(new PointF(x, y), radius, filled);
    PathHelper.rotatePath(x, y, path, rotator.getRotationDegrees(0, 360, new Point(x, y)));
    // PathHelper.rotatePath(x, y, path.trailPath,
    // rotator.getRotationDegrees(0, 360, new Point(x, y)));
    bitmapCanvas.drawPath(path, paint);
    glossyDrawer.draw(x, y, paint, radius, path);

    glossyDrawer.draw(x, y, paint, radius, path.trailPath, //
        GLOSSY_REFLECTIONS_STYLE.NONE, GLOSSY_GLOW_STYLE.VERTICAL, true);
    outlineDrawer.draw(paint, radius, path);

  }

}
