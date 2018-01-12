package ligaaas.teamc.jsf.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * A MD5 converter.
 * 
 * @author teamC
 *
 */
@FacesConverter("Md5Converter")
public class Md5Converter implements Converter {

	/**
	 * <p>
	 * Convert the specified string value, which is associated with the specified
	 * {@link UIComponent}, into a model data object that is appropriate for being
	 * stored during the <em>Apply Request Values</em> phase of the request
	 * processing lifecycle.
	 * </p>
	 *
	 * @param arg0
	 *            {@link FacesContext} for the request being processed
	 * @param arg1
	 *            {@link UIComponent} with which this model object value is
	 *            associated
	 * @param arg2
	 *            String value to be converted (may be <code>null</code>)
	 * @return <code>null</code> if the value to convert is <code>null</code>,
	 *         otherwise the result of the conversion
	 * @throws NullPointerException
	 *             if <code>context</code> or <code>component</code> is
	 *             <code>null</code>
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return MD5(arg2);
	}

	/**
	 * <p>
	 * Convert the specified model object value, which is associated with the
	 * specified {@link UIComponent}, into a String that is suitable for being
	 * included in the response generated during the <em>Render Response</em> phase
	 * of the request processing lifeycle.
	 * </p>
	 *
	 * @param arg0
	 *            {@link FacesContext} for the request being processed
	 * @param arg1
	 *            {@link UIComponent} with which this model object value is
	 *            associated
	 * @param arg2
	 *            Model object value to be converted (may be <code>null</code>)
	 * @return a zero-length String if value is <code>null</code>, otherwise the
	 *         result of the conversion
	 * @throws NullPointerException
	 *             if <code>context</code> or <code>component</code> is
	 *             <code>null</code>
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return String.valueOf(arg2);
	}

	/**
	 * Return the MD5 of a String.
	 * 
	 * @param md5
	 *            String to transform.
	 * @return Md5 of the String.
	 */
	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
}