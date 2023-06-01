package org.example;

import org.example.model.Player;

import java.util.List;

public interface IPlayerRepo extends IRepository<Integer, Player>{
    List<Player> filterPlayer(String position, Integer age, String nationality, String rating);
}
