package com.uml.contradiction.converter;

//import com.uml.contradiction.converter.core.ClassParser;
import java.io.File;
import java.util.List;

import com.uml.contradiction.model.MetaData;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassDiagram;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.cclass.Dependency;
import com.uml.contradiction.model.cclass.Generalization;
import com.uml.contradiction.model.cclass.Realization;
import com.uml.contradiction.model.object.Link;
import com.uml.contradiction.model.object.OObject;
import com.uml.contradiction.model.object.ObjectDiagram;
import com.uml.contradiction.model.object.ObjectGraph;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.InteractionElement;
import com.uml.contradiction.model.sequence.LifeLine;
import com.uml.contradiction.model.sequence.Message;
import com.uml.contradiction.model.sequence.SequenceGraph;
import com.uml.contradiction.model.statemachine.StateMachine;
import com.uml.contradiction.model.statemachine.StateMachineGraph;

public class TestShell {
	public static void main(String[] argc) {
		// System.out.println("Hello world");

		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\5.JustTestClass.uml");
		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\10.compos.uml");
		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\composMultipl_Role.uml");
		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\N-arn association.uml");
		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\6.EntBean_Conrol in packNotRoot.uml");
		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\Test xmi.uml");
		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\8.ManyStereotypes_DefaultPack.uml");

//		 File file = new
//		 File("E:\\Programming\\Work_spaces\\workspace3_java\\4 ))) Test2 xmi.uml");
		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\5.2 ))) Test3 xmi.uml");
		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\7))) Seq_s from frame and package.uml");
		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\8.5. Nested class.uml");
		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\9.3 Realization without end.uml");
		// File file = new
		// File("E:\\Programming\\Work_spaces\\workspace3_java\\9.5 AssocClass.uml");

//		 File file = new File("D:\\Programming\\vpworkspace\\Experiments\\Sequence2.uml");
//		File file = new File("E:\\Programming\\Work_spaces\\workspace3_java\\ch.xmi");

//		File file2 = new File("g.uml");

		// File file = new File("main\\Test xmi.uml");
		 
		 File file = new File("E:\\Programming\\Work_spaces\\vpworkspace3\\Support\\xmi2.xmi");

		XMIConverter.reset();
		XMIConverter.setFile(file);

		try {
			XMIConverter.parse();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in parsing\n\n");
		}
		// �������� ClassDiagram
		printClassGraf();

		printObjectGraf();

		printSequenceGraf();

		printStateMachineGraf();

		MetaData.printMetaData();
	}

	public static void printClassGraf() {
		List<CClass> class_s = ClassGraph.getClasses();
		List<Association> asssoc_s = ClassGraph.getAssociations();
		List<ClassDiagram> clDiagrams = ClassGraph.getClassDiagrams();
		List<Dependency> depens = ClassGraph.getDependencies();
		List<Realization> reals = ClassGraph.getRealizations();
		List<Generalization> geners = ClassGraph.getGeneralizations();

		for (CClass cls : class_s) {
			// if(cls.getType() == VertexType.NARY)
			// System.out.println("Upi "+ cls);
			// if(cls.getClass() == AssociationClass.class)
			// System.out.println("Ololo "+ cls);

			System.out.println(cls);
		}

		for (Association ass : asssoc_s) {
			System.out.println(ass);
		}

		for (Dependency dep : depens) {
			System.out.println(dep);
		}

		for (Realization real : reals) {
			System.out.println(real);
		}

		for (Generalization gen : geners) {
			System.out.println(gen);
		}

		for (ClassDiagram clds : clDiagrams) {
			System.out.println(clds);
		}
	}

	public static void printObjectGraf() {
		List<OObject> object_s = ObjectGraph.getObjects();
		List<Link> link_s = ObjectGraph.getLinks();
		List<ObjectDiagram> objDiagrams = ObjectGraph.getObjectDiagrams();

		for (OObject obj : object_s) {
			System.out.println(obj);
		}

		for (Link lnk : link_s) {
			System.out.println(lnk);
		}

		for (ObjectDiagram obds : objDiagrams) {
			System.out.println(obds);
		}
	}

	public static void printSequenceGraf() {
		List<Interaction> interList = SequenceGraph.getInteractions();

		for (Interaction intr : interList) {
			System.out.println(intr);

			List<LifeLine> lifeLines = intr.getLifeLines();
			if (lifeLines != null) {
				System.out.println("lifelines::::");
				for (LifeLine lf : lifeLines) {
					System.out.println(lf);
				}
			}

			List<InteractionElement> inElems = intr.getChilds();
			if (inElems != null) {
				System.out.println("messages::::");

				for (InteractionElement inEL : inElems) {
					if (inEL.getClass() == Message.class) {
						System.out.println(inEL);
					}
				}
			}
		}
	}

	public static void printStateMachineGraf() {
		List<StateMachine> stateMach_s = StateMachineGraph.getStateMachines();

		for (StateMachine stm : stateMach_s) {
			System.out.println(stm);
		}
	}
}
