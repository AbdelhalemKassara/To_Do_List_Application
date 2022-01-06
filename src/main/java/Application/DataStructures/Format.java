package Application.DataStructures;

public abstract class Format {
    private int spacingOuter = 28;
    private int spacingMid = 27;
    private String toStringFormat;

    public Format() {
        updateToStringFormat();
    }

    //"%-26.26s | %-56.56s | %-26.26s\n"
    private void updateToStringFormat() {
        toStringFormat = "%-" + spacingOuter + "." + spacingOuter + "s | %-" + spacingMid + "." + spacingMid + "s | %-" + spacingOuter + "."+ spacingOuter +"s";
    }
    public void changeToStringWidth(int spacingMid, int spacingOuter) {
        this.spacingOuter = spacingOuter;
        this.spacingMid = spacingMid;
        updateToStringFormat();
    }
    public String getFormat() {
        return toStringFormat;
    }
    public int getSpacingMid() {
        return spacingMid;
    }
    public int getSpacingOuter() {
        return spacingOuter;
    }
}
