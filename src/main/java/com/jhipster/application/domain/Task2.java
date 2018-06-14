package com.jhipster.application.domain;


import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Task2.
 */
@Entity
@Table(name = "task_2")
@Document(indexName = "task2")
public class Task2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "app_id")
    private Integer appId;

    @Column(name = "name")
    private String name;

    @Column(name = "desce")
    private String desce;

    @Column(name = "wx_appid")
    private String wxAppid;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public Task2 appId(Integer appId) {
        this.appId = appId;
        return this;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public Task2 name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesce() {
        return desce;
    }

    public Task2 desce(String desce) {
        this.desce = desce;
        return this;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }

    public String getWxAppid() {
        return wxAppid;
    }

    public Task2 wxAppid(String wxAppid) {
        this.wxAppid = wxAppid;
        return this;
    }

    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid;
    }

    public String getTitle() {
        return title;
    }

    public Task2 title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Task2 description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task2 task2 = (Task2) o;
        if (task2.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), task2.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Task2{" +
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
