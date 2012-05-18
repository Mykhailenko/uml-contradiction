package com.uml.contradiction.engine;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.CriterionSuite;

public class RunCriterions {
	private static final Logger LOGGER = Logger.getRootLogger();

	public List<VerificationResult> runAll() {
		return run(CriterionSuite.getAllCriterion());
	}

	public List<VerificationResult> run(List<Criterion> criterions) {
		List<CriterionRunner> criterionRunners = new LinkedList<CriterionRunner>();
		try {
			for (Criterion criterion : criterions) {
				CriterionRunner criterionRunner = new CriterionRunner(criterion);
				criterionRunners.add(criterionRunner);
				criterionRunner.start();
			}
			for (CriterionRunner criterionRunner : criterionRunners) {
				criterionRunner.join();
			}
		} catch (InterruptedException e) {
			LOGGER.error("OLOLO " + e);
		}
		List<VerificationResult> result = new LinkedList<VerificationResult>();
		for (CriterionRunner criterionRunner : criterionRunners) {
			result.add(criterionRunner.getResult());
		}
		return result;
	}
}
