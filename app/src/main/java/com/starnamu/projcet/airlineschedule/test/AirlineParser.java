package com.starnamu.projcet.airlineschedule.test;

import android.content.Context;
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

    ArrayList<AirlineItem> itemLists;
    String AirlineRequest;

    Handler handler;
    Context mContext;

    public AirlineParser(String Url, Context context) {
        this.mContext = context;
        this.AirlineRequest = Url;
        handler = new Handler();
        onParseThread();
    }

    public void onParseThread() {
        Thread thread = new Thread(new Runnable() {
            int i = 0;

            public void run() {
                String Url = URLHADE + AirlineRequest + SERVICEKEY;
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
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void reFresh() {
        handler.post(new Runnable() {
            @Override
            public void run() {
            }
        });
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

        ArrayList<AirlineItem> itemList = new ArrayList<>();
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (parseItemNode(nodeList, i) != null) {
                    AirlineItem item = parseItemNode(nodeList, i);
                    itemList.add(item);
                }
            }
        }
        return itemList;
    }

    private AirlineItem parseItemNode(NodeList nodeList, int index) {
        Element elem = (Element) nodeList.item(index);
        for (int i = 0; i < PARSERITEMGROUP.length; i++) {
            element[i] = (Element) elem.getElementsByTagName(PARSERITEMGROUP[i]).item(0);

            if (element[i] != null) {
                Node firstchild = element[i].getFirstChild();
                if (firstchild != null) {
                    itemStr[i] = firstchild.getNodeValue();
                }
            }
        }
        AirlineItem item = new AirlineItem(itemStr);
        return item;
    }
}