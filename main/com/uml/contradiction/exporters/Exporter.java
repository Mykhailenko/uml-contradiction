package com.uml.contradiction.exporters;

public interface Exporter {
	/**
	 * Save to current folder file named 'result'.
	 * @param verificationResults
	 * @throws Exception
	 */
	void export(String [] verificationResults) throws Exception;
}
