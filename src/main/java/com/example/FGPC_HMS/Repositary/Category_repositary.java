package com.example.FGPC_HMS.Repositary;

import com.example.FGPC_HMS.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Category_repositary extends JpaRepository<Category,Integer> {
}
