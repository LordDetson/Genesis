package by.babanin.genesis.gui.dto;

import org.springframework.stereotype.Component;

import com.almasb.fxgl.dsl.FXGL;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleIntegerProperty;

@Component
public class GeneralSettings {

    private static final int DEFAULT_ROW_COUNT = 40;
    private static final int DEFAULT_COLUMN_COUNT = 40;
    private static final double sin60 = 0.8660254037844386;

    private final SimpleIntegerProperty rowCount;
    private final SimpleIntegerProperty columnCount;
    private final ReadOnlyDoubleWrapper radiusOfHexagon;

    public GeneralSettings() {
        this.rowCount = new SimpleIntegerProperty(DEFAULT_ROW_COUNT);
        this.columnCount = new SimpleIntegerProperty(DEFAULT_COLUMN_COUNT);
        this.radiusOfHexagon = new ReadOnlyDoubleWrapper();
        rowCount.addListener((observable, oldValue, newValue) -> calculateRadiusOfHexagon());
        columnCount.addListener((observable, oldValue, newValue) -> calculateRadiusOfHexagon());
    }

    public int getRowCount() {
        return rowCount.get();
    }

    public SimpleIntegerProperty rowCountProperty() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount.set(rowCount);
    }

    public int getColumnCount() {
        return columnCount.get();
    }

    public SimpleIntegerProperty columnCountProperty() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount.set(columnCount);
    }

    public double getRadiusOfHexagon() {
        return radiusOfHexagon.get();
    }

    public ReadOnlyDoubleProperty radiusOfHexagonProperty() {
        return radiusOfHexagon;
    }

    public void calculateRadiusOfHexagon() {
        double appWidth = FXGL.getAppWidth();
        double appHeight = FXGL.getAppHeight();
        double bigLag = appWidth / getSubColumnCount();
        double minLag = appHeight / getSubRowCount();
        radiusOfHexagon.set(Math.min(minLag * 2, bigLag / sin60));
    }

    private int getSubColumnCount() {
        return getColumnCount() * 2 + 1;
    }

    private int getSubRowCount() {
        return getRowCount() * 3 + 1;
    }
}
