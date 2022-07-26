package com.urise.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Мобильный"),
    SKYPE("Скайп"),
    EMAIL("Электронная почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    LINK("Home page");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
