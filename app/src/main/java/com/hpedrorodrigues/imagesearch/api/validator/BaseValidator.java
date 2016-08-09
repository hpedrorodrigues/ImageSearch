package com.hpedrorodrigues.imagesearch.api.validator;

abstract class BaseValidator<T> {

    public abstract void validate(T entity);
}