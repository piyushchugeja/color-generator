import java.awt.Color;
public class ColorHelper {
    public static Color contrastColorByShift(Color color, int shift) {
        float alpha = (color == null) ? 1.0F : color.getAlpha() / 256.0F;
        if (color == null) {
            color = Color.BLACK;
        }

        if (shift < 1) {
            shift = 128;
        }

        int r1 = color.getRed();
        int g1 = color.getGreen();
        int b1 = color.getBlue();
        float r2 = (float) ((r1 + shift) % 256) / 256.0F;
        float g2 = (float) ((g1 + shift) % 256) / 256.0F;
        float b2 = (float) ((b1 + shift) % 256) / 256.0F;

        Color reval = makeColor(r2, g2, b2, alpha);

        return reval;
    }
    public static Color makeColor(int r, int g, int b) {
        r = Math.max(r, 0);
        r = Math.min(r, 255);
        g = Math.max(g, 0);
        g = Math.min(g, 255);
        b = Math.max(b, 0);
        b = Math.min(b, 255);
        return new Color(r, g, b);
    }
    public static Color makeColor(float r, float g, float b, float a) {
        r = Math.max(r, 0.0F);
        r = Math.min(r, 1.0F);
        g = Math.max(g, 0.0F);
        g = Math.min(g, 1.0F);
        b = Math.max(b, 0.0F);
        b = Math.min(b, 1.0F);
        a = Math.max(a, 0.0F);
        a = Math.min(a, 1.0F);
        return new Color(r, g, b, a);
    }
}
