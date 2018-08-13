package com.tabor.validator;

import java.util.function.Predicate;


/**
 * This class was prepared for validate input
 * @param <T>
 */
@FunctionalInterface
public interface Validator<T> extends Predicate<T> {

}
