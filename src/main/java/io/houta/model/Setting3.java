package io.houta.model;

public class Setting3 {
    String colorBackgroundDefaultCell;
    String colorBackgroundClickCell;
    String colorBackgroundSelectZone;
    String colorBackgroundSameValue;

    String colorForegroundDefaultCell;
    String colorForegroundAddCell;
    String colorForegroundErrorCell;
    String colorForegroundSolutionCell;

    

    public Setting3(String colorBackgroundDefaultCell, String colorBackgroundClickCell,
            String colorBackgroundSelectZone, String colorBackgroundSameValue, String colorForegroundDefaultCell,
            String colorForegroundAddCell, String colorForegroundErrorCell, String colorForegroundSolutionCell) {
        this.colorBackgroundDefaultCell = colorBackgroundDefaultCell;
        this.colorBackgroundClickCell = colorBackgroundClickCell;
        this.colorBackgroundSelectZone = colorBackgroundSelectZone;
        this.colorBackgroundSameValue = colorBackgroundSameValue;
        this.colorForegroundDefaultCell = colorForegroundDefaultCell;
        this.colorForegroundAddCell = colorForegroundAddCell;
        this.colorForegroundErrorCell = colorForegroundErrorCell;
        this.colorForegroundSolutionCell = colorForegroundSolutionCell;
    }
    
    @Override
    public String toString() {
        return "Setting3 [colorBackgroundDefaultCell=" + colorBackgroundDefaultCell + ", colorBackgroundClickCell="
                + colorBackgroundClickCell + ", colorBackgroundSelectZone=" + colorBackgroundSelectZone
                + ", colorBackgroundSameValue=" + colorBackgroundSameValue + ", colorForegroundDefaultCell="
                + colorForegroundDefaultCell + ", colorForegroundAddCell=" + colorForegroundAddCell
                + ", colorForegroundErrorCell=" + colorForegroundErrorCell + ", colorForegroundSolutionCell="
                + colorForegroundSolutionCell + "]";
    }

    public String getColorBackgroundDefaultCell() {
        return colorBackgroundDefaultCell;
    }
    public void setColorBackgroundDefaultCell(String colorBackgroundDefaultCell) {
        this.colorBackgroundDefaultCell = colorBackgroundDefaultCell;
    }
    public String getColorBackgroundClickCell() {
        return colorBackgroundClickCell;
    }
    public void setColorBackgroundClickCell(String colorBackgroundClickCell) {
        this.colorBackgroundClickCell = colorBackgroundClickCell;
    }
    public String getColorBackgroundSelectZone() {
        return colorBackgroundSelectZone;
    }
    public void setColorBackgroundSelectZone(String colorBackgroundSelectZone) {
        this.colorBackgroundSelectZone = colorBackgroundSelectZone;
    }
    public String getColorBackgroundSameValue() {
        return colorBackgroundSameValue;
    }
    public void setColorBackgroundSameValue(String colorBackgroundSameValue) {
        this.colorBackgroundSameValue = colorBackgroundSameValue;
    }
    public String getColorForegroundDefaultCell() {
        return colorForegroundDefaultCell;
    }
    public void setColorForegroundDefaultCell(String colorForegroundDefaultCell) {
        this.colorForegroundDefaultCell = colorForegroundDefaultCell;
    }
    public String getColorForegroundAddCell() {
        return colorForegroundAddCell;
    }
    public void setColorForegroundAddCell(String colorForegroundAddCell) {
        this.colorForegroundAddCell = colorForegroundAddCell;
    }
    public String getColorForegroundErrorCell() {
        return colorForegroundErrorCell;
    }
    public void setColorForegroundErrorCell(String colorForegroundErrorCell) {
        this.colorForegroundErrorCell = colorForegroundErrorCell;
    }
    public String getColorForegroundSolutionCell() {
        return colorForegroundSolutionCell;
    }
    public void setColorForegroundSolutionCell(String colorForegroundSolutionCell) {
        this.colorForegroundSolutionCell = colorForegroundSolutionCell;
    }
    
}
