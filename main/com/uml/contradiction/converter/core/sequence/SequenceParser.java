package com.uml.contradiction.converter.core.sequence;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.uml.contradiction.converter.core.CoreParser;
import com.uml.contradiction.converter.core.CoreParserImpl;
import com.uml.contradiction.model.sequence.Interaction;
import com.uml.contradiction.model.sequence.InteractionElement;
import com.uml.contradiction.model.sequence.LifeLine;
import com.uml.contradiction.model.sequence.Message;
import com.uml.contradiction.model.sequence.SequenceGraph;

//������������ ��� ��������������� ��������� xmi ���������, 
//����������� � uml ��������� �������������������, � �������������� ������� ������, ������������� �� Java
public class SequenceParser extends CoreParserImpl implements CoreParser {
	private static final Logger LOGGER = Logger.getRootLogger();

	//������ �����������: �������� - ������� ������, ���� - ��� ������������� � xmi ���������
	private Map<String, Interaction> interactionsFrWithId = new LinkedHashMap<String, Interaction>();
	private Map<String, Interaction> interactsSequenceWithId = new LinkedHashMap<String, Interaction>();
	private Map<String, LifeLine> lifelinesWithId = new LinkedHashMap<String, LifeLine>();
	private Map<String, LifeLine> fragmentsWithLifeln = new LinkedHashMap<String, LifeLine>();
	private Map<String, Message> messagesWithId = new LinkedHashMap<String, Message>();

	SequenceParsHelper sequenceParsHelper; // �������� ��������� ��� ������� ��������

	public SequenceParser() {
		super();

		sequenceParsHelper = new SequenceParsHelper(interactionsFrWithId,
				lifelinesWithId, fragmentsWithLifeln, messagesWithId);
	}

	@Override
	public List<Object> parse(Element umlModelEl) {
		try {
			NodeList sequencesFrame = umlModelEl
					.getElementsByTagName("ownedBehavior");
			int temp;
			// ������� ��� sequence, ���� ��������� ������ � ��������� �������
			// � � ������ ���������
			for (temp = 0; temp < sequencesFrame.getLength(); temp++) {
				// ������ ������ ��������
				Element curSequenceEl = (Element) sequencesFrame.item(temp);

				@SuppressWarnings("unused")
				Interaction oneOfmainInter = parseFrame(curSequenceEl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ���������� ������������ Sequence ���������- ��� ownedBehavior ��� frame
	public Interaction parseFrame(Element frameEl) {
		Interaction curInteraction = null;
		String idFrameEl;

		try { // �������� ��� ��������� sequence �������
			if (frameEl.getAttribute("xmi:type").equals("uml:Interaction")) {
				curInteraction = new Interaction();

				idFrameEl = frameEl.getAttribute("xmi:id");

				String tagName = frameEl.getTagName();
				if (tagName.equals("ownedBehavior")) {
					interactsSequenceWithId.put(idFrameEl, curInteraction);
				}

				interactionsFrWithId.put(idFrameEl, curInteraction);
				curInteraction.setName(frameEl.getAttribute("name"));

				sequenceParsHelper.parseAllLifelines(frameEl);

				NodeList includedLifelines = frameEl
						.getElementsByTagName("lifeline");
				// �������� � ���������� ���������� �������
				for (int k = 0; k < includedLifelines.getLength(); k++) {
					Element lifelineEl = (Element) includedLifelines.item(k);

					// �������� ��� ����� ������������� ������ ����������������
					// ��������
					if (lifelineEl.getParentNode() == frameEl) { 
						// �������� ��� ����� Lifeline
						if (lifelineEl.getAttribute("xmi:type").equals(
								"uml:Lifeline")) {
							// �������� LifeLine
							LifeLine curLifeLine = lifelinesWithId
									.get(lifelineEl.getAttribute("xmi:id"));
							
							if (curLifeLine != null) {
								List<LifeLine> lifnes = curInteraction
										.getLifeLines();
								if (lifnes == null) {
									lifnes = new LinkedList<LifeLine>();
									curInteraction.setLifeLines(lifnes);
								}
								lifnes.add(curLifeLine); // ���������� k ������
															// ����� �����
							} else {
								LOGGER.error("Not exist lifeline in frame");
							}
						}
					}
				}

				// NodeList events = frameEl.getElementsByTagName("fragment");
				// //�������� � ���������� ����������� Events
				// for (int k = 0; k < events.getLength(); k++) {
				// Element eventEl = (Element)events.item(k);
				//
				// //�������� ��� ����� ������������� ������ ����������������
				// ��������
				// if(eventEl.getParentNode() == frameEl)
				// {
				// //�������� Event
				// Event curEvent = sequenceParsHelper.parseEvent(eventEl);
				// eventsWithId.put(eventEl.getAttribute("xmi:id"), curEvent);
				// }
				// }
				sequenceParsHelper.parseAllInlaidEvents(frameEl);

				// ����� ������������������
				// ������� ���� �� ���� ��������� ��������� ������
				// � �������� � �������� ��� � �����������
				NodeList childsOfFrameEl = frameEl.getChildNodes();

				for (int i = 0; i < childsOfFrameEl.getLength(); i++) {

					if (childsOfFrameEl.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element includeInterInFrame = (Element) childsOfFrameEl
								.item(i);

						// �������� ��� ����� ������������� ������
						// ���������������� ��������
						if (includeInterInFrame.getParentNode() == frameEl) {

							// �������� � ���������� ��������
							if (includeInterInFrame.getTagName()
									.equals("frame")) {
								List<InteractionElement> childs = curInteraction
										.getChilds();

								if (childs == null) {
									childs = new LinkedList<InteractionElement>();
									curInteraction.setChilds(childs);
								}
								childs.add(parseFrame(includeInterInFrame));
							} else {
								// �������� � ����������
								if (includeInterInFrame.getTagName().equals(
										"message")) {
									if (includeInterInFrame.getAttribute(
											"xmi:type").equals("uml:Message")) {
										List<InteractionElement> childs = curInteraction
												.getChilds();

										if (childs == null) {
											childs = new LinkedList<InteractionElement>();
											curInteraction.setChilds(childs);
										}
										Message mess = sequenceParsHelper
												.parseMessage(includeInterInFrame);
										if (mess != null) {
											childs.add(mess);
											messagesWithId
													.put(includeInterInFrame
															.getAttribute("xmi:id"),
															mess);
										}
									}
								}
							}

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curInteraction;
	}

	@Override
	public void makeResult() {
		sequenceParsHelper.checkedConnections();

		Collection<Interaction> allFrames = interactionsFrWithId.values();
		List<Interaction> interList = SequenceGraph.getInteractions();

		for (Interaction intr : allFrames) {
			interList.add(intr);
		}
	}

}
