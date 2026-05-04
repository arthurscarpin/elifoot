package github.arthurscarpin.elifoot.mapper;

import github.arthurscarpin.elifoot.dto.request.PlayerRequest;
import github.arthurscarpin.elifoot.dto.response.PlayerDetailResponse;
import github.arthurscarpin.elifoot.dto.response.PlayerResponse;
import github.arthurscarpin.elifoot.entity.Club;
import github.arthurscarpin.elifoot.entity.Player;
import github.arthurscarpin.elifoot.enums.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayerMapperTest {

    private final PlayerMapper mapper = Mappers.getMapper(PlayerMapper.class);

    @Test
    @DisplayName("toPlayerResponse - Should map Player to PlayerResponse correctly")
    void toPlayerResponse() {
        // Arrange | Given - Construct objects to call the method
        Player player = Player.builder()
                .id(1L)
                .name("Player Test")
                .position(Position.FORWARD)
                .shirtNumber(10)
                .urlImg("https://example.com/player-test.png")
                .club(null)
                .build();

        // Action | When - Call the method being tested
        PlayerResponse playerResponse = mapper.toPlayerResponse(player);

        // Assertions | Then - Test the results
        assertNotNull(playerResponse);
        assertEquals(player.getId(), playerResponse.id());
        assertEquals(player.getName(), playerResponse.name());
        assertEquals(player.getPosition().getLabel(), playerResponse.position());
        assertEquals(player.getShirtNumber(), playerResponse.shirtNumber());
        assertEquals(player.getUrlImg(), playerResponse.urlImg());
    }

    @Test
    @DisplayName("toPlayerDetailResponse - Should map Player to PlayerDetailResponse correctly, including Club details")
    void toPlayerDetailResponse() {
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

        Player player = Player.builder()
                .id(1L)
                .name("Player Test")
                .position(Position.FORWARD)
                .shirtNumber(10)
                .urlImg("https://example.com/player-test.png")
                .club(club)
                .build();

        // Action | When - Call the method being tested
        PlayerDetailResponse playerDetailResponse = mapper.toPlayerDetailResponse(player);

        // Assertions | Then - Test the results
        assertNotNull(playerDetailResponse);
        assertNotNull(playerDetailResponse.club());

        assertEquals(player.getId(), playerDetailResponse.id());
        assertEquals(player.getName(), playerDetailResponse.name());
        assertEquals(player.getPosition().getLabel(), playerDetailResponse.position());
        assertEquals(player.getShirtNumber(), playerDetailResponse.shirtNumber());
        assertEquals(player.getUrlImg(), playerDetailResponse.urlImg());
        assertEquals(player.getClub().getId(), playerDetailResponse.club().id());
        assertEquals(player.getClub().getName(), playerDetailResponse.club().name());
    }

    @Test
    @DisplayName("toEntity - Should map PlayerRequest to Player entity correctly")
    void toEntity() {
        // Arrange | Given - Construct objects to call the method
        PlayerRequest playerRequest = PlayerRequest.builder()
                .name("Player Test")
                .position(Position.FORWARD)
                .shirtNumber(10)
                .urlImg("https://example.com/player-test.png")
                .clubId(1L)
                .build();

        // Action | When - Call the method being tested
        Player player = mapper.toEntity(playerRequest);

        // Assertions | Then - Test the results
        assertNotNull(player);
        assertEquals(player.getName(), playerRequest.name());
        assertEquals(player.getPosition(), playerRequest.position());
        assertEquals(player.getShirtNumber(), playerRequest.shirtNumber());
        assertEquals(player.getUrlImg(), playerRequest.urlImg());
        assertEquals(player.getClub().getId(), playerRequest.clubId());
    }

    @Test
    @DisplayName("mapPositionToString - Should map Position enum to its label string correctly")
    void mapPositionToString() {
        // Arrange | Given - Construct objects to call the method
        Position position = Position.FORWARD;

        // Action | When - Call the method being tested
        String label = mapper.mapPositionToString(position);

        // Assertions | Then - Test the results
        assertNotNull(label);
        assertEquals("Forward", label);
    }
}