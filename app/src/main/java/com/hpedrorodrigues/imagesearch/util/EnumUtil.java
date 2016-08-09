package com.hpedrorodrigues.imagesearch.util;

import java.util.Arrays;
import java.util.List;

public class EnumUtil {

    public static <T extends Enum<?>> List<T> valuesAsList(Class<T> enumType) {
        return Arrays.asList(enumType.getEnumConstants());
    }
}