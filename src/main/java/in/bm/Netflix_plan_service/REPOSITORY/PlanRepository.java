package in.bm.Netflix_plan_service.REPOSITORY;

import in.bm.Netflix_plan_service.ENTITY.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
