package com.danielpm1982.springboot2meetingmng.domain;

public class Event {
    private Long id;
    private String name;
    private String theme;
    private String outfit;
    private String host;
    private Integer attendance;
    private String details;
    public Event() {
    }
    public Event(String name, String theme, String outfit, String host, Integer attendance) {
        this.name = name;
        this.theme = theme;
        this.outfit = outfit;
        this.host = host;
        this.attendance = attendance;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public String getOutfit() {
        return outfit;
    }
    public void setOutfit(String outfit) {
        this.outfit = outfit;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public Integer getAttendance() {
        return attendance;
    }
    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", theme='" + theme + '\'' +
                ", outfit='" + outfit + '\'' +
                ", host='" + host + '\'' +
                ", attendance=" + attendance +
                ", details='" + details + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id.equals(event.id);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
