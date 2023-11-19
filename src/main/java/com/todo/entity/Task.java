package com.todo.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(schema = "todo", name = "task")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task extends BaseEntity implements Serializable {
    @Column
    String description;
    @Enumerated(EnumType.ORDINAL)
    @Convert(converter = TinyIntJdbcType.class)
    Status status;
}

