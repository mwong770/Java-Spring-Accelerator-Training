package com.trilogyed.tasker.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

public class TaskViewModel {

    private int id;

    @Size(min = 1, max = 255, message = "The size of description must be between {min} and {max} characters.")
    @NotBlank(message = "Please supply a value for description.")
    private String description;

    @NotBlank(message = "Please supply a value for create date.")
    private LocalDate createDate;

    @NotBlank(message = "Please supply a value for due date.")
    private LocalDate dueDate;

    @Size(min = 1, max = 50, message = "The size of category must be between {min} and {max} characters.")
    private String category;

    private String advertisement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

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
