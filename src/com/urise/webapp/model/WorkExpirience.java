package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class WorkExpirience implements Serializable {
    private static final long serialVersionUID = 1L;

    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final String vacancy;
    private final String text;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkExpirience that = (WorkExpirience) o;
        return startDate.equals(that.startDate) && finishDate.equals(that.finishDate) && vacancy.equals(that.vacancy)
                && text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, finishDate, vacancy, text);
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
}

