package com.backend.server.model.repository;


import com.backend.server.api.admin.dto.category.AdminCommonCategoryResponse;
import com.backend.server.model.entity.InquiryType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InquiryTypeRepository extends JpaRepository<InquiryType, Long> {

    @Query("SELECT new com.backend.server.api.admin.dto.category.AdminCommonCategoryResponse" +
            "(p.id, p.name, p.description, COUNT(u), p.createdAt) " +
            "FROM InquiryType p LEFT JOIN p.inquiries u " +
            "GROUP BY p.id")
    List<AdminCommonCategoryResponse> getInquiryTypeList();
}
