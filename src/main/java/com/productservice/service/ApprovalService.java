package com.productservice.service;

import com.google.common.collect.Lists;
import com.productservice.entity.Approval;
import com.productservice.entity.Product;
import com.productservice.repository.ApprovalQueueRepository;
import com.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ApprovalService {
    Logger log = LoggerFactory.getLogger(ApprovalService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApprovalQueueRepository approvalQueueRepository;

    public void approvalRequest(Product product ){
        Approval approval = new Approval();
        approval.setProductId(product.getId());
        approval.setRequestedDate(product.getPostedDate());
        approvalQueueRepository.save(approval);
    }

    public int approve(Integer approvalId){
        Approval approval = approvalQueueRepository.findById(approvalId).orElse(null);
        if(approval != null){
            approvalQueueRepository.deleteById(approvalId);
            return productRepository.updateStatus("approved", approval.getProductId());
        }
        log.info("No pending approval found to approve for approvalId : ", approvalId);
        return 0;
    }

    public int reject(Integer approvalId) {
        Approval approval = approvalQueueRepository.findById(approvalId).orElse(null);
        if (approval != null) {
            approvalQueueRepository.deleteById(approvalId);
            return productRepository.updateStatus("rejected", approval.getProductId());
        }
        log.info("No pending approval found to reject for approvalId : ", approvalId);
        return 0;
    }

    public List<Approval> getAllPendingApprovals(){
        return Lists.newArrayList(approvalQueueRepository.findAll());
    }
}
