package repositorios;

import java.util.*;
import java.util.function.Function;
import java.util.UUID;

public class InMemoryRepository<T> {

    private final Map<UUID, T> data = new HashMap<>();
    private final Function<T, UUID> idGetter;
    private final java.util.function.BiConsumer<T, UUID> idSetter;

    public InMemoryRepository(Function<T, UUID> idGetter,
                              java.util.function.BiConsumer<T, UUID> idSetter) {
        this.idGetter = idGetter;
        this.idSetter = idSetter;
    }

    public T save(T entity) {
        UUID id = idGetter.apply(entity);
        if (id == null) {
            id = UUID.randomUUID();
            idSetter.accept(entity, id);
        }
        data.put(id, entity);
        return entity;
    }

    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    public Optional<T> findById(UUID id) {
        return Optional.ofNullable(data.get(id));
    }

    public Optional<T> update(UUID id, T updated) {
        if (!data.containsKey(id)) return Optional.empty();
        idSetter.accept(updated, id);
        data.put(id, updated);
        return Optional.of(updated);
    }

    public Optional<T> delete(UUID id) {
        return Optional.ofNullable(data.remove(id));
    }

    public List<T> findByField(String fieldName, Object value) {
        List<T> results = new ArrayList<>();
        try {
            for (T entity : data.values()) {
                var getter = entity.getClass().getMethod("get" + capitalize(fieldName));
                Object fieldValue = getter.invoke(entity);
                if (fieldValue != null && fieldValue.equals(value)) {
                    results.add(entity);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
