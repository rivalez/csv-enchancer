package com.tabor.validator;

public class ArgumentsValidator<T> implements Validator<T> {
    @Override
    public boolean test(T o) {
        return false;
    }
}
