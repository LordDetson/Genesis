package by.babanin.genesis.gui.formatter;

import java.util.function.UnaryOperator;

import org.apache.commons.lang3.StringUtils;

import javafx.scene.control.TextFormatter;

public class FloatFormatter extends TextFormatter<String> {

    public FloatFormatter() {
        super(new FloatFilter());
    }

    private static class FloatFilter implements UnaryOperator<Change> {

        private static final String IGNORE_CHARACTERS = "fFdD";

        @Override
        public Change apply(Change change) {
            String text = change.getControlNewText();
            if(StringUtils.isEmpty(text)) {
                return change;
            }
            String lastChar = text.substring(text.length() - 1);
            if(IGNORE_CHARACTERS.contains(lastChar)) {
                return null;
            }
            try {
                Float.parseFloat(text);
                return change;
            }
            catch(Throwable e) {
                return null;
            }
        }
    }
}
