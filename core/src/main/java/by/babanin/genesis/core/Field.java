package by.babanin.genesis.core;

public interface Field<T extends ReportableComponent> {

    String getId();

    Class<?> getType();

    Object getValue(T component);

    void setValue(T component, Object value);
}
