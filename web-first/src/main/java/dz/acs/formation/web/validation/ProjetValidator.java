package dz.acs.formation.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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