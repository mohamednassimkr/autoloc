package tn.esprit.autoloc.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.service.CrudService;

import java.util.List;
import java.util.function.BiConsumer;

abstract class AbstractCrudService<T> implements CrudService<T> {

    private final JpaRepository<T, Long> repository;
    private final BiConsumer<T, Long> idSetter;

    protected AbstractCrudService(
            JpaRepository<T, Long> repository,
            BiConsumer<T, Long> idSetter
    ) {
        this.repository = repository;
        this.idSetter = idSetter;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id));
    }

    @Override
    public T update(Long id, T entity) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id));
        idSetter.accept(entity, id);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Entity not found: " + id);
        }
        repository.deleteById(id);
    }
}
