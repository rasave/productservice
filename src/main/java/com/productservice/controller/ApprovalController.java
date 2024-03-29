package com.productservice.controller;

import com.productservice.entity.Approval;
import com.productservice.service.ApprovalService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/api/products/approval-queue")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @RequestMapping(method = GET)
    public ResponseEntity<List<Approval>> getAllPendingApprovals() {
        List<Approval> resourceWithUrls = approvalService.getAllPendingApprovals();
        return new ResponseEntity<>(resourceWithUrls, OK);
    }

    @RequestMapping(value = "/{approvalId}/approve", method = PUT)
    public ResponseEntity approve(@PathVariable("approvalId") Integer approvalId) {
        approvalService.approve(approvalId);
        return new ResponseEntity<>(OK);
    }

    @RequestMapping(value = "/{approvalId}/reject", method = PUT)
    public ResponseEntity reject(@PathVariable("approvalId") Integer approvalId) {
        approvalService.reject(approvalId);
        return new ResponseEntity<>(OK);
    }
}
