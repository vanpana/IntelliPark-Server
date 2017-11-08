package com.cyberschnitzel.smartspark.component;

import java.util.Arrays;

import com.cyberschnitzel.smartspark.domain.Entities.Employee;
import com.cyberschnitzel.smartspark.domain.User;
import com.vaadin.annotations.PropertyId;
import com.cyberschnitzel.smartspark.event.DashboardEvent.CloseOpenWindowsEvent;
import com.cyberschnitzel.smartspark.event.DashboardEvent.ProfileUpdatedEvent;
import com.cyberschnitzel.smartspark.event.DashboardEventBus;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;

@SuppressWarnings("serial")
public class ProfilePreferencesWindow extends Window {

    public static final String ID = "profilepreferenceswindow";

    private final BeanFieldGroup<Employee> fieldGroup;
    /*
     * Fields for editing the User object are defined here as class members.
     * They are later bound to a FieldGroup by calling
     * fieldGroup.bindMemberFields(this). The Fields' values don't need to be
     * explicitly set, calling fieldGroup.setItemDataSource(user) synchronizes
     * the fields with the user object.
     */
    private Employee emp;
    @PropertyId("Name")
    private TextField nameField;
    @PropertyId("Surname")
    private TextField surnameField;
    @PropertyId("email")
    private TextField emailField;
    @PropertyId("password")
    private TextField passwordField;
    @PropertyId("carplate")
    private TextField carplateField;
    @PropertyId("neighbourhood")
    private ListSelect<String> neighbourhoodField;

    @PropertyId("employdate")
    private TextField employdateField;
    @PropertyId("multiplier")
    private TextField multiplierField;

    private ProfilePreferencesWindow(final Employee emp,
            final boolean preferencesTabOpen) {
        this.emp = emp;

        addStyleName("profile-window");
        setId(ID);
        Responsive.makeResponsive(this);

        setModal(true);
        setCloseShortcut(KeyCode.ESCAPE, null);
        setResizable(false);
        setClosable(false);
        setHeight(90.0f, Unit.PERCENTAGE);

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        content.setMargin(new MarginInfo(true, false, false, false));
        content.setSpacing(false);
        setContent(content);

        TabSheet detailsWrapper = new TabSheet();
        detailsWrapper.setSizeFull();
        detailsWrapper.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        detailsWrapper.addStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
        detailsWrapper.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
        content.addComponent(detailsWrapper);
        content.setExpandRatio(detailsWrapper, 1f);

        detailsWrapper.addComponent(buildProfileTab());
        detailsWrapper.addComponent(buildPreferencesTab());

        if (preferencesTabOpen) {
            detailsWrapper.setSelectedTab(1);
        }

        content.addComponent(buildFooter());

        fieldGroup = new BeanFieldGroup<Employee>(Employee.class);
        fieldGroup.bindMemberFields(this);
        fieldGroup.setItemDataSource(emp);
    }

    private Component buildPreferencesTab() {
        VerticalLayout root = new VerticalLayout();
        root.setCaption("Preferences");
        root.setIcon(FontAwesome.COGS);
        root.setSpacing(true);
        root.setMargin(true);
        root.setSizeFull();

        Label message = new Label("Not implemented in this demo");
        message.setSizeUndefined();
        message.addStyleName(ValoTheme.LABEL_LIGHT);
        root.addComponent(message);
        root.setComponentAlignment(message, Alignment.MIDDLE_CENTER);

        return root;
    }

    private Component buildProfileTab() {
        HorizontalLayout root = new HorizontalLayout();
        root.setCaption("Profile");
        root.setIcon(FontAwesome.USER);
        root.setWidth(100.0f, Unit.PERCENTAGE);
        root.setMargin(true);
        root.addStyleName("profile-form");

        VerticalLayout pic = new VerticalLayout();
        pic.setSizeUndefined();
        pic.setSpacing(true);
        Image profilePic = new Image(null,
                new ThemeResource("img/profile-pic-300px.jpg"));
        profilePic.setWidth(100.0f, Unit.PIXELS);
        pic.addComponent(profilePic);

        Button upload = new Button("Change…", new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Not implemented in this demo");
            }
        });
        upload.addStyleName(ValoTheme.BUTTON_TINY);
        pic.addComponent(upload);

        root.addComponent(pic);

        FormLayout details = new FormLayout();
        details.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        root.addComponent(details);
        root.setExpandRatio(details, 1);

        nameField = new TextField("Name");
        nameField.setValue(emp.getName());
        details.addComponent(nameField);

        surnameField = new TextField("Surname");
        surnameField.setValue(emp.getSurname());
        details.addComponent(surnameField);

        emailField =  new TextField("Email");
        emailField.setValue(emp.getEmail());
        details.addComponent(emailField);

        passwordField =  new TextField("Password");
        passwordField.setValue(emp.getPassword());
        details.addComponent(passwordField);

        carplateField =  new TextField("Car Plate");
        carplateField.setValue(emp.getCar_plate());
        details.addComponent(carplateField);

        neighbourhoodField =  new ListSelect<>("Neighbourhood");
        neighbourhoodField.setItems("Centru", "Manastur", "Marasti", "Buna Ziua", "Mihai Viteazu");
        neighbourhoodField.select(emp.getNeighbourhood());
        neighbourhoodField.setRows(5);
        details.addComponent(neighbourhoodField);

        return root;
    }

    private Component buildFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footer.setWidth(100.0f, Unit.PERCENTAGE);
        footer.setSpacing(false);

        Button ok = new Button("OK");
        ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
        ok.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    fieldGroup.commit();
                    // Updated user should also be persisted to database. But
                    // not in this demo.

                    Notification success = new Notification(
                            "Profile updated successfully");
                    success.setDelayMsec(2000);
                    success.setStyleName("bar success small");
                    success.setPosition(Position.BOTTOM_CENTER);
                    success.show(Page.getCurrent());

                    DashboardEventBus.post(new ProfileUpdatedEvent());
                    close();
                } catch (CommitException e) {
                    Notification.show("Error while updating profile",
                            Type.ERROR_MESSAGE);
                }

            }
        });
        ok.focus();
        footer.addComponent(ok);
        footer.setComponentAlignment(ok, Alignment.TOP_RIGHT);
        return footer;
    }

    public static void open(final Employee emp,
            final boolean preferencesTabActive) {
        DashboardEventBus.post(new CloseOpenWindowsEvent());
        Window w = new ProfilePreferencesWindow(emp, preferencesTabActive);
        UI.getCurrent().addWindow(w);
        w.focus();
    }
}
