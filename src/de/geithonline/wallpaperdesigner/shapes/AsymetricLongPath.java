package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.shapes.IronCrossPath.IRONCROSS_TYPE;
import de.geithonline.wallpaperdesigner.shapes.SquarePath.SQUARE_STYLE;
import de.geithonline.wallpaperdesigner.utils.GeometrieHelper;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class AsymetricLongPath extends Path {

	public enum ASYMETRIC_STYLE {
		RAUTE, DRACHEN, DRACHEN_UPSIDEDOWN, OVAL, TRIANGLE, LENSE, LENSE_V2, LENSE_V3, DROP, TAG, KNIFE, KNIFE_V2, KNIFE_V3, //
		CROSS, DOUBLE_CROSS, SPERM, VIRUS, VIRUS_V2, LONG_HEART, CHAIN_CIRCLE, CHAIN_CIRCLE_UPSIDEDOWN, SPIKY_CROSS, //
		SPEAR1, BIRD, CROSS_SLIM, GOLF_PIN, PIN, CROSS_SLIM_DOUBLE, TULIP_NORMAL, PLANE, ARROW, CROSS_SLIM_V2, CROSS_SLIM_V3, //
		TULIP_FAT, TULIP_SLIM, BIRD_V2, SPACESHIP, CROSS_SHARP, DROP_REVERSE, VIRUS_V3, CROSS_SPLIT, CROSS_SPLIT2, IRON_CROSS, //
		SPACESHIP_V2, SQUARE_CHAIN, RITUAL_AXE, IRON_CROSS_ROUND, TRIANGLE_RANDOM_HEIGHT, CROSS_SLIM_UPSIDE_DOWN, CROSS_UPSIDE_DOWN, CROSS_SPLIT_UPSIDE_DOWN, DROP_SHARP, LENSE_SHARP, WAVE;
	}

	public AsymetricLongPath(final PointF center, final float radius, final float height, final boolean filled, final ASYMETRIC_STYLE style) {

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
			case TRIANGLE_RANDOM_HEIGHT:
				drawTriangle(center, Randomizer.getRandomFloat(radius * 0.05f, radius), height, filled);
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
			case LENSE_SHARP:
				drawLenseSharp(center, radius, height, filled);
				break;
			case WAVE:
				drawWave(center, radius, height, filled);
				break;
			case DROP_SHARP:
				drawSharpDrop(center, radius, height, filled);
				break;
			case DROP:
				drawLongDrop(center, radius, height, filled);
				break;
			case DROP_REVERSE:
				drawDropReverse(center, radius, height, filled);
				break;
			case LONG_HEART:
				drawLongHeart(center, radius, height, filled);
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
			case GOLF_PIN:
				drawGolfPin(center, radius, height, filled);
				break;
			case PIN:
				drawPin(center, radius, height, filled);
				break;
			case PLANE:
				drawPlane(center, radius, height, filled);
				break;
			case CROSS:
				drawCross(center, radius, height, filled);
				break;
			case CROSS_UPSIDE_DOWN:
				drawCrossUpsideDown(center, radius, height, filled);
				break;
			case CROSS_SPLIT:
				drawCrossSplit(center, radius, height, filled);
				break;
			case CROSS_SPLIT_UPSIDE_DOWN:
				drawCrossSplitUpsideDown(center, radius, height, filled);
				break;
			case CROSS_SPLIT2:
				drawCrossSplit2(center, radius, height, filled);
				break;
			case CROSS_SHARP:
				drawSharpCross(center, radius, height, filled);
				break;
			case CROSS_SLIM:
				drawCrossSlim(center, radius, height, filled);
				break;
			case CROSS_SLIM_UPSIDE_DOWN:
				drawCrossSlimUpdsideDown(center, radius, height, filled);
				break;
			case CROSS_SLIM_V2:
				drawCrossSlimV2(center, radius, height, filled);
				break;
			case CROSS_SLIM_V3:
				drawCrossSlimV3(center, radius, height, filled);
				break;
			case CROSS_SLIM_DOUBLE:
				drawCrossSlimDouble(center, radius, height, filled);
				break;
			case SPIKY_CROSS:
				drawCrossMartial(center, radius, height, filled);
				break;
			case DOUBLE_CROSS:
				drawCrossDouble(center, radius, height, filled, 1f);
				break;
			case SPEAR1:
				drawSpear(center, radius, height, filled);
				break;
			case IRON_CROSS:
				drawIronCross(center, radius, height, filled);
				break;
			case IRON_CROSS_ROUND:
				drawIronCrossRound(center, radius, height, filled);
				break;
			case TULIP_SLIM:
				drawTulipSlim(center, radius, height, filled);
				break;
			case TULIP_NORMAL:
				drawTulipNormal(center, radius, height, filled);
				break;
			case TULIP_FAT:
				drawTulipFat(center, radius, height, filled);
				break;
			case BIRD:
				drawBird(center, radius, height, filled);
				break;
			case BIRD_V2:
				drawBirdV2(center, radius, height, filled);
				break;
			case ARROW:
				drawArrow(center, radius, height, filled);
				break;
			case SPERM:
				drawSperm(center, radius, height, filled);
				break;
			case VIRUS:
				drawVirus(8, center, radius, height, filled, false);
				break;
			case VIRUS_V2:
				drawVirus(16, center, radius, height, filled, false);
				break;
			case VIRUS_V3:
				drawVirus(12, center, radius, height, filled, true);
				break;
			case CHAIN_CIRCLE:
				drawCircleChain(center, radius, height, filled);
				break;
			case CHAIN_CIRCLE_UPSIDEDOWN:
				drawCircleChain2(center, radius, height, filled);
				break;
			case SPACESHIP:
				drawSpaceship(center, radius, height, filled);
				break;
			case SPACESHIP_V2:
				drawSpaceshipV2(center, radius, height, filled);
				break;
			case SQUARE_CHAIN:
				drawSquaresChain(center, radius, height, filled);
				break;
			case RITUAL_AXE:
				drawRitualAxe(center, radius, height, filled);
				break;

		}

	}

	// ##################################################################################
	private void drawSquaresChain(final PointF center, final float radius, final float height, final boolean filled) {
		final int count = (int) (height / radius);
		final float sizeFactor = radius / count;
		for (int i = 0; i < count; i++) {
			final PointF c = new PointF(center.x, center.y - i * radius * 2);
			final float r = (i + 1) * sizeFactor;
			final Path sq = new SquarePath(c, r, filled, SQUARE_STYLE.NORMAL, Direction.CW);
			addPath(sq);
		}

	}

	// ##################################################################################
	private void drawRaute(final PointF center, final float radius, final float height, final boolean filled) {
		addPath(drawNormalRaute(center, radius, height, Direction.CW));
		if (!filled) {
			addPath(drawNormalRaute(new PointF(center.x, center.y - radius * 2), radius / 2, height / 2, Direction.CCW));
		}
	}

	private Path drawNormalRaute(final PointF center, final float radius, final float height, final Direction dir) {
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
	private void drawDrache(final PointF center, final float radius, final float height, final boolean filled) {
		addPath(drawDrachenRaute(center, radius, height, Direction.CW));
		if (!filled) {
			addCircle(center.x, center.y - height * 5 / 6, radius / 2, Direction.CCW);
		}
	}

	private Path drawDrachenRaute(final PointF center, final float radius, final float height, final Direction dir) {
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
	private void drawDracheUpside(final PointF center, final float radius, final float height, final boolean filled) {
		addPath(drawDrachenRauteUpside(center, radius, height, Direction.CW));
		if (!filled) {
			addCircle(center.x, center.y - height * 1 / 6, radius / 2, Direction.CCW);
		}
	}

	private Path drawDrachenRauteUpside(final PointF center, final float radius, final float height, final Direction dir) {
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
	private void drawOval(final PointF center, final float radius, final float height, final boolean filled) {
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
	private void drawTriangle(final PointF center, final float radius, final float height, final boolean filled) {
		addPath(drawTriangle(center, radius, height, Direction.CW));
		if (!filled) {
			addPath(drawTriangle(new PointF(center.x, center.y - radius * 2), radius / 2, height / 2, Direction.CCW));
		}
	}

	private Path drawTriangle(final PointF center, final float radius, final float height, final Direction dir) {
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
	private void drawLense(final PointF center, final float radius, final float height, final boolean filled) {
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
	private void drawLenseV2(final PointF center, final float radius, final float height, final boolean filled) {
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
	private void drawLenseV3(final PointF center, final float radius, final float height, final boolean filled) {
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
	private void drawLongDrop(final PointF center, final float radius, final float height, final boolean filled) {
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
	private void drawLenseSharp(final PointF center, final float radius, final float height, final boolean filled) {
		final PointF c = new PointF(center.x + height / 2, center.y);
		final LenseWithSharpEndsPath lens = new LenseWithSharpEndsPath(c, height / 2, radius * 0.7f);
		addPath(lens);
		if (!filled) {
			addCircle(center.x + height / 2, center.y, radius / 2, Direction.CCW);
		}
		PathHelper.rotatePath(center, this, -90);
	}

	// ##################################################################################
	private void drawWave(final PointF center, final float radius, final float height, final boolean filled) {
		final PointF c = new PointF(center.x + height / 2, center.y);
		final SinusCosinusWavePath lens = new SinusCosinusWavePath(c, height / 2, radius * 0.7f);
		addPath(lens);
		if (!filled) {
			addCircle(center.x + height / 2, center.y, radius / 2, Direction.CCW);
		}
		PathHelper.rotatePath(center, this, -90);
	}

	// ##################################################################################
	private void drawSharpDrop(final PointF center, final float radius, final float height, final boolean filled) {
		final RectF oval = new RectF();

		moveTo(center.x, center.y - height);
		cubicTo(center.x, center.y - height + 4 * radius, // CP1
				center.x + radius, center.y - 2 * radius, // CP2
				center.x + radius, center.y - radius);

		oval.left = center.x;
		oval.right = center.x + 2 * radius;
		oval.top = center.y - radius;
		oval.bottom = center.y + radius;
		arcTo(oval, -90, -90);
		oval.left = center.x - 2 * radius;
		oval.right = center.x;
		oval.top = center.y - radius;
		oval.bottom = center.y + radius;
		arcTo(oval, 0, -90);

		cubicTo(center.x - radius, center.y - 2 * radius, // CP1
				center.x, center.y - height + 4 * radius, // CP2
				center.x, center.y - height);
		close();

		if (!filled) {
			addCircle(center.x, center.y - 1.3f * radius, radius * 1 / 3, Direction.CCW);
		}

	}

	// ##################################################################################
	private void drawDropReverse(final PointF center, final float radius, final float height, final boolean filled) {
		final RectF oval = new RectF();
		oval.left = center.x - radius;
		oval.right = center.x + radius;
		oval.top = center.y - height - radius;
		oval.bottom = center.y - height + radius;

		moveTo(center.x, center.y);
		cubicTo(center.x, center.y - height + 1.0f * radius, // CP1
				center.x + radius, center.y - height + 1.0f * radius, // CP2
				center.x + radius, center.y - height);
		arcTo(oval, 0, -180);
		cubicTo(center.x - radius, center.y - height + 1.0f * radius, // CP2
				center.x, center.y - height + 1.0f * radius, // CP1
				center.x, center.y);
		close();
		if (!filled) {
			addCircle(center.x, center.y - height, radius * 2 / 3, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawLongHeart(final PointF center, final float radius, final float height, final boolean filled) {
		final RectF oval = new RectF();

		moveTo(center.x, center.y);
		cubicTo(center.x, center.y - height, // CP1
				center.x - radius, center.y - height + radius, // CP2
				center.x - radius, center.y - height);

		oval.left = center.x - radius;
		oval.right = center.x;
		oval.top = center.y - height - radius / 2;
		oval.bottom = center.y - height + radius / 2;
		arcTo(oval, -180, 180);
		oval.left = center.x;
		oval.right = center.x + radius;
		oval.top = center.y - height - radius / 2;
		oval.bottom = center.y - height + radius / 2;
		arcTo(oval, -180, 180);

		cubicTo(center.x + radius, center.y - height + radius, // CP1
				center.x, center.y - height, // CP2
				center.x, center.y);
		close();

		if (!filled) {
			addCircle(center.x - radius / 2, center.y - height, radius / 3, Direction.CCW);
			addCircle(center.x + radius / 2, center.y - height, radius / 3, Direction.CCW);
		}

	}

	// ##################################################################################
	private void drawTag(final PointF center, final float radius, final float height, final boolean filled) {
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
	private void drawKnife(final PointF center, final float radius, final float height, final boolean filled) {
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
	private void drawKnifeV2(final PointF center, final float radius, final float height, final boolean filled) {
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
	private void drawKnifeV3(final PointF center, final float radius, final float height, final boolean filled) {
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
	private void drawCross(final PointF center, final float radius, final float height, final boolean filled) {
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
	private void drawCrossUpsideDown(final PointF center, final float radius, final float height, final boolean filled) {
		drawCross(center, radius, height, filled);
		PathHelper.mirrorPathUpDown(center.x, center.y - height / 2, this);
	}

	// ##################################################################################
	private void drawCrossSplit(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x - radius * 0.75f, center.y - height / 2, // controllpoint
				center.x, center.y - height + radius); // Zielpunkt
		quadTo(center.x + radius * 0.75f, center.y - height / 2, // controllpoint
				center.x, center.y);
		close();
		addPath(drawPillow(4, new PointF(center.x, center.y - height), radius));

		if (!filled) {
			final Path p = drawPillow(4, new PointF(center.x, center.y - height), radius * 0.6f);
			PathHelper.mirrorPathLeftRight(center.x, center.y - height, p);
			addPath(p);
		}
	}

	// ##################################################################################
	private void drawCrossSplitUpsideDown(final PointF center, final float radius, final float height, final boolean filled) {
		drawCrossSplit(center, radius, height, filled);
		PathHelper.mirrorPathUpDown(center.x, center.y - height / 2, this);
	}

	// ##################################################################################
	private void drawCrossSplit2(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x, center.y - height + 3 * radius, // controllpoint
				center.x - radius * 0.7f, center.y - height + 2 * radius);

		quadTo(center.x, center.y - height + 2 * radius, // controllpoint
				center.x, center.y - height + radius); // Zielpunkt
		quadTo(center.x, center.y - height + 2 * radius, // controllpoint
				center.x + radius * 0.7f, center.y - height + 2 * radius); // Zielpunkt
		quadTo(center.x, center.y - height + 3 * radius, // controllpoint
				center.x, center.y);
		close();

		addPath(drawPillow(4, new PointF(center.x, center.y - height), radius));

		if (!filled) {
			final Path p = drawPillow(4, new PointF(center.x, center.y - height), radius * 0.6f);
			PathHelper.mirrorPathLeftRight(center.x, center.y - height, p);
			addPath(p);
		}
	}

	private Path drawPillow(final int arms, final PointF center, final float radius) {
		final Path path = new Path();
		final float angle = (float) (2 * Math.PI / (arms));
		final float cpRadius = radius * 0.1f;
		for (int i = 0; i <= arms; i++) {
			final PointF cp = new PointF();
			final PointF p = new PointF();
			cp.x = (float) (center.x + Math.cos((i - 0.5f) * angle) * cpRadius);
			cp.y = (float) (center.y + Math.sin((i - 0.5f) * angle) * cpRadius);
			p.x = (float) (center.x + Math.cos((i) * angle) * radius);
			p.y = (float) (center.y + Math.sin((i) * angle) * radius);
			if (i == 0) {
				path.moveTo(p.x, p.y);
			} else {
				path.quadTo(cp.x, cp.y, p.x, p.y);
			}
		}
		path.close();
		return path;
	}

	// ##################################################################################
	private void drawCrossFlower(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x - radius * 0.5f, center.y - height / 2, // controllpoint
				center.x, center.y - height); // Zielpunkt
		quadTo(center.x + radius * 0.5f, center.y - height / 2, // controllpoint
				center.x, center.y);
		close();

		final PointF c = new PointF(center.x, center.y - height);
		final ASYMETRIC_STYLE type = ASYMETRIC_STYLE.CROSS;
		final float h = radius;
		final float r = radius / 4;
		final Path p1 = new AsymetricLongPath(c, r, h, filled, type);
		PathHelper.rotatePath(c.x, c.y, p1, 135);
		addPath(p1);
		final Path p2 = new AsymetricLongPath(c, r, h, filled, type);
		PathHelper.rotatePath(c.x, c.y, p2, -135);
		addPath(p2);
		final Path p3 = new AsymetricLongPath(c, r, h, filled, type);
		PathHelper.rotatePath(c.x, c.y, p3, 90);
		addPath(p3);
		final Path p4 = new AsymetricLongPath(c, r, h, filled, type);
		PathHelper.rotatePath(c.x, c.y, p4, -90);
		addPath(p4);
		final Path p5 = new AsymetricLongPath(c, r, h, filled, type);
		PathHelper.rotatePath(c.x, c.y, p5, 45);
		addPath(p5);
		final Path p6 = new AsymetricLongPath(c, r, h, filled, type);
		PathHelper.rotatePath(c.x, c.y, p6, -45);
		addPath(p6);
		final Path p7 = new AsymetricLongPath(c, r, h, filled, type);
		addPath(p7);

	}

	// ##################################################################################
	private void drawSharpCross(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x, center.y - height + 1.5f * radius, // controllpoint
				center.x - radius, center.y - height + radius); // Zielpunkt
		quadTo(center.x, center.y - height + radius / 2, // controllpoint
				center.x, center.y - height - radius);
		quadTo(center.x, center.y - height + radius / 2, // controllpoint
				center.x + radius, center.y - height + radius);
		quadTo(center.x, center.y - height + 1.5f * radius, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		if (!filled) {
			addCircle(center.x, center.y - height + 1.2f * radius, radius / 3, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawCrossSlim(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);

		cubicTo(center.x, center.y - height, // CP1
				center.x, center.y - height, // CP2
				center.x - radius, center.y - height); // Zielpunkt
		quadTo(center.x, center.y - height, // controllpoint
				center.x, center.y - height - radius); // Zielpunkt
		quadTo(center.x, center.y - height, // controllpoint
				center.x + radius, center.y - height); // Zielpunkt
		cubicTo(center.x, center.y - height, // CP1
				center.x, center.y - height, // CP2
				center.x, center.y); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height, radius * 0.2f, Direction.CCW);

			// Kreise an den Spitzen
			// addCircle(center.x - radius - radius * 0.2f, center.y - height, radius * 0.2f, Direction.CCW);
			// addCircle(center.x, center.y - height - radius - radius * 0.2f, radius * 0.2f, Direction.CCW);
			// addCircle(center.x + radius + radius * 0.2f, center.y - height, radius * 0.2f, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawCrossSlimUpdsideDown(final PointF center, final float radius, final float height, final boolean filled) {
		drawCrossSlim(center, radius, height, filled);
		PathHelper.mirrorPathUpDown(center.x, center.y - height / 2, this);
	}

	// #####################################################-#############################
	private void drawCrossSlimV2(final PointF center, final float radius, final float height, final boolean filled) {
		final float offset = 0.1f;

		moveTo(center.x, center.y);

		cubicTo(center.x, center.y - height, // CP1
				center.x, center.y - height + radius * offset, // CP2
				center.x - radius, center.y - height + radius * offset); // Zielpunkt
		lineTo(center.x - radius, center.y - height - radius * offset);

		quadTo(center.x, center.y - height, // controllpoint
				center.x - radius * offset, center.y - height - radius); // Zielpunkt
		lineTo(center.x + radius * offset, center.y - height - radius);
		quadTo(center.x, center.y - height, // controllpoint
				center.x + radius, center.y - height - radius * offset); // Zielpunkt
		lineTo(center.x + radius, center.y - height + radius * offset);
		cubicTo(center.x, center.y - height + radius * offset, // CP1
				center.x, center.y - height, // CP2
				center.x, center.y); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height, radius * 0.2f, Direction.CCW);
		}
	}

	// #####################################################-#############################
	private void drawCrossSlimV3(final PointF center, final float radius, final float height, final boolean filled) {
		final float offset = 0.1f;

		moveTo(center.x, center.y);

		cubicTo(center.x, center.y - height, // CP1
				center.x, center.y - height + radius * offset, // CP2
				center.x - radius, center.y - height + radius * offset); // Zielpunkt
		lineTo(center.x - radius, center.y - height - radius * offset);

		quadTo(center.x, center.y - height, // controllpoint
				center.x, center.y - height - radius); // Zielpunkt
		quadTo(center.x, center.y - height, // controllpoint
				center.x + radius, center.y - height - radius * offset); // Zielpunkt
		lineTo(center.x + radius, center.y - height + radius * offset);
		cubicTo(center.x, center.y - height + radius * offset, // CP1
				center.x, center.y - height, // CP2
				center.x, center.y); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height, radius * 0.2f, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawPlane(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);

		cubicTo(center.x, center.y - height, // CP1
				center.x, center.y - height, // CP2
				center.x - radius, center.y - height); // Zielpunkt
		lineTo(center.x - radius, center.y - height - radius / 4);
		lineTo(center.x - radius / 4, center.y - height - radius / 4);

		quadTo(center.x - radius / 4, center.y - height - radius * 0.75f, // controllpoint
				center.x, center.y - height - radius); // Zielpunkt
		quadTo(center.x + radius / 4, center.y - height - radius * 0.75f, // controllpoint
				center.x + radius / 4, center.y - height - radius / 4); // Zielpunkt
		lineTo(center.x + radius, center.y - height - radius / 4);
		lineTo(center.x + radius, center.y - height);

		cubicTo(center.x, center.y - height, // CP1
				center.x, center.y - height, // CP2
				center.x, center.y); // Zielpunkt

		close();
		if (!filled) {
			moveTo(center.x, center.y - height - radius * 0.5f);
			lineTo(center.x - radius / 4, center.y - height - radius / 8);
			lineTo(center.x + radius / 4, center.y - height - radius / 8);
			close();
		}
	}

	// ##################################################################################
	private void drawArrow(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);

		lineTo(center.x - radius / 10, center.y - height + radius / 2);
		lineTo(center.x - radius, center.y - height + radius);

		// lineTo(center.x, center.y - height - radius);
		quadTo(center.x - radius / 4, center.y - height, // controllpoint
				center.x, center.y - height - radius); // Zielpunkt
		quadTo(center.x + radius / 4, center.y - height, // controllpoint
				center.x + radius, center.y - height + radius);

		lineTo(center.x + radius / 10, center.y - height + radius / 2);
		lineTo(center.x, center.y);

		close();
		if (!filled) {
			moveTo(center.x, center.y - height - radius * 0.5f);
			lineTo(center.x - radius / 4, center.y - height);
			lineTo(center.x + radius / 4, center.y - height);
			close();
		}
	}

	// ##################################################################################
	private void drawCrossSlimDouble(final PointF center, final float radius, final float height, final boolean filled) {

		final Path cross1 = new AsymetricLongPath(center, radius / 2, (int) (height - 1.5f * radius), filled, ASYMETRIC_STYLE.CROSS_SLIM);

		addPath(cross1);

		moveTo(center.x, center.y - height + radius);

		quadTo(center.x, center.y - height, // controllpoint
				center.x - radius, center.y - height); // Zielpunkt
		quadTo(center.x, center.y - height, // controllpoint
				center.x, center.y - height - radius); // Zielpunkt
		quadTo(center.x, center.y - height, // controllpoint
				center.x + radius, center.y - height); // Zielpunkt
		quadTo(center.x, center.y - height, // controllpoint
				center.x, center.y - height + radius); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height, radius * 0.2f, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawGolfPin(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);

		quadTo(center.x, center.y - height, // controllpoint
				center.x - radius, center.y - height - radius); // Zielpunkt
		quadTo(center.x, center.y - height, // controllpoint
				center.x + radius, center.y - height - radius); // Zielpunkt
		quadTo(center.x, center.y - height, // controllpoint
				center.x, center.y); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height - radius, radius * 0.3f, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawCrossMartial(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);
		quadTo(center.x, center.y - height - radius / 2, // controllpoint
				center.x - radius / 2, center.y - height + radius / 2); // Zielpunkt

		quadTo(center.x - radius / 2, center.y - height, // controllpoint
				center.x - radius, center.y - height);

		quadTo(center.x, center.y - height, // controllpoint
				center.x, center.y - height - radius);

		quadTo(center.x, center.y - height, // controllpoint
				center.x + radius, center.y - height);

		quadTo(center.x + radius / 2, center.y - height, // controllpoint
				center.x + radius / 2, center.y - height + radius / 2);

		quadTo(center.x, center.y - height - radius / 2, // controllpoint
				center.x, center.y); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height, radius * 0.2f, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawSpear(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);

		cubicTo(center.x, center.y - height, // CP1
				center.x - radius, center.y - height + radius, // CP2
				center.x - radius, center.y - height); // Zielpunkt

		final RectF oval = new RectF();
		oval.left = center.x - radius;
		oval.right = center.x - radius * 1 / 3;
		oval.top = center.y - height - radius * 1 / 3;
		oval.bottom = center.y - height + radius * 1 / 3;
		arcTo(oval, 180, -270);

		quadTo(center.x, center.y - height - radius * 1 / 3, // controllpoint
				center.x, center.y - height - radius * 1.5f); // Zielpunkt

		quadTo(center.x, center.y - height - radius * 1 / 3, // controllpoint
				center.x + radius * 2 / 3, center.y - height - radius * 1 / 3); // Zielpunkt

		oval.left = center.x + radius * 1 / 3;
		oval.right = center.x + radius;
		oval.top = center.y - height - radius * 1 / 3;
		oval.bottom = center.y - height + radius * 1 / 3;
		arcTo(oval, -90, -270);

		cubicTo(center.x + radius, center.y - height + radius, // CP1
				center.x, center.y - height, // CP2
				center.x, center.y); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height - radius * 1 / 3, radius * 0.2f, Direction.CCW);
			addCircle(center.x, center.y - height + radius * 0.05f, radius * 0.13f, Direction.CCW);
			addCircle(center.x, center.y - height + radius * 1 / 3, radius * 0.1f, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawTulipNormal(final PointF center, final float radius, final float height, final boolean filled) {
		drawTulip(center, radius, height, filled, 0);
	}

	private void drawTulipSlim(final PointF center, final float radius, final float height, final boolean filled) {
		drawTulip(center, radius, height, filled, -1);
	}

	private void drawTulipFat(final PointF center, final float radius, final float height, final boolean filled) {
		drawTulip(center, radius, height, filled, 1);
	}

	/**
	 * @param center
	 * @param radius
	 * @param height
	 * @param filled
	 * @param slimmy
	 *            -1 = very slip, 0=nomal, +1=bigger
	 */
	private void drawTulip(final PointF center, final float radius, final float height, final boolean filled, final int slimmy) {
		moveTo(center.x, center.y);

		final float offset = slimmy * radius;
		cubicTo(center.x, center.y - height + offset, // CP1
				center.x - radius / 2, center.y - height + radius, // CP2
				center.x - radius, center.y - height); // Zielpunkt

		quadTo(center.x - radius / 2, center.y - height + radius / 2, // controllpoint
				center.x, center.y - height - radius / 2); // Zielpunkt

		quadTo(center.x + radius / 2, center.y - height + radius / 2, // controllpoint
				center.x + radius, center.y - height); // Zielpunkt

		cubicTo(center.x + radius / 2, center.y - height + radius, // CP1
				center.x, center.y - height + offset, // CP2
				center.x, center.y); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height - radius * 3 / 4, radius * 0.2f, Direction.CCW);
		}
	}

	// #################################################################################
	private void drawSpaceshipV2(final PointF center, final float radius, final float height, final boolean filled) {
		final Path p = new SpaceshipPath(new PointF(center.x, center.y - height - radius), radius, filled, height);
		addPath(p);
	}

	// #################################################################################
	private void drawSpaceship(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);

		final float offset = 0 * radius;
		final float f = 0.75f;
		final float d = f * radius;

		cubicTo(center.x, center.y - height + offset, // CP1
				center.x - radius, center.y - height + radius, // CP2
				center.x - radius, center.y - height); // Zielpunkt

		quadTo(center.x - radius, center.y - height - radius / 2, // controllpoint
				center.x - radius / 4, center.y - height - radius); // Zielpunkt
		quadTo(center.x - d, center.y - height - radius / 2, // controllpoint
				center.x - d, center.y - height); // Zielpunkt

		final RectF oval = new RectF();
		oval.left = center.x - d;
		oval.right = center.x;
		oval.top = center.y - height - d / 2;
		oval.bottom = center.y - height + d / 2;
		arcTo(oval, 180, -180);
		oval.left = center.x;
		oval.right = center.x + d;
		oval.top = center.y - height - d / 2;
		oval.bottom = center.y - height + d / 2;
		arcTo(oval, 180, -180);

		// quadTo(center.x - radius / 4, center.y - height + radius / 4, // controllpoint
		// center.x, center.y - height); // Zielpunkt
		// quadTo(center.x + radius / 4, center.y - height + radius / 4, // controllpoint
		// center.x + radius / 2, center.y - height); // Zielpunkt

		quadTo(center.x + d, center.y - height - radius / 2, // controllpoint
				center.x + radius / 4, center.y - height - radius); // Zielpunkt
		quadTo(center.x + radius, center.y - height - radius / 2, // controllpoint
				center.x + radius, center.y - height); // Zielpunkt

		cubicTo(center.x + radius, center.y - height + radius, // CP1
				center.x, center.y - height + offset, // CP2
				center.x, center.y); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height + radius / 2, radius * 0.2f, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawBird(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);
		lineTo(center.x - radius * 0.1f, center.y - height + radius);

		cubicTo(center.x - radius * 0.1f, center.y - height + radius / 2, // CP2
				center.x - radius / 2, center.y - height, // CP1
				center.x - radius, center.y - height + radius / 2); // Zielpunkt

		quadTo(center.x - radius, center.y - height, // controllpoint
				center.x - radius / 2, center.y - height);

		quadTo(center.x, center.y - height, // controllpoint
				center.x, center.y - height - radius);
		// wierder runter
		quadTo(center.x, center.y - height, // controllpoint
				center.x + radius / 2, center.y - height);

		quadTo(center.x + radius, center.y - height, // controllpoint
				center.x + radius, center.y - height + radius / 2);

		cubicTo(center.x + radius / 2, center.y - height, // CP1
				center.x + radius * 0.1f, center.y - height + radius / 2, // CP2
				center.x + radius * 0.1f, center.y - height + radius); // Zielpunkt
		lineTo(center.x, center.y);

		close();
		if (!filled) {
			addCircle(center.x, center.y - height + radius / 4, radius * 0.2f, Direction.CCW);
		}
	}

	private void drawBirdV2(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);

		quadTo(center.x, center.y - height, // controllpoint
				center.x - radius, center.y - height); // Zielpunkt

		quadTo(center.x - radius, center.y - height - radius / 2, // controllpoint
				center.x - radius / 2, center.y - height - radius / 2);

		quadTo(center.x, center.y - height - radius / 2, // controllpoint
				center.x, center.y - height - radius);
		// wierder runter
		quadTo(center.x, center.y - height - radius / 2, // controllpoint
				center.x + radius / 2, center.y - height - radius / 2);

		quadTo(center.x + radius, center.y - height - radius / 2, // controllpoint
				center.x + radius, center.y - height); // Zielpunkt

		quadTo(center.x, center.y - height, // controllpoint
				center.x, center.y); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height - radius * 3 / 4, radius * 0.2f, Direction.CCW);
		}
	}

	// ##################################################################################
	/**
	 * @param center
	 * @param radius
	 * @param height
	 * @param filled
	 * @param widthUnten
	 *            (zwischen 0 und eins macht sinn)
	 */
	private void drawCrossDouble(final PointF center, final float radius, final float height, final boolean filled, final float widthUnten) {
		moveTo(center.x, center.y);
		quadTo(center.x, center.y - radius, // controllpoint
				center.x - radius * widthUnten, center.y - radius); // Zielpunkt

		quadTo(center.x, center.y - height / 2, // controllpoint
				center.x - radius, center.y - height + radius); // Zielpunkt
		quadTo(center.x, center.y - height + radius, // controllpoint
				center.x, center.y - height);
		quadTo(center.x, center.y - height + radius, // controllpoint
				center.x + radius, center.y - height + radius);
		quadTo(center.x, center.y - height / 2, // controllpoint
				center.x + radius * widthUnten, center.y - radius); // Zielpunkt
		quadTo(center.x, center.y - radius, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		if (!filled) {
			addCircle(center.x, center.y - height + 1.3f * radius, radius / 3, Direction.CCW);
			addCircle(center.x, center.y - 1.3f * radius, radius / 3, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawSperm(final PointF center, final float radius, final float height, final boolean filled) {
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
	private void drawVirus(final int arms, final PointF center, final float radius, final float height, final boolean filled, final boolean randomradius) {

		final PointF circlecenter = new PointF(center.x, center.y - height);
		final float angle = (float) (2 * Math.PI / (arms));
		float cpRadius = radius * 0.1f;
		float zackenRadius = radius;

		for (int i = 0; i <= arms; i++) {
			if (randomradius) {
				cpRadius = radius * Randomizer.getRandomFloat(0, 0.5f);
				zackenRadius = radius * Randomizer.getRandomFloat(0.6f, 1.4f);
			}
			final PointF cp = new PointF();
			final PointF p = new PointF();
			cp.x = (int) (circlecenter.x + Math.cos((i - 0.5f) * angle) * cpRadius);
			cp.y = (int) (circlecenter.y + Math.sin((i - 0.5f) * angle) * cpRadius);
			if (i == arms / 4) {
				p.x = center.x;
				p.y = center.y;
			} else {
				p.x = (int) (circlecenter.x + Math.cos((i) * angle) * zackenRadius);
				p.y = (int) (circlecenter.y + Math.sin((i) * angle) * zackenRadius);
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

	// ##################################################################################
	private void drawCircleChain(final PointF center, final float radius, final float height, final boolean filled) {

		final PointF p = new PointF(center.x, center.y);
		float offset = 0;
		final int length = 6;
		for (int i = 0; i < length; i++) {
			final float r = radius * (length - i) / length;
			p.y = center.y - offset;
			addCircle(p.x, p.y, r, Direction.CCW);
			if (!filled) {
				addCircle(p.x, p.y, r * 0.6f, Direction.CW);
			}
			offset = offset + 2 * r;
		}
	}

	// ##################################################################################
	private void drawCircleChain2(final PointF center, final float radius, final float height, final boolean filled) {

		final PointF p = new PointF(center.x, center.y);
		float offset = 0;
		final int length = 6;
		for (int i = 0; i < length; i++) {
			final float r = radius * (1 + i) / length;
			p.y = center.y - offset;
			addCircle(p.x, p.y, r, Direction.CCW);
			if (!filled) {
				addCircle(p.x, p.y, r * 0.6f, Direction.CW);
			}
			final float r2 = radius * (2 + i) / length;
			offset = offset + 2 * r2;
		}
	}

	// ##################################################################################
	private void drawIronCross(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);

		quadTo(center.x, center.y - height + radius * 1.5f, // controllpoint
				center.x - radius / 3, center.y - height + radius); // Zielpunkt

		quadTo(center.x, center.y - height + radius * 1.25f, // controllpoint
				center.x + radius / 3, center.y - height + radius); // Zielpunkt

		quadTo(center.x, center.y - height + radius * 1.5f, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		final Path cPath = new IronCrossPath(new PointF(center.x, center.y - height), radius, filled, IRONCROSS_TYPE.SPITZ);
		addPath(cPath);
	}

	// ##################################################################################
	private void drawIronCrossRound(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);

		quadTo(center.x, center.y - height + radius * 1.5f, // controllpoint
				center.x - radius / 3, center.y - height + radius); // Zielpunkt

		quadTo(center.x, center.y - height + radius * 1.25f, // controllpoint
				center.x + radius / 3, center.y - height + radius); // Zielpunkt

		quadTo(center.x, center.y - height + radius * 1.5f, // controllpoint
				center.x, center.y); // Zielpunkt
		close();
		final Path cPath = new IronCrossPath(new PointF(center.x, center.y - height), radius, filled, IRONCROSS_TYPE.RUND);
		addPath(cPath);
	}

	// ##################################################################################
	private void drawRitualAxe(final PointF center, final float radius, final float height, final boolean filled) {
		final float raster = radius / 3;
		final float r1 = raster;
		final float r2 = 2 * raster;
		final float r3 = 3 * raster;
		RectF oval = new RectF();
		PointF c = new PointF();

		moveTo(center.x, center.y);

		// lineTo(center.x - r2, center.y - height - r2);
		quadTo(center.x, center.y - height - r2, // controllpoint
				center.x - r2, center.y - height - r2); // Zielpunkt

		c = new PointF(center.x - r2, center.y - height - r3);
		oval = GeometrieHelper.getCircle(c, r1);
		arcTo(oval, 90, -270);

		c = new PointF(center.x, center.y - height - r3);
		oval = GeometrieHelper.getCircle(c, r3);
		arcTo(oval, 180, 180);

		c = new PointF(center.x + r2, center.y - height - r3);
		oval = GeometrieHelper.getCircle(c, r1);
		arcTo(oval, 0, -270);

		quadTo(center.x, center.y - height - r2, // controllpoint
				center.x, center.y); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height - radius, radius * 0.3f, Direction.CCW);
		}
	}

	// ##################################################################################
	private void drawPin(final PointF center, final float radius, final float height, final boolean filled) {
		moveTo(center.x, center.y);

		quadTo(center.x, center.y - height + radius * 1.5f, // controllpoint
				center.x - radius / 2, center.y - height + radius); // Zielpunkt
		quadTo(center.x, center.y - height + radius * 1.5f, // controllpoint
				center.x + radius / 2, center.y - height + radius); // Zielpunkt
		quadTo(center.x, center.y - height + radius * 1.5f, // controllpoint
				center.x, center.y); // Zielpunkt

		close();

		moveTo(center.x, center.y - height - radius);
		quadTo(center.x, center.y - height, // controllpoint
				center.x + radius, center.y - height); // Zielpunkt
		final RectF oval = new RectF();
		oval.left = center.x - radius;
		oval.right = center.x + radius;
		oval.top = center.y - height - radius;
		oval.bottom = center.y - height + radius;
		arcTo(oval, 0, 180);
		quadTo(center.x, center.y - height, // controllpoint
				center.x, center.y - height - radius); // Zielpunkt

		close();
		if (!filled) {
			addCircle(center.x, center.y - height + radius / 2, radius * 0.3f, Direction.CCW);
		}
	}

}
