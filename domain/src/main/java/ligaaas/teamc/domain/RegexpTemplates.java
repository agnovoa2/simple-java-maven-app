package ligaaas.teamc.domain;

public final class RegexpTemplates {
	public static final String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";
	public static final String PHONE = "\\+[0-9]{11}";
}
