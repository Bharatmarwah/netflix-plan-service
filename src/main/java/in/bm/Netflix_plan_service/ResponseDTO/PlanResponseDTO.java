package in.bm.Netflix_plan_service.ResponseDTO;

import in.bm.Netflix_plan_service.ENTITY.PlanResolution;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class PlanResponseDTO {

    private long id;
    private String planName;
    private double price;
    private String supportiveDevices;
    private PlanResolution resolution;
    private int maxDevice;
}
