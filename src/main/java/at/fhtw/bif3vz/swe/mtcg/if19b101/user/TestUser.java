package at.fhtw.bif3vz.swe.mtcg.if19b101.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestUser implements Serializable {
    private String username;
    private String password;
    private String token;
}