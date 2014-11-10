package de.geithonline.wallpaperdesigner.bitmapdrawer.layout;

public class LayoutProperties {
	private final boolean anzahlPatterns;
	private final boolean blurring;
	private final boolean overlap;
	private final boolean upsideDown;

	private final CharSequence[] variants;

	public LayoutProperties(final boolean anzahlPatterns, final boolean blurring, final boolean overlap, final boolean upsideDown, final CharSequence[] variants) {
		super();
		this.anzahlPatterns = anzahlPatterns;
		this.blurring = blurring;
		this.overlap = overlap;
		this.upsideDown = upsideDown;
		this.variants = variants;
	}

	public boolean hasAnzahlPatterns() {
		return anzahlPatterns;
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

}
