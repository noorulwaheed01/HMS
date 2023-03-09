package com.example.FGPC_HMS.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String result;

    private Patient patient;

    private Family_Tree family_tree;

}
