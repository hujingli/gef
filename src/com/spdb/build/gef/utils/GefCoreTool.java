package com.spdb.build.gef.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GefCoreTool {

	public static XStream getXStream() {
		XStream xStream = new XStream(new DomDriver());
		xStream.setMode(XStream.ID_REFERENCES);
		
		return xStream;
	}
}
