package fr.shewbii.sojahbbackend.service;

import fr.shewbii.sojahbbackend.repository.RefereeRepository;
import fr.shewbii.sojahbbackend.service.dto.RefereeDTO;
import fr.shewbii.sojahbbackend.service.mapper.RefereeMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link fr.shewbii.sojahbbackend.domain.Referee}.
 */
@Service
@Transactional
public class RefereeService {

    private final RefereeMapper refereeMapper;

    private final RefereeRepository refereeRepository;

    public RefereeService(RefereeMapper refereeMapper, RefereeRepository refereeRepository) {
        this.refereeMapper = refereeMapper;
        this.refereeRepository = refereeRepository;
    }

    @Transactional(readOnly = true)
    public List<RefereeDTO> findAll() {
        return refereeRepository.findAll().stream().map(refereeMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Optional<RefereeDTO> findById(Long id) {
        return refereeRepository.findById(id).map(refereeMapper::toDto);
    }
}
