package fr.shewbii.sojahbbackend.service.dto;

import fr.shewbii.sojahbbackend.domain.enums.CategoryEnum;
import fr.shewbii.sojahbbackend.domain.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RefereeDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private CategoryEnum category;
    private GenderEnum gender;
}
