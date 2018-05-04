package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.Arrays;
import java.util.List;

public enum ELayoutVariant {
	RANDOM("Random", Arrays.asList(//
			ELayoutSubVariant.NONE//
	)), //

	LOGICAL_DIRECTED("Logical", Arrays.asList( //
			ELayoutSubVariant.INNER, //
			ELayoutSubVariant.OUTER, //
			ELayoutSubVariant.TOWER, //
			ELayoutSubVariant.CENTER, //
			ELayoutSubVariant.TRISTEP, //
			ELayoutSubVariant.QUADSTEP, //
			ELayoutSubVariant.DUO_CENTER, //
			ELayoutSubVariant.DUO_STEP_INNER_2_OUTER, //
			ELayoutSubVariant.DUO_STEP_OUTER_2_INNER //
	)), //

	GEOMETRICAL_DIRECTED("Directed", Arrays.asList( //
			ELayoutSubVariant.INNER, //
			ELayoutSubVariant.OUTER, //
			ELayoutSubVariant.LEFT_RIGHT, //
			ELayoutSubVariant.RIGHT_LEFT, //
			ELayoutSubVariant.TOP_BOTTOM, //
			ELayoutSubVariant.BOTTOM_TOP, //
			ELayoutSubVariant.TOP_LEFT_BOTTOM_RIGHT, //
			ELayoutSubVariant.TOP_RIGHT_BOTTOM_LEFT //
	)), //
	ALTERNATING("Alternating", Arrays.asList( //
			ELayoutSubVariant.ALL_CORNERS, //
			ELayoutSubVariant.ALL_SIDES, //
			ELayoutSubVariant.LEFT_RIGHT, //
			ELayoutSubVariant.TOP_BOTTOM, //
			ELayoutSubVariant.TOP_LEFT_BOTTOM_RIGHT, //
			ELayoutSubVariant.TOP_RIGHT_BOTTOM_LEFT, //
			ELayoutSubVariant.TOP_RIGHT_TOP_LEFT, //
			ELayoutSubVariant.BOTTOM_RIGHT_BOTTOM_LEFT //
	));//

	private String name;
	private List<ELayoutSubVariant> subVariants;

	public String getName() {
		return name;
	}

	private ELayoutVariant(final String name, final List<ELayoutSubVariant> subVariants) {
		this.name = name;
		this.subVariants = subVariants;
	}

	public static ELayoutVariant getEnumForName(final String search) {
		for (final ELayoutVariant e : ELayoutVariant.values()) {
			if (search != null && search.equals(e.getName())) {
				return e;
			}
		}
		return RANDOM;
	}

	public List<ELayoutSubVariant> getESubVariants() {
		return subVariants;
	}

	public CharSequence[] getSubVariants() {
		final CharSequence[] sequence = new CharSequence[subVariants.size()];
		for (int i = 0; i < subVariants.size(); i++) {
			sequence[i] = subVariants.get(i).getName();
		}
		return sequence;
	}

	public boolean hasSubVariants() {
		return subVariants != null;
	}

}
