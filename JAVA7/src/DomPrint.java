import java.io.*;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

class DomPrint {
    private static void printIndention (int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
    }
    private static void print(Node node, int level){
        if (level > 0) {
            System.out.println("");
            printIndention(level);
        }
        System.out.println(node.getNodeName().toString() + ":");
        if (node.hasAttributes()) {

            NamedNodeMap attributes = node.getAttributes();
            if (attributes.getLength() > 0) {
                level ++;

                for(int i = 0; i < attributes.getLength(); i++){
                    Node attribute = attributes.item(i);
                    printIndention(level);
                    String s ="." + attribute.getNodeName() + "=";
                    System.out.println(s + attribute.getNodeName());
                }
                level--;
            }
        }
        String value = node.getNodeValue();
        value = (value == null ? "" : value.trim());
        if (value.length() > 0) {
            printIndention(level);
            System.out.println(value);
        }
        if (node.hasChildNodes()) {
            level++;
            NodeList children = node.getChildNodes();

            for (int i = 0; i < children.getLength(); i++){
                Node child = children.item(i);
                print (child, level);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1){
            System.out.println("Usage: java DomPrint имя_файла_xml");
            System.exit(0);
        }
        File docFile = new File(args[0]);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(docFile);
            print(doc.getDocumentElement(), 0);
        } catch(Exception ex){
            System.out.println(ex);
        }
    }

}

