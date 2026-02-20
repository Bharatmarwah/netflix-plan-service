package in.bm.Netflix_plan_service.RequestDTO;

import in.bm.Netflix_plan_service.ENTITY.PlanResolution;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PlanRequestDTO {

    @NotBlank
    private String planName;

    @NotNull
    private double price;

    @NotNull
    private String supportiveDevices;

    private PlanResolution resolution;

    @NotNull
    private int maxDevice;
}
