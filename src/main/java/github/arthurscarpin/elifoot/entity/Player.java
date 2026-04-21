package github.arthurscarpin.elifoot.entity;

import github.arthurscarpin.elifoot.enums.Position;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(name = "player_seq", sequenceName = "player_seq", allocationSize = 1)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Position position;

    private Integer shirtNumber;

    private String urlImg;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}
