package polinema.ac.id.utsony.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import polinema.ac.id.utsony.DataHelper;
import polinema.ac.id.utsony.HomeActivity;
import polinema.ac.id.utsony.R;
import polinema.ac.id.utsony.models.SuperHero;

public class SuperHeroAdapter extends RecyclerView.Adapter<SuperHeroAdapter.MyViewHolder> {
    List<SuperHero> heroList;
    private static ClickListener clickListener;
    DataHelper dbcenter;

    public SuperHeroAdapter(List<SuperHero> heroList) {
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        dbcenter = new DataHelper(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View superHeroView = layoutInflater.inflate(R.layout.item_super_hero,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(superHeroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SuperHero hero = heroList.get(position);
        holder.txt.setText(hero.getTugas());
        holder.txt1.setText(hero.getTarget());
        holder.txt2.setText(hero.getNo());
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txt, txt1, txt2;
        public CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txt = itemView.findViewById(R.id.tugas);
            txt1 = itemView.findViewById(R.id.target);
            txt2 = itemView.findViewById(R.id.no);
            checkBox = itemView.findViewById(R.id.chk_delete);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // makes the set disappear when checkbox is ticked.
                        if(isChecked){
                            final String selection = heroList.get(getAdapterPosition()).getTugas();
                            SQLiteDatabase db = dbcenter.getWritableDatabase();
                            db.execSQL("delete from tugas where tugas='"+selection+"'");
                            HomeActivity.ma.clear();
                            HomeActivity.ma.RefreshList();
                        }

                    }
                });
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        SuperHeroAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
