package net.mybus.repository;

import net.mybus.domain.MyBusStop;
import net.mybus.domain.MyBusStopId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBusStopRepository extends JpaRepository<MyBusStop, MyBusStopId> {
}
