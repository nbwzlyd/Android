package adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by lyd10892 on 2016/5/18.
 */
public class AminTouchHelperCallBack extends ItemTouchHelper.Callback {


    private moveItemListener mListener = null;

    public AminTouchHelperCallBack(moveItemListener listener) {
        mListener = listener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (mListener != null) {
            mListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (mListener != null) {
            mListener.onSwiped(viewHolder.getAdapterPosition());
        }

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (viewHolder instanceof ItemSelectChangedListener) {
            ItemSelectChangedListener listener = (ItemSelectChangedListener) viewHolder;
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                listener.onSelectChanged();
            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof ItemSelectChangedListener) {
            ItemSelectChangedListener listener = (ItemSelectChangedListener) viewHolder;
            listener.onSelectChangedFinish();
        }
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    public interface moveItemListener {
        void onMove(int fromPos, int targetPos);

        void onSwiped(int position);


    }

    public interface ItemSelectChangedListener {
        void onSelectChanged();

        void onSelectChangedFinish();
    }
}
