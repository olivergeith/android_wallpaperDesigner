package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class PumpkinPath extends Path {

	public enum PUMPKIN_TYP {
		MUNDRUND_AUGERUND, MUNDZACKIG_AUGERUND, RANDOM, MUNDZACKIG_AUGEDREIECKIGV2;
	}

	public static final int AUGE_RUND = 1;
	public static final int AUGE_DREIECKIG = 2;
	public static final int AUGE_DREIECKIG_V2 = 3;

	public static final int MUND_RUND = 1;
	public static final int MUND_ZACKIG = 2;

	public PumpkinPath(final PointF center, final float radius, final PUMPKIN_TYP variante) {
		super();
		switch (variante) {
		default:
		case MUNDRUND_AUGERUND:
			drawPumpkin(center, radius, AUGE_RUND, MUND_RUND);
			break;
		case MUNDZACKIG_AUGERUND:
			drawPumpkin(center, radius, AUGE_RUND, MUND_ZACKIG);
			break;
		case MUNDZACKIG_AUGEDREIECKIGV2:
			drawPumpkin(center, radius, AUGE_DREIECKIG_V2, MUND_ZACKIG);
			break;
		case RANDOM:
			drawPumpkin(center, radius, Randomizer.getRandomInt(1, 3), Randomizer.getRandomInt(1, 2));
			break;
		}

	}

	private void drawPumpkin(final PointF center, final float radius, final int augentyp, final int mundtyp) {
		addPath(getKuerbis(center, radius));
		switch (mundtyp) {
		default:
		case MUND_RUND:
			addPath(getMouthRund(center, radius));
			break;
		case MUND_ZACKIG:
			addPath(getMouth(center, radius));
			break;
		}
		switch (augentyp) {
		default:
		case AUGE_RUND:
			addPath(getAugenRund(center, radius));
			break;
		case AUGE_DREIECKIG:
			addPath(getAugenDreieck(center, radius));
			break;
		case AUGE_DREIECKIG_V2:
			addPath(getAugenDreieckV2(center, radius));
			break;
		}
		close();
	}

	private Path getKuerbis(final PointF center, final float radius) {
		final Path p = new Path();
		final float raster = radius / 7;

		p.moveTo(center.x - 2 * raster, center.y - 4 * raster);
		p.cubicTo(center.x - 8 * raster, center.y - 8 * raster, // CP1
				center.x - 8 * raster, center.y + 8 * raster, // CP2
				center.x - 1 * raster, center.y + 4 * raster);

		p.quadTo(center.x + 0 * raster, center.y + 5 * raster, // controllpoint
				center.x + 1 * raster, center.y + 4 * raster); // Zielpunkt

		p.cubicTo(center.x + 8 * raster, center.y + 8 * raster, // CP1
				center.x + 8 * raster, center.y - 8 * raster, // CP2
				center.x + 2 * raster, center.y - 4 * raster);

		p.quadTo(center.x + 0 * raster, center.y - 6 * raster, // controllpoint
				center.x - 2 * raster, center.y - 4 * raster); // Zielpunkt
		p.close();

		// stengel
		p.moveTo(center.x - 1 * raster, center.y - 5 * raster);
		p.quadTo(center.x + 0 * raster, center.y - 5.5f * raster, // controllpoint
				center.x + 1 * raster, center.y - 5 * raster); // Zielpunkt
		p.quadTo(center.x + 0 * raster, center.y - 8 * raster, // controllpoint
				center.x - 2 * raster, center.y - 8 * raster); // Zielpunkt
		p.quadTo(center.x - 0 * raster, center.y - 7 * raster, // controllpoint
				center.x - 1 * raster, center.y - 5 * raster); // Zielpunkt
		p.close();
		// nase
		p.moveTo(center.x - 0 * raster, center.y - 0.5f * raster);
		p.lineTo(center.x + 0.5f * raster, center.y + 0.5f * raster);
		p.lineTo(center.x - 0.5f * raster, center.y + 0.5f * raster);
		p.close();

		return p;
	}

	private Path getMouth(final PointF center, final float radius) {
		final Path p = new Path();
		final float raster = radius / 9;

		p.moveTo(center.x - 6 * raster, center.y - 0 * raster);
		p.lineTo(center.x - 4 * raster, center.y + 2 * raster);
		p.lineTo(center.x - 2 * raster, center.y + 1 * raster);
		p.lineTo(center.x - 0 * raster, center.y + 2 * raster);
		p.lineTo(center.x + 2 * raster, center.y + 1 * raster);
		p.lineTo(center.x + 4 * raster, center.y + 2 * raster);
		p.lineTo(center.x + 6 * raster, center.y + 0 * raster);
		p.lineTo(center.x + 4 * raster, center.y + 4 * raster);
		p.lineTo(center.x + 2 * raster, center.y + 2 * raster);
		p.lineTo(center.x + 0 * raster, center.y + 4 * raster);
		p.lineTo(center.x - 2 * raster, center.y + 2 * raster);
		p.lineTo(center.x - 4 * raster, center.y + 4 * raster);
		p.close();
		return p;
	}

	private Path getMouthRund(final PointF center, final float radius) {
		final Path p = new Path();
		final float raster = radius / 9;

		p.moveTo(center.x - 6 * raster, center.y - 0 * raster);
		p.quadTo(center.x - 0 * raster, center.y + 2.5f * raster, // controllpoint
				center.x + 6 * raster, center.y - 0 * raster);
		p.lineTo(center.x + 3 * raster, center.y + 2 * raster);
		p.lineTo(center.x + 2.5f * raster, center.y + 4 * raster);
		p.lineTo(center.x + 2 * raster, center.y + 2 * raster);
		p.quadTo(center.x - 0 * raster, center.y + 2.5f * raster, // controllpoint
				center.x - 2 * raster, center.y + 2 * raster);
		p.lineTo(center.x - 2.5f * raster, center.y + 4 * raster);
		p.lineTo(center.x - 3 * raster, center.y + 2 * raster);
		p.close();
		return p;
	}

	private Path getAugenDreieck(final PointF center, final float radius) {
		final Path p = new Path();
		final float raster = radius / 8;

		p.moveTo(center.x - 1 * raster, center.y - 1 * raster);
		p.lineTo(center.x - 5 * raster, center.y - 1.5f * raster);
		p.lineTo(center.x - 4 * raster, center.y - 4 * raster);
		p.lineTo(center.x - 3 * raster, center.y - 3 * raster);
		p.quadTo(center.x - 3.5f * raster, center.y - 1.5f * raster, // controllpoint
				center.x - 2 * raster, center.y - 2 * raster);
		p.close();
		p.moveTo(center.x + 1 * raster, center.y - 1 * raster);
		p.lineTo(center.x + 2 * raster, center.y - 2 * raster);
		p.quadTo(center.x + 3.5f * raster, center.y - 1.5f * raster, // controllpoint
				center.x + 3 * raster, center.y - 3 * raster);
		p.lineTo(center.x + 4 * raster, center.y - 4 * raster);
		p.lineTo(center.x + 5 * raster, center.y - 1.5f * raster);
		p.close();
		return p;
	}

	private Path getAugenDreieckV2(final PointF center, final float radius) {
		final Path p = new Path();
		final float raster = radius / 8;

		p.moveTo(center.x - 1 * raster, center.y - 1 * raster);
		p.lineTo(center.x - 2 * raster, center.y - 0.6f * raster);
		p.lineTo(center.x - 3 * raster, center.y - 1 * raster);
		p.lineTo(center.x - 3 * raster, center.y - 0.3f * raster);
		p.lineTo(center.x - 4 * raster, center.y - 0 * raster);
		p.quadTo(center.x - 5 * raster, center.y - 2 * raster, // controllpoint
				center.x - 4 * raster, center.y - 4 * raster);
		p.quadTo(center.x - 3 * raster, center.y - 2 * raster, // controllpoint
				center.x - 1 * raster, center.y - 1 * raster);
		p.close();

		p.moveTo(center.x + 1 * raster, center.y - 1 * raster);
		p.quadTo(center.x + 3 * raster, center.y - 2 * raster, // controllpoint
				center.x + 4 * raster, center.y - 4 * raster);
		p.quadTo(center.x + 5 * raster, center.y - 2 * raster, // controllpoint
				center.x + 4 * raster, center.y - 0 * raster);
		p.lineTo(center.x + 3 * raster, center.y - 0.3f * raster);
		p.lineTo(center.x + 3 * raster, center.y - 1 * raster);
		p.lineTo(center.x + 2 * raster, center.y - 0.6f * raster);

		p.close();

		return p;
	}

	private Path getAugenRund(final PointF center, final float radius) {
		final Path p = new Path();
		p.addPath(getAugeRundLinks(center, radius));
		p.addPath(getAugeRundRechts(center, radius));
		return p;
	}

	private Path getAugeRundLinks(final PointF center, final float radius) {
		final Path p = new Path();
		final float raster = radius / 8;

		final RectF oval = new RectF();
		oval.left = center.x - 5 * raster;
		oval.right = center.x - 1 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 2 * raster;

		p.moveTo(center.x - 1 * raster, center.y - 0 * raster);
		p.arcTo(oval, 0, 180);
		p.lineTo(center.x - 3 * raster, center.y - 0 * raster);
		oval.left = center.x - 3 * raster;
		oval.right = center.x - 2 * raster;
		oval.top = center.y - 0.5f * raster;
		oval.bottom = center.y + 0.5f * raster;
		p.arcTo(oval, 180, -180);
		p.close();
		PathHelper.rotatePath(center.x, center.y, p, 45);
		return p;
	}

	private Path getAugeRundRechts(final PointF center, final float radius) {
		final Path p = new Path();
		final float raster = radius / 8;

		final RectF oval = new RectF();

		p.moveTo(center.x + 1 * raster, center.y - 0 * raster);
		p.lineTo(center.x + 2 * raster, center.y - 0 * raster);

		oval.left = center.x + 2 * raster;
		oval.right = center.x + 3 * raster;
		oval.top = center.y - 0.5f * raster;
		oval.bottom = center.y + 0.5f * raster;
		p.arcTo(oval, -180, -180);

		p.lineTo(center.x + 5 * raster, center.y - 0 * raster);

		oval.left = center.x + 1 * raster;
		oval.right = center.x + 5 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 2 * raster;
		p.arcTo(oval, 0, 180);

		p.close();
		PathHelper.rotatePath(center.x, center.y, p, -45);
		return p;
	}

}
