package at.fhtw.bif3vz.swe.mtcg.if19b101.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCardDB implements Serializable {
    private String cardid;
    private String name;
    private float damage;
}
