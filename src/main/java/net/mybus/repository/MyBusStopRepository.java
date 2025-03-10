package net.mybus.repository;

import net.mybus.domain.MyBusStop;
import net.mybus.domain.MyBusStopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBusStopRepository extends JpaRepository<MyBusStop, MyBusStopId> {
}
