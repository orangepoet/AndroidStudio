package studio.android.handler;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import studio.android.activity.NasaDailyActivity;

public class IotdHandler extends DefaultHandler {
	
	private static final String xml = "<root><news>Happy new Year!</news></root>";
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	}
	
	public void processFeed(){
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			Thread.sleep(NasaDailyActivity.FAKE_EXECUTE_TIME);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			parser = parserFactory.newSAXParser();

			InputSource is = new InputSource(new StringReader(xml));
			parser.parse(is, this);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}