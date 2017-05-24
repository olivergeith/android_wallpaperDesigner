// package de.geithonline.wallpaperdesigner.bitmapdrawer.backgrounddrawer;
//
// import android.graphics.Canvas;
// import android.graphics.Paint;
// import android.graphics.PointF;
// import android.graphics.Rect;
// import de.geithonline.wallpaperdesigner.settings.Settings;
// import de.geithonline.wallpaperdesigner.utils.ColorHelper;
//
// public class BackgroundDrawerTornadoV2 {
//
// /**
// * @param canvas
// * @param rings
// * staerke des Tornados 1-4 sinnnvoll
// * @param center
// */
// public static void draw4ColorTornado(final Canvas canvas, final int rings, final int arms, final PointF center) {
// final int tornadoColors[] = buildTornadoColors(13 * arms);
// final int cWidth = canvas.getWidth();
// final int cHeight = canvas.getHeight();
// final int steps = 30;
//
// final int blockW = cWidth / steps;
// final int blockH = cHeight / steps;
//
// final Paint paint = new Paint();
// final int index = 0;
//
// for (int step = 0; step < steps; step++) {
// final int anzRects = steps - step; // steps 30
// final int y = steps - anzR;
// final int startX = 0 + step;
// for (int x = 0; x < step; x++) {
// final Rect r = new Rect((x + 0) * blockW, (y + 0) * blockH, (x + 1) * blockW, (y + 1) * blockH);
// }
// for (int y = 0; y < steps; y++) {
//
// }
// }
// }
//
// private static void drawRectLineLeftRight(final int x, final int y, final int anzahl, final int width, final int hight, final int colorIndex) {
// for (int i = 0; i < anzahl; i++) {
// final Rect r = new Rect(x + i * width, y, x + (i + 1) * width, y + hight);
// }
// }
//
// private static void drawRectLineRightLeft(final int x, final int y, final int anzahl, final int width, final int hight, final int colorIndex) {
// for (int i = 0; i < anzahl; i++) {
// final Paint paint = new Paint();
// final int color = tornadoColors[index % tornadoColors.length];
//
// final Rect r = new Rect(x - (i + 1) * width, y, x - i * width, y + hight);
// }
// }
//
// private static int getColor(final int number) {
// switch (number) {
// default:
// case 1:
// return Settings.getPatternColor1();
// case 2:
// return Settings.getPatternColor2();
// case 3:
// return Settings.getPatternColor3();
// case 4:
// return Settings.getPatternColor4();
// case 5:
// return Settings.getPatternColor1();
// }
// }
//
// private static int[] buildTornadoColors(final int steps) {
// final int tornadoColors[] = new int[steps * 4];
// int index = 0;
// for (int color = 1; color < 5; color++) {
// for (int i = 0; i < steps; i++) {
//
// final int col = ColorHelper.getRadiantColor(getColor(color), getColor(color + 1), i, 0, steps - 1);
// tornadoColors[index] = col;
// index++;
// }
// }
//
// final boolean reverse = Settings.isReverseColors();
// if (reverse) {
// reverse(tornadoColors);
// }
// return tornadoColors;
// }
//
// private static void reverse(final int[] data) {
// int left = 0;
// int right = data.length - 1;
//
// while (left < right) {
// // swap the values at the left and right indices
// final int temp = data[left];
// data[left] = data[right];
// data[right] = temp;
//
// // move the left and right index pointers in toward the center
// left++;
// right--;
// }
// }
//
// }
