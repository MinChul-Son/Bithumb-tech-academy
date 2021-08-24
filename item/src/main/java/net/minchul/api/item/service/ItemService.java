package net.minchul.api.item.service;

import net.minchul.api.item.domain.Item;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public interface ItemService {
    List<Item> findAll();

    Optional<Item> findById(long id);

    boolean existsById(long id);

    long count();

    void deleteById(long id);

    void save(Item item);
}
