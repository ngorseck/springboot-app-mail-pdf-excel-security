package sn.isi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.isi.entities.User;

@Repository
public interface IUser extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = :em")
    public User getByEmail(@Param("em") String email);
}
