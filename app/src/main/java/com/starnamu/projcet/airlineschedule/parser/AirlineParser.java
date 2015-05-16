package com.starnamu.projcet.airlineschedule.parser;

import android.os.Handler;
import android.util.Log;

import com.starnamu.projcet.airlineschedule.comm.CommonConventions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class AirlineParser implements CommonConventions {

    Element[] element = new Element[PARSERITEMGROUP.length];
    String[] itemStr = new String[PARSERITEMGROUP.length];
    ArrayList<AirlineItem> TempList;
    ArrayList<AirlineItem> itemLists;
    String DepartureAirlineRequest;
    String ArrivalAirlineRequest;
    Handler handler;
    String Url;
    String ADstate;


    public AirlineParser(String UrlD, String UrlA) {
        this.DepartureAirlineRequest = UrlD;
        this.ArrivalAirlineRequest = UrlA;
        handler = new Handler();
        TempList = new ArrayList<>();
        ParserThread thread = new ParserThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ParserThread extends Thread {
        int i = 0;

        public void run() {
            Url = URLHADE + DepartureAirlineRequest + SERVICEKEY;
            DAurl(Url, "D");

            Url = URLHADE + ArrivalAirlineRequest + SERVICEKEY;
            DAurl(Url, "A");
        }

        private void DAurl(String Url, String state) {
            ADstate = state;
            try {
                URL url = new URL(Url);
                InputStream inStream = url.openStream();
                i++;
                Log.i("몇번 실행", Integer.toString(i));
                airportparser(inStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<AirlineItem> getArrayList() {
        return itemLists;
    }

    public void airportparser(InputStream inStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(inStream);
        itemLists = parserDocument(document);
    }

    public ArrayList<AirlineItem> parserDocument(Document document) {
        Element element = document.getDocumentElement();
        element.getElementsByTagName("item");
        NodeList nodeList = element.getElementsByTagName("item");

        if (nodeList != null) {
            for (int i = 1; i < nodeList.getLength(); i++) {
                if (parseItemNode(nodeList, i) != null) {
                    AirlineItem item = parseItemNode(nodeList, i);
                    TempList.add(item);
                }
            }
        }
        return TempList;
    }

    private AirlineItem parseItemNode(NodeList nodeList, int index) {
        Element elem = (Element) nodeList.item(index);
        for (int i = 1; i < PARSERITEMGROUP.length; i++) {
            element[i] = (Element) elem.getElementsByTagName(PARSERITEMGROUP[i]).item(0);
            if (element[i] == null) {
                itemStr[i] = " ";
            } else if (element[i] != null) {
                Node firstchild = element[i].getFirstChild();
                if (firstchild != null) {
                    itemStr[i] = firstchild.getNodeValue();
                }
            }
        }
      /*  if (true) {
            itemStr[0] = "A";
        } else if (false) {
            itemStr[0] = "D";
        }
*/
        AirlineItem item = new AirlineItem(itemStr);
        return item;
    }
}