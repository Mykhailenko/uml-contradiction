package com.uml.contradiction.exporters;

import java.util.List;

import com.uml.contradiction.engine.model.VerificationResult;

public interface Exporter {
	void export(List<VerificationResult> verificationResults) throws Exception;
}
