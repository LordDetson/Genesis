package by.babanin.genesis.core;

import java.util.function.BiConsumer;
import java.util.function.Function;

public enum WorldField implements Field<World> {
    ROW_COUNT("rowCount", Integer.class, World::getRowCount, null),
    COLUMN_COUNT("columnCount", Integer.class, World::getColumnCount, null),
    ;

    private final String id;
    private final Class<?> type;
    private final Function<World, ?> getter;
    private final BiConsumer<World, Object> setter;


    WorldField(String id, Class<?> type, Function<World, ?> getter, BiConsumer<World, Object> setter) {
        this.id = id;
        this.type = type;
        this.getter = getter;
        this.setter = setter;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public Object getValue(World component) {
        return getter.apply(component);
    }

    @Override
    public void setValue(World component, Object value) {
        setter.accept(component, value);
    }
}
