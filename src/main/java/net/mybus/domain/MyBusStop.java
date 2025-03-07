package net.mybus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_my_bus_stop")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@IdClass(MyBusStopId.class)
public class MyBusStop {
    @Id
    private String busStopId;
    @Id
    private String userId;
    @ManyToOne
    @JoinColumn(name="userId", insertable=false, updatable=false)
    private User user;
}
