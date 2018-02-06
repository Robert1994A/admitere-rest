package ro.inf.ucv.admitere.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ro.inf.ucv.admitere.repository.UserRepository;

public class UniqueCnpValidator implements ConstraintValidator<UniqueCnp, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UniqueCnp constraintAnnotation) {
	}

	@Override
	public boolean isValid(String cnp, ConstraintValidatorContext context) {
		if (userRepository == null) {
			return true;
		}
		return userRepository.findByUsername(cnp) == null;
	}

}