package com.planetsystems.tela.managementapp.client.presenter.reports.nationalperformace;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
    import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.planetsystems.tela.managementapp.client.presenter.main.MainPresenter;
import com.planetsystems.tela.managementapp.client.widget.ControlsPane;
import com.planetsystems.tela.managementapp.client.widget.MenuButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.planetsystems.tela.managementapp.client.place.NameTokens;
public class NationalPerformacePresenter extends Presenter<NationalPerformacePresenter.MyView, NationalPerformacePresenter.MyProxy>  {
    interface MyView extends View  {
    	
    	public ControlsPane getControlsPane();

    	public VLayout getContentPane();
    }
    @ContentSlot
    public static final Type<RevealContentHandler<?>> SLOT_NationalPerformace = new Type<RevealContentHandler<?>>();

    @NameToken(NameTokens.nationalperformace)
    @ProxyCodeSplit
    interface MyProxy extends ProxyPlace<NationalPerformacePresenter> {
    }

    @Inject
    NationalPerformacePresenter(
            EventBus eventBus,
            MyView view, 
            MyProxy proxy) {
        super(eventBus, view, proxy, MainPresenter.SLOT_Main);
        
    }
    
    protected void onBind() {
        super.onBind(); 
        loadMenuButtons();
    }
    
    private void loadMenuButtons() {
		MenuButton filter = new MenuButton("Filter");
		MenuButton refresh = new MenuButton("Refresh");
		MenuButton export = new MenuButton("Export");

		List<MenuButton> buttons = new ArrayList<>();
		buttons.add(filter);
		buttons.add(refresh);
		buttons.add(export);

		getView().getControlsPane().addMenuButtons("National Performance", buttons);

		showFilter(filter);

	}

	private void showFilter(final MenuButton button) {
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				final Menu menu = new Menu();

				MenuItem item1 = new MenuItem("End of Week Time Attendance Reports");
				MenuItem item2 = new MenuItem("End of Month Time Attendance Reports");
				MenuItem item3 = new MenuItem("End of Term Time Attendance Reports");

				menu.setItems(item1, item2, item3);

				menu.showNextTo(button, "bottom");

			}
		});
	}
    
}