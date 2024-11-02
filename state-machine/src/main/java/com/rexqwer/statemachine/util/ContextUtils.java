package com.rexqwer.statemachine.util;

import org.springframework.statemachine.StateContext;

import java.util.Map;
import java.util.Objects;

public class ContextUtils {

    public static void put(StateContext<?, ?> context, Object key, Object value) {
        context.getExtendedState().getVariables().put(key, value);
    }

    public static <T> T getFor(Map<Object, Object> variables, Object key, Class<T> clazz) {
        Object o = variables.get(key);
        if (Objects.isNull(o)) throw new IllegalStateException("Value for variable %s not found".formatted(key.toString()));
        return clazz.cast(o);
    }
}
