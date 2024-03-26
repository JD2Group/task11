package it.academy.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "students")
@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Builder.Default
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "country_name")
    private String countryName;

    @Builder.Default
    @OneToMany(mappedBy = "country")
    private Set<Student> students = new HashSet<>();
}
