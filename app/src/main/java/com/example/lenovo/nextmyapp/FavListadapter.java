package com.example.lenovo.nextmyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anye.greendao.gen.UserDao;

import java.util.List;

/**
 * Created by lenovo on 2017/5/18.
 */
public class FavListadapter extends BaseAdapter {
    private UserDao mUserDao=MyApplication.getinstances().getDaoSession().getUserDao();
    private List<EntityMyFav> list;
    private Context context;
    private LayoutInflater inflater;
    private final String htttps="https://github.com/";

    public FavListadapter(List<EntityMyFav> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.fav_items,null);
            holder.tv1= ((TextView) view.findViewById(R.id.fav_item_tv1));
            holder.tv2= ((TextView) view.findViewById(R.id.fav_item_tv2));
            holder.tv3= ((TextView) view.findViewById(R.id.fav_item_tv3));
            holder.tv3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User findUser = mUserDao.queryBuilder().where(UserDao.Properties.Bt.eq(holder.tv1.getText())).build().unique();
                    if(findUser==null){
                        User user=new User();
                        user.setBt(holder.tv1.getText()+"");
                        user.setJs(holder.tv2.getText()+"");
                        String ass=(holder.tv1.getText()).toString().trim().replace(" ","");
                        user.setWz(htttps+ass);
                        mUserDao.insert(user);
                        holder.tv3.setBackgroundResource(R.drawable.ic_star);

                    }else {
                        holder.tv3.setBackgroundResource(R.drawable.ic_unstar_navbar);
                        mUserDao.deleteByKey(findUser.getId());


                    }
                }
            });
            view.setTag(holder);
        }else {
            holder=(ViewHolder) view.getTag();
        }
        EntityMyFav myfav=(EntityMyFav) getItem(i);
        holder.tv1.setText(myfav.bt);
        holder.tv2.setText(myfav.js);
        return view;
    }

    class ViewHolder{
        public TextView tv1,tv2,tv3;
    }
}
