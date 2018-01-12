package ligaaas.teamc.jsf;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * ManagedBean for logout
 * 
 * @author teamC
 *
 */

@Named("logout")
@RequestScoped
public class LogoutManagedBean {

	public void logout() {
		// Invalidate session of a sessionscoped managed bean
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			FacesContext fContext = FacesContext.getCurrentInstance();
			ExternalContext extContext = fContext.getExternalContext();
			extContext.redirect(extContext.getRequestContextPath() + "/index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
