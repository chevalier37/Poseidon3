package com.nnk.springboot.serviceTEST;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointTest {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Autowired
	private CurvePointService curvePointService;

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void findAllCurvePointTest() {
		CurvePoint curvePoint = new CurvePoint(3, 5.0, 6.0);
		CurvePoint curvePoint1 = new CurvePoint(4, 6.0, 7.0);
		curvePointRepository.save(curvePoint);
		curvePointRepository.save(curvePoint1);

		List<CurvePoint> ListCurvePointExpected = new ArrayList<>();
		ListCurvePointExpected.add(curvePoint);
		ListCurvePointExpected.add(curvePoint1);

		List<CurvePoint> CurvePointList = curvePointService.findAllcurvePoint();

		assertEquals(ListCurvePointExpected.size(), CurvePointList.size());
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void addCurvePointTest() {
		CurvePoint curvePoint = new CurvePoint(3, 5.0, 6.0);
		curvePointService.addcurvePoint(curvePoint);

		assertEquals((Object) 3.0, curvePointRepository.findById(1).get().getCurveId());
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void getCurvePointByIdTest() {
		CurvePoint curvePoint = new CurvePoint(3, 5.0, 6.0);
		curvePointRepository.save(curvePoint);

		assertEquals((Object) 3, curvePointService.getCurvePointById(1).get().getCurveId());
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void deleteCurvePointTest() {
		CurvePoint curvePoint = new CurvePoint(3, 5.0, 6.0);
		curvePointRepository.save(curvePoint);

		curvePointService.deletecurvePoint(1);
		Optional<CurvePoint> curvePointOpt = curvePointRepository.findById(1);

		Assert.assertFalse(curvePointOpt.isPresent());
	}

}
