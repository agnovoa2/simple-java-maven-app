package ligaaas.teamc.converter;

import ligaaas.teamc.DTO.HeadQuarterDTO;
import ligaaas.teamc.domain.HeadQuarter;

public class HeadQuarterConverter {
	public static HeadQuarterDTO toHeadQuarterDTO(HeadQuarter headQuarter) {
		HeadQuarterDTO headQuarterDTO = new HeadQuarterDTO();
		
		headQuarterDTO.setHeadquarterAddress(headQuarter.getHeadquarterAddress());
		headQuarterDTO.setHeadquarterDescription(headQuarter.getHeadquarterDescription());
		headQuarterDTO.setHeadquarterLocality(headQuarter.getHeadquarterLocality());
		headQuarterDTO.setHeadquarterName(headQuarter.getHeadquarterName());
		headQuarterDTO.setHeadquarterProvince(headQuarter.getHeadquarterProvince());
		
		return headQuarterDTO;
	}
}
