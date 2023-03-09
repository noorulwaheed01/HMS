package com.example.FGPC_HMS.Repositary;

import com.example.FGPC_HMS.Model.Family_Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Family_repositary extends JpaRepository<Family_Tree, Integer> {
}
