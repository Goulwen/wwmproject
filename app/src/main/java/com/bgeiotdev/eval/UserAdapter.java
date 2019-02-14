package com.bgeiotdev.eval;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bgeiotdev.eval.data.bdd.User;
import com.bgeiotdev.eval.util.ColorUtils;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private static final String TAG = UserAdapter.class.getSimpleName();

    private final ListItemClickListener mOnClickListener;

    private static int viewHolderCount;
    private List<User> listeUser;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public UserAdapter(ListItemClickListener listener) {
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.activity_page_scoring_view_holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        UserViewHolder userViewHolder = new UserViewHolder(view);

        userViewHolder.viewNom.setText("Nom\n");
        userViewHolder.viewPrenom.setText("Prénom\n");
        userViewHolder.viewEmail.setText("Email\n");
        userViewHolder.viewScore.setText("Score\n");

        // Changer le background des view
        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        userViewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);
        viewHolderCount++;

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, int position) {
        Log.d(TAG, "position #" + position);
        userViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {

        return listeUser.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView viewClassement;
        TextView viewNom;
        TextView viewPrenom;
        TextView viewEmail;
        TextView viewScore;

        public UserViewHolder(View itemView) {
            super(itemView);

            viewClassement = (TextView) itemView.findViewById(R.id.viewClassement);
            viewNom = (TextView) itemView.findViewById(R.id.viewNom);
            viewPrenom = (TextView) itemView.findViewById(R.id.viewPrenom);
            viewEmail = (TextView) itemView.findViewById(R.id.viewEmail);
            viewScore = (TextView) itemView.findViewById(R.id.viewScore);
            itemView.setOnClickListener(this);
        }

        // Affiche position
        void bind(int listIndex) {
            User user = listeUser.get(listIndex);
            viewClassement.setText(String.valueOf(listIndex + 1));

            viewNom.setText(user.getNom());
            viewPrenom.setText(user.getPrenom());
            viewEmail.setText(user.getEmail());
            viewScore.setText(""+user.getScore());
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
