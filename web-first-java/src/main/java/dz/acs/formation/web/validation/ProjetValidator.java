package dz.acs.formation.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
/**
 * ProjetValidator
 * @author ataibi
 *
 */
import org.springframework.validation.Validator;

import dz.acs.formation.web.model.Projet;
/**
 * ProjetValidator
 * @author ataibi
 *
 */
@Component
public class ProjetValidator implements Validator {
	
    @Override
    public boolean supports(final Class<?> calzz) {
        return Projet.class.isAssignableFrom(calzz);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repoName", "required.repo");        
    }
}
//	 implements ConstraintValidator<ProjetValid, String> 
//	@Override
//	public boolean isValid(String value, ConstraintValidatorContext context) {
//		// TODO Auto-generated method stub
//		return false;
//	}