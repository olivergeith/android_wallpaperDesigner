
package de.geithonline.wallpaperdesigner.shapes.composed;

public enum ESinusObjectsType {
    star, bubble, heart, heart90, heart270;
    public static ESinusObjectsType enumForName(final String name) {
        switch (name) {
            default:
            case "Star":
                return ESinusObjectsType.star;
            case "Bubble":
                return ESinusObjectsType.bubble;
            case "Heart":
                return ESinusObjectsType.heart;
            case "Heart90":
                return ESinusObjectsType.heart90;
            case "Heart270":
                return ESinusObjectsType.heart270;
        }
    }

    public static ESinusObjectsType enumForNameUsedInQualle(final String name) {
        switch (name) {
            default:
            case "Star":
                return ESinusObjectsType.star;
            case "Bubble":
                return ESinusObjectsType.bubble;
            case "Heart":
            case "Heart90":
            case "Heart270":
                return ESinusObjectsType.heart90;
        }
    }

}
