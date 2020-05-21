package me.devilsen.list;

import android.content.Context;
import androidx.annotation.MenuRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;

import me.devilsen.dialog.R;

/**
 * desc :
 * date : 2018/2/23
 *
 * @author : dongSen
 */
public class BottomListHelper implements DismissListener {

    private ListAdapter listAdapter;

    private BottomListInterface bottomListInterface;
    private ActionMenu menu;

    public BottomListHelper(BottomListInterface bottomListInterface) {
        this.bottomListInterface = bottomListInterface;
    }

    public int getLayoutRes() {
        return R.layout.layout_list_bottom_sheet;
    }

    public void bindView(Context context, View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_list_bottom_sheet);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new LineItemDecoration(context));

        recyclerView.setAdapter(listAdapter);

    }

    public void setData(Context context, @MenuRes int xmlRes) {
        if (menu == null) {
            menu = new ActionMenu(context);
        }else{
            menu.clear();
        }

        new MenuInflater(context).inflate(xmlRes, menu);

        if (listAdapter == null) {
            listAdapter = new ListAdapter();
        }
        listAdapter.setListData(menu);

        listAdapter.notifyDataSetChanged();

        listAdapter.setOnDismissListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        if (listAdapter == null) {
            listAdapter = new ListAdapter();
        }
        listAdapter.setItemClickListener(itemClickListener);
    }

    @Override
    public void onDismiss() {
        bottomListInterface.dismissDialog();
    }

    public void add(int id, int order, int textRes) {
        menu.add(0, id, order, textRes);
    }
}
