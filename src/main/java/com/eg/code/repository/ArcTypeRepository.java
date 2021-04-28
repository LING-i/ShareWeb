package com.eg.code.repository;

import com.eg.code.entity.ArcType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 资源类型Repository
 */
public interface ArcTypeRepository extends JpaRepository<ArcType,Integer>, JpaSpecificationExecutor<ArcType> {

    @Query(value ="select * from arc_type where arc_type_name = ?1" ,nativeQuery = true)
    public ArcType getArcTypByName(String name);


}
