package com.chinamobile.workspace.ui.swipelistview;

public class BaseSwipeListViewListener implements SwipeListViewListener {
    public void onOpened(int position, boolean toRight) {
    }

    
    public void onClosed(int position, boolean fromRight) {
    }

    
    public void onListChanged() {
    }

    
    public void onMove(int position, float x) {
    }

    
    public void onStartOpen(int position, int action, boolean right) {
    }

    
    public void onStartClose(int position, boolean right) {
    }

    
    public void onClickFrontView(int position) {
    }

    
    public void onClickBackView(int position) {
    }

    
    public void onDismiss(int[] reverseSortedPositions) {
    }

    
    public int onChangeSwipeMode(int position) {
        return SwipeListView.SWIPE_MODE_DEFAULT;
    }
}
