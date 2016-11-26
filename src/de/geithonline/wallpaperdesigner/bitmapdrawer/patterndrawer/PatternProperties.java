package de.geithonline.wallpaperdesigner.bitmapdrawer.patterndrawer;

public class PatternProperties {
	private final boolean outlineOption;
	private final boolean randomrotateOption;
	private final boolean textOption;
	private final boolean filledOption;
	private final boolean leafOption;
	private final boolean glossyOption;

	private final CharSequence[] variants;

	public PatternProperties(final boolean outlineOption, final boolean randomrotateOption, final boolean textOption, final boolean filledOption,
			final boolean leafOption, final boolean glossyOption, final CharSequence[] variants) {
		super();
		this.outlineOption = outlineOption;
		this.randomrotateOption = randomrotateOption;
		this.textOption = textOption;
		this.filledOption = filledOption;
		this.leafOption = leafOption;
		this.glossyOption = glossyOption;
		this.variants = variants;
	}

	public PatternProperties(final boolean outlineOption, final boolean randomrotateOption, final boolean textOption, final boolean filledOption,
			final boolean leafOption, final boolean glossyOption) {
		super();
		this.outlineOption = outlineOption;
		this.randomrotateOption = randomrotateOption;
		this.textOption = textOption;
		this.filledOption = filledOption;
		this.leafOption = leafOption;
		this.glossyOption = glossyOption;
		variants = null;
	}

	public boolean hasOutlineOption() {
		return outlineOption;
	}

	public boolean hasRandomrotateOption() {
		return randomrotateOption;
	}

	public boolean hasTextOption() {
		return textOption;
	}

	public boolean hasFilledOption() {
		return filledOption;
	}

	public boolean hasLeafOption() {
		return leafOption;
	}

	public boolean hasGlossyOption() {
		return glossyOption;
	}

	public boolean hasVariants() {
		return variants != null;
	}

	public CharSequence[] getVariants() {
		return variants;
	}

}
