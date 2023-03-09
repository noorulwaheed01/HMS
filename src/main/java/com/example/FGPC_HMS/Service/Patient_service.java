package com.example.FGPC_HMS.Service;

import com.example.FGPC_HMS.Model.Category;
import com.example.FGPC_HMS.Model.Family_Tree;
import com.example.FGPC_HMS.Model.Patient;
import com.example.FGPC_HMS.Repositary.Category_repositary;
import com.example.FGPC_HMS.Repositary.Family_repositary;
import com.example.FGPC_HMS.Repositary.Patient_repositary;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Patient_service {

    @Autowired
    Patient_repositary patient_repositary;

    @Autowired
    Family_repositary family_repositary;

    @Autowired
    Category_repositary category_repositary;


    public Patient add_new_patient(Patient patient){
        patient = patient_repositary.save(patient);
        return patient;
    }

    public String update_patient_record(Patient patient){
        Optional<Patient> patient1 = patient_repositary.findById(patient.getMrn());
        if (patient1.isPresent()){
            patient.setRelationship_family(patient1.get().getRelationship_family());
            patient.setFamily(patient1.get().getFamily());
            patient_repositary.save(patient);
            return "Patient record update successfully";
        }else {
            return "Patient record not found";
        }
    }

    public Patient search_patient(Patient patient){
        if(patient.getMrn() != null){
            Optional<Patient> patient1 = patient_repositary.findById(patient.getMrn());
            return patient1.get();
        }else{
            return null;
        }
    }

    public String change_from_normal_to_vip(Integer mrn){
        Optional<Patient> patient1 = patient_repositary.findById(mrn);

        if(patient1.isPresent()){
            if(patient1.get().getFamily() != null){
                Optional<Category> category = category_repositary.findById(2);
                patient1.get().getFamily().setCategory(category.get());
                patient_repositary.save(patient1.get());
                return "Patient family tree change from normal to Vip";
            }
            else{
                return "Patient is not in any Family";
            }
        }else {
            return "Patient not Found";
        }

    }

    public String change_from_vip_to_normal(Integer mrn){
        Optional<Patient> patient1 = patient_repositary.findById(mrn);

        if(patient1.isPresent()){
            if(patient1.get().getFamily() != null){
                Optional<Category> category = category_repositary.findById(1);
                patient1.get().getFamily().setCategory(category.get());
                patient_repositary.save(patient1.get());
                return "Patient family tree change from vip to normal";
            }
            else{
                return "Patient is not in any Family";
            }
        }else {
            return "Patient not Found";
        }

    }

    public Family_Tree search_family(Integer id){
        Optional<Family_Tree> family_tree = family_repositary.findById(id);
        if (family_tree.isPresent()){
            return family_tree.get();
        }
        return null;
    }

    public String add_new_family(Integer id){
        Optional<Patient> patient = patient_repositary.findById(id);

        if (patient.isPresent()){
            if(patient.get().getFamily() == null){
                Family_Tree newfamily = new Family_Tree();
                patient.get().setRelationship_family(Patient.relation.Owner);
                Optional<Category> category = category_repositary.findById(1);
                newfamily.setCategory(category.get());
                newfamily.getMembers().add(patient.get());
                patient.get().setFamily(newfamily);
                patient_repositary.save(patient.get());
                return "Patient is Add to new Family";
            }else {
                return "Patient is Already in Family";
            }
        }else {
            return "Patient not Found";
        }
    }

    @Transactional
    public String add_in_family(Integer mrn,Integer family_id,Patient.relation relation_family_owner){

        Optional<Patient> patient = patient_repositary.findById(mrn);
        if(patient.isPresent()){
            Optional<Family_Tree> family = family_repositary.findById(family_id);
            if(family.isPresent()){
                if(patient.get().getFamily() == null){
                    family.get().getMembers().add(patient.get());
                    family.get().setNo_family_member(family.get().getNo_family_member()+1);
                    patient.get().setFamily(family.get());
                    patient.get().setRelationship_family(relation_family_owner);
                    patient_repositary.save(patient.get());
                    return "Patient mrn = " +patient.get().getMrn() +" is added to family id = "+patient.get().getFamily().getFamily_id() + " with realtion of = " + relation_family_owner;
                }else {
                    return "patient is already in family";
                }
            }else {
                return "Family not found";
            }
        }
        return "Patient not Found";
    }

    public String remove_from_family(Integer mrn,Integer family_id){

        Optional<Patient> patient = patient_repositary.findById(mrn);
        if(patient.isPresent()){
            Optional<Family_Tree> family = family_repositary.findById(family_id);
            if(family.isPresent()){
                if(patient.get().getFamily() != null){
                    if(patient.get().getFamily().getFamily_id() == family_id){
                        if (patient.get().getRelationship_family() != Patient.relation.Owner){
                            family.get().setNo_family_member(family.get().getNo_family_member()-1);
                            family.get().getMembers().remove(patient.get());
                            patient.get().setFamily(null);
                            patient.get().setRelationship_family(null);
                            patient_repositary.save(patient.get());
                            return "Patient mrn = " +patient.get().getMrn() +" is removed from family id = " + family_id ;
                        }else{
                            return "patient is owner of family";
                        }
                    }else {
                        return "patient is not in that family";
                    }
                }else {
                    return "patient is not in any family";
                }
            }else {
                return "Family not found";
            }
        }
        return "Patient not Found";
    }

}
