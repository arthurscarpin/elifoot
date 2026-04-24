package github.arthurscarpin.elifoot.service;

import github.arthurscarpin.elifoot.entity.Scope;
import github.arthurscarpin.elifoot.exceptions.ResourceNotFoundException;
import github.arthurscarpin.elifoot.repository.ScopeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScopeService {

    private final ScopeRepository repository;

    public Scope findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Scope not found with id: " + id));
    }
}
