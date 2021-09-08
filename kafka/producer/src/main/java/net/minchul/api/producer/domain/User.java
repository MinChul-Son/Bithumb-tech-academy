package net.minchul.api.producer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data @Component
public class User {
    private String id;
    private String name;
    private String email;

    @Override
    public String toString() {
        return String.format("%s, %s, %s \n", id, name, email);
    }
}
