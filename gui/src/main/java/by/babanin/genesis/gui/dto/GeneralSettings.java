package by.babanin.genesis.gui.dto;

import org.springframework.stereotype.Component;

import by.babanin.genesis.gui.NoiseAlgorithmItem;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

@Component
public class GeneralSettings {

    private static final int DEFAULT_ROW_COUNT = 40;
    private static final int DEFAULT_COLUMN_COUNT = 40;

    private final SimpleIntegerProperty rowCount;
    private final SimpleIntegerProperty columnCount;

    public GeneralSettings() {
        this.rowCount = new SimpleIntegerProperty(DEFAULT_ROW_COUNT);
        this.columnCount = new SimpleIntegerProperty(DEFAULT_COLUMN_COUNT);
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
}
