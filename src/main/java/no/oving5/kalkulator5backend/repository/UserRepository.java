package no.oving5.kalkulator5backend.repository;

import no.oving5.kalkulator5backend.model.User;

public interface UserRepository {
    int save(User user);
    User findById(Long id);
}
