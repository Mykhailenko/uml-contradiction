package com.uml.contradiction.converter.core;

import java.io.File;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

public interface CoreParser {

	public List<Object> parse(String filename);
}

