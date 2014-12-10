package de.geithonline.wallpaperdesigner.settings;

import java.util.HashMap;
import java.util.Map;

public class PatternPropertyStore {

	private static final Map<String, PatternProperties> patternProperties = new HashMap<String, PatternProperties>();

	static {
		// new PatternProperties(outline, randomrotate, text, filled, leaf,
		// glossy)
		patternProperties.put("Assorted Shapes", new PatternProperties(true, true, false, false, false, false, //
				new CharSequence[] { "Lemon", "YingYang", "Crop Circles", "Roses", "Pillows", "Android", "Ikae Robot",
						"Footprint", "Pentagram", "4Sails", "Dice", "Drop", "Mixed" }));
		patternProperties.put("Bubbles", new PatternProperties(true, false, false, false, false, true));
		patternProperties.put("Chess", new PatternProperties(true, true, false, false, false, false, //
				new CharSequence[] { "Square", "Star", "Circle", "Pillow", "Heart", "Arrow", "Triangle", "Mixed" }));
		patternProperties.put("Dandelion", new PatternProperties(true, false, false, false, false, false));
		patternProperties.put("Deathstars", new PatternProperties(true, true, false, false, false, false));

		patternProperties.put("Fish", new PatternProperties(true, true, false, false, false, false, //
				new CharSequence[] { "V1", "V2", "Shark V1", "Shark V2", "Mixed" }));

		patternProperties.put("Flowers", new PatternProperties(true, true, false, true, true, false, //
				new CharSequence[] { "V1", "V2", "V3", "V4", "V5", "V6", "V7", "Mixed V1-V3", "Mixed V4-V5" }));

		patternProperties.put("Gears", new PatternProperties(true, false, false, true, false, false));

		patternProperties.put("Geometrical Shapes", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "Triangle", "Square", "Square (rounded)", "Square (Mixed)", "Pentagon", "Hexagon",
						"Octagon", "Circle", "Half Circle", "Mixed", "Mixed (with Circle)" }));

		patternProperties.put("Geometrical (long) Shapes", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "Rectangle", "Rectangle (rounded)", "Rectangle (Mixed)", "Triangle", "Oval",
						"Diamond", "Dragon", "Dragon (upsidedown)", "Drop", "Lense", "Lense V2", "Lense V3", "Tag",
						"Knife", "Knife V2", "Knife V3", "Cross", "Double Cross", "Sperm", "Virus", "Virus V2",
						"Long Heart" }));

		patternProperties.put("Hearts", new PatternProperties(true, true, false, false, false, true, //
				new CharSequence[] { "Curvy", "Straigth", "Round", "Peek", "Lovely", "Asymetric", "Mixed" }));

		patternProperties.put("Hedgehog", new PatternProperties(true, true, false, true, false, false));

		patternProperties.put("Invertable Shapes", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "Heart V1", "Heart V2", "Arrow", "Arrow (round)", "Plus", "Minus", "Star", "Gear",
						"Crown", "Mixed", "Mixed Plus-Minus" }));

		patternProperties.put("Lines", new PatternProperties(false, false, false, false, false, false, //
				new CharSequence[] { "Blitz", "Crickle Crackle", "Maze", "Spirals", "Streamers", "Mixed" }));

		patternProperties.put("Mandala", new PatternProperties(true, true, false, false, true, false, //
				new CharSequence[] { "V1", "V2", "V3", "V4", "Mixed" }));

		patternProperties.put("Maritim", new PatternProperties(true, true, false, false, true, false, //
				new CharSequence[] { "Sailboat V1", "Sailboat V2", "Lighthouse", "Anchor", "Mixed" }));

		patternProperties.put("Material", new PatternProperties(true, false, false, false, false, false, //
				new CharSequence[] { "Stripe", "Half Stripe", "Arc 1", "Arc 2", "Skyline", "Edgy Bars",
						"Rotating Bars", "Rotating Triangles", "Rotating Arches (random arches)",
						"Rotating Arches (quarter arches)", "Rotating Arches (half arches)",
						"Rotating Arches (3-quarter arches)" }));

		patternProperties.put("PacMan", new PatternProperties(true, true, false, false, false, true, //
				new CharSequence[] { "Ghost", "Pacman", "Mixed" }));

		patternProperties.put("Pillows", new PatternProperties(true, true, false, false, false, false, //
				new CharSequence[] { "3 Edge Pillow", "4 Edge Pillow", "5 Edge Pillow", "6 Edge Pillow", "Plectrum",
						"Fingernail", "Treky", "YingYang", "Peeek", "Armor", "Messer", "Blazon", "Mixed" }));

		patternProperties.put("Planes", new PatternProperties(true, true, false, false, false, false, //
				new CharSequence[] { "Old Planes", "Boing", "Stealthbomber", "Mixed" }));

		patternProperties.put("Puzzle", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "Manneken", "Top-Right", "Cross", "All", "Mixed" }));
		patternProperties.put("Puzzle (Circle Connector)", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "Manneken", "Top-Right", "Cross", "All", "Mixed" }));
		patternProperties.put("Puzzle (Square)", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "Manneken", "Top-Right", "Cross", "All", "Mixed" }));
		patternProperties.put("Puzzle (Square Connector)", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "Manneken", "Top-Right", "Cross", "All", "Mixed" }));

		patternProperties.put("Rectangles", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "Random Ratio", "Random Ratio (Rounded)", "Random Ratio (Mixed)",//
						"Golden Ratio", "Golden Ratio (Rounded)", "Golden Ratio (Mixed)", //
						"4-3 Ratio", "4-3 Ratio (Rounded)", "4-3 Ratio (Mixed)", //
						"1-2 Ratio", "1-2 Ratio (Rounded)", "1-2 Ratio (Mixed)" }));

		patternProperties.put("Rings", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "Rings V1 (Flange)", "Rings V2 (Asymetric)", "Rings V3 (Concentric)",
						"Rings V4 (Dizzy)", "Mixed" }));

		patternProperties.put("Saw", new PatternProperties(true, false, false, true, false, false));

		patternProperties.put("Shells", new PatternProperties(true, true, false, false, true, false, //
				new CharSequence[] { "Shells V1", "Shells V2", "Shells V3", "Shells V4", "Shells V5", "Shells V6",
						"Shells Mixed" }));

		patternProperties.put("Spooky", new PatternProperties(true, true, false, false, false, false, //
				new CharSequence[] { "Skull", "Bat V1 (Aarons Cute Design)", "Bat V2", "Bat V3", "Bat V4", "Ghost V1",
						"Ghost V2", "Owl V1", "Owl V2", "Owl V3", "Owl V4", "Mixed", "Mixed Bats", "Mixed Ghosts",
						"Mixed Owls" }));

		patternProperties.put("Smiley", new PatternProperties(true, true, false, true, false, true));

		patternProperties.put("Space", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "Rocket V1", "Rocket V2", "Rocket V3", "Rocket V4", "Rocket V5", "Rocket V6",
						"Ufo V1", "Ufo V2", "Satellite", "Mixed Rockets", "Mixed" }));

		patternProperties.put("Stars", new PatternProperties(true, true, false, false, true, true, //
				new CharSequence[] { "Nomral", "Outline", "Spikey", "Mixed" }));
		patternProperties.put("Star Circles", new PatternProperties(true, true, false, true, false, false));

		patternProperties.put("Text", new PatternProperties(true, false, true, true, false, false, //
				new CharSequence[] { "Letters", "Numbers", "Custom Text" }));

		patternProperties.put("Weather", new PatternProperties(true, true, false, true, true, false, //
				new CharSequence[] { "Sun", "Cloud", "Mixed" }));

		patternProperties.put("Virus Attack", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "V1", "V2", "V3", "V4", "V5", "V6", "V7", "Mixed" }));

		patternProperties.put("XmasTrees", new PatternProperties(true, true, false, true, false, false));

	}

	public static boolean hasPatternTextOption(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasTextOption();
	}

	public static boolean hasPatternGlossyEffect(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasGlossyOption();
	}

	public static boolean hasNumberOfLeafsOption(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasLeafOption();
	}

	public static boolean hasPatternFilledOption(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasFilledOption();
	}

	public static boolean hasPatternRandomRotate(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasRandomrotateOption();
	}

	public static boolean hasPatternOutlineEffect(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasOutlineOption();
	}

	public static boolean hasPatternVariants(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasVariants();
	}

	public static CharSequence[] getPatternVariants(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return null;
		}
		final CharSequence[] variants = p.getVariants();
		return variants;
	}

	public static boolean hasPatternSpecialSettings(final String pattern) {
		return PatternPropertyStore.hasPatternGlossyEffect(pattern)//
				|| PatternPropertyStore.hasPatternOutlineEffect(pattern)//
				|| PatternPropertyStore.hasPatternRandomRotate(pattern)//
				|| PatternPropertyStore.hasPatternTextOption(pattern)//
				|| PatternPropertyStore.hasNumberOfLeafsOption(pattern)//
				|| PatternPropertyStore.hasPatternFilledOption(pattern);
	}
}
