package com.jetbrains;

import javax.servlet.annotation.WebServlet;

import com.jetbrains.Controller.Controller;
import com.jetbrains.Repository.CarPoolRepository;
import com.jetbrains.Repository.NotificationRepository;
import com.jetbrains.Repository.Repository;
import com.jetbrains.Repository.VacationRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.*;
import com.vaadin.shared.Position;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;

@SuppressWarnings("serial")
public class LoginUI extends VerticalLayout {
    private Repository repo;
    //repo.add(repo.getEmployee("van.panaite@gmail.com"));
    private NotificationRepository notifrepo;
    private VacationRepository vacrepo;
    private CarPoolRepository cprepo;
    private Controller ctrl;

    public LoginUI() {
        //inits


        repo = new Repository("resources/myparking.db");
        notifrepo = new NotificationRepository("resources/myparking.db");
        vacrepo = new VacationRepository("resources/myparking.db");
        cprepo = new CarPoolRepository("resources/myparking.db");
        ctrl = new Controller(repo, notifrepo, vacrepo, cprepo);

        //UI init
        setSizeFull();
        setMargin(false);
        setSpacing(false);

        Component loginForm = buildLoginForm();
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
    }

    private Component buildLoginForm() {
        final VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setMargin(false);
        Responsive.makeResponsive(loginPanel);
        loginPanel.addStyleName("login-panel");

        loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        loginPanel.addComponent(new CheckBox("Remember me", true));
        return loginPanel;
    }

    private Component buildFields() {
        HorizontalLayout fields = new HorizontalLayout();
        fields.addStyleName("fields");

        final TextField username = new TextField("Username");
        username.setIcon(FontAwesome.USER);
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final PasswordField password = new PasswordField("Password");
        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final Button signin = new Button("Sign In");
        signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        signin.focus();

        fields.addComponents(username, password, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        signin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    if (ctrl.checkLogin(username.getValue(), password.getValue())) {
                        ServerView sv = new ServerView(ctrl);
                        getUI().setContent(sv);
                    }
                }
                catch(NullPointerException npe){
                    Notification notification = new Notification(
                            "Bad credentials");
                    notification
                            .setDescription("<span>Username or password is wrong!</span>");
                    notification.setHtmlContentAllowed(true);
                    notification.setStyleName("tray dark small closable login-help");
                    notification.setPosition(Position.BOTTOM_CENTER);
                    notification.setDelayMsec(20000);
                    notification.show(Page.getCurrent());
                }
//                DashboardEventBus.post(new UserLoginRequestedEvent(username
//                        .getValue(), password.getValue()));
            }});
        return fields;
    }

    private Component buildLabels() {
        CssLayout labels = new CssLayout();
        labels.addStyleName("labels");

        Label welcome = new Label("Welcome");
        welcome.setSizeUndefined();
        welcome.addStyleName(ValoTheme.LABEL_H4);
        welcome.addStyleName(ValoTheme.LABEL_COLORED);
        labels.addComponent(welcome);

//        Label title = new Label("QuickTickets Dashboard");
//        title.setSizeUndefined();
//        title.addStyleName(ValoTheme.LABEL_H3);
//        title.addStyleName(ValoTheme.LABEL_LIGHT);
//        labels.addComponent(title);
        return labels;
    }

}

//@Theme("mytheme")
//public class LoginUI extends UI {
//
//    /**
//     *
//     */
//    private static final long serialVersionUID = 1L;
//
//    /*
//     * The "Main method".
//     *
//     * This is the entry point method executed to initialize and configure the
//     * visible user interface. Executed on every browser reload because a new
//     * instance is created for each web page loaded.
//     */
//    @Override
//    protected void init(VaadinRequest request) {
//        System.out.println("Working Directory = " +
//                System.getProperty("user.dir"));
//        try{
//            Class.forName("org.sqlite.JDBC");
//        }
//        catch (ClassNotFoundException cnfe){
//            System.out.println(cnfe.getMessage());
//        }
//
//
//        Repository repo = new Repository("resources/myparking.db");
//        //repo.add(repo.getEmployee("van.panaite@gmail.com"));
//
//        NotificationRepository notifrepo = new NotificationRepository("resources/myparking.db");
//        VacationRepository vacrepo = new VacationRepository("resources/myparking.db");
//        CarPoolRepository cprepo = new CarPoolRepository("resources/myparking.db");
//
//
//        Controller ctrl = new Controller(repo, notifrepo, vacrepo, cprepo);
//
//        final VerticalLayout layout = new VerticalLayout();
//        layout.setMargin(true);
//        setContent(layout);
//
//        final TextField username = new TextField("Email");
//        final TextField password = new TextField("Password");
//        Button button = new Button("Login");
//
//        button.addClickListener(new Button.ClickListener() {
//            @Override
//
//            public void buttonClick(Button.ClickEvent event) {
//                if (ctrl.checkLogin(username.getValue(), password.getValue())){
//                    ServerView ui = new ServerView(ctrl);
//                    setContent(ui);
//                }
//
//            }
//        });
//
//        final Panel loginPanel = new Panel("Login...");
//        layout.addComponent(loginPanel);
//
//        final FormLayout loginForm = new FormLayout();
//        loginForm.setMargin(true);
//        loginForm.addComponent(username);
//        loginForm.addComponent(password);
//        loginForm.addComponent(button);
//
//        loginPanel.setContent(loginForm);
//        layout.setSizeFull();
//        layout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
//        loginPanel.setWidth(null);
//    }
//
//    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
//    @VaadinServletConfiguration(ui = LoginUI.class, productionMode = false)
//    public static class MyUIServlet extends VaadinServlet {
//
//        /**
//         *
//         */
//        private static final long serialVersionUID = 1L;
//    }
//}
