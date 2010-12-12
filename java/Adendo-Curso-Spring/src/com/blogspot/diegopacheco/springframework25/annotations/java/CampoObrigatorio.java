package com.blogspot.diegopacheco.springframework25.annotations.java;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anota��o que infica que um campo de uma classe nada pode ser nulo ou vazio. <br>
 * Logo voc� � obrigado a informar um valor ao campo que for anotado com esta anota��o.
 * 
 * @author Diego Pacheco
 * @version 1.0
 * @since 26/09/2009
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CampoObrigatorio {
	
}
