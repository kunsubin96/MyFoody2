package com.example.kunsubin.foody.GridViewAnGi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kunsubin.foody.MainActivity;
import com.example.kunsubin.foody.Object.NhaHang;
import com.example.kunsubin.foody.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kunsubin on 4/10/2017.
 */
//đưa nội dung lên khung chính bên ăn gì
public class AdapterGridViewAnGi extends BaseAdapter{
    Context context;
    List<NhaHang> listNhaHang;
    private static LayoutInflater inflater=null;
    public AdapterGridViewAnGi(MainActivity mainActivity, List<NhaHang> listNhaHang) {
        this.listNhaHang=listNhaHang;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listNhaHang.size();
    }

    @Override
    public NhaHang getItem(int position) {
        return listNhaHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int i) {
        return i;
    }
    public class Holder
    {
        ImageView image_view_angi;
        TextView text_viewMonAn;
        TextView textViewNhaHang;
        TextView textViewDiaChi;
        CircleImageView imageAvatar;
        TextView nameUser;
        TextView ngayDang;
        public Holder(View view){
            this.image_view_angi=(ImageView)view.findViewById(R.id.image_view_angi);
            this.text_viewMonAn=(TextView) view.findViewById(R.id.text_viewMonAn);
            this.textViewNhaHang=(TextView)view.findViewById(R.id.textViewNhaHang);
            this.textViewDiaChi=(TextView)view.findViewById(R.id.textViewDiaChi);
            this.imageAvatar=(CircleImageView) view.findViewById(R.id.imageAvatar);
            this.nameUser=(TextView)view.findViewById(R.id.nameUser);
            this.ngayDang=(TextView)view.findViewById(R.id.ngayDang);

        }
        //show dữ liệu lên grid view tab Ăn Gi
        public void showNoiDung(NhaHang nhaHang){
            text_viewMonAn.setText(nhaHang.getMonChinh());
            textViewNhaHang.setText(nhaHang.getName());
            textViewDiaChi.setText(nhaHang.getDiaChi());
            if (nhaHang.getHinh()!= null) {
                Glide.with(context).load(nhaHang.getHinh()).into(image_view_angi);
            }
            if(nhaHang.getInfo()!=null){
                nameUser.setText(nhaHang.getInfo().getName());
                ngayDang.setText(nhaHang.getInfo().getDate());
                if(nhaHang.getInfo().getPhoto()!=null)
                    Glide.with(context).load(nhaHang.getInfo().getPhoto()).into(imageAvatar);
            }
        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        NhaHang item=listNhaHang.get(position);
        if(convertView==null){
            convertView=inflater.inflate(R.layout.nhahang_angi, null);
            holder=new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(Holder) convertView.getTag();
        }
        holder=(Holder) convertView.getTag();

        holder.showNoiDung(item);

        return convertView;
    }
}
