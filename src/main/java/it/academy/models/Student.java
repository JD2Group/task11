package it.academy.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;


    @Column
    private String surname;

    @Column
    private int age;

    @Embedded
    @ToString.Exclude
    private Address address;

    @Column
    private int mark;
}
