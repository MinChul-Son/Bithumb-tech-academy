package net.minchul.api.order.service;

import net.minchul.api.order.domain.Order;
import net.minchul.api.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrderService {
    List<Order> findAll();

    Optional<Order> findById(long id);

    boolean existsById(long id);

    long count();

    void deleteById(long id);

    void save(Order order);
}
