package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.StarPath.STAR_TYPE;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class TrailOfStarsPath extends Path {

  public Path trailPath = new Path();

  public TrailOfStarsPath(final PointF center, final float radius, final boolean filled) {
    super();

    addPath(new StarPath(5, center, radius, STAR_TYPE.NORMAL, true));

    // Trail ony when filled
    if (filled) {
      final float distanceToTop = center.y - 5;
      final float trailRadius = radius / 5;
      final float distance = 2f;
      final int numberOfTrailStars = (int) (distanceToTop / trailRadius / distance);
      for (int i = 0; i < numberOfTrailStars - 1; i++) {
        final PointF pos = new PointF();
        pos.x = center.x + Randomizer.getRandomFloat(-trailRadius / 2, +trailRadius / 2);
        pos.y = 5 + distance * trailRadius * i;
        final float randRadius = Randomizer.getRandomFloat(trailRadius / 2, trailRadius * 2);
        if (Randomizer.getRandomInt(0, 5) != 1) {
          trailPath.addPath(new StarPath(5, pos, randRadius, STAR_TYPE.NORMAL, true));
        }
      }
    }
  }

}
