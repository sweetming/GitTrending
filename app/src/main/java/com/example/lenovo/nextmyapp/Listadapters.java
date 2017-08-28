package com.example.lenovo.nextmyapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anye.greendao.gen.UserDao;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/6.
 */
public class Listadapters extends BaseAdapter {

    private  UserDao mUserDao=MyApplication.getinstances().getDaoSession().getUserDao();
     private List<EntityMyapp> list;
    private Context context;
    private LayoutInflater inflater;
    private List<ImageView> ivlist=new ArrayList<>();
    private final String htttps="https://github.com/";

public void setList(List<EntityMyapp> list){
    this.list=list;
}
    public Listadapters(List<EntityMyapp>list,Context context){
        this.list=list;
        this.context=context;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        if(list!=null) {
            return list.size();
        }else {
        return 0;}
    }

    @Override
    public Object getItem(int i) {
        if(list!=null){
        return list.get(i);}else {
        return null;}
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
            view=inflater.inflate(R.layout.items,null);
            holder.bt=(TextView) view.findViewById(R.id.lv_bt);
            holder.jj=(TextView) view.findViewById(R.id.lv_jj);

            holder.iv1= ((ImageView) view.findViewById(R.id.iv_1));
            holder.iv2= ((ImageView) view.findViewById(R.id.iv_2));
            holder.iv3= ((ImageView) view.findViewById(R.id.iv_3));
            holder.iv4= ((ImageView) view.findViewById(R.id.iv_4));
            holder.iv5= ((ImageView) view.findViewById(R.id.iv_5));
            holder.b_b=(TextView) view.findViewById(R.id.b_b);
            holder.btn=(TextView) view.findViewById(R.id.ittem_sc);

            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User findUser = mUserDao.queryBuilder().where(UserDao.Properties.Bt.eq(holder.bt.getText())).build().unique();
                    if(findUser==null){
                        User user=new User();
                        user.setBt(holder.bt.getText()+"");
                        user.setJs(holder.jj.getText()+"");
                        String ass=(holder.bt.getText()).toString().trim().replace(" ","");
                        user.setWz(htttps+ass);
                         mUserDao.insert(user);
                    holder.btn.setBackgroundResource(R.drawable.ic_star);

                   }else {
                        holder.btn.setBackgroundResource(R.drawable.ic_unstar_navbar);
                        mUserDao.deleteByKey(findUser.getId());


                    }
                }
            });
            view.setTag(holder);
        }else {
            holder=(ViewHolder) view.getTag();
        }

        EntityMyapp entity = (EntityMyapp) getItem(i);

        if (entity.bt != null) {
            holder.bt.setText(entity.bt.toString()+"");
        }
        if (entity.js != null) {
            holder.jj.setText(entity.js.toString()+"");
        }

        if(entity.list!=null){
            ivlist.clear();
            ivlist.add(holder.iv1);
            ivlist.add(holder.iv2);
            ivlist.add(holder.iv3);
            ivlist.add(holder.iv4);
            ivlist.add(holder.iv5);
for (int count=0;count<entity.list.size();count++){
    Glide.with(context).load(entity.list.get(count)).into(ivlist.get(count));
}
            User findUser = mUserDao.queryBuilder().where(UserDao.Properties.Bt.eq(holder.bt.getText())).build().unique();

            if(findUser==null){

                holder.btn.setBackgroundResource(R.drawable.ic_unstar_navbar);

            }else {
                holder.btn.setBackgroundResource(R.drawable.ic_star);
                ;

            }

        }

return view;

    }
    class ViewHolder{
        public TextView bt;
        public TextView jj;
        private TextView star;
        public TextView b_b;
        private ImageView iv1,iv2,iv3,iv4,iv5;
        private TextView btn;
    }
}
