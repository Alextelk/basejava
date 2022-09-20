package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            write(dos, contacts.entrySet(), entry -> {
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(entry.getValue());
                    }
            );
            Map<SectionType, AbstractSection> sections = r.getSections();
            write(dos, sections.entrySet(), entry -> {
                SectionType sectiontype = entry.getKey();
                dos.writeUTF(sectiontype.name());
                switch (sectiontype) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) entry.getValue()).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = ((ListSection) entry.getValue()).getText();
                        write(dos, list, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizationList = ((OrganizationSection) entry.getValue()).getOrganizations();
                        write(dos, organizationList, org -> {
                            dos.writeUTF(org.getOrganization());
                            dos.writeUTF(org.getHomePage().getUrl());
                            List<Organization.WorkExpirience> periods = org.getWorkExpirience();
                            write(dos, periods, per -> {
                                writeLocalDate(dos, per.getStartDate());
                                writeLocalDate(dos, per.getFinishDate());
                                dos.writeUTF(per.getVacancy());
                                dos.writeUTF(per.getText());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readOne(dis, () -> resume.setContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readOne(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        TextSection textSection = new TextSection();
                        textSection.setText(dis.readUTF());
                        resume.setSections(sectionType, textSection);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = new ListSection(read(dis, dis::readUTF));
                        resume.setSections(sectionType, listSection);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = new OrganizationSection();
                        readOne(dis, () -> {
                            String organizationName = dis.readUTF();
                            String url = dis.readUTF();
                            readOne(dis, () -> {
                                Organization organization = new Organization(organizationName, url, readLocalDate(dis)
                                        , readLocalDate(dis), dis.readUTF(), dis.readUTF());
                                organizationSection.setOrganizationsList(organization);
                            });
                        });
                        resume.setSections(sectionType, organizationSection);
                        break;
                }
            });
            return resume;
        }
    }


    private <T> void write(DataOutputStream dos, Collection<T> collection, Writer<T> writer) throws IOException {
        Objects.requireNonNull(collection);
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private <T> List<T> read(DataInputStream dis, Reader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private void readOne(DataInputStream dis, SimpleReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
        dos.writeInt(localDate.getDayOfMonth());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }

    private interface Writer<T> {
        void write(T t) throws IOException;
    }

    private interface Reader<T> {
        T read() throws IOException;
    }

    private interface SimpleReader {
        void read() throws IOException;
    }
}

