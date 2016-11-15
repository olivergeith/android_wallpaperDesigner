package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

public class LayoutProperties {
	private final boolean anzahlPatterns;
	private final boolean blurring;
	private final boolean overlap;
	private final boolean upsideDown;

	private final CharSequence[] variants;
	private final boolean randomStartWinkel;
	private boolean adjustableCenter = false;

	public LayoutProperties(final boolean anzahlPatterns, final boolean blurring, final boolean overlap, final boolean upsideDown,
			final boolean randomStartWinkel, final CharSequence[] variants) {
		super();
		this.anzahlPatterns = anzahlPatterns;
		this.blurring = blurring;
		this.overlap = overlap;
		this.upsideDown = upsideDown;
		this.variants = variants;
		this.randomStartWinkel = randomStartWinkel;
	}

	public LayoutProperties(final boolean anzahlPatterns, final boolean blurring, final boolean overlap, final boolean upsideDown,
			final boolean randomStartWinkel, final boolean adjustableCenter, final CharSequence[] variants) {
		super();
		this.anzahlPatterns = anzahlPatterns;
		this.blurring = blurring;
		this.overlap = overlap;
		this.upsideDown = upsideDown;
		this.adjustableCenter = adjustableCenter;
		this.variants = variants;
		this.randomStartWinkel = randomStartWinkel;
	}

	public boolean hasAnzahlPatterns() {
		return anzahlPatterns;
	}

	public boolean hasAdjustableCenter() {
		return adjustableCenter;
	}

	public boolean hasBlurring() {
		return blurring;
	}

	public boolean hasOverlap() {
		return overlap;
	}

	public boolean hasUpsideDown() {
		return upsideDown;
	}

	public boolean hasVariants() {
		return variants != null;
	}

	public CharSequence[] getVariants() {
		return variants;
	}

	public boolean hasRandomStartWinkel() {
		return randomStartWinkel;
	}

}
