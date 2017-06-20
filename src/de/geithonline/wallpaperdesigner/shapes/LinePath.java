package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class LinePath extends Path {

  public enum LINE_STYLE {
    straight, sinus;
  }

  public LinePath(final PointF center, final float radius, final LINE_STYLE style) {
    super();
    switch (style) {
    default:
    case straight:
      moveTo(center.x - radius, center.y);
      lineTo(center.x + radius, center.y);
      break;
    case sinus:
      // nach links
      final float l = center.x - radius;
      final float r = center.x + radius;
      final float mitteY = center.y;
      final float sinradius = radius * 0.1f;
      final int sinRepeats = 7;
      lineTo(l, mitteY);
      // und nun der Sinus
      final int points = 30;
      for (int i = 1; i <= points; i++) {
        final float x = l + i * (r - l) / points;
        final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
        final float y = mitteY + (float) (sinradius * Math.sin(angle));
        lineTo(x, y);
      }
      break;
    }

  }
}
