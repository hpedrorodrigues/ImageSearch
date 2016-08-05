package com.hpedrorodrigues.researcher.api.validator;

abstract class BaseValidator<T> {

    public abstract void validate(T entity);
}