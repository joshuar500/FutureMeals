package com.joshrincon.springyummly.validation;

@java.lang.annotation.Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.PARAMETER})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
@javax.validation.Constraint(validatedBy = com.joshrincon.springyummly.validation.ValidEmailImpl.class)
public @interface ValidEmail {
    String message() default "This does not appear to be a valid email address.";
    Class<?>[] groups() default {};
    Class<? extends javax.validation.Payload>[] payload() default {};
    int min() default 6;
    int max() default 2147483647;
    @java.lang.annotation.Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.PARAMETER})
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @java.lang.annotation.Documented
    static @interface List {
        javax.validation.constraints.Size[] value();
    }
}