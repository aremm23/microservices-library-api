package com.artsem.api.authenticationservice.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@ToString()
@EqualsAndHashCode()
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user_details")
@NoArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "userIdSeqGen", sequenceName = "user_details_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "userIdSeqGen")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}