package com.example.administrator.everread.Activity.book;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.everread.MyAdapterBookInfo;
import com.example.administrator.everread.R;

/**
 * Created by Administrator on 2017/4/15.
 */
public class InfoBookActivity extends Activity {

    private RecyclerView mRecyclerView;

    private HomeAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book);


        mRecyclerView = (RecyclerView) findViewById(R.id.info_book);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeAdapter();
        mRecyclerView.setAdapter(mAdapter);

        //为RecyclerView添加HeaderView和FooterView
        setHeaderView(mRecyclerView);
        setFooterView(mRecyclerView);
    }

    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(this).inflate(R.layout.header_info, view, false);
        mAdapter.setHeaderView(header);
    }

    private void setFooterView(RecyclerView view){
        View footer = LayoutInflater.from(this).inflate(R.layout.footer_info, view, false);
        mAdapter.setFooterView(footer);
    }



    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ListHolder>
    {

        public static final int TYPE_HEADER = 0;  //说明是带有Header的
        public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
        public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的


        private View mHeaderView;
        private View mFooterView;

        private String[] infoBook={"从印染厂的实习生到纽约时装皇后\n" +
                "\n" +
                "从嫁给王子到独自创建时尚品牌\n" +
                "\n" +
                "她用一条裹身裙成为了她想成为的女人\n" +
                "\n" +
                "“继可可•香奈儿后时尚界最具市场号召力的女性”\n" +
                "\n" +
                "《福布斯》杂志2012全球时尚界最有影响力女性\n" +
                "\n" +
                "Diane von Furstenberg品牌创始人\n" +
                "\n" +
                "美国时装设计师协会（ CFDA）主席首部亲笔完整自传\n" +
                "\n" +
                "近百幅珍贵照片首次公开\n" +
                "\n" +
                "FaceBook首席运营官 雪莉•桑德伯格\n" +
                "\n" +
                "《Vogue》杂志美国版主编 安娜•温图尔\n" +
                "\n" +
                "《欲望都市》主演 莎拉•杰西卡•帕克"
                ,"巴金（1904年11月25日－2005年10月17日）之一，"
                ,"1.《激流》总序\n" +
                "2.家\n" +
                "3.附录"};

        private String[] intro={"简介","作者简介","目录"};

        //HeaderView和FooterView的get和set函数
        public View getHeaderView() {
            return mHeaderView;
        }
        public void setHeaderView(View headerView) {
            mHeaderView = headerView;
            notifyItemInserted(0);
        }
        public View getFooterView() {
            return mFooterView;
        }
        public void setFooterView(View footerView) {
            mFooterView = footerView;
            notifyItemInserted(getItemCount()-1);
        }

        /** 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    * */
        @Override
        public int getItemViewType(int position) {
            if (mHeaderView == null && mFooterView == null){
                return TYPE_NORMAL;
            }
            if (position == 0){
                //第一个item应该加载Header
                return TYPE_HEADER;
            }
            if (position == getItemCount()-1){
                //最后一个,应该加载Footer
                return TYPE_FOOTER;
            }
            return TYPE_NORMAL;
        }

        @Override
        public ListHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            if(mHeaderView != null && viewType == TYPE_HEADER) {
                return new ListHolder(mHeaderView);
            }
            if(mFooterView != null && viewType == TYPE_FOOTER){
                return new ListHolder(mFooterView);
            }
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_info, parent, false);
            return new ListHolder(layout);

        }

        @Override
        public void onBindViewHolder(ListHolder holder, int position)
        {
            if(getItemViewType(position) == TYPE_NORMAL){
                if(holder instanceof ListHolder) {
                    holder.tv_idea.setText(intro[position-1]);
                    holder.tv_name.setText(infoBook[position-1]);
                   // holder.imageView.setImageResource(mDatas.get(position-1).getImage());
                    // return;
                }
                return;
            }else if(getItemViewType(position) == TYPE_HEADER){
                return;
            }else{
                if(holder.recyclerView.getAdapter()==null) {

                    holder.recyclerView.setAdapter(new MyAdapterBookInfo());
                }else {
                    holder.recyclerView.getAdapter().notifyDataSetChanged();
                }
                return;
            }

        }

        @Override
        public int getItemCount()
        {
            if(mHeaderView == null && mFooterView == null){
                return 3;
            }else if(mHeaderView == null && mFooterView != null){
                return 3 + 1;
            }else if (mHeaderView != null && mFooterView == null){
                return 3 + 1;
            }else {
                return 3 + 2;
            }
        }

        class ListHolder extends RecyclerView.ViewHolder
        {

            TextView tv_idea;
            TextView tv_name;
           RecyclerView recyclerView;

            public ListHolder(View view)
            {
                super(view);
                //如果是headerview或者是footerview,直接返回
                if (itemView == mHeaderView){
                    return;
                }
                if (itemView == mFooterView){
                    recyclerView= (RecyclerView) view.findViewById(R.id.footer_info);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext());
                    layoutManager.canScrollHorizontally();
                    recyclerView.setLayoutManager(layoutManager);
                    return;
                }
                tv_idea = (TextView) view.findViewById(R.id.intro);
                tv_name= (TextView) view.findViewById(R.id.content_intro);

            }
        }
    }
}
