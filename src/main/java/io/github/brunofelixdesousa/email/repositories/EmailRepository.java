package io.github.brunofelixdesousa.email.repositories;

import io.github.brunofelixdesousa.email.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
