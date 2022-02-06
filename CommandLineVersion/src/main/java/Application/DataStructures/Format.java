package Application.DataStructures;

public abstract class Format {
    private int spacingOuter = 28;
    private int spacingMid = 27;
    private String toStringFormat;

    public Format() {
        updateToStringFormat();
    }

    //"%-28.28s | %-27.27s | %-28.28s"
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
    public int getTotalSpace() {
        return (2 * spacingOuter) + spacingMid + 6;
    }
}
