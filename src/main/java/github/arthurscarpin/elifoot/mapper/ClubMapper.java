package github.arthurscarpin.elifoot.mapper;

import github.arthurscarpin.elifoot.dto.request.ClubRequest;
import github.arthurscarpin.elifoot.dto.response.ClubDetailResponse;
import github.arthurscarpin.elifoot.dto.response.ClubResponse;
import github.arthurscarpin.elifoot.entity.Club;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClubMapper {

    ClubResponse toClubResponse(Club entity);

    ClubDetailResponse toClubDetailResponse(Club entity);

    @Mapping(target = "stadium.id", source = "stadiumId")
    Club toEntity(ClubRequest request);
}
