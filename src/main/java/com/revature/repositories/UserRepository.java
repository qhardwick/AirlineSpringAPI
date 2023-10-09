package com.revature.repositories;

import com.revature.beans.User;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCassandraRepository<User, String> {

}
