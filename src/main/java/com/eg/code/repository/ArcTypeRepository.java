package com.eg.code.repository;

import com.eg.code.entity.ArcType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 资源类型Repository
 */
public interface ArcTypeRepository extends JpaRepository<ArcType,Integer>, JpaSpecificationExecutor<ArcType> {
}
