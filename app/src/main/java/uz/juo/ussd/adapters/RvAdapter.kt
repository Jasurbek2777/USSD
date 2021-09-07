package uz.juo.ussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.juo.ussd.databinding.RvItem2Binding
import uz.juo.ussd.models.News

class RvAdapter(var list: ArrayList<News>, var itemCLick: setOnCLick) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var item: RvItem2Binding) : RecyclerView.ViewHolder(item.root) {
        fun onBind(data: News, position: Int) {
            item.root.setOnClickListener {
                itemCLick.itemOnClick(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh = Vh(
        RvItem2Binding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
    interface setOnCLick {
        fun itemOnClick(data: News, position: Int)
        fun itemActiveClick(data: News, position: Int)
    }
}