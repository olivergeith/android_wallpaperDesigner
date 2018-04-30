package de.geithonline.wallpaperdesigner.settings.enums;

public enum EGlossyGlowStyle {
	CENTER("Center"), //
	HORIZONTAL("Horizontal"), //
	VERTICAL("Vertical"), //
	VERTICAL_WHITE("Vertical (White)"), //
	VERTICAL_RAINBOW("Vertical Rainbow");

	public static EGlossyGlowStyle enumForName(final String search) {
		for (final EGlossyGlowStyle e : EGlossyGlowStyle.values()) {
			if (search != null && search.equals(e.getName())) {
				return e;
			}
		}
		return CENTER;
	}

	private String name;

	public String getName() {
		return name;
	}

	private EGlossyGlowStyle(final String name) {
		this.name = name;
	}

}
