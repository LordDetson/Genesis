package by.babanin.genesis.core;

public interface ReportableComponent {

    default Object getFieldValue(Field<ReportableComponent> field) {
        return field.getValue(this);
    }
}
