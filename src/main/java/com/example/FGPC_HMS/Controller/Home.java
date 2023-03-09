package com.example.FGPC_HMS.Controller;

import com.example.FGPC_HMS.Model.Family_Tree;
import com.example.FGPC_HMS.Model.Patient;
import com.example.FGPC_HMS.Service.Patient_service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins="*")
public class Home {

    @Autowired
    Patient_service patient_service;



    @PostMapping(value = "/")
    public Patient register_patient(@RequestBody Patient patient){
        return patient_service.add_new_patient(patient);
    }

    @PutMapping(value = "/update_Patient")
    public String update_patient(@RequestBody Patient patient){
        return patient_service.update_patient_record(patient);
    }

    @PostMapping(value = "/create_new_family")
    public String create_new_family(@RequestParam Integer mrn){

        return patient_service.add_new_family(mrn);

    }

    @PostMapping(value = "/change_from_vip_to_normal")
    public String change_family_from_vip_to_normal(@RequestBody Patient patient){

        return patient_service.change_from_vip_to_normal(patient.getMrn());

    }

    @PostMapping(value = "/change_from_normal_to_vip")
    public String change_family_from_normal_to_vip(@RequestBody Patient patient){

        return patient_service.change_from_normal_to_vip(patient.getMrn());

    }

    @GetMapping(value = "/search_patient")
    public Patient search_patient(@RequestBody Patient patient,HttpSession session){
//        System.out.printf("id -> " + session.getId());
        return patient_service.search_patient(patient);

    }

    @GetMapping(value = "/search_family")
    public Family_Tree search_family(@RequestParam Integer id){

        return patient_service.search_family(id);

    }

    @PutMapping(value = "/add_in_family")
    public String add_in_family(@RequestParam Integer mrn,@RequestParam Integer family_id,@RequestParam String relation){

        return patient_service.add_in_family(mrn,family_id,Patient.relation.valueOf(relation));

    }

    @PutMapping(value = "/remove_from_family")
    public String remove_from_family(@RequestParam Integer mrn,@RequestParam Integer family_id){

        return patient_service.remove_from_family(mrn,family_id);

    }

}
