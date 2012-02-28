package com.uml.contradiction.engine.model.mapping;

import java.util.List;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.InteractionElement;
import com.uml.contradiction.model.sequence.SequenceGraph;

public class MessagesForTransitionMapping implements Mapping {

	@Override
	public List map(List args) throws MappingException {
		
		for(Interaction interaction : SequenceGraph.getInteractions()){
			for(InteractionElement ie : interaction.getChilds()){
				if(ie.isMessage()){
					
				}
			}
		}
		return null;
	}

}
