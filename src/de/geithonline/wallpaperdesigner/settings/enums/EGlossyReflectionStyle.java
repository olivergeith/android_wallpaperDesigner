package de.geithonline.wallpaperdesigner.settings.enums;

public enum EGlossyReflectionStyle {
	SMALL_OVAL("Small Oval"), //
	BIG_OVAL("Big Oval"), //
	DIAGONAL("Diagonal"), //
	TOP_LEFT("Topleft"), //
	NONE("None"), //
	DIAGONAL_CURVED("Diagonal (curved)"), //
	DIAGONAL_45GRAD("Diagonal 45°"), //
	CURVED_FROM_TOP("Curved from top"), //
	DIAGONAL_FLIPPED("Diagonal (flipped)"), //
	DIAGONAL_45GRAD_FLIPPED("Diagonal 45° (flipped)"), //
	TOP_GLOW("Top Glow"), BOTTOM_GLOW("Bottom Glow"), //
	DIAGONAL_CURVED_V2("Diagonal (curved) V2"), //
	TOP_LEFT_V2("Topleft V2"), //
	FULL_OVAL("Full Oval");

	public static EGlossyReflectionStyle enumForName(final String search) {
		for (final EGlossyReflectionStyle e : EGlossyReflectionStyle.values()) {
			if (search != null && search.equals(e.getName())) {
				return e;
			}
		}
		return DIAGONAL;
	}

	private String name;

	public String getName() {
		return name;
	}

	private EGlossyReflectionStyle(final String name) {
		this.name = name;
	}

}
