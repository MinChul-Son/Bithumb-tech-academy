package net.minchul.api.item.service;

import net.minchul.api.item.domain.Item;
import net.minchul.api.item.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootConfiguration
@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @Autowired
    private ItemServiceImpl itemService;
    @Autowired
    private ItemRepository itemRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    void findAll() {
        Item item = Item.builder().itemBrand("A").itemName("B").itemColor("C").build();
        itemService.save(item);
    }

    @Test
    void findById() {
    }

    @Test
    void existsById() {
    }

    @Test
    void count() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void save() {
    }
}