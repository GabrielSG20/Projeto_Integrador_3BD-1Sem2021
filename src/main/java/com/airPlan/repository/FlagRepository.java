package airPlan.repository;

import airPlan.model.Flag;

public interface FlagRepository {
	Flag save(Flag flag);
	Flag editTag(Flag flag);
}
