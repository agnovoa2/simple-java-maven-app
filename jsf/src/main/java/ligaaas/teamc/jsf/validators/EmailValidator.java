package ligaaas.teamc.jsf.validators;

import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

import ligaaas.teamc.domain.RegexpTemplates;

/**
 * Custom JSF Validator for Email input
 * 
 * @author teamC
 */
@FacesValidator("custom.emailValidator")
public class EmailValidator implements Validator, ClientValidator {

	private Pattern pattern;

	/**
	 * Initialize the pattern with the EMAIL regex.
	 */
	public EmailValidator() {
		pattern = Pattern.compile(RegexpTemplates.EMAIL);
	}

	/**
	 * Validate an email. If any violations are found, a {@link ValidatorException}
	 * will be thrown containing the {@link javax.faces.application.FacesMessage}
	 * describing the failure.
	 *
	 * @param component
	 *            UIComponent we are checking for correctness
	 * @param value
	 *            the value to validate
	 * @throws ValidatorException
	 *             if validation fails
	 * @throws NullPointerException
	 *             if <code>context</code> or <code>component</code> is
	 *             <code>null</code>
	 */
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}

		if (!pattern.matcher(value.toString()).matches()) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error",
					value + " is not a valid email;"));
		}
	}

	/**
	 * Not implemented method, always return null.
	 */
	public Map<String, Object> getMetadata() {
		return null;
	}

	/**
	 * Return the Id of the validator.
	 * 
	 */
	public String getValidatorId() {
		return "custom.emailValidator";
	}

}
