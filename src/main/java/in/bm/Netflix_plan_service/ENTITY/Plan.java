    package in.bm.Netflix_plan_service.ENTITY;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.List;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "plans")
    public class Plan {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long planId;

        @Column(nullable = false)
        private String userId;

        @Column(nullable = false)
        private String planName;

        @Column(nullable = false)
        private double price;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private PlanResolution resolution;

        @Column(nullable = false)
        private int maxDevice;

        @OneToMany(mappedBy = "plan" , cascade = CascadeType.ALL ,orphanRemoval = true)
        private List<UserSubscription> userSubscriptions;

    }
