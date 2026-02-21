package in.bm.Netflix_plan_service.CONTROLLER;

import in.bm.Netflix_plan_service.RequestDTO.PlanRequestDTO;
import in.bm.Netflix_plan_service.ResponseDTO.PlanResponseDTO;
import in.bm.Netflix_plan_service.SERVICE.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanResponseDTO createPlan(@Valid @RequestBody PlanRequestDTO requestDTO){
        return planService.createPlan(requestDTO);
    }

    @GetMapping("/{planId}")
    @ResponseStatus(HttpStatus.OK)
    public PlanResponseDTO createPlan(@PathVariable Long planId){
        return planService.getPlanById(planId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlanResponseDTO> getAllPlans(){
        return planService.getAllPlans();
    }

    @PatchMapping("/{planId}")
    @ResponseStatus(HttpStatus.OK)
    public PlanResponseDTO updatePlan(@PathVariable Long planId , @Valid @RequestBody PlanRequestDTO requestDTO){
        return planService.updatePlan(planId,requestDTO);
    }
}
