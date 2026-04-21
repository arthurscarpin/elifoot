package github.arthurscarpin.elifoot.entity;

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
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stadium_seq")
    @SequenceGenerator(name = "stadium_seq", sequenceName = "stadium_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String city;

    private Integer capacity;

    private String urlImg;
}
