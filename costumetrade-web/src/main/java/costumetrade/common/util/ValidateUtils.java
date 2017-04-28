/**
 * Copyright (C) 2014-2015, Utry and/or its affiliates. All rights reserved. Utry
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.util;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * 验证工具
 * @author zhouyq
 * @Date 2017年1月22日
 */
public class ValidateUtils {

    private static final Validator validator =
            Validation.buildDefaultValidatorFactory().getValidator();


    private ValidateUtils() {

    }

    public static <T> boolean validate(T object, Class<?>... groups) {

         Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
         return handleValidateResult(constraintViolations);

    }

	/**
	 *  @param constraintViolations
	 *  @return
	 */
	private static <T> boolean handleValidateResult(Set<ConstraintViolation<T>> constraintViolations) {
		if(constraintViolations.isEmpty()){
        	return true;
         }
         String message =  collectConstraintViolationMessage(constraintViolations);
         throw new IllegalArgumentException(message);
	}
    
    /**
	 *  @param constraintViolations
	 *  @return
	 */
	private static <T> String collectConstraintViolationMessage(Set<ConstraintViolation<T>> constraintViolations) {
		StringBuilder stringBuilder = new StringBuilder();
        Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<T> constraintViolation = (ConstraintViolation<T>) iterator.next();
            stringBuilder.append(constraintViolation.getMessage());
            if (iterator.hasNext()) {
                stringBuilder.append(",");
            }

        }
		return stringBuilder.toString();
	}


    public static <T> boolean validateProperty(T object, String propertyName,
            Class<?>... groups) {

    	Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(object, propertyName, groups);
    	return handleValidateResult(constraintViolations);
    }

    public static <T> boolean validateValue(Class<T> beanType,
            String propertyName, Object value, Class<?>... groups) {

    	 Set<ConstraintViolation<T>> constraintViolations =  validator.validateValue(beanType, propertyName, value, groups);
         return handleValidateResult(constraintViolations);

    }

}
