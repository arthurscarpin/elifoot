package github.arthurscarpin.elifoot.mapper;

import github.arthurscarpin.elifoot.dto.request.StadiumRequest;
import github.arthurscarpin.elifoot.dto.response.StadiumResponse;
import github.arthurscarpin.elifoot.entity.Stadium;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StadiumMapper {

    StadiumResponse toResponse(Stadium entity);

    Stadium toEntity(StadiumRequest request);
}
