package airPlan.repository;

import airPlan.model.Manual;

public interface ManualRepository {
	Manual save(Manual manual);
	void findIdManual(Manual manual);
}
