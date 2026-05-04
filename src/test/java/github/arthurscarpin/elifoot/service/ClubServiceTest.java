package github.arthurscarpin.elifoot.service;

import github.arthurscarpin.elifoot.dto.request.ClubRequest;
import github.arthurscarpin.elifoot.dto.response.ClubDetailResponse;
import github.arthurscarpin.elifoot.entity.Club;
import github.arthurscarpin.elifoot.entity.Stadium;
import github.arthurscarpin.elifoot.mapper.ClubMapper;
import github.arthurscarpin.elifoot.repository.ClubRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClubServiceTest {

    @InjectMocks
    ClubService clubService;

    @Mock
    ClubRepository repository;

    @Mock
    ClubMapper mapper;

    @Mock
    StadiumService stadiumService;

    @Captor
    ArgumentCaptor<Club> clubCaptor;

    @Test
    void searchResource() {
        // Arrange | Given - Construct objects to call the method
        // Action | When - Call the method being tested
        // Assertions | Then - Test the results
    }

    @Test
    @DisplayName("Should save new club with stadium successfully")
    void shouldSaveNewClubWithStadium() {
        // Arrange | Given - Construct objects to call the method
        Stadium stadium = Stadium.builder()
                .id(2L)
                .name("New Stadium")
                .city("New City")
                .capacity(2900)
                .urlImg("https://example.com/stadium-test.jpg")
                .build();

        Club newClub = Club.builder()
                .id(1L)
                .name("New Club")
                .founded(LocalDate.of(1980, 10, 10))
                .urlImg("https://example.com/stadium-test.jpg")
                .stadium(Stadium.builder().id(2L).build())
                .build();

        ClubRequest request = ClubRequest.builder()
                .stadiumId(2L)
                .build();

        // Action | When - Call the method being tested
        Mockito.when(mapper.toEntity(Mockito.any())).thenReturn(newClub);
        Mockito.when(stadiumService.searchResource(Mockito.anyLong())).thenReturn(stadium);
        Mockito.when(repository.save(Mockito.any())).thenAnswer(inv -> inv.getArgument(0));
        Mockito.when(mapper.toClubDetailResponse(Mockito.any())).thenReturn(Mockito.mock(ClubDetailResponse.class));
        
        clubService.save(request);

        // Assertions | Then - Test the results
        Mockito.verify(mapper).toEntity(Mockito.any());
        Mockito.verify(stadiumService).searchResource(Mockito.anyLong());
        Mockito.verify(mapper).toClubDetailResponse(Mockito.any());
        Mockito.verify(repository).save(clubCaptor.capture());

        Club club = clubCaptor.getValue();

        assertNotNull(club);
        assertNotNull(club.getCreatedAt());

        assertEquals(newClub.getId(), club.getId());
        assertEquals(newClub.getName(), club.getName());
        assertEquals(newClub.getFounded(), club.getFounded());
        assertEquals(newClub.getUrlImg(), club.getUrlImg());

        assertNotNull(club.getStadium());
        assertEquals(stadium.getId(), club.getStadium().getId());
        assertEquals(stadium.getName(), club.getStadium().getName());
        assertEquals(stadium.getCity(), club.getStadium().getCity());
        assertEquals(stadium.getCapacity(), club.getStadium().getCapacity());
    }

    @Test
    @DisplayName("Should save new club without stadium successfully")
    void shouldSaveNewClubWithoutStadium() {
        // Arrange | Given - Construct objects to call the method
        Club newClub = Club.builder()
                .id(1L)
                .name("New Club")
                .founded(LocalDate.of(1980, 10, 10))
                .urlImg("https://example.com/stadium-test.jpg")
                .build();

        ClubRequest request = ClubRequest.builder()
                .stadiumId(2L)
                .build();

        // Action | When - Call the method being tested
        Mockito.when(mapper.toEntity(Mockito.any())).thenReturn(newClub);
        Mockito.when(repository.save(Mockito.any())).thenAnswer(inv -> inv.getArgument(0));
        Mockito.when(mapper.toClubDetailResponse(Mockito.any())).thenReturn(Mockito.mock(ClubDetailResponse.class));

        clubService.save(request);

        // Assertions | Then - Test the results
        Mockito.verify(mapper).toEntity(Mockito.any());
        Mockito.verify(stadiumService, Mockito.never()).searchResource(Mockito.anyLong());
        Mockito.verify(repository).save(clubCaptor.capture());

        Club club = clubCaptor.getValue();

        assertNotNull(club);
        assertNotNull(club.getCreatedAt());
        assertNull(club.getStadium());

        assertEquals(newClub.getId(), club.getId());
        assertEquals(newClub.getName(), club.getName());
        assertEquals(newClub.getFounded(), club.getFounded());
        assertEquals(newClub.getUrlImg(), club.getUrlImg());
    }

    @Test
    void findAll() {
        // Arrange | Given - Construct objects to call the method
        // Action | When - Call the method being tested
        // Assertions | Then - Test the results
    }

    @Test
    void findById() {
        // Arrange | Given - Construct objects to call the method
        // Action | When - Call the method being tested
        // Assertions | Then - Test the results
    }

    @Test
    void updateById() {
        // Arrange | Given - Construct objects to call the method
        // Action | When - Call the method being tested
        // Assertions | Then - Test the results
    }

    @Test
    void deleteById() {
        // Arrange | Given - Construct objects to call the method
        // Action | When - Call the method being tested
        // Assertions | Then - Test the results
    }
}