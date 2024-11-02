package com.rexqwer.statemachine.executor;

public interface EventExecutor<E, K> {

    <V> V executeEvent(K key, E event, Object valueKey, Class<V> clazz);

    void executeEvent(K key, E event);
}
