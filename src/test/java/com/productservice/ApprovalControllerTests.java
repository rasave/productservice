package com.productservice;

import com.productservice.controller.ApprovalController;
import com.productservice.entity.Approval;
import com.productservice.service.ApprovalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class ApprovalControllerTests {

	@Mock
	ApprovalService approvalService;

	@InjectMocks
	private ApprovalController approvalController;

	@BeforeEach
	public void init() {
		openMocks(this);
	}

	@Test
	void testGetAllApprovals() {
		List<Approval> approvals = getApprovals();
		when(approvalService.getAllPendingApprovals()).thenReturn(approvals);
		List<Approval> approvals1 = approvalController.getAllPendingApprovals().getBody();
		assertEquals(approvals.size(), approvals1.size());
	}

	@Test
	void testApprove() {
		when(approvalService.approve(1)).thenReturn(1);
		assertEquals(HttpStatus.OK, approvalController.approve(1).getStatusCode());
	}

	@Test
	void testReject() {
		when(approvalService.reject(1)).thenReturn(1);
		assertEquals(HttpStatus.OK, approvalController.reject(1).getStatusCode());
	}

	private List<Approval> getApprovals(){
		Approval a1 = new Approval();
		a1.setId(1);
		List<Approval> approvals = Arrays.asList(a1);
		return approvals;
	}

}
