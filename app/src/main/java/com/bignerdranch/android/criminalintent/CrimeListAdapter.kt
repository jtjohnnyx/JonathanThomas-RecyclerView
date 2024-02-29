package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemSeriouscrimeBinding

class CrimeHolder(
    private val binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        when (binding) {
            is ListItemCrimeBinding -> {
                binding.crimeTitle.text = crime.title
                binding.crimeDate.text = crime.date.toString()

                binding.root.setOnClickListener {
                    Toast.makeText(
                        binding.root.context,
                        "${crime.title} clicked!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        when (binding) {
            is ListItemSeriouscrimeBinding -> {
                binding.crimeTitle.text = crime.title + " [Serious]"
                binding.crimeDate.text = crime.date.toString()

                binding.root.setOnClickListener {
                    Toast.makeText(
                        binding.root.context,
                        "${crime.title} clicked!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                binding.button.setOnClickListener {
                    Toast.makeText(
                        binding.root.context,
                        "Police Called!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>,
    private val normal: Int = 0,
    private val serious: Int = 1
) : RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewBinding = when (viewType) {
            normal -> ListItemCrimeBinding.inflate(inflater, parent, false)
            serious -> ListItemSeriouscrimeBinding.inflate(inflater, parent, false)
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
        return CrimeHolder(binding)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime)
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        val crime = crimes[position]
        return if (crime.requiresPolice)
            0
        else
            1
    }

}
