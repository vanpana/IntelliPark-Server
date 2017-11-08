package com.cyberschnitzel.smartspark.view;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

public class PopupNotification{

    public PopupNotification(String title, String caption) {
        Notification notification = new Notification(
                title);
        notification
                .setDescription(caption);
        notification.setHtmlContentAllowed(true);
        notification.setStyleName("tray dark small closable login-help");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.show(Page.getCurrent());
    }

    public PopupNotification(String title, String caption, int delay) {
        Notification notification = new Notification(
                title);
        notification
                .setDescription(caption);
        notification.setHtmlContentAllowed(true);
        notification.setStyleName("tray dark small closable login-help");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(delay);
        notification.show(Page.getCurrent());
    }
}
