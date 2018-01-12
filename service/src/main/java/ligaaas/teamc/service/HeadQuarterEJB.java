package ligaaas.teamc.service;

import static java.util.Objects.requireNonNull;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ligaaas.teamc.domain.HeadQuarter;
import ligaaas.teamc.domain.User;

/**
 * EJB for HeadQuarter
 *
 * @author teamC
 *
 */

@Stateless
@RolesAllowed("registered")
public class HeadQuarterEJB {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private UserEJB userEJB;

	@Inject
	private Principal currentUser;

	/**
	 * Creates a new {@link HeadQuarter}. If the {@link HeadQuarter} already has
	 * other entities related to it, they will be created too.
	 * 
	 * @param headQuarter
	 *            A new headQuarter to be stored.
	 * @return The persistent version of the {@link HeadQuarter} created.
	 * @throws NullPointerException
	 *             if {@link HeadQuarter} is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if {@link HeadQuarter} already exists.
	 */
	public HeadQuarter create(HeadQuarter headQuarter) {
		requireNonNull(headQuarter, "HeadQuarter can't be null");
		HeadQuarter existentHeadQuarter = em.find(HeadQuarter.class, headQuarter.getHeadQuarterId());

		if (existentHeadQuarter != null) {
			throw new IllegalArgumentException("Headquarter already exists");
		}

		User headQuarterUser = userEJB.findByLogin(currentUser.getName()).get(0);
		headQuarter.setHeadQuarterManagedByUser(headQuarterUser);
		headQuarterUser.getUserHeadQuarters().add(headQuarter);

		HeadQuarter newHeadQuarter = em.merge(headQuarter);
		em.merge(headQuarterUser);
		return newHeadQuarter;
	}

	/**
	 * Updates a headQuarter. If the {@link HeadQuarter} is not stored, it will be
	 * persisted.
	 * 
	 * @param headQuarter
	 *            A modified {@link HeadQuarter} to be persisted.
	 * @return The persistent version of the {@link HeadQuarter} modified.
	 * @throws NullPointerException
	 *             if the {@link HeadQuarter} is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if {@link HeadQuarter} doesn't exist.
	 * @throws EJBAccessException
	 *             if headQuarter's user isn't the current principal.
	 */

	public HeadQuarter update(HeadQuarter headQuarter) {
		requireNonNull(headQuarter, "Headquarter can't be null");

		HeadQuarter existentHeadQuarter = em.find(HeadQuarter.class, headQuarter.getHeadQuarterId());
		if (existentHeadQuarter == null) {
			throw new IllegalArgumentException("Headquarter does not exists");
		}

		String cur = currentUser.getName();

		User headQuarterUser = userEJB.findByLogin(cur).get(0);
		if (headQuarter.getHeadQuarterManagedByUser().getUserLogin().equals(headQuarterUser.getUserLogin())) {
			return em.merge(headQuarter);
		} else {
			throw new EJBAccessException("Headquarter's user is not the current principal");
		}

	}

	/**
	 * Deletes a {@link HeadQuarter}.
	 * 
	 * @param headquarterID.
	 *            The id of the {@link HeadQuarter} to be deleted.
	 * @throws NullPointerException
	 *             if the {@link HeadQuarter} does not exist.
	 * @throws EJBAccessException
	 *             if HeadQuarter's user isn't the current principal.
	 * 
	 */
	public void delete(long headquarterID) {
		HeadQuarter toDelete = em.find(HeadQuarter.class, headquarterID);
		requireNonNull(toDelete, "HeadQuarter can't be null");

		String cur = currentUser.getName();

		User headquarterUser = userEJB.findByLogin(cur).get(0);
		if (toDelete.getHeadQuarterManagedByUser().getUserLogin().equals(headquarterUser.getUserLogin())) {
			toDelete.setHeadQuarterDeleted(true);
			em.merge(toDelete);
		} else {
			throw new EJBAccessException("Headquarter's user is not the current principal");
		}
	}

	/**
	 * Returns a {@link List} of {@link HeadQuarter} with the specified user.
	 * 
	 * @param user.
	 *            The user of the {@link HeadQuarter} to be searched.
	 * @return A {@link List} of {@link HeadQuarter} with the specified user.
	 * @throws NullPointerException
	 *             if the headQuarter user is null
	 */
	public List<HeadQuarter> findByUser(User user) {
		requireNonNull(user, "HeadQuarter user can't be null");
		User headQuarterUser = userEJB.findByLogin(currentUser.getName()).get(0);
		if (!headQuarterUser.getUserLogin().equals(user.getUserLogin())) {
			throw new EJBAccessException("HeadQuarter's user is not the current principal");
		}
		return em.createQuery("SELECT h FROM HeadQuarter h WHERE h.headQuarterManagedByUser = :user and h.headquarterDeleted = false", HeadQuarter.class)
				.setParameter("user", headQuarterUser).getResultList();
	}

}
