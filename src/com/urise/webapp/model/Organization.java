package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final String link;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final String vacancy;
    private final String text;

    public Organization(String link, String name, LocalDate startDate, LocalDate finishDate, String vacancy,
                        String text) {
        this.link = link;
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.vacancy = vacancy;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return link.equals(that.link) && name.equals(that.name) && startDate.equals(that.startDate) &&
                finishDate.equals(that.finishDate) && vacancy.equals(that.vacancy) && text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, name, startDate, finishDate, vacancy, text);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startDate +
                ", finishTime=" + finishDate +
                ", vacancy='" + vacancy + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
