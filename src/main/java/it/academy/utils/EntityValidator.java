package it.academy.utils;

import it.academy.models.Student;
import lombok.experimental.UtilityClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


@UtilityClass
public class EntityValidator {


    public static String validateStudent(Student forSave) {
        Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        Set<ConstraintViolation<Student>> violations = validator.validate(forSave);
        if (!violations.isEmpty()){
            StringBuilder out = new StringBuilder();
            for (ConstraintViolation<Student> violation : violations){
                out.append(violation.getMessage());
            }
            return out.toString();
        }
        return null;
    }
}
