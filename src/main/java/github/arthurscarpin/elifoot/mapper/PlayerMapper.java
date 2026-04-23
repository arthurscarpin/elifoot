package github.arthurscarpin.elifoot.mapper;

import github.arthurscarpin.elifoot.dto.request.PlayerRequest;
import github.arthurscarpin.elifoot.dto.response.PlayerDetailResponse;
import github.arthurscarpin.elifoot.dto.response.PlayerResponse;
import github.arthurscarpin.elifoot.entity.Player;
import github.arthurscarpin.elifoot.enums.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "position", source = "position", qualifiedByName = "enumToString")
    PlayerResponse toPlayerResponse(Player player);

    @Mapping(target = "position", source = "position", qualifiedByName = "enumToString")
    PlayerDetailResponse toPlayerDetailResponse(Player player);

    @Mapping(target = "club.id", source = "clubId")
    Player toEntity(PlayerRequest request);

    @Named("enumToString")
    default String mapPositionToString(Position position) {
        return position != null ? position.getLabel() : null;
    }
}
