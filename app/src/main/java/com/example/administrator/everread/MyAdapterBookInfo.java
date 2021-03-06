package com.example.administrator.everread;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/17.
 */
public class MyAdapterBookInfo extends RecyclerView.Adapter<MyAdapterBookInfo.ListHolder> {

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_footer, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
                  holder.pagernumber.setText("第238页");
                  holder.contentNote.setText("天真的人们能够爱——这就是他们的秘密");
                  holder.name_user.setText("华生");
                  holder.time_note.setText("一年以前");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ListHolder extends RecyclerView.ViewHolder {

        private TextView pagernumber;
        private TextView contentNote;
        private TextView name_user;
        private TextView time_note;
        public ListHolder(View itemView) {
            super(itemView);

            pagernumber= (TextView) itemView.findViewById(R.id.pager_number);
            contentNote= (TextView) itemView.findViewById(R.id.content_pen);
            name_user= (TextView) itemView.findViewById(R.id.name_reader);
            time_note= (TextView) itemView.findViewById(R.id.time_writer);
        }
    }
}
