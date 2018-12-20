package com.iton.vaadinmongosample;


import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@Theme(Lumo.class)
@HtmlImport("frontend://styles/NewFile.html")

public class MainView extends VerticalLayout {

    public MainView() {
   
        SampleScreen screen = new SampleScreen();
        addClassName("mainclass");
        add(screen);
        screen.setPadding(false);
//        screen.setHeight("-1px");
        screen.getStyle().set( "height","auto" );
    setAlignItems(Alignment.STRETCH);
//        screen.setSizeUndefined();
        expand(screen);
        setSizeFull();
        setMargin(false);
        setPadding(false);
      setSpacing(false);
      setBoxSizing(BoxSizing.BORDER_BOX);
//      getElement().setAttribute("theme", Valo.Spacing.PADDING_M);
    }
}
