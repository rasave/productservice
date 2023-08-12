package com.productservice.repository;

import com.productservice.entity.Approval;
import org.springframework.data.repository.CrudRepository;

public interface ApprovalQueueRepository extends CrudRepository<Approval, Integer> {

}
