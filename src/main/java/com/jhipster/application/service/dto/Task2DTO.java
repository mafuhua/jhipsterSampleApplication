package com.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Task2 entity.
 */
public class Task2DTO implements Serializable {

    private Long id;

    private Integer appId;

    private String name;

    private String desce;

    private String wxAppid;

    private String title;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }

    public String getWxAppid() {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task2DTO task2DTO = (Task2DTO) o;
        if(task2DTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), task2DTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Task2DTO{" +
            "id=" + getId() +
            ", appId=" + getAppId() +
            ", name='" + getName() + "'" +
            ", desce='" + getDesce() + "'" +
            ", wxAppid='" + getWxAppid() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
