package org.example;

import org.example.model.Manager;

public interface IManagerRepo extends IRepository<Integer, Manager>{
    Manager getManager(String username);
}
