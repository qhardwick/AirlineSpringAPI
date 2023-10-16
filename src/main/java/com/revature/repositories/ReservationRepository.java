package com.revature.repositories;

import com.revature.beans.Reservation;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends ReactiveCassandraRepository<Reservation, String> {
}
