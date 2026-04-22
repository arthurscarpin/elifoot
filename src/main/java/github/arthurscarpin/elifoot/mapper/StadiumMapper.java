package github.arthurscarpin.elifoot.mapper;

import github.arthurscarpin.elifoot.dto.request.StadiumRequest;
import github.arthurscarpin.elifoot.dto.response.StadiumResponse;
import github.arthurscarpin.elifoot.entity.Stadium;
import org.springframework.stereotype.Component;

@Component
public class StadiumMapper {

    public static StadiumResponse toResponse(Stadium entity) {
        return StadiumResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .city(entity.getCity())
                .capacity(entity.getCapacity())
                .urlImg(entity.getUrlImg())
                .build();
    }

    public static Stadium toEntity(StadiumRequest request) {
        return Stadium.builder()
                .name(request.name())
                .city(request.city())
                .capacity(request.capacity())
                .urlImg(request.urlImg())
                .build();
    }
}
