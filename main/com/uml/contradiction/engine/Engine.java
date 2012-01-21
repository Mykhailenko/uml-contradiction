package com.uml.contradiction.engine;

import com.uml.contradiction.engine.model.Criterion;
import com.uml.contradiction.engine.model.Quantifier;
import com.uml.contradiction.engine.model.VerificationResult;

public class Engine {
	private Criterion criterion;
	public Engine(Criterion criterion) {
		this.criterion = criterion;
	}
	public VerificationResult verify(){
		for(int i = 0; i < criterion.getQuantifiers().size(); ++i){
			try{
				
			}catch(Exception e){
				
			}
		}
		return null;
	}
}
