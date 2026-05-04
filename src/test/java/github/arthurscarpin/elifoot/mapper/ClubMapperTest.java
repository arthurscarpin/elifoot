package github.arthurscarpin.elifoot.mapper;

import github.arthurscarpin.elifoot.dto.request.ClubRequest;
import github.arthurscarpin.elifoot.dto.response.ClubDetailResponse;
import github.arthurscarpin.elifoot.dto.response.ClubResponse;
import github.arthurscarpin.elifoot.entity.Club;
import github.arthurscarpin.elifoot.entity.Stadium;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClubMapperTest {

    private final ClubMapper mapper = Mappers.getMapper(ClubMapper.class);

    @Test
    @DisplayName("toClubResponse - Should map Club to ClubResponse correctly")
    void toClubResponse() {
        // Arrange | Given - Construct objects to call the method
        Club club = Club.builder()
                .id(1L)
                .name("Club Test")
                .founded(LocalDate.of(1900, 5, 15))
                .urlImg("https://example.com/club-test.png")
                .createdAt(LocalDateTime.now())
                .active(true)
                .stadium(null)
                .players(List.of())
                .build();

        // Action | When - Call the method being tested
        ClubResponse clubResponse = mapper.toClubResponse(club);

        // Assertions | Then - Test the results
        assertNotNull(clubResponse);
        assertEquals(club.getId(), clubResponse.id());
        assertEquals(club.getName(), clubResponse.name());
        assertEquals(club.getFounded(), clubResponse.founded());
        assertEquals(club.getUrlImg(), clubResponse.urlImg());
    }

    @Test
    @DisplayName("toClubDetailResponse - Should map Club to ClubDetailResponse correctly, including Stadium details")
    void toClubDetailResponse() {
        // Arrange | Given - Construct objects to call the method
        Stadium stadium = Stadium.builder()
                .id(1L)
                .name("Stadium Test")
                .city("City Test")
                .capacity(78000)
                .urlImg("https://example.com/stadium-test.jpg")
                .build();

        Club club = Club.builder()
                .id(1L)
                .name("Club Test")
                .founded(LocalDate.of(1900, 5, 15))
                .urlImg("https://example.com/club-test.png")
                .createdAt(LocalDateTime.now())
                .active(true)
                .stadium(stadium)
                .players(List.of())
                .build();

        // Action | When - Call the method being tested
        ClubDetailResponse clubDetailResponse = mapper.toClubDetailResponse(club);

        // Assertions | Then - Test the results
        assertNotNull(clubDetailResponse);
        assertNotNull(clubDetailResponse.stadium());

        assertEquals(club.getId(), clubDetailResponse.id());
        assertEquals(club.getName(), clubDetailResponse.name());
        assertEquals(club.getFounded(), clubDetailResponse.founded());
        assertEquals(club.getUrlImg(), clubDetailResponse.urlImg());

        assertEquals(club.getStadium().getId(), clubDetailResponse.stadium().id());
        assertEquals(club.getStadium().getName(), clubDetailResponse.stadium().name());
        assertEquals(club.getStadium().getCity(), clubDetailResponse.stadium().city());
        assertEquals(club.getStadium().getCapacity(), clubDetailResponse.stadium().capacity());
        assertEquals(club.getStadium().getUrlImg(), clubDetailResponse.stadium().urlImg());
    }

    @Test
    @DisplayName("toEntity - Should map ClubRequest to Club entity correctly, including Stadium association")
    void toEntity() {
        // Arrange | Given - Construct objects to call the method
        ClubRequest clubRequest = ClubRequest.builder()
                .name("Club Test")
                .founded(LocalDate.of(1900, 5, 15))
                .urlImg("https://example.com/club-test.png")
                .stadiumId(1L)
                .build();

        // Action | When - Call the method being tested
        Club club = mapper.toEntity(clubRequest);

        // Assertions | Then - Test the results
        assertNotNull(club);
        assertEquals(club.getName(), clubRequest.name());
        assertEquals(club.getFounded(), clubRequest.founded());
        assertEquals(club.getUrlImg(), clubRequest.urlImg());
        assertEquals(club.getStadium().getId(), clubRequest.stadiumId());
    }
}