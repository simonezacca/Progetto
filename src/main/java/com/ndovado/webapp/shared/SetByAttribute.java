package com.ndovado.webapp.shared;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SetByAttribute {
    AttributeType type();
}
