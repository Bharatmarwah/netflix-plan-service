package in.bm.Netflix_plan_service.RequestDTO;

import in.bm.Netflix_plan_service.ENTITY.PlanResolution;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class PlanRequestDTO {

    @NotBlank
    private String planName;

    @NotNull
    @Positive
    private double price;

    @NotNull
    private String supportiveDevices;

    private PlanResolution resolution;

    @NotNull
    @Min(0)
    private int maxDevice;
}
