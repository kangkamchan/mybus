package net.mybus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MyBusStopId implements Serializable {
    private String userId;
    private String busStopId;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyBusStopId myBusId = (MyBusStopId) o;
        return Objects.equals(userId, myBusId.userId) && Objects.equals(busStopId, myBusId.busStopId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userId, busStopId);
    }
}
