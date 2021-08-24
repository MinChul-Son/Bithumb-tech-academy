package net.minchul.api;

import lombok.RequiredArgsConstructor;
import net.minchul.api.item.domain.Item;
import net.minchul.api.item.service.ItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ItemApplication implements CommandLineRunner {
    private final ItemService itemService;

    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        itemService.deleteAll();
//        itemService.save(new Item("삼성", "갤럭시", "검은색"));
//        itemService.save(new Item("애플", "아이폰", "흰색"));
//        itemService.save(new Item("샤오미", "홍미", "파란색"));
    }
}
