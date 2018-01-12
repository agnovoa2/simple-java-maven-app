package ligaaas.teamc.domain;

/**
 * An Enum that represents the values of the field SportType
 *
 * @author teamC
 *
 */
public enum SportType {
	FOOTBALL11 {
		@Override
		/**
		 * Returns the pretty value of FOOTBALL11
		 * @return The pretty value of FOOTBALL11
		 */
		public String toString() {
			return "Football 11";
		}
	},

	FOOTBALL7{
		@Override
		/**
		 * Returns the pretty value of FOOTBALL7
		 * @return The pretty value of FOOTBALL7
		 */
		public String toString() {
			return "Football 7";
		}
	}, BASKETBALL{
		@Override
		/**
		 * Returns the pretty value of BASKETBALL
		 * @return The pretty value of BASKETBALL
		 */
		public String toString() {
			return "Basketball";
		}
	}, VIDEOGAMES{
		@Override
		/**
		 * Returns the pretty value of VIDEOGAMES
		 * @return The pretty value of VIDEOGAMES
		 */
		public String toString() {
			return "Videogames";
		}
	}
}
