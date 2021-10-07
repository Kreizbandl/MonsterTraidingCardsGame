package at.fhtw.bif3vz.swe.mtcg.if19b101.user;


import at.fhtw.bif3vz.swe.mtcg.if19b101.card.Card;

import java.util.ArrayList;
import java.util.List;

public class User {
    String username;
    String password;
    int coins;
    Stack stack;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.coins = 20;
    }

    public Stack getStack() {
        return this.stack;
    }

    public void setStack(List<Card> allCards) {
        this.stack = stack;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
