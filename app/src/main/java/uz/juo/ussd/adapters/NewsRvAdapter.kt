package uz.juo.ussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.juo.ussd.databinding.RvItem2Binding

class NewsRvAdapter(var list: ArrayList<String>, var itemCLick: setOnCLick) :
    RecyclerView.Adapter<NewsRvAdapter.VhNews>() {
    inner class VhNews(var item: RvItem2Binding) : RecyclerView.ViewHolder(item.root) {
        fun onBind(data: String, position: Int) {
            item.root.setOnClickListener {
                itemCLick.itemOnClick(list[position], position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhNews = VhNews(
        RvItem2Binding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: VhNews, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
    interface setOnCLick {
        fun itemOnClick(data: String, position: Int)
    }

}