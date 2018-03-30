
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class LinePath extends Path {

	public enum LINE_STYLE {
		straight, zigzag, bow, blitz, sound;
	}

	public LinePath(final PointF center, final float radius, final LINE_STYLE style, final boolean filled) {
		super();
		switch (style) {
		default:
		case straight:
			drawStraight(center, radius);
			break;
		case blitz:
			drawBlitz(center, radius);
			break;
		case zigzag:
			drawZigZag(center, radius);
			break;
		case bow:
			drawBow(center, radius, filled);
			break;
		case sound:
			drawSound(center, radius, filled);
			break;
		}

	}

	private void drawStraight(final PointF center, final float radius) {
		final float l = center.x - radius;
		final float r = center.x + radius;
		moveTo(l, center.y);
		lineTo(r, center.y);
	}

	private void drawBow(final PointF center, final float radius, final boolean filled) {
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float mitteY = center.y;
		// nach links
		float sinradius = radius * 0.4f;
		if (filled) {
			sinradius = Randomizer.getRandomFloat(-radius * 0.4f, radius * 0.4f);
		}
		final int sinRepeats = 1;
		moveTo(l, mitteY);
		// und nun der Sinus
		final int points = 30;
		for (int i = 1; i <= points; i++) {
			final float x = l + i * (r - l) / points;
			final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
			final float y = mitteY + (float) (sinradius * Math.sin(angle));
			lineTo(x, y);
		}
	}

	private void drawSound(final PointF center, final float radius, final boolean filled) {
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float mitteY = center.y;
		// nach links
		final float sinradius1 = radius * Randomizer.getRandomFloat(0.05f, 0.25f);
		final float sinradius2 = radius * Randomizer.getRandomFloat(0.05f, 0.25f);
		final float sinradius3 = radius * Randomizer.getRandomFloat(0.05f, 0.25f);
		final int sinRepeats1 = Randomizer.getRandomInt(2, 25);
		final int sinRepeats2 = Randomizer.getRandomInt(5, 25);
		final int sinRepeats3 = Randomizer.getRandomInt(10, 25);
		moveTo(l, mitteY);
		// und nun der Sinus
		final int points = 150;
		for (int i = 1; i <= points; i++) {
			final float x = l + i * (r - l) / points;
			final float angle1 = (float) ((float) i / points * Math.PI * sinRepeats1);
			final float angle2 = (float) ((float) i / points * Math.PI * sinRepeats2);
			final float angle3 = (float) ((float) i / points * Math.PI * sinRepeats3);
			final float y = mitteY + (float) (sinradius1 * Math.sin(angle1) + sinradius2 * Math.sin(angle2) + sinradius3 * Math.sin(angle3));
			lineTo(x, y);
		}
	}

	private void drawZigZag(final PointF center, final float radius) {
		// nach links
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float sinradius = radius * 0.1f;
		final float mitteY = center.y;
		final int sinRepeats = 7;
		moveTo(l, mitteY);
		// und nun der Sinus
		final int points = 2 * sinRepeats;
		for (int i = 1; i <= points; i++) {
			final float x = l + i * (r - l) / points;
			final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
			final float y = mitteY + (float) (sinradius * Math.sin(angle));
			lineTo(x, y);
		}
	}

	private void drawBlitz(final PointF center, final float radius) {
		final float sinradius = radius * 0.2f;
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float mitteY = center.y;
		// nach links
		moveTo(l, mitteY);
		// und nun der Sinus
		final int points = 15;

		for (int i = 1; i <= points; i++) {
			final float x = l + i * (r - l) / points;
			final float y;
			if (i % 2 == 0) {
				y = mitteY + Randomizer.getRandomFloat(0, sinradius);
			} else {
				y = mitteY + Randomizer.getRandomFloat(-sinradius, 0);
			}
			lineTo(x, y);
		}
	}

	private Path getBlitzArm(final int anzahl, final PointF startingPoint, final float radius, final float winkel) {
		final Path p = new Path();
		final float sinradius = radius * 0.2f;
		p.moveTo(startingPoint.x, startingPoint.y);
		for (int i = 1; i <= anzahl; i++) {
			final float x = startingPoint.x + i * radius / anzahl;
			final float y;
			if (i % 2 == 0) {
				y = startingPoint.y + Randomizer.getRandomFloat(0, sinradius);
			} else {
				y = startingPoint.y + Randomizer.getRandomFloat(-sinradius, 0);
			}
			p.lineTo(x, y);
			// rekursion
			if (i == Randomizer.getRandomInt(1, anzahl)) {
				getBlitzArm(anzahl - i, new PointF(x, y), radius - (i * radius / anzahl), Randomizer.getRandomFloat(-30, 00));
			}

		}
		if (winkel != 0) {
			PathHelper.rotatePath(startingPoint.x, startingPoint.y, p, winkel);
		}
		addPath(p);
		return p;
	}

}
