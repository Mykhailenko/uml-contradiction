package com.uml.contradiction.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import static org.junit.Assert.*;


@SuiteClasses({EngineTest.class, MustExistClassCriterionTest.class, CorrectTypeCriterionTest.class})
@RunWith(Suite.class)
public class TestSuite {
	public void test(){
		assertTrue(true);
	}
}
