package com.trilogyed.tasker.model;

import java.util.Objects;

public class TaskViewModel extends Task {

    private String advertisement;

    // getters and setters

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    // overriding methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskViewModel that = (TaskViewModel) o;
        return getId() == that.getId() &&
                getDescription().equals(that.getDescription()) &&
                getCreateDate().equals(that.getCreateDate()) &&
                getDueDate().equals(that.getDueDate()) &&
                Objects.equals(getCategory(), that.getCategory()) &&
                Objects.equals(getAdvertisement(), that.getAdvertisement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getCreateDate(), getDueDate(), getCategory(), getAdvertisement());
    }

    @Override
    public String toString() {
        return "TaskViewModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", dueDate=" + dueDate +
                ", category='" + category + '\'' +
                ", advertisement='" + advertisement + '\'' +
                '}';
    }

}
