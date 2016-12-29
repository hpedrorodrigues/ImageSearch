package com.hpedrorodrigues.imagesearch.data.validator;

abstract class BaseValidator<T> {

    public abstract void validate(T entity);
}