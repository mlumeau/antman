package org.ICE.PDC.antman;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({
	CaseTest.class, 
	FourmiTest.class, 
	FourmiliereTest.class,
	OuvriereTest.class
})

@RunWith(Suite.class)
public class TestRunner {}