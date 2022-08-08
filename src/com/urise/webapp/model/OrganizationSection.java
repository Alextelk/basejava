package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {

    private final List<Organization> organizationsList = new ArrayList<>();

    public OrganizationSection() {
        Objects.requireNonNull(organizationsList, "organizations must not be null");
    }

    public List<Organization> getOrganizations() {
        return organizationsList;
    }

    public void setOrganizationsList(Organization organization) {
        organizationsList.add(organization);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return organizationsList.equals(that.organizationsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationsList);
    }

    @Override
    public String toString() {
        return organizationsList.toString();
    }
}
