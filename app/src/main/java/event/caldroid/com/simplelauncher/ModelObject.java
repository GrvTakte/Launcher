package event.caldroid.com.simplelauncher;

/**
 * Created by gaurav on 11/05/17.
 */

public enum ModelObject {
    RED(R.string.home,R.layout.activity_home),
    BLUE(R.string.home1,R.layout.activity_home1),
    GREEN(R.string.home2, R.layout.activity_home2);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId){
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId(){
        return mTitleResId;
    }

    public int getLayoutResId(){
        return  mLayoutResId;
    }
}

