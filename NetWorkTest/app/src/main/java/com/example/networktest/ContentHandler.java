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
    private StringBuilder name;
    private StringBuilder version;
    private StringBuilder result;
    private SAXListener mListener;

    public ContentHandler(SAXListener listener) {
        super();
        mListener = listener;
    }

    //XML的SAX解析方式
    //在开始XML解析的时候调用
    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
        result = new StringBuilder();
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
        }else if("name".equals(nodeName)) {
            name.append(ch, start, length);
        }else if("version".equals(nodeName)) {
            version.append(ch, start, length);
        }
    }

    //完成解析某个节点的时候调用
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("app".equals(localName)) {
            //需要去除两端空格
            result.append(id.toString().trim());
            result.append(name.toString().trim());
            result.append(version.toString().trim());
            result.append("\n");
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    //完成整个XML解析的时候调用
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        mListener.onEndDocument(result.toString());
    }

    public interface SAXListener {
        void onEndDocument(String res);
    }
}
