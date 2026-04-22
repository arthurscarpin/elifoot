package github.arthurscarpin.elifoot.service;

import github.arthurscarpin.elifoot.dto.request.StadiumRequest;
import github.arthurscarpin.elifoot.dto.response.StadiumResponse;
import github.arthurscarpin.elifoot.entity.Stadium;
import github.arthurscarpin.elifoot.exception.ResourceNotFoundException;
import github.arthurscarpin.elifoot.mapper.StadiumMapper;
import github.arthurscarpin.elifoot.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository repository;

    private final StadiumMapper mapper;

    private Stadium searchResource(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stadium not found with id: " + id));
    }

    @Transactional
    public StadiumResponse save(StadiumRequest request) {
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public Page<StadiumResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    public StadiumResponse findById(Long id) {
        return mapper.toResponse(searchResource(id));
    }

    @Transactional
    public StadiumResponse updateById(Long id, StadiumRequest request) {
        Stadium stadium = searchResource(id);
        stadium.setName(request.name());
        stadium.setCity(request.city());
        stadium.setCapacity(request.capacity());
        stadium.setUrlImg(request.urlImg());
        return mapper.toResponse(repository.save(stadium));
    }

    @Transactional
    public void deleteById(Long id) {
        Stadium stadium = searchResource(id);
        repository.deleteById(stadium.getId());
    }
}
