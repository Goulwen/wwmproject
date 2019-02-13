package com.bgeiotdev.eval;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bgeiotdev.eval.data.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private static final String TAG = UserAdapter.class.getSimpleName();

    private final ListItemClickListener mOnClickListener;

    private static int viewHolderCount;
    private  String strNom;
    private  String strPrenom;
    private  String strEmail;
    private int intScore;
    private List<User> listeUser;

    private int mNumberItems;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public UserAdapter(int numberOfItems, ListItemClickListener listener, String nom, String prenom, String email, int score) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
        strNom = nom;
        strPrenom = prenom;
        strEmail = email;
        intScore = score;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.activity_page_scoring_view_holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        UserViewHolder viewHolder = new UserViewHolder(view);

        viewHolder.viewHolderIndex1.setText("Nom\n" + strNom);
        viewHolder.viewHolderIndex2.setText("Prénom\n" + strPrenom);
        viewHolder.viewHolderIndex3.setText("Email\n" + strEmail);
        viewHolder.viewHolderIndex4.setText("Score\n" + intScore);

        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Log.d(TAG, "position #" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {

        return mNumberItems;
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView listItemNumberView;
        TextView viewHolderIndex1;
        TextView viewHolderIndex2;
        TextView viewHolderIndex3;
        TextView viewHolderIndex4;

        public UserViewHolder(View itemView) {
            super(itemView);

            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            viewHolderIndex1 = (TextView) itemView.findViewById(R.id.tv_view_holder_instance1);
            viewHolderIndex2 = (TextView) itemView.findViewById(R.id.tv_view_holder_instance2);
            viewHolderIndex3 = (TextView) itemView.findViewById(R.id.tv_view_holder_instance3);
            viewHolderIndex4 = (TextView) itemView.findViewById(R.id.tv_view_holder_instance4);
            itemView.setOnClickListener(this);
        }

        // Affiche position de 1 à 10
        void bind(int listIndex) {
            User user = listeUser.get(listIndex);
            listItemNumberView.setText(String.valueOf(listIndex + 1));

            viewHolderIndex1.setText(user.getNom());
            viewHolderIndex2.setText(user.getPrenom());
            viewHolderIndex3.setText(user.getEmail());
            viewHolderIndex4.setText(""+user.getScore());
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

    public void setListUser(List<User> users) {
        listeUser = users;
        notifyDataSetChanged();
    }
}
