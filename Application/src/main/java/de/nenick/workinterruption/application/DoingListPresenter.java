package de.nenick.workinterruption.application;

import android.view.MenuItem;

import de.nenick.workinterruption.application.functions.DeleteDoingFunction;
import de.nenick.workinterruption.application.functions.GetDoingListFunction;

public class DoingListPresenter {

    private GetDoingListFunction getDoingListFunction = new GetDoingListFunction();
    private DeleteDoingFunction deleteDoingFunction = new DeleteDoingFunction();

    private DoingListActivity view;

    public DoingListPresenter(DoingListActivity view) {
        this.view = view;
    }

    public void loadDoingList() {
        getDoingListFunction.apply(view, view.getListView());
    }

    public void deleteDoing(MenuItem item) {
        deleteDoingFunction.apply(item, view.getContentResolver());
    }
}
