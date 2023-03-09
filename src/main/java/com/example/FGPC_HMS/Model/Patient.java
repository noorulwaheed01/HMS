package com.example.FGPC_HMS.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;


@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer mrn;


    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @NotBlank(message="Date_of_birth must not be blank")
    private Date date_of_birth;



    @NotBlank(message="Father_name must not be blank")
    private String father_name;


    @Size(min=13,max = 13, message="Cnic must be at 13 characters long")
    private String cnic;

    private String address;

    @Size(min=11,max = 13, message="Cnic must be at 11 characters long")
    private String contact_no;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private relation relationship_family;


    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,targetEntity = Family_Tree.class)
    @JoinColumn(name = "family_id",referencedColumnName = "family_id",nullable = true)
    @JsonBackReference
    private Family_Tree family;

    public enum relation{
        Owner,Father,Mother,Son,Daughter
    }

    public enum Gender{
        Male,Female
    }

}
