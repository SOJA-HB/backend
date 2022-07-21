package fr.shewbii.sojahbbackend.service.mapper;

import fr.shewbii.sojahbbackend.domain.Referee;
import fr.shewbii.sojahbbackend.service.dto.RefereeDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Referee} and its DTO {@link RefereeDTO}.
 */
@Mapper(componentModel = "spring")
public interface RefereeMapper extends EntityMapper<RefereeDTO, Referee> {
}
