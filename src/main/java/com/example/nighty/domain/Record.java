package com.example.nighty.domain;

public class Record {
    private Long id;

    private Long userId;

    private String date;

    private String sleepTime;

    private String wakeupTime;

    private String emotion;

    private String dream;

    private String evaluation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getWakeupTime() {
        return wakeupTime;
    }

    public void setWakeupTime(String wakeupTime) {
        this.wakeupTime = wakeupTime;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getDream() {
        return dream;
    }

    public void setDream(String dream) {
        this.dream = dream;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", date=").append(date);
        sb.append(", sleepTime=").append(sleepTime);
        sb.append(", wakeupTime=").append(wakeupTime);
        sb.append(", emotion=").append(emotion);
        sb.append(", dream=").append(dream);
        sb.append(", evaluation=").append(evaluation);
        sb.append("]");
        return sb.toString();
    }
}