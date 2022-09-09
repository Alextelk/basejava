package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private List<String> listResume = new ArrayList<>();

    public List<String> getText() {
        return listResume;
    }

    public void addToList(String text) {
        listResume.add(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return listResume.equals(that.listResume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listResume);
    }

    @Override
    public String toString() {
        return listResume.toString();
    }
}
