package org.example;

import org.example.model.Scouter;

public interface IScouterRepo extends IRepository<Integer, Scouter>{
    Scouter getScouter(String username);
}
