package com.example.drawerlayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.MoneyViewHolder> {
    private List<Money> mMoneyList;
    private ClickItemMoney mClickItemMoney;

    public interface ClickItemMoney{
        void updateMoney(Money money);
        void deleteMoney(Money money);
    }

    public MoneyAdapter(ClickItemMoney clickItemMoney) {
        mClickItemMoney = clickItemMoney;
    }

    public   void setData(List<Money> list){
        this.mMoneyList = list;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public MoneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        return new MoneyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyViewHolder holder, int position) {
        Money money = mMoneyList.get(position);
        if (money == null){
            return;
        }
        holder.tvMoney.setText(money.getMoney());
        holder.tvPrice.setText(money.getPrice());
        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickItemMoney.updateMoney(money);
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickItemMoney.deleteMoney(money);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mMoneyList != null){
            return mMoneyList.size();
        }
        return 0;
    }

    public class MoneyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMoney;
        private TextView tvPrice;
        private Button btn_update;
        private Button btn_delete;

        public MoneyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMoney = itemView.findViewById(R.id.tv_Money);
            tvPrice = itemView.findViewById(R.id.tv_Price);
            btn_update = itemView.findViewById(R.id.btn_update);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }

}
