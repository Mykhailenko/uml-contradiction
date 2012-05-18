package com.uml.contradiction.converter.core;

import java.util.List;

import org.w3c.dom.Element;

public interface CoreParser {

	public List<Object> parse(Element umlModelEl);

	public void makeResult();
}
