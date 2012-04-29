package com.uml.contradiction.exporters;

import com.uml.contradiction.engine.model.GeneralResult;

public interface Exporter {
	/**
	 * Save to current folder file named 'result'.
	 * @param verificationResults
	 * @throws Exception
	 */
	void export(GeneralResult generalResult) throws Exception;
}
