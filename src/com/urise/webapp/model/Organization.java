package com.urise.webapp.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private List<WorkExpirience> description = new ArrayList<>();

    public Organization(Link homePage, List<WorkExpirience> description) {
        this.homePage = homePage;
        this.description = description;
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
}
