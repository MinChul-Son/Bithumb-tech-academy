package net.minchul.api.item.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "items")
public class Item {

    @Id
    @Column(name = "item_id")
    private long itemId;
}
