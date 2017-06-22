package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.SquarePath.SQUARE_STYLE;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class TrailOfBoxesPath extends Path {

  public Path trailPath = new Path();

  public TrailOfBoxesPath(final PointF center, final float radius, final boolean filled) {
    super();

    addPath(new SquarePath(center, radius, true, SQUARE_STYLE.NORMAL, Direction.CW));

    // Trail ony when filled
    if (filled) {
      final float distanceToTop = center.y - 5;
      final float trailRadius = radius / 5;
      final float distance = 1.5f;
      final int numberOfTrailStars = (int) (distanceToTop / trailRadius / distance);
      for (int i = 0; i < numberOfTrailStars - 1; i++) {
        final PointF pos = new PointF();
        pos.x = center.x + Randomizer.getRandomFloat(-trailRadius / 2, +trailRadius / 2);
        pos.y = 5 + distance * trailRadius * i;
        final float randRadius = Randomizer.getRandomFloat(trailRadius / 2, trailRadius);
        if (Randomizer.getRandomBoolean()) {
          trailPath.addPath(new SquarePath(pos, randRadius, true, SQUARE_STYLE.NORMAL, Direction.CW));
        }
      }
    }
  }

}
