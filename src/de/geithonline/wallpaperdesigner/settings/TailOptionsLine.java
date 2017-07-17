
package de.geithonline.wallpaperdesigner.settings;

import de.geithonline.wallpaperdesigner.settings.Settings.TailLineType;
import de.geithonline.wallpaperdesigner.shapes.SinusPath.SinusType;

public class TailOptionsLine extends TailOptions {

	public boolean closedSinus = false;
	public TailLineType tailtype = TailLineType.Sinus;
	public SinusType sinusType = SinusType.decreasingAmplitude;
}
