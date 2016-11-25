package de.geithonline.wallpaperdesigner.settings;

import java.util.HashMap;
import java.util.Map;

public class PatternPropertyStore {

	private static final Map<String, PatternProperties> patternProperties = new HashMap<String, PatternProperties>();

	static {
		// new PatternProperties(outline, randomrotate, text, filled, leaf, glossy)
		patternProperties.put("Assorted Shapes",
				new PatternProperties(true, true, false, true, false, true, //
						new CharSequence[] { "Lemon", "YingYang", "Crop Circles", "Roses", "Pillows", "Android", "Ikae Robot", "Footprint", "Pentagram",
								"4Sails", "Dice", "Drop", "Hedgehog", "Dandelion", "Deathstar", "R2D2", "Sonic", "Iron Cross", "Mixed" }));

		patternProperties.put("Bubbles", new PatternProperties(true, false, false, false, false, true, //
				new CharSequence[] { "Bubble" }));

		patternProperties.put("Chess", new PatternProperties(true, true, false, false, false, false, //
				new CharSequence[] { "Square", "Star", "Circle", "Pillow", "Heart", "Arrow", "Triangle", "Mixed" }));

		patternProperties.put("Fish", new PatternProperties(true, true, false, false, false, true, //
				new CharSequence[] { "V1", "V2", "Shark V1", "Shark V2", "Mixed" }));

		patternProperties.put("Flipped", new PatternProperties(true, true, false, true, false, false, //
				new CharSequence[] { "Triangle", "Triangle V2", "Square", "Rectangle", "Quarter Arc", "Quarter Arc V2" }));

		patternProperties.put("Flowers", new PatternProperties(true, true, false, true, true, true, //
				new CharSequence[] { "V1", "V2", "V3", "V4", "V5", "V6", "V7", "Mixed V1-V3", "Mixed V4-V5" }));

		patternProperties.put("Gears-Saws", new PatternProperties(true, false, false, true, true, true, //
				new CharSequence[] { "Gear", "Saw", "Star Gear", "Mixed" }));

		patternProperties.put("Geometrical Shapes",
				new PatternProperties(true, true, false, true, false, true, //
						new CharSequence[] { "Triangle", "Square", "Square (rounded)", "Square (Mixed)", "Pentagon", "Hexagon", "Octagon", "Circle", "Oval",
								"Oval (random width)", "Oval (random)", "Half Circle", "Mixed", "Mixed (with Circle)" }));

		patternProperties.put("Geometrical (long) Shapes",
				new PatternProperties(true, true, false, true, false, false, //
						new CharSequence[] { "Rectangle", "Rectangle (rounded)", "Rectangle (Mixed)", //
								"Triangle", "Oval", "Diamond", "Dragon", "Dragon (upsidedown)", "Drop", "Drop (upsidedown)", "Lense", "Lense V2", "Lense V3", //
								"Tag", "Knife", "Knife V2", "Knife V3", //
								"Cross", "Cross (Sharp)", "Cross (Split)", "Cross (Split 2)", //
								"Cross (Slim)", "Cross (Slim V2)", "Cross (Slim V3)", "Cross (Slim-Double)", //
								"Spiky Cross", "Double Cross", "Sperm", //
								"Virus", "Virus V2", "Virus V3", //
								"Long Heart", "Square Chain", "Circle Chain", "Circle Chain (upsidedown)", "Spear", "Iron Cross", //
								"Bird", "Bird V2", "Golf Pin", "Pin", "Tulip (Slim)", "Tulip", "Tulip (Fat)", "Plane", "Spaceship", "Spaceship V2",
								"Ritual Axe", "Arrow" }));

		patternProperties.put("3D (long) Shapes", new PatternProperties(true, true, false, false, false, false, //
				new CharSequence[] { "Long Pyramide", "Pyramide", "Long Cube", "Cube", "Long Cone", "Cone", "Mixed" }));

		// new PatternProperties(outline, randomrotate, text, filled, leaf, glossy)
		patternProperties.put("Hearts", new PatternProperties(true, true, false, false, false, true, //
				new CharSequence[] { "Curvy", "Straigth", "Round", "Peek", "Lovely", "Asymetric", "Mixed" }));

		patternProperties.put("Invertable Shapes",
				new PatternProperties(true, true, false, true, false, true, //
						new CharSequence[] { "Heart V1", "Heart V2", "Arrow", "Arrow (round)", "Plus", "Minus", "Star", "Gear", "Crown", "Mixed",
								"Mixed Plus-Minus" }));

		patternProperties.put("Leafs", new PatternProperties(true, true, false, false, false, true, //
				new CharSequence[] { "Maple", "Finger Maple", "Weed", "Round Leaf", "Mixed" }));
		patternProperties.put("Lines", new PatternProperties(false, false, false, false, false, false, //
				new CharSequence[] { "Blitz", "Crickle Crackle", "Maze", "Spirals", "Streamers", "Mixed" }));
		patternProperties.put("Logos",
				new PatternProperties(true, true, false, false, false, true, //
						new CharSequence[] { "Resurrection Remix", "Resurrection Remix V2", "Resurrection Remix V3", "Resurrection Remix V4",
								"Resurrection Remix V5", "Resurrection Remix V6", "ElementalX Kernel", "Peace Sign", "Weed Sign", "Weed Sign V2", "Nexus V1",
								"Nexus V2", "Nexus V3", "Oneplus One V1", "Oneplus One V2", "LG V1", "LG V2" }));

		patternProperties.put("Mandala", new PatternProperties(true, true, false, false, true, false, //
				new CharSequence[] { "V1", "V2", "V3", "V4", "Mixed" }));

		patternProperties.put("Maritim", new PatternProperties(true, true, false, false, true, true, //
				new CharSequence[] { "Sailboat V1", "Sailboat V2", "Lighthouse", "Anchor", "Mixed" }));

		patternProperties.put("Material",
				new PatternProperties(true, false, false, false, false, false, //
						new CharSequence[] { "Stripe", "Stripe V2", "Stripe V3", "Half Stripe", "Half Stripe V2", "Half Stripe V3", "Arc 1", "Arc 2", "Arc 3",
								"Skyline", "Pyramide Skyline", "Edgy Bars", "Rotating Bars", "Rotating Triangles", "Rotating Arches (random arches)",
								"Rotating Arches (quarter arches)", "Rotating Arches (half arches)", "Rotating Arches (3-quarter arches)" }));

		patternProperties.put("PacMan", new PatternProperties(true, true, false, false, false, true, //
				new CharSequence[] { "Ghost", "Pacman", "Mixed" }));

		patternProperties.put("Pillows",
				new PatternProperties(true, true, false, false, false, true, //
						new CharSequence[] { "3 Edge Pillow", "4 Edge Pillow", "5 Edge Pillow", "6 Edge Pillow", "Plectrum", "Fingernail", "Treky", "YingYang",
								"Peeek", "Armor", "Messer", "Blazon", "Mixed" }));

		patternProperties.put("Planes", new PatternProperties(true, true, false, false, false, true, //
				new CharSequence[] { "Old Planes", "Boing", "Stealthbomber", "Mixed" }));

		// new PatternProperties(outline, randomrotate, text, filled, leaf, glossy)
		patternProperties.put("Puzzle", new PatternProperties(true, true, false, true, false, true, //
				new CharSequence[] { "Manneken", "Top-Right", "Cross", "All", "Mixed" }));
		patternProperties.put("Puzzle (Circle Connector)", new PatternProperties(true, true, false, true, false, true, //
				new CharSequence[] { "Manneken", "Top-Right", "Cross", "All", "Mixed" }));
		patternProperties.put("Puzzle (Square)", new PatternProperties(true, true, false, true, false, true, //
				new CharSequence[] { "Manneken", "Top-Right", "Cross", "All", "Mixed" }));
		patternProperties.put("Puzzle (Square Connector)", new PatternProperties(true, true, false, true, false, true, //
				new CharSequence[] { "Manneken", "Top-Right", "Cross", "All", "Mixed" }));

		patternProperties.put("Rectangles",
				new PatternProperties(true, true, false, true, false, true, //
						new CharSequence[] { "Random Ratio", "Random Ratio (Rounded)", "Random Ratio (Mixed)", //
								"Golden Ratio", "Golden Ratio (Rounded)", "Golden Ratio (Mixed)", //
								"4-3 Ratio", "4-3 Ratio (Rounded)", "4-3 Ratio (Mixed)", //
								"1-2 Ratio", "1-2 Ratio (Rounded)", "1-2 Ratio (Mixed)" }));

		patternProperties.put("Rings", new PatternProperties(true, true, false, true, false, true, //
				new CharSequence[] { "Rings V1 (Flange)", "Rings V2 (Asymetric)", "Rings V3 (Concentric)", "Rings V4 (Dizzy)", "Mixed" }));

		patternProperties.put("Shells", new PatternProperties(true, true, false, false, true, true, //
				new CharSequence[] { "Shells V1", "Shells V2", "Shells V3", "Shells V4", "Shells V5", "Shells V6", "Shells Mixed" }));

		patternProperties.put("Smiley", new PatternProperties(true, true, false, true, false, true, //
				new CharSequence[] { "Happy", "Sad", "Mixed" }));

		patternProperties.put("Sonic", new PatternProperties(true, true, false, true, true, true, //
				new CharSequence[] { "Normal", "Double", "Special", "One Arm" }));

		patternProperties.put("Spooky",
				new PatternProperties(true, true, false, false, false, true, //
						new CharSequence[] { "Skull", "Bat V1 (Aarons Cute Design)", "Bat V2", "Bat V3", "Bat V4", "Ghost V1", "Ghost V2", "Owl V1", "Owl V2",
								"Owl V3", "Owl V4", "Pumpkin (round eyes)", "Pumpkin (triangel eyes)", "Pumpkin (round eyes and mouth)", "Mixed",
								"Mixed Pumpkins", "Mixed Bats", "Mixed Ghosts", "Mixed Owls" }));

		patternProperties.put("Space",
				new PatternProperties(true, true, false, true, false, true, //
						new CharSequence[] { "Rocket V1", "Rocket V2", "Rocket V3", "Rocket V4", "Rocket V5", "Rocket V6", "Ufo V1", "Ufo V2", "Satellite",
								"Spaceship", "Mixed Rockets", "Mixed" }));

		patternProperties.put("Square",
				new PatternProperties(true, true, false, true, false, true, //
						new CharSequence[] { "Square (round inner corner)", "Square (round inner corner V2)", "Square (rounded)",
								"Square (square inner corner)", "Square (line corner)", "Square (circle corner)", "Square (outer circle corner)",
								"Square (castel)" }));

		patternProperties.put("Stars", new PatternProperties(true, true, false, true, true, true, //
				new CharSequence[] { "Nomral", "Spikey", "Star Circle", "Mixed" }));

		// new PatternProperties(outline, randomrotate, text, filled, leaf, glossy)
		patternProperties.put("Text", new PatternProperties(true, false, true, true, false, false, //
				new CharSequence[] { "Letters", "Numbers", "Custom Text" }));

		patternProperties.put("Weather",
				new PatternProperties(true, true, false, true, true, true, //
						new CharSequence[] { "Sun", "Cloud", "Sun with Flames", "Sun with Flames (Drop Style)", "Sun with Flames (Triangle)",
								"Sun with Flames (Arrows)", "Sun with Flames (Arrows V2)", "Sun with Flames (Sharp Tooth)", "Mixed" }));

		patternProperties.put("Virus Attack", new PatternProperties(true, true, false, true, false, true, //
				new CharSequence[] { "V1", "V2", "V3", "V4", "V5", "V6", "V7", "V8", "Mixed" }));

		patternProperties.put("Xmas", new PatternProperties(true, true, false, true, false, true, //
				new CharSequence[] { "Tree" }));

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
