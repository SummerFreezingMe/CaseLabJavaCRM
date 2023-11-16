package com.greenatom.domain.entity;

import com.greenatom.domain.enums.ClaimStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

/**
 * A Claim
 */

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "claim")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @NonNull
    private Order order;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @NonNull
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    @NonNull
    private Client client;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotBlank
    private ClaimStatus claimStatus;

    @Column(name = "creation_time")
    private Instant creationTime;

    @Column(name = "resolved_time")
    private Instant resolvedTime;

}
