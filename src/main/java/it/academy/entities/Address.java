package it.academy.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {

    private String city;

    private String street;

    private int house;
}
