package com.app.apicustomers.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer implements Serializable {

    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Integer id;


    @Column(length = 20)
    private String firstName;


    @Column(length = 20)
    private String lastName;


    private String email;

    @Column(columnDefinition = "DATE")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

}
