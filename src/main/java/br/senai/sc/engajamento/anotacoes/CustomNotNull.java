package br.senai.sc.engajamento.anotacoes;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@NotNull(message = "")
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomNotNull {
    String message() default "{nomeDoCampo} não informado(a)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}