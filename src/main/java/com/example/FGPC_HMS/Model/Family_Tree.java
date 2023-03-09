package com.example.FGPC_HMS.Model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "family_tree")
public class Family_Tree {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int family_id;


    private int no_family_member = 1;

    @OneToMany(mappedBy = "family",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,targetEntity = Patient.class)
    @JsonManagedReference
    private Set<Patient> members = new HashSet<>();


    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,targetEntity = Category.class)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id",nullable = false)
    private Category category;

}
