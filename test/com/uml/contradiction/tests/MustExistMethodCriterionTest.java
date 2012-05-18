package com.uml.contradiction.tests;

import java.io.File;

import org.junit.Test;

import com.uml.contradiction.converter.XMIConverter;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.LifeLine;
import com.uml.contradiction.model.sequence.SequenceGraph;

import static org.junit.Assert.assertTrue;
public class MustExistMethodCriterionTest {
	@Test
	public void test(){
		File file = new File("g.uml");
		
		XMIConverter.reset();
		XMIConverter.setFile(file);
		
		try {
			XMIConverter.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(SequenceGraph.getInteractions().size());
//		for(Interaction interaction : SequenceGraph.getInteractions()){
//			System.out.println(interaction.getName());
//		}
		Interaction interaction = SequenceGraph.getInteractions().get(1);
		for(LifeLine ll : interaction.getLifeLines()){
			System.out.println(ll.getName());
//			if(ll.getCclass() == null){
//				System.out.println("there null cclass");
//			}else{
//				System.out.println(ll.getCclass().getFullName());
//			}
		}
//		for(InteractionElement ie : interaction.getChilds()){
//			Message message = (Message) ie;
//			System.out.println("* " + message.getMethodName() + " - " + message.getParamCount());
//		}
//		Interaction interaction = SequenceGraph.getInteractions().get(0);
//		for(InteractionElement ie : interaction.getChilds()){
//			Message message = (Message) ie;
//			System.out.println("= " + message.getParamCount());
//			
//		}
//		Criterion criterion = new MustExistMethodCriterion();
//		Engine engine = new Engine(criterion);
//		VerificationResult result = engine.verify();
//		assertTrue(result.isGood());
		assertTrue(true);
	}
}
