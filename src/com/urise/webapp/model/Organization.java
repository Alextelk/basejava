package com.urise.webapp.model;


import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;
    private List<WorkExpirience> description = new ArrayList<>();

    public Organization(String organizationName, String url, LocalDate localDate, LocalDate date, String s, String readUTF) {
    }

    public Organization(String name, String url, WorkExpirience... workExpirience) {
        this(new Link(name, url), Arrays.asList(workExpirience));
    }

    public Organization(Link homePage, List<WorkExpirience> description) {
        this.homePage = homePage;
        this.description = description;
    }

    public Organization() {
    }

    public Link getHomePage() {
        return homePage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, description);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", description=" + description +
                '}';
    }

    public List<WorkExpirience> getWorkExpirience() {
        return description;
    }

    public String getOrganization() {
        return homePage.getName();
    }

    public void setDescription(List<WorkExpirience> description) {
        this.description = description;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class WorkExpirience implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate finishDate;
        private String vacancy;
        private String text;

        public WorkExpirience() {
        }

        public WorkExpirience(LocalDate startDate, LocalDate finishDate, String vacancy, String text) {
            this.startDate = startDate;
            this.finishDate = finishDate;
            this.vacancy = vacancy;
            this.text = text;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getFinishDate() {
            return finishDate;
        }

        public String getVacancy() {
            return vacancy;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "Expirience{" +
                    "startDate=" + startDate +
                    ", finishDate=" + finishDate +
                    ", vacancy='" + vacancy + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WorkExpirience that = (WorkExpirience) o;
            return Objects.equals(startDate, that.startDate) &&
                    Objects.equals(finishDate, that.finishDate) &&
                    Objects.equals(vacancy, that.vacancy) &&
                    Objects.equals(text, that.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, finishDate, vacancy, text);
        }
    }


}
