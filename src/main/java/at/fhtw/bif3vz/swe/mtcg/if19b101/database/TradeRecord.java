package at.fhtw.bif3vz.swe.mtcg.if19b101.database;

import java.io.Serializable;

public record TradeRecord(
        String token,
        String id,
        String cardToTrade,
        String type,
        float minimumDamage) implements Serializable
{
}

