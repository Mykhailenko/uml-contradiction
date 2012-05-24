package com.uml.contradiction.engine.model.predicate;

import java.util.List;

import com.uml.contradiction.engine.model.predicate.exception.PredicatException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.sequence.Message;

public class MessageVisibility implements Predicate {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean predict(List list) throws PredicatException {
		assert list != null;
		assert list.size() == 2;
		Object first = list.get(0);
		if ((first instanceof Message) == false) {
			throw new PredicatException("Unexpected type: "
					+ first.getClass().toString());
		}
		Object second = list.get(1);
		if ((second instanceof CClass) == false) {
			throw new PredicatException("Unexpected type: "
					+ second.getClass().toString());
		}
		Message message = (Message) first;
		CClass cClass = (CClass) second;
		MMethod m = null;
		for (MMethod method : cClass.getMethods()) {
			if (MessageBelongToClass.messageBelongToMethod(message, method)) {
				m = method; 
				break;
			}
		}
		if(m != null){
			switch (m.getVisibility()) {
			case PUBLIC:
				return true;
			case PACKAGE:
				return isBothClassesInSamePackege(message.getSource().getcClass(), cClass);
			case PRIVATE:
			case PROTECTED:
			default:
				return false;
			}
		}else{
			return false;
		}
	}

	private boolean isBothClassesInSamePackege(CClass cl0, CClass cl1) {
		if(cl0 != null && cl1 != null){
			if(cl0 instanceof CClass &&
					cl1 instanceof CClass){
				if(cl0.getPackageName() != null &&
						cl1.getPackageName() != null){
					return cl0.getPackageName().equals(cl1.getPackageName());
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public String getFailDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
