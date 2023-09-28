package ru.projectraid.activitys_shop;

public enum ActivityTable {

    LIKE_EVENT(1),
    COMMENT_EVENT(1),
    REPOST_EVENT(2),
    POLL_EVENT(1);

    public int activitiesCount = 0;
    ActivityTable(int activities) {
        this.activitiesCount = activities;
    }

}
