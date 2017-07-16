
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.OvalPath;

public class QuallePath2 extends QuallePath {

	public QuallePath2(final PointF center, final float radius, final EQualleType type) {
		super(center, radius, type);
	}

	@Override
	protected void drawQualle(final PointF center, final float radius) {
		final float x = center.x;
		final float y = center.y;
		final PointF c = new PointF(x - radius * 0.5f, y);
		addPath(new OvalPath(c, radius * 0.5f, radius, Direction.CCW));
	}

	@Override
	protected void drawInnerQualle(final PointF center, final float radius) {
		final float x = center.x;
		final float y = center.y;
		final PointF c = new PointF(x - radius * 0.25f, y);
		addPath(new OvalPath(c, radius * 0.25f, radius * 0.66f, Direction.CCW));

	}

	@Override
	protected void drawBubbleTail(final PointF center, final float radius) {
		final PointF c = new PointF();
		// start of tail is radius/2 to the left
		c.x = center.x - radius * 0.25f;
		c.y = center.y;
		super.drawBubbleTail(c, radius);
	}

	@Override
	public void drawTail(final PointF center, final float radius) {
		final PointF c = new PointF();
		// start of tail is radius/2 to the left
		c.x = center.x - radius * 0.25f;
		c.y = center.y;
		super.drawTail(c, radius);
	}

}
