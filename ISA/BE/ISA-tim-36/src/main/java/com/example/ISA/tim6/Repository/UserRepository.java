package com.example.ISA.tim6.Repository;

import com.example.ISA.tim6.ENUMS.Role;
import com.example.ISA.tim6.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    @Query(value = "SELECT * from users where id not in (select distinct(admins_id) from centre_admins) and role = 'ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE'", nativeQuery = true)
    List<User> findAllByRole(Role role);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
