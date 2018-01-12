package ligaaas.teamc.domain;

public enum CompetitionType {
	SIMPLE {
		@Override
		/**
		 * Returns the pretty value of SIMPLE
		 * 
		 * @return The pretty value of SIMPLE
		 */
		public String toString() {
			return "Simple";
		}
	},
	DOUBLE {
		@Override
		/**
		 * Returns the pretty value of DOUBLE
		 * 
		 * @return The pretty value of DOUBLE
		 */
		public String toString() {
			return "Double";
		}
	}
}
