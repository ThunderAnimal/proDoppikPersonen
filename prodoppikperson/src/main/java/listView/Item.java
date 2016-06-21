package listView;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by martin_w on 16.06.2015.
 */
public interface Item {
    public int getViewType();
    public View getView(LayoutInflater inflater, View convertView);
}
