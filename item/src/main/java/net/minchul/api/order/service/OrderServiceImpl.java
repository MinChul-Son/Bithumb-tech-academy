package net.minchul.api.order.service;

import lombok.RequiredArgsConstructor;
import net.minchul.api.order.domain.Order;
import net.minchul.api.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public boolean existsById(long id) {
        return orderRepository.existsById(id);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}
