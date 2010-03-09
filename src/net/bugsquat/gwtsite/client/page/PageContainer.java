package net.bugsquat.gwtsite.client.page;

import net.bugsquat.gwtsite.client.PageContainerLayout;
import net.bugsquat.gwtsite.client.PageLoader;
import net.bugsquat.gwtsite.client.layout.DefaultPageContainerLayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PageContainer extends Composite implements PageDataLoadedListener
{
    private Page<? extends PageController> currentPage;
    private PageContainerLayout content;

    public PageContainer()
    {
        content = GWT.create(PageContainerLayout.class);
        initWidget(content.getContent());
    }

    public void load(Page<? extends PageController> page)
    {
        // Indicate loading until the page loads its data.
        showPageLoading(true);

        page.setLoadDataListener(this);
        page.loadData();
    }

    /**
     * Shows/hides the loading indicator
     *
     * @param show
     *            when true, the indicator is shown, else it is hidden.
     */
    private void showPageLoading(boolean show)
    {
        if (show) {
            PageLoader.getInstance().showLoadingPage();
        } else {
            PageLoader.getInstance().hideLoadingPage();
        }
    }

    /**
     * Called after the current page's data has been loaded. Hides the loading
     * indicator and displays the current page.
     */
    @Override
    public void pageDataHasBeenLoaded(Page<? extends PageController> loadedPage)
    {
        if (currentPage != null)
        {
            currentPage.removeFromParent();
        }
        currentPage = loadedPage;

        showPageLoading(false);
        content.setPage(currentPage);
    }

}