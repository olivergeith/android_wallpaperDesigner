package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class AsymetricLongPath extends Path {

	public enum ASYMETRIC_STYLE {
		RAUTE, DRACHEN, DRACHEN_UPSIDEDOWN, OVAL, TRIANGLE, LENSE, LENSE_V2, LENSE_V3, DROP, TAG, KNIFE, KNIFE_V2, KNIFE_V3, CROSS, DOUBLE_CROSS, SPERM, VIRUS, VIRUS_V2;
	}

	public AsymetricLongPath(final PointF center, final float radius, final int height, final boolean filled, final ASYMETRIC_STYLE style) {

		switch (style) {
		default:
		case RAUTE:
			drawRaute(center, radius, height, filled);
			break;
		case DRACHEN:
			drawDrache(center, radius, height, filled);
			break;
		case DRACHEN_UPSIDEDOWN:
			drawDracheUpside(center, radius, height, filled);
			break;
		case OVAL:
			drawOval(center, radius, height, filled);
			break;
		case TRIANGLE:
			drawTriangle(center, radius, height, filled);
			break;
		case LENSE:
			drawLense(center, radius, height, filled);
			break;
		case LENSE_V2:
			drawLenseV2(center, radius, height, filled);
			break;
		case LENSE_V3:
			drawLenseV3(center, radius, height, filled);
			break;
		case DROP:
			drawLongDrop(center, radius, height, filled);
			break;
		case TAG:
			drawTag(center, radius, height, filled);
			break;
		case KNIFE:
			drawKnife(center, radius, height, filled);
			break;
		case KNIFE_V2:
			drawKnifeV2(center, radius, height, filled);
			break;
		case KNIFE_V3:
			drawKnifeV3(center, radius, height, filled);
			break;
		case CROSS:
			drawCross(center, radius, height, filled);
			break;
		case DOUBLE_CROSS:
			drawCrossV2(center, radius, height, filled);
			break;
		case SPERM:
			drawSperm(center, radius, height, filled);
			break;
		case VIRUS:
			drawVirus(8, center, radius, height, filled);
			break;
		case VIRUS_V2:
			drawVirus(16, center, radius, height, filled);
			break;
		}

	}

	// ##################################################################################
	private void drawRaute(final PointF center, final float radius, final int height, final boolean filled) {
		addPath(drawNormalRaute(center, radius, height, Direction.CW));
		if (!filled) {
			addPath(drawNormalRaute(new PointF(center.x, center.y - radius * 2), radius / 2, height / 2, Direction.CCW));
		}
	}

	private Path drawNormalRaute(final PointF center, final float radius, final int height, final Direction dir) {
		final Path p = new Path();
		if (dir.equals(Direction.CW)) {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x - radius, center.y - height / 2);
			p.lineTo(center.x, center.y - height);
			p.lineTo(center.x + radius, center.y - height / 2);
			p.close();
		} else {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x + radius, center.y - height / 2);
			p.lineTo(center.x, center.y - height);
			p.lineTo(center.x - radius, center.y - height / 2);
			p.close();
		}
		return p;
	}

	// ##################################################################################
	private void drawDrache(final PointF center, final float radius, final int height, final boolean filled) {
		addPath(drawDrachenRaute(center, radius, height, Direction.CW));
		if (!filled) {
			addCircle(center.x, center.y - height * 5 / 6, radius / 2, Direction.CCW);
		}
	}

	private Path drawDrachenRaute(final PointF center, final float radius, final int height, final Direction dir) {
		final Path p = new Path();
		if (dir.equals(Direction.CW)) {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x - radius, center.y - height * 5 / 6);
			p.lineTo(center.x, center.y - height);
			p.lineTo(center.x + radius, center.y - height * 5 / 6);
			p.close();
		} else {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x + radius, center.y - height * 5 / 6);
			p.lineTo(center.x, center.y - height);
			p.lineTo(center.x - radius, center.y - height * 5 / 6);
			p.close();
		}
		return p;
	}

	// ##################################################################################
	private void drawDracheUpside(final PointF center, final float radius, final int height, final boolean filled) {
		addPath(drawDrachenRauteUpside(center, radius, height, Direction.CW));
		if (!filled) {
			addCircle(center.x, center.y - height * 1 / 6, radius / 2, Direction.CCW);
		}
	}

	private Path drawDrachenRauteUpside(final PointF center, final float radius, final int height, final Direction dir) {
		final Path p = new Path();
		if (dir.equals(Direction.CW)) {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x - radius, center.y - height * 1 / 6);
			p.lineTo(center.x, center.y - height);
			p.lineTo(center.x + radius, center.y - height * 1 / 6);
			p.close();
		} else {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x + radius, center.y - height * 1 / 6);
			p.lineTo(center.x, center.y - height);
			p.lineTo(center.x - radius, center.y - height * 1 / 6);
			p.close();
		}
		return p;
	}

	// ##################################################################################
	private void drawOval(final PointF center, final float radius, final int height, final boolean filled) {
		final RectF rect = new RectF();

		rect.left = center.x - radius;
		rect.right = center.x + radius;
		rect.top = center.y - height;
		rect.bottom = center.y;

		addOval(rect, Direction.CW);
		if (!filled) {
			rect.left = center.x - radius / 2;
			rect.right = center.x + radius / 2;
			rect.top = center.y - height * 3 / 4;
			rect.bottom = center.y - height / 4;
			addOval(rect, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawTriangle(final PointF center, final float radius, final int height, final boolean filled) {
		addPath(drawTriangle(center, radius, height, Direction.CW));
		if (!filled) {
			addPath(drawTriangle(new PointF(center.x, center.y - radius * 2), radius / 2, height / 2, Direction.CCW));
		}
	}

	private Path drawTriangle(final PointF center, final float radius, final int height, final Direction dir) {
		final Path p = new Path();
		if (dir.equals(Direction.CW)) {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x - radius, center.y - height);
			p.lineTo(center.x + radius, center.y - height);
			p.close();
		} else {
			p.moveTo(center.x, center.y);
			p.lineTo(center.x + radius, center.y - height);
			p.lineTo(center.x - radius, center.y - height);
			p.close();
		}
		return p;
	}

	// ##################################################################################
	private void drawLense(final PointF center, final float radius, final int height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x - 2 * radius, center.y - height * 1 / 4, // controllpoint
				center.x, center.y - height); // Zielpunkt
		quadTo(center.x + 2 * radius, center.y - height * 1 / 4, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		if (!filled) {
			addCircle(center.x, center.y - height * 1 / 4, radius * 5 / 10, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawLenseV2(final PointF center, final float radius, final int height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x - 2 * radius, center.y - height * 1 / 2, // controllpoint
				center.x, center.y - height); // Zielpunkt
		quadTo(center.x + 2 * radius, center.y - height * 1 / 2, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		if (!filled) {
			addCircle(center.x, center.y - height * 1 / 2, radius * 6 / 10, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawLenseV3(final PointF center, final float radius, final int height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x - 2 * radius, center.y - height * 5 / 6, // controllpoint
				center.x, center.y - height); // Zielpunkt
		quadTo(center.x + 2 * radius, center.y - height * 5 / 6, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		if (!filled) {
			addCircle(center.x, center.y - height * 5 / 6, radius * 5 / 10, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawLongDrop(final PointF center, final float radius, final int height, final boolean filled) {
		final RectF oval = new RectF();
		oval.left = center.x - radius;
		oval.right = center.x + radius;
		oval.top = center.y - 2 * radius;
		oval.bottom = center.y;

		moveTo(center.x, center.y - height);
		cubicTo(center.x, center.y - height + 4 * radius, // CP1
				center.x + radius, center.y - 2 * radius, // CP2
				center.x + radius, center.y - radius);
		arcTo(oval, 0, 180);
		cubicTo(center.x - radius, center.y - 2 * radius, // CP1
				center.x, center.y - height + 4 * radius, // CP2
				center.x, center.y - height);
		close();

		if (!filled) {
			addCircle(center.x, center.y - radius, radius * 2 / 3, Direction.CCW);
		}

	}

	// ##################################################################################
	private void drawTag(final PointF center, final float radius, final int height, final boolean filled) {
		final RectF oval = new RectF();
		oval.left = center.x - radius;
		oval.right = center.x + radius;
		oval.top = center.y - 2 * radius;
		oval.bottom = center.y;

		moveTo(center.x, center.y - height);
		lineTo(center.x + radius, center.y - height + radius);
		lineTo(center.x + radius, center.y - radius);
		arcTo(oval, 0, 180);
		lineTo(center.x - radius, center.y - height + radius);
		close();

		if (!filled) {
			addCircle(center.x, center.y - radius, radius * 2 / 3, Direction.CCW);
		}

	}

	// ##################################################################################
	private void drawKnife(final PointF center, final float radius, final int height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x - radius, center.y - radius, // controllpoint
				center.x - radius, center.y - height + radius); // Zielpunkt
		lineTo(center.x, center.y - height);
		lineTo(center.x + radius, center.y - height + radius);
		quadTo(center.x + radius, center.y - radius, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		if (!filled) {
			addCircle(center.x, center.y - height + radius, radius / 2, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawKnifeV2(final PointF center, final float radius, final int height, final boolean filled) {
		final RectF oval = new RectF();
		oval.left = center.x - radius;
		oval.right = center.x + radius;
		oval.top = center.y - height;
		oval.bottom = center.y - height + 2 * radius;

		moveTo(center.x, center.y);
		quadTo(center.x - radius, center.y - radius, // controllpoint
				center.x - radius, center.y - height + radius); // Zielpunkt
		arcTo(oval, 180, 180);
		quadTo(center.x + radius, center.y - radius, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		if (!filled) {
			addCircle(center.x, center.y - height + radius, radius / 2, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawKnifeV3(final PointF center, final float radius, final int height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x - radius, center.y - radius, // controllpoint
				center.x - radius, center.y - height + radius); // Zielpunkt
		quadTo(center.x, center.y - height + radius, // controllpoint
				center.x, center.y - height);
		quadTo(center.x, center.y - height + radius, // controllpoint
				center.x + radius, center.y - height + radius);
		quadTo(center.x + radius, center.y - radius, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		if (!filled) {
			addCircle(center.x, center.y - height + 1.5f * radius, radius / 2, Direction.CCW);

			addCircle(center.x - radius, center.y - height, radius * 6 / 10, Direction.CCW);
			addCircle(center.x + radius, center.y - height, radius * 6 / 10, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawCross(final PointF center, final float radius, final int height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x, center.y - height + 1.5f * radius, // controllpoint
				center.x - radius, center.y - height + radius); // Zielpunkt
		quadTo(center.x, center.y - height + radius, // controllpoint
				center.x, center.y - height);
		quadTo(center.x, center.y - height + radius, // controllpoint
				center.x + radius, center.y - height + radius);
		quadTo(center.x, center.y - height + 1.5f * radius, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		if (!filled) {
			addCircle(center.x, center.y - height + 1.2f * radius, radius / 3, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawCrossV2(final PointF center, final float radius, final int height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x, center.y - radius, // controllpoint
				center.x - radius, center.y - radius); // Zielpunkt

		quadTo(center.x, center.y - height / 2, // controllpoint
				center.x - radius, center.y - height + radius); // Zielpunkt
		quadTo(center.x, center.y - height + radius, // controllpoint
				center.x, center.y - height);
		quadTo(center.x, center.y - height + radius, // controllpoint
				center.x + radius, center.y - height + radius);
		quadTo(center.x, center.y - height / 2, // controllpoint
				center.x + radius, center.y - radius); // Zielpunkt
		quadTo(center.x, center.y - radius, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		if (!filled) {
			addCircle(center.x, center.y - height + 1.3f * radius, radius / 3, Direction.CCW);
			addCircle(center.x, center.y - 1.3f * radius, radius / 3, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawSperm(final PointF center, final float radius, final int height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x, center.y - height + 2 * radius, // controllpoint
				center.x - radius, center.y - height + 1.5f * radius); // Zielpunkt
		quadTo(center.x - radius, center.y - height, // controllpoint
				center.x, center.y - height);
		quadTo(center.x + radius, center.y - height, // controllpoint
				center.x + radius, center.y - height + 1.5f * radius);
		quadTo(center.x, center.y - height + 2 * radius, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		if (!filled) {
			addCircle(center.x, center.y - height + 1.1f * radius, radius * 0.4f, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawVirus(final int arms, final PointF center, final float radius, final int height, final boolean filled) {

		final PointF circlecenter = new PointF(center.x, center.y - height + radius);
		final float angle = (float) (2 * Math.PI / (arms));
		final float cpRadius = radius * 0.1f;
		for (int i = 0; i <= arms; i++) {
			final PointF cp = new PointF();
			final PointF p = new PointF();
			cp.x = (int) (circlecenter.x + Math.cos((i - 0.5f) * angle) * cpRadius);
			cp.y = (int) (circlecenter.y + Math.sin((i - 0.5f) * angle) * cpRadius);
			if (i == arms / 4) {
				p.x = center.x;
				p.y = center.y;
			} else {
				p.x = (int) (circlecenter.x + Math.cos((i) * angle) * radius);
				p.y = (int) (circlecenter.y + Math.sin((i) * angle) * radius);
			}
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				quadTo(cp.x, cp.y, p.x, p.y);
			}
		}
		close();
		if (!filled) {
			addCircle(circlecenter.x, circlecenter.y, radius * 0.35f, Direction.CCW);
		}
	}

}
