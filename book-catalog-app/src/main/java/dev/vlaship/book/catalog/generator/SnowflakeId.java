package dev.vlaship.book.catalog.generator;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@IdGeneratorType(SnowflakeIdGenerator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface SnowflakeId {
}
