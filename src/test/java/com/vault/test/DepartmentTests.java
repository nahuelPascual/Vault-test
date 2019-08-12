package com.vault.test;

import com.vault.test.rest.dto.DepartmentDto;
import com.vault.test.service.DepartmentService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class DepartmentTests {

	private static final Logger LOGGER = Logger.getLogger(DepartmentTests.class);

	@Inject
	private DepartmentService departmentService;

	@Test
	public void createDepartment() {
		DepartmentDto departmentDto = new DepartmentDto();

		departmentDto.setDepartmentId(5555L);
		departmentDto.setDepartmentName("TestDept");
		departmentDto.setLocationId(2L);
		departmentDto.setManagerId(1L);

		try {
			departmentService.create(departmentDto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			Assert.fail();
		}

	}

}
