package at.fhtw.bif3vz.swe.mtcg.if19b101.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDB implements Serializable {
    private String username;
    private String password;
    private String token;
}