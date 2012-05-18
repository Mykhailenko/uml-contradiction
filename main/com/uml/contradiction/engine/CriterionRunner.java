package com.uml.contradiction.engine;

import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;

public class CriterionRunner extends Thread {
	private Criterion criterion;
	private VerificationResult result = null;

	public CriterionRunner(Criterion criterion) {
		this.criterion = criterion;
	}

	@Override
	public void run() {
		Checker engine = new Checker(criterion);
		result = engine.verify();
	}

	public VerificationResult getResult() {
		return result;
	}
}
