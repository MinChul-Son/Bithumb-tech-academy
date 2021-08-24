package net.minchul.api.item.controller;

import net.minchul.api.item.domain.Item;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @GetMapping
    public List<Item> findAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Optional<Item> findById(@PathVariable long id) {
        return Optional.empty();
    }

    @PostMapping
    public void save(Item Item) {

    }

    @PutMapping
    public void update(Item Item) {

    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable long id) {
        return false;
    }

    @GetMapping("/count")
    public long count() {
        return 0;
    }

    @DeleteMapping
    public void deleteById(long id) {

    }
}
