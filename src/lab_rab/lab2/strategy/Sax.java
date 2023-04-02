package lab_rab.lab2.strategy;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.stream.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Sax implements Strategy {

    public Student student = new Student();
    private String inputXml = "";
    private String resultXml = "";


    public Sax(String args0, String args1) {
        inputXml = args0;
        resultXml = args1;
    }

    private void initSax()  throws ParserConfigurationException, SAXException, IOException, XMLStreamException  {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(inputXml, getDefaultHandler());
        correctMark();
    }


    DefaultHandler defaultHandler = new DefaultHandler() {

        String lastname = "";
        String title = "";
        String mark = "";
        int countMarks = 0;
        double sum = 0;
        String avrg = "";
        String elem = "";

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            student.setLastName(lastname);
            if ((sum / countMarks) != Double.parseDouble(avrg)) {
                student.setAverageMark(sum / countMarks);
            } else student.setAverageMark(Double.parseDouble(avrg));
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            elem = qName;
            if (qName.equalsIgnoreCase("student")) {
                lastname = attributes.getValue("lastname");
            }

            title = attributes.getValue("title");
            mark = attributes.getValue("mark");


            if (title != null) {
                student.addSubject(title, Integer.parseInt(mark));
                sum += Double.parseDouble(mark);
                countMarks++;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            elem = "";
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (elem.equals("average")) {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    str.append(ch[start + i]);
                }
                avrg = str.toString();
            }
        }

    };

    public void correctMark() throws XMLStreamException, IOException {
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xsw = null;
        try {
            xsw = xof.createXMLStreamWriter(new FileWriter(resultXml));
            xsw.writeStartDocument();
            xsw.writeStartElement("student");
            xsw.writeAttribute("lastname", student.getLastName());

            for (Student.Subject subject : student.getSubjects()) {
                xsw.writeStartElement("subject");
                xsw.writeAttribute("title", subject.getTitle());
                xsw.writeAttribute("mark", String.valueOf(subject.getMark()));
                xsw.writeEndElement();
            }

            xsw.writeStartElement("average");
            xsw.writeCharacters(String.valueOf(student.getAverageMark()));
            xsw.writeEndElement();

            xsw.writeEndElement();
            xsw.writeEndDocument();
            xsw.flush();
        } catch (Exception e) {
            System.err.println("Unable to write the file: " + e.getMessage());
        }
    }


    public DefaultHandler getDefaultHandler() {
        return defaultHandler;
    }

    @Override
    public void analyze() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        this.initSax();
    }

    public class Student{
        String lastName;
        double average;
        ArrayList<Subject> subjects = new ArrayList<>();

        public void addSubject(String title,  int mark){
            Subject subject =  new Subject(title,mark);
            subjects.add(subject);
        }

        public Subject getSubject(int i) {
            return subjects.get(i);
        }

        public int getSubjectsCount(){
            return subjects.size();
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public double getAverageMark() {
            return average;
        }

        public void setAverageMark(double average) {
            this.average = average;
        }

        public ArrayList<Subject> getSubjects() {
            return subjects;
        }

        private class Subject{
            String title;
            int mark;

            public Subject(String title, int mark) {
                this.title = title;
                this.mark = mark;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getMark() {
                return mark;
            }

            public void setMark(int mark) {
                this.mark = mark;
            }
        }
    }
}
    