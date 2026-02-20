package in.bm.Netflix_plan_service.REPOSITORY;

import in.bm.Netflix_plan_service.ENTITY.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription,Long> {
}
