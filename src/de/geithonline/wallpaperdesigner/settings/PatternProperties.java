package de.geithonline.wallpaperdesigner.settings;

public class PatternProperties {
	private final boolean outlineOption;
	private final boolean randomrotateOption;
	private final boolean textOption;
	private final boolean filledOption;
	private final boolean leafOption;
	private final boolean glossyOption;

	public PatternProperties(final boolean outlineOption, final boolean randomrotateOption, final boolean textOption, final boolean filledOption,
			final boolean leafOption, final boolean glossyOption) {
		super();
		this.outlineOption = outlineOption;
		this.randomrotateOption = randomrotateOption;
		this.textOption = textOption;
		this.filledOption = filledOption;
		this.leafOption = leafOption;
		this.glossyOption = glossyOption;
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

}
