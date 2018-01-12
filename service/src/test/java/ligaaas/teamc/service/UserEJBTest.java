package ligaaas.teamc.service;

import static ligaaas.teamc.domain.entities.IsEqualToUser.equalToUser;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import es.uvigo.esei.dgss.teamc.ligaaas.service.util.security.RoleCaller;
import es.uvigo.esei.dgss.teamc.ligaaas.service.util.security.TestPrincipal;
import ligaaas.teamc.domain.User;
import ligaaas.teamc.domain.entities.IsEqualToUser;
import ligaaas.teamc.domain.entities.UsersDataset;

@RunWith(Arquillian.class)
@UsingDataSet("users.xml")
@CleanupUsingScript({ "cleanup.sql", "cleanup-autoincrement.sql" })
public class UserEJBTest {

	@Inject
	private UserEJB facade;

	@EJB(beanName = "registered-caller")
	private RoleCaller asUser;

	@EJB(beanName = "anonymous-caller")
	private RoleCaller asAnonymous;

	@Inject
	private TestPrincipal currentUser;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(UserEJB.class, UsersDataset.class, IsEqualToEntity.class, TestPrincipal.class,
						IsEqualToUser.class)
				.addPackage(User.class.getPackage()).addPackage(RoleCaller.class.getPackage())
				.addPackage(TestPrincipal.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource("jboss-web.xml")
				.addAsResource("arquillian.extension.persistence.properties")
				.addAsResource("arquillian.extension.persistence.dbunit.properties")
				.addAsWebInfResource("beans.xml", "beans.xml");
	}

	@Before
	public void before() {
		currentUser.setName(null);
	}

	@Test
	@ShouldMatchDataSet("users.xml")
	public void testFindByUserLogin() {
		final User existentUser = UsersDataset.existentUser();
		final List<User> actualUser = asAnonymous.call(() -> facade.findByLogin(existentUser.getUserLogin()));

		assertThat(actualUser.get(0), is(equalToUser(existentUser)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testFindByUserLoginWithNull() {
		asAnonymous.call(() -> facade.findByLogin(null));
	}
	
	@Test
	@ShouldMatchDataSet("users.xml")
	public void testFindByUserToken() {
		final User existentUser = UsersDataset.existentUser();
		final List<User> actualUser = asAnonymous.call(() -> facade.findByToken(existentUser.getUserToken()));

		assertThat(actualUser.get(0), is(equalToUser(existentUser)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testFindByUserTokenWithNull() {
		asAnonymous.call(() -> facade.findByToken(null));
	}
	
	@Test
	@ShouldMatchDataSet("users.xml")
	public void testFindByUserEmail() {
		final User existentUser = UsersDataset.existentUser();
		final List<User> actualUser = asAnonymous.call(() -> facade.findByEmail(existentUser.getUserEmail()));

		assertThat(actualUser.get(0), is(equalToUser(existentUser)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testFindByUserEmailWithNull() {
		asAnonymous.call(() -> facade.findByEmail(null));
	}

	@Test
	@ShouldMatchDataSet({ "users.xml", "usersCreate.xml" })
	public void testRegisterUser() {
		final User registerUser = UsersDataset.registerUser();
		final User actualUser = asAnonymous.call(() -> facade.registerUser(registerUser));

		assertThat(actualUser, is(equalToUser(registerUser)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "users.xml", "usersCreate.xml" })
	public void testRegisterUserWithNull() {
		asAnonymous.call(() -> facade.registerUser(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "users.xml", "usersCreate.xml" })
	public void testRegisterUserWithExistingLogin() {
		final User registerUser = UsersDataset.registerUserWithExistingLogin();
		asAnonymous.call(() -> facade.registerUser(registerUser));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "users.xml", "usersCreate.xml" })
	public void testRegisterUserWithExistingEmail() {
		final User registerUser = UsersDataset.registerUserWithExistingEmail();
		asAnonymous.call(() -> facade.registerUser(registerUser));
	}

	@Test
	@ShouldMatchDataSet("users.xml")
	public void testGenerateHash() {
		final User registerUser = UsersDataset.existentUser();
		String actualHash = asAnonymous.call(() -> facade.generateHash(registerUser));
		String initialHash = UsersDataset.existentUserHash();
		assertThat(actualHash, is(initialHash));
	}

	@Test
	@ShouldMatchDataSet("users.xml")
	public void testFindUser() {
		final User predefinedUser = UsersDataset.existentUser();
		final User actualUser = asAnonymous.call(() -> facade.find(predefinedUser.getUserId()));
		assertThat(actualUser, is(equalToUser(predefinedUser)));
	}

	@Test
	@ShouldMatchDataSet("usersActivated.xml")
	public void testActivateUser() {
		final User predefinedUser = UsersDataset.existentUser();
		final User actualUser = asAnonymous.call(() -> facade.activateUser(predefinedUser.getUserId()));
		final User activatedUser = UsersDataset.existentUserActivated();
		assertThat(actualUser, is(equalToUser(activatedUser)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testActivateUserInvalidId() {
		asAnonymous.call(() -> facade.activateUser((long) 0));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testActivateUserIllegalAccess() {
		final User predefinedUser = UsersDataset.existentUser();
		this.currentUser.setName(predefinedUser.getUserLogin());
		asUser.call(() -> facade.activateUser(predefinedUser.getUserId()));
	}

	@Test
	@ShouldMatchDataSet("users.xml")
	public void testActivateUserNotFound() {
		final User predefinedUser = UsersDataset.registerUser();
		final User actualUser = asAnonymous.call(() -> facade.activateUser(predefinedUser.getUserId()));
		assertThat(actualUser, is(nullValue()));
	}

	@Test
	@ShouldMatchDataSet("users.xml")
	public void testActivateUserAlreadyActivated() {
		final User activatedUser = UsersDataset.activatedUser();
		final User actualUser = asAnonymous.call(() -> facade.activateUser(activatedUser.getUserId()));
		assertThat(actualUser, is(nullValue()));
	}

	@Test
	@ShouldMatchDataSet("users.xml")
	public void testUpdateUser() {
		this.currentUser.setName(UsersDataset.existentUser().getUserLogin());
		final User predefinedUser = UsersDataset.existentUser();
		final User modifiedUser = UsersDataset.modifiedUser();

		modifiedUser.setUserName(predefinedUser.getUserName());

		final User actualUser = asUser.call(() -> facade.update(modifiedUser));
		assertThat(actualUser, is(equalToUser(predefinedUser)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testUpdateUserIllegalAccess() {
		final User modifiedUser = UsersDataset.modifiedUser();
		asAnonymous.call(() -> facade.update(modifiedUser));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testUpdateUserWithNull() {
		this.currentUser.setName(UsersDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.update(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testUpdateUserWithNonExistent() {
		this.currentUser.setName(UsersDataset.existentUser().getUserLogin());
		final User nonExistantUser = UsersDataset.registerUser();
		asUser.call(() -> facade.update(nonExistantUser));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testUpdateUserWithAnotherUser() {
		this.currentUser.setName(UsersDataset.existentUser().getUserLogin());
		final User predefinedUser = UsersDataset.activatedUser();
		asUser.run(() -> facade.update(predefinedUser));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testUpdateUserWithAnotherPassword() {
		this.currentUser.setName(UsersDataset.existentUser().getUserLogin());
		final User existentUser = UsersDataset.existentUser();
		existentUser.setUserPassword("abcdefg123456..");
		asUser.call(() -> facade.update(existentUser));
	}

	@Test
	@ShouldMatchDataSet("users.xml")
	public void testUpdatePasswordUser() {
		this.currentUser.setName(UsersDataset.existentUser().getUserLogin());
		final User predefinedUser = UsersDataset.existentUser();
		asUser.call(() -> facade.updatePassword(predefinedUser, "abcd1234.."));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testUpdatePasswordUserWithNullUser() {
		this.currentUser.setName(UsersDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.updatePassword(null, "abcd1234.."));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testUpdatePasswordUserWithNullPassword() {
		this.currentUser.setName(UsersDataset.existentUser().getUserLogin());
		final User predefinedUser = UsersDataset.existentUser();
		asUser.call(() -> facade.updatePassword(predefinedUser, null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testUpdateUserPasswordWithNonExistent() {
		this.currentUser.setName(UsersDataset.existentUser().getUserLogin());
		final User nonExistantUser = UsersDataset.registerUser();
		asUser.call(() -> facade.updatePassword(nonExistantUser, "abcd1234.."));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testUpdateUserPasswordWithAnotherUser() {
		this.currentUser.setName(UsersDataset.existentUser().getUserLogin());
		final User predefinedUser = UsersDataset.activatedUser();
		asUser.call(() -> facade.updatePassword(predefinedUser, "abcd1234.."));
	}

	@Test
	@ShouldMatchDataSet({ "usersDelete.xml" })
	public void testDeleteUser() {
		this.currentUser.setName(UsersDataset.existentUserActivated().getUserLogin());
		final User predefinedUser = UsersDataset.existentUserActivated();
		asUser.run(() -> facade.delete(predefinedUser.getUserId()));
	}
	
	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testDeleteUserIllegalAccess() {
		this.currentUser.setName(UsersDataset.existentUserActivated().getUserLogin());
		final User predefinedUser = UsersDataset.existentUserActivated();
		asAnonymous.run(() -> facade.delete(predefinedUser.getUserId()));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testDeleteUserWithNonExistent() {
		this.currentUser.setName(UsersDataset.existentUserActivated().getUserLogin());
		final User nonExistentUser = UsersDataset.registerUser();
		asUser.run(() -> facade.delete(nonExistentUser.getUserId()));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("users.xml")
	public void testDeleteUserWithAnotherUser() {
		this.currentUser.setName(UsersDataset.existentUser().getUserLogin());
		final User activatedUser = UsersDataset.activatedUser();
		asUser.run(() -> facade.delete(activatedUser.getUserId()));
	}

}
