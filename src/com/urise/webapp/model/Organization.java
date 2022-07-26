package com.urise.webapp.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

public class Organization {
    private final String link;
    private final String name;
    private final LocalDate startTime;
    private final LocalDate finishTime;
    private final String vacancy;
    private final String text;

    public Organization(String link, String name, LocalDate startTime, LocalDate finishTime, String vacancy, String text) {
        this.link = link;
        this.name = name;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.vacancy = vacancy;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return link.equals(that.link) && name.equals(that.name) && startTime.equals(that.startTime) && finishTime.equals(that.finishTime) && vacancy.equals(that.vacancy) && text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, name, startTime, finishTime, vacancy, text);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", vacancy='" + vacancy + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
