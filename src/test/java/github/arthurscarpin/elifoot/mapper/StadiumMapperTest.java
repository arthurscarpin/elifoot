package github.arthurscarpin.elifoot.mapper;

import github.arthurscarpin.elifoot.dto.request.StadiumRequest;
import github.arthurscarpin.elifoot.dto.response.StadiumResponse;
import github.arthurscarpin.elifoot.entity.Stadium;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class StadiumMapperTest {

    private final StadiumMapper mapper = Mappers.getMapper(StadiumMapper.class);

    @Test
    @DisplayName("toResponse - Should map Stadium to StadiumResponse correctly")
    void toResponse() {
        // Arrange | Given - Construct objects to call the method
        Stadium stadium = Stadium.builder()
                .id(1L)
                .name("Stadium Test")
                .city("City Test")
                .capacity(78000)
                .urlImg("https://example.com/stadium-test.jpg")
                .build();

        // Action | When - Call the method being tested
        StadiumResponse stadiumResponse = mapper.toResponse(stadium);

        // Assertions | Then - Test the results
        assertNotNull(stadiumResponse);
        assertEquals(stadium.getId(), stadiumResponse.id());
        assertEquals(stadium.getName(), stadiumResponse.name());
        assertEquals(stadium.getCity(), stadiumResponse.city());
        assertEquals(stadium.getCapacity(), stadiumResponse.capacity());
        assertEquals(stadium.getUrlImg(), stadiumResponse.urlImg());
    }

    @Test
    @DisplayName("toEntity - Should map StadiumRequest to Stadium correctly")
    void toEntity() {
        // Arrange | Given - Construct objects to call the method
        StadiumRequest stadiumRequest = StadiumRequest.builder()
                .name("Stadium Test")
                .city("City Test")
                .capacity(78000)
                .urlImg("https://example.com/stadium-test.jpg")
                .build();

        // Action | When - Call the method being tested
        Stadium stadium = mapper.toEntity(stadiumRequest);

        // Assertions | Then - Test the results
        assertNotNull(stadium);
        assertEquals(stadium.getName(), stadiumRequest.name());
        assertEquals(stadium.getCity(), stadiumRequest.city());
        assertEquals(stadium.getCapacity(), stadiumRequest.capacity());
        assertEquals(stadium.getUrlImg(), stadiumRequest.urlImg());
    }
}