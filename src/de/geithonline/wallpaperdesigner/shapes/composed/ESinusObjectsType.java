
package de.geithonline.wallpaperdesigner.shapes.composed;

public enum ESinusObjectsType {
    star, bubble, heart;
    public static ESinusObjectsType enumForName(final String name) {
        switch (name) {
            default:
            case "Star":
                return ESinusObjectsType.star;
            case "Bubble":
                return ESinusObjectsType.bubble;
            case "Heart":
                return ESinusObjectsType.heart;
        }
    }

}
