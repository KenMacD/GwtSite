package net.bugsquat.gwtsite.client;

import net.bugsquat.gwtsite.client.page.PageContainer;
import net.bugsquat.gwtsite.client.page.PageId;
import net.bugsquat.gwtsite.client.page.Pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;


public class PageLoader {
    private static final String LOADING_DIV_KEY = "loading-container";

    private static PageLoader instance;

    private PageContainer pageContainer;

	private Pages pages;

    private PageLoader() {
    	// NOTE: Deferred bind dictates what instance is to be created (module definition).
    	pages = GWT.create(Pages.class);
        pageContainer = new PageContainer();
        RootPanel.get().add(pageContainer);
    }

    public static PageLoader getInstance() {
        if (instance == null) {
            instance = new PageLoader();
        }
        return instance;
    }

    public PageContainer getPageContainer() {
        return pageContainer;
    }

    public void showLoadingPage() {
        RootPanel.get(LOADING_DIV_KEY).setVisible(true);
    }

    public void hideLoadingPage() {
        RootPanel.get(LOADING_DIV_KEY).setVisible(false);
    }

    public void loadPage(PageId pageId) {
        pageContainer.load(pages.getPage(pageId));
    }

}