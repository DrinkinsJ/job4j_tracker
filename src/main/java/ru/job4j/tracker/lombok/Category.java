package ru.job4j.tracker.lombok;

import com.sun.istack.NotNull;
import lombok.*;

@EqualsAndHashCode
@RequiredArgsConstructor
public class Category {
    @NotNull
    @EqualsAndHashCode.Include
    @Getter
    private int id;
    @Getter
    @Setter
    private String name;
}
