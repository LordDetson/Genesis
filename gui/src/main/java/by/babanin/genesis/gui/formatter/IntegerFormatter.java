package by.babanin.genesis.gui.formatter;

import java.util.function.UnaryOperator;

import org.apache.commons.lang3.StringUtils;

import by.babanin.genesis.gui.util.AppUtils;
import javafx.scene.control.TextFormatter;

public class IntegerFormatter extends TextFormatter<String> {

    public IntegerFormatter() {
        super(new IntegerFilter());
    }

    private static class IntegerFilter implements UnaryOperator<Change> {
        @Override
        public Change apply(Change change) {
            String text = change.getControlNewText();
            return StringUtils.isEmpty(text) || AppUtils.isInteger(text) ? change : null;
        }
    }
}
