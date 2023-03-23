package com.aanshik.UserService.Repository;

import com.aanshik.UserService.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
