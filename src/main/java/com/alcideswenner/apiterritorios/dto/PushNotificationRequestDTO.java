package com.alcideswenner.apiterritorios.dto;

public class PushNotificationRequestDTO {
    private Notification notification;
    private String priority;
    private Data data;
    private String to;

    public static class Data {
        private String clickaction;
        private String id;
        private String status;

        public String getClickaction() {
            return clickaction;
        }

        public String getId() {
            return id;
        }

        public String getStatus() {
            return status;
        }

        public void setClickaction(String clickaction) {
            this.clickaction = clickaction;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class Notification {
        private String body;
        private String title;

        public String getBody() {
            return body;
        }

        public String getTitle() {
            return title;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
