package fr.shewbii.sojahbbackend.web.rest;

import fr.shewbii.sojahbbackend.service.RefereeService;
import fr.shewbii.sojahbbackend.service.dto.RefereeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/referees")
public class RefereeController {

    private final RefereeService refereeService;

    public RefereeController(RefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @GetMapping
    public List<RefereeDTO> findAll() {
        return refereeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefereeDTO> findById(@PathVariable Long id) {
        Optional<RefereeDTO> officeDTO = refereeService.findById(id);
        return ResponseUtil.wrapOrNotFound(officeDTO);
    }

}
