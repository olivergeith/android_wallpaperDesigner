
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;

public class SinusPath extends Path {

	public enum SinusType {
		normal, decreasingAmplitude;
		public static SinusType enumForName(final String name) {
			switch (name) {
			default:
			case "Decreasing Amplitude":
				return SinusType.decreasingAmplitude;
			case "Normal":
				return SinusType.normal;
			}
		}

	}

	/**
	 * @param center
	 * @param radius
	 * @param sinRepeats
	 *            1 == one bow/Halbwelle, 2 == a complete sinus
	 * @param amplitude
	 */
	public SinusPath(final PointF center, final float radius, final int sinRepeats, final float amplitude, final SinusType type) {
		super();
		switch (type) {
		case decreasingAmplitude:
			drawSinusDecreasingAmplitude(center, radius, sinRepeats, amplitude, false);
			break;
		default:
		case normal:
			drawSinus(center, radius, sinRepeats, amplitude, false);
			break;
		}
	}

	/**
	 * @param center
	 * @param radius
	 * @param sinRepeats
	 *            1 == one bow/Halbwelle, 2 == a complete sinus
	 * @param amplitude
	 */
	public SinusPath(final PointF center, final float radius, final int sinRepeats, final float amplitude, final SinusType type, final boolean filled) {
		super();
		switch (type) {
		case decreasingAmplitude:
			drawSinusDecreasingAmplitude(center, radius, sinRepeats, amplitude, filled);
			break;
		default:
		case normal:
			drawSinus(center, radius, sinRepeats, amplitude, filled);
			break;
		}
	}

	/**
	 * @param center
	 * @param radius
	 * @param sinRepeats
	 *            1 == one bow/Halbwelle, 2 == a complete sinus
	 * @param amplitude
	 */
	public SinusPath(final PointF center, final float radius, final int sinRepeats, final float amplitude) {
		super();
		drawSinus(center, radius, sinRepeats, amplitude, false);
	}

	/**
	 * @param center
	 * @param radius
	 * @param sinRepeats
	 *            1 == one bow/Halbwelle, 2 == a complete sinus
	 * @param amplitude
	 */
	public SinusPath(final PointF center, final float radius, final int sinRepeats, final float amplitude, final boolean filled) {
		super();
		drawSinus(center, radius, sinRepeats, amplitude, filled);
	}

	/**
	 * @param center
	 * @param radius
	 * @param sinRepeats
	 *            1 == one bow, 2 == a complete sinus
	 * @param amplitude
	 */
	private void drawSinus(final PointF center, final float radius, final int sinRepeats, final float amplitude, final boolean filled) {
		// nach links
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float mitteY = center.y;
		moveTo(l, mitteY);
		// und nun der Sinus
		final int points = 100;
		for (int i = 1; i <= points; i++) {
			final float x = l + i * (r - l) / points;
			final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
			final float y = mitteY + (float) (amplitude * Math.sin(angle));
			lineTo(x, y);
		}
		if (filled) {
			close();
		}
	}

	/**
	 * @param center
	 * @param radius
	 * @param sinRepeats
	 *            1 == one bow, 2 == a complete sinus
	 * @param amplitude
	 */
	private void drawSinusDecreasingAmplitude(final PointF center, final float radius, final int sinRepeats, final float amplitude, final boolean filled) {
		// nach links
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float mitteY = center.y;
		moveTo(l, mitteY);
		// und nun der Sinus
		final int points = 100;
		for (int i = 1; i <= points; i++) {
			final float a = (points - i) * amplitude / points;
			final float x = l + i * (r - l) / points;
			final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
			final float y = mitteY + (float) (a * Math.sin(angle));
			lineTo(x, y);
		}
		if (filled) {
			close();
		}

	}

}
