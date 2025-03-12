package net.mybus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tbl_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(nullable = false, unique = true, length = 20)
    private String userId;
    @Column(nullable = false, length = 20)
    private String userName;
    @Column(nullable = false, length = 200)
    private String password;
    @Column(length = 20)
    private String provider;
    private String providerId;
}
