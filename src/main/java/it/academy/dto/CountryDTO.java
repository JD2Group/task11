package it.academy.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class CountryDTO implements Serializable {

    @Builder.Default
    private Long id = null;

    @Builder.Default
    private String countryName = "";
}
