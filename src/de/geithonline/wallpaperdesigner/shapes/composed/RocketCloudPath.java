package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.OvalPath;
import de.geithonline.wallpaperdesigner.shapes.SplatterPath;
import de.geithonline.wallpaperdesigner.shapes.SplatterPath.SPLATTER_TYPE;

public class RocketCloudPath extends ComposedPath {

  /**
   * @param ecken
   * @param center
   * @param radius
   * @param rotate
   *          0-2pi
   */
  public RocketCloudPath(final PointF center, final float radius, final int bHeight) {
    super();

    addPath(new OvalPath(center, radius / 10, bHeight - center.y, Direction.CW));
    // oberste Wolke
    final PointF pos = new PointF();
    pos.x = center.x;
    pos.y = bHeight - 3 * radius;
    addPath(new SplatterPath(pos, radius, true, SPLATTER_TYPE.Cloud));
    // nächste Schicht
    pos.x = center.x - radius / 2;
    pos.y = bHeight - 2 * radius;
    addPath(new SplatterPath(pos, radius, true, SPLATTER_TYPE.Cloud));
    pos.x = center.x + radius / 2;
    pos.y = bHeight - 2 * radius;
    addPath(new SplatterPath(pos, radius, true, SPLATTER_TYPE.Cloud));
    // nächste Schicht
    pos.x = center.x - radius;
    pos.y = bHeight - 1 * radius;
    addPath(new SplatterPath(pos, radius, true, SPLATTER_TYPE.Cloud));
    pos.x = center.x + radius;
    pos.y = bHeight - 1 * radius;
    addPath(new SplatterPath(pos, radius, true, SPLATTER_TYPE.Cloud));
    pos.x = center.x;
    pos.y = bHeight - 1 * radius;
    addPath(new SplatterPath(pos, radius, true, SPLATTER_TYPE.Cloud));
    // nächste Schicht
    pos.x = center.x - 2 * radius;
    pos.y = bHeight;
    addPath(new SplatterPath(pos, radius, true, SPLATTER_TYPE.Cloud));
    pos.x = center.x + 2 * radius;
    pos.y = bHeight;
    addPath(new SplatterPath(pos, radius, true, SPLATTER_TYPE.Cloud));
    pos.x = center.x - 1 * radius;
    pos.y = bHeight;
    addPath(new SplatterPath(pos, radius, true, SPLATTER_TYPE.Cloud));
    pos.x = center.x + 1 * radius;
    pos.y = bHeight;
    addPath(new SplatterPath(pos, radius, true, SPLATTER_TYPE.Cloud));
    pos.x = center.x;
    pos.y = bHeight;
    addPath(new SplatterPath(pos, radius, true, SPLATTER_TYPE.Cloud));
  }

}
