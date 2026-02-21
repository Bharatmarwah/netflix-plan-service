package in.bm.Netflix_plan_service.SERVICE;

import in.bm.Netflix_plan_service.ENTITY.Plan;
import in.bm.Netflix_plan_service.REPOSITORY.PlanRepository;
import in.bm.Netflix_plan_service.REPOSITORY.UserSubscriptionRepository;
import in.bm.Netflix_plan_service.RequestDTO.PlanRequestDTO;
import in.bm.Netflix_plan_service.ResponseDTO.PlanResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;

    @Transactional
    @CacheEvict(cacheNames = "plans", allEntries = true)
    public PlanResponseDTO createPlan(PlanRequestDTO requestDTO){

        Plan plan =  new Plan();
        plan.setPlanName(requestDTO.getPlanName());
        plan.setPrice(requestDTO.getPrice());
        plan.setResolution(requestDTO.getResolution());
        plan.setMaxDevice(requestDTO.getMaxDevice());
        plan.setSupportiveDevices(requestDTO.getSupportiveDevices());

        var savedPlan = planRepository.save(plan);

        return PlanResponseDTO.builder()
                .id(savedPlan.getPlanId())
                .planName(savedPlan.getPlanName())
                .price(savedPlan.getPrice())
                .resolution(savedPlan.getResolution())
                .maxDevice(savedPlan.getMaxDevice())
                .supportiveDevices(savedPlan.getSupportiveDevices())
                .build();
    }


    @Transactional
    @Cacheable(cacheNames = "plan",key = "#planId")
    public PlanResponseDTO getPlanById(Long planId) {
        var plan = planRepository
                .findById(planId)
                .orElseThrow(()->
                        new RuntimeException("Plan not found with id: "+planId));

        return PlanResponseDTO.builder()
                .id(plan.getPlanId())
                .planName(plan.getPlanName())
                .price(plan.getPrice())
                .resolution(plan.getResolution())
                .maxDevice(plan.getMaxDevice())
                .supportiveDevices(plan.getSupportiveDevices())
                .build();
    }

    @Transactional
    @Cacheable(cacheNames = "plans",key = "'all'")
    public List<PlanResponseDTO> getAllPlans() {
        List<Plan> plans = planRepository.findAll();

        return plans.stream().map(plan-> PlanResponseDTO
                .builder()
                .id(plan.getPlanId())
                .planName(plan.getPlanName())
                .price(plan.getPrice())
                .resolution(plan.getResolution())
                .maxDevice(plan.getMaxDevice())
                .supportiveDevices(plan.getSupportiveDevices())
                .build()).toList();
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "plan", allEntries = true),
            @CacheEvict(cacheNames = "plans", allEntries = true)
    })
    public PlanResponseDTO updatePlan(Long planId, PlanRequestDTO requestDTO) {
        var plan = planRepository.findById(planId).orElseThrow(()-> new RuntimeException("Plan not found with id: "+planId));

        plan.setPlanName(requestDTO.getPlanName());
        plan.setPrice(requestDTO.getPrice());
        plan.setResolution(requestDTO.getResolution());
        plan.setMaxDevice(requestDTO.getMaxDevice());
        plan.setSupportiveDevices(requestDTO.getSupportiveDevices());

        var updatedPlan = planRepository.save(plan);

        return PlanResponseDTO.builder()
                .id(updatedPlan.getPlanId())
                .planName(updatedPlan.getPlanName())
                .price(updatedPlan.getPrice())
                .resolution(updatedPlan.getResolution())
                .maxDevice(updatedPlan.getMaxDevice())
                .supportiveDevices(updatedPlan.getSupportiveDevices())
                .build();
    }
}
