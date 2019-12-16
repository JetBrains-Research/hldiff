package com.github.gumtreediff.gen.python;

import com.github.gumtreediff.gen.ExternalProcessTreeGenerator;
import com.github.gumtreediff.gen.Register;
import com.github.gumtreediff.gen.Registry;
import com.github.gumtreediff.io.LineReader;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.TreeContext;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.*;
import java.io.*;
import java.util.*;

@Register(id = "python-pythonparser", accept = {"\\.py$"}, priority = Registry.Priority.MAXIMUM)
public class PythonTreeGenerator extends ExternalProcessTreeGenerator {

    private static final String PYTHONPARSER_CMD = System.getProperty("gt.pp.path", "pythonparser");

    private static final QName LABEL = new QName("label");

    private static final QName POS = new QName("pos");

    private static final QName LENGTH = new QName("length");

    private static final QName TYPE = new QName("type");

    @Override
    public TreeContext generate(Reader r) throws IOException {
        LineReader lr = new LineReader(r);
        String output = readStandardOutput(lr);
        return getTreeContext(output);
    }

    public TreeContext getTreeContext(String xml) {
        XMLInputFactory fact = XMLInputFactory.newInstance();
        TreeContext context = new TreeContext();
        try {
            ArrayDeque<ITree> trees = new ArrayDeque<>();
            XMLEventReader r = fact.createXMLEventReader(new StringReader(xml));
            while (r.hasNext()) {
                XMLEvent ev = r.nextEvent();
                if (ev.isStartElement()) {
                    StartElement s = ev.asStartElement();
                    if (s.getName().getLocalPart().equals("tree")) {
                        String typeLabel = s.getAttributeByName(TYPE).getValue();
                        String label = "";
                        if (s.getAttributeByName(LABEL) != null)
                            label = s.getAttributeByName(LABEL).getValue();
                        int type = typeLabel.hashCode();
                        ITree t = context.createTree(type, label, typeLabel);
                        if (trees.isEmpty()) {
                            context.setRoot(t);
                        } else {
                            t.setParentAndUpdateChildren(trees.peekFirst());
                        }
                        setPos(t, s);
                        trees.addFirst(t);
                    } else {
                        continue;
                    }
                } else if (ev.isEndElement() && ev.asEndElement().getName().getLocalPart().equals("tree"))
                    trees.removeFirst();
            }
            context.validate();
            return context;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setPos(ITree t, StartElement e) {
        int pos = Integer.parseInt(e.getAttributeByName(POS).getValue());
        int length = Integer.parseInt(e.getAttributeByName(LENGTH).getValue());
        t.setPos(pos);
        t.setLength(length);
    }

    public String[] getCommandLine(String file) {
        return new String[]{PYTHONPARSER_CMD, file};
    }
}
