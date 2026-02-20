
package in.bm.Netflix_plan_service.ENTITY;

public enum PlanResolution {
    RES_480(480),
    RES_720(720),
    RES_1080(1080),
    RES_2160(2160);

    private final int pixels;

    PlanResolution(int pixels) {
        this.pixels = pixels;
    }

    public int getPixels() {
        return pixels;
    }

    @Override
    public String toString() {
        return String.valueOf(pixels);
    }
}