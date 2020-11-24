package com.example.networktest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Created by zl on 19-10-24.
 */
public class ContentHandler extends DefaultHandler {

    private String nodeName;
    private StringBuilder id;

    //XML的SAX解析方式
    //在开始XML解析的时候调用
    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
    }

    //解析某个节点的时候调用
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //记录当前节点名
        nodeName = localName;
    }

    //获取节点内容的时候调用
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //根据当前节点判断将内容添加到哪一个StringBuilder中
        if("id".equals(nodeName)) {
            id.append(ch, start, length);
        }
    }

    //完成解析某个节点的时候调用
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("app".equals(localName)) {
            Log.d("TAG", "endElement: " + id);
            id.setLength(0);
        }
    }

    //完成整个XML解析的时候调用
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
