package github.arthurscarpin.elifoot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Position {
    GOALKEEPER("Goalkeeper"),
    DEFENDER("Defender"),
    FULLBACK("Fullback"),
    MIDFIELDER("Midfielder"),
    FORWARD("Forward");

    private final String label;
}
