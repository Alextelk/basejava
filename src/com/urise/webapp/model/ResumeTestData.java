package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume1 = new Resume("uuid1", "Григорий Кислин");
        resume1.setContacts(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume1.setContacts(ContactType.SKYPE, "skype:grigory.kislin");
        resume1.setContacts(ContactType.EMAIL, "gkislin@yandex.ru");

        resume1.setSections(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume1.setSections(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        ListSection listSection1 = new ListSection();
        listSection1.addToList("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        listSection1.addToList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        resume1.setSections(SectionType.ACHIEVEMENT, listSection1);

        ListSection listSection2 = new ListSection();
        listSection2.addToList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        listSection2.addToList("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        listSection2.addToList("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        listSection2.addToList("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        resume1.setSections(SectionType.QUALIFICATIONS, listSection2);

        OrganizationSection expirience = new OrganizationSection();
        expirience.setOrganizationsList(new Organization("www.javaops.ru", "Java Online Project", LocalDate.of(2013, 10, 1), LocalDate.now(), "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок."));
        expirience.setOrganizationsList(new Organization("www.yota.ru", "Yota", LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 01), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));
        resume1.setSections(SectionType.EXPERIENCE, expirience);

        OrganizationSection education = new OrganizationSection();
        education.setOrganizationsList(new Organization("www.siemens.com", "Siemens AG", LocalDate.of(2005, 01, 01), LocalDate.of(2005, 04, 01), "Студент", "3 месяца обучения мобильным IN сетям (Берлин)"));
        education.setOrganizationsList(new Organization("www.alcatel.com", "Alcatel", LocalDate.of(1997, 9, 01), LocalDate.of(1998, 03, 01), "Студент", "6 месяцев обучения цифровым телефонным сетям (Москва)"));
        resume1.setSections(SectionType.EDUCATION, education);

        System.out.println(resume1.getFullName());
        for (Map.Entry<ContactType, String> pair : resume1.getContacts().entrySet()) {
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }

        for (Map.Entry<SectionType, AbstractSection> pair : resume1.getSections().entrySet()) {
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }
    }
}
