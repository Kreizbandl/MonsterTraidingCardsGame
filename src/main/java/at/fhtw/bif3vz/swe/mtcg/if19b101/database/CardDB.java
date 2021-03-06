package at.fhtw.bif3vz.swe.mtcg.if19b101.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDB implements Serializable {
    private String id;
    private String name;
    private float damage;
}
