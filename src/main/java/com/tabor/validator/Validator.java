package com.tabor.validator;

import java.util.function.Predicate;

@FunctionalInterface
public interface Validator<T> extends Predicate<T> {

}
