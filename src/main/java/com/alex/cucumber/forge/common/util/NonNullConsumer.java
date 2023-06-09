package com.alex.cucumber.forge.common.util;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface NonNullConsumer<T>
{
    void accept(@NotNull T t);
}
