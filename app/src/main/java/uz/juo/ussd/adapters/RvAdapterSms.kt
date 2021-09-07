package uz.juo.ussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.juo.ussd.databinding.RvTestItemBinding
import uz.juo.ussd.models.SmsPaketlar

class RvAdapterSms(var list: ArrayList<SmsPaketlar>, var itemCLick: setOnCLick) :
    RecyclerView.Adapter<RvAdapterSms.Vh>() {
    inner class Vh(var item: RvTestItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun onBind(data: SmsPaketlar, position: Int) {
            item.rvItemTv.text = data.name
            item.rvItemDescTv.text = data.price + "\n" + data.count_sms + "\n" + data.day_sms
            item.root.setOnClickListener {
                itemCLick.itemOnClick(list[position], position)
            }
            item.active.setOnClickListener {
                itemCLick.itemOnClick(list[position], position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh = Vh(
        RvTestItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
    interface setOnCLick {
        fun itemOnClick(data: SmsPaketlar, position: Int)
        fun itemActiveClick(data: SmsPaketlar, position: Int)
    }
}