package org.example;

import java.util.List;

public interface IRepository<ID, Entity> {

    void save(Entity elem);
    void update(ID id, Entity newElem);
    void delete(ID id);
    List<Entity> getAll();
    Entity getOne(ID id);
}
