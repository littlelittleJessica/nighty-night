package com.example.nighty.Resp;

public class RecordResp {
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
        final StringBuilder sb = new StringBuilder("RecordResp{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", date='").append(date).append('\'');
        sb.append(", sleepTime='").append(sleepTime).append('\'');
        sb.append(", wakeupTime='").append(wakeupTime).append('\'');
        sb.append(", emotion='").append(emotion).append('\'');
        sb.append(", dream='").append(dream).append('\'');
        sb.append(", evaluation='").append(evaluation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}