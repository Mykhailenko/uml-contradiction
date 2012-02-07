package com.uml.contradiction.converter.core;

import java.io.File;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import com.uml.contradiction.gui.models.DiagramForChoise;

public interface CoreParser {

	public List<Object> parse(DiagramForChoise diagrForSearch, Element umlModelEl);
}

