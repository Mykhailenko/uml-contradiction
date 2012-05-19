package com.uml.contradiction.converter.core;

import java.util.List;

import org.w3c.dom.Element;

public interface CoreParser {

	List<Object> parse(Element umlModelEl);

	void makeResult();
}
