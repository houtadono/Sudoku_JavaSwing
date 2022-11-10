package io.houta.model;

public class Setting1 {
    boolean stopwatch;
    boolean errorChecking;
    boolean markDuplicateNumbers;
    boolean markZone;

    public Setting1(boolean stopwatch, boolean errorChecking, boolean markDuplicateNumbers, boolean markZone) {
        this.stopwatch = stopwatch;
        this.errorChecking = errorChecking;
        this.markDuplicateNumbers = markDuplicateNumbers;
        this.markZone = markZone;
    }

    @Override
    public String toString() {
        return "Setting1 [stopwatch=" + stopwatch + ", errorChecking=" + errorChecking + ", markDuplicateNumber="
                + markDuplicateNumbers + ", markZon=" + markZone + "]";
    }

    public boolean isStopwatch() {
        return stopwatch;
    }
    public void setStopwatch(boolean stopwatch) {
        this.stopwatch = stopwatch;
    }
    public boolean isErrorChecking() {
        return errorChecking;
    }
    public void setErrorChecking(boolean errorChecking) {
        this.errorChecking = errorChecking;
    }
    public boolean isMarkDuplicateNumbers() {
        return markDuplicateNumbers;
    }
    public void setMarkDuplicateNumbers(boolean markDuplicateNumbers) {
        this.markDuplicateNumbers = markDuplicateNumbers;
    }
    public boolean isMarkZone() {
        return markZone;
    }
    public void setMarkZone(boolean markZon) {
        this.markZone = markZon;
    }

}
