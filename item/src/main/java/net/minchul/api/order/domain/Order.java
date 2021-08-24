package net.minchul.api.order.domain;

import lombok.Data;
import net.minchul.api.item.domain.Item;
import net.minchul.api.user.domain.User;

import javax.persistence.*;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    private long orderId;

    @Column(name = "price")
    private long price;

    @Column(name = "order_status")
    private String count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
